package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class ObservableMapNotification<T, R> extends io.reactivex.internal.operators.observable.a<T, ObservableSource<? extends R>> {
    public final Function<? super T, ? extends ObservableSource<? extends R>> h;
    public final Function<? super Throwable, ? extends ObservableSource<? extends R>> i;
    public final Callable<? extends ObservableSource<? extends R>> j;

    /* loaded from: classes12.dex */
    public static final class a<T, R> implements Observer<T>, Disposable {
        public final Observer<? super ObservableSource<? extends R>> h;
        public final Function<? super T, ? extends ObservableSource<? extends R>> i;
        public final Function<? super Throwable, ? extends ObservableSource<? extends R>> j;
        public final Callable<? extends ObservableSource<? extends R>> k;
        public Disposable l;

        public a(Observer<? super ObservableSource<? extends R>> observer, Function<? super T, ? extends ObservableSource<? extends R>> function, Function<? super Throwable, ? extends ObservableSource<? extends R>> function2, Callable<? extends ObservableSource<? extends R>> callable) {
            this.h = observer;
            this.i = function;
            this.j = function2;
            this.k = callable;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.l.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.l.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            try {
                this.h.onNext((ObservableSource) ObjectHelper.requireNonNull(this.k.call(), "The onComplete ObservableSource returned is null"));
                this.h.onComplete();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            try {
                this.h.onNext((ObservableSource) ObjectHelper.requireNonNull(this.j.apply(th), "The onError ObservableSource returned is null"));
                this.h.onComplete();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.h.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            try {
                this.h.onNext((ObservableSource) ObjectHelper.requireNonNull(this.i.apply(t), "The onNext ObservableSource returned is null"));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.l, disposable)) {
                this.l = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableMapNotification(ObservableSource<T> observableSource, Function<? super T, ? extends ObservableSource<? extends R>> function, Function<? super Throwable, ? extends ObservableSource<? extends R>> function2, Callable<? extends ObservableSource<? extends R>> callable) {
        super(observableSource);
        this.h = function;
        this.i = function2;
        this.j = callable;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super ObservableSource<? extends R>> observer) {
        this.source.subscribe(new a(observer, this.h, this.i, this.j));
    }
}
