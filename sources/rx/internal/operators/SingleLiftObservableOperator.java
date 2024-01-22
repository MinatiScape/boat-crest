package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.operators.SingleFromObservable;
import rx.internal.producers.SingleProducer;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class SingleLiftObservableOperator<T, R> implements Single.OnSubscribe<R> {
    public final Single.OnSubscribe<T> h;
    public final Observable.Operator<? extends R, ? super T> i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends SingleSubscriber<T> {
        public final Subscriber<? super T> i;

        public a(Subscriber<? super T> subscriber) {
            this.i = subscriber;
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            this.i.onError(th);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.i.setProducer(new SingleProducer(this.i, t));
        }
    }

    public SingleLiftObservableOperator(Single.OnSubscribe<T> onSubscribe, Observable.Operator<? extends R, ? super T> operator) {
        this.h = onSubscribe;
        this.i = operator;
    }

    public static <T> SingleSubscriber<T> wrap(Subscriber<T> subscriber) {
        a aVar = new a(subscriber);
        subscriber.add(aVar);
        return aVar;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super R> singleSubscriber) {
        SingleFromObservable.a aVar = new SingleFromObservable.a(singleSubscriber);
        singleSubscriber.add(aVar);
        try {
            Subscriber<? super T> call = RxJavaHooks.onSingleLift(this.i).call(aVar);
            SingleSubscriber wrap = wrap(call);
            call.onStart();
            this.h.call(wrap);
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, singleSubscriber);
        }
    }
}
