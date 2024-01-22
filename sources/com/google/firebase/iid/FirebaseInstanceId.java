package com.google.firebase.iid;

import android.os.Build;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.heartbeatinfo.HeartBeatInfo;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.iid.p;
import com.google.firebase.iid.q;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.platforminfo.UserAgentPublisher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.GuardedBy;
import org.slf4j.Marker;
@Deprecated
/* loaded from: classes10.dex */
public class FirebaseInstanceId {
    public static q j;
    @VisibleForTesting
    @GuardedBy("FirebaseInstanceId.class")
    public static ScheduledExecutorService l;
    @VisibleForTesting

    /* renamed from: a  reason: collision with root package name */
    public final Executor f11283a;
    public final FirebaseApp b;
    public final Metadata c;
    public final GmsRpc d;
    public final p e;
    public final FirebaseInstallationsApi f;
    @GuardedBy("this")
    public boolean g;
    public final List<FirebaseInstanceIdInternal.NewTokenListener> h;
    public static final long i = TimeUnit.HOURS.toSeconds(8);
    public static final Pattern k = Pattern.compile("\\AA[\\w-]{38}\\z");

    public FirebaseInstanceId(FirebaseApp firebaseApp, Metadata metadata, Executor executor, Executor executor2, Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseInstallationsApi firebaseInstallationsApi) {
        this.g = false;
        this.h = new ArrayList();
        if (Metadata.getDefaultSenderId(firebaseApp) != null) {
            synchronized (FirebaseInstanceId.class) {
                if (j == null) {
                    j = new q(firebaseApp.getApplicationContext());
                }
            }
            this.b = firebaseApp;
            this.c = metadata;
            this.d = new GmsRpc(firebaseApp, metadata, provider, provider2, firebaseInstallationsApi);
            this.f11283a = executor2;
            this.e = new p(executor);
            this.f = firebaseInstallationsApi;
            return;
        }
        throw new IllegalStateException("FirebaseInstanceId failed to initialize, FirebaseApp is missing project ID");
    }

    public static <T> T c(@NonNull Task<T> task) throws InterruptedException {
        Preconditions.checkNotNull(task, "Task must not be null");
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        task.addOnCompleteListener(d.h, new OnCompleteListener(countDownLatch) { // from class: com.google.firebase.iid.e

            /* renamed from: a  reason: collision with root package name */
            public final CountDownLatch f11290a;

            {
                this.f11290a = countDownLatch;
            }

            @Override // com.google.android.gms.tasks.OnCompleteListener
            public void onComplete(Task task2) {
                this.f11290a.countDown();
            }
        });
        countDownLatch.await(30000L, TimeUnit.MILLISECONDS);
        return (T) j(task);
    }

    @VisibleForTesting
    @KeepForSdk
    public static synchronized void clearInstancesForTest() {
        synchronized (FirebaseInstanceId.class) {
            ScheduledExecutorService scheduledExecutorService = l;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
            l = null;
            j = null;
        }
    }

    public static void e(@NonNull FirebaseApp firebaseApp) {
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getProjectId(), "Please set your project ID. A valid Firebase project ID is required to communicate with Firebase server APIs: It identifies your project with Google.");
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getApplicationId(), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.");
        Preconditions.checkNotEmpty(firebaseApp.getOptions().getApiKey(), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.");
        Preconditions.checkArgument(p(firebaseApp.getOptions().getApplicationId()), "Please set your Application ID. A valid Firebase App ID is required to communicate with Firebase server APIs: It identifies your application with Firebase.Please refer to https://firebase.google.com/support/privacy/init-options.");
        Preconditions.checkArgument(o(firebaseApp.getOptions().getApiKey()), "Please set a valid API key. A Firebase API key is required to communicate with Firebase server APIs: It authenticates your project with Google.Please refer to https://firebase.google.com/support/privacy/init-options.");
    }

    @NonNull
    public static FirebaseInstanceId getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static <T> T j(@NonNull Task<T> task) {
        if (task.isSuccessful()) {
            return task.getResult();
        }
        if (!task.isCanceled()) {
            if (task.isComplete()) {
                throw new IllegalStateException(task.getException());
            }
            throw new IllegalThreadStateException("Firebase Installations getId Task has timed out.");
        }
        throw new CancellationException("Task is already canceled");
    }

