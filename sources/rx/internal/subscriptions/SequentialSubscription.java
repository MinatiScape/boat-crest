package rx.internal.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class SequentialSubscription extends AtomicReference<Subscription> implements Subscription {
    private static final long serialVersionUID = 995205034283130269L;

    public SequentialSubscription() {
    }

    public Subscription current() {
        Subscription subscription = (Subscription) super.get();
        return subscription == Unsubscribed.INSTANCE ? Subscriptions.unsubscribed() : subscription;
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return get() == Unsubscribed.INSTANCE;
    }

    public boolean replace(Subscription subscription) {
        Subscription subscription2;
        do {
            subscription2 = get();
            if (subscription2 == Unsubscribed.INSTANCE) {
                if (subscription != null) {
                    subscription.unsubscribe();
                    return false;
                }
                return false;
            }
        } while (!compareAndSet(subscription2, subscription));
        return true;
    }

    public boolean replaceWeak(Subscription subscription) {
        Subscription subscription2 = get();
        Unsubscribed unsubscribed = Unsubscribed.INSTANCE;
        if (subscription2 == unsubscribed) {
            if (subscription != null) {
                subscription.unsubscribe();
            }
            return false;
        } else if (!compareAndSet(subscription2, subscription) && get() == unsubscribed) {
            if (subscription != null) {
                subscription.unsubscribe();
            }
            return false;
        } else {
            return true;
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        Subscription andSet;
        Subscription subscription = get();
        Unsubscribed unsubscribed = Unsubscribed.INSTANCE;
        if (subscription == unsubscribed || (andSet = getAndSet(unsubscribed)) == null || andSet == unsubscribed) {
            return;
        }
        andSet.unsubscribe();
    }

    public boolean update(Subscription subscription) {
        Subscription subscription2;
        do {
            subscription2 = get();
            if (subscription2 == Unsubscribed.INSTANCE) {
                if (subscription != null) {
                    subscription.unsubscribe();
                    return false;
                }
                return false;
            }
        } while (!compareAndSet(subscription2, subscription));
        if (subscription2 != null) {
            subscription2.unsubscribe();
            return true;
        }
        return true;
    }

    public boolean updateWeak(Subscription subscription) {
        Subscription subscription2 = get();
        Unsubscribed unsubscribed = Unsubscribed.INSTANCE;
        if (subscription2 == unsubscribed) {
            if (subscription != null) {
                subscription.unsubscribe();
            }
            return false;
        } else if (compareAndSet(subscription2, subscription)) {
            return true;
        } else {
            Subscription subscription3 = get();
            if (subscription != null) {
                subscription.unsubscribe();
            }
            return subscription3 == unsubscribed;
        }
    }

    public SequentialSubscription(Subscription subscription) {
        lazySet(subscription);
    }
}
