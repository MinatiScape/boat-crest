package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
/* loaded from: classes12.dex */
public final class ObservableMaterialize<T> extends io.reactivex.rxjava3.internal.operators.observable.a<T, Notification<T>> {

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final Observer<? super Notification<T>> h;
        public Disposable i;

        public a(Observer<? super Notification<T>> observer) {
            this.h = observer;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.i.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.i.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.h.onNext(Notification.createOnComplete());
            this.h.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.h.onNext(Notification.createOnError(th));
            this.h.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            this.h.onNext(Notification.createOnNext(t));
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.i, disposable)) {
                this.i = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableMaterialize(ObservableSource<T> observableSource) {
        super(observableSource);
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super Notification<T>> observer) {
        this.source.subscribe(new a(observer));
    }
}
