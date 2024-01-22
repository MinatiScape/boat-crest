package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
/* loaded from: classes12.dex */
public final class ObservableFromUnsafeSource<T> extends Observable<T> {
    public final ObservableSource<T> h;

    public ObservableFromUnsafeSource(ObservableSource<T> observableSource) {
        this.h = observableSource;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.h.subscribe(observer);
    }
}
