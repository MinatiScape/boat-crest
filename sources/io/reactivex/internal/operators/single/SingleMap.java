package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
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

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            this.h.onSubscribe(disposable);
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            try {
                this.h.onSuccess(ObjectHelper.requireNonNull(this.i.apply(t), "The mapper function returned a null value."));
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

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super R> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i));
    }
}
