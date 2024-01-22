package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableReduceSeedSingle<T, R> extends Single<R> {
    public final Publisher<T> h;
    public final R i;
    public final BiFunction<R, ? super T, R> j;

    /* loaded from: classes12.dex */
    public static final class a<T, R> implements FlowableSubscriber<T>, Disposable {
        public final SingleObserver<? super R> h;
        public final BiFunction<R, ? super T, R> i;
        public R j;
        public Subscription k;

        public a(SingleObserver<? super R> singleObserver, BiFunction<R, ? super T, R> biFunction, R r) {
            this.h = singleObserver;
            this.j = r;
            this.i = biFunction;
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
            R r = this.j;
            if (r != null) {
                this.j = null;
                this.k = SubscriptionHelper.CANCELLED;
                this.h.onSuccess(r);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.j != null) {
                this.j = null;
                this.k = SubscriptionHelper.CANCELLED;
                this.h.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            R r = this.j;
            if (r != null) {
                try {
                    R apply = this.i.apply(r, t);
                    Objects.requireNonNull(apply, "The reducer returned a null value");
                    this.j = apply;
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.k.cancel();
                    onError(th);
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.k, subscription)) {
                this.k = subscription;
                this.h.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }
    }

    public FlowableReduceSeedSingle(Publisher<T> publisher, R r, BiFunction<R, ? super T, R> biFunction) {
        this.h = publisher;
        this.i = r;
        this.j = biFunction;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.j, this.i));
    }
}
