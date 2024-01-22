package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiPredicate;
/* loaded from: classes12.dex */
public final class SingleContains<T> extends Single<Boolean> {
    public final SingleSource<T> h;
    public final Object i;
    public final BiPredicate<Object, Object> j;

    /* loaded from: classes12.dex */
    public final class a implements SingleObserver<T> {
        public final SingleObserver<? super Boolean> h;

        public a(SingleObserver<? super Boolean> singleObserver) {
            this.h = singleObserver;
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
                SingleContains singleContains = SingleContains.this;
                this.h.onSuccess(Boolean.valueOf(singleContains.j.test(t, singleContains.i)));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }
    }

    public SingleContains(SingleSource<T> singleSource, Object obj, BiPredicate<Object, Object> biPredicate) {
        this.h = singleSource;
        this.i = obj;
        this.j = biPredicate;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        this.h.subscribe(new a(singleObserver));
    }
}
