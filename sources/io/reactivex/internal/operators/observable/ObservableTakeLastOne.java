package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
/* loaded from: classes12.dex */
public final class ObservableTakeLastOne<T> extends io.reactivex.internal.operators.observable.a<T, T> {

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final Observer<? super T> h;
        public Disposable i;
        public T j;

        public a(Observer<? super T> observer) {
            this.h = observer;
        }

        public void a() {
            T t = this.j;
            if (t != null) {
                this.j = null;
                this.h.onNext(t);
            }
            this.h.onComplete();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.j = null;
            this.i.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.i.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            a();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.j = null;
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.j = t;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.i, disposable)) {
                this.i = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableTakeLastOne(ObservableSource<T> observableSource) {
        super(observableSource);
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new a(observer));
    }
}