    public static boolean n() {
        return Log.isLoggable("FirebaseInstanceId", 3) || (Build.VERSION.SDK_INT == 23 && Log.isLoggable("FirebaseInstanceId", 3));
    }

    public static boolean o(@Nonnull String str) {
        return k.matcher(str).matches();
    }

    public static boolean p(@Nonnull String str) {
        return str.contains(":");
    }

    public static String v(String str) {
        return (str.isEmpty() || str.equalsIgnoreCase("fcm") || str.equalsIgnoreCase("gcm")) ? Marker.ANY_MARKER : str;
    }

    public synchronized void A(long j2) {
        f(new s(this, Math.min(Math.max(30L, j2 + j2), i)), j2);
        this.g = true;
    }

    public boolean B(@Nullable q.a aVar) {
        return aVar == null || aVar.c(this.c.getAppVersionCode());
    }

    public void a(FirebaseInstanceIdInternal.NewTokenListener newTokenListener) {
        this.h.add(newTokenListener);
    }

    public final <T> T b(Task<T> task) throws IOException {
        try {
            return (T) Tasks.await(task, 30000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | TimeoutException unused) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                if ("INSTANCE_ID_RESET".equals(cause.getMessage())) {
                    w();
                }
                throw ((IOException) cause);
            } else if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else {
                throw new IOException(e);
            }
        }
    }

    public String d() throws IOException {
        return getToken(Metadata.getDefaultSenderId(this.b), Marker.ANY_MARKER);
    }

    @WorkerThread
    @Deprecated
    public void deleteInstanceId() throws IOException {
        e(this.b);
        if (Looper.getMainLooper() != Looper.myLooper()) {
            b(this.f.delete());
            w();
            return;
        }
        throw new IOException("MAIN_THREAD");
    }

    @WorkerThread
    @Deprecated
    public void deleteToken(@NonNull String str, @NonNull String str2) throws IOException {
        e(this.b);
        if (Looper.getMainLooper() != Looper.myLooper()) {
            String v = v(str2);
            b(this.d.deleteToken(h(), str, v));
            j.e(k(), str, v);
            return;
        }
        throw new IOException("MAIN_THREAD");
    }

    public void f(Runnable runnable, long j2) {
        synchronized (FirebaseInstanceId.class) {
            if (l == null) {
                l = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("FirebaseInstanceId"));
            }
            l.schedule(runnable, j2, TimeUnit.SECONDS);
        }
    }

    public FirebaseApp g() {
        return this.b;
    }

    public long getCreationTime() {
        return j.f(this.b.getPersistenceKey());
    }

    @NonNull
    @WorkerThread
    @Deprecated
    public String getId() {
        e(this.b);
        z();
        return h();
    }

    @NonNull
    @Deprecated
    public Task<InstanceIdResult> getInstanceId() {
        e(this.b);
        return i(Metadata.getDefaultSenderId(this.b), Marker.ANY_MARKER);
    }

    @Nullable
    @Deprecated
    public String getToken() {
        e(this.b);
        q.a l2 = l();
        if (B(l2)) {
            y();
        }
        return q.a.b(l2);
    }

    public String h() {
        try {
            j.k(this.b.getPersistenceKey());
            return (String) c(this.f.getId());
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public final Task<InstanceIdResult> i(final String str, String str2) {
        final String v = v(str2);
        return Tasks.forResult(null).continueWithTask(this.f11283a, new Continuation(this, str, v) { // from class: com.google.firebase.iid.c

            /* renamed from: a  reason: collision with root package name */
            public final FirebaseInstanceId f11289a;
            public final String b;
            public final String c;

            {
                this.f11289a = this;
                this.b = str;
                this.c = v;
            }

            @Override // com.google.android.gms.tasks.Continuation
            public Object then(Task task) {
                return this.f11289a.u(this.b, this.c, task);
            }
        });
    }

    @VisibleForTesting
    @KeepForSdk
    public boolean isFcmAutoInitEnabled() {
        throw new IllegalStateException("FirebaseMessaging version not supported. Update to latest version.");
    }

    @VisibleForTesting
    public boolean isGmsCorePresent() {
        return this.c.isGmscorePresent();
    }

    public final String k() {
        return FirebaseApp.DEFAULT_APP_NAME.equals(this.b.getName()) ? "" : this.b.getPersistenceKey();
    }

    @Nullable
    public q.a l() {
        return m(Metadata.getDefaultSenderId(this.b), Marker.ANY_MARKER);
    }

    @Nullable
    @VisibleForTesting
    public q.a m(String str, String str2) {
        return j.h(k(), str, str2);
    }

    public final /* synthetic */ Task r(String str, String str2, String str3, String str4) throws Exception {
        j.j(k(), str, str2, str4, this.c.getAppVersionCode());
        return Tasks.forResult(new k(str3, str4));
    }

    public final /* synthetic */ void s(q.a aVar, InstanceIdResult instanceIdResult) {
        String token = instanceIdResult.getToken();
        if (aVar == null || !token.equals(aVar.f11302a)) {
            for (FirebaseInstanceIdInternal.NewTokenListener newTokenListener : this.h) {
                newTokenListener.onNewToken(token);
            }
        }
    }

    @VisibleForTesting
    @KeepForSdk
    public void setFcmAutoInitEnabled(boolean z) {
        throw new IllegalStateException("FirebaseMessaging version not supported. Update to latest version.");
    }

    public final /* synthetic */ Task t(final String str, final String str2, final String str3, final q.a aVar) {
        return this.d.getToken(str, str2, str3).onSuccessTask(this.f11283a, new SuccessContinuation(this, str2, str3, str) { // from class: com.google.firebase.iid.g

            /* renamed from: a  reason: collision with root package name */
            public final FirebaseInstanceId f11292a;
            public final String b;
            public final String c;
            public final String d;

            {
                this.f11292a = this;
                this.b = str2;
                this.c = str3;
                this.d = str;
            }

            @Override // com.google.android.gms.tasks.SuccessContinuation
            public Task then(Object obj) {
                return this.f11292a.r(this.b, this.c, this.d, (String) obj);
            }
        }).addOnSuccessListener(h.h, new OnSuccessListener(this, aVar) { // from class: com.google.firebase.iid.i

            /* renamed from: a  reason: collision with root package name */
            public final FirebaseInstanceId f11293a;
            public final q.a b;

            {
                this.f11293a = this;
                this.b = aVar;
            }

            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(Object obj) {
                this.f11293a.s(this.b, (InstanceIdResult) obj);
            }
        });
    }

    public final /* synthetic */ Task u(final String str, final String str2, Task task) throws Exception {
        final String h = h();
        final q.a m = m(str, str2);
        if (!B(m)) {
            return Tasks.forResult(new k(h, m.f11302a));
        }
        return this.e.a(str, str2, new p.a(this, h, str, str2, m) { // from class: com.google.firebase.iid.f

            /* renamed from: a  reason: collision with root package name */
            public final FirebaseInstanceId f11291a;
            public final String b;
            public final String c;
            public final String d;
            public final q.a e;

            {
                this.f11291a = this;
                this.b = h;
                this.c = str;
                this.d = str2;
                this.e = m;
            }

            @Override // com.google.firebase.iid.p.a
            public Task start() {
                return this.f11291a.t(this.b, this.c, this.d, this.e);
            }
        });
    }

    public synchronized void w() {
        j.d();
    }

    public synchronized void x(boolean z) {
        this.g = z;
    }

    public synchronized void y() {
        if (this.g) {
            return;
        }
        A(0L);
    }

    public final void z() {
        if (B(l())) {
            y();
        }
    }

    @NonNull
    @Keep
    public static FirebaseInstanceId getInstance(@NonNull FirebaseApp firebaseApp) {
        e(firebaseApp);
        FirebaseInstanceId firebaseInstanceId = (FirebaseInstanceId) firebaseApp.get(FirebaseInstanceId.class);
        Preconditions.checkNotNull(firebaseInstanceId, "Firebase Instance ID component is not present");
        return firebaseInstanceId;
    }

    @Nullable
    @WorkerThread
    @Deprecated
    public String getToken(@NonNull String str, @NonNull String str2) throws IOException {
        e(this.b);
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return ((InstanceIdResult) b(i(str, str2))).getToken();
        }
        throw new IOException("MAIN_THREAD");
    }

    public FirebaseInstanceId(FirebaseApp firebaseApp, Provider<UserAgentPublisher> provider, Provider<HeartBeatInfo> provider2, FirebaseInstallationsApi firebaseInstallationsApi) {
        this(firebaseApp, new Metadata(firebaseApp.getApplicationContext()), b.b(), b.b(), provider, provider2, firebaseInstallationsApi);
    }
}
