package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.observers.Subscribers;
/* loaded from: classes13.dex */
public final class OnSubscribeDefer<T> implements Observable.OnSubscribe<T> {
    public final Func0<? extends Observable<? extends T>> h;

    public OnSubscribeDefer(Func0<? extends Observable<? extends T>> func0) {
        this.h = func0;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        try {
            this.h.call().unsafeSubscribe(Subscribers.wrap(subscriber));
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
