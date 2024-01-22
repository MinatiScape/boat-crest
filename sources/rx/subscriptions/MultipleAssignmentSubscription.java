package rx.subscriptions;

import rx.Subscription;
import rx.internal.subscriptions.SequentialSubscription;
/* loaded from: classes13.dex */
public final class MultipleAssignmentSubscription implements Subscription {
    public final SequentialSubscription h = new SequentialSubscription();

    public Subscription get() {
        return this.h.current();
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.h.isUnsubscribed();
    }

    public void set(Subscription subscription) {
        if (subscription != null) {
            this.h.replace(subscription);
            return;
        }
        throw new IllegalArgumentException("Subscription can not be null");
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.h.unsubscribe();
    }
}
