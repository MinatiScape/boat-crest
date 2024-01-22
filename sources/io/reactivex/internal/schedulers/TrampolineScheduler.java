package io.reactivex.internal.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public final class TrampolineScheduler extends Scheduler {
    public static final TrampolineScheduler i = new TrampolineScheduler();

    /* loaded from: classes12.dex */
    public static final class a implements Runnable {
        public final Runnable h;
        public final c i;
        public final long j;

        public a(Runnable runnable, c cVar, long j) {
            this.h = runnable;
            this.i = cVar;
            this.j = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.i.k) {
                return;
            }
            long now = this.i.now(TimeUnit.MILLISECONDS);
            long j = this.j;
            if (j > now) {
                try {
                    Thread.sleep(j - now);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    RxJavaPlugins.onError(e);
                    return;
                }
            }
            if (this.i.k) {
                return;
            }
            this.h.run();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b implements Comparable<b> {
        public final Runnable h;
        public final long i;
        public final int j;
        public volatile boolean k;

        public b(Runnable runnable, Long l, int i) {
            this.h = runnable;
            this.i = l.longValue();
            this.j = i;
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(b bVar) {
            int compare = ObjectHelper.compare(this.i, bVar.i);
            return compare == 0 ? ObjectHelper.compare(this.j, bVar.j) : compare;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c extends Scheduler.Worker {
        public final PriorityBlockingQueue<b> h = new PriorityBlockingQueue<>();
        public final AtomicInteger i = new AtomicInteger();
        public final AtomicInteger j = new AtomicInteger();
        public volatile boolean k;

        /* loaded from: classes12.dex */
        public final class a implements Runnable {
            public final b h;

            public a(b bVar) {
                this.h = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.k = true;
                c.this.h.remove(this.h);
            }
        }

        public Disposable a(Runnable runnable, long j) {
            if (this.k) {
                return EmptyDisposable.INSTANCE;
            }
            b bVar = new b(runnable, Long.valueOf(j), this.j.incrementAndGet());
            this.h.add(bVar);
            if (this.i.getAndIncrement() == 0) {
                int i = 1;
                while (!this.k) {
                    b poll = this.h.poll();
                    if (poll == null) {
                        i = this.i.addAndGet(-i);
                        if (i == 0) {
                            return EmptyDisposable.INSTANCE;
                        }
                    } else if (!poll.k) {
                        poll.h.run();
                    }
                }
                this.h.clear();
                return EmptyDisposable.INSTANCE;
            }
            return Disposables.fromRunnable(new a(bVar));
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.k = true;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.k;
        }

        @Override // io.reactivex.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable runnable) {
            return a(runnable, now(TimeUnit.MILLISECONDS));
        }

        @Override // io.reactivex.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
            long now = now(TimeUnit.MILLISECONDS) + timeUnit.toMillis(j);
            return a(new a(runnable, this, now), now);
        }
    }

    public static TrampolineScheduler instance() {
        return i;
    }

    @Override // io.reactivex.Scheduler
    @NonNull
    public Scheduler.Worker createWorker() {
        return new c();
    }

    @Override // io.reactivex.Scheduler
    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable runnable) {
        RxJavaPlugins.onSchedule(runnable).run();
        return EmptyDisposable.INSTANCE;
    }

    @Override // io.reactivex.Scheduler
    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable runnable, long j, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(j);
            RxJavaPlugins.onSchedule(runnable).run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            RxJavaPlugins.onError(e);
        }
        return EmptyDisposable.INSTANCE;
    }
}
