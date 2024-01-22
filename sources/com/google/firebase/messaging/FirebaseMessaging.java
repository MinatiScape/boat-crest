package com.google.firebase.messaging;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.j0;
import com.google.firebase.messaging.m0;
import com.google.firebase.platforminfo.UserAgentPublisher;
import com.mappls.android.lms.MapplsLMSDbAdapter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
/* loaded from: classes10.dex */
public class FirebaseMessaging {
    @NonNull
    @Deprecated
    public static final String INSTANCE_ID_SCOPE = "FCM";
    public static final long n = TimeUnit.HOURS.toSeconds(8);
    @SuppressLint({"StaticFieldLeak"})
    public static m0 o;
    @Nullable
    @SuppressLint({"FirebaseUnknownNullness"})
    @VisibleForTesting
    public static TransportFactory p;
    @GuardedBy("FirebaseMessaging.class")
    @VisibleForTesting
    public static ScheduledExecutorService q;

    /* renamed from: a  reason: collision with root package name */
    public final FirebaseApp f11330a;
    @Nullable
    public final FirebaseInstanceIdInternal b;
    public final FirebaseInstallationsApi c;
    public final Context d;
    public final d0 e;
    public final j0 f;
    public final a g;
    public final Executor h;
    public final Executor i;
    public final Task<r0> j;
    public final h0 k;
    @GuardedBy("this")
    public boolean l;
    public final Application.ActivityLifecycleCallbacks m;

    /* loaded from: classes10.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public final Subscriber f11331a;
        @GuardedBy("this")
        public boolean b;
        @Nullable
        @GuardedBy("this")
        public EventHandler<DataCollectionDefaultChange> c;
        @Nullable
        @GuardedBy("this")
        public Boolean d;

        public a(Subscriber subscriber) {
            this.f11331a = subscriber;
        }

        public synchronized void a() {
            if (this.b) {
                return;
            }
            Boolean d = d();
            this.d = d;
            if (d == null) {
                EventHandler<DataCollectionDefaultChange> eventHandler = new EventHandler(this) { // from class: com.google.firebase.messaging.z

                    /* renamed from: a  reason: collision with root package name */
                    public final FirebaseMessaging.a f11372a;

                    {
                        this.f11372a = this;
                    }

