package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
/* loaded from: classes12.dex */
public final class SingleJust<T> extends Single<T> {
    public final T h;

    public SingleJust(T t) {
        this.h = t;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        singleObserver.onSubscribe(Disposable.disposed());
        singleObserver.onSuccess((T) this.h);
    }
}
