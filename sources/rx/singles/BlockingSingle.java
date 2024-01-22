package rx.singles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.BlockingOperatorToFuture;
import rx.internal.util.BlockingUtils;
/* loaded from: classes13.dex */
public final class BlockingSingle<T> {

    /* renamed from: a  reason: collision with root package name */
    public final Single<? extends T> f15701a;

    /* loaded from: classes13.dex */
    public class a extends SingleSubscriber<T> {
        public final /* synthetic */ AtomicReference i;
        public final /* synthetic */ CountDownLatch j;
        public final /* synthetic */ AtomicReference k;

        public a(BlockingSingle blockingSingle, AtomicReference atomicReference, CountDownLatch countDownLatch, AtomicReference atomicReference2) {
            this.i = atomicReference;
            this.j = countDownLatch;
            this.k = atomicReference2;
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            this.k.set(th);
            this.j.countDown();
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.i.set(t);
            this.j.countDown();
        }
    }

    public BlockingSingle(Single<? extends T> single) {
        this.f15701a = single;
    }

    public static <T> BlockingSingle<T> from(Single<? extends T> single) {
        return new BlockingSingle<>(single);
    }

    public Future<T> toFuture() {
        return BlockingOperatorToFuture.toFuture(this.f15701a.toObservable());
    }

    public T value() {
        AtomicReference atomicReference = new AtomicReference();
        AtomicReference atomicReference2 = new AtomicReference();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        BlockingUtils.awaitForComplete(countDownLatch, this.f15701a.subscribe(new a(this, atomicReference, countDownLatch, atomicReference2)));
        Throwable th = (Throwable) atomicReference2.get();
        if (th == null) {
            return (T) atomicReference.get();
        }
        throw Exceptions.propagate(th);
    }
}
