package rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
/* loaded from: classes13.dex */
public final class BlockingOperatorToFuture {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class a<T> extends Subscriber<T> {
        public final /* synthetic */ CountDownLatch l;
        public final /* synthetic */ AtomicReference m;
        public final /* synthetic */ AtomicReference n;

        public a(CountDownLatch countDownLatch, AtomicReference atomicReference, AtomicReference atomicReference2) {
            this.l = countDownLatch;
            this.m = atomicReference;
            this.n = atomicReference2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.countDown();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.compareAndSet(null, th);
            this.l.countDown();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.n.set(t);
        }
    }

    public BlockingOperatorToFuture() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Future<T> toFuture(Observable<? extends T> observable) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicReference atomicReference = new AtomicReference();
        AtomicReference atomicReference2 = new AtomicReference();
        return new b(countDownLatch, observable.single().subscribe((Subscriber<? super Object>) new a(countDownLatch, atomicReference2, atomicReference)), atomicReference2, atomicReference);
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class b<T> implements Future<T> {
        public volatile boolean h;
        public final /* synthetic */ CountDownLatch i;
        public final /* synthetic */ Subscription j;
        public final /* synthetic */ AtomicReference k;
        public final /* synthetic */ AtomicReference l;

        public b(CountDownLatch countDownLatch, Subscription subscription, AtomicReference atomicReference, AtomicReference atomicReference2) {
            this.i = countDownLatch;
            this.j = subscription;
            this.k = atomicReference;
            this.l = atomicReference2;
        }

        public final T a() throws ExecutionException {
            Throwable th = (Throwable) this.k.get();
            if (th == null) {
                if (!this.h) {
                    return (T) this.l.get();
                }
                throw new CancellationException("Subscription unsubscribed");
            }
            throw new ExecutionException("Observable onError", th);
        }

        @Override // java.util.concurrent.Future
        public boolean cancel(boolean z) {
            if (this.i.getCount() > 0) {
                this.h = true;
                this.j.unsubscribe();
                this.i.countDown();
                return true;
            }
            return false;
        }

        @Override // java.util.concurrent.Future
        public T get() throws InterruptedException, ExecutionException {
            this.i.await();
            return a();
        }

        @Override // java.util.concurrent.Future
        public boolean isCancelled() {
            return this.h;
        }

        @Override // java.util.concurrent.Future
        public boolean isDone() {
            return this.i.getCount() == 0;
        }

        @Override // java.util.concurrent.Future
        public T get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
            if (this.i.await(j, timeUnit)) {
                return a();
            }
            throw new TimeoutException("Timed out after " + timeUnit.toMillis(j) + "ms waiting for underlying Observable.");
        }
    }
}
