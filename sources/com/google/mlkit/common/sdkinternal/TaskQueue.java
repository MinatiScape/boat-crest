package com.google.mlkit.common.sdkinternal;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicReference;
@KeepForSdk
/* loaded from: classes10.dex */
public class TaskQueue {
    @GuardedBy("lock")
    public boolean b;

    /* renamed from: a  reason: collision with root package name */
    public final Object f11593a = new Object();
    @GuardedBy("lock")
    public final Queue c = new ArrayDeque();
    public final AtomicReference d = new AtomicReference();

    public final void c() {
        synchronized (this.f11593a) {
            if (this.c.isEmpty()) {
                this.b = false;
                return;
            }
            c cVar = (c) this.c.remove();
            d(cVar.f11594a, cVar.b);
        }
    }

    @KeepForSdk
    public void checkIsRunningOnCurrentThread() {
        Preconditions.checkState(Thread.currentThread().equals(this.d.get()));
    }

    public final void d(Executor executor, final Runnable runnable) {
        try {
            executor.execute(new Runnable() { // from class: com.google.mlkit.common.sdkinternal.zzt
                @Override // java.lang.Runnable
                public final void run() {
                    TaskQueue taskQueue = TaskQueue.this;
                    Runnable runnable2 = runnable;
                    d dVar = new d(taskQueue, null);
                    try {
                        runnable2.run();
                        dVar.close();
                    } catch (Throwable th) {
                        try {
                            dVar.close();
                        } catch (Throwable th2) {
                            try {
                                Throwable.class.getDeclaredMethod("addSuppressed", Throwable.class).invoke(th, th2);
                            } catch (Exception unused) {
                            }
                        }
                        throw th;
                    }
                }
            });
        } catch (RejectedExecutionException unused) {
            c();
        }
    }

    @KeepForSdk
    public void submit(@NonNull Executor executor, @NonNull Runnable runnable) {
        synchronized (this.f11593a) {
            if (this.b) {
                this.c.add(new c(executor, runnable, null));
                return;
            }
            this.b = true;
            d(executor, runnable);
        }
    }
}
