package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.GuardedBy;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executor;
/* loaded from: classes10.dex */
public final class p0 {
    @GuardedBy("TopicsStore.class")
    public static WeakReference<p0> d;

    /* renamed from: a  reason: collision with root package name */
    public final SharedPreferences f11355a;
    public l0 b;
    public final Executor c;

    public p0(SharedPreferences sharedPreferences, Executor executor) {
        this.c = executor;
        this.f11355a = sharedPreferences;
    }

    @WorkerThread
    public static synchronized p0 b(Context context, Executor executor) {
        synchronized (p0.class) {
            WeakReference<p0> weakReference = d;
            p0 p0Var = weakReference != null ? weakReference.get() : null;
            if (p0Var == null) {
                p0 p0Var2 = new p0(context.getSharedPreferences("com.google.android.gms.appid", 0), executor);
                p0Var2.d();
                d = new WeakReference<>(p0Var2);
                return p0Var2;
            }
            return p0Var;
        }
    }

    public synchronized boolean a(o0 o0Var) {
        return this.b.a(o0Var.e());
    }

    @Nullable
    public synchronized o0 c() {
        return o0.a(this.b.f());
    }

    @WorkerThread
    public final synchronized void d() {
        this.b = l0.d(this.f11355a, "topic_operation_queue", com.clevertap.android.sdk.Constants.SEPARATOR_COMMA, this.c);
    }

    public synchronized boolean e(o0 o0Var) {
        return this.b.g(o0Var.e());
    }
}
