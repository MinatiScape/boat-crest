package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
/* loaded from: classes12.dex */
public final class ObservableSwitchIfEmpty<T> extends io.reactivex.internal.operators.observable.a<T, T> {
    public final ObservableSource<? extends T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T> {
        public final Observer<? super T> h;
        public final ObservableSource<? extends T> i;
        public boolean k = true;
        public final SequentialDisposable j = new SequentialDisposable();

        public a(Observer<? super T> observer, ObservableSource<? extends T> observableSource) {
            this.h = observer;
            this.i = observableSource;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.k) {
                this.k = false;
                this.i.subscribe(this);
                return;
            }
            this.h.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.k) {
                this.k = false;
            }
            this.h.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.j.update(disposable);
        }
    }

    public ObservableSwitchIfEmpty(ObservableSource<T> observableSource, ObservableSource<? extends T> observableSource2) {
        super(observableSource);
        this.h = observableSource2;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        a aVar = new a(observer, this.h);
        observer.onSubscribe(aVar.j);
        this.source.subscribe(aVar);
    }
}
