package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
/* loaded from: classes12.dex */
public final class CompletableFromObservable<T> extends Completable {
    public final ObservableSource<T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T> {
        public final CompletableObserver h;

        public a(CompletableObserver completableObserver) {
            this.h = completableObserver;
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
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.h.onSubscribe(disposable);
        }
    }

    public CompletableFromObservable(ObservableSource<T> observableSource) {
        this.h = observableSource;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.h.subscribe(new a(completableObserver));
    }
}
