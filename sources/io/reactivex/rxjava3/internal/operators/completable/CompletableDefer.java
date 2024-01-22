package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class CompletableDefer extends Completable {
    public final Supplier<? extends CompletableSource> h;

    public CompletableDefer(Supplier<? extends CompletableSource> supplier) {
        this.h = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        try {
            CompletableSource completableSource = this.h.get();
            Objects.requireNonNull(completableSource, "The completableSupplier returned a null CompletableSource");
            completableSource.subscribe(completableObserver);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, completableObserver);
        }
    }
}
