package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class SingleDoAfterTerminate<T> extends Single<T> {
    public final SingleSource<T> h;
    public final Action i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements SingleObserver<T>, Disposable {
        public final SingleObserver<? super T> h;
        public final Action i;
        public Disposable j;

        public a(SingleObserver<? super T> singleObserver, Action action) {
            this.h = singleObserver;
            this.i = action;
        }

        public final void a() {
            try {
                this.i.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.j.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.j.isDisposed();
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.h.onError(th);
            a();
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            this.h.onSuccess(t);
            a();
        }
    }

    public SingleDoAfterTerminate(SingleSource<T> singleSource, Action action) {
        this.h = singleSource;
        this.i = action;
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i));
    }
}