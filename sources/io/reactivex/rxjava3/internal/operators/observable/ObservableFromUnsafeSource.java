package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
/* loaded from: classes12.dex */
public final class ObservableFromUnsafeSource<T> extends Observable<T> {
    public final ObservableSource<T> h;

    public ObservableFromUnsafeSource(ObservableSource<T> observableSource) {
        this.h = observableSource;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.h.subscribe(observer);
    }
}
