package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableFromObservable<T> extends Flowable<T> {
    public final ObservableSource<T> i;

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

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.h.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            this.h.onNext(t);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            this.i = disposable;
            this.h.onSubscribe(this);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
        }
    }

    public FlowableFromObservable(ObservableSource<T> observableSource) {
        this.i = observableSource;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.i.subscribe(new a(subscriber));
    }
}
