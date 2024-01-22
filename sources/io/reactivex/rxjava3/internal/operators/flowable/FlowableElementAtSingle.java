package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.fuseable.FuseToFlowable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableElementAtSingle<T> extends Single<T> implements FuseToFlowable<T> {
    public final Flowable<T> h;
    public final long i;
    public final T j;

    /* loaded from: classes12.dex */
    public static final class a<T> implements FlowableSubscriber<T>, Disposable {
        public final SingleObserver<? super T> h;
        public final long i;
        public final T j;
        public Subscription k;
        public long l;
        public boolean m;

        public a(SingleObserver<? super T> singleObserver, long j, T t) {
            this.h = singleObserver;
            this.i = j;
            this.j = t;
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
            this.k = SubscriptionHelper.CANCELLED;
            if (this.m) {
                return;
            }
            this.m = true;
            T t = this.j;
            if (t != null) {
                this.h.onSuccess(t);
            } else {
                this.h.onError(new NoSuchElementException());
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.m) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.m = true;
            this.k = SubscriptionHelper.CANCELLED;
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.m) {
                return;
            }
            long j = this.l;
            if (j == this.i) {
                this.m = true;
                this.k.cancel();
                this.k = SubscriptionHelper.CANCELLED;
                this.h.onSuccess(t);
                return;
            }
            this.l = j + 1;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.k, subscription)) {
                this.k = subscription;
                this.h.onSubscribe(this);
                subscription.request(this.i + 1);
            }
        }
    }

    public FlowableElementAtSingle(Flowable<T> flowable, long j, T t) {
        this.h = flowable;
        this.i = j;
        this.j = t;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.FuseToFlowable
    public Flowable<T> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableElementAt(this.h, this.i, this.j, true));
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe((FlowableSubscriber) new a(singleObserver, this.i, this.j));
    }
}
