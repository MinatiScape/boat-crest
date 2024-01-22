package rx.observables;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.OnSubscribeAutoConnect;
import rx.internal.operators.OnSubscribeRefCount;
/* loaded from: classes13.dex */
public abstract class ConnectableObservable<T> extends Observable<T> {

    /* loaded from: classes13.dex */
    public class a implements Action1<Subscription> {
        public final /* synthetic */ Subscription[] h;

        public a(ConnectableObservable connectableObservable, Subscription[] subscriptionArr) {
            this.h = subscriptionArr;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscription subscription) {
            this.h[0] = subscription;
        }
    }

    public ConnectableObservable(Observable.OnSubscribe<T> onSubscribe) {
        super(onSubscribe);
    }

    public Observable<T> autoConnect() {
        return autoConnect(1);
    }

    public final Subscription connect() {
        Subscription[] subscriptionArr = new Subscription[1];
        connect(new a(this, subscriptionArr));
        return subscriptionArr[0];
    }

    public abstract void connect(Action1<? super Subscription> action1);

    public Observable<T> refCount() {
        return Observable.unsafeCreate(new OnSubscribeRefCount(this));
    }

    public Observable<T> autoConnect(int i) {
        return autoConnect(i, Actions.empty());
    }

    public Observable<T> autoConnect(int i, Action1<? super Subscription> action1) {
        if (i <= 0) {
            connect(action1);
            return this;
        }
        return Observable.unsafeCreate(new OnSubscribeAutoConnect(this, i, action1));
    }
}
