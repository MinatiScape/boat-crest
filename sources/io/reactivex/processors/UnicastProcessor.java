package io.reactivex.processors;

import io.reactivex.Flowable;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class UnicastProcessor<T> extends FlowableProcessor<T> {
    public final SpscLinkedArrayQueue<T> i;
    public final AtomicReference<Runnable> j;
    public final boolean k;
    public volatile boolean l;
    public Throwable m;
    public final AtomicReference<Subscriber<? super T>> n;
    public volatile boolean o;
    public final AtomicBoolean p;
    public final BasicIntQueueSubscription<T> q;
    public final AtomicLong r;
    public boolean s;

    /* loaded from: classes12.dex */
    public final class a extends BasicIntQueueSubscription<T> {
        private static final long serialVersionUID = -4896760517184205454L;

        public a() {
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (UnicastProcessor.this.o) {
                return;
            }
            UnicastProcessor.this.o = true;
            UnicastProcessor.this.f();
            UnicastProcessor.this.n.lazySet(null);
            if (UnicastProcessor.this.q.getAndIncrement() == 0) {
                UnicastProcessor.this.n.lazySet(null);
                UnicastProcessor unicastProcessor = UnicastProcessor.this;
                if (unicastProcessor.s) {
                    return;
                }
                unicastProcessor.i.clear();
            }
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public void clear() {
            UnicastProcessor.this.i.clear();
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return UnicastProcessor.this.i.isEmpty();
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() {
            return UnicastProcessor.this.i.poll();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(UnicastProcessor.this.r, j);
                UnicastProcessor.this.g();
            }
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 2) != 0) {
                UnicastProcessor.this.s = true;
                return 2;
            }
            return 0;
        }
    }

    public UnicastProcessor(int i) {
        this(i, null, true);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastProcessor<T> create() {
        return new UnicastProcessor<>(Flowable.bufferSize());
    }

    public boolean e(boolean z, boolean z2, boolean z3, Subscriber<? super T> subscriber, SpscLinkedArrayQueue<T> spscLinkedArrayQueue) {
        if (this.o) {
            spscLinkedArrayQueue.clear();
            this.n.lazySet(null);
            return true;
        } else if (z2) {
            if (z && this.m != null) {
                spscLinkedArrayQueue.clear();
                this.n.lazySet(null);
                subscriber.onError(this.m);
                return true;
            } else if (z3) {
                Throwable th = this.m;
                this.n.lazySet(null);
                if (th != null) {
                    subscriber.onError(th);
                } else {
                    subscriber.onComplete();
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void f() {
        Runnable andSet = this.j.getAndSet(null);
        if (andSet != null) {
            andSet.run();
        }
    }

    public void g() {
        if (this.q.getAndIncrement() != 0) {
            return;
        }
        int i = 1;
        Subscriber<? super T> subscriber = this.n.get();
        while (subscriber == null) {
            i = this.q.addAndGet(-i);
            if (i == 0) {
                return;
            }
            subscriber = this.n.get();
        }
        if (this.s) {
            h(subscriber);
        } else {
            i(subscriber);
        }
    }

    @Override // io.reactivex.processors.FlowableProcessor
    @Nullable
    public Throwable getThrowable() {
        if (this.l) {
            return this.m;
        }
        return null;
    }

    public void h(Subscriber<? super T> subscriber) {
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.i;
        int i = 1;
        boolean z = !this.k;
        while (!this.o) {
            boolean z2 = this.l;
            if (z && z2 && this.m != null) {
                spscLinkedArrayQueue.clear();
                this.n.lazySet(null);
                subscriber.onError(this.m);
                return;
            }
            subscriber.onNext(null);
            if (z2) {
                this.n.lazySet(null);
                Throwable th = this.m;
                if (th != null) {
                    subscriber.onError(th);
                    return;
                } else {
                    subscriber.onComplete();
                    return;
                }
            }
            i = this.q.addAndGet(-i);
            if (i == 0) {
                return;
            }
        }
        this.n.lazySet(null);
    }

    @Override // io.reactivex.processors.FlowableProcessor
    public boolean hasComplete() {
        return this.l && this.m == null;
    }

    @Override // io.reactivex.processors.FlowableProcessor
    public boolean hasSubscribers() {
        return this.n.get() != null;
    }

    @Override // io.reactivex.processors.FlowableProcessor
    public boolean hasThrowable() {
        return this.l && this.m != null;
    }

    public void i(Subscriber<? super T> subscriber) {
        int i;
        long j;
        SpscLinkedArrayQueue<T> spscLinkedArrayQueue = this.i;
        boolean z = true;
        boolean z2 = !this.k;
        int i2 = 1;
        while (true) {
            long j2 = this.r.get();
            long j3 = 0;
            while (true) {
                i = (j2 > j3 ? 1 : (j2 == j3 ? 0 : -1));
                if (i == 0) {
                    j = j3;
                    break;
                }
                boolean z3 = this.l;
                T poll = spscLinkedArrayQueue.poll();
                boolean z4 = poll == null ? z : false;
                j = j3;
                if (e(z2, z3, z4, subscriber, spscLinkedArrayQueue)) {
                    return;
                }
                if (z4) {
                    break;
                }
                subscriber.onNext(poll);
                j3 = 1 + j;
                z = true;
            }
            if (i == 0 && e(z2, this.l, spscLinkedArrayQueue.isEmpty(), subscriber, spscLinkedArrayQueue)) {
                return;
            }
            if (j != 0 && j2 != Long.MAX_VALUE) {
                this.r.addAndGet(-j);
            }
            i2 = this.q.addAndGet(-i2);
            if (i2 == 0) {
                return;
            }
            z = true;
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.l || this.o) {
            return;
        }
        this.l = true;
        f();
        g();
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.l && !this.o) {
            this.m = th;
            this.l = true;
            f();
            g();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.l || this.o) {
            return;
        }
        this.i.offer(t);
        g();
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (!this.l && !this.o) {
            subscription.request(Long.MAX_VALUE);
        } else {
            subscription.cancel();
        }
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        if (!this.p.get() && this.p.compareAndSet(false, true)) {
            subscriber.onSubscribe(this.q);
            this.n.set(subscriber);
            if (this.o) {
                this.n.lazySet(null);
                return;
            } else {
                g();
                return;
            }
        }
        EmptySubscription.error(new IllegalStateException("This processor allows only a single Subscriber"), subscriber);
    }

    public UnicastProcessor(int i, Runnable runnable) {
        this(i, runnable, true);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastProcessor<T> create(int i) {
        return new UnicastProcessor<>(i);
    }

    public UnicastProcessor(int i, Runnable runnable, boolean z) {
        this.i = new SpscLinkedArrayQueue<>(ObjectHelper.verifyPositive(i, "capacityHint"));
        this.j = new AtomicReference<>(runnable);
        this.k = z;
        this.n = new AtomicReference<>();
        this.p = new AtomicBoolean();
        this.q = new a();
        this.r = new AtomicLong();
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastProcessor<T> create(boolean z) {
        return new UnicastProcessor<>(Flowable.bufferSize(), null, z);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastProcessor<T> create(int i, Runnable runnable) {
        ObjectHelper.requireNonNull(runnable, "onTerminate");
        return new UnicastProcessor<>(i, runnable);
    }

    @CheckReturnValue
    @NonNull
    public static <T> UnicastProcessor<T> create(int i, Runnable runnable, boolean z) {
        ObjectHelper.requireNonNull(runnable, "onTerminate");
        return new UnicastProcessor<>(i, runnable, z);
    }
}