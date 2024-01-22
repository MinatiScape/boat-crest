package io.reactivex.rxjava3.internal.schedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.disposables.ListCompositeDisposable;
import io.reactivex.rxjava3.internal.functions.ObjectHelper;
import io.reactivex.rxjava3.internal.schedulers.SchedulerMultiWorkerSupport;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ComputationScheduler extends Scheduler implements SchedulerMultiWorkerSupport {
    public static final b k;
    public static final RxThreadFactory l;
    public static final int m = a(Runtime.getRuntime().availableProcessors(), Integer.getInteger("rx3.computation-threads", 0).intValue());
    public static final c n;
    public final ThreadFactory i;
    public final AtomicReference<b> j;

    /* loaded from: classes12.dex */
    public static final class b implements SchedulerMultiWorkerSupport {
        public final int h;
        public final c[] i;
        public long j;

        public b(int i, ThreadFactory threadFactory) {
            this.h = i;
            this.i = new c[i];
            for (int i2 = 0; i2 < i; i2++) {
                this.i[i2] = new c(threadFactory);
            }
        }

        public c a() {
            int i = this.h;
            if (i == 0) {
                return ComputationScheduler.n;
            }
            c[] cVarArr = this.i;
            long j = this.j;
            this.j = 1 + j;
            return cVarArr[(int) (j % i)];
        }

        public void b() {
            for (c cVar : this.i) {
                cVar.dispose();
            }
        }

        @Override // io.reactivex.rxjava3.internal.schedulers.SchedulerMultiWorkerSupport
        public void createWorkers(int i, SchedulerMultiWorkerSupport.WorkerCallback workerCallback) {
            int i2 = this.h;
            if (i2 == 0) {
                for (int i3 = 0; i3 < i; i3++) {
                    workerCallback.onWorker(i3, ComputationScheduler.n);
                }
                return;
            }
            int i4 = ((int) this.j) % i2;
            for (int i5 = 0; i5 < i; i5++) {
                workerCallback.onWorker(i5, new a(this.i[i4]));
                i4++;
                if (i4 == i2) {
                    i4 = 0;
                }
            }
            this.j = i4;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends NewThreadWorker {
        public c(ThreadFactory threadFactory) {
            super(threadFactory);
        }
    }

    static {
        c cVar = new c(new RxThreadFactory("RxComputationShutdown"));
        n = cVar;
        cVar.dispose();
        RxThreadFactory rxThreadFactory = new RxThreadFactory("RxComputationThreadPool", Math.max(1, Math.min(10, Integer.getInteger("rx3.computation-priority", 5).intValue())), true);
        l = rxThreadFactory;
        b bVar = new b(0, rxThreadFactory);
        k = bVar;
        bVar.b();
    }

    public ComputationScheduler() {
        this(l);
    }

    public static int a(int i, int i2) {
        return (i2 <= 0 || i2 > i) ? i : i2;
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    @NonNull
    public Scheduler.Worker createWorker() {
        return new a(this.j.get().a());
    }

    @Override // io.reactivex.rxjava3.internal.schedulers.SchedulerMultiWorkerSupport
    public void createWorkers(int i, SchedulerMultiWorkerSupport.WorkerCallback workerCallback) {
        ObjectHelper.verifyPositive(i, "number > 0 required");
        this.j.get().createWorkers(i, workerCallback);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable runnable, long j, TimeUnit timeUnit) {
        return this.j.get().a().scheduleDirect(runnable, j, timeUnit);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    @NonNull
    public Disposable schedulePeriodicallyDirect(@NonNull Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.j.get().a().schedulePeriodicallyDirect(runnable, j, j2, timeUnit);
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public void shutdown() {
        AtomicReference<b> atomicReference = this.j;
        b bVar = k;
        b andSet = atomicReference.getAndSet(bVar);
        if (andSet != bVar) {
            andSet.b();
        }
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public void start() {
        b bVar = new b(m, this.i);
        if (this.j.compareAndSet(k, bVar)) {
            return;
        }
        bVar.b();
    }

    public ComputationScheduler(ThreadFactory threadFactory) {
        this.i = threadFactory;
        this.j = new AtomicReference<>(k);
        start();
    }

    /* loaded from: classes12.dex */
    public static final class a extends Scheduler.Worker {
        public final ListCompositeDisposable h;
        public final CompositeDisposable i;
        public final ListCompositeDisposable j;
        public final c k;
        public volatile boolean l;

        public a(c cVar) {
            this.k = cVar;
            ListCompositeDisposable listCompositeDisposable = new ListCompositeDisposable();
            this.h = listCompositeDisposable;
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            this.i = compositeDisposable;
            ListCompositeDisposable listCompositeDisposable2 = new ListCompositeDisposable();
            this.j = listCompositeDisposable2;
            listCompositeDisposable2.add(listCompositeDisposable);
            listCompositeDisposable2.add(compositeDisposable);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.j.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.l;
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable runnable) {
            if (this.l) {
                return EmptyDisposable.INSTANCE;
            }
            return this.k.scheduleActual(runnable, 0L, TimeUnit.MILLISECONDS, this.h);
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
            if (this.l) {
                return EmptyDisposable.INSTANCE;
            }
            return this.k.scheduleActual(runnable, j, timeUnit, this.i);
        }
    }
}
