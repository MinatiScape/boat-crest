package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue;
import io.reactivex.rxjava3.internal.queue.MpscLinkedQueue;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.processors.UnicastProcessor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableWindowBoundarySelector<T, B, V> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, Flowable<T>> {
    public final Publisher<B> i;
    public final Function<? super B, ? extends Publisher<V>> j;
    public final int k;

    /* loaded from: classes12.dex */
    public static final class a<T, B, V> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, Runnable {
        private static final long serialVersionUID = 8646217640096099753L;
        public final int bufferSize;
        public final Function<? super B, ? extends Publisher<V>> closingIndicator;
        public final Subscriber<? super Flowable<T>> downstream;
        public long emitted;
        public final Publisher<B> open;
        public volatile boolean openDone;
        public Subscription upstream;
        public volatile boolean upstreamCanceled;
        public volatile boolean upstreamDone;
        public final SimplePlainQueue<Object> queue = new MpscLinkedQueue();
        public final CompositeDisposable resources = new CompositeDisposable();
        public final List<UnicastProcessor<T>> windows = new ArrayList();
        public final AtomicLong windowCount = new AtomicLong(1);
        public final AtomicBoolean downstreamCancelled = new AtomicBoolean();
        public final AtomicThrowable error = new AtomicThrowable();
        public final c<B> startSubscriber = new c<>(this);
        public final AtomicLong requested = new AtomicLong();

        /* renamed from: io.reactivex.rxjava3.internal.operators.flowable.FlowableWindowBoundarySelector$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0821a<T, V> extends Flowable<T> implements FlowableSubscriber<V>, Disposable {
            public final a<T, ?, V> i;
            public final UnicastProcessor<T> j;
            public final AtomicReference<Subscription> k = new AtomicReference<>();
            public final AtomicBoolean l = new AtomicBoolean();

            public C0821a(a<T, ?, V> aVar, UnicastProcessor<T> unicastProcessor) {
                this.i = aVar;
                this.j = unicastProcessor;
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public void dispose() {
                SubscriptionHelper.cancel(this.k);
            }

            public boolean e() {
                return !this.l.get() && this.l.compareAndSet(false, true);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public boolean isDisposed() {
                return this.k.get() == SubscriptionHelper.CANCELLED;
            }

            @Override // org.reactivestreams.Subscriber
            public void onComplete() {
                this.i.close(this);
            }

            @Override // org.reactivestreams.Subscriber
            public void onError(Throwable th) {
                if (isDisposed()) {
                    RxJavaPlugins.onError(th);
                } else {
                    this.i.closeError(th);
                }
            }

            @Override // org.reactivestreams.Subscriber
            public void onNext(V v) {
                if (SubscriptionHelper.cancel(this.k)) {
                    this.i.close(this);
                }
            }

            @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
            public void onSubscribe(Subscription subscription) {
                if (SubscriptionHelper.setOnce(this.k, subscription)) {
                    subscription.request(Long.MAX_VALUE);
                }
            }

            @Override // io.reactivex.rxjava3.core.Flowable
            public void subscribeActual(Subscriber<? super T> subscriber) {
                this.j.subscribe(subscriber);
                this.l.set(true);
            }
        }

        /* loaded from: classes12.dex */
        public static final class b<B> {

            /* renamed from: a  reason: collision with root package name */
            public final B f13967a;

            public b(B b) {
                this.f13967a = b;
            }
        }

        /* loaded from: classes12.dex */
        public static final class c<B> extends AtomicReference<Subscription> implements FlowableSubscriber<B> {
            private static final long serialVersionUID = -3326496781427702834L;
            public final a<?, B, ?> parent;

            public c(a<?, B, ?> aVar) {
                this.parent = aVar;
            }

            public void cancel() {
                SubscriptionHelper.cancel(this);
            }

            @Override // org.reactivestreams.Subscriber
            public void onComplete() {
                this.parent.openComplete();
            }

            @Override // org.reactivestreams.Subscriber
            public void onError(Throwable th) {
                this.parent.openError(th);
            }

            @Override // org.reactivestreams.Subscriber
            public void onNext(B b) {
                this.parent.open(b);
            }

            @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
            public void onSubscribe(Subscription subscription) {
                if (SubscriptionHelper.setOnce(this, subscription)) {
                    subscription.request(Long.MAX_VALUE);
                }
            }
        }

        public a(Subscriber<? super Flowable<T>> subscriber, Publisher<B> publisher, Function<? super B, ? extends Publisher<V>> function, int i) {
            this.downstream = subscriber;
            this.open = publisher;
            this.closingIndicator = function;
            this.bufferSize = i;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.downstreamCancelled.compareAndSet(false, true)) {
                if (this.windowCount.decrementAndGet() == 0) {
                    this.upstream.cancel();
                    this.startSubscriber.cancel();
                    this.resources.dispose();
                    this.error.tryTerminateAndReport();
                    this.upstreamCanceled = true;
                    drain();
                    return;
                }
                this.startSubscriber.cancel();
            }
        }

        public void close(C0821a<T, V> c0821a) {
            this.queue.offer(c0821a);
            drain();
        }

        public void closeError(Throwable th) {
            this.upstream.cancel();
            this.startSubscriber.cancel();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super Flowable<T>> subscriber = this.downstream;
            SimplePlainQueue<Object> simplePlainQueue = this.queue;
            List<UnicastProcessor<T>> list = this.windows;
            int i = 1;
            while (true) {
                if (this.upstreamCanceled) {
                    simplePlainQueue.clear();
                    list.clear();
                } else {
                    boolean z = this.upstreamDone;
                    Object poll = simplePlainQueue.poll();
                    boolean z2 = poll == null;
                    if (z && (z2 || this.error.get() != null)) {
                        terminateDownstream(subscriber);
                        this.upstreamCanceled = true;
                    } else if (!z2) {
                        if (poll instanceof b) {
                            if (!this.downstreamCancelled.get()) {
                                long j = this.emitted;
                                if (this.requested.get() != j) {
                                    this.emitted = j + 1;
                                    try {
                                        Publisher<V> apply = this.closingIndicator.apply(((b) poll).f13967a);
                                        Objects.requireNonNull(apply, "The closingIndicator returned a null Publisher");
                                        Publisher<V> publisher = apply;
                                        this.windowCount.getAndIncrement();
                                        UnicastProcessor<T> create = UnicastProcessor.create(this.bufferSize, this);
                                        C0821a c0821a = new C0821a(this, create);
                                        subscriber.onNext(c0821a);
                                        if (c0821a.e()) {
                                            create.onComplete();
                                        } else {
                                            list.add(create);
                                            this.resources.add(c0821a);
                                            publisher.subscribe(c0821a);
                                        }
                                    } catch (Throwable th) {
                                        Exceptions.throwIfFatal(th);
                                        this.upstream.cancel();
                                        this.startSubscriber.cancel();
                                        this.resources.dispose();
                                        Exceptions.throwIfFatal(th);
                                        this.error.tryAddThrowableOrReport(th);
                                        this.upstreamDone = true;
                                    }
                                } else {
                                    this.upstream.cancel();
                                    this.startSubscriber.cancel();
                                    this.resources.dispose();
                                    this.error.tryAddThrowableOrReport(new MissingBackpressureException(FlowableWindowTimed.e(j)));
                                    this.upstreamDone = true;
                                }
                            }
                        } else if (poll instanceof C0821a) {
                            UnicastProcessor<T> unicastProcessor = ((C0821a) poll).j;
                            list.remove(unicastProcessor);
                            this.resources.delete((Disposable) poll);
                            unicastProcessor.onComplete();
                        } else {
                            for (UnicastProcessor<T> unicastProcessor2 : list) {
                                unicastProcessor2.onNext(poll);
                            }
                        }
                    } else if (this.openDone && list.size() == 0) {
                        this.upstream.cancel();
                        this.startSubscriber.cancel();
                        this.resources.dispose();
                        terminateDownstream(subscriber);
                        this.upstreamCanceled = true;
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.startSubscriber.cancel();
            this.resources.dispose();
            this.upstreamDone = true;
            drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.startSubscriber.cancel();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                this.open.subscribe(this.startSubscriber);
                subscription.request(Long.MAX_VALUE);
            }
        }

        public void open(B b2) {
            this.queue.offer(new b(b2));
            drain();
        }

        public void openComplete() {
            this.openDone = true;
            drain();
        }

        public void openError(Throwable th) {
            this.upstream.cancel();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windowCount.decrementAndGet() == 0) {
                this.upstream.cancel();
                this.startSubscriber.cancel();
                this.resources.dispose();
                this.error.tryTerminateAndReport();
                this.upstreamCanceled = true;
                drain();
            }
        }

        public void terminateDownstream(Subscriber<?> subscriber) {
            Throwable terminate = this.error.terminate();
            if (terminate == null) {
                for (UnicastProcessor<T> unicastProcessor : this.windows) {
                    unicastProcessor.onComplete();
                }
                subscriber.onComplete();
            } else if (terminate != ExceptionHelper.TERMINATED) {
                for (UnicastProcessor<T> unicastProcessor2 : this.windows) {
                    unicastProcessor2.onError(terminate);
                }
                subscriber.onError(terminate);
            }
        }
    }

    public FlowableWindowBoundarySelector(Flowable<T> flowable, Publisher<B> publisher, Function<? super B, ? extends Publisher<V>> function, int i) {
        super(flowable);
        this.i = publisher;
        this.j = function;
        this.k = i;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super Flowable<T>> subscriber) {
        this.source.subscribe((FlowableSubscriber) new a(subscriber, this.i, this.j, this.k));
    }
}
