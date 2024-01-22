package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.schedulers.Timed;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableTimeInterval<T> extends io.reactivex.internal.operators.flowable.a<T, Timed<T>> {
    public final Scheduler i;
    public final TimeUnit j;

    /* loaded from: classes12.dex */
    public static final class a<T> implements FlowableSubscriber<T>, Subscription {
        public final Subscriber<? super Timed<T>> h;
        public final TimeUnit i;
        public final Scheduler j;
        public Subscription k;
        public long l;

        public a(Subscriber<? super Timed<T>> subscriber, TimeUnit timeUnit, Scheduler scheduler) {
            this.h = subscriber;
            this.j = scheduler;
            this.i = timeUnit;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.k.cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.h.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            long now = this.j.now(this.i);
            long j = this.l;
            this.l = now;
            this.h.onNext(new Timed(t, now - j, this.i));
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.k, subscription)) {
                this.l = this.j.now(this.i);
                this.k = subscription;
                this.h.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.k.request(j);
        }
    }

    public FlowableTimeInterval(Flowable<T> flowable, TimeUnit timeUnit, Scheduler scheduler) {
        super(flowable);
        this.i = scheduler;
        this.j = timeUnit;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super Timed<T>> subscriber) {
        this.source.subscribe((FlowableSubscriber) new a(subscriber, this.j, this.i));
    }
}
