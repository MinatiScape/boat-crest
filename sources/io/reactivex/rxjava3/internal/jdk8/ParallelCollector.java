package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class ParallelCollector<T, A, R> extends Flowable<R> {
    public final ParallelFlowable<? extends T> i;
    public final Collector<T, A, R> j;

    /* loaded from: classes12.dex */
    public static final class a<T, A, R> extends AtomicReference<Subscription> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -7954444275102466525L;
        public final BiConsumer<A, T> accumulator;
        public final BinaryOperator<A> combiner;
        public A container;
        public boolean done;
        public final b<T, A, R> parent;

        public a(b<T, A, R> bVar, A a2, BiConsumer<A, T> biConsumer, BinaryOperator<A> binaryOperator) {
            this.parent = bVar;
            this.accumulator = biConsumer;
            this.combiner = binaryOperator;
            this.container = a2;
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            A a2 = this.container;
            this.container = null;
            this.done = true;
            this.parent.innerComplete(a2, this.combiner);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.container = null;
            this.done = true;
            this.parent.innerError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                this.accumulator.accept(this.container, t);
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
    public static final class b<T, A, R> extends DeferredScalarSubscription<R> {
        private static final long serialVersionUID = -5370107872170712765L;
        public final AtomicReference<c<A>> current;
        public final AtomicThrowable error;
        public final Function<A, R> finisher;
        public final AtomicInteger remaining;
        public final a<T, A, R>[] subscribers;

        public b(Subscriber<? super R> subscriber, int i, Collector<T, A, R> collector) {
            super(subscriber);
            this.current = new AtomicReference<>();
            this.remaining = new AtomicInteger();
            this.error = new AtomicThrowable();
            this.finisher = collector.finisher();
            a<T, A, R>[] aVarArr = new a[i];
            for (int i2 = 0; i2 < i; i2++) {
                aVarArr[i2] = new a<>(this, collector.supplier().get(), collector.accumulator(), collector.combiner());
            }
            this.subscribers = aVarArr;
            this.remaining.lazySet(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public c<A> addValue(A a2) {
            c<A> cVar;
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
                cVar.first = a2;
            } else {
                cVar.second = a2;
            }
            if (cVar.releaseSlot()) {
                this.current.compareAndSet(cVar, null);
                return cVar;
            }
            return null;
        }

        @Override // io.reactivex.rxjava3.internal.subscriptions.DeferredScalarSubscription, org.reactivestreams.Subscription
        public void cancel() {
            for (a<T, A, R> aVar : this.subscribers) {
                aVar.cancel();
            }
        }

        public void innerComplete(A a2, BinaryOperator<A> binaryOperator) {
            while (true) {
                c<A> addValue = addValue(a2);
                if (addValue == null) {
                    break;
                }
                try {
                    a2 = (A) binaryOperator.apply(addValue.first, addValue.second);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    innerError(th);
                    return;
                }
            }
            if (this.remaining.decrementAndGet() == 0) {
                c<A> cVar = this.current.get();
                this.current.lazySet(null);
                try {
                    R apply = this.finisher.apply(cVar.first);
                    Objects.requireNonNull(apply, "The finisher returned a null value");
                    complete(apply);
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    innerError(th2);
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

    public ParallelCollector(ParallelFlowable<? extends T> parallelFlowable, Collector<T, A, R> collector) {
        this.i = parallelFlowable;
        this.j = collector;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        try {
            b bVar = new b(subscriber, this.i.parallelism(), this.j);
            subscriber.onSubscribe(bVar);
            this.i.subscribe(bVar.subscribers);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, subscriber);
        }
    }
}
