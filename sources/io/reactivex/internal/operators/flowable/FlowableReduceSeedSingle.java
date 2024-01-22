package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
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

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.k.cancel();
            this.k = SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.disposables.Disposable
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
                    this.j = (R) ObjectHelper.requireNonNull(this.i.apply(r, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.k.cancel();
                    onError(th);
                }
            }
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
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

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.j, this.i));
    }
}
