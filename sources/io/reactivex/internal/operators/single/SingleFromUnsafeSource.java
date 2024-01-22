package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
/* loaded from: classes12.dex */
public final class SingleFromUnsafeSource<T> extends Single<T> {
    public final SingleSource<T> h;

    public SingleFromUnsafeSource(SingleSource<T> singleSource) {
        this.h = singleSource;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(singleObserver);
    }
}
