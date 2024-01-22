package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
/* loaded from: classes12.dex */
public final class CompletableToSingle<T> extends Single<T> {
    public final CompletableSource h;
    public final Supplier<? extends T> i;
    public final T j;

    /* loaded from: classes12.dex */
    public final class a implements CompletableObserver {
        public final SingleObserver<? super T> h;

        public a(SingleObserver<? super T> singleObserver) {
            this.h = singleObserver;
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onComplete() {
            T t;
            CompletableToSingle completableToSingle = CompletableToSingle.this;
            Supplier<? extends T> supplier = completableToSingle.i;
            if (supplier != null) {
                try {
                    t = supplier.get();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.h.onError(th);
                    return;
                }
            } else {
                t = completableToSingle.j;
            }
            if (t == null) {
                this.h.onError(new NullPointerException("The value supplied is null"));
            } else {
                this.h.onSuccess(t);
            }
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.h.onSubscribe(disposable);
        }
    }

    public CompletableToSingle(CompletableSource completableSource, Supplier<? extends T> supplier, T t) {
        this.h = completableSource;
        this.j = t;
        this.i = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(new a(singleObserver));
    }
}
