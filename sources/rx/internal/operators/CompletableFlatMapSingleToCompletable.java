package rx.internal.operators;

import rx.Completable;
import rx.CompletableSubscriber;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class CompletableFlatMapSingleToCompletable<T> implements Completable.OnSubscribe {
    public final Single<T> h;
    public final Func1<? super T, ? extends Completable> i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends SingleSubscriber<T> implements CompletableSubscriber {
        public final CompletableSubscriber i;
        public final Func1<? super T, ? extends Completable> j;

        public a(CompletableSubscriber completableSubscriber, Func1<? super T, ? extends Completable> func1) {
            this.i = completableSubscriber;
            this.j = func1;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.i.onCompleted();
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            this.i.onError(th);
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            add(subscription);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            try {
                Completable call = this.j.call(t);
                if (call == null) {
                    onError(new NullPointerException("The mapper returned a null Completable"));
                } else {
                    call.subscribe(this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                onError(th);
            }
        }
    }

    public CompletableFlatMapSingleToCompletable(Single<T> single, Func1<? super T, ? extends Completable> func1) {
        this.h = single;
        this.i = func1;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        a aVar = new a(completableSubscriber, this.i);
        completableSubscriber.onSubscribe(aVar);
        this.h.subscribe(aVar);
    }
}
