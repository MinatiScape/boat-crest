package rx.subscriptions;

import java.util.concurrent.Future;
import rx.Subscription;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public final class Subscriptions {

    /* renamed from: a  reason: collision with root package name */
    public static final b f15707a = new b();

    /* loaded from: classes13.dex */
    public static final class a implements Subscription {
        public final Future<?> h;

        public a(Future<?> future) {
            this.h = future;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.h.isCancelled();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.h.cancel(true);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b implements Subscription {
        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return true;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
        }
    }

    public Subscriptions() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription create(Action0 action0) {
        return BooleanSubscription.create(action0);
    }

    public static Subscription empty() {
        return BooleanSubscription.create();
    }

    public static Subscription from(Future<?> future) {
        return new a(future);
    }

    public static Subscription unsubscribed() {
        return f15707a;
    }

    public static CompositeSubscription from(Subscription... subscriptionArr) {
        return new CompositeSubscription(subscriptionArr);
    }
}
