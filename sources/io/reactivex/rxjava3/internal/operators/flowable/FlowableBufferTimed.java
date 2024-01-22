package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.queue.MpscLinkedQueue;
import io.reactivex.rxjava3.internal.subscribers.QueueDrainSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.QueueDrainHelper;
import io.reactivex.rxjava3.subscribers.SerializedSubscriber;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableBufferTimed<T, U extends Collection<? super T>> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, U> {
    public final long i;
    public final long j;
    public final TimeUnit k;
    public final Scheduler l;
    public final Supplier<U> m;
    public final int n;
    public final boolean o;

    /* loaded from: classes12.dex */
    public static final class a<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Subscription, Runnable, Disposable {
        public final Supplier<U> j;
        public final long k;
        public final TimeUnit l;
        public final int m;
        public final boolean n;
        public final Scheduler.Worker o;
        public U p;
        public Disposable q;
        public Subscription r;
        public long s;
        public long t;

        public a(Subscriber<? super U> subscriber, Supplier<U> supplier, long j, TimeUnit timeUnit, int i, boolean z, Scheduler.Worker worker) {
            super(subscriber, new MpscLinkedQueue());
            this.j = supplier;
            this.k = j;
            this.l = timeUnit;
            this.m = i;
            this.n = z;
            this.o = worker;
        }

        @Override // io.reactivex.rxjava3.internal.subscribers.QueueDrainSubscriber, io.reactivex.rxjava3.internal.util.QueueDrain
        /* renamed from: a */
        public boolean accept(Subscriber<? super U> subscriber, U u) {
            subscriber.onNext(u);
            return true;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            synchronized (this) {
                this.p = null;
            }
            this.r.cancel();
            this.o.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.o.isDisposed();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            U u;
            synchronized (this) {
                u = this.p;
                this.p = null;
            }
            if (u != null) {
                this.queue.offer(u);
                this.done = true;
                if (enter()) {
                    QueueDrainHelper.drainMaxLoop(this.queue, this.downstream, false, this, this);
                }
                this.o.dispose();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            synchronized (this) {
                this.p = null;
            }
            this.downstream.onError(th);
            this.o.dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            synchronized (this) {
                U u = this.p;
                if (u == null) {
                    return;
                }
                u.add(t);
                if (u.size() < this.m) {
                    return;
                }
                this.p = null;
                this.s++;
                if (this.n) {
                    this.q.dispose();
                }
                fastPathOrderedEmitMax(u, false, this);
                try {
                    U u2 = this.j.get();
                    Objects.requireNonNull(u2, "The supplied buffer is null");
                    U u3 = u2;
                    synchronized (this) {
                        this.p = u3;
                        this.t++;
                    }
                    if (this.n) {
                        Scheduler.Worker worker = this.o;
                        long j = this.k;
                        this.q = worker.schedulePeriodically(this, j, j, this.l);
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancel();
                    this.downstream.onError(th);
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.r, subscription)) {
                this.r = subscription;
                try {
                    U u = this.j.get();
                    Objects.requireNonNull(u, "The supplied buffer is null");
                    this.p = u;
                    this.downstream.onSubscribe(this);
                    Scheduler.Worker worker = this.o;
                    long j = this.k;
                    this.q = worker.schedulePeriodically(this, j, j, this.l);
                    subscription.request(Long.MAX_VALUE);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.o.dispose();
                    subscription.cancel();
                    EmptySubscription.error(th, this.downstream);
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            requested(j);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                U u = this.j.get();
                Objects.requireNonNull(u, "The supplied buffer is null");
                U u2 = u;
                synchronized (this) {
                    U u3 = this.p;
                    if (u3 != null && this.s == this.t) {
                        this.p = u2;
                        fastPathOrderedEmitMax(u3, false, this);
                    }
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                this.downstream.onError(th);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Subscription, Runnable, Disposable {
        public final Supplier<U> j;
        public final long k;
        public final TimeUnit l;
        public final Scheduler m;
        public Subscription n;
        public U o;
        public final AtomicReference<Disposable> p;

        public b(Subscriber<? super U> subscriber, Supplier<U> supplier, long j, TimeUnit timeUnit, Scheduler scheduler) {
            super(subscriber, new MpscLinkedQueue());
            this.p = new AtomicReference<>();
            this.j = supplier;
            this.k = j;
            this.l = timeUnit;
            this.m = scheduler;
        }

        @Override // io.reactivex.rxjava3.internal.subscribers.QueueDrainSubscriber, io.reactivex.rxjava3.internal.util.QueueDrain
        /* renamed from: a */
        public boolean accept(Subscriber<? super U> subscriber, U u) {
            this.downstream.onNext(u);
            return true;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            this.n.cancel();
            DisposableHelper.dispose(this.p);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            cancel();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.p.get() == DisposableHelper.DISPOSED;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            DisposableHelper.dispose(this.p);
            synchronized (this) {
                U u = this.o;
                if (u == null) {
                    return;
                }
                this.o = null;
                this.queue.offer(u);
                this.done = true;
                if (enter()) {
                    QueueDrainHelper.drainMaxLoop(this.queue, this.downstream, false, null, this);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.p);
            synchronized (this) {
                this.o = null;
            }
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            synchronized (this) {
                U u = this.o;
                if (u != null) {
                    u.add(t);
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.n, subscription)) {
                this.n = subscription;
                try {
                    U u = this.j.get();
                    Objects.requireNonNull(u, "The supplied buffer is null");
                    this.o = u;
                    this.downstream.onSubscribe(this);
                    if (this.cancelled) {
                        return;
                    }
                    subscription.request(Long.MAX_VALUE);
                    Scheduler scheduler = this.m;
                    long j = this.k;
                    Disposable schedulePeriodicallyDirect = scheduler.schedulePeriodicallyDirect(this, j, j, this.l);
                    if (this.p.compareAndSet(null, schedulePeriodicallyDirect)) {
                        return;
                    }
                    schedulePeriodicallyDirect.dispose();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancel();
                    EmptySubscription.error(th, this.downstream);
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            requested(j);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                U u = this.j.get();
                Objects.requireNonNull(u, "The supplied buffer is null");
                U u2 = u;
                synchronized (this) {
                    U u3 = this.o;
                    if (u3 == null) {
                        return;
                    }
                    this.o = u2;
                    fastPathEmitMax(u3, false, this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                this.downstream.onError(th);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Subscription, Runnable {
        public final Supplier<U> j;
        public final long k;
        public final long l;
        public final TimeUnit m;
        public final Scheduler.Worker n;
        public final List<U> o;
        public Subscription p;

        /* loaded from: classes12.dex */
        public final class a implements Runnable {
            public final U h;

            public a(U u) {
                this.h = u;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (c.this) {
                    c.this.o.remove(this.h);
                }
                c cVar = c.this;
                cVar.fastPathOrderedEmitMax(this.h, false, cVar.n);
            }
        }

        public c(Subscriber<? super U> subscriber, Supplier<U> supplier, long j, long j2, TimeUnit timeUnit, Scheduler.Worker worker) {
            super(subscriber, new MpscLinkedQueue());
            this.j = supplier;
            this.k = j;
            this.l = j2;
            this.m = timeUnit;
            this.n = worker;
            this.o = new LinkedList();
        }

        @Override // io.reactivex.rxjava3.internal.subscribers.QueueDrainSubscriber, io.reactivex.rxjava3.internal.util.QueueDrain
        /* renamed from: a */
        public boolean accept(Subscriber<? super U> subscriber, U u) {
            subscriber.onNext(u);
            return true;
        }

        public void c() {
            synchronized (this) {
                this.o.clear();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            this.p.cancel();
            this.n.dispose();
            c();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            ArrayList<Collection> arrayList;
            synchronized (this) {
                arrayList = new ArrayList(this.o);
                this.o.clear();
            }
            for (Collection collection : arrayList) {
                this.queue.offer(collection);
            }
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainMaxLoop(this.queue, this.downstream, false, this.n, this);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.done = true;
            this.n.dispose();
            c();
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            synchronized (this) {
                for (U u : this.o) {
                    u.add(t);
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.p, subscription)) {
                this.p = subscription;
                try {
                    U u = this.j.get();
                    Objects.requireNonNull(u, "The supplied buffer is null");
                    U u2 = u;
                    this.o.add(u2);
                    this.downstream.onSubscribe(this);
                    subscription.request(Long.MAX_VALUE);
                    Scheduler.Worker worker = this.n;
                    long j = this.l;
                    worker.schedulePeriodically(this, j, j, this.m);
                    this.n.schedule(new a(u2), this.k, this.m);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.n.dispose();
                    subscription.cancel();
                    EmptySubscription.error(th, this.downstream);
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            requested(j);
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.cancelled) {
                return;
            }
            try {
                U u = this.j.get();
                Objects.requireNonNull(u, "The supplied buffer is null");
                U u2 = u;
                synchronized (this) {
                    if (this.cancelled) {
                        return;
                    }
                    this.o.add(u2);
                    this.n.schedule(new a(u2), this.k, this.m);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                this.downstream.onError(th);
            }
        }
    }

    public FlowableBufferTimed(Flowable<T> flowable, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, Supplier<U> supplier, int i, boolean z) {
        super(flowable);
        this.i = j;
        this.j = j2;
        this.k = timeUnit;
        this.l = scheduler;
        this.m = supplier;
        this.n = i;
        this.o = z;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super U> subscriber) {
        if (this.i == this.j && this.n == Integer.MAX_VALUE) {
            this.source.subscribe((FlowableSubscriber) new b(new SerializedSubscriber(subscriber), this.m, this.i, this.k, this.l));
            return;
        }
        Scheduler.Worker createWorker = this.l.createWorker();
        if (this.i == this.j) {
            this.source.subscribe((FlowableSubscriber) new a(new SerializedSubscriber(subscriber), this.m, this.i, this.k, this.n, this.o, createWorker));
        } else {
            this.source.subscribe((FlowableSubscriber) new c(new SerializedSubscriber(subscriber), this.m, this.i, this.j, this.k, createWorker));
        }
    }
}
