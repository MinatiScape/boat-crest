package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
/* loaded from: classes12.dex */
public final class CompletableDoOnEvent extends Completable {
    public final CompletableSource h;
    public final Consumer<? super Throwable> i;

    /* loaded from: classes12.dex */
    public final class a implements CompletableObserver {
        public final CompletableObserver h;

        public a(CompletableObserver completableObserver) {
            this.h = completableObserver;
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            try {
                CompletableDoOnEvent.this.i.accept(null);
                this.h.onComplete();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            try {
                CompletableDoOnEvent.this.i.accept(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.h.onError(th);
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.h.onSubscribe(disposable);
        }
    }

    public CompletableDoOnEvent(CompletableSource completableSource, Consumer<? super Throwable> consumer) {
        this.h = completableSource;
        this.i = consumer;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.h.subscribe(new a(completableObserver));
    }
}
