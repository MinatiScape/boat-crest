package rx.internal.util;

import rx.Notification;
import rx.Observer;
import rx.functions.Action1;
/* loaded from: classes13.dex */
public final class ActionNotificationObserver<T> implements Observer<T> {
    public final Action1<Notification<? super T>> h;

    public ActionNotificationObserver(Action1<Notification<? super T>> action1) {
        this.h = action1;
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.h.call(Notification.createOnCompleted());
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.h.call(Notification.createOnError(th));
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.h.call(Notification.createOnNext(t));
    }
}
