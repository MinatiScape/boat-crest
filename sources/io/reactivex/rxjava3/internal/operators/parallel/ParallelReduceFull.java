package io.reactivex.rxjava3.internal.operators.parallel;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class ParallelReduceFull<T> extends Flowable<T> {
    public final ParallelFlowable<? extends T> i;
    public final BiFunction<T, T, T> j;

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -7954444275102466525L;
        public boolean done;
        public final b<T> parent;
        public final BiFunction<T, T, T> reducer;
        public T value;

        public a(b<T> bVar, BiFunction<T, T, T> biFunction) {
            this.parent = bVar;
            this.reducer = biFunction;
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.parent.innerComplete(this.value);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.done = true;
            this.parent.innerError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            T t2 = this.value;
            if (t2 == null) {
                this.value = t;
                return;
            }
            try {
                T apply = this.reducer.apply(t2, t);
                Objects.requireNonNull(apply, "The reducer returned a null value");
                this.value = apply;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                get().cancel();
                onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.setOnce(this, subscription, Long.MAX_VALUE);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends DeferredScalarSubscription<T> {
        private static final long serialVersionUID = -5370107872170712765L;
        public final AtomicReference<c<T>> current;
        public final AtomicThrowable error;
        public final BiFunction<T, T, T> reducer;
        public final AtomicInteger remaining;
        public final a<T>[] subscribers;

        public b(Subscriber<? super T> subscriber, int i, BiFunction<T, T, T> biFunction) {
            super(subscriber);
            this.current = new AtomicReference<>();
            this.remaining = new AtomicInteger();
            this.error = new AtomicThrowable();
            a<T>[] aVarArr = new a[i];
            for (int i2 = 0; i2 < i; i2++) {
                aVarArr[i2] = new a<>(this, biFunction);
            }
            this.subscribers = aVarArr;
            this.reducer = biFunction;
            this.remaining.lazySet(i);
        }

        public c<T> addValue(T t) {
            c<T> cVar;
            int tryAcquireSlot;
            while (true) {
                cVar = this.current.get();
                if (cVar == null) {
                    cVar = new c<>();
                    if (!this.current.compareAndSet(null, cVar)) {
                        continue;
                    }
                }
                tryAcquireSlot = cVar.tryAcquireSlot();
                if (tryAcquireSlot >= 0) {
                    break;
                }
                this.current.compareAndSet(cVar, null);
            }
            if (tryAcquireSlot == 0) {
                cVar.first = t;
            } else {
                cVar.second = t;
            }
            if (cVar.releaseSlot()) {
                this.current.compareAndSet(cVar, null);
                return cVar;
            }
            return null;
        }

        @Override // io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription, org.reactivestreams.Subscription
        public void cancel() {
            for (a<T> aVar : this.subscribers) {
                aVar.cancel();
            }
        }

        public void innerComplete(T t) {
            if (t != null) {
                while (true) {
                    c<T> addValue = addValue(t);
                    if (addValue == null) {
                        break;
                    }
                    try {
                        t = this.reducer.apply(addValue.first, addValue.second);
                        Objects.requireNonNull(t, "The reducer returned a null value");
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        innerError(th);
                        return;
                    }
                }
            }
            if (this.remaining.decrementAndGet() == 0) {
                c<T> cVar = this.current.get();
                this.current.lazySet(null);
                if (cVar != null) {
                    complete(cVar.first);
                } else {
                    this.downstream.onComplete();
                }
            }
        }

        public void innerError(Throwable th) {
            if (this.error.compareAndSet(null, th)) {
                cancel();
                this.downstream.onError(th);
            } else if (th != this.error.get()) {
                RxJavaPlugins.onError(th);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T> extends AtomicInteger {
        private static final long serialVersionUID = 473971317683868662L;
        public T first;
        public final AtomicInteger releaseIndex = new AtomicInteger();
        public T second;

        public boolean releaseSlot() {
            return this.releaseIndex.incrementAndGet() == 2;
        }

        public int tryAcquireSlot() {
            int i;
            do {
                i = get();
                if (i >= 2) {
                    return -1;
                }
            } while (!compareAndSet(i, i + 1));
            return i;
        }
    }

    public ParallelReduceFull(ParallelFlowable<? extends T> parallelFlowable, BiFunction<T, T, T> biFunction) {
        this.i = parallelFlowable;
        this.j = biFunction;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        b bVar = new b(subscriber, this.i.parallelism(), this.j);
        subscriber.onSubscribe(bVar);
        this.i.subscribe(bVar.subscribers);
    }
}
