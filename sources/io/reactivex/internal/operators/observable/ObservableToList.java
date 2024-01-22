package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.Collection;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class ObservableToList<T, U extends Collection<? super T>> extends io.reactivex.internal.operators.observable.a<T, U> {
    public final Callable<U> h;

    /* loaded from: classes12.dex */
    public static final class a<T, U extends Collection<? super T>> implements Observer<T>, Disposable {
        public final Observer<? super U> h;
        public Disposable i;
        public U j;

        public a(Observer<? super U> observer, U u) {
            this.h = observer;
            this.j = u;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.i.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.i.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            U u = this.j;
            this.j = null;
            this.h.onNext(u);
            this.h.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.j = null;
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.j.add(t);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.i, disposable)) {
                this.i = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableToList(ObservableSource<T> observableSource, int i) {
        super(observableSource);
        this.h = Functions.createArrayList(i);
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super U> observer) {
        try {
            this.source.subscribe(new a(observer, (Collection) ObjectHelper.requireNonNull(this.h.call(), "The collectionSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources.")));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
        }
    }

    public ObservableToList(ObservableSource<T> observableSource, Callable<U> callable) {
        super(observableSource);
        this.h = callable;
    }
}
