package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class SingleDoOnUnsubscribe<T> implements Single.OnSubscribe<T> {
    public final Single.OnSubscribe<T> h;
    public final Action0 i;

    public SingleDoOnUnsubscribe(Single.OnSubscribe<T> onSubscribe, Action0 action0) {
        this.h = onSubscribe;
        this.i = action0;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        singleSubscriber.add(Subscriptions.create(this.i));
        this.h.call(singleSubscriber);
    }
}
