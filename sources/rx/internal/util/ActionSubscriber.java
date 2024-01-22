package rx.internal.util;

import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
/* loaded from: classes13.dex */
public final class ActionSubscriber<T> extends Subscriber<T> {
    public final Action1<? super T> l;
    public final Action1<Throwable> m;
    public final Action0 n;

    public ActionSubscriber(Action1<? super T> action1, Action1<Throwable> action12, Action0 action0) {
        this.l = action1;
        this.m = action12;
        this.n = action0;
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.n.call();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.m.call(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.l.call(t);
    }
}
