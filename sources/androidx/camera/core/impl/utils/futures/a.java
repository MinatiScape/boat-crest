package androidx.camera.core.impl.utils.futures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.core.util.Preconditions;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes.dex */
public class a<I, O> extends FutureChain<O> implements Runnable {
    @Nullable
    public AsyncFunction<? super I, ? extends O> j;
    public final BlockingQueue<Boolean> k = new LinkedBlockingQueue(1);
    public final CountDownLatch l = new CountDownLatch(1);
    @Nullable
    public ListenableFuture<? extends I> m;
    @Nullable
    public volatile ListenableFuture<? extends O> n;

    /* renamed from: androidx.camera.core.impl.utils.futures.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class RunnableC0118a implements Runnable {
        public final /* synthetic */ ListenableFuture h;

        public RunnableC0118a(ListenableFuture listenableFuture) {
            this.h = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                try {
                    a.this.a(Futures.getUninterruptibly(this.h));
                } catch (CancellationException unused) {
                    a.this.cancel(false);
                    a.this.n = null;
                    return;
                } catch (ExecutionException e) {
                    a.this.b(e.getCause());
                }
                a.this.n = null;
            } catch (Throwable th) {
                a.this.n = null;
                throw th;
            }
        }
    }

    public a(@NonNull AsyncFunction<? super I, ? extends O> asyncFunction, @NonNull ListenableFuture<? extends I> listenableFuture) {
        this.j = (AsyncFunction) Preconditions.checkNotNull(asyncFunction);
        this.m = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
    }

    public final void c(@Nullable Future<?> future, boolean z) {
        if (future != null) {
            future.cancel(z);
        }
    }

    @Override // androidx.camera.core.impl.utils.futures.FutureChain, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        if (super.cancel(z)) {
            d(this.k, Boolean.valueOf(z));
            c(this.m, z);
            c(this.n, z);
            return true;
        }
        return false;
    }

    public final <E> void d(@NonNull BlockingQueue<E> blockingQueue, @NonNull E e) {
        boolean z = false;
        while (true) {
            try {
                blockingQueue.put(e);
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    public final <E> E e(@NonNull BlockingQueue<E> blockingQueue) {
        E take;
        boolean z = false;
        while (true) {
            try {
                take = blockingQueue.take();
                break;
            } catch (InterruptedException unused) {
                z = true;
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
        return take;
    }

    @Override // androidx.camera.core.impl.utils.futures.FutureChain, java.util.concurrent.Future
    @Nullable
    public O get() throws InterruptedException, ExecutionException {
        if (!isDone()) {
            ListenableFuture<? extends I> listenableFuture = this.m;
            if (listenableFuture != null) {
                listenableFuture.get();
            }
            this.l.await();
            ListenableFuture<? extends O> listenableFuture2 = this.n;
            if (listenableFuture2 != null) {
                listenableFuture2.get();
            }
        }
        return (O) super.get();
    }

    @Override // java.lang.Runnable
    public void run() {
        ListenableFuture<? extends O> apply;
        try {
            try {
                try {
                    try {
                        apply = this.j.apply(Futures.getUninterruptibly(this.m));
                        this.n = apply;
                    } catch (Error e) {
                        b(e);
                    } catch (UndeclaredThrowableException e2) {
                        b(e2.getCause());
                    }
                } catch (Throwable th) {
                    this.j = null;
                    this.m = null;
                    this.l.countDown();
                    throw th;
                }
            } catch (CancellationException unused) {
                cancel(false);
            } catch (ExecutionException e3) {
                b(e3.getCause());
            }
        } catch (Exception e4) {
            b(e4);
        }
        if (isCancelled()) {
            apply.cancel(((Boolean) e(this.k)).booleanValue());
            this.n = null;
            this.j = null;
            this.m = null;
            this.l.countDown();
            return;
        }
        apply.addListener(new RunnableC0118a(apply), CameraXExecutors.directExecutor());
        this.j = null;
        this.m = null;
        this.l.countDown();
    }

    @Override // androidx.camera.core.impl.utils.futures.FutureChain, java.util.concurrent.Future
    @Nullable
    public O get(long j, @NonNull TimeUnit timeUnit) throws TimeoutException, ExecutionException, InterruptedException {
        if (!isDone()) {
            TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
            if (timeUnit != timeUnit2) {
                j = timeUnit2.convert(j, timeUnit);
                timeUnit = timeUnit2;
            }
            ListenableFuture<? extends I> listenableFuture = this.m;
            if (listenableFuture != null) {
                long nanoTime = System.nanoTime();
                listenableFuture.get(j, timeUnit);
                j -= Math.max(0L, System.nanoTime() - nanoTime);
            }
            long nanoTime2 = System.nanoTime();
            if (this.l.await(j, timeUnit)) {
                j -= Math.max(0L, System.nanoTime() - nanoTime2);
                ListenableFuture<? extends O> listenableFuture2 = this.n;
                if (listenableFuture2 != null) {
                    listenableFuture2.get(j, timeUnit);
                }
            } else {
                throw new TimeoutException();
            }
        }
        return (O) super.get(j, timeUnit);
    }
}
