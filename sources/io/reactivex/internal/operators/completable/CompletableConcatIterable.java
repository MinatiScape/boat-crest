package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public final class CompletableConcatIterable extends Completable {
    public final Iterable<? extends CompletableSource> h;

    /* loaded from: classes12.dex */
    public static final class a extends AtomicInteger implements CompletableObserver {
        private static final long serialVersionUID = -7965400327305809232L;
        public final CompletableObserver downstream;
        public final SequentialDisposable sd = new SequentialDisposable();
        public final Iterator<? extends CompletableSource> sources;

        public a(CompletableObserver completableObserver, Iterator<? extends CompletableSource> it) {
            this.downstream = completableObserver;
            this.sources = it;
        }

        public void next() {
            if (!this.sd.isDisposed() && getAndIncrement() == 0) {
                Iterator<? extends CompletableSource> it = this.sources;
                while (!this.sd.isDisposed()) {
                    try {
                        if (!it.hasNext()) {
                            this.downstream.onComplete();
                            return;
                        }
                        try {
                            ((CompletableSource) ObjectHelper.requireNonNull(it.next(), "The CompletableSource returned is null")).subscribe(this);
                            if (decrementAndGet() == 0) {
                                return;
                            }
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            this.downstream.onError(th);
                            return;
                        }
                    } catch (Throwable th2) {
                        Exceptions.throwIfFatal(th2);
                        this.downstream.onError(th2);
                        return;
                    }
                }
            }
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            next();
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.sd.replace(disposable);
        }
    }

    public CompletableConcatIterable(Iterable<? extends CompletableSource> iterable) {
        this.h = iterable;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        try {
            a aVar = new a(completableObserver, (Iterator) ObjectHelper.requireNonNull(this.h.iterator(), "The iterator returned is null"));
            completableObserver.onSubscribe(aVar.sd);
            aVar.next();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, completableObserver);
        }
    }
}
