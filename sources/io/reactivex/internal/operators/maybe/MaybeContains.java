package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.HasUpstreamMaybeSource;
/* loaded from: classes12.dex */
public final class MaybeContains<T> extends Single<Boolean> implements HasUpstreamMaybeSource<T> {
    public final MaybeSource<T> h;
    public final Object i;

    /* loaded from: classes12.dex */
    public static final class a implements MaybeObserver<Object>, Disposable {
        public final SingleObserver<? super Boolean> h;
        public final Object i;
        public Disposable j;

        public a(SingleObserver<? super Boolean> singleObserver, Object obj) {
            this.h = singleObserver;
            this.i = obj;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.j.dispose();
            this.j = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.j.isDisposed();
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.j = DisposableHelper.DISPOSED;
            this.h.onSuccess(Boolean.FALSE);
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            this.j = DisposableHelper.DISPOSED;
            this.h.onError(th);
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(Object obj) {
            this.j = DisposableHelper.DISPOSED;
            this.h.onSuccess(Boolean.valueOf(ObjectHelper.equals(obj, this.i)));
        }
    }

    public MaybeContains(MaybeSource<T> maybeSource, Object obj) {
        this.h = maybeSource;
        this.i = obj;
    }

    @Override // io.reactivex.internal.fuseable.HasUpstreamMaybeSource
    public MaybeSource<T> source() {
        return this.h;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i));
    }
}
