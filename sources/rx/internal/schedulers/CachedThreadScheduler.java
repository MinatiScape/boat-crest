package rx.internal.schedulers;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.util.RxThreadFactory;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class CachedThreadScheduler extends Scheduler implements SchedulerLifecycle {
    public static final long j;
    public static final TimeUnit k = TimeUnit.SECONDS;
    public static final c l;
    public static final a m;
    public final ThreadFactory h;
    public final AtomicReference<a> i = new AtomicReference<>(m);

    /* loaded from: classes13.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public final ThreadFactory f15679a;
        public final long b;
        public final ConcurrentLinkedQueue<c> c;
        public final CompositeSubscription d;
        public final ScheduledExecutorService e;
        public final Future<?> f;

        /* renamed from: rx.internal.schedulers.CachedThreadScheduler$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class ThreadFactoryC0968a implements ThreadFactory {
            public final /* synthetic */ ThreadFactory h;

            public ThreadFactoryC0968a(a aVar, ThreadFactory threadFactory) {
                this.h = threadFactory;
            }

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                Thread newThread = this.h.newThread(runnable);
                newThread.setName(newThread.getName() + " (Evictor)");
                return newThread;
            }
        }

        /* loaded from: classes13.dex */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.a();
            }
        }

        public a(ThreadFactory threadFactory, long j, TimeUnit timeUnit) {
            ScheduledFuture<?> scheduledFuture;
            this.f15679a = threadFactory;
            long nanos = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.b = nanos;
            this.c = new ConcurrentLinkedQueue<>();
            this.d = new CompositeSubscription();
            ScheduledExecutorService scheduledExecutorService = null;
            if (timeUnit != null) {
                scheduledExecutorService = Executors.newScheduledThreadPool(1, new ThreadFactoryC0968a(this, threadFactory));
                NewThreadWorker.tryEnableCancelPolicy(scheduledExecutorService);
                scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(new b(), nanos, nanos, TimeUnit.NANOSECONDS);
            } else {
                scheduledFuture = null;
            }
            this.e = scheduledExecutorService;
            this.f = scheduledFuture;
        }

        public void a() {
            if (this.c.isEmpty()) {
                return;
            }
            long c = c();
            Iterator<c> it = this.c.iterator();
            while (it.hasNext()) {
                c next = it.next();
                if (next.c() > c) {
                    return;
                }
                if (this.c.remove(next)) {
                    this.d.remove(next);
                }
            }
        }

        public c b() {
            if (this.d.isUnsubscribed()) {
                return CachedThreadScheduler.l;
            }
            while (!this.c.isEmpty()) {
                c poll = this.c.poll();
                if (poll != null) {
                    return poll;
                }
            }
            c cVar = new c(this.f15679a);
            this.d.add(cVar);
            return cVar;
        }

        public long c() {
            return System.nanoTime();
        }

        public void d(c cVar) {
            cVar.d(c() + this.b);
            this.c.offer(cVar);
        }

        public void e() {
            try {
                Future<?> future = this.f;
                if (future != null) {
                    future.cancel(true);
                }
                ScheduledExecutorService scheduledExecutorService = this.e;
                if (scheduledExecutorService != null) {
                    scheduledExecutorService.shutdownNow();
                }
            } finally {
                this.d.unsubscribe();
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b extends Scheduler.Worker implements Action0 {
        public final a i;
        public final c j;
        public final CompositeSubscription h = new CompositeSubscription();
        public final AtomicBoolean k = new AtomicBoolean();

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public final /* synthetic */ Action0 h;

            public a(Action0 action0) {
                this.h = action0;
            }

            @Override // rx.functions.Action0
            public void call() {
                if (b.this.isUnsubscribed()) {
                    return;
                }
                this.h.call();
            }
        }

        public b(a aVar) {
            this.i = aVar;
            this.j = aVar.b();
        }

        @Override // rx.functions.Action0
        public void call() {
            this.i.d(this.j);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.h.isUnsubscribed();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            return schedule(action0, 0L, null);
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (this.k.compareAndSet(false, true)) {
                this.j.schedule(this);
            }
            this.h.unsubscribe();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            if (this.h.isUnsubscribed()) {
                return Subscriptions.unsubscribed();
            }
            ScheduledAction scheduleActual = this.j.scheduleActual(new a(action0), j, timeUnit);
            this.h.add(scheduleActual);
            scheduleActual.addParent(this.h);
            return scheduleActual;
        }
    }

    /* loaded from: classes13.dex */
    public static final class c extends NewThreadWorker {
        public long o;

        public c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.o = 0L;
        }

        public long c() {
            return this.o;
        }

        public void d(long j) {
            this.o = j;
        }
    }

    static {
        c cVar = new c(RxThreadFactory.NONE);
        l = cVar;
        cVar.unsubscribe();
        a aVar = new a(null, 0L, null);
        m = aVar;
        aVar.e();
        j = Integer.getInteger("rx.io-scheduler.keepalive", 60).intValue();
    }

    public CachedThreadScheduler(ThreadFactory threadFactory) {
        this.h = threadFactory;
        start();
    }

    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        return new b(this.i.get());
    }

    @Override // rx.internal.schedulers.SchedulerLifecycle
    public void shutdown() {
        a aVar;
        a aVar2;
        do {
            aVar = this.i.get();
            aVar2 = m;
            if (aVar == aVar2) {
                return;
            }
        } while (!this.i.compareAndSet(aVar, aVar2));
        aVar.e();
    }

    @Override // rx.internal.schedulers.SchedulerLifecycle
    public void start() {
        a aVar = new a(this.h, j, k);
        if (this.i.compareAndSet(m, aVar)) {
            return;
        }
        aVar.e();
    }
}
