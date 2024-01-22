package rx.internal.schedulers;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.MultipleAssignmentSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class ExecutorScheduler extends Scheduler {

    /* renamed from: a  reason: collision with root package name */
    public final Executor f15681a;

    public ExecutorScheduler(Executor executor) {
        this.f15681a = executor;
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new a(this.f15681a);
    }

    /* loaded from: classes13.dex */
    public static final class a extends Scheduler.Worker implements Runnable {
        public final Executor h;
        public final ConcurrentLinkedQueue<ScheduledAction> j = new ConcurrentLinkedQueue<>();
        public final AtomicInteger k = new AtomicInteger();
        public final CompositeSubscription i = new CompositeSubscription();
        public final ScheduledExecutorService l = GenericScheduledExecutorService.getInstance();

        /* renamed from: rx.internal.schedulers.ExecutorScheduler$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0970a implements Action0 {
            public final /* synthetic */ MultipleAssignmentSubscription h;

            public C0970a(MultipleAssignmentSubscription multipleAssignmentSubscription) {
                this.h = multipleAssignmentSubscription;
            }

            @Override // rx.functions.Action0
            public void call() {
                a.this.i.remove(this.h);
            }
        }

        /* loaded from: classes13.dex */
        public class b implements Action0 {
            public final /* synthetic */ MultipleAssignmentSubscription h;
            public final /* synthetic */ Action0 i;
            public final /* synthetic */ Subscription j;

            public b(MultipleAssignmentSubscription multipleAssignmentSubscription, Action0 action0, Subscription subscription) {
                this.h = multipleAssignmentSubscription;
                this.i = action0;
                this.j = subscription;
            }

            @Override // rx.functions.Action0
            public void call() {
                if (this.h.isUnsubscribed()) {
                    return;
                }
                Subscription schedule = a.this.schedule(this.i);
                this.h.set(schedule);
                if (schedule.getClass() == ScheduledAction.class) {
                    ((ScheduledAction) schedule).add(this.j);
                }
            }
        }

        public a(Executor executor) {
            this.h = executor;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.i.isUnsubscribed();
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!this.i.isUnsubscribed()) {
                ScheduledAction poll = this.j.poll();
                if (poll == null) {
                    return;
                }
                if (!poll.isUnsubscribed()) {
                    if (!this.i.isUnsubscribed()) {
                        poll.run();
                    } else {
                        this.j.clear();
                        return;
                    }
                }
                if (this.k.decrementAndGet() == 0) {
                    return;
                }
            }
            this.j.clear();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            ScheduledAction scheduledAction = new ScheduledAction(RxJavaHooks.onScheduledAction(action0), this.i);
            this.i.add(scheduledAction);
            this.j.offer(scheduledAction);
            if (this.k.getAndIncrement() == 0) {
                try {
                    this.h.execute(this);
                } catch (RejectedExecutionException e) {
                    this.i.remove(scheduledAction);
                    this.k.decrementAndGet();
                    RxJavaHooks.onError(e);
                    throw e;
                }
            }
            return scheduledAction;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.i.unsubscribe();
            this.j.clear();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            if (j <= 0) {
                return schedule(action0);
            }
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            Action0 onScheduledAction = RxJavaHooks.onScheduledAction(action0);
            MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
            MultipleAssignmentSubscription multipleAssignmentSubscription2 = new MultipleAssignmentSubscription();
            multipleAssignmentSubscription2.set(multipleAssignmentSubscription);
            this.i.add(multipleAssignmentSubscription2);
            Subscription create = Subscriptions.create(new C0970a(multipleAssignmentSubscription2));
            ScheduledAction scheduledAction = new ScheduledAction(new b(multipleAssignmentSubscription2, onScheduledAction, create));
            multipleAssignmentSubscription.set(scheduledAction);
            try {
                scheduledAction.add(this.l.schedule(scheduledAction, j, timeUnit));
                return create;
            } catch (RejectedExecutionException e) {
                RxJavaHooks.onError(e);
                throw e;
            }
        }
    }
}
