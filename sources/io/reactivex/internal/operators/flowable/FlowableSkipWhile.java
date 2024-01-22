package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableSkipWhile<T> extends io.reactivex.internal.operators.flowable.a<T, T> {
    public final Predicate<? super T> i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements FlowableSubscriber<T>, Subscription {
        public final Subscriber<? super T> h;
        public final Predicate<? super T> i;
        public Subscription j;
        public boolean k;

        public a(Subscriber<? super T> subscriber, Predicate<? super T> predicate) {
            this.h = subscriber;
            this.i = predicate;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.j.cancel();
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
            if (this.k) {
                this.h.onNext(t);
                return;
            }
            try {
                if (this.i.test(t)) {
                    this.j.request(1L);
                    return;
                }
                this.k = true;
                this.h.onNext(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.j.cancel();
                this.h.onError(th);
            }
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.j, subscription)) {
                this.j = subscription;
                this.h.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.j.request(j);
        }
    }

    public FlowableSkipWhile(Flowable<T> flowable, Predicate<? super T> predicate) {
        super(flowable);
        this.i = predicate;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new a(subscriber, this.i));
    }
}
