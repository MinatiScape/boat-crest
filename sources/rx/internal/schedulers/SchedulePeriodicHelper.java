package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.subscriptions.SequentialSubscription;
/* loaded from: classes13.dex */
public final class SchedulePeriodicHelper {
    public static final long CLOCK_DRIFT_TOLERANCE_NANOS = TimeUnit.MINUTES.toNanos(Long.getLong("rx.scheduler.drift-tolerance", 15).longValue());

    /* loaded from: classes13.dex */
    public interface NowNanoSupplier {
        long nowNanos();
    }

    /* loaded from: classes13.dex */
    public static class a implements Action0 {
        public long h;
        public long i;
        public long j;
        public final /* synthetic */ long k;
        public final /* synthetic */ long l;
        public final /* synthetic */ Action0 m;
        public final /* synthetic */ SequentialSubscription n;
        public final /* synthetic */ NowNanoSupplier o;
        public final /* synthetic */ Scheduler.Worker p;
        public final /* synthetic */ long q;

        public a(long j, long j2, Action0 action0, SequentialSubscription sequentialSubscription, NowNanoSupplier nowNanoSupplier, Scheduler.Worker worker, long j3) {
            this.k = j;
            this.l = j2;
            this.m = action0;
            this.n = sequentialSubscription;
            this.o = nowNanoSupplier;
            this.p = worker;
            this.q = j3;
            this.i = j;
            this.j = j2;
        }

        @Override // rx.functions.Action0
        public void call() {
            long j;
            this.m.call();
            if (this.n.isUnsubscribed()) {
                return;
            }
            NowNanoSupplier nowNanoSupplier = this.o;
            long nowNanos = nowNanoSupplier != null ? nowNanoSupplier.nowNanos() : TimeUnit.MILLISECONDS.toNanos(this.p.now());
            long j2 = SchedulePeriodicHelper.CLOCK_DRIFT_TOLERANCE_NANOS;
            long j3 = this.i;
            if (nowNanos + j2 >= j3) {
                long j4 = this.q;
                if (nowNanos < j3 + j4 + j2) {
                    long j5 = this.j;
                    long j6 = this.h + 1;
                    this.h = j6;
                    j = j5 + (j6 * j4);
                    this.i = nowNanos;
                    this.n.replace(this.p.schedule(this, j - nowNanos, TimeUnit.NANOSECONDS));
                }
            }
            long j7 = this.q;
            long j8 = nowNanos + j7;
            long j9 = this.h + 1;
            this.h = j9;
            this.j = j8 - (j7 * j9);
            j = j8;
            this.i = nowNanos;
            this.n.replace(this.p.schedule(this, j - nowNanos, TimeUnit.NANOSECONDS));
        }
    }

    public SchedulePeriodicHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static Subscription schedulePeriodically(Scheduler.Worker worker, Action0 action0, long j, long j2, TimeUnit timeUnit, NowNanoSupplier nowNanoSupplier) {
        long nanos = timeUnit.toNanos(j2);
        long nowNanos = nowNanoSupplier != null ? nowNanoSupplier.nowNanos() : TimeUnit.MILLISECONDS.toNanos(worker.now());
        SequentialSubscription sequentialSubscription = new SequentialSubscription();
        SequentialSubscription sequentialSubscription2 = new SequentialSubscription(sequentialSubscription);
        sequentialSubscription.replace(worker.schedule(new a(nowNanos, timeUnit.toNanos(j) + nowNanos, action0, sequentialSubscription2, nowNanoSupplier, worker, nanos), j, timeUnit));
        return sequentialSubscription2;
    }
}
