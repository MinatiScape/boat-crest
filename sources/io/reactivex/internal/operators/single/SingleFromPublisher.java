package io.reactivex.internal.operators.single;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class SingleFromPublisher<T> extends Single<T> {
    public final Publisher<? extends T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements FlowableSubscriber<T>, Disposable {
        public final SingleObserver<? super T> h;
        public Subscription i;
        public T j;
        public boolean k;
        public volatile boolean l;

        public a(SingleObserver<? super T> singleObserver) {
            this.h = singleObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.l = true;
            this.i.cancel();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.l;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.k) {
                return;
            }
            this.k = true;
            T t = this.j;
            this.j = null;
            if (t == null) {
                this.h.onError(new NoSuchElementException("The source Publisher is empty"));
            } else {
                this.h.onSuccess(t);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.k) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.k = true;
            this.j = null;
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.k) {
                return;
            }
            if (this.j != null) {
                this.i.cancel();
                this.k = true;
                this.j = null;
                this.h.onError(new IndexOutOfBoundsException("Too many elements in the Publisher"));
                return;
            }
            this.j = t;
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.i, subscription)) {
                this.i = subscription;
                this.h.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }
    }

    public SingleFromPublisher(Publisher<? extends T> publisher) {
        this.h = publisher;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(new a(singleObserver));
    }
}
