package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscribers.QueueDrainSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableWindowTimed<T> extends io.reactivex.internal.operators.flowable.a<T, Flowable<T>> {
    public final long i;
    public final long j;
    public final TimeUnit k;
    public final Scheduler l;
    public final long m;
    public final int n;
    public final boolean o;

    /* loaded from: classes12.dex */
    public static final class a<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Subscription {
        public final long j;
        public final TimeUnit k;
        public final Scheduler l;
        public final int m;
        public final boolean n;
        public final long o;
        public final Scheduler.Worker p;
        public long q;
        public long r;
        public Subscription s;
        public UnicastProcessor<T> t;
        public volatile boolean u;
        public final SequentialDisposable v;

        /* renamed from: io.reactivex.internal.operators.flowable.FlowableWindowTimed$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class RunnableC0756a implements Runnable {
            public final long h;
            public final a<?> i;

            public RunnableC0756a(long j, a<?> aVar) {
                this.h = j;
                this.i = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                a<?> aVar = this.i;
                if (!aVar.cancelled) {
                    aVar.queue.offer(this);
                } else {
                    aVar.u = true;
                }
                if (aVar.enter()) {
                    aVar.d();
                }
            }
        }

        public a(Subscriber<? super Flowable<T>> subscriber, long j, TimeUnit timeUnit, Scheduler scheduler, int i, long j2, boolean z) {
            super(subscriber, new MpscLinkedQueue());
            this.v = new SequentialDisposable();
            this.j = j;
            this.k = timeUnit;
            this.l = scheduler;
            this.m = i;
            this.o = j2;
            this.n = z;
            if (z) {
                this.p = scheduler.createWorker();
            } else {
                this.p = null;
            }
        }

        public void c() {
            this.v.dispose();
            Scheduler.Worker worker = this.p;
            if (worker != null) {
                worker.dispose();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
        }

        public void d() {
            SimpleQueue simpleQueue = this.queue;
            Subscriber<? super V> subscriber = this.downstream;
            UnicastProcessor<T> unicastProcessor = this.t;
            int i = 1;
            while (!this.u) {
                boolean z = this.done;
                Object poll = simpleQueue.poll();
                boolean z2 = poll == null;
                boolean z3 = poll instanceof RunnableC0756a;
                if (z && (z2 || z3)) {
                    this.t = null;
                    simpleQueue.clear();
                    Throwable th = this.error;
                    if (th != null) {
                        unicastProcessor.onError(th);
                    } else {
                        unicastProcessor.onComplete();
                    }
                    c();
                    return;
                } else if (z2) {
                    i = leave(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    int i2 = i;
                    if (z3) {
                        RunnableC0756a runnableC0756a = (RunnableC0756a) poll;
                        if (!this.n || this.r == runnableC0756a.h) {
                            unicastProcessor.onComplete();
                            this.q = 0L;
                            unicastProcessor = UnicastProcessor.create(this.m);
                            this.t = unicastProcessor;
                            long requested = requested();
                            if (requested != 0) {
                                subscriber.onNext(unicastProcessor);
                                if (requested != Long.MAX_VALUE) {
                                    produced(1L);
                                }
                            } else {
                                this.t = null;
                                this.queue.clear();
                                this.s.cancel();
                                subscriber.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
                                c();
                                return;
                            }
                        }
                    } else {
                        unicastProcessor.onNext(NotificationLite.getValue(poll));
                        long j = this.q + 1;
                        if (j >= this.o) {
                            this.r++;
                            this.q = 0L;
                            unicastProcessor.onComplete();
                            long requested2 = requested();
                            if (requested2 != 0) {
                                UnicastProcessor<T> create = UnicastProcessor.create(this.m);
                                this.t = create;
                                this.downstream.onNext(create);
                                if (requested2 != Long.MAX_VALUE) {
                                    produced(1L);
                                }
                                if (this.n) {
                                    this.v.get().dispose();
                                    Scheduler.Worker worker = this.p;
                                    RunnableC0756a runnableC0756a2 = new RunnableC0756a(this.r, this);
                                    long j2 = this.j;
                                    this.v.replace(worker.schedulePeriodically(runnableC0756a2, j2, j2, this.k));
                                }
                                unicastProcessor = create;
                            } else {
                                this.t = null;
                                this.s.cancel();
                                this.downstream.onError(new MissingBackpressureException("Could not deliver window due to lack of requests"));
                                c();
                                return;
                            }
                        } else {
                            this.q = j;
                        }
                    }
                    i = i2;
                }
            }
            this.s.cancel();
            simpleQueue.clear();
            c();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            if (enter()) {
                d();
            }
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            if (enter()) {
                d();
            }
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.u) {
                return;
            }
            if (fastEnter()) {
                UnicastProcessor<T> unicastProcessor = this.t;
                unicastProcessor.onNext(t);
                long j = this.q + 1;
                if (j >= this.o) {
                    this.r++;
                    this.q = 0L;
                    unicastProcessor.onComplete();
                    long requested = requested();
                    if (requested != 0) {
                        UnicastProcessor<T> create = UnicastProcessor.create(this.m);
                        this.t = create;
                        this.downstream.onNext(create);
                        if (requested != Long.MAX_VALUE) {
                            produced(1L);
                        }
                        if (this.n) {
                            this.v.get().dispose();
                            Scheduler.Worker worker = this.p;
                            RunnableC0756a runnableC0756a = new RunnableC0756a(this.r, this);
                            long j2 = this.j;
                            this.v.replace(worker.schedulePeriodically(runnableC0756a, j2, j2, this.k));
                        }
                    } else {
                        this.t = null;
                        this.s.cancel();
                        this.downstream.onError(new MissingBackpressureException("Could not deliver window due to lack of requests"));
                        c();
                        return;
                    }
                } else {
                    this.q = j;
                }
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
            }
            d();
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            Disposable schedulePeriodicallyDirect;
            if (SubscriptionHelper.validate(this.s, subscription)) {
                this.s = subscription;
                Subscriber<? super V> subscriber = this.downstream;
                subscriber.onSubscribe(this);
                if (this.cancelled) {
                    return;
                }
                UnicastProcessor<T> create = UnicastProcessor.create(this.m);
                this.t = create;
                long requested = requested();
                if (requested != 0) {
                    subscriber.onNext(create);
                    if (requested != Long.MAX_VALUE) {
                        produced(1L);
                    }
                    RunnableC0756a runnableC0756a = new RunnableC0756a(this.r, this);
                    if (this.n) {
                        Scheduler.Worker worker = this.p;
                        long j = this.j;
                        schedulePeriodicallyDirect = worker.schedulePeriodically(runnableC0756a, j, j, this.k);
                    } else {
                        Scheduler scheduler = this.l;
                        long j2 = this.j;
                        schedulePeriodicallyDirect = scheduler.schedulePeriodicallyDirect(runnableC0756a, j2, j2, this.k);
                    }
                    if (this.v.replace(schedulePeriodicallyDirect)) {
                        subscription.request(Long.MAX_VALUE);
                        return;
                    }
                    return;
                }
                this.cancelled = true;
                subscription.cancel();
                subscriber.onError(new MissingBackpressureException("Could not deliver initial window due to lack of requests."));
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            requested(j);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements FlowableSubscriber<T>, Subscription {
        public static final Object r = new Object();
        public final long j;
        public final TimeUnit k;
        public final Scheduler l;
        public final int m;
        public Subscription n;
        public UnicastProcessor<T> o;
        public final SequentialDisposable p;
        public volatile boolean q;

        public b(Subscriber<? super Flowable<T>> subscriber, long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
            super(subscriber, new MpscLinkedQueue());
            this.p = new SequentialDisposable();
            this.j = j;
            this.k = timeUnit;
            this.l = scheduler;
            this.m = i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0021, code lost:
            r2.onError(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0025, code lost:
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0028, code lost:
            r10.p.dispose();
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x002d, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0018, code lost:
            r10.o = null;
            r0.clear();
            r0 = r10.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x001f, code lost:
            if (r0 == null) goto L14;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a() {
            /*
                r10 = this;
                io.reactivex.internal.fuseable.SimplePlainQueue<U> r0 = r10.queue
                org.reactivestreams.Subscriber<? super V> r1 = r10.downstream
                io.reactivex.processors.UnicastProcessor<T> r2 = r10.o
                r3 = 1
            L7:
                boolean r4 = r10.q
                boolean r5 = r10.done
                java.lang.Object r6 = r0.poll()
                r7 = 0
                if (r5 == 0) goto L2e
                if (r6 == 0) goto L18
                java.lang.Object r5 = io.reactivex.internal.operators.flowable.FlowableWindowTimed.b.r
                if (r6 != r5) goto L2e
            L18:
                r10.o = r7
                r0.clear()
                java.lang.Throwable r0 = r10.error
                if (r0 == 0) goto L25
                r2.onError(r0)
                goto L28
            L25:
                r2.onComplete()
            L28:
                io.reactivex.internal.disposables.SequentialDisposable r0 = r10.p
                r0.dispose()
                return
            L2e:
                if (r6 != 0) goto L38
                int r3 = -r3
                int r3 = r10.leave(r3)
                if (r3 != 0) goto L7
                return
            L38:
                java.lang.Object r5 = io.reactivex.internal.operators.flowable.FlowableWindowTimed.b.r
                if (r6 != r5) goto L87
                r2.onComplete()
                if (r4 != 0) goto L81
                int r2 = r10.m
                io.reactivex.processors.UnicastProcessor r2 = io.reactivex.processors.UnicastProcessor.create(r2)
                r10.o = r2
                long r4 = r10.requested()
                r8 = 0
                int r6 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1))
                if (r6 == 0) goto L65
                r1.onNext(r2)
                r6 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r4 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                if (r4 == 0) goto L7
                r4 = 1
                r10.produced(r4)
                goto L7
            L65:
                r10.o = r7
                io.reactivex.internal.fuseable.SimplePlainQueue<U> r0 = r10.queue
                r0.clear()
                org.reactivestreams.Subscription r0 = r10.n
                r0.cancel()
                io.reactivex.exceptions.MissingBackpressureException r0 = new io.reactivex.exceptions.MissingBackpressureException
                java.lang.String r2 = "Could not deliver first window due to lack of requests."
                r0.<init>(r2)
                r1.onError(r0)
                io.reactivex.internal.disposables.SequentialDisposable r0 = r10.p
                r0.dispose()
                return
            L81:
                org.reactivestreams.Subscription r4 = r10.n
                r4.cancel()
                goto L7
            L87:
                java.lang.Object r4 = io.reactivex.internal.util.NotificationLite.getValue(r6)
                r2.onNext(r4)
                goto L7
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableWindowTimed.b.a():void");
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            if (enter()) {
                a();
            }
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            if (enter()) {
                a();
            }
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.q) {
                return;
            }
            if (fastEnter()) {
                this.o.onNext(t);
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
            }
            a();
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.n, subscription)) {
                this.n = subscription;
                this.o = UnicastProcessor.create(this.m);
                Subscriber<? super V> subscriber = this.downstream;
                subscriber.onSubscribe(this);
                long requested = requested();
                if (requested != 0) {
                    subscriber.onNext(this.o);
                    if (requested != Long.MAX_VALUE) {
                        produced(1L);
                    }
                    if (this.cancelled) {
                        return;
                    }
                    SequentialDisposable sequentialDisposable = this.p;
                    Scheduler scheduler = this.l;
                    long j = this.j;
                    if (sequentialDisposable.replace(scheduler.schedulePeriodicallyDirect(this, j, j, this.k))) {
                        subscription.request(Long.MAX_VALUE);
                        return;
                    }
                    return;
                }
                this.cancelled = true;
                subscription.cancel();
                subscriber.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            requested(j);
        }

        public void run() {
            if (this.cancelled) {
                this.q = true;
            }
            this.queue.offer(r);
            if (enter()) {
                a();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Subscription, Runnable {
        public final long j;
        public final long k;
        public final TimeUnit l;
        public final Scheduler.Worker m;
        public final int n;
        public final List<UnicastProcessor<T>> o;
        public Subscription p;
        public volatile boolean q;

        /* loaded from: classes12.dex */
        public final class a implements Runnable {
            public final UnicastProcessor<T> h;

            public a(UnicastProcessor<T> unicastProcessor) {
                this.h = unicastProcessor;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.a(this.h);
            }
        }

        /* loaded from: classes12.dex */
        public static final class b<T> {

            /* renamed from: a  reason: collision with root package name */
            public final UnicastProcessor<T> f13908a;
            public final boolean b;

            public b(UnicastProcessor<T> unicastProcessor, boolean z) {
                this.f13908a = unicastProcessor;
                this.b = z;
            }
        }

        public c(Subscriber<? super Flowable<T>> subscriber, long j, long j2, TimeUnit timeUnit, Scheduler.Worker worker, int i) {
            super(subscriber, new MpscLinkedQueue());
            this.j = j;
            this.k = j2;
            this.l = timeUnit;
            this.m = worker;
            this.n = i;
            this.o = new LinkedList();
        }

        public void a(UnicastProcessor<T> unicastProcessor) {
            this.queue.offer(new b(unicastProcessor, false));
            if (enter()) {
                b();
            }
        }

        public void b() {
            SimpleQueue simpleQueue = this.queue;
            Subscriber<? super V> subscriber = this.downstream;
            List<UnicastProcessor<T>> list = this.o;
            int i = 1;
            while (!this.q) {
                boolean z = this.done;
                T t = (T) simpleQueue.poll();
                boolean z2 = t == null;
                boolean z3 = t instanceof b;
                if (z && (z2 || z3)) {
                    simpleQueue.clear();
                    Throwable th = this.error;
                    if (th != null) {
                        for (UnicastProcessor<T> unicastProcessor : list) {
                            unicastProcessor.onError(th);
                        }
                    } else {
                        for (UnicastProcessor<T> unicastProcessor2 : list) {
                            unicastProcessor2.onComplete();
                        }
                    }
                    list.clear();
                    this.m.dispose();
                    return;
                } else if (z2) {
                    i = leave(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (z3) {
                    b bVar = (b) t;
                    if (bVar.b) {
                        if (!this.cancelled) {
                            long requested = requested();
                            if (requested != 0) {
                                UnicastProcessor<T> create = UnicastProcessor.create(this.n);
                                list.add(create);
                                subscriber.onNext(create);
                                if (requested != Long.MAX_VALUE) {
                                    produced(1L);
                                }
                                this.m.schedule(new a(create), this.j, this.l);
                            } else {
                                subscriber.onError(new MissingBackpressureException("Can't emit window due to lack of requests"));
                            }
                        }
                    } else {
                        list.remove(bVar.f13908a);
                        bVar.f13908a.onComplete();
                        if (list.isEmpty() && this.cancelled) {
                            this.q = true;
                        }
                    }
                } else {
                    for (UnicastProcessor<T> unicastProcessor3 : list) {
                        unicastProcessor3.onNext(t);
                    }
                }
            }
            this.p.cancel();
            simpleQueue.clear();
            list.clear();
            this.m.dispose();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            if (enter()) {
                b();
            }
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            if (enter()) {
                b();
            }
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (fastEnter()) {
                for (UnicastProcessor<T> unicastProcessor : this.o) {
                    unicastProcessor.onNext(t);
                }
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(t);
                if (!enter()) {
                    return;
                }
            }
            b();
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.p, subscription)) {
                this.p = subscription;
                this.downstream.onSubscribe(this);
                if (this.cancelled) {
                    return;
                }
                long requested = requested();
                if (requested != 0) {
                    UnicastProcessor<T> create = UnicastProcessor.create(this.n);
                    this.o.add(create);
                    this.downstream.onNext(create);
                    if (requested != Long.MAX_VALUE) {
                        produced(1L);
                    }
                    this.m.schedule(new a(create), this.j, this.l);
                    Scheduler.Worker worker = this.m;
                    long j = this.k;
                    worker.schedulePeriodically(this, j, j, this.l);
                    subscription.request(Long.MAX_VALUE);
                    return;
                }
                subscription.cancel();
                this.downstream.onError(new MissingBackpressureException("Could not emit the first window due to lack of requests"));
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            requested(j);
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = new b(UnicastProcessor.create(this.n), true);
            if (!this.cancelled) {
                this.queue.offer(bVar);
            }
            if (enter()) {
                b();
            }
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

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super Flowable<T>> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        long j = this.i;
        long j2 = this.j;
        if (j == j2) {
            long j3 = this.m;
            if (j3 == Long.MAX_VALUE) {
                this.source.subscribe((FlowableSubscriber) new b(serializedSubscriber, this.i, this.k, this.l, this.n));
                return;
            } else {
                this.source.subscribe((FlowableSubscriber) new a(serializedSubscriber, j, this.k, this.l, this.n, j3, this.o));
                return;
            }
        }
        this.source.subscribe((FlowableSubscriber) new c(serializedSubscriber, j, j2, this.k, this.l.createWorker(), this.n));
    }
}
