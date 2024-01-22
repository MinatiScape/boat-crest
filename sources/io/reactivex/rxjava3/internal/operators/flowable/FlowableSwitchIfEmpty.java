package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionArbiter;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableSwitchIfEmpty<T> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, T> {
    public final Publisher<? extends T> i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements FlowableSubscriber<T> {
        public final Subscriber<? super T> h;
        public final Publisher<? extends T> i;
        public boolean k = true;
        public final SubscriptionArbiter j = new SubscriptionArbiter(false);

        public a(Subscriber<? super T> subscriber, Publisher<? extends T> publisher) {
            this.h = subscriber;
            this.i = publisher;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.k) {
                this.k = false;
                this.i.subscribe(this);
                return;
            }
            this.h.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.k) {
                this.k = false;
            }
            this.h.onNext(t);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            this.j.setSubscription(subscription);
        }
    }

    public FlowableSwitchIfEmpty(Flowable<T> flowable, Publisher<? extends T> publisher) {
        super(flowable);
        this.i = publisher;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        a aVar = new a(subscriber, this.i);
        subscriber.onSubscribe(aVar.j);
        this.source.subscribe((FlowableSubscriber) aVar);
    }
}
