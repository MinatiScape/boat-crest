package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableAmb<T> extends Flowable<T> {
    public final Publisher<? extends T>[] i;
    public final Iterable<? extends Publisher<? extends T>> j;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Subscription {
        public final Subscriber<? super T> h;
        public final b<T>[] i;
        public final AtomicInteger j = new AtomicInteger();

        public a(Subscriber<? super T> subscriber, int i) {
            this.h = subscriber;
            this.i = new b[i];
        }

        public void a(Publisher<? extends T>[] publisherArr) {
            b<T>[] bVarArr = this.i;
            int length = bVarArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                bVarArr[i] = new b<>(this, i2, this.h);
                i = i2;
            }
            this.j.lazySet(0);
            this.h.onSubscribe(this);
            for (int i3 = 0; i3 < length && this.j.get() == 0; i3++) {
                publisherArr[i3].subscribe(bVarArr[i3]);
            }
        }

        public boolean b(int i) {
            int i2 = 0;
            if (this.j.get() == 0 && this.j.compareAndSet(0, i)) {
                b<T>[] bVarArr = this.i;
                int length = bVarArr.length;
                while (i2 < length) {
                    int i3 = i2 + 1;
                    if (i3 != i) {
                        bVarArr[i2].cancel();
                    }
                    i2 = i3;
                }
                return true;
            }
            return false;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.j.get() != -1) {
                this.j.lazySet(-1);
                for (b<T> bVar : this.i) {
                    bVar.cancel();
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                int i = this.j.get();
                if (i > 0) {
                    this.i[i - 1].request(j);
                } else if (i == 0) {
                    for (b<T> bVar : this.i) {
                        bVar.request(j);
                    }
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -1185974347409665484L;
        public final Subscriber<? super T> downstream;
        public final int index;
        public final AtomicLong missedRequested = new AtomicLong();
        public final a<T> parent;
        public boolean won;

        public b(a<T> aVar, int i, Subscriber<? super T> subscriber) {
            this.parent = aVar;
            this.index = i;
            this.downstream = subscriber;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.won) {
                this.downstream.onComplete();
            } else if (this.parent.b(this.index)) {
                this.won = true;
                this.downstream.onComplete();
            } else {
                get().cancel();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.won) {
                this.downstream.onError(th);
            } else if (this.parent.b(this.index)) {
                this.won = true;
                this.downstream.onError(th);
            } else {
                get().cancel();
                RxJavaPlugins.onError(th);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.won) {
                this.downstream.onNext(t);
            } else if (this.parent.b(this.index)) {
                this.won = true;
                this.downstream.onNext(t);
            } else {
                get().cancel();
            }
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            SubscriptionHelper.deferredSetOnce(this, this.missedRequested, subscription);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            SubscriptionHelper.deferredRequest(this, this.missedRequested, j);
        }
    }

    public FlowableAmb(Publisher<? extends T>[] publisherArr, Iterable<? extends Publisher<? extends T>> iterable) {
        this.i = publisherArr;
        this.j = iterable;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        int length;
        Publisher<? extends T>[] publisherArr = this.i;
        if (publisherArr == null) {
            publisherArr = new Publisher[8];
            try {
                length = 0;
                for (Publisher<? extends T> publisher : this.j) {
                    if (publisher == null) {
                        EmptySubscription.error(new NullPointerException("One of the sources is null"), subscriber);
                        return;
                    }
                    if (length == publisherArr.length) {
                        Publisher<? extends T>[] publisherArr2 = new Publisher[(length >> 2) + length];
                        System.arraycopy(publisherArr, 0, publisherArr2, 0, length);
                        publisherArr = publisherArr2;
                    }
                    int i = length + 1;
                    publisherArr[length] = publisher;
                    length = i;
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptySubscription.error(th, subscriber);
                return;
            }
        } else {
            length = publisherArr.length;
        }
        if (length == 0) {
            EmptySubscription.complete(subscriber);
        } else if (length == 1) {
            publisherArr[0].subscribe(subscriber);
        } else {
            new a(subscriber, length).a(publisherArr);
        }
    }
}
