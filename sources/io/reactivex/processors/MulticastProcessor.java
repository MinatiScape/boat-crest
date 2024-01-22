package io.reactivex.processors;

import io.reactivex.Flowable;
import io.reactivex.annotations.BackpressureKind;
import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
@SchedulerSupport("none")
@BackpressureSupport(BackpressureKind.FULL)
/* loaded from: classes12.dex */
public final class MulticastProcessor<T> extends FlowableProcessor<T> {
    public static final a[] u = new a[0];
    public static final a[] v = new a[0];
    public final AtomicInteger i;
    public final AtomicReference<Subscription> j;
    public final AtomicReference<a<T>[]> k;
    public final AtomicBoolean l;
    public final int m;
    public final int n;
    public final boolean o;
    public volatile SimpleQueue<T> p;
    public volatile boolean q;
    public volatile Throwable r;
    public int s;
    public int t;

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = -363282618957264509L;
        public final Subscriber<? super T> downstream;
        public long emitted;
        public final MulticastProcessor<T> parent;

        public a(Subscriber<? super T> subscriber, MulticastProcessor<T> multicastProcessor) {
            this.downstream = subscriber;
            this.parent = multicastProcessor;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.g(this);
            }
        }

        public void onComplete() {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onComplete();
            }
        }

        public void onError(Throwable th) {
            if (get() != Long.MIN_VALUE) {
                this.downstream.onError(th);
            }
        }

        public void onNext(T t) {
            if (get() != Long.MIN_VALUE) {
                this.emitted++;
                this.downstream.onNext(t);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            long j2;
            long j3;
            if (SubscriptionHelper.validate(j)) {
                do {
                    j2 = get();
                    if (j2 == Long.MIN_VALUE) {
                        return;
                    }
                    if (j2 == Long.MAX_VALUE) {
                        return;
                    }
                    j3 = j2 + j;
                } while (!compareAndSet(j2, j3 >= 0 ? j3 : Long.MAX_VALUE));
                this.parent.f();
            }
        }
    }

    public MulticastProcessor(int i, boolean z) {
        ObjectHelper.verifyPositive(i, "bufferSize");
        this.m = i;
        this.n = i - (i >> 2);
        this.i = new AtomicInteger();
        this.k = new AtomicReference<>(u);
        this.j = new AtomicReference<>();
        this.o = z;
        this.l = new AtomicBoolean();
    }

    @CheckReturnValue
    @NonNull
    public static <T> MulticastProcessor<T> create() {
        return new MulticastProcessor<>(Flowable.bufferSize(), false);
    }

    public boolean e(a<T> aVar) {
        a<T>[] aVarArr;
        a<T>[] aVarArr2;
        do {
            aVarArr = this.k.get();
            if (aVarArr == v) {
                return false;
            }
            int length = aVarArr.length;
            aVarArr2 = new a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.k.compareAndSet(aVarArr, aVarArr2));
        return true;
    }

    public void f() {
        int i;
        T t;
        if (this.i.getAndIncrement() != 0) {
            return;
        }
        AtomicReference<a<T>[]> atomicReference = this.k;
        int i2 = this.s;
        int i3 = this.n;
        int i4 = this.t;
        int i5 = 1;
        while (true) {
            SimpleQueue<T> simpleQueue = this.p;
            if (simpleQueue != null) {
                a<T>[] aVarArr = atomicReference.get();
                if (aVarArr.length != 0) {
                    int length = aVarArr.length;
                    long j = -1;
                    long j2 = -1;
                    int i6 = 0;
                    while (i6 < length) {
                        a<T> aVar = aVarArr[i6];
                        long j3 = aVar.get();
                        if (j3 >= 0) {
                            if (j2 == j) {
                                j2 = j3 - aVar.emitted;
                            } else {
                                j2 = Math.min(j2, j3 - aVar.emitted);
                            }
                        }
                        i6++;
                        j = -1;
                    }
                    int i7 = i2;
                    while (true) {
                        i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1));
                        if (i <= 0) {
                            break;
                        }
                        a<T>[] aVarArr2 = atomicReference.get();
                        if (aVarArr2 == v) {
                            simpleQueue.clear();
                            return;
                        } else if (aVarArr != aVarArr2) {
                            break;
                        } else {
                            boolean z = this.q;
                            try {
                                t = simpleQueue.poll();
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                SubscriptionHelper.cancel(this.j);
                                this.r = th;
                                this.q = true;
                                t = null;
                                z = true;
                            }
                            boolean z2 = t == null;
                            if (z && z2) {
                                Throwable th2 = this.r;
                                if (th2 != null) {
                                    for (a<T> aVar2 : atomicReference.getAndSet(v)) {
                                        aVar2.onError(th2);
                                    }
                                    return;
                                }
                                for (a<T> aVar3 : atomicReference.getAndSet(v)) {
                                    aVar3.onComplete();
                                }
                                return;
                            } else if (z2) {
                                break;
                            } else {
                                for (a<T> aVar4 : aVarArr) {
                                    aVar4.onNext(t);
                                }
                                j2--;
                                if (i4 != 1 && (i7 = i7 + 1) == i3) {
                                    this.j.get().request(i3);
                                    i7 = 0;
                                }
                            }
                        }
                    }
                    if (i == 0) {
                        a<T>[] aVarArr3 = atomicReference.get();
                        a<T>[] aVarArr4 = v;
                        if (aVarArr3 == aVarArr4) {
                            simpleQueue.clear();
                            return;
                        } else if (aVarArr != aVarArr3) {
                            i2 = i7;
                        } else if (this.q && simpleQueue.isEmpty()) {
                            Throwable th3 = this.r;
                            if (th3 != null) {
                                for (a<T> aVar5 : atomicReference.getAndSet(aVarArr4)) {
                                    aVar5.onError(th3);
                                }
                                return;
                            }
                            for (a<T> aVar6 : atomicReference.getAndSet(aVarArr4)) {
                                aVar6.onComplete();
                            }
                            return;
                        }
                    }
                    i2 = i7;
                }
            }
            this.s = i2;
            i5 = this.i.addAndGet(-i5);
            if (i5 == 0) {
                return;
            }
        }
    }

    public void g(a<T> aVar) {
        while (true) {
            a<T>[] aVarArr = this.k.get();
            int length = aVarArr.length;
            if (length == 0) {
                return;
            }
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (aVarArr[i2] == aVar) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                if (this.o) {
                    if (this.k.compareAndSet(aVarArr, v)) {
                        SubscriptionHelper.cancel(this.j);
                        this.l.set(true);
                        return;
                    }
                } else if (this.k.compareAndSet(aVarArr, u)) {
                    return;
                }
            } else {
                a<T>[] aVarArr2 = new a[length - 1];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, i);
                System.arraycopy(aVarArr, i + 1, aVarArr2, i, (length - i) - 1);
                if (this.k.compareAndSet(aVarArr, aVarArr2)) {
                    return;
                }
            }
        }
    }

    @Override // io.reactivex.processors.FlowableProcessor
    public Throwable getThrowable() {
        if (this.l.get()) {
            return this.r;
        }
        return null;
    }

    @Override // io.reactivex.processors.FlowableProcessor
    public boolean hasComplete() {
        return this.l.get() && this.r == null;
    }

    @Override // io.reactivex.processors.FlowableProcessor
    public boolean hasSubscribers() {
        return this.k.get().length != 0;
    }

    @Override // io.reactivex.processors.FlowableProcessor
    public boolean hasThrowable() {
        return this.l.get() && this.r != null;
    }

    public boolean offer(T t) {
        if (this.l.get()) {
            return false;
        }
        ObjectHelper.requireNonNull(t, "offer called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.t == 0 && this.p.offer(t)) {
            f();
            return true;
        }
        return false;
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.l.compareAndSet(false, true)) {
            this.q = true;
            f();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        ObjectHelper.requireNonNull(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.l.compareAndSet(false, true)) {
            this.r = th;
            this.q = true;
            f();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        if (this.l.get()) {
            return;
        }
        if (this.t == 0) {
            ObjectHelper.requireNonNull(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
            if (!this.p.offer(t)) {
                SubscriptionHelper.cancel(this.j);
                onError(new MissingBackpressureException());
                return;
            }
        }
        f();
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.setOnce(this.j, subscription)) {
            if (subscription instanceof QueueSubscription) {
                QueueSubscription queueSubscription = (QueueSubscription) subscription;
                int requestFusion = queueSubscription.requestFusion(3);
                if (requestFusion == 1) {
                    this.t = requestFusion;
                    this.p = queueSubscription;
                    this.q = true;
                    f();
                    return;
                } else if (requestFusion == 2) {
                    this.t = requestFusion;
                    this.p = queueSubscription;
                    subscription.request(this.m);
                    return;
                }
            }
            this.p = new SpscArrayQueue(this.m);
            subscription.request(this.m);
        }
    }

    public void start() {
        if (SubscriptionHelper.setOnce(this.j, EmptySubscription.INSTANCE)) {
            this.p = new SpscArrayQueue(this.m);
        }
    }

    public void startUnbounded() {
        if (SubscriptionHelper.setOnce(this.j, EmptySubscription.INSTANCE)) {
            this.p = new SpscLinkedArrayQueue(this.m);
        }
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        Throwable th;
        a<T> aVar = new a<>(subscriber, this);
        subscriber.onSubscribe(aVar);
        if (e(aVar)) {
            if (aVar.get() == Long.MIN_VALUE) {
                g(aVar);
            } else {
                f();
            }
        } else if ((this.l.get() || !this.o) && (th = this.r) != null) {
            subscriber.onError(th);
        } else {
            subscriber.onComplete();
        }
    }

    @CheckReturnValue
    @NonNull
    public static <T> MulticastProcessor<T> create(boolean z) {
        return new MulticastProcessor<>(Flowable.bufferSize(), z);
    }

    @CheckReturnValue
    @NonNull
    public static <T> MulticastProcessor<T> create(int i) {
        return new MulticastProcessor<>(i, false);
    }

    @CheckReturnValue
    @NonNull
    public static <T> MulticastProcessor<T> create(int i, boolean z) {
        return new MulticastProcessor<>(i, z);
    }
}
