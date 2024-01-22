package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.internal.fuseable.ScalarCallable;
import io.reactivex.internal.operators.observable.ObservableScalarXMap;
/* loaded from: classes12.dex */
public final class ObservableJust<T> extends Observable<T> implements ScalarCallable<T> {
    public final T h;

    public ObservableJust(T t) {
        this.h = t;
    }

    @Override // io.reactivex.internal.fuseable.ScalarCallable, java.util.concurrent.Callable
    public T call() {
        return this.h;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        ObservableScalarXMap.ScalarDisposable scalarDisposable = new ObservableScalarXMap.ScalarDisposable(observer, this.h);
        observer.onSubscribe(scalarDisposable);
        scalarDisposable.run();
    }
}
