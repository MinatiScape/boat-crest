package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.fuseable.FuseToFlowable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collector;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableCollectWithCollectorSingle<T, A, R> extends Single<R> implements FuseToFlowable<R> {
    public final Flowable<T> h;
    public final Collector<T, A, R> i;

    /* loaded from: classes12.dex */
    public static final class a<T, A, R> implements FlowableSubscriber<T>, Disposable {
        public final SingleObserver<? super R> h;
        public final BiConsumer<A, T> i;
        public final Function<A, R> j;
        public Subscription k;
        public boolean l;
        public A m;

        public a(SingleObserver<? super R> singleObserver, A a2, BiConsumer<A, T> biConsumer, Function<A, R> function) {
            this.h = singleObserver;
            this.m = a2;
            this.i = biConsumer;
            this.j = function;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.k.cancel();
            this.k = SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.k == SubscriptionHelper.CANCELLED;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.k = SubscriptionHelper.CANCELLED;
            A a2 = this.m;
            this.m = null;
            try {
                R apply = this.j.apply(a2);
                Objects.requireNonNull(apply, "The finisher returned a null value");
                this.h.onSuccess(apply);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.l) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.l = true;
            this.k = SubscriptionHelper.CANCELLED;
            this.m = null;
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            try {
                this.i.accept(this.m, t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.k.cancel();
                onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(@NonNull Subscription subscription) {
            if (SubscriptionHelper.validate(this.k, subscription)) {
                this.k = subscription;
                this.h.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }
    }

    public FlowableCollectWithCollectorSingle(Flowable<T> flowable, Collector<T, A, R> collector) {
        this.h = flowable;
        this.i = collector;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.FuseToFlowable
    public Flowable<R> fuseToFlowable() {
        return new FlowableCollectWithCollector(this.h, this.i);
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(@NonNull SingleObserver<? super R> singleObserver) {
        try {
            this.h.subscribe((FlowableSubscriber) new a(singleObserver, this.i.supplier().get(), this.i.accumulator(), this.i.finisher()));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, singleObserver);
        }
    }
}
