package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.internal.schedulers.NewThreadWorker;
import io.reactivex.rxjava3.internal.schedulers.SchedulerWhen;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.SchedulerRunnableIntrospection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public abstract class Scheduler {
    public static final long h = TimeUnit.MINUTES.toNanos(Long.getLong("rx3.scheduler.drift-tolerance", 15).longValue());

    /* loaded from: classes12.dex */
    public static abstract class Worker implements Disposable {

        /* loaded from: classes12.dex */
        public final class a implements Runnable, SchedulerRunnableIntrospection {
            @NonNull
            public final Runnable h;
            @NonNull
            public final SequentialDisposable i;
            public final long j;
            public long k;
            public long l;
            public long m;

            public a(long j, @NonNull Runnable runnable, long j2, @NonNull SequentialDisposable sequentialDisposable, long j3) {
                this.h = runnable;
                this.i = sequentialDisposable;
                this.j = j3;
                this.l = j2;
                this.m = j;
            }

            @Override // io.reactivex.rxjava3.schedulers.SchedulerRunnableIntrospection
            public Runnable getWrappedRunnable() {
                return this.h;
            }

            @Override // java.lang.Runnable
            public void run() {
                long j;
                this.h.run();
                if (this.i.isDisposed()) {
                    return;
                }
                Worker worker = Worker.this;
                TimeUnit timeUnit = TimeUnit.NANOSECONDS;
                long now = worker.now(timeUnit);
                long j2 = Scheduler.h;
                long j3 = this.l;
                if (now + j2 >= j3) {
                    long j4 = this.j;
                    if (now < j3 + j4 + j2) {
                        long j5 = this.m;
                        long j6 = this.k + 1;
                        this.k = j6;
                        j = j5 + (j6 * j4);
                        this.l = now;
                        this.i.replace(Worker.this.schedule(this, j - now, timeUnit));
                    }
                }
                long j7 = this.j;
                long j8 = now + j7;
                long j9 = this.k + 1;
                this.k = j9;
                this.m = j8 - (j7 * j9);
                j = j8;
                this.l = now;
                this.i.replace(Worker.this.schedule(this, j - now, timeUnit));
            }
        }

        public long now(@NonNull TimeUnit timeUnit) {
            return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @NonNull
        public Disposable schedule(@NonNull Runnable runnable) {
            return schedule(runnable, 0L, TimeUnit.NANOSECONDS);
        }

        @NonNull
        public abstract Disposable schedule(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit);

        @NonNull
        public Disposable schedulePeriodically(@NonNull Runnable runnable, long j, long j2, @NonNull TimeUnit timeUnit) {
            SequentialDisposable sequentialDisposable = new SequentialDisposable();
            SequentialDisposable sequentialDisposable2 = new SequentialDisposable(sequentialDisposable);
            Runnable onSchedule = RxJavaPlugins.onSchedule(runnable);
            long nanos = timeUnit.toNanos(j2);
            long now = now(TimeUnit.NANOSECONDS);
            Disposable schedule = schedule(new a(now + timeUnit.toNanos(j), onSchedule, now, sequentialDisposable2, nanos), j, timeUnit);
            if (schedule == EmptyDisposable.INSTANCE) {
                return schedule;
            }
            sequentialDisposable.replace(schedule);
            return sequentialDisposable2;
        }
    }

    /* loaded from: classes12.dex */
    public static final class a implements Disposable, Runnable, SchedulerRunnableIntrospection {
        @NonNull
        public final Runnable h;
        @NonNull
        public final Worker i;
        @Nullable
        public Thread j;

        public a(@NonNull Runnable runnable, @NonNull Worker worker) {
            this.h = runnable;
            this.i = worker;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.j == Thread.currentThread()) {
                Worker worker = this.i;
                if (worker instanceof NewThreadWorker) {
                    ((NewThreadWorker) worker).shutdown();
                    return;
                }
            }
            this.i.dispose();
        }

        @Override // io.reactivex.rxjava3.schedulers.SchedulerRunnableIntrospection
        public Runnable getWrappedRunnable() {
            return this.h;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.i.isDisposed();
        }

        @Override // java.lang.Runnable
        public void run() {
            this.j = Thread.currentThread();
            try {
                this.h.run();
            } finally {
                dispose();
                this.j = null;
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b implements Disposable, Runnable, SchedulerRunnableIntrospection {
        @NonNull
        public final Runnable h;
        @NonNull
        public final Worker i;
        public volatile boolean j;

        public b(@NonNull Runnable runnable, @NonNull Worker worker) {
            this.h = runnable;
            this.i = worker;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.j = true;
            this.i.dispose();
        }

        @Override // io.reactivex.rxjava3.schedulers.SchedulerRunnableIntrospection
        public Runnable getWrappedRunnable() {
            return this.h;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.j;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.j) {
                return;
            }
            try {
                this.h.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.i.dispose();
                throw ExceptionHelper.wrapOrThrow(th);
            }
        }
    }

    public static long clockDriftTolerance() {
        return h;
    }

    @NonNull
    public abstract Worker createWorker();

    public long now(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable runnable) {
        return scheduleDirect(runnable, 0L, TimeUnit.NANOSECONDS);
    }

    @NonNull
    public Disposable schedulePeriodicallyDirect(@NonNull Runnable runnable, long j, long j2, @NonNull TimeUnit timeUnit) {
        Worker createWorker = createWorker();
        b bVar = new b(RxJavaPlugins.onSchedule(runnable), createWorker);
        Disposable schedulePeriodically = createWorker.schedulePeriodically(bVar, j, j2, timeUnit);
        return schedulePeriodically == EmptyDisposable.INSTANCE ? schedulePeriodically : bVar;
    }

    public void shutdown() {
    }

    public void start() {
    }

    @NonNull
    public <S extends Scheduler & Disposable> S when(@NonNull Function<Flowable<Flowable<Completable>>, Completable> function) {
        Objects.requireNonNull(function, "combine is null");
        return new SchedulerWhen(function, this);
    }

    @NonNull
    public Disposable scheduleDirect(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
        Worker createWorker = createWorker();
        a aVar = new a(RxJavaPlugins.onSchedule(runnable), createWorker);
        createWorker.schedule(aVar, j, timeUnit);
        return aVar;
    }
}
