package io.reactivex.internal.operators.observable;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableReduceMaybe<T> extends Maybe<T> {
    public final ObservableSource<T> h;
    public final BiFunction<T, T, T> i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final MaybeObserver<? super T> h;
        public final BiFunction<T, T, T> i;
        public boolean j;
        public T k;
        public Disposable l;

        public a(MaybeObserver<? super T> maybeObserver, BiFunction<T, T, T> biFunction) {
            this.h = maybeObserver;
            this.i = biFunction;
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
            if (this.j) {
                return;
            }
            this.j = true;
            T t = this.k;
            this.k = null;
            if (t != null) {
                this.h.onSuccess(t);
            } else {
                this.h.onComplete();
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.j) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.j = true;
            this.k = null;
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.j) {
                return;
            }
            T t2 = this.k;
            if (t2 == null) {
                this.k = t;
                return;
            }
            try {
                this.k = (T) ObjectHelper.requireNonNull(this.i.apply(t2, t), "The reducer returned a null value");
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.l.dispose();
                onError(th);
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

    public ObservableReduceMaybe(ObservableSource<T> observableSource, BiFunction<T, T, T> biFunction) {
        this.h = observableSource;
        this.i = biFunction;
    }

    @Override // io.reactivex.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.h.subscribe(new a(maybeObserver, this.i));
    }
}
