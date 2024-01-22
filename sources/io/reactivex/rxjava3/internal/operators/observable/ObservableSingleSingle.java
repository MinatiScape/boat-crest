package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
/* loaded from: classes12.dex */
public final class ObservableSingleSingle<T> extends Single<T> {
    public final ObservableSource<? extends T> h;
    public final T i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final SingleObserver<? super T> h;
        public final T i;
        public Disposable j;
        public T k;
        public boolean l;

        public a(SingleObserver<? super T> singleObserver, T t) {
            this.h = singleObserver;
            this.i = t;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.j.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.j.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.l) {
                return;
            }
            this.l = true;
            T t = this.k;
            this.k = null;
            if (t == null) {
                t = this.i;
            }
            if (t != null) {
                this.h.onSuccess(t);
            } else {
                this.h.onError(new NoSuchElementException());
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.l) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.l = true;
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            if (this.k != null) {
                this.l = true;
                this.j.dispose();
                this.h.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                return;
            }
            this.k = t;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableSingleSingle(ObservableSource<? extends T> observableSource, T t) {
        this.h = observableSource;
        this.i = t;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i));
    }
}
