package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue;
import io.reactivex.rxjava3.internal.queue.MpscLinkedQueue;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.processors.UnicastProcessor;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableWindowTimed<T> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, Flowable<T>> {
    public final long i;
    public final long j;
    public final TimeUnit k;
    public final Scheduler l;
    public final long m;
    public final int n;
    public final boolean o;

    /* loaded from: classes12.dex */
    public static abstract class a<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 5724293814035355511L;
        public final int bufferSize;
        public volatile boolean done;
        public final Subscriber<? super Flowable<T>> downstream;
        public long emitted;
        public Throwable error;
        public final long timespan;
        public final TimeUnit unit;
        public Subscription upstream;
        public volatile boolean upstreamCancelled;
        public final SimplePlainQueue<Object> queue = new MpscLinkedQueue();
        public final AtomicLong requested = new AtomicLong();
        public final AtomicBoolean downstreamCancelled = new AtomicBoolean();
        public final AtomicInteger windowCount = new AtomicInteger(1);

        public a(Subscriber<? super Flowable<T>> subscriber, long j, TimeUnit timeUnit, int i) {
            this.downstream = subscriber;
            this.timespan = j;
            this.unit = timeUnit;
            this.bufferSize = i;
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            if (this.downstreamCancelled.compareAndSet(false, true)) {
                windowDone();
            }
        }

        abstract void cleanupResources();

        abstract void createFirstWindow();

        abstract void drain();

        @Override // org.reactivestreams.Subscriber
        public final void onComplete() {
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public final void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public final void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public final void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                createFirstWindow();
            }
        }

        @Override // org.reactivestreams.Subscription
        public final void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
            }
        }

        final void windowDone() {
            if (this.windowCount.decrementAndGet() == 0) {
                cleanupResources();
                this.upstream.cancel();
                this.upstreamCancelled = true;
                drain();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends a<T> implements Runnable {
        private static final long serialVersionUID = -6130475889925953722L;
        public long count;
        public final long maxSize;
        public final boolean restartTimerOnMaxSize;
        public final Scheduler scheduler;
        public final SequentialDisposable timer;
        public UnicastProcessor<T> window;
        public final Scheduler.Worker worker;

        /* loaded from: classes12.dex */
        public static final class a implements Runnable {
            public final b<?> h;
            public final long i;

            public a(b<?> bVar, long j) {
                this.h = bVar;
                this.i = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.boundary(this);
            }
        }

        public b(Subscriber<? super Flowable<T>> subscriber, long j, TimeUnit timeUnit, Scheduler scheduler, int i, long j2, boolean z) {
            super(subscriber, j, timeUnit, i);
            this.scheduler = scheduler;
            this.maxSize = j2;
            this.restartTimerOnMaxSize = z;
            if (z) {
                this.worker = scheduler.createWorker();
            } else {
                this.worker = null;
            }
            this.timer = new SequentialDisposable();
        }

        public void boundary(a aVar) {
            this.queue.offer(aVar);
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableWindowTimed.a
        public void cleanupResources() {
            this.timer.dispose();
            Scheduler.Worker worker = this.worker;
            if (worker != null) {
                worker.dispose();
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableWindowTimed.a
        public void createFirstWindow() {
            if (this.downstreamCancelled.get()) {
                return;
            }
            if (this.requested.get() != 0) {
                this.emitted = 1L;
                this.windowCount.getAndIncrement();
                this.window = UnicastProcessor.create(this.bufferSize, this);
                io.reactivex.rxjava3.internal.operators.flowable.b bVar = new io.reactivex.rxjava3.internal.operators.flowable.b(this.window);
                this.downstream.onNext(bVar);
                a aVar = new a(this, 1L);
                if (this.restartTimerOnMaxSize) {
                    SequentialDisposable sequentialDisposable = this.timer;
                    Scheduler.Worker worker = this.worker;
                    long j = this.timespan;
                    sequentialDisposable.replace(worker.schedulePeriodically(aVar, j, j, this.unit));
                } else {
                    SequentialDisposable sequentialDisposable2 = this.timer;
                    Scheduler scheduler = this.scheduler;
                    long j2 = this.timespan;
                    sequentialDisposable2.replace(scheduler.schedulePeriodicallyDirect(aVar, j2, j2, this.unit));
                }
                if (bVar.e()) {
                    this.window.onComplete();
                }
                this.upstream.request(Long.MAX_VALUE);
                return;
            }
            this.upstream.cancel();
            this.downstream.onError(new MissingBackpressureException(FlowableWindowTimed.e(this.emitted)));
            cleanupResources();
            this.upstreamCancelled = true;
        }

        public UnicastProcessor<T> createNewWindow(UnicastProcessor<T> unicastProcessor) {
            if (unicastProcessor != null) {
                unicastProcessor.onComplete();
                unicastProcessor = null;
            }
            if (this.downstreamCancelled.get()) {
                cleanupResources();
            } else {
                long j = this.emitted;
                if (this.requested.get() == j) {
                    this.upstream.cancel();
                    cleanupResources();
                    this.upstreamCancelled = true;
                    this.downstream.onError(new MissingBackpressureException(FlowableWindowTimed.e(j)));
                } else {
                    long j2 = j + 1;
                    this.emitted = j2;
                    this.windowCount.getAndIncrement();
                    unicastProcessor = UnicastProcessor.create(this.bufferSize, this);
                    this.window = unicastProcessor;
                    io.reactivex.rxjava3.internal.operators.flowable.b bVar = new io.reactivex.rxjava3.internal.operators.flowable.b(unicastProcessor);
                    this.downstream.onNext(bVar);
                    if (this.restartTimerOnMaxSize) {
                        SequentialDisposable sequentialDisposable = this.timer;
                        Scheduler.Worker worker = this.worker;
                        a aVar = new a(this, j2);
                        long j3 = this.timespan;
                        sequentialDisposable.update(worker.schedulePeriodically(aVar, j3, j3, this.unit));
                    }
                    if (bVar.e()) {
                        unicastProcessor.onComplete();
                    }
                }
            }
            return unicastProcessor;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableWindowTimed.a
        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            SimplePlainQueue<Object> simplePlainQueue = this.queue;
            Subscriber<? super Flowable<T>> subscriber = this.downstream;
            UnicastProcessor<T> unicastProcessor = this.window;
            int i = 1;
            while (true) {
                if (this.upstreamCancelled) {
                    simplePlainQueue.clear();
                    this.window = null;
                    unicastProcessor = null;
                } else {
                    boolean z = this.done;
                    Object poll = simplePlainQueue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            if (unicastProcessor != 0) {
                                unicastProcessor.onError(th);
                            }
                            subscriber.onError(th);
                        } else {
                            if (unicastProcessor != 0) {
                                unicastProcessor.onComplete();
                            }
                            subscriber.onComplete();
                        }
                        cleanupResources();
                        this.upstreamCancelled = true;
                    } else if (!z2) {
                        if (poll instanceof a) {
                            if (((a) poll).i == this.emitted || !this.restartTimerOnMaxSize) {
                                this.count = 0L;
                                unicastProcessor = createNewWindow(unicastProcessor);
                            }
                        } else if (unicastProcessor != null) {
                            unicastProcessor.onNext(poll);
                            long j = this.count + 1;
                            if (j == this.maxSize) {
                                this.count = 0L;
                                unicastProcessor = createNewWindow(unicastProcessor);
                            } else {
                                this.count = j;
                            }
                        }
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            windowDone();
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T> extends a<T> implements Runnable {
        public static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 1155822639622580836L;
        public final Scheduler scheduler;
        public final SequentialDisposable timer;
        public UnicastProcessor<T> window;
        public final Runnable windowRunnable;

        /* loaded from: classes12.dex */
        public final class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.windowDone();
            }
        }

        public c(Subscriber<? super Flowable<T>> subscriber, long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
            super(subscriber, j, timeUnit, i);
            this.scheduler = scheduler;
            this.timer = new SequentialDisposable();
            this.windowRunnable = new a();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableWindowTimed.a
        public void cleanupResources() {
            this.timer.dispose();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableWindowTimed.a
        public void createFirstWindow() {
            if (this.downstreamCancelled.get()) {
                return;
            }
            if (this.requested.get() != 0) {
                this.windowCount.getAndIncrement();
                this.window = UnicastProcessor.create(this.bufferSize, this.windowRunnable);
                this.emitted = 1L;
                io.reactivex.rxjava3.internal.operators.flowable.b bVar = new io.reactivex.rxjava3.internal.operators.flowable.b(this.window);
                this.downstream.onNext(bVar);
                SequentialDisposable sequentialDisposable = this.timer;
                Scheduler scheduler = this.scheduler;
                long j = this.timespan;
                sequentialDisposable.replace(scheduler.schedulePeriodicallyDirect(this, j, j, this.unit));
                if (bVar.e()) {
                    this.window.onComplete();
                }
                this.upstream.request(Long.MAX_VALUE);
                return;
            }
            this.upstream.cancel();
            this.downstream.onError(new MissingBackpressureException(FlowableWindowTimed.e(this.emitted)));
            cleanupResources();
            this.upstreamCancelled = true;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableWindowTimed.a
        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            SimplePlainQueue<Object> simplePlainQueue = this.queue;
            Subscriber<? super Flowable<T>> subscriber = this.downstream;
            UnicastProcessor<T> unicastProcessor = this.window;
            int i = 1;
            while (true) {
                if (this.upstreamCancelled) {
                    simplePlainQueue.clear();
                    this.window = null;
                    unicastProcessor = (UnicastProcessor<T>) null;
                } else {
                    boolean z = this.done;
                    Object poll = simplePlainQueue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            if (unicastProcessor != null) {
                                unicastProcessor.onError(th);
                            }
                            subscriber.onError(th);
                        } else {
                            if (unicastProcessor != null) {
                                unicastProcessor.onComplete();
                            }
                            subscriber.onComplete();
                        }
                        cleanupResources();
                        this.upstreamCancelled = true;
                    } else if (!z2) {
                        if (poll == NEXT_WINDOW) {
                            if (unicastProcessor != null) {
                                unicastProcessor.onComplete();
                                this.window = null;
                                unicastProcessor = (UnicastProcessor<T>) null;
                            }
                            if (this.downstreamCancelled.get()) {
                                this.timer.dispose();
                            } else {
                                long j = this.requested.get();
                                long j2 = this.emitted;
                                if (j == j2) {
                                    this.upstream.cancel();
                                    cleanupResources();
                                    this.upstreamCancelled = true;
                                    subscriber.onError(new MissingBackpressureException(FlowableWindowTimed.e(this.emitted)));
                                } else {
                                    this.emitted = j2 + 1;
                                    this.windowCount.getAndIncrement();
                                    unicastProcessor = (UnicastProcessor<T>) UnicastProcessor.create(this.bufferSize, this.windowRunnable);
                                    this.window = unicastProcessor;
                                    io.reactivex.rxjava3.internal.operators.flowable.b bVar = new io.reactivex.rxjava3.internal.operators.flowable.b(unicastProcessor);
                                    subscriber.onNext(bVar);
                                    if (bVar.e()) {
                                        unicastProcessor.onComplete();
                                    }
                                }
                            }
                        } else if (unicastProcessor != null) {
                            unicastProcessor.onNext(poll);
                        }
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.queue.offer(NEXT_WINDOW);
            drain();
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<T> extends a<T> implements Runnable {
        private static final long serialVersionUID = -7852870764194095894L;
        public final long timeskip;
        public final List<UnicastProcessor<T>> windows;
        public final Scheduler.Worker worker;
        public static final Object WINDOW_OPEN = new Object();
        public static final Object WINDOW_CLOSE = new Object();

        /* loaded from: classes12.dex */
        public static final class a implements Runnable {
            public final d<?> h;
            public final boolean i;

            public a(d<?> dVar, boolean z) {
                this.h = dVar;
                this.i = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.boundary(this.i);
            }
        }

        public d(Subscriber<? super Flowable<T>> subscriber, long j, long j2, TimeUnit timeUnit, Scheduler.Worker worker, int i) {
            super(subscriber, j, timeUnit, i);
            this.timeskip = j2;
            this.worker = worker;
            this.windows = new LinkedList();
        }

        public void boundary(boolean z) {
            this.queue.offer(z ? WINDOW_OPEN : WINDOW_CLOSE);
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableWindowTimed.a
        public void cleanupResources() {
            this.worker.dispose();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableWindowTimed.a
        public void createFirstWindow() {
            if (this.downstreamCancelled.get()) {
                return;
            }
            if (this.requested.get() != 0) {
                this.emitted = 1L;
                this.windowCount.getAndIncrement();
                UnicastProcessor<T> create = UnicastProcessor.create(this.bufferSize, this);
                this.windows.add(create);
                io.reactivex.rxjava3.internal.operators.flowable.b bVar = new io.reactivex.rxjava3.internal.operators.flowable.b(create);
                this.downstream.onNext(bVar);
                this.worker.schedule(new a(this, false), this.timespan, this.unit);
                Scheduler.Worker worker = this.worker;
                a aVar = new a(this, true);
                long j = this.timeskip;
                worker.schedulePeriodically(aVar, j, j, this.unit);
                if (bVar.e()) {
                    create.onComplete();
                    this.windows.remove(create);
                }
                this.upstream.request(Long.MAX_VALUE);
                return;
            }
            this.upstream.cancel();
            this.downstream.onError(new MissingBackpressureException(FlowableWindowTimed.e(this.emitted)));
            cleanupResources();
            this.upstreamCancelled = true;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableWindowTimed.a
        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            SimplePlainQueue<Object> simplePlainQueue = this.queue;
            Subscriber<? super Flowable<T>> subscriber = this.downstream;
            List<UnicastProcessor<T>> list = this.windows;
            int i = 1;
            while (true) {
                if (this.upstreamCancelled) {
                    simplePlainQueue.clear();
                    list.clear();
                } else {
                    boolean z = this.done;
                    Object poll = simplePlainQueue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            for (UnicastProcessor<T> unicastProcessor : list) {
                                unicastProcessor.onError(th);
                            }
                            subscriber.onError(th);
                        } else {
                            for (UnicastProcessor<T> unicastProcessor2 : list) {
                                unicastProcessor2.onComplete();
                            }
                            subscriber.onComplete();
                        }
                        cleanupResources();
                        this.upstreamCancelled = true;
                    } else if (!z2) {
                        if (poll == WINDOW_OPEN) {
                            if (!this.downstreamCancelled.get()) {
                                long j = this.emitted;
                                if (this.requested.get() != j) {
                                    this.emitted = j + 1;
                                    this.windowCount.getAndIncrement();
                                    UnicastProcessor<T> create = UnicastProcessor.create(this.bufferSize, this);
                                    list.add(create);
                                    io.reactivex.rxjava3.internal.operators.flowable.b bVar = new io.reactivex.rxjava3.internal.operators.flowable.b(create);
                                    subscriber.onNext(bVar);
                                    this.worker.schedule(new a(this, false), this.timespan, this.unit);
                                    if (bVar.e()) {
                                        create.onComplete();
                                    }
                                } else {
                                    this.upstream.cancel();
                                    MissingBackpressureException missingBackpressureException = new MissingBackpressureException(FlowableWindowTimed.e(j));
                                    for (UnicastProcessor<T> unicastProcessor3 : list) {
                                        unicastProcessor3.onError(missingBackpressureException);
                                    }
                                    subscriber.onError(missingBackpressureException);
                                    cleanupResources();
                                    this.upstreamCancelled = true;
                                }
                            }
                        } else if (poll == WINDOW_CLOSE) {
                            if (!list.isEmpty()) {
                                list.remove(0).onComplete();
                            }
                        } else {
                            for (UnicastProcessor<T> unicastProcessor4 : list) {
                                unicastProcessor4.onNext(poll);
                            }
                        }
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            windowDone();
        }
    }

    public FlowableWindowTimed(Flowable<T> flowable, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, long j3, int i, boolean z) {
        super(flowable);
        this.i = j;
        this.j = j2;
        this.k = timeUnit;
        this.l = scheduler;
        this.m = j3;
        this.n = i;
        this.o = z;
    }

    public static String e(long j) {
        return "Unable to emit the next window (#" + j + ") due to lack of requests. Please make sure the downstream is ready to consume windows.";
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super Flowable<T>> subscriber) {
        if (this.i == this.j) {
            if (this.m == Long.MAX_VALUE) {
                this.source.subscribe((FlowableSubscriber) new c(subscriber, this.i, this.k, this.l, this.n));
                return;
            } else {
                this.source.subscribe((FlowableSubscriber) new b(subscriber, this.i, this.k, this.l, this.n, this.m, this.o));
                return;
            }
        }
        this.source.subscribe((FlowableSubscriber) new d(subscriber, this.i, this.j, this.k, this.l.createWorker(), this.n));
    }
}
