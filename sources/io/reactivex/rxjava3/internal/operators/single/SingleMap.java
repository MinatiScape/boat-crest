package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class SingleMap<T, R> extends Single<R> {
    public final SingleSource<? extends T> h;
    public final Function<? super T, ? extends R> i;

    /* loaded from: classes12.dex */
    public static final class a<T, R> implements SingleObserver<T> {
        public final SingleObserver<? super R> h;
        public final Function<? super T, ? extends R> i;

        public a(SingleObserver<? super R> singleObserver, Function<? super T, ? extends R> function) {
            this.h = singleObserver;
            this.i = function;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.h.onSubscribe(disposable);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            try {
                R apply = this.i.apply(t);
                Objects.requireNonNull(apply, "The mapper function returned a null value.");
                this.h.onSuccess(apply);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                onError(th);
            }
        }
    }

    public SingleMap(SingleSource<? extends T> singleSource, Function<? super T, ? extends R> function) {
        this.h = singleSource;
        this.i = function;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i));
    }
}
