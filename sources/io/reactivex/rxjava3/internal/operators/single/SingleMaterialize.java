package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.internal.operators.mixed.MaterializeSingleObserver;
/* loaded from: classes12.dex */
public final class SingleMaterialize<T> extends Single<Notification<T>> {
    public final Single<T> h;

    public SingleMaterialize(Single<T> single) {
        this.h = single;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super Notification<T>> singleObserver) {
        this.h.subscribe(new MaterializeSingleObserver(singleObserver));
    }
}
