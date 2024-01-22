package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BooleanSupplier;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.QueueDrainHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableBuffer<T, C extends Collection<? super T>> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, C> {
    public final int i;
    public final int j;
    public final Supplier<C> k;

    /* loaded from: classes12.dex */
    public static final class a<T, C extends Collection<? super T>> implements FlowableSubscriber<T>, Subscription {
        public final Subscriber<? super C> h;
        public final Supplier<C> i;
        public final int j;
        public C k;
        public Subscription l;
        public boolean m;
        public int n;

        public a(Subscriber<? super C> subscriber, int i, Supplier<C> supplier) {
            this.h = subscriber;
            this.j = i;
            this.i = supplier;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.l.cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.m) {
                return;
            }
            this.m = true;
            C c = this.k;
            this.k = null;
            if (c != null) {
                this.h.onNext(c);
            }
            this.h.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.m) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.k = null;
            this.m = true;
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.m) {
                return;
            }
            C c = this.k;
            if (c == null) {
                try {
                    C c2 = this.i.get();
                    Objects.requireNonNull(c2, "The bufferSupplier returned a null buffer");
                    c = c2;
                    this.k = c;
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancel();
                    onError(th);
                    return;
                }
            }
            c.add(t);
            int i = this.n + 1;
            if (i == this.j) {
                this.n = 0;
                this.k = null;
                this.h.onNext(c);
                return;
            }
            this.n = i;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.l, subscription)) {
                this.l = subscription;
                this.h.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                this.l.request(BackpressureHelper.multiplyCap(j, this.j));
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, C extends Collection<? super T>> extends AtomicLong implements FlowableSubscriber<T>, Subscription, BooleanSupplier {
        private static final long serialVersionUID = -7370244972039324525L;
        public final Supplier<C> bufferSupplier;
        public volatile boolean cancelled;
        public boolean done;
        public final Subscriber<? super C> downstream;
        public int index;
        public long produced;
        public final int size;
        public final int skip;
        public Subscription upstream;
        public final AtomicBoolean once = new AtomicBoolean();
        public final ArrayDeque<C> buffers = new ArrayDeque<>();

        public b(Subscriber<? super C> subscriber, int i, int i2, Supplier<C> supplier) {
            this.downstream = subscriber;
            this.size = i;
            this.skip = i2;
            this.bufferSupplier = supplier;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.cancelled = true;
            this.upstream.cancel();
        }

        @Override // io.reactivex.rxjava3.functions.BooleanSupplier
        public boolean getAsBoolean() {
            return this.cancelled;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            long j = this.produced;
            if (j != 0) {
                BackpressureHelper.produced(this, j);
            }
            QueueDrainHelper.postComplete(this.downstream, this.buffers, this, this);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.buffers.clear();
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            ArrayDeque<C> arrayDeque = this.buffers;
            int i = this.index;
            int i2 = i + 1;
            if (i == 0) {
                try {
                    C c = this.bufferSupplier.get();
                    Objects.requireNonNull(c, "The bufferSupplier returned a null buffer");
                    arrayDeque.offer(c);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancel();
                    onError(th);
                    return;
                }
            }
            C peek = arrayDeque.peek();
            if (peek.size() + 1 == this.size) {
                arrayDeque.poll();
                peek.add(t);
                this.produced++;
                this.downstream.onNext(peek);
            }
            Iterator<C> it = arrayDeque.iterator();
            while (it.hasNext()) {
                it.next().add(t);
            }
            if (i2 == this.skip) {
                i2 = 0;
            }
            this.index = i2;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (!SubscriptionHelper.validate(j) || QueueDrainHelper.postCompleteRequest(j, this.downstream, this.buffers, this, this)) {
                return;
            }
            if (!this.once.get() && this.once.compareAndSet(false, true)) {
                this.upstream.request(BackpressureHelper.addCap(this.size, BackpressureHelper.multiplyCap(this.skip, j - 1)));
                return;
            }
            this.upstream.request(BackpressureHelper.multiplyCap(this.skip, j));
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T, C extends Collection<? super T>> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -5616169793639412593L;
        public C buffer;
        public final Supplier<C> bufferSupplier;
        public boolean done;
        public final Subscriber<? super C> downstream;
        public int index;
        public final int size;
        public final int skip;
        public Subscription upstream;

        public c(Subscriber<? super C> subscriber, int i, int i2, Supplier<C> supplier) {
            this.downstream = subscriber;
            this.size = i;
            this.skip = i2;
            this.bufferSupplier = supplier;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            C c = this.buffer;
            this.buffer = null;
            if (c != null) {
                this.downstream.onNext(c);
            }
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.buffer = null;
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            C c = this.buffer;
            int i = this.index;
            int i2 = i + 1;
            if (i == 0) {
                try {
                    C c2 = this.bufferSupplier.get();
                    Objects.requireNonNull(c2, "The bufferSupplier returned a null buffer");
                    c = c2;
                    this.buffer = c;
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    cancel();
                    onError(th);
                    return;
                }
            }
            if (c != null) {
                c.add(t);
                if (c.size() == this.size) {
                    this.buffer = null;
                    this.downstream.onNext(c);
                }
            }
            if (i2 == this.skip) {
                i2 = 0;
            }
            this.index = i2;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                if (get() == 0 && compareAndSet(0, 1)) {
                    this.upstream.request(BackpressureHelper.addCap(BackpressureHelper.multiplyCap(j, this.size), BackpressureHelper.multiplyCap(this.skip - this.size, j - 1)));
                    return;
                }
                this.upstream.request(BackpressureHelper.multiplyCap(this.skip, j));
            }
        }
    }

    public FlowableBuffer(Flowable<T> flowable, int i, int i2, Supplier<C> supplier) {
        super(flowable);
        this.i = i;
        this.j = i2;
        this.k = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super C> subscriber) {
        int i = this.i;
        int i2 = this.j;
        if (i == i2) {
            this.source.subscribe((FlowableSubscriber) new a(subscriber, i, this.k));
        } else if (i2 > i) {
            this.source.subscribe((FlowableSubscriber) new c(subscriber, this.i, this.j, this.k));
        } else {
            this.source.subscribe((FlowableSubscriber) new b(subscriber, this.i, this.j, this.k));
        }
    }
}
