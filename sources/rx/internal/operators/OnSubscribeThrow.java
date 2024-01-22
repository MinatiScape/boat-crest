package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class OnSubscribeThrow<T> implements Observable.OnSubscribe<T> {
    public final Throwable h;

    public OnSubscribeThrow(Throwable th) {
        this.h = th;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        subscriber.onError(this.h);
    }
}
