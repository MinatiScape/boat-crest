package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;
import io.reactivex.rxjava3.subscribers.SerializedSubscriber;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableDebounce<T, U> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, T> {
    public final Function<? super T, ? extends Publisher<U>> i;

    /* loaded from: classes12.dex */
    public static final class a<T, U> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 6725975399620862591L;
        public final Function<? super T, ? extends Publisher<U>> debounceSelector;
        public final AtomicReference<Disposable> debouncer = new AtomicReference<>();
        public boolean done;
        public final Subscriber<? super T> downstream;
        public volatile long index;
        public Subscription upstream;

        /* renamed from: io.reactivex.rxjava3.internal.operators.flowable.FlowableDebounce$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0807a<T, U> extends DisposableSubscriber<U> {
            public final a<T, U> i;
            public final long j;
            public final T k;
            public boolean l;
            public final AtomicBoolean m = new AtomicBoolean();

            public C0807a(a<T, U> aVar, long j, T t) {
                this.i = aVar;
                this.j = j;
                this.k = t;
            }

            public void a() {
                if (this.m.compareAndSet(false, true)) {
                    this.i.emit(this.j, this.k);
                }
            }

            @Override // org.reactivestreams.Subscriber
            public void onComplete() {
                if (this.l) {
                    return;
                }
                this.l = true;
                a();
            }

            @Override // org.reactivestreams.Subscriber
            public void onError(Throwable th) {
                if (this.l) {
                    RxJavaPlugins.onError(th);
                    return;
                }
                this.l = true;
                this.i.onError(th);
            }

            @Override // org.reactivestreams.Subscriber
            public void onNext(U u) {
                if (this.l) {
                    return;
                }
                this.l = true;
                cancel();
                a();
            }
        }

        public a(Subscriber<? super T> subscriber, Function<? super T, ? extends Publisher<U>> function) {
            this.downstream = subscriber;
            this.debounceSelector = function;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
            DisposableHelper.dispose(this.debouncer);
        }

        public void emit(long j, T t) {
            if (j == this.index) {
                if (get() != 0) {
                    this.downstream.onNext(t);
                    BackpressureHelper.produced(this, 1L);
                    return;
                }
                cancel();
                this.downstream.onError(new MissingBackpressureException("Could not deliver value due to lack of requests"));
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            Disposable disposable = this.debouncer.get();
            if (DisposableHelper.isDisposed(disposable)) {
                return;
            }
            C0807a c0807a = (C0807a) disposable;
            if (c0807a != null) {
                c0807a.a();
            }
            DisposableHelper.dispose(this.debouncer);
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.debouncer);
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            long j = this.index + 1;
            this.index = j;
            Disposable disposable = this.debouncer.get();
            if (disposable != null) {
                disposable.dispose();
            }
            try {
                Publisher<U> apply = this.debounceSelector.apply(t);
                Objects.requireNonNull(apply, "The publisher supplied is null");
                Publisher<U> publisher = apply;
                C0807a c0807a = new C0807a(this, j, t);
                if (this.debouncer.compareAndSet(disposable, c0807a)) {
                    publisher.subscribe(c0807a);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                cancel();
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this, j);
            }
        }
    }

    public FlowableDebounce(Flowable<T> flowable, Function<? super T, ? extends Publisher<U>> function) {
        super(flowable);
        this.i = function;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new a(new SerializedSubscriber(subscriber), this.i));
    }
}
