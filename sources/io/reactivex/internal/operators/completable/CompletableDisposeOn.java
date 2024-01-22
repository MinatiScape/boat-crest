package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class CompletableDisposeOn extends Completable {
    public final CompletableSource h;
    public final Scheduler i;

    /* loaded from: classes12.dex */
    public static final class a implements CompletableObserver, Disposable, Runnable {
        public final CompletableObserver h;
        public final Scheduler i;
        public Disposable j;
        public volatile boolean k;

        public a(CompletableObserver completableObserver, Scheduler scheduler) {
            this.h = completableObserver;
            this.i = scheduler;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.k = true;
            this.i.scheduleDirect(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.k;
        }

        @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
        public void onComplete() {
            if (this.k) {
                return;
            }
            this.h.onComplete();
        }

        @Override // io.reactivex.CompletableObserver
        public void onError(Throwable th) {
            if (this.k) {
                RxJavaPlugins.onError(th);
            } else {
                this.h.onError(th);
            }
        }

        @Override // io.reactivex.CompletableObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.j.dispose();
            this.j = DisposableHelper.DISPOSED;
        }
    }

    public CompletableDisposeOn(CompletableSource completableSource, Scheduler scheduler) {
        this.h = completableSource;
        this.i = scheduler;
    }

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.h.subscribe(new a(completableObserver, this.i));
    }
}
