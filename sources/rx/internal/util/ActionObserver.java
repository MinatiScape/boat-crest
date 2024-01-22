package rx.internal.util;

import rx.Observer;
import rx.functions.Action0;
import rx.functions.Action1;
/* loaded from: classes13.dex */
public final class ActionObserver<T> implements Observer<T> {
    public final Action1<? super T> h;
    public final Action1<? super Throwable> i;
    public final Action0 j;

    public ActionObserver(Action1<? super T> action1, Action1<? super Throwable> action12, Action0 action0) {
        this.h = action1;
        this.i = action12;
        this.j = action0;
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.j.call();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.i.call(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.h.call(t);
    }
}
