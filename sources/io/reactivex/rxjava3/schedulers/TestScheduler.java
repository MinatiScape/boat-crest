package io.reactivex.rxjava3.schedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class TestScheduler extends Scheduler {
    public final Queue<b> i = new PriorityBlockingQueue(11);
    public long j;
    public volatile long k;

    /* loaded from: classes12.dex */
    public static final class b implements Comparable<b> {
        public final long h;
        public final Runnable i;
        public final a j;
        public final long k;

        public b(a aVar, long j, Runnable runnable, long j2) {
            this.h = j;
            this.i = runnable;
            this.j = aVar;
            this.k = j2;
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(b bVar) {
            long j = this.h;
            long j2 = bVar.h;
            if (j == j2) {
                return Long.compare(this.k, bVar.k);
            }
            return Long.compare(j, j2);
        }

        public String toString() {
            return String.format("TimedRunnable(time = %d, run = %s)", Long.valueOf(this.h), this.i.toString());
        }
    }

    public TestScheduler() {
    }

    public final void a(long j) {
        while (true) {
            b peek = this.i.peek();
            if (peek == null) {
                break;
            }
            long j2 = peek.h;
            if (j2 > j) {
                break;
            }
            if (j2 == 0) {
                j2 = this.k;
            }
            this.k = j2;
            this.i.remove(peek);
            if (!peek.j.h) {
                peek.i.run();
            }
        }
        this.k = j;
    }

    public void advanceTimeBy(long j, TimeUnit timeUnit) {
        advanceTimeTo(this.k + timeUnit.toNanos(j), TimeUnit.NANOSECONDS);
    }

    public void advanceTimeTo(long j, TimeUnit timeUnit) {
        a(timeUnit.toNanos(j));
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    @NonNull
    public Scheduler.Worker createWorker() {
        return new a();
    }

    @Override // io.reactivex.rxjava3.core.Scheduler
    public long now(@NonNull TimeUnit timeUnit) {
        return timeUnit.convert(this.k, TimeUnit.NANOSECONDS);
    }

    public void triggerActions() {
        a(this.k);
    }

    public TestScheduler(long j, TimeUnit timeUnit) {
        this.k = timeUnit.toNanos(j);
    }

    /* loaded from: classes12.dex */
    public final class a extends Scheduler.Worker {
        public volatile boolean h;

        /* renamed from: io.reactivex.rxjava3.schedulers.TestScheduler$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class RunnableC0868a implements Runnable {
            public final b h;

            public RunnableC0868a(b bVar) {
                this.h = bVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                TestScheduler.this.i.remove(this.h);
            }
        }

        public a() {
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.h = true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.h;
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        public long now(@NonNull TimeUnit timeUnit) {
            return TestScheduler.this.now(timeUnit);
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable runnable, long j, @NonNull TimeUnit timeUnit) {
            if (this.h) {
                return EmptyDisposable.INSTANCE;
            }
            long nanos = TestScheduler.this.k + timeUnit.toNanos(j);
            TestScheduler testScheduler = TestScheduler.this;
            long j2 = testScheduler.j;
            testScheduler.j = 1 + j2;
            b bVar = new b(this, nanos, runnable, j2);
            TestScheduler.this.i.add(bVar);
            return Disposable.fromRunnable(new RunnableC0868a(bVar));
        }

        @Override // io.reactivex.rxjava3.core.Scheduler.Worker
        @NonNull
        public Disposable schedule(@NonNull Runnable runnable) {
            if (this.h) {
                return EmptyDisposable.INSTANCE;
            }
            TestScheduler testScheduler = TestScheduler.this;
            long j = testScheduler.j;
            testScheduler.j = 1 + j;
            b bVar = new b(this, 0L, runnable, j);
            TestScheduler.this.i.add(bVar);
            return Disposable.fromRunnable(new RunnableC0868a(bVar));
        }
    }
}
