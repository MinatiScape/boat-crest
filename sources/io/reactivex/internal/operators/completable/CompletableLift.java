package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableOperator;
import io.reactivex.CompletableSource;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class CompletableLift extends Completable {
    public final CompletableSource h;
    public final CompletableOperator i;

    public CompletableLift(CompletableSource completableSource, CompletableOperator completableOperator) {
        this.h = completableSource;
        this.i = completableOperator;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        try {
            this.h.subscribe(this.i.apply(completableObserver));
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }
}
