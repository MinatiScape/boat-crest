package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
/* loaded from: classes12.dex */
public final class CompletableDetach extends Completable {
    public final CompletableSource h;

    /* loaded from: classes12.dex */
    public static final class a implements CompletableObserver, Disposable {
        public CompletableObserver h;
        public Disposable i;

        public a(CompletableObserver completableObserver) {
            this.h = completableObserver;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.h = null;
            this.i.dispose();
            this.i = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.i.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onComplete() {
            this.i = DisposableHelper.DISPOSED;
            CompletableObserver completableObserver = this.h;
            if (completableObserver != null) {
                this.h = null;
                completableObserver.onComplete();
            }
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onError(Throwable th) {
            this.i = DisposableHelper.DISPOSED;
            CompletableObserver completableObserver = this.h;
            if (completableObserver != null) {
                this.h = null;
                completableObserver.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.i, disposable)) {
                this.i = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public CompletableDetach(CompletableSource completableSource) {
        this.h = completableSource;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.h.subscribe(new a(completableObserver));
    }
}
