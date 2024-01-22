package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableZipIterable<T, U, V> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, V> {
    public final Iterable<U> i;
    public final BiFunction<? super T, ? super U, ? extends V> j;

    /* loaded from: classes12.dex */
    public static final class a<T, U, V> implements FlowableSubscriber<T>, Subscription {
        public final Subscriber<? super V> h;
        public final Iterator<U> i;
        public final BiFunction<? super T, ? super U, ? extends V> j;
        public Subscription k;
        public boolean l;

        public a(Subscriber<? super V> subscriber, Iterator<U> it, BiFunction<? super T, ? super U, ? extends V> biFunction) {
            this.h = subscriber;
            this.i = it;
            this.j = biFunction;
        }

        public void a(Throwable th) {
            Exceptions.throwIfFatal(th);
            this.l = true;
            this.k.cancel();
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.k.cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
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
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            try {
                U next = this.i.next();
                Objects.requireNonNull(next, "The iterator returned a null value");
                try {
                    V apply = this.j.apply(t, next);
                    Objects.requireNonNull(apply, "The zipper function returned a null value");
                    this.h.onNext(apply);
                    try {
                        if (this.i.hasNext()) {
                            return;
                        }
                        this.l = true;
                        this.k.cancel();
                        this.h.onComplete();
                    } catch (Throwable th) {
                        a(th);
                    }
                } catch (Throwable th2) {
                    a(th2);
                }
            } catch (Throwable th3) {
                a(th3);
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
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

    public FlowableZipIterable(Flowable<T> flowable, Iterable<U> iterable, BiFunction<? super T, ? super U, ? extends V> biFunction) {
        super(flowable);
        this.i = iterable;
        this.j = biFunction;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super V> subscriber) {
        try {
            Iterator<U> it = this.i.iterator();
            Objects.requireNonNull(it, "The iterator returned by other is null");
            Iterator<U> it2 = it;
            try {
                if (!it2.hasNext()) {
                    EmptySubscription.complete(subscriber);
                } else {
                    this.source.subscribe((FlowableSubscriber) new a(subscriber, it2, this.j));
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptySubscription.error(th, subscriber);
            }
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            EmptySubscription.error(th2, subscriber);
        }
    }
}
