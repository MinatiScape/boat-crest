package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
/* loaded from: classes13.dex */
public enum NeverObservableHolder implements Observable.OnSubscribe<Object> {
    INSTANCE;
    
    public static final Observable<Object> NEVER = Observable.unsafeCreate(INSTANCE);

    public static <T> Observable<T> instance() {
        return (Observable<T>) NEVER;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super Object> subscriber) {
    }
}
