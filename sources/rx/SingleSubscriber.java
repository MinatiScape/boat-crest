package rx;

import rx.internal.util.SubscriptionList;
/* loaded from: classes13.dex */
public abstract class SingleSubscriber<T> implements Subscription {
    public final SubscriptionList h = new SubscriptionList();

    public final void add(Subscription subscription) {
        this.h.add(subscription);
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.h.isUnsubscribed();
    }

    public abstract void onError(Throwable th);

    public abstract void onSuccess(T t);

    @Override // rx.Subscription
    public final void unsubscribe() {
        this.h.unsubscribe();
    }
}
