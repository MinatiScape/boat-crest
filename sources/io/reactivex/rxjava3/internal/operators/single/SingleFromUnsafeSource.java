package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
/* loaded from: classes12.dex */
public final class SingleFromUnsafeSource<T> extends Single<T> {
    public final SingleSource<T> h;

    public SingleFromUnsafeSource(SingleSource<T> singleSource) {
        this.h = singleSource;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(singleObserver);
    }
}
