package rx.internal.schedulers;

import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class TrampolineScheduler extends Scheduler {
    public static final TrampolineScheduler INSTANCE = new TrampolineScheduler();

    /* loaded from: classes13.dex */
    public static final class a extends Scheduler.Worker {
        public final AtomicInteger h = new AtomicInteger();
        public final PriorityBlockingQueue<b> i = new PriorityBlockingQueue<>();
        public final BooleanSubscription j = new BooleanSubscription();
        public final AtomicInteger k = new AtomicInteger();

        /* renamed from: rx.internal.schedulers.TrampolineScheduler$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0972a implements Action0 {
            public final /* synthetic */ b h;

            public C0972a(b bVar) {
                this.h = bVar;
            }

            @Override // rx.functions.Action0
            public void call() {
                a.this.i.remove(this.h);
            }
        }

        public final Subscription a(Action0 action0, long j) {
            if (this.j.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            b bVar = new b(action0, Long.valueOf(j), this.h.incrementAndGet());
            this.i.add(bVar);
            if (this.k.getAndIncrement() == 0) {
                do {
                    b poll = this.i.poll();
                    if (poll != null) {
                        poll.h.call();
                    }
                } while (this.k.decrementAndGet() > 0);
                return Subscriptions.unsubscribed();
            }
            return Subscriptions.create(new C0972a(bVar));
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.j.isUnsubscribed();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            return a(action0, now());
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.j.unsubscribe();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            long now = now() + timeUnit.toMillis(j);
            return a(new rx.internal.schedulers.b(action0, this, now), now);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b implements Comparable<b> {
        public final Action0 h;
        public final Long i;
        public final int j;

        public b(Action0 action0, Long l, int i) {
            this.h = action0;
            this.i = l;
            this.j = i;
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(b bVar) {
            int compareTo = this.i.compareTo(bVar.i);
            return compareTo == 0 ? TrampolineScheduler.a(this.j, bVar.j) : compareTo;
        }
    }

    public static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new a();
    }
}
