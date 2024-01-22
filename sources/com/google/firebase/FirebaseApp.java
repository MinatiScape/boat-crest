package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.collection.ArrayMap;
import androidx.core.os.UserManagerCompat;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentDiscovery;
import com.google.firebase.components.ComponentDiscoveryService;
import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.events.Publisher;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.DataCollectionConfigStorage;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public class FirebaseApp {
    @NonNull
    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    public static final Object j = new Object();
    public static final Executor k = new c();
    @GuardedBy("LOCK")
    public static final Map<String, FirebaseApp> l = new ArrayMap();

    /* renamed from: a  reason: collision with root package name */
    public final Context f11064a;
    public final String b;
    public final FirebaseOptions c;
    public final ComponentRuntime d;
    public final Lazy<DataCollectionConfigStorage> g;
    public final AtomicBoolean e = new AtomicBoolean(false);
    public final AtomicBoolean f = new AtomicBoolean();
    public final List<BackgroundStateChangeListener> h = new CopyOnWriteArrayList();
    public final List<FirebaseAppLifecycleListener> i = new CopyOnWriteArrayList();

    @KeepForSdk
    /* loaded from: classes10.dex */
    public interface BackgroundStateChangeListener {
        @KeepForSdk
        void onBackgroundStateChanged(boolean z);
    }

    @TargetApi(14)
    /* loaded from: classes10.dex */
    public static class b implements BackgroundDetector.BackgroundStateChangeListener {

        /* renamed from: a  reason: collision with root package name */
        public static AtomicReference<b> f11065a = new AtomicReference<>();

        public static void b(Context context) {
            if (PlatformVersion.isAtLeastIceCreamSandwich() && (context.getApplicationContext() instanceof Application)) {
                Application application = (Application) context.getApplicationContext();
                if (f11065a.get() == null) {
                    b bVar = new b();
                    if (f11065a.compareAndSet(null, bVar)) {
                        BackgroundDetector.initialize(application);
                        BackgroundDetector.getInstance().addListener(bVar);
                    }
                }
            }
        }

        @Override // com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener
        public void onBackgroundStateChanged(boolean z) {
            synchronized (FirebaseApp.j) {
                Iterator it = new ArrayList(FirebaseApp.l.values()).iterator();
                while (it.hasNext()) {
                    FirebaseApp firebaseApp = (FirebaseApp) it.next();
                    if (firebaseApp.e.get()) {
                        firebaseApp.k(z);
                    }
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    public static class c implements Executor {
        public static final Handler h = new Handler(Looper.getMainLooper());

        public c() {
        }

        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            h.post(runnable);
        }
    }

    @TargetApi(24)
    /* loaded from: classes10.dex */
    public static class d extends BroadcastReceiver {
        public static AtomicReference<d> b = new AtomicReference<>();

        /* renamed from: a  reason: collision with root package name */
        public final Context f11066a;

        public d(Context context) {
            this.f11066a = context;
        }

        public static void b(Context context) {
            if (b.get() == null) {
                d dVar = new d(context);
                if (b.compareAndSet(null, dVar)) {
                    context.registerReceiver(dVar, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public void c() {
            this.f11066a.unregisterReceiver(this);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.j) {
                for (FirebaseApp firebaseApp : FirebaseApp.l.values()) {
                    firebaseApp.h();
                }
            }
            c();
        }
    }

    public FirebaseApp(final Context context, String str, FirebaseOptions firebaseOptions) {
        this.f11064a = (Context) Preconditions.checkNotNull(context);
        this.b = Preconditions.checkNotEmpty(str);
        this.c = (FirebaseOptions) Preconditions.checkNotNull(firebaseOptions);
        this.d = ComponentRuntime.builder(k).addLazyComponentRegistrars(ComponentDiscovery.forContext(context, ComponentDiscoveryService.class).discoverLazy()).addComponentRegistrar(new FirebaseCommonRegistrar()).addComponent(Component.of(context, Context.class, new Class[0])).addComponent(Component.of(this, FirebaseApp.class, new Class[0])).addComponent(Component.of(firebaseOptions, FirebaseOptions.class, new Class[0])).build();
        this.g = new Lazy<>(new Provider() { // from class: com.google.firebase.a
            @Override // com.google.firebase.inject.Provider
            public final Object get() {
                DataCollectionConfigStorage i;
                i = FirebaseApp.this.i(context);
                return i;
            }
        });
    }

    @VisibleForTesting
    public static void clearInstancesForTest() {
        synchronized (j) {
            l.clear();
        }
    }

    public static List<String> g() {
        ArrayList arrayList = new ArrayList();
        synchronized (j) {
            for (FirebaseApp firebaseApp : l.values()) {
                arrayList.add(firebaseApp.getName());
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    @NonNull
    public static List<FirebaseApp> getApps(@NonNull Context context) {
        ArrayList arrayList;
        synchronized (j) {
            arrayList = new ArrayList(l.values());
        }
        return arrayList;
    }

    @NonNull
    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (j) {
            firebaseApp = l.get(DEFAULT_APP_NAME);
            if (firebaseApp == null) {
                throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + ProcessUtils.getMyProcessName() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
            }
        }
        return firebaseApp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DataCollectionConfigStorage i(Context context) {
        return new DataCollectionConfigStorage(context, getPersistenceKey(), (Publisher) this.d.get(Publisher.class));
    }

    @Nullable
    public static FirebaseApp initializeApp(@NonNull Context context) {
        synchronized (j) {
            if (l.containsKey(DEFAULT_APP_NAME)) {
                return getInstance();
            }
            FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
            if (fromResource == null) {
                Log.w("FirebaseApp", "Default FirebaseApp failed to initialize because no default options were found. This usually means that com.google.gms:google-services was not applied to your gradle project.");
                return null;
            }
            return initializeApp(context, fromResource);
        }
    }

    public static String j(@NonNull String str) {
        return str.trim();
    }

    @KeepForSdk
    public void addBackgroundStateChangeListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        f();
        if (this.e.get() && BackgroundDetector.getInstance().isInBackground()) {
            backgroundStateChangeListener.onBackgroundStateChanged(true);
        }
        this.h.add(backgroundStateChangeListener);
    }

    @KeepForSdk
    public void addLifecycleEventListener(@NonNull FirebaseAppLifecycleListener firebaseAppLifecycleListener) {
        f();
        Preconditions.checkNotNull(firebaseAppLifecycleListener);
        this.i.add(firebaseAppLifecycleListener);
    }

    public void delete() {
        if (this.f.compareAndSet(false, true)) {
            synchronized (j) {
                l.remove(this.b);
            }
            l();
        }
    }

    public boolean equals(Object obj) {
        if (obj instanceof FirebaseApp) {
            return this.b.equals(((FirebaseApp) obj).getName());
        }
        return false;
    }

    public final void f() {
        Preconditions.checkState(!this.f.get(), "FirebaseApp was deleted");
    }

    @KeepForSdk
    public <T> T get(Class<T> cls) {
        f();
        return (T) this.d.get(cls);
    }

    @NonNull
    public Context getApplicationContext() {
        f();
        return this.f11064a;
    }

    @NonNull
    public String getName() {
        f();
        return this.b;
    }

    @NonNull
    public FirebaseOptions getOptions() {
        f();
        return this.c;
    }

    @KeepForSdk
    public String getPersistenceKey() {
        return Base64Utils.encodeUrlSafeNoPadding(getName().getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(getOptions().getApplicationId().getBytes(Charset.defaultCharset()));
    }

    public final void h() {
        if (!UserManagerCompat.isUserUnlocked(this.f11064a)) {
            Log.i("FirebaseApp", "Device in Direct Boot Mode: postponing initialization of Firebase APIs for app " + getName());
            d.b(this.f11064a);
            return;
        }
        Log.i("FirebaseApp", "Device unlocked: initializing all Firebase APIs for app " + getName());
        this.d.initializeEagerComponents(isDefaultApp());
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    @KeepForSdk
    public boolean isDataCollectionDefaultEnabled() {
        f();
        return this.g.get().isEnabled();
    }

    @KeepForSdk
    @VisibleForTesting
    public boolean isDefaultApp() {
        return DEFAULT_APP_NAME.equals(getName());
    }

    public final void k(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (BackgroundStateChangeListener backgroundStateChangeListener : this.h) {
            backgroundStateChangeListener.onBackgroundStateChanged(z);
        }
    }

    public final void l() {
        for (FirebaseAppLifecycleListener firebaseAppLifecycleListener : this.i) {
            firebaseAppLifecycleListener.onDeleted(this.b, this.c);
        }
    }

    @KeepForSdk
    public void removeBackgroundStateChangeListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        f();
        this.h.remove(backgroundStateChangeListener);
    }

    @KeepForSdk
    public void removeLifecycleEventListener(@NonNull FirebaseAppLifecycleListener firebaseAppLifecycleListener) {
        f();
        Preconditions.checkNotNull(firebaseAppLifecycleListener);
        this.i.remove(firebaseAppLifecycleListener);
    }

    public void setAutomaticResourceManagementEnabled(boolean z) {
        f();
        if (this.e.compareAndSet(!z, z)) {
            boolean isInBackground = BackgroundDetector.getInstance().isInBackground();
            if (z && isInBackground) {
                k(true);
            } else if (z || !isInBackground) {
            } else {
                k(false);
            }
        }
    }

    @KeepForSdk
    public void setDataCollectionDefaultEnabled(Boolean bool) {
        f();
        this.g.get().setEnabled(bool);
    }

    public String toString() {
        return Objects.toStringHelper(this).add(AppMeasurementSdk.ConditionalUserProperty.NAME, this.b).add("options", this.c).toString();
    }

    @KeepForSdk
    @Deprecated
    public void setDataCollectionDefaultEnabled(boolean z) {
        setDataCollectionDefaultEnabled(Boolean.valueOf(z));
    }

    @KeepForSdk
    public static String getPersistenceKey(String str, FirebaseOptions firebaseOptions) {
        return Base64Utils.encodeUrlSafeNoPadding(str.getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(firebaseOptions.getApplicationId().getBytes(Charset.defaultCharset()));
    }

    @NonNull
    public static FirebaseApp getInstance(@NonNull String str) {
        FirebaseApp firebaseApp;
        List<String> g;
        String str2;
        synchronized (j) {
            firebaseApp = l.get(j(str));
            if (firebaseApp == null) {
                if (g().isEmpty()) {
                    str2 = "";
                } else {
                    str2 = "Available app names: " + TextUtils.join(", ", g);
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", str, str2));
            }
        }
        return firebaseApp;
    }

    @NonNull
    public static FirebaseApp initializeApp(@NonNull Context context, @NonNull FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, DEFAULT_APP_NAME);
    }

    @NonNull
    public static FirebaseApp initializeApp(@NonNull Context context, @NonNull FirebaseOptions firebaseOptions, @NonNull String str) {
        FirebaseApp firebaseApp;
        b.b(context);
        String j2 = j(str);
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (j) {
            Map<String, FirebaseApp> map = l;
            boolean z = !map.containsKey(j2);
            Preconditions.checkState(z, "FirebaseApp name " + j2 + " already exists!");
            Preconditions.checkNotNull(context, "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, j2, firebaseOptions);
            map.put(j2, firebaseApp);
        }
        firebaseApp.h();
        return firebaseApp;
    }
}
