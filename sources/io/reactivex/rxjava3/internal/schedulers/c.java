package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class c implements Callable<Void>, Disposable {
    public static final FutureTask<Void> m = new FutureTask<>(Functions.EMPTY_RUNNABLE, null);
    public final Runnable h;
    public final ExecutorService k;
    public Thread l;
    public final AtomicReference<Future<?>> j = new AtomicReference<>();
    public final AtomicReference<Future<?>> i = new AtomicReference<>();

    public c(Runnable runnable, ExecutorService executorService) {
        this.h = runnable;
        this.k = executorService;
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: a */
    public Void call() {
        this.l = Thread.currentThread();
        try {
            this.h.run();
            c(this.k.submit(this));
            this.l = null;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            this.l = null;
            RxJavaPlugins.onError(th);
        }
        return null;
    }

    public void b(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.j.get();
            if (future2 == m) {
                future.cancel(this.l != Thread.currentThread());
                return;
            }
        } while (!this.j.compareAndSet(future2, future));
    }

    public void c(Future<?> future) {
        Future<?> future2;
        do {
            future2 = this.i.get();
            if (future2 == m) {
                future.cancel(this.l != Thread.currentThread());
                return;
            }
        } while (!this.i.compareAndSet(future2, future));
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        AtomicReference<Future<?>> atomicReference = this.j;
        FutureTask<Void> futureTask = m;
        Future<?> andSet = atomicReference.getAndSet(futureTask);
        if (andSet != null && andSet != futureTask) {
            andSet.cancel(this.l != Thread.currentThread());
        }
        Future<?> andSet2 = this.i.getAndSet(futureTask);
        if (andSet2 == null || andSet2 == futureTask) {
            return;
        }
        andSet2.cancel(this.l != Thread.currentThread());
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.j.get() == m;
    }
}
