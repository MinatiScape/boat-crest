package io.reactivex.rxjava3.internal.operators.maybe;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.schedulers.Timed;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class MaybeTimeInterval<T> extends Maybe<Timed<T>> {
    public final MaybeSource<T> h;
    public final TimeUnit i;
    public final Scheduler j;
    public final boolean k;

    /* loaded from: classes12.dex */
    public static final class a<T> implements MaybeObserver<T>, Disposable {
        public final MaybeObserver<? super Timed<T>> h;
        public final TimeUnit i;
        public final Scheduler j;
        public final long k;
        public Disposable l;

        public a(MaybeObserver<? super Timed<T>> maybeObserver, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.h = maybeObserver;
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

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onComplete() {
            this.h.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onError(@NonNull Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
        public void onSubscribe(@NonNull Disposable disposable) {
            if (DisposableHelper.validate(this.l, disposable)) {
                this.l = disposable;
                this.h.onSubscribe(this);
            }
        }

        @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
        public void onSuccess(@NonNull T t) {
            this.h.onSuccess(new Timed(t, this.j.now(this.i) - this.k, this.i));
        }
    }

    public MaybeTimeInterval(MaybeSource<T> maybeSource, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.h = maybeSource;
        this.i = timeUnit;
        this.j = scheduler;
        this.k = z;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(@NonNull MaybeObserver<? super Timed<T>> maybeObserver) {
        this.h.subscribe(new a(maybeObserver, this.i, this.j, this.k));
    }
}
