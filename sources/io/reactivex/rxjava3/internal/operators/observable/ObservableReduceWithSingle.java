package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.operators.observable.ObservableReduceSeedSingle;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class ObservableReduceWithSingle<T, R> extends Single<R> {
    public final ObservableSource<T> h;
    public final Supplier<R> i;
    public final BiFunction<R, ? super T, R> j;

    public ObservableReduceWithSingle(ObservableSource<T> observableSource, Supplier<R> supplier, BiFunction<R, ? super T, R> biFunction) {
        this.h = observableSource;
        this.i = supplier;
        this.j = biFunction;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        try {
            R r = this.i.get();
            Objects.requireNonNull(r, "The seedSupplier returned a null value");
            this.h.subscribe(new ObservableReduceSeedSingle.a(singleObserver, this.j, r));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, singleObserver);
        }
    }
}
