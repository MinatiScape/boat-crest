package rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class SingleTakeUntilCompletable<T> implements Single.OnSubscribe<T> {
    public final Single.OnSubscribe<T> h;
    public final Completable i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends SingleSubscriber<T> implements CompletableSubscriber {
        public final SingleSubscriber<? super T> i;
        public final AtomicBoolean j = new AtomicBoolean();

        public a(SingleSubscriber<? super T> singleSubscriber) {
            this.i = singleSubscriber;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            onError(new CancellationException("Single::takeUntil(Completable) - Stream was canceled before emitting a terminal event."));
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            if (this.j.compareAndSet(false, true)) {
                unsubscribe();
                this.i.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            add(subscription);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            if (this.j.compareAndSet(false, true)) {
                unsubscribe();
                this.i.onSuccess(t);
            }
        }
    }

    public SingleTakeUntilCompletable(Single.OnSubscribe<T> onSubscribe, Completable completable) {
        this.h = onSubscribe;
        this.i = completable;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        a aVar = new a(singleSubscriber);
        singleSubscriber.add(aVar);
        this.i.subscribe(aVar);
        this.h.call(aVar);
    }
}
