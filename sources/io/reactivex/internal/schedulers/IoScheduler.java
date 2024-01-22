package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.EmptyDisposable;
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
/* loaded from: classes12.dex */
public final class IoScheduler extends Scheduler {
    public static final long KEEP_ALIVE_TIME_DEFAULT = 60;
    public static final RxThreadFactory k;
    public static final RxThreadFactory l;
    public static final c o;
    public static final a p;
    public final ThreadFactory i;
    public final AtomicReference<a> j;
    public static final TimeUnit n = TimeUnit.SECONDS;
    public static final long m = Long.getLong("rx2.io-keep-alive-time", 60).longValue();

    /* loaded from: classes12.dex */
    public static final class a implements Runnable {
        public final long h;
        public final ConcurrentLinkedQueue<c> i;
        public final CompositeDisposable j;
        public final ScheduledExecutorService k;
        public final Future<?> l;
        public final ThreadFactory m;

        public a(long j, TimeUnit timeUnit, ThreadFactory threadFactory) {
            ScheduledFuture<?> scheduledFuture;
            long nanos = timeUnit != null ? timeUnit.toNanos(j) : 0L;
            this.h = nanos;
            this.i = new ConcurrentLinkedQueue<>();
            this.j = new CompositeDisposable();
            this.m = threadFactory;
            ScheduledExecutorService scheduledExecutorService = null;
            if (timeUnit != null) {
                scheduledExecutorService = Executors.newScheduledThreadPool(1, IoScheduler.l);
                scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(this, nanos, nanos, TimeUnit.NANOSECONDS);
            } else {
                scheduledFuture = null;
            }
            this.k = scheduledExecutorService;
            this.l = scheduledFuture;
        }

        public void a() {
            if (this.i.isEmpty()) {
                return;
            }
            long c = c();
            Iterator<c> it = this.i.iterator();
            while (it.hasNext()) {
                c next = it.next();
                if (next.a() > c) {
                    return;
                }
                if (this.i.remove(next)) {
                    this.j.remove(next);
                }
            }
        }

        public c b() {
            if (this.j.isDisposed()) {
                return IoScheduler.o;
            }
            while (!this.i.isEmpty()) {
                c poll = this.i.poll();
                if (poll != null) {
                    return poll;
                }
            }
            c cVar = new c(this.m);
            this.j.add(cVar);
            return cVar;
        }

        public long c() {
            return System.nanoTime();
        }

        public void d(c cVar) {
            cVar.b(c() + this.h);
            this.i.offer(cVar);
        }

        public void e() {
            this.j.dispose();
            Future<?> future = this.l;
            if (future != null) {
                future.cancel(true);
            }
            ScheduledExecutorService scheduledExecutorService = this.k;
            if (scheduledExecutorService != null) {
                scheduledExecutorService.shutdownNow();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b extends Scheduler.Worker {
        public final a i;
        public final c j;
        public final AtomicBoolean k = new AtomicBoolean();
        public final CompositeDisposable h = new CompositeDisposable();

        public b(a aVar) {
            this.i = aVar;
            this.j = aVar.b();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.k.compareAndSet(false, true)) {
                this.h.dispose();
                this.i.d(this.j);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.k.get();
        }

        @Override // io.reactivex.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
            if (this.h.isDisposed()) {
                return EmptyDisposable.INSTANCE;
            }
            return this.j.scheduleActual(runnable, j, timeUnit, this.h);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends NewThreadWorker {
        public long j;

        public c(ThreadFactory threadFactory) {
            super(threadFactory);
            this.j = 0L;
        }

        public long a() {
            return this.j;
        }

        public void b(long j) {
            this.j = j;
        }
    }

    static {
        c cVar = new c(new RxThreadFactory("RxCachedThreadSchedulerShutdown"));
        o = cVar;
        cVar.dispose();
        int max = Math.max(1, Math.min(10, Integer.getInteger("rx2.io-priority", 5).intValue()));
        RxThreadFactory rxThreadFactory = new RxThreadFactory("RxCachedThreadScheduler", max);
        k = rxThreadFactory;
        l = new RxThreadFactory("RxCachedWorkerPoolEvictor", max);
        a aVar = new a(0L, null, rxThreadFactory);
        p = aVar;
        aVar.e();
    }

    public IoScheduler() {
        this(k);
    }

    @Override // io.reactivex.Scheduler
    @NonNull
    public Scheduler.Worker createWorker() {
        return new b(this.j.get());
    }

    @Override // io.reactivex.Scheduler
    public void shutdown() {
        a aVar;
        a aVar2;
        do {
            aVar = this.j.get();
            aVar2 = p;
            if (aVar == aVar2) {
                return;
            }
        } while (!this.j.compareAndSet(aVar, aVar2));
        aVar.e();
    }

    public int size() {
        return this.j.get().j.size();
    }

    @Override // io.reactivex.Scheduler
    public void start() {
        a aVar = new a(m, n, this.i);
        if (this.j.compareAndSet(p, aVar)) {
            return;
        }
        aVar.e();
    }

    public IoScheduler(ThreadFactory threadFactory) {
        this.i = threadFactory;
        this.j = new AtomicReference<>(p);
        start();
    }
}
