package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public final class CompletableMergeIterable extends Completable {
    public final Iterable<? extends CompletableSource> h;

    /* loaded from: classes12.dex */
    public static final class a extends AtomicBoolean implements CompletableObserver {
        private static final long serialVersionUID = -7730517613164279224L;
        public final CompletableObserver downstream;
        public final CompositeDisposable set;
        public final AtomicInteger wip;

        public a(CompletableObserver completableObserver, CompositeDisposable compositeDisposable, AtomicInteger atomicInteger) {
            this.downstream = completableObserver;
            this.set = compositeDisposable;
            this.wip = atomicInteger;
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            if (this.wip.decrementAndGet() == 0 && compareAndSet(false, true)) {
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            this.set.dispose();
            if (compareAndSet(false, true)) {
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            this.set.add(disposable);
        }
    }

    public CompletableMergeIterable(Iterable<? extends CompletableSource> iterable) {
        this.h = iterable;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        completableObserver.onSubscribe(compositeDisposable);
        try {
            Iterator it = (Iterator) ObjectHelper.requireNonNull(this.h.iterator(), "The source iterator returned is null");
            AtomicInteger atomicInteger = new AtomicInteger(1);
            a aVar = new a(completableObserver, compositeDisposable, atomicInteger);
            while (!compositeDisposable.isDisposed()) {
                try {
                    if (!it.hasNext()) {
                        aVar.onComplete();
                        return;
                    } else if (compositeDisposable.isDisposed()) {
                        return;
                    } else {
                        try {
                            CompletableSource completableSource = (CompletableSource) ObjectHelper.requireNonNull(it.next(), "The iterator returned a null CompletableSource");
                            if (compositeDisposable.isDisposed()) {
                                return;
                            }
                            atomicInteger.getAndIncrement();
                            completableSource.subscribe(aVar);
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            compositeDisposable.dispose();
                            aVar.onError(th);
                            return;
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    compositeDisposable.dispose();
                    aVar.onError(th2);
                    return;
                }
            }
        } catch (Throwable th3) {
            Exceptions.throwIfFatal(th3);
            completableObserver.onError(th3);
        }
    }
}