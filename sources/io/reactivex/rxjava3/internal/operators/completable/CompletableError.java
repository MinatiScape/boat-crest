package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
/* loaded from: classes12.dex */
public final class CompletableError extends Completable {
    public final Throwable h;

    public CompletableError(Throwable th) {
        this.h = th;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        EmptyDisposable.error(this.h, completableObserver);
    }
}
