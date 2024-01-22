package rx.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.util.RxThreadFactory;
import rx.internal.util.SubscriptionList;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class EventLoopsScheduler extends Scheduler implements SchedulerLifecycle {
    public static final int j;
    public static final c k;
    public static final b l;
    public final ThreadFactory h;
    public final AtomicReference<b> i = new AtomicReference<>(l);

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public final int f15680a;
        public final c[] b;
        public long c;

        public b(ThreadFactory threadFactory, int i) {
            this.f15680a = i;
            this.b = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.b[i2] = new c(threadFactory);
            }
        }

        public c a() {
            int i = this.f15680a;
            if (i == 0) {
                return EventLoopsScheduler.k;
            }
            c[] cVarArr = this.b;
            long j = this.c;
            this.c = 1 + j;
            return cVarArr[(int) (j % i)];
        }

        public void b() {
            for (c cVar : this.b) {
                cVar.unsubscribe();
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c extends NewThreadWorker {
        public c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        int intValue = Integer.getInteger("rx.scheduler.max-computation-threads", 0).intValue();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (intValue <= 0 || intValue > availableProcessors) {
            intValue = availableProcessors;
        }
        j = intValue;
        c cVar = new c(RxThreadFactory.NONE);
        k = cVar;
        cVar.unsubscribe();
        l = new b(null, 0);
    }

    public EventLoopsScheduler(ThreadFactory threadFactory) {
        this.h = threadFactory;
        start();
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new a(this.i.get().a());
    }

    public Subscription scheduleDirect(Action0 action0) {
        return this.i.get().a().scheduleActual(action0, -1L, TimeUnit.NANOSECONDS);
    }

    @Override // rx.internal.schedulers.SchedulerLifecycle
    public void shutdown() {
        b bVar;
        b bVar2;
        do {
            bVar = this.i.get();
            bVar2 = l;
            if (bVar == bVar2) {
                return;
            }
        } while (!this.i.compareAndSet(bVar, bVar2));
        bVar.b();
    }

    @Override // rx.internal.schedulers.SchedulerLifecycle
    public void start() {
        b bVar = new b(this.h, j);
        if (this.i.compareAndSet(l, bVar)) {
            return;
        }
        bVar.b();
    }

    /* loaded from: classes13.dex */
    public static final class a extends Scheduler.Worker {
        public final SubscriptionList h;
        public final CompositeSubscription i;
        public final SubscriptionList j;
        public final c k;

        /* renamed from: rx.internal.schedulers.EventLoopsScheduler$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0969a implements Action0 {
            public final /* synthetic */ Action0 h;

            public C0969a(Action0 action0) {
                this.h = action0;
            }

            @Override // rx.functions.Action0
            public void call() {
                if (a.this.isUnsubscribed()) {
                    return;
                }
                this.h.call();
            }
        }

        /* loaded from: classes13.dex */
        public class b implements Action0 {
            public final /* synthetic */ Action0 h;

            public b(Action0 action0) {
                this.h = action0;
            }

            @Override // rx.functions.Action0
            public void call() {
                if (a.this.isUnsubscribed()) {
                    return;
                }
                this.h.call();
            }
        }

        public a(c cVar) {
            SubscriptionList subscriptionList = new SubscriptionList();
            this.h = subscriptionList;
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            this.i = compositeSubscription;
            this.j = new SubscriptionList(subscriptionList, compositeSubscription);
            this.k = cVar;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.j.isUnsubscribed();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.k.scheduleActual(new C0969a(action0), 0L, (TimeUnit) null, this.h);
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.j.unsubscribe();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            if (isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            return this.k.scheduleActual(new b(action0), j, timeUnit, this.i);
        }
    }
}
