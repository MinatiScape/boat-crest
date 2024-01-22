package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.fuseable.FuseToFlowable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableElementAtMaybe<T> extends Maybe<T> implements FuseToFlowable<T> {
    public final Flowable<T> h;
    public final long i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements FlowableSubscriber<T>, Disposable {
        public final MaybeObserver<? super T> h;
        public final long i;
        public Subscription j;
        public long k;
        public boolean l;

        public a(MaybeObserver<? super T> maybeObserver, long j) {
            this.h = maybeObserver;
            this.i = j;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.j.cancel();
            this.j = SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.j == SubscriptionHelper.CANCELLED;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.j = SubscriptionHelper.CANCELLED;
            if (this.l) {
                return;
            }
            this.l = true;
            this.h.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.l) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.l = true;
            this.j = SubscriptionHelper.CANCELLED;
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            long j = this.k;
            if (j == this.i) {
                this.l = true;
                this.j.cancel();
                this.j = SubscriptionHelper.CANCELLED;
                this.h.onSuccess(t);
                return;
            }
            this.k = j + 1;
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.j, subscription)) {
                this.j = subscription;
                this.h.onSubscribe(this);
                subscription.request(this.i + 1);
            }
        }
    }

    public FlowableElementAtMaybe(Flowable<T> flowable, long j) {
        this.h = flowable;
        this.i = j;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.FuseToFlowable
    public Flowable<T> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableElementAt(this.h, this.i, null, false));
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.h.subscribe((FlowableSubscriber) new a(maybeObserver, this.i));
    }
}
