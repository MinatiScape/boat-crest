package rx.internal.operators;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public final class SingleOnSubscribeDelaySubscriptionOther<T> implements Single.OnSubscribe<T> {
    public final Single<? extends T> h;
    public final Observable<?> i;

    /* loaded from: classes13.dex */
    public class a extends SingleSubscriber<T> {
        public final /* synthetic */ SingleSubscriber i;

        public a(SingleOnSubscribeDelaySubscriptionOther singleOnSubscribeDelaySubscriptionOther, SingleSubscriber singleSubscriber) {
            this.i = singleSubscriber;
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            this.i.onError(th);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.i.onSuccess(t);
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<Object> {
        public boolean l;
        public final /* synthetic */ SingleSubscriber m;
        public final /* synthetic */ SerialSubscription n;

        public b(SingleSubscriber singleSubscriber, SerialSubscription serialSubscription) {
            this.m = singleSubscriber;
            this.n = serialSubscription;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.n.set(this.m);
            SingleOnSubscribeDelaySubscriptionOther.this.h.subscribe(this.m);
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
        public void onNext(Object obj) {
            onCompleted();
        }
    }

    public SingleOnSubscribeDelaySubscriptionOther(Single<? extends T> single, Observable<?> observable) {
        this.h = single;
        this.i = observable;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        a aVar = new a(this, singleSubscriber);
        SerialSubscription serialSubscription = new SerialSubscription();
        singleSubscriber.add(serialSubscription);
        b bVar = new b(aVar, serialSubscription);
        serialSubscription.set(bVar);
        this.i.subscribe((Subscriber<? super Object>) bVar);
    }
}
