package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableOnErrorNext<T> extends io.reactivex.rxjava3.internal.operators.observable.a<T, T> {
    public final Function<? super Throwable, ? extends ObservableSource<? extends T>> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T> {
        public final Observer<? super T> h;
        public final Function<? super Throwable, ? extends ObservableSource<? extends T>> i;
        public final SequentialDisposable j = new SequentialDisposable();
        public boolean k;
        public boolean l;

        public a(Observer<? super T> observer, Function<? super Throwable, ? extends ObservableSource<? extends T>> function) {
            this.h = observer;
            this.i = function;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.k = true;
            this.h.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.k) {
                if (this.l) {
                    RxJavaPlugins.onError(th);
                    return;
                } else {
                    this.h.onError(th);
                    return;
                }
            }
            this.k = true;
            try {
                ObservableSource<? extends T> apply = this.i.apply(th);
                if (apply == null) {
                    NullPointerException nullPointerException = new NullPointerException("Observable is null");
                    nullPointerException.initCause(th);
                    this.h.onError(nullPointerException);
                    return;
                }
                apply.subscribe(this);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.h.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            this.h.onNext(t);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            this.j.replace(disposable);
        }
    }

    public ObservableOnErrorNext(ObservableSource<T> observableSource, Function<? super Throwable, ? extends ObservableSource<? extends T>> function) {
        super(observableSource);
        this.h = function;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        a aVar = new a(observer, this.h);
        observer.onSubscribe(aVar.j);
        this.source.subscribe(aVar);
    }
}
