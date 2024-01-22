package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.observers.Subscribers;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OnSubscribeDelaySubscriptionOther<T, U> implements Observable.OnSubscribe<T> {
    public final Observable<? extends T> h;
    public final Observable<U> i;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<U> {
        public boolean l;
        public final /* synthetic */ Subscriber m;
        public final /* synthetic */ SerialSubscription n;

        public a(Subscriber subscriber, SerialSubscription serialSubscription) {
            this.m = subscriber;
            this.n = serialSubscription;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.n.set(Subscriptions.unsubscribed());
            OnSubscribeDelaySubscriptionOther.this.h.unsafeSubscribe(this.m);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.l) {
                RxJavaHooks.onError(th);
                return;
            }
            this.l = true;
            this.m.onError(th);
        }

        @Override // rx.Observer
        public void onNext(U u) {
            onCompleted();
        }
    }

    public OnSubscribeDelaySubscriptionOther(Observable<? extends T> observable, Observable<U> observable2) {
        this.h = observable;
        this.i = observable2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        a aVar = new a(Subscribers.wrap(subscriber), serialSubscription);
        serialSubscription.set(aVar);
        this.i.unsafeSubscribe(aVar);
    }
}
