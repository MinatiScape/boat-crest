package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiConsumer;
/* loaded from: classes12.dex */
public final class SingleDoOnEvent<T> extends Single<T> {
    public final SingleSource<T> h;
    public final BiConsumer<? super T, ? super Throwable> i;

    /* loaded from: classes12.dex */
    public final class a implements SingleObserver<T> {
        public final SingleObserver<? super T> h;

        public a(SingleObserver<? super T> singleObserver) {
            this.h = singleObserver;
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            try {
                SingleDoOnEvent.this.i.accept(null, th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.h.onError(th);
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            this.h.onSubscribe(disposable);
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            try {
                SingleDoOnEvent.this.i.accept(t, null);
                this.h.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }
    }

    public SingleDoOnEvent(SingleSource<T> singleSource, BiConsumer<? super T, ? super Throwable> biConsumer) {
        this.h = singleSource;
        this.i = biConsumer;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(new a(singleObserver));
    }
}
