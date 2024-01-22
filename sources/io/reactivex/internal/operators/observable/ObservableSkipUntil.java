package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;
/* loaded from: classes12.dex */
public final class ObservableSkipUntil<T, U> extends io.reactivex.internal.operators.observable.a<T, T> {
    public final ObservableSource<U> h;

    /* loaded from: classes12.dex */
    public final class a implements Observer<U> {
        public final ArrayCompositeDisposable h;
        public final b<T> i;
        public final SerializedObserver<T> j;
        public Disposable k;

        public a(ObservableSkipUntil observableSkipUntil, ArrayCompositeDisposable arrayCompositeDisposable, b<T> bVar, SerializedObserver<T> serializedObserver) {
            this.h = arrayCompositeDisposable;
            this.i = bVar;
            this.j = serializedObserver;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.i.k = true;
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.h.dispose();
            this.j.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(U u) {
            this.k.dispose();
            this.i.k = true;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.k, disposable)) {
                this.k = disposable;
                this.h.setResource(1, disposable);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> implements Observer<T> {
        public final Observer<? super T> h;
        public final ArrayCompositeDisposable i;
        public Disposable j;
        public volatile boolean k;
        public boolean l;

        public b(Observer<? super T> observer, ArrayCompositeDisposable arrayCompositeDisposable) {
            this.h = observer;
            this.i = arrayCompositeDisposable;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.i.dispose();
            this.h.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.i.dispose();
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.l) {
                this.h.onNext(t);
            } else if (this.k) {
                this.l = true;
                this.h.onNext(t);
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.i.setResource(0, disposable);
            }
        }
    }

    public ObservableSkipUntil(ObservableSource<T> observableSource, ObservableSource<U> observableSource2) {
        super(observableSource);
        this.h = observableSource2;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        SerializedObserver serializedObserver = new SerializedObserver(observer);
        ArrayCompositeDisposable arrayCompositeDisposable = new ArrayCompositeDisposable(2);
        serializedObserver.onSubscribe(arrayCompositeDisposable);
        b bVar = new b(serializedObserver, arrayCompositeDisposable);
        this.h.subscribe(new a(this, arrayCompositeDisposable, bVar, serializedObserver));
        this.source.subscribe(bVar);
    }
}
