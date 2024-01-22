package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.observers.Subscribers;
/* loaded from: classes13.dex */
public final class OnSubscribeDelaySubscriptionWithSelector<T, U> implements Observable.OnSubscribe<T> {
    public final Observable<? extends T> h;
    public final Func0<? extends Observable<U>> i;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<U> {
        public final /* synthetic */ Subscriber l;

        public a(Subscriber subscriber) {
            this.l = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            OnSubscribeDelaySubscriptionWithSelector.this.h.unsafeSubscribe(Subscribers.wrap(this.l));
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(U u) {
        }
    }

    public OnSubscribeDelaySubscriptionWithSelector(Observable<? extends T> observable, Func0<? extends Observable<U>> func0) {
        this.h = observable;
        this.i = func0;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        try {
            this.i.call().take(1).unsafeSubscribe(new a(subscriber));
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
