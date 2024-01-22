package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.internal.operators.mixed.MaterializeSingleObserver;
/* loaded from: classes12.dex */
public final class MaybeMaterialize<T> extends Single<Notification<T>> {
    public final Maybe<T> h;

    public MaybeMaterialize(Maybe<T> maybe) {
        this.h = maybe;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super Notification<T>> singleObserver) {
        this.h.subscribe(new MaterializeSingleObserver(singleObserver));
    }
}
