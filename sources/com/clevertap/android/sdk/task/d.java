package com.clevertap.android.sdk.task;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
/* loaded from: classes2.dex */
public class d implements ExecutorService {
    public long h = 0;
    public ExecutorService i = Executors.newSingleThreadExecutor();

    /* loaded from: classes2.dex */
    public class a implements Runnable {
        public final /* synthetic */ Runnable h;

        public a(Runnable runnable) {
            this.h = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            d.this.h = Thread.currentThread().getId();
            this.h.run();
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes2.dex */
    public class b<T> implements Callable<T> {
        public final /* synthetic */ Callable h;

        public b(Callable callable) {
            this.h = callable;
        }

        @Override // java.util.concurrent.Callable
        public T call() throws Exception {
            d.this.h = Thread.currentThread().getId();
            return (T) this.h.call();
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.i.awaitTermination(j, timeUnit);
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        Objects.requireNonNull(runnable, "PostAsyncSafelyExecutor#execute: task can't ne null");
        if (Thread.currentThread().getId() == this.h) {
            runnable.run();
        } else {
            this.i.execute(new a(runnable));
        }
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("PostAsyncSafelyExecutor#invokeAll: This method is not supported");
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("PostAsyncSafelyExecutor#invokeAny: This method is not supported");
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.i.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.i.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.i.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return this.i.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        Objects.requireNonNull(callable, "PostAsyncSafelyExecutor#submit: task can't ne null");
        if (Thread.currentThread().getId() == this.h) {
            try {
                callable.call();
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return this.i.submit(new b(callable));
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("PostAsyncSafelyExecutor#invokeAll: This method is not supported");
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("PostAsyncSafelyExecutor#invokeAny: This method is not supported");
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        Objects.requireNonNull(runnable, "PostAsyncSafelyExecutor#submit: task can't ne null");
        FutureTask futureTask = new FutureTask(runnable, t);
        execute(futureTask);
        return futureTask;
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        Objects.requireNonNull(runnable, "PostAsyncSafelyExecutor#submit: task can't ne null");
        FutureTask futureTask = new FutureTask(runnable, null);
        execute(futureTask);
        return futureTask;
    }
}
