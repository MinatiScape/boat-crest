package com.google.firebase.messaging;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.collection.ArrayMap;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.GmsRpc;
import com.google.firebase.installations.FirebaseInstallationsApi;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes10.dex */
public class r0 {
    public static final long j = TimeUnit.HOURS.toSeconds(8);

    /* renamed from: a  reason: collision with root package name */
    public final Context f11357a;
    public final h0 b;
    public final d0 c;
    public final FirebaseMessaging d;
    public final FirebaseInstallationsApi e;
    public final ScheduledExecutorService g;
    public final p0 i;
    @GuardedBy("pendingOperations")
    public final Map<String, ArrayDeque<TaskCompletionSource<Void>>> f = new ArrayMap();
    @GuardedBy("this")
    public boolean h = false;

    public r0(FirebaseMessaging firebaseMessaging, FirebaseInstallationsApi firebaseInstallationsApi, h0 h0Var, p0 p0Var, d0 d0Var, Context context, @NonNull ScheduledExecutorService scheduledExecutorService) {
        this.d = firebaseMessaging;
        this.e = firebaseInstallationsApi;
        this.b = h0Var;
        this.i = p0Var;
        this.c = d0Var;
        this.f11357a = context;
        this.g = scheduledExecutorService;
    }

    @WorkerThread
    public static <T> T b(Task<T> task) throws IOException {
        try {
            return (T) Tasks.await(task, 30L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e = e;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        } catch (ExecutionException e2) {
            Throwable cause = e2.getCause();
            if (!(cause instanceof IOException)) {
                if (cause instanceof RuntimeException) {
                    throw ((RuntimeException) cause);
                }
                throw new IOException(e2);
            }
            throw ((IOException) cause);
        } catch (TimeoutException e3) {
            e = e3;
            throw new IOException("SERVICE_NOT_AVAILABLE", e);
        }
    }

    @VisibleForTesting
    public static Task<r0> e(final FirebaseMessaging firebaseMessaging, final FirebaseInstallationsApi firebaseInstallationsApi, final h0 h0Var, final d0 d0Var, final Context context, @NonNull final ScheduledExecutorService scheduledExecutorService) {
        return Tasks.call(scheduledExecutorService, new Callable(context, scheduledExecutorService, firebaseMessaging, firebaseInstallationsApi, h0Var, d0Var) { // from class: com.google.firebase.messaging.q0
            public final Context h;
            public final ScheduledExecutorService i;
            public final FirebaseMessaging j;
            public final FirebaseInstallationsApi k;
            public final h0 l;
            public final d0 m;

            {
                this.h = context;
                this.i = scheduledExecutorService;
                this.j = firebaseMessaging;
                this.k = firebaseInstallationsApi;
                this.l = h0Var;
                this.m = d0Var;
            }

            @Override // java.util.concurrent.Callable
            public Object call() {
                return r0.i(this.h, this.i, this.j, this.k, this.l, this.m);
            }
        });
    }

    public static boolean g() {
        return Log.isLoggable(Constants.TAG, 3) || (Build.VERSION.SDK_INT == 23 && Log.isLoggable(Constants.TAG, 3));
    }

    public static final /* synthetic */ r0 i(Context context, ScheduledExecutorService scheduledExecutorService, FirebaseMessaging firebaseMessaging, FirebaseInstallationsApi firebaseInstallationsApi, h0 h0Var, d0 d0Var) throws Exception {
        return new r0(firebaseMessaging, firebaseInstallationsApi, h0Var, p0.b(context, scheduledExecutorService), d0Var, context, scheduledExecutorService);
    }

    public final void a(o0 o0Var, TaskCompletionSource<Void> taskCompletionSource) {
        ArrayDeque<TaskCompletionSource<Void>> arrayDeque;
        synchronized (this.f) {
            String e = o0Var.e();
            if (this.f.containsKey(e)) {
                arrayDeque = this.f.get(e);
            } else {
                ArrayDeque<TaskCompletionSource<Void>> arrayDeque2 = new ArrayDeque<>();
                this.f.put(e, arrayDeque2);
                arrayDeque = arrayDeque2;
            }
            arrayDeque.add(taskCompletionSource);
        }
    }

    @WorkerThread
    public final void c(String str) throws IOException {
        b(this.c.k((String) b(this.e.getId()), this.d.c(), str));
    }

    @WorkerThread
    public final void d(String str) throws IOException {
        b(this.c.l((String) b(this.e.getId()), this.d.c(), str));
    }

    public boolean f() {
        return this.i.c() != null;
    }

    public synchronized boolean h() {
        return this.h;
    }

    public final void j(o0 o0Var) {
        synchronized (this.f) {
            String e = o0Var.e();
            if (this.f.containsKey(e)) {
                ArrayDeque<TaskCompletionSource<Void>> arrayDeque = this.f.get(e);
                TaskCompletionSource<Void> poll = arrayDeque.poll();
                if (poll != null) {
                    poll.setResult(null);
                }
                if (arrayDeque.isEmpty()) {
                    this.f.remove(e);
                }
            }
        }
    }

    @WorkerThread
    public boolean k(o0 o0Var) throws IOException {
        char c;
        try {
            String b = o0Var.b();
            int hashCode = b.hashCode();
            if (hashCode != 83) {
                if (hashCode == 85 && b.equals("U")) {
                    c = 1;
                }
                c = 65535;
            } else {
                if (b.equals(ExifInterface.LATITUDE_SOUTH)) {
                    c = 0;
                }
                c = 65535;
            }
            if (c == 0) {
                c(o0Var.c());
                if (g()) {
                    String c2 = o0Var.c();
                    StringBuilder sb = new StringBuilder(String.valueOf(c2).length() + 31);
                    sb.append("Subscribe to topic: ");
                    sb.append(c2);
                    sb.append(" succeeded.");
                    Log.d(Constants.TAG, sb.toString());
                }
            } else if (c != 1) {
                if (g()) {
                    String valueOf = String.valueOf(o0Var);
                    StringBuilder sb2 = new StringBuilder(valueOf.length() + 24);
                    sb2.append("Unknown topic operation");
                    sb2.append(valueOf);
                    sb2.append(".");
                    Log.d(Constants.TAG, sb2.toString());
                }
            } else {
                d(o0Var.c());
                if (g()) {
                    String c3 = o0Var.c();
                    StringBuilder sb3 = new StringBuilder(String.valueOf(c3).length() + 35);
                    sb3.append("Unsubscribe from topic: ");
                    sb3.append(c3);
                    sb3.append(" succeeded.");
                    Log.d(Constants.TAG, sb3.toString());
                }
            }
            return true;
        } catch (IOException e) {
            if (!"SERVICE_NOT_AVAILABLE".equals(e.getMessage()) && !GmsRpc.ERROR_INTERNAL_SERVER_ERROR.equals(e.getMessage())) {
                if (e.getMessage() == null) {
                    Log.e(Constants.TAG, "Topic operation failed without exception message. Will retry Topic operation.");
                    return false;
                }
                throw e;
            }
            String message = e.getMessage();
            StringBuilder sb4 = new StringBuilder(String.valueOf(message).length() + 53);
            sb4.append("Topic operation failed: ");
            sb4.append(message);
            sb4.append(". Will retry Topic operation.");
            Log.e(Constants.TAG, sb4.toString());
            return false;
        }
    }

    public void l(Runnable runnable, long j2) {
        this.g.schedule(runnable, j2, TimeUnit.SECONDS);
    }

    @VisibleForTesting
    public Task<Void> m(o0 o0Var) {
        this.i.a(o0Var);
        TaskCompletionSource<Void> taskCompletionSource = new TaskCompletionSource<>();
        a(o0Var, taskCompletionSource);
        return taskCompletionSource.getTask();
    }

    public synchronized void n(boolean z) {
        this.h = z;
    }

    public final void o() {
        if (h()) {
            return;
        }
        s(0L);
    }

    public void p() {
        if (f()) {
            o();
        }
    }

    public Task<Void> q(String str) {
        Task<Void> m = m(o0.f(str));
        p();
        return m;
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x000d, code lost:
        if (g() == false) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000f, code lost:
        android.util.Log.d(com.google.firebase.messaging.Constants.TAG, "topic sync succeeded");
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0017, code lost:
        return true;
     */
    @androidx.annotation.WorkerThread
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean r() throws java.io.IOException {
        /*
            r2 = this;
        L0:
            monitor-enter(r2)
            com.google.firebase.messaging.p0 r0 = r2.i     // Catch: java.lang.Throwable -> L2b
            com.google.firebase.messaging.o0 r0 = r0.c()     // Catch: java.lang.Throwable -> L2b
            if (r0 != 0) goto L19
            boolean r0 = g()     // Catch: java.lang.Throwable -> L2b
            if (r0 == 0) goto L16
            java.lang.String r0 = "FirebaseMessaging"
            java.lang.String r1 = "topic sync succeeded"
            android.util.Log.d(r0, r1)     // Catch: java.lang.Throwable -> L2b
        L16:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2b
            r0 = 1
            return r0
        L19:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2b
            boolean r1 = r2.k(r0)
            if (r1 != 0) goto L22
            r0 = 0
            return r0
        L22:
            com.google.firebase.messaging.p0 r1 = r2.i
            r1.e(r0)
            r2.j(r0)
            goto L0
        L2b:
            r0 = move-exception
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L2b
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.messaging.r0.r():boolean");
    }

    public void s(long j2) {
        l(new s0(this, this.f11357a, this.b, Math.min(Math.max(30L, j2 + j2), j)), j2);
        n(true);
    }

    public Task<Void> t(String str) {
        Task<Void> m = m(o0.g(str));
        p();
        return m;
    }
}
