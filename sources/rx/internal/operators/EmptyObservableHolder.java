package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
/* loaded from: classes13.dex */
public enum EmptyObservableHolder implements Observable.OnSubscribe<Object> {
    INSTANCE;
    
    public static final Observable<Object> EMPTY = Observable.unsafeCreate(INSTANCE);

    public static <T> Observable<T> instance() {
        return (Observable<T>) EMPTY;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super Object> subscriber) {
        subscriber.onCompleted();
    }
}
