package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class SingleDoOnSubscribe<T> extends Single<T> {
    public final SingleSource<T> h;
    public final Consumer<? super Disposable> i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements SingleObserver<T> {
        public final SingleObserver<? super T> h;
        public final Consumer<? super Disposable> i;
        public boolean j;

        public a(SingleObserver<? super T> singleObserver, Consumer<? super Disposable> consumer) {
            this.h = singleObserver;
            this.i = consumer;
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            if (this.j) {
                RxJavaPlugins.onError(th);
            } else {
                this.h.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            try {
                this.i.accept(disposable);
                this.h.onSubscribe(disposable);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.j = true;
                disposable.dispose();
                EmptyDisposable.error(th, this.h);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(T t) {
            if (this.j) {
                return;
            }
            this.h.onSuccess(t);
        }
    }

    public SingleDoOnSubscribe(SingleSource<T> singleSource, Consumer<? super Disposable> consumer) {
        this.h = singleSource;
        this.i = consumer;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i));
    }
}
