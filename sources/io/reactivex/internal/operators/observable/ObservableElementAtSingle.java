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
import java.util.NoSuchElementException;
/* loaded from: classes12.dex */
public final class ObservableElementAtSingle<T> extends Single<T> implements FuseToObservable<T> {
    public final ObservableSource<T> h;
    public final long i;
    public final T j;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final SingleObserver<? super T> h;
        public final long i;
        public final T j;
        public Disposable k;
        public long l;
        public boolean m;

        public a(SingleObserver<? super T> singleObserver, long j, T t) {
            this.h = singleObserver;
            this.i = j;
            this.j = t;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.k.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.k.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.m) {
                return;
            }
            this.m = true;
            T t = this.j;
            if (t != null) {
                this.h.onSuccess(t);
            } else {
                this.h.onError(new NoSuchElementException());
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.m) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.m = true;
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.m) {
                return;
            }
            long j = this.l;
            if (j == this.i) {
                this.m = true;
                this.k.dispose();
                this.h.onSuccess(t);
                return;
            }
            this.l = j + 1;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.k, disposable)) {
                this.k = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableElementAtSingle(ObservableSource<T> observableSource, long j, T t) {
        this.h = observableSource;
        this.i = j;
        this.j = t;
    }

    @Override // io.reactivex.internal.fuseable.FuseToObservable
    public Observable<T> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableElementAt(this.h, this.i, this.j, true));
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super T> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i, this.j));
    }
}
