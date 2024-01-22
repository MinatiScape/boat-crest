package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.observers.DeferredScalarDisposable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
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
    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        DeferredScalarDisposable deferredScalarDisposable = new DeferredScalarDisposable(observer);
        observer.onSubscribe(deferredScalarDisposable);
        if (deferredScalarDisposable.isDisposed()) {
            return;
        }
        try {
            TimeUnit timeUnit = this.j;
            deferredScalarDisposable.complete(ExceptionHelper.nullCheck(timeUnit != null ? this.h.get(this.i, timeUnit) : this.h.get(), "Future returned a null value."));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (deferredScalarDisposable.isDisposed()) {
                return;
            }
            observer.onError(th);
        }
    }
}
