package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableCountSingle<T> extends Single<Long> implements FuseToObservable<Long> {
    public final ObservableSource<T> h;

    /* loaded from: classes12.dex */
    public static final class a implements Observer<Object>, Disposable {
        public final SingleObserver<? super Long> h;
        public Disposable i;
        public long j;

        public a(SingleObserver<? super Long> singleObserver) {
            this.h = singleObserver;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.i.dispose();
            this.i = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.i.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.i = DisposableHelper.DISPOSED;
            this.h.onSuccess(Long.valueOf(this.j));
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.i = DisposableHelper.DISPOSED;
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(Object obj) {
            this.j++;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.i, disposable)) {
                this.i = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableCountSingle(ObservableSource<T> observableSource) {
        this.h = observableSource;
    }

    @Override // io.reactivex.internal.fuseable.FuseToObservable
    public Observable<Long> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableCount(this.h));
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super Long> singleObserver) {
        this.h.subscribe(new a(singleObserver));
    }
}
