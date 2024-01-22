package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
/* loaded from: classes12.dex */
public final class CompletableFromUnsafeSource extends Completable {
    public final CompletableSource h;

    public CompletableFromUnsafeSource(CompletableSource completableSource) {
        this.h = completableSource;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.h.subscribe(completableObserver);
    }
}
