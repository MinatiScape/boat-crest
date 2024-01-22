package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableReduceSeedSingle<T, R> extends Single<R> {
    public final ObservableSource<T> h;
    public final R i;
    public final BiFunction<R, ? super T, R> j;

    /* loaded from: classes12.dex */
    public static final class a<T, R> implements Observer<T>, Disposable {
        public final SingleObserver<? super R> h;
        public final BiFunction<R, ? super T, R> i;
        public R j;
        public Disposable k;

        public a(SingleObserver<? super R> singleObserver, BiFunction<R, ? super T, R> biFunction, R r) {
            this.h = singleObserver;
            this.j = r;
            this.i = biFunction;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.k.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.k.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            R r = this.j;
            if (r != null) {
                this.j = null;
                this.h.onSuccess(r);
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.j != null) {
                this.j = null;
                this.h.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            R r = this.j;
            if (r != null) {
                try {
                    this.j = (R) ObjectHelper.requireNonNull(this.i.apply(r, t), "The reducer returned a null value");
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.k.dispose();
                    onError(th);
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.k, disposable)) {
                this.k = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableReduceSeedSingle(ObservableSource<T> observableSource, R r, BiFunction<R, ? super T, R> biFunction) {
        this.h = observableSource;
        this.i = r;
        this.j = biFunction;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.j, this.i));
    }
}
