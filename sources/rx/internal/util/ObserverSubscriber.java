package rx.internal.util;

import rx.Observer;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class ObserverSubscriber<T> extends Subscriber<T> {
    public final Observer<? super T> l;

    public ObserverSubscriber(Observer<? super T> observer) {
        this.l = observer;
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.l.onCompleted();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.l.onError(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.l.onNext(t);
    }
}
