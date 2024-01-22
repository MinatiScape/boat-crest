package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposables;
/* loaded from: classes12.dex */
public final class SingleJust<T> extends Single<T> {
    public final T h;

    public SingleJust(T t) {
        this.h = t;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        singleObserver.onSubscribe(Disposables.disposed());
        singleObserver.onSuccess((T) this.h);
    }
}
