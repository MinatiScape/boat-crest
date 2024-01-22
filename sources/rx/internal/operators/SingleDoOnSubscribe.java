package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public final class SingleDoOnSubscribe<T> implements Single.OnSubscribe<T> {
    public final Single.OnSubscribe<T> h;
    public final Action0 i;

    public SingleDoOnSubscribe(Single.OnSubscribe<T> onSubscribe, Action0 action0) {
        this.h = onSubscribe;
        this.i = action0;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        try {
            this.i.call();
            this.h.call(singleSubscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            singleSubscriber.onError(th);
        }
    }
}
