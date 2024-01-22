package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.internal.operators.mixed.MaterializeSingleObserver;
/* loaded from: classes12.dex */
public final class CompletableMaterialize<T> extends Single<Notification<T>> {
    public final Completable h;

    public CompletableMaterialize(Completable completable) {
        this.h = completable;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super Notification<T>> singleObserver) {
        this.h.subscribe(new MaterializeSingleObserver(singleObserver));
    }
}