                    @Override // com.google.firebase.events.EventHandler
                    public void handle(Event event) {
                        this.f11372a.c(event);
                    }
                };
                this.c = eventHandler;
                this.f11331a.subscribe(DataCollectionDefaultChange.class, eventHandler);
            }
            this.b = true;
        }

        public synchronized boolean b() {
            boolean isDataCollectionDefaultEnabled;
            a();
            Boolean bool = this.d;
            if (bool == null) {
                isDataCollectionDefaultEnabled = FirebaseMessaging.this.f11330a.isDataCollectionDefaultEnabled();
            } else {
                isDataCollectionDefaultEnabled = bool.booleanValue();
            }
            return isDataCollectionDefaultEnabled;
        }

        public final /* synthetic */ void c(Event event) {
            if (b()) {
                FirebaseMessaging.this.w();
            }
        }

        @Nullable
        public final Boolean d() {
            ApplicationInfo applicationInfo;
            Bundle bundle;
            Context applicationContext = FirebaseMessaging.this.f11330a.getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences("com.google.firebase.messaging", 0);
            if (sharedPreferences.contains("auto_init")) {
                return Boolean.valueOf(sharedPreferences.getBoolean("auto_init", false));
            }
            try {
                PackageManager packageManager = applicationContext.getPackageManager();
                if (packageManager == null || (applicationInfo = packageManager.getApplicationInfo(applicationContext.getPackageName(), 128)) == null || (bundle = applicationInfo.metaData) == null || !bundle.containsKey("firebase_messaging_auto_init_enabled")) {
                    return null;
                }
                return Boolean.valueOf(applicationInfo.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        public synchronized void e(boolean z) {
            a();
            EventHandler<DataCollectionDefaultChange> eventHandler = this.c;
            if (eventHandler != null) {
                this.f11331a.unsubscribe(DataCollectionDefaultChange.class, eventHandler);
                this.c = null;
            }
            SharedPreferences.Editor edit = FirebaseMessaging.this.f11330a.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
            edit.putBoolean("auto_init", z);
            edit.apply();
            if (z) {
                FirebaseMessaging.this.w();
            }
            this.d = Boolean.valueOf(z);
        }
    }

    public FirebaseMessaging(FirebaseApp firebaseApp, @Nullable FirebaseInstanceIdInternal firebaseInstanceIdInternal, Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseInstallationsApi firebaseInstallationsApi, @Nullable TransportFactory transportFactory, Subscriber subscriber) {
        this(firebaseApp, firebaseInstanceIdInternal, provider, provider2, firebaseInstallationsApi, transportFactory, subscriber, new h0(firebaseApp.getApplicationContext()));
    }

    @NonNull
    public static synchronized FirebaseMessaging getInstance() {
        FirebaseMessaging firebaseMessaging;
        synchronized (FirebaseMessaging.class) {
            firebaseMessaging = getInstance(FirebaseApp.getInstance());
        }
        return firebaseMessaging;
    }

    @Nullable
    public static TransportFactory getTransportFactory() {
        return p;
    }

    public String c() throws IOException {
        FirebaseInstanceIdInternal firebaseInstanceIdInternal = this.b;
        if (firebaseInstanceIdInternal != null) {
            try {
                return (String) Tasks.await(firebaseInstanceIdInternal.getTokenTask());
            } catch (InterruptedException | ExecutionException e) {
                throw new IOException(e);
            }
        }
        m0.a h = h();
        if (!y(h)) {
            return h.f11350a;
        }
        final String c = h0.c(this.f11330a);
        try {
            String str = (String) Tasks.await(this.c.getId().continueWithTask(l.d(), new Continuation(this, c) { // from class: com.google.firebase.messaging.x

                /* renamed from: a  reason: collision with root package name */
                public final FirebaseMessaging f11369a;
                public final String b;

                {
                    this.f11369a = this;
                    this.b = c;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public Object then(Task task) {
                    return this.f11369a.l(this.b, task);
                }
            }));
            o.g(g(), c, str, this.k.a());
            if (h == null || !str.equals(h.f11350a)) {
                d(str);
            }
            return str;
        } catch (InterruptedException | ExecutionException e2) {
            throw new IOException(e2);
        }
    }

    @NonNull
    public Task<Void> deleteToken() {
        if (this.b != null) {
            final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            this.h.execute(new Runnable(this, taskCompletionSource) { // from class: com.google.firebase.messaging.t
                public final FirebaseMessaging h;
                public final TaskCompletionSource i;

                {
                    this.h = this;
                    this.i = taskCompletionSource;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.h.m(this.i);
                }
            });
            return taskCompletionSource.getTask();
        } else if (h() == null) {
            return Tasks.forResult(null);
        } else {
            final ExecutorService d = l.d();
            return this.c.getId().continueWithTask(d, new Continuation(this, d) { // from class: com.google.firebase.messaging.u

                /* renamed from: a  reason: collision with root package name */
                public final FirebaseMessaging f11364a;
                public final ExecutorService b;

                {
                    this.f11364a = this;
                    this.b = d;
                }

                @Override // com.google.android.gms.tasks.Continuation
                public Object then(Task task) {
                    return this.f11364a.o(this.b, task);
                }
            });
        }
    }

    @NonNull
    public boolean deliveryMetricsExportToBigQueryEnabled() {
        return MessagingAnalytics.a();
    }

    public void e(Runnable runnable, long j) {
        synchronized (FirebaseMessaging.class) {
            if (q == null) {
                q = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("TAG"));
            }
            q.schedule(runnable, j, TimeUnit.SECONDS);
        }
    }

    public Context f() {
        return this.d;
    }

    public final String g() {
        return FirebaseApp.DEFAULT_APP_NAME.equals(this.f11330a.getName()) ? "" : this.f11330a.getPersistenceKey();
    }

    @NonNull
    public Task<String> getToken() {
        FirebaseInstanceIdInternal firebaseInstanceIdInternal = this.b;
        if (firebaseInstanceIdInternal != null) {
            return firebaseInstanceIdInternal.getTokenTask();
        }
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.h.execute(new Runnable(this, taskCompletionSource) { // from class: com.google.firebase.messaging.s
            public final FirebaseMessaging h;
            public final TaskCompletionSource i;

            {
                this.h = this;
                this.i = taskCompletionSource;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.p(this.i);
            }
        });
        return taskCompletionSource.getTask();
    }

    @Nullable
    @VisibleForTesting
    public m0.a h() {
        return o.e(g(), h0.c(this.f11330a));
    }

    /* renamed from: i */
    public final void d(String str) {
        if (FirebaseApp.DEFAULT_APP_NAME.equals(this.f11330a.getName())) {
            if (Log.isLoggable(Constants.TAG, 3)) {
                String valueOf = String.valueOf(this.f11330a.getName());
                Log.d(Constants.TAG, valueOf.length() != 0 ? "Invoking onNewToken for app: ".concat(valueOf) : new String("Invoking onNewToken for app: "));
            }
            Intent intent = new Intent("com.google.firebase.messaging.NEW_TOKEN");
            intent.putExtra(MapplsLMSDbAdapter.KEY_TOKEN, str);
            new FcmBroadcastProcessor(this.d).process(intent);
        }
    }

    public boolean isAutoInitEnabled() {
        return this.g.b();
    }

    @VisibleForTesting
    public boolean j() {
        return this.k.g();
    }

    public final /* synthetic */ Task k(Task task) {
        return this.e.e((String) task.getResult());
    }

    public final /* synthetic */ Task l(String str, final Task task) throws Exception {
        return this.f.a(str, new j0.a(this, task) { // from class: com.google.firebase.messaging.y

            /* renamed from: a  reason: collision with root package name */
            public final FirebaseMessaging f11370a;
            public final Task b;

            {
                this.f11370a = this;
                this.b = task;
            }

            @Override // com.google.firebase.messaging.j0.a
            public Task start() {
                return this.f11370a.k(this.b);
            }
        });
    }

    public final /* synthetic */ void m(TaskCompletionSource taskCompletionSource) {
        try {
            this.b.deleteToken(h0.c(this.f11330a), INSTANCE_ID_SCOPE);
            taskCompletionSource.setResult(null);
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    public final /* synthetic */ Void n(Task task) throws Exception {
        o.d(g(), h0.c(this.f11330a));
        return null;
    }

    public final /* synthetic */ Task o(ExecutorService executorService, Task task) throws Exception {
        return this.e.b((String) task.getResult()).continueWith(executorService, new Continuation(this) { // from class: com.google.firebase.messaging.o

            /* renamed from: a  reason: collision with root package name */
            public final FirebaseMessaging f11353a;

            {
                this.f11353a = this;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public Object then(Task task2) {
                this.f11353a.n(task2);
                return null;
            }
        });
    }

    public final /* synthetic */ void p(TaskCompletionSource taskCompletionSource) {
        try {
            taskCompletionSource.setResult(c());
        } catch (Exception e) {
            taskCompletionSource.setException(e);
        }
    }

    public final /* synthetic */ void q() {
        if (isAutoInitEnabled()) {
            w();
        }
    }

    public final /* synthetic */ void r(r0 r0Var) {
        if (isAutoInitEnabled()) {
            r0Var.p();
        }
    }

    public void send(@NonNull RemoteMessage remoteMessage) {
        if (!TextUtils.isEmpty(remoteMessage.getTo())) {
            Intent intent = new Intent("com.google.android.gcm.intent.SEND");
            Intent intent2 = new Intent();
            intent2.setPackage("com.google.example.invalidpackage");
            intent.putExtra("app", PendingIntent.getBroadcast(this.d, 0, intent2, Build.VERSION.SDK_INT >= 23 ? 67108864 : 0));
            intent.setPackage("com.google.android.gms");
            remoteMessage.b(intent);
            this.d.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
            return;
        }
        throw new IllegalArgumentException("Missing 'to'");
    }

    public void setAutoInitEnabled(boolean z) {
        this.g.e(z);
    }

    public void setDeliveryMetricsExportToBigQuery(boolean z) {
        MessagingAnalytics.u(z);
    }

    @NonNull
    public Task<Void> subscribeToTopic(@NonNull final String str) {
        return this.j.onSuccessTask(new SuccessContinuation(str) { // from class: com.google.firebase.messaging.v

            /* renamed from: a  reason: collision with root package name */
            public final String f11365a;

            {
                this.f11365a = str;
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public Task then(Object obj) {
                Task q2;
                q2 = ((r0) obj).q(this.f11365a);
                return q2;
            }
        });
    }

    public synchronized void u(boolean z) {
        this.l = z;
    }

    @NonNull
    public Task<Void> unsubscribeFromTopic(@NonNull final String str) {
        return this.j.onSuccessTask(new SuccessContinuation(str) { // from class: com.google.firebase.messaging.w

            /* renamed from: a  reason: collision with root package name */
            public final String f11367a;

            {
                this.f11367a = str;
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public Task then(Object obj) {
                Task t;
                t = ((r0) obj).t(this.f11367a);
                return t;
            }
        });
    }

    public final synchronized void v() {
        if (this.l) {
            return;
        }
        x(0L);
    }

    public final void w() {
        FirebaseInstanceIdInternal firebaseInstanceIdInternal = this.b;
        if (firebaseInstanceIdInternal != null) {
            firebaseInstanceIdInternal.getToken();
        } else if (y(h())) {
            v();
        }
    }

    public synchronized void x(long j) {
        e(new n0(this, Math.min(Math.max(30L, j + j), n)), j);
        this.l = true;
    }

    @VisibleForTesting
    public boolean y(@Nullable m0.a aVar) {
        return aVar == null || aVar.b(this.k.a());
    }

    @NonNull
    @Keep
    public static synchronized FirebaseMessaging getInstance(@NonNull FirebaseApp firebaseApp) {
        FirebaseMessaging firebaseMessaging;
        synchronized (FirebaseMessaging.class) {
            firebaseMessaging = (FirebaseMessaging) firebaseApp.get(FirebaseMessaging.class);
            Preconditions.checkNotNull(firebaseMessaging, "Firebase Messaging component is not present");
        }
        return firebaseMessaging;
    }

    public FirebaseMessaging(FirebaseApp firebaseApp, @Nullable FirebaseInstanceIdInternal firebaseInstanceIdInternal, Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseInstallationsApi firebaseInstallationsApi, @Nullable TransportFactory transportFactory, Subscriber subscriber, h0 h0Var) {
        this(firebaseApp, firebaseInstanceIdInternal, firebaseInstallationsApi, transportFactory, subscriber, h0Var, new d0(firebaseApp, h0Var, provider, provider2, firebaseInstallationsApi), l.e(), l.b());
    }

    public FirebaseMessaging(FirebaseApp firebaseApp, @Nullable FirebaseInstanceIdInternal firebaseInstanceIdInternal, FirebaseInstallationsApi firebaseInstallationsApi, @Nullable TransportFactory transportFactory, Subscriber subscriber, h0 h0Var, d0 d0Var, Executor executor, Executor executor2) {
        this.l = false;
        p = transportFactory;
        this.f11330a = firebaseApp;
        this.b = firebaseInstanceIdInternal;
        this.c = firebaseInstallationsApi;
        this.g = new a(subscriber);
        Context applicationContext = firebaseApp.getApplicationContext();
        this.d = applicationContext;
        m mVar = new m();
        this.m = mVar;
        this.k = h0Var;
        this.i = executor;
        this.e = d0Var;
        this.f = new j0(executor);
        this.h = executor2;
        Context applicationContext2 = firebaseApp.getApplicationContext();
        if (applicationContext2 instanceof Application) {
            ((Application) applicationContext2).registerActivityLifecycleCallbacks(mVar);
        } else {
            String valueOf = String.valueOf(applicationContext2);
            StringBuilder sb = new StringBuilder(valueOf.length() + 125);
            sb.append("Context ");
            sb.append(valueOf);
            sb.append(" was not an application, can't register for lifecycle callbacks. Some notification events may be dropped as a result.");
            Log.w(Constants.TAG, sb.toString());
        }
        if (firebaseInstanceIdInternal != null) {
            firebaseInstanceIdInternal.addNewTokenListener(new FirebaseInstanceIdInternal.NewTokenListener(this) { // from class: com.google.firebase.messaging.n

                /* renamed from: a  reason: collision with root package name */
                public final FirebaseMessaging f11351a;

                {
                    this.f11351a = this;
                }

                @Override // com.google.firebase.iid.internal.FirebaseInstanceIdInternal.NewTokenListener
                public void onNewToken(String str) {
                    this.f11351a.d(str);
                }
            });
        }
        synchronized (FirebaseMessaging.class) {
            if (o == null) {
                o = new m0(applicationContext);
            }
        }
        executor2.execute(new Runnable(this) { // from class: com.google.firebase.messaging.p
            public final FirebaseMessaging h;

            {
                this.h = this;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.q();
            }
        });
        Task<r0> e = r0.e(this, firebaseInstallationsApi, h0Var, d0Var, applicationContext, l.f());
        this.j = e;
        e.addOnSuccessListener(l.g(), new OnSuccessListener(this) { // from class: com.google.firebase.messaging.q

            /* renamed from: a  reason: collision with root package name */
            public final FirebaseMessaging f11356a;

            {
                this.f11356a = this;
            }

            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(Object obj) {
                this.f11356a.r((r0) obj);
            }
        });
    }
}
