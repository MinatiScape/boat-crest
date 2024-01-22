package rx.internal.schedulers;

import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Subscription;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action0;
import rx.internal.util.SubscriptionList;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class ScheduledAction extends AtomicReference<Thread> implements Runnable, Subscription {
    private static final long serialVersionUID = -3962399486978279857L;
    public final Action0 action;
    public final SubscriptionList cancel;

    /* loaded from: classes13.dex */
    public final class a implements Subscription {
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
            if (ScheduledAction.this.get() != Thread.currentThread()) {
                this.h.cancel(true);
            } else {
                this.h.cancel(false);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b extends AtomicBoolean implements Subscription {
        private static final long serialVersionUID = 247232374289553518L;
        public final SubscriptionList parent;
        public final ScheduledAction s;

        public b(ScheduledAction scheduledAction, SubscriptionList subscriptionList) {
            this.s = scheduledAction;
            this.parent = subscriptionList;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.s.isUnsubscribed();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.s);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c extends AtomicBoolean implements Subscription {
        private static final long serialVersionUID = 247232374289553518L;
        public final CompositeSubscription parent;
        public final ScheduledAction s;

        public c(ScheduledAction scheduledAction, CompositeSubscription compositeSubscription) {
            this.s = scheduledAction;
            this.parent = compositeSubscription;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.s.isUnsubscribed();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this.s);
            }
        }
    }

    public ScheduledAction(Action0 action0) {
        this.action = action0;
        this.cancel = new SubscriptionList();
    }

    public void add(Subscription subscription) {
        this.cancel.add(subscription);
    }

    public void addParent(CompositeSubscription compositeSubscription) {
        this.cancel.add(new c(this, compositeSubscription));
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.cancel.isUnsubscribed();
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            try {
                lazySet(Thread.currentThread());
                this.action.call();
            } finally {
                unsubscribe();
            }
        } catch (OnErrorNotImplementedException e) {
            signalError(new IllegalStateException("Exception thrown on Scheduler.Worker thread. Add `onError` handling.", e));
        } catch (Throwable th) {
            signalError(new IllegalStateException("Fatal Exception thrown on Scheduler.Worker thread.", th));
        }
    }

    public void signalError(Throwable th) {
        RxJavaHooks.onError(th);
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        if (this.cancel.isUnsubscribed()) {
            return;
        }
        this.cancel.unsubscribe();
    }

    public void add(Future<?> future) {
        this.cancel.add(new a(future));
    }

    public void addParent(SubscriptionList subscriptionList) {
        this.cancel.add(new b(this, subscriptionList));
    }

    public ScheduledAction(Action0 action0, CompositeSubscription compositeSubscription) {
        this.action = action0;
        this.cancel = new SubscriptionList(new c(this, compositeSubscription));
    }

    public ScheduledAction(Action0 action0, SubscriptionList subscriptionList) {
        this.action = action0;
        this.cancel = new SubscriptionList(new b(this, subscriptionList));
    }
}
