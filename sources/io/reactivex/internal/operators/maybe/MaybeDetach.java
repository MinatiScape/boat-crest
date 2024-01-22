package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
/* loaded from: classes12.dex */
public final class MaybeDetach<T> extends io.reactivex.internal.operators.maybe.a<T, T> {

    /* loaded from: classes12.dex */
    public static final class a<T> implements MaybeObserver<T>, Disposable {
        public MaybeObserver<? super T> h;
        public Disposable i;

        public a(MaybeObserver<? super T> maybeObserver) {
            this.h = maybeObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.h = null;
            this.i.dispose();
            this.i = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.i.isDisposed();
        }

        @Override // io.reactivex.MaybeObserver
        public void onComplete() {
            this.i = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> maybeObserver = this.h;
            if (maybeObserver != null) {
                this.h = null;
                maybeObserver.onComplete();
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onError(Throwable th) {
            this.i = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> maybeObserver = this.h;
            if (maybeObserver != null) {
                this.h = null;
                maybeObserver.onError(th);
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.i, disposable)) {
                this.i = disposable;
                this.h.onSubscribe(this);
            }
        }

        @Override // io.reactivex.MaybeObserver
        public void onSuccess(T t) {
            this.i = DisposableHelper.DISPOSED;
            MaybeObserver<? super T> maybeObserver = this.h;
            if (maybeObserver != null) {
                this.h = null;
                maybeObserver.onSuccess(t);
            }
        }
    }

    public MaybeDetach(MaybeSource<T> maybeSource) {
        super(maybeSource);
    }

    @Override // io.reactivex.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.source.subscribe(new a(maybeObserver));
    }
}
