package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.schedulers.Timed;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class SingleTimeInterval<T> extends Single<Timed<T>> {
    public final SingleSource<T> h;
    public final TimeUnit i;
    public final Scheduler j;
    public final boolean k;

    /* loaded from: classes12.dex */
    public static final class a<T> implements SingleObserver<T>, Disposable {
        public final SingleObserver<? super Timed<T>> h;
        public final TimeUnit i;
        public final Scheduler j;
        public final long k;
        public Disposable l;

        public a(SingleObserver<? super Timed<T>> singleObserver, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.h = singleObserver;
            this.i = timeUnit;
            this.j = scheduler;
            this.k = z ? scheduler.now(timeUnit) : 0L;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.l.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.l.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(@NonNull Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(@NonNull Disposable disposable) {
            if (DisposableHelper.validate(this.l, disposable)) {
                this.l = disposable;
                this.h.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(@NonNull T t) {
            this.h.onSuccess(new Timed(t, this.j.now(this.i) - this.k, this.i));
        }
    }

    public SingleTimeInterval(SingleSource<T> singleSource, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.h = singleSource;
        this.i = timeUnit;
        this.j = scheduler;
        this.k = z;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public void subscribeActual(@NonNull SingleObserver<? super Timed<T>> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i, this.j, this.k));
    }
}
