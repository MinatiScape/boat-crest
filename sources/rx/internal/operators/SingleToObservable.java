package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.internal.operators.SingleLiftObservableOperator;
/* loaded from: classes13.dex */
public final class SingleToObservable<T> implements Observable.OnSubscribe<T> {
    public final Single.OnSubscribe<T> h;

    public SingleToObservable(Single.OnSubscribe<T> onSubscribe) {
        this.h = onSubscribe;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        SingleLiftObservableOperator.a aVar = new SingleLiftObservableOperator.a(subscriber);
        subscriber.add(aVar);
        this.h.call(aVar);
    }
}
