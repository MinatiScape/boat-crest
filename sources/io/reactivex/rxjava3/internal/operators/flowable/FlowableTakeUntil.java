package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.HalfSerializer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableTakeUntil<T, U> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, T> {
    public final Publisher<? extends U> i;

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -4945480365982832967L;
        public final Subscriber<? super T> downstream;
        public final AtomicLong requested = new AtomicLong();
        public final AtomicReference<Subscription> upstream = new AtomicReference<>();
        public final a<T>.C0819a other = new C0819a();
        public final AtomicThrowable error = new AtomicThrowable();

        /* renamed from: io.reactivex.rxjava3.internal.operators.flowable.FlowableTakeUntil$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class C0819a extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
            private static final long serialVersionUID = -3592821756711087922L;

            public C0819a() {
            }

            @Override // org.reactivestreams.Subscriber
            public void onComplete() {
                SubscriptionHelper.cancel(a.this.upstream);
                a aVar = a.this;
                HalfSerializer.onComplete(aVar.downstream, aVar, aVar.error);
            }

            @Override // org.reactivestreams.Subscriber
            public void onError(Throwable th) {
                SubscriptionHelper.cancel(a.this.upstream);
                a aVar = a.this;
                HalfSerializer.onError(aVar.downstream, th, aVar, aVar.error);
            }

            @Override // org.reactivestreams.Subscriber
            public void onNext(Object obj) {
                SubscriptionHelper.cancel(this);
                onComplete();
            }

            @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
            public void onSubscribe(Subscription subscription) {
                SubscriptionHelper.setOnce(this, subscription, Long.MAX_VALUE);
            }
        }

        public a(Subscriber<? super T> subscriber) {
            this.downstream = subscriber;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            SubscriptionHelper.cancel(this.other);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            HalfSerializer.onComplete(this.downstream, this, this.error);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            SubscriptionHelper.cancel(this.other);
            HalfSerializer.onError(this.downstream, th, this, this.error);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            HalfSerializer.onNext(this.downstream, t, this, this.error);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.deferredSetOnce(this.upstream, this.requested, subscription);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.upstream, this.requested, j);
        }
    }

    public FlowableTakeUntil(Flowable<T> flowable, Publisher<? extends U> publisher) {
        super(flowable);
        this.i = publisher;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        a aVar = new a(subscriber);
        subscriber.onSubscribe(aVar);
        this.i.subscribe(aVar.other);
        this.source.subscribe((FlowableSubscriber) aVar);
    }
}