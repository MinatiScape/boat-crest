package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.Subscribers;
/* loaded from: classes13.dex */
public class OperatorDoOnSubscribe<T> implements Observable.Operator<T, T> {
    public final Action0 h;

    public OperatorDoOnSubscribe(Action0 action0) {
        this.h = action0;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        this.h.call();
        return Subscribers.wrap(subscriber);
    }
}
