package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.Notification;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.Experimental;
import io.reactivex.internal.operators.mixed.MaterializeSingleObserver;
@Experimental
/* loaded from: classes12.dex */
public final class MaybeMaterialize<T> extends Single<Notification<T>> {
    public final Maybe<T> h;

    public MaybeMaterialize(Maybe<T> maybe) {
        this.h = maybe;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super Notification<T>> singleObserver) {
        this.h.subscribe(new MaterializeSingleObserver(singleObserver));
    }
}
