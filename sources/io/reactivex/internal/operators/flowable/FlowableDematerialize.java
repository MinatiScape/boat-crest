package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Notification;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableDematerialize<T, R> extends io.reactivex.internal.operators.flowable.a<T, R> {
    public final Function<? super T, ? extends Notification<R>> i;

    /* loaded from: classes12.dex */
    public static final class a<T, R> implements FlowableSubscriber<T>, Subscription {
        public final Subscriber<? super R> h;
        public final Function<? super T, ? extends Notification<R>> i;
        public boolean j;
        public Subscription k;

        public a(Subscriber<? super R> subscriber, Function<? super T, ? extends Notification<R>> function) {
            this.h = subscriber;
            this.i = function;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.k.cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.j) {
                return;
            }
            this.j = true;
            this.h.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.j) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.j = true;
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.j) {
                if (t instanceof Notification) {
                    Notification notification = (Notification) t;
                    if (notification.isOnError()) {
                        RxJavaPlugins.onError(notification.getError());
                        return;
                    }
                    return;
                }
                return;
            }
            try {
                Notification notification2 = (Notification) ObjectHelper.requireNonNull(this.i.apply(t), "The selector returned a null Notification");
                if (notification2.isOnError()) {
                    this.k.cancel();
                    onError(notification2.getError());
                } else if (notification2.isOnComplete()) {
                    this.k.cancel();
                    onComplete();
                } else {
                    this.h.onNext((Object) notification2.getValue());
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.k.cancel();
                onError(th);
            }
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.k, subscription)) {
                this.k = subscription;
                this.h.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.k.request(j);
        }
    }

    public FlowableDematerialize(Flowable<T> flowable, Function<? super T, ? extends Notification<R>> function) {
        super(flowable);
        this.i = function;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        this.source.subscribe((FlowableSubscriber) new a(subscriber, this.i));
    }
}
