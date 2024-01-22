package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class ObservableScanSeed<T, R> extends io.reactivex.internal.operators.observable.a<T, R> {
    public final BiFunction<R, ? super T, R> h;
    public final Callable<R> i;

    /* loaded from: classes12.dex */
    public static final class a<T, R> implements Observer<T>, Disposable {
        public final Observer<? super R> h;
        public final BiFunction<R, ? super T, R> i;
        public R j;
        public Disposable k;
        public boolean l;

        public a(Observer<? super R> observer, BiFunction<R, ? super T, R> biFunction, R r) {
            this.h = observer;
            this.i = biFunction;
            this.j = r;
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
            if (this.l) {
                return;
            }
            this.l = true;
            this.h.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.l) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.l = true;
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            try {
                R r = (R) ObjectHelper.requireNonNull(this.i.apply(this.j, t), "The accumulator returned a null value");
                this.j = r;
                this.h.onNext(r);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.k.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.k, disposable)) {
                this.k = disposable;
                this.h.onSubscribe(this);
                this.h.onNext((R) this.j);
            }
        }
    }

    public ObservableScanSeed(ObservableSource<T> observableSource, Callable<R> callable, BiFunction<R, ? super T, R> biFunction) {
        super(observableSource);
        this.h = biFunction;
        this.i = callable;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super R> observer) {
        try {
            this.source.subscribe(new a(observer, this.h, ObjectHelper.requireNonNull(this.i.call(), "The seed supplied is null")));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
        }
    }
}
