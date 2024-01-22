package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.internal.operators.observable.ObservableFromCompletable;
/* loaded from: classes12.dex */
public final class CompletableToObservable<T> extends Observable<T> {
    public final CompletableSource h;

    public CompletableToObservable(CompletableSource completableSource) {
        this.h = completableSource;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.h.subscribe(new ObservableFromCompletable.FromCompletableObserver(observer));
    }
}
