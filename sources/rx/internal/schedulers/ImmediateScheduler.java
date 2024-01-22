package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class ImmediateScheduler extends Scheduler {
    public static final ImmediateScheduler INSTANCE = new ImmediateScheduler();

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new a();
    }

    /* loaded from: classes13.dex */
    public final class a extends Scheduler.Worker {
        public final BooleanSubscription h = new BooleanSubscription();

        public a() {
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.h.isUnsubscribed();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            return schedule(new b(action0, this, ImmediateScheduler.this.now() + timeUnit.toMillis(j)));
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.h.unsubscribe();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            action0.call();
            return Subscriptions.unsubscribed();
        }
    }
}
