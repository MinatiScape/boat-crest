package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
/* loaded from: classes12.dex */
public final class SingleHide<T> extends Single<T> {
    public final SingleSource<? extends T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements SingleObserver<T>, Disposable {
        public final SingleObserver<? super T> h;
        public Disposable i;

        public a(SingleObserver<? super T> singleObserver) {
            this.h = singleObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.i.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.i.isDisposed();
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.i, disposable)) {
                this.i = disposable;
                this.h.onSubscribe(this);
            }
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            this.h.onSuccess(t);
        }
    }

    public SingleHide(SingleSource<? extends T> singleSource) {
        this.h = singleSource;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(new a(singleObserver));
    }
}
