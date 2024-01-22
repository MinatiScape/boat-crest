package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.NoSuchElementException;
/* loaded from: classes12.dex */
public final class ObservableLastSingle<T> extends Single<T> {
    public final ObservableSource<T> h;
    public final T i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final SingleObserver<? super T> h;
        public final T i;
        public Disposable j;
        public T k;

        public a(SingleObserver<? super T> singleObserver, T t) {
            this.h = singleObserver;
            this.i = t;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.j.dispose();
            this.j = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.j == DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.j = DisposableHelper.DISPOSED;
            T t = this.k;
            if (t != null) {
                this.k = null;
                this.h.onSuccess(t);
                return;
            }
            T t2 = this.i;
            if (t2 != null) {
                this.h.onSuccess(t2);
            } else {
                this.h.onError(new NoSuchElementException());
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.j = DisposableHelper.DISPOSED;
            this.k = null;
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.k = t;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableLastSingle(ObservableSource<T> observableSource, T t) {
        this.h = observableSource;
        this.i = t;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i));
    }
}
