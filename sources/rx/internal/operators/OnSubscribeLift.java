package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OnSubscribeLift<T, R> implements Observable.OnSubscribe<R> {
    public final Observable.OnSubscribe<T> h;
    public final Observable.Operator<? extends R, ? super T> i;

    public OnSubscribeLift(Observable.OnSubscribe<T> onSubscribe, Observable.Operator<? extends R, ? super T> operator) {
        this.h = onSubscribe;
        this.i = operator;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        try {
            Subscriber<? super T> call = RxJavaHooks.onObservableLift(this.i).call(subscriber);
            call.onStart();
            this.h.call(call);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }
}
