package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class CompletableToSingle<T> extends Single<T> {
    public final CompletableSource h;
    public final Callable<? extends T> i;
    public final T j;

    /* loaded from: classes12.dex */
    public final class a implements CompletableObserver {
        public final SingleObserver<? super T> h;

        public a(SingleObserver<? super T> singleObserver) {
            this.h = singleObserver;
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            T call;
            CompletableToSingle completableToSingle = CompletableToSingle.this;
            Callable<? extends T> callable = completableToSingle.i;
            if (callable != null) {
                try {
                    call = callable.call();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.h.onError(th);
                    return;
                }
            } else {
                call = completableToSingle.j;
            }
            if (call == null) {
                this.h.onError(new NullPointerException("The value supplied is null"));
            } else {
                this.h.onSuccess(call);
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.h.onSubscribe(disposable);
        }
    }

    public CompletableToSingle(CompletableSource completableSource, Callable<? extends T> callable, T t) {
        this.h = completableSource;
        this.j = t;
        this.i = callable;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(new a(singleObserver));
    }
}
