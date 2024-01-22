package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
/* loaded from: classes12.dex */
public final class CompletableFromUnsafeSource extends Completable {
    public final CompletableSource h;

    public CompletableFromUnsafeSource(CompletableSource completableSource) {
        this.h = completableSource;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.h.subscribe(completableObserver);
    }
}
