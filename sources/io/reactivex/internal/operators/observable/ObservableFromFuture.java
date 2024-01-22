package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.DeferredScalarDisposable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class ObservableFromFuture<T> extends Observable<T> {
    public final Future<? extends T> h;
    public final long i;
    public final TimeUnit j;

    public ObservableFromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        this.h = future;
        this.i = j;
        this.j = timeUnit;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        DeferredScalarDisposable deferredScalarDisposable = new DeferredScalarDisposable(observer);
        observer.onSubscribe(deferredScalarDisposable);
        if (deferredScalarDisposable.isDisposed()) {
            return;
        }
        try {
            TimeUnit timeUnit = this.j;
            deferredScalarDisposable.complete(ObjectHelper.requireNonNull(timeUnit != null ? this.h.get(this.i, timeUnit) : this.h.get(), "Future returned null"));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (deferredScalarDisposable.isDisposed()) {
                return;
            }
            observer.onError(th);
        }
    }
}
