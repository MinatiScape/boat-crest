package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class SingleDefer<T> extends Single<T> {
    public final Supplier<? extends SingleSource<? extends T>> h;

    public SingleDefer(Supplier<? extends SingleSource<? extends T>> supplier) {
        this.h = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        try {
            SingleSource<? extends T> singleSource = this.h.get();
            Objects.requireNonNull(singleSource, "The singleSupplier returned a null SingleSource");
            singleSource.subscribe(singleObserver);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, singleObserver);
        }
    }
}
