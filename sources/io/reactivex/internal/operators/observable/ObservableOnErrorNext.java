package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableOnErrorNext<T> extends io.reactivex.internal.operators.observable.a<T, T> {
    public final Function<? super Throwable, ? extends ObservableSource<? extends T>> h;
    public final boolean i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T> {
        public final Observer<? super T> h;
        public final Function<? super Throwable, ? extends ObservableSource<? extends T>> i;
        public final boolean j;
        public final SequentialDisposable k = new SequentialDisposable();
        public boolean l;
        public boolean m;

        public a(Observer<? super T> observer, Function<? super Throwable, ? extends ObservableSource<? extends T>> function, boolean z) {
            this.h = observer;
            this.i = function;
            this.j = z;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.m) {
                return;
            }
            this.m = true;
            this.l = true;
            this.h.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.l) {
                if (this.m) {
                    RxJavaPlugins.onError(th);
                    return;
                } else {
                    this.h.onError(th);
                    return;
                }
            }
            this.l = true;
            if (this.j && !(th instanceof Exception)) {
                this.h.onError(th);
                return;
            }
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

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.m) {
                return;
            }
            this.h.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.k.replace(disposable);
        }
    }

    public ObservableOnErrorNext(ObservableSource<T> observableSource, Function<? super Throwable, ? extends ObservableSource<? extends T>> function, boolean z) {
        super(observableSource);
        this.h = function;
        this.i = z;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        a aVar = new a(observer, this.h, this.i);
        observer.onSubscribe(aVar.k);
        this.source.subscribe(aVar);
    }
}
