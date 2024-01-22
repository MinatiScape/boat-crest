package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.operators.maybe.MaybeOnErrorComplete;
/* loaded from: classes12.dex */
public final class SingleOnErrorComplete<T> extends Maybe<T> {
    public final Single<T> h;
    public final Predicate<? super Throwable> i;

    public SingleOnErrorComplete(Single<T> single, Predicate<? super Throwable> predicate) {
        this.h = single;
        this.i = predicate;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.h.subscribe(new MaybeOnErrorComplete.OnErrorCompleteMultiObserver(maybeObserver, this.i));
    }
}
