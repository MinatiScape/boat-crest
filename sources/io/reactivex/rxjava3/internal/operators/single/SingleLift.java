package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleOperator;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class SingleLift<T, R> extends Single<R> {
    public final SingleSource<T> h;
    public final SingleOperator<? extends R, ? super T> i;

    public SingleLift(SingleSource<T> singleSource, SingleOperator<? extends R, ? super T> singleOperator) {
        this.h = singleSource;
        this.i = singleOperator;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        try {
            SingleObserver<? super Object> apply = this.i.apply(singleObserver);
            Objects.requireNonNull(apply, "The onLift returned a null SingleObserver");
            this.h.subscribe(apply);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, singleObserver);
        }
    }
}
