package rx.schedulers;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.schedulers.SchedulePeriodicHelper;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public class TestScheduler extends Scheduler {
    public static long c;

    /* renamed from: a  reason: collision with root package name */
    public final Queue<c> f15697a = new PriorityQueue(11, new a());
    public long b;

    /* loaded from: classes13.dex */
    public static final class a implements Comparator<c> {
        @Override // java.util.Comparator
        /* renamed from: a */
        public int compare(c cVar, c cVar2) {
            long j = cVar.f15698a;
            long j2 = cVar2.f15698a;
            if (j == j2) {
                if (cVar.d < cVar2.d) {
                    return -1;
                }
                return cVar.d > cVar2.d ? 1 : 0;
            } else if (j < j2) {
                return -1;
            } else {
                return j > j2 ? 1 : 0;
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        public final long f15698a;
        public final Action0 b;
        public final Scheduler.Worker c;
        public final long d;

        public c(Scheduler.Worker worker, long j, Action0 action0) {
            long j2 = TestScheduler.c;
            TestScheduler.c = 1 + j2;
            this.d = j2;
            this.f15698a = j;
            this.b = action0;
            this.c = worker;
        }

        public String toString() {
            return String.format("TimedAction(time = %d, action = %s)", Long.valueOf(this.f15698a), this.b.toString());
        }
    }

    public final void a(long j) {
        while (!this.f15697a.isEmpty()) {
            c peek = this.f15697a.peek();
            long j2 = peek.f15698a;
            if (j2 > j) {
                break;
            }
            if (j2 == 0) {
                j2 = this.b;
            }
            this.b = j2;
            this.f15697a.remove();
            if (!peek.c.isUnsubscribed()) {
                peek.b.call();
            }
        }
        this.b = j;
    }

    public void advanceTimeBy(long j, TimeUnit timeUnit) {
        advanceTimeTo(this.b + timeUnit.toNanos(j), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long j, TimeUnit timeUnit) {
        a(timeUnit.toNanos(j));
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new b();
    }

    @Override // rx.Scheduler
    public long now() {
        return TimeUnit.NANOSECONDS.toMillis(this.b);
    }

    public void triggerActions() {
        a(this.b);
    }

    /* loaded from: classes13.dex */
    public final class b extends Scheduler.Worker implements SchedulePeriodicHelper.NowNanoSupplier {
        public final BooleanSubscription h = new BooleanSubscription();

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public final /* synthetic */ c h;

            public a(c cVar) {
                this.h = cVar;
            }

            @Override // rx.functions.Action0
            public void call() {
                TestScheduler.this.f15697a.remove(this.h);
            }
        }

        /* renamed from: rx.schedulers.TestScheduler$b$b  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0973b implements Action0 {
            public final /* synthetic */ c h;

            public C0973b(c cVar) {
                this.h = cVar;
            }

            @Override // rx.functions.Action0
            public void call() {
                TestScheduler.this.f15697a.remove(this.h);
            }
        }

        public b() {
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.h.isUnsubscribed();
        }

        @Override // rx.Scheduler.Worker
        public long now() {
            return TestScheduler.this.now();
        }

        @Override // rx.internal.schedulers.SchedulePeriodicHelper.NowNanoSupplier
        public long nowNanos() {
            return TestScheduler.this.b;
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            c cVar = new c(this, TestScheduler.this.b + timeUnit.toNanos(j), action0);
            TestScheduler.this.f15697a.add(cVar);
            return Subscriptions.create(new a(cVar));
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedulePeriodically(Action0 action0, long j, long j2, TimeUnit timeUnit) {
            return SchedulePeriodicHelper.schedulePeriodically(this, action0, j, j2, timeUnit, this);
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.h.unsubscribe();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            c cVar = new c(this, 0L, action0);
            TestScheduler.this.f15697a.add(cVar);
            return Subscriptions.create(new C0973b(cVar));
        }
    }
}
