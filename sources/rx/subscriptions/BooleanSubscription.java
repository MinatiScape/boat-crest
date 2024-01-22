package rx.subscriptions;

import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public final class BooleanSubscription implements Subscription {
    public static final Action0 i = new a();
    public final AtomicReference<Action0> h;

    /* loaded from: classes13.dex */
    public static class a implements Action0 {
        @Override // rx.functions.Action0
        public void call() {
        }
    }

    public BooleanSubscription() {
        this.h = new AtomicReference<>();
    }

    public static BooleanSubscription create() {
        return new BooleanSubscription();
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.h.get() == i;
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        Action0 andSet;
        Action0 action0 = this.h.get();
        Action0 action02 = i;
        if (action0 == action02 || (andSet = this.h.getAndSet(action02)) == null || andSet == action02) {
            return;
        }
        andSet.call();
    }

    public static BooleanSubscription create(Action0 action0) {
        return new BooleanSubscription(action0);
    }

    public BooleanSubscription(Action0 action0) {
        this.h = new AtomicReference<>(action0);
    }
}
