package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposables;
/* loaded from: classes12.dex */
public final class MaybeError<T> extends Maybe<T> {
    public final Throwable h;

    public MaybeError(Throwable th) {
        this.h = th;
    }

    @Override // io.reactivex.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        maybeObserver.onSubscribe(Disposables.disposed());
        maybeObserver.onError(this.h);
    }
}
