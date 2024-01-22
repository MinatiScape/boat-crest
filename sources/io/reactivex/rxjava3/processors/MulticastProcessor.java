package io.reactivex.rxjava3.processors;

import io.reactivex.rxjava3.annotations.BackpressureKind;
import io.reactivex.rxjava3.annotations.BackpressureSupport;
import io.reactivex.rxjava3.annotations.CheckReturnValue;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.SchedulerSupport;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.functions.ObjectHelper;
import io.reactivex.rxjava3.internal.fuseable.QueueSubscription;
import io.reactivex.rxjava3.internal.fuseable.SimpleQueue;
import io.reactivex.rxjava3.internal.queue.SpscArrayQueue;
import io.reactivex.rxjava3.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
@BackpressureSupport(BackpressureKind.FULL)
@SchedulerSupport("none")
/* loaded from: classes12.dex */
public final class MulticastProcessor<T> extends FlowableProcessor<T> {
    public static final a[] t = new a[0];
    public static final a[] u = new a[0];
    public final int l;
    public final int m;
    public final boolean n;
    public volatile SimpleQueue<T> o;
    public volatile boolean p;
    public volatile Throwable q;
    public int r;
    public int s;
    public final AtomicInteger i = new AtomicInteger();
    public final AtomicReference<a<T>[]> k = new AtomicReference<>(t);
    public final AtomicReference<Subscription> j = new AtomicReference<>();

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
            if (SubscriptionHelper.validate(j)) {
                long addCancel = BackpressureHelper.addCancel(this, j);
                if (addCancel == Long.MIN_VALUE || addCancel == Long.MAX_VALUE) {
                    return;
                }
                this.parent.f();
            }
        }
    }

    public MulticastProcessor(int i, boolean z) {
        this.l = i;
        this.m = i - (i >> 2);
        this.n = z;
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
            if (aVarArr == u) {
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
        T t2;
        if (this.i.getAndIncrement() != 0) {
            return;
        }
        AtomicReference<a<T>[]> atomicReference = this.k;
        int i2 = this.r;
        int i3 = this.m;
        int i4 = this.s;
        int i5 = 1;
        while (true) {
            SimpleQueue<T> simpleQueue = this.o;
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
                        if (aVarArr2 == u) {
                            simpleQueue.clear();
                            return;
                        } else if (aVarArr != aVarArr2) {
                            break;
                        } else {
                            boolean z = this.p;
                            try {
                                t2 = simpleQueue.poll();
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                SubscriptionHelper.cancel(this.j);
                                this.q = th;
                                this.p = true;
                                t2 = null;
                                z = true;
                            }
                            boolean z2 = t2 == null;
                            if (z && z2) {
                                Throwable th2 = this.q;
                                if (th2 != null) {
                                    for (a<T> aVar2 : atomicReference.getAndSet(u)) {
                                        aVar2.onError(th2);
                                    }
                                    return;
                                }
                                for (a<T> aVar3 : atomicReference.getAndSet(u)) {
                                    aVar3.onComplete();
                                }
                                return;
                            } else if (z2) {
                                break;
                            } else {
                                for (a<T> aVar4 : aVarArr) {
                                    aVar4.onNext(t2);
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
                        a<T>[] aVarArr4 = u;
                        if (aVarArr3 == aVarArr4) {
                            simpleQueue.clear();
                            return;
                        } else if (aVarArr != aVarArr3) {
                            i2 = i7;
                        } else if (this.p && simpleQueue.isEmpty()) {
                            Throwable th3 = this.q;
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
            this.r = i2;
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
                if (this.n) {
                    if (this.k.compareAndSet(aVarArr, u)) {
                        SubscriptionHelper.cancel(this.j);
                        this.p = true;
                        return;
                    }
                } else if (this.k.compareAndSet(aVarArr, t)) {
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

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    public Throwable getThrowable() {
        if (this.p) {
            return this.q;
        }
        return null;
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    public boolean hasComplete() {
        return this.p && this.q == null;
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    public boolean hasSubscribers() {
        return this.k.get().length != 0;
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @CheckReturnValue
    public boolean hasThrowable() {
        return this.p && this.q != null;
    }

    @CheckReturnValue
    public boolean offer(@NonNull T t2) {
        ExceptionHelper.nullCheck(t2, "offer called with a null value.");
        if (this.p) {
            return false;
        }
        if (this.s == 0) {
            if (this.o.offer(t2)) {
                f();
                return true;
            }
            return false;
        }
        throw new IllegalStateException("offer() should not be called in fusion mode!");
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.p = true;
        f();
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(@NonNull Throwable th) {
        ExceptionHelper.nullCheck(th, "onError called with a null Throwable.");
        if (!this.p) {
            this.q = th;
            this.p = true;
            f();
            return;
        }
        RxJavaPlugins.onError(th);
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(@NonNull T t2) {
        if (this.p) {
            return;
        }
        if (this.s == 0) {
            ExceptionHelper.nullCheck(t2, "onNext called with a null value.");
            if (!this.o.offer(t2)) {
                SubscriptionHelper.cancel(this.j);
                onError(new MissingBackpressureException());
                return;
            }
        }
        f();
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(@NonNull Subscription subscription) {
        if (SubscriptionHelper.setOnce(this.j, subscription)) {
            if (subscription instanceof QueueSubscription) {
                QueueSubscription queueSubscription = (QueueSubscription) subscription;
                int requestFusion = queueSubscription.requestFusion(3);
                if (requestFusion == 1) {
                    this.s = requestFusion;
                    this.o = queueSubscription;
                    this.p = true;
                    f();
                    return;
                } else if (requestFusion == 2) {
                    this.s = requestFusion;
                    this.o = queueSubscription;
                    subscription.request(this.l);
                    return;
                }
            }
            this.o = new SpscArrayQueue(this.l);
            subscription.request(this.l);
        }
    }

    public void start() {
        if (SubscriptionHelper.setOnce(this.j, EmptySubscription.INSTANCE)) {
            this.o = new SpscArrayQueue(this.l);
        }
    }

    public void startUnbounded() {
        if (SubscriptionHelper.setOnce(this.j, EmptySubscription.INSTANCE)) {
            this.o = new SpscLinkedArrayQueue(this.l);
        }
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(@NonNull Subscriber<? super T> subscriber) {
        Throwable th;
        a<T> aVar = new a<>(subscriber, this);
        subscriber.onSubscribe(aVar);
        if (e(aVar)) {
            if (aVar.get() == Long.MIN_VALUE) {
                g(aVar);
            } else {
                f();
            }
        } else if (this.p && (th = this.q) != null) {
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
        ObjectHelper.verifyPositive(i, "bufferSize");
        return new MulticastProcessor<>(i, false);
    }

    @CheckReturnValue
    @NonNull
    public static <T> MulticastProcessor<T> create(int i, boolean z) {
        ObjectHelper.verifyPositive(i, "bufferSize");
        return new MulticastProcessor<>(i, z);
    }
}
