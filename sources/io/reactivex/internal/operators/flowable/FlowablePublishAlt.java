package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowablePublishAlt<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T>, ResettableConnectable {
    public final Publisher<T> i;
    public final int j;
    public final AtomicReference<b<T>> k = new AtomicReference<>();

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 2845000326761540265L;
        public final Subscriber<? super T> downstream;
        public long emitted;
        public final b<T> parent;

        public a(Subscriber<? super T> subscriber, b<T> bVar) {
            this.downstream = subscriber;
            this.parent = bVar;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.drain();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            BackpressureHelper.addCancel(this, j);
            this.parent.drain();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        public static final a[] EMPTY = new a[0];
        public static final a[] TERMINATED = new a[0];
        private static final long serialVersionUID = -1672047311619175801L;
        public final int bufferSize;
        public int consumed;
        public final AtomicReference<b<T>> current;
        public volatile boolean done;
        public Throwable error;
        public volatile SimpleQueue<T> queue;
        public int sourceMode;
        public final AtomicReference<Subscription> upstream = new AtomicReference<>();
        public final AtomicBoolean connect = new AtomicBoolean();
        public final AtomicReference<a<T>[]> subscribers = new AtomicReference<>(EMPTY);

        public b(AtomicReference<b<T>> atomicReference, int i) {
            this.current = atomicReference;
            this.bufferSize = i;
        }

        public boolean add(a<T> aVar) {
            a<T>[] aVarArr;
            a<T>[] aVarArr2;
            do {
                aVarArr = this.subscribers.get();
                if (aVarArr == TERMINATED) {
                    return false;
                }
                int length = aVarArr.length;
                aVarArr2 = new a[length + 1];
                System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
                aVarArr2[length] = aVar;
            } while (!this.subscribers.compareAndSet(aVarArr, aVarArr2));
            return true;
        }

        public boolean checkTerminated(boolean z, boolean z2) {
            a<T>[] andSet;
            if (z && z2) {
                Throwable th = this.error;
                if (th != null) {
                    signalError(th);
                    return true;
                }
                for (a<T> aVar : this.subscribers.getAndSet(TERMINATED)) {
                    if (!aVar.isCancelled()) {
                        aVar.downstream.onComplete();
                    }
                }
                return true;
            }
            return false;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.subscribers.getAndSet(TERMINATED);
            this.current.compareAndSet(this, null);
            SubscriptionHelper.cancel(this.upstream);
        }

        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            SimpleQueue<T> simpleQueue = this.queue;
            int i = this.consumed;
            int i2 = this.bufferSize;
            int i3 = i2 - (i2 >> 2);
            boolean z = this.sourceMode != 1;
            int i4 = 1;
            SimpleQueue<T> simpleQueue2 = simpleQueue;
            int i5 = i;
            while (true) {
                if (simpleQueue2 != null) {
                    long j = Long.MAX_VALUE;
                    a<T>[] aVarArr = this.subscribers.get();
                    boolean z2 = false;
                    for (a<T> aVar : aVarArr) {
                        long j2 = aVar.get();
                        if (j2 != Long.MIN_VALUE) {
                            j = Math.min(j2 - aVar.emitted, j);
                            z2 = true;
                        }
                    }
                    if (!z2) {
                        j = 0;
                    }
                    for (long j3 = 0; j != j3; j3 = 0) {
                        boolean z3 = this.done;
                        try {
                            T poll = simpleQueue2.poll();
                            boolean z4 = poll == null;
                            if (checkTerminated(z3, z4)) {
                                return;
                            }
                            if (z4) {
                                break;
                            }
                            for (a<T> aVar2 : aVarArr) {
                                if (!aVar2.isCancelled()) {
                                    aVar2.downstream.onNext(poll);
                                    aVar2.emitted++;
                                }
                            }
                            if (z && (i5 = i5 + 1) == i3) {
                                this.upstream.get().request(i3);
                                i5 = 0;
                            }
                            j--;
                            if (aVarArr != this.subscribers.get()) {
                                break;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.upstream.get().cancel();
                            simpleQueue2.clear();
                            this.done = true;
                            signalError(th);
                            return;
                        }
                    }
                    if (checkTerminated(this.done, simpleQueue2.isEmpty())) {
                        return;
                    }
                }
                this.consumed = i5;
                i4 = addAndGet(-i4);
                if (i4 == 0) {
                    return;
                }
                if (simpleQueue2 == null) {
                    simpleQueue2 = this.queue;
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode == 0 && !this.queue.offer(t)) {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            } else {
                drain();
            }
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
                if (subscription instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) subscription;
                    int requestFusion = queueSubscription.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        subscription.request(this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                subscription.request(this.bufferSize);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void remove(a<T> aVar) {
            a<T>[] aVarArr;
            a[] aVarArr2;
            do {
                aVarArr = this.subscribers.get();
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
                    aVarArr2 = EMPTY;
                } else {
                    a[] aVarArr3 = new a[length - 1];
                    System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                    System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                    aVarArr2 = aVarArr3;
                }
            } while (!this.subscribers.compareAndSet(aVarArr, aVarArr2));
        }

        public void signalError(Throwable th) {
            a<T>[] andSet;
            for (a<T> aVar : this.subscribers.getAndSet(TERMINATED)) {
                if (!aVar.isCancelled()) {
                    aVar.downstream.onError(th);
                }
            }
        }
    }

    public FlowablePublishAlt(Publisher<T> publisher, int i) {
        this.i = publisher;
        this.j = i;
    }

    @Override // io.reactivex.flowables.ConnectableFlowable
    public void connect(Consumer<? super Disposable> consumer) {
        b<T> bVar;
        while (true) {
            bVar = this.k.get();
            if (bVar != null && !bVar.isDisposed()) {
                break;
            }
            b<T> bVar2 = new b<>(this.k, this.j);
            if (this.k.compareAndSet(bVar, bVar2)) {
                bVar = bVar2;
                break;
            }
        }
        boolean z = true;
        if (bVar.connect.get() || !bVar.connect.compareAndSet(false, true)) {
            z = false;
        }
        try {
            consumer.accept(bVar);
            if (z) {
                this.i.subscribe(bVar);
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    public int publishBufferSize() {
        return this.j;
    }

    @Override // io.reactivex.internal.disposables.ResettableConnectable
    public void resetIf(Disposable disposable) {
        this.k.compareAndSet((b) disposable, null);
    }

    @Override // io.reactivex.internal.fuseable.HasUpstreamPublisher
    public Publisher<T> source() {
        return this.i;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        b<T> bVar;
        while (true) {
            bVar = this.k.get();
            if (bVar != null) {
                break;
            }
            b<T> bVar2 = new b<>(this.k, this.j);
            if (this.k.compareAndSet(bVar, bVar2)) {
                bVar = bVar2;
                break;
            }
        }
        a<T> aVar = new a<>(subscriber, bVar);
        subscriber.onSubscribe(aVar);
        if (bVar.add(aVar)) {
            if (aVar.isCancelled()) {
                bVar.remove(aVar);
                return;
            } else {
                bVar.drain();
                return;
            }
        }
        Throwable th = bVar.error;
        if (th != null) {
            subscriber.onError(th);
        } else {
            subscriber.onComplete();
        }
    }
}
