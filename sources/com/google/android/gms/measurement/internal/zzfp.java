package com.google.android.gms.measurement.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes10.dex */
public final class zzfp extends x0 {
    public static final AtomicLong k = new AtomicLong(Long.MIN_VALUE);
    @Nullable
    public d0 b;
    @Nullable
    public d0 c;
    public final PriorityBlockingQueue<c0<?>> d;
    public final BlockingQueue<c0<?>> e;
    public final Thread.UncaughtExceptionHandler f;
    public final Thread.UncaughtExceptionHandler g;
    public final Object h;
    public final Semaphore i;
    public volatile boolean j;

    public zzfp(zzfs zzfsVar) {
        super(zzfsVar);
        this.h = new Object();
        this.i = new Semaphore(2);
        this.d = new PriorityBlockingQueue<>();
        this.e = new LinkedBlockingQueue();
        this.f = new b0(this, "Thread death: Uncaught exception on worker thread");
        this.g = new b0(this, "Thread death: Uncaught exception on network thread");
    }

    public static /* bridge */ /* synthetic */ boolean j(zzfp zzfpVar) {
        boolean z = zzfpVar.j;
        return false;
    }

    @Nullable
    public final <T> T e(AtomicReference<T> atomicReference, long j, String str, Runnable runnable) {
        synchronized (atomicReference) {
            this.zzs.zzaz().zzp(runnable);
            try {
                atomicReference.wait(j);
            } catch (InterruptedException unused) {
                this.zzs.zzay().zzk().zza(str.length() != 0 ? "Interrupted waiting for ".concat(str) : new String("Interrupted waiting for "));
                return null;
            }
        }
        T t = atomicReference.get();
        if (t == null) {
            this.zzs.zzay().zzk().zza(str.length() != 0 ? "Timed out waiting for ".concat(str) : new String("Timed out waiting for "));
        }
        return t;
    }

    public final void k(c0<?> c0Var) {
        synchronized (this.h) {
            this.d.add(c0Var);
            d0 d0Var = this.b;
            if (d0Var == null) {
                d0 d0Var2 = new d0(this, "Measurement Worker", this.d);
                this.b = d0Var2;
                d0Var2.setUncaughtExceptionHandler(this.f);
                this.b.start();
            } else {
                d0Var.a();
            }
        }
    }

    @Override // com.google.android.gms.measurement.internal.w0
    public final void zzax() {
        if (Thread.currentThread() != this.c) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    @Override // com.google.android.gms.measurement.internal.x0
    public final boolean zzf() {
        return false;
    }

    @Override // com.google.android.gms.measurement.internal.w0
    public final void zzg() {
        if (Thread.currentThread() != this.b) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final <V> Future<V> zzh(Callable<V> callable) throws IllegalStateException {
        zzu();
        Preconditions.checkNotNull(callable);
        c0<?> c0Var = new c0<>(this, (Callable<?>) callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.b) {
            if (!this.d.isEmpty()) {
                this.zzs.zzay().zzk().zza("Callable skipped the worker queue.");
            }
            c0Var.run();
        } else {
            k(c0Var);
        }
        return c0Var;
    }

    public final <V> Future<V> zzi(Callable<V> callable) throws IllegalStateException {
        zzu();
        Preconditions.checkNotNull(callable);
        c0<?> c0Var = new c0<>(this, (Callable<?>) callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.b) {
            c0Var.run();
        } else {
            k(c0Var);
        }
        return c0Var;
    }

    public final void zzo(Runnable runnable) throws IllegalStateException {
        zzu();
        Preconditions.checkNotNull(runnable);
        c0<?> c0Var = new c0<>(this, runnable, false, "Task exception on network thread");
        synchronized (this.h) {
            this.e.add(c0Var);
            d0 d0Var = this.c;
            if (d0Var == null) {
                d0 d0Var2 = new d0(this, "Measurement Network", this.e);
                this.c = d0Var2;
                d0Var2.setUncaughtExceptionHandler(this.g);
                this.c.start();
            } else {
                d0Var.a();
            }
        }
    }

    public final void zzp(Runnable runnable) throws IllegalStateException {
        zzu();
        Preconditions.checkNotNull(runnable);
        k(new c0<>(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzq(Runnable runnable) throws IllegalStateException {
        zzu();
        Preconditions.checkNotNull(runnable);
        k(new c0<>(this, runnable, true, "Task exception on worker thread"));
    }

    public final boolean zzs() {
        return Thread.currentThread() == this.b;
    }
}
