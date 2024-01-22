package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableFromObservable<T> extends Flowable<T> {
    public final Observable<T> i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Subscription {
        public final Subscriber<? super T> h;
        public Disposable i;

        public a(Subscriber<? super T> subscriber) {
            this.h = subscriber;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.i.dispose();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.h.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.h.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.i = disposable;
            this.h.onSubscribe(this);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
        }
    }

    public FlowableFromObservable(Observable<T> observable) {
        this.i = observable;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.i.subscribe(new a(subscriber));
    }
}
