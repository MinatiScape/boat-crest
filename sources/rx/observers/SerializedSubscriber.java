package rx.observers;

import rx.Observer;
import rx.Subscriber;
/* loaded from: classes13.dex */
public class SerializedSubscriber<T> extends Subscriber<T> {
    public final Observer<T> l;

    public SerializedSubscriber(Subscriber<? super T> subscriber) {
        this(subscriber, true);
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

    public SerializedSubscriber(Subscriber<? super T> subscriber, boolean z) {
        super(subscriber, z);
        this.l = new SerializedObserver(subscriber);
    }
}
