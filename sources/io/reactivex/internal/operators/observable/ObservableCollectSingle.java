package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class ObservableCollectSingle<T, U> extends Single<U> implements FuseToObservable<U> {
    public final ObservableSource<T> h;
    public final Callable<? extends U> i;
    public final BiConsumer<? super U, ? super T> j;

    /* loaded from: classes12.dex */
    public static final class a<T, U> implements Observer<T>, Disposable {
        public final SingleObserver<? super U> h;
        public final BiConsumer<? super U, ? super T> i;
        public final U j;
        public Disposable k;
        public boolean l;

        public a(SingleObserver<? super U> singleObserver, U u, BiConsumer<? super U, ? super T> biConsumer) {
            this.h = singleObserver;
            this.i = biConsumer;
            this.j = u;
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
            if (this.l) {
                return;
            }
            this.l = true;
            this.h.onSuccess((U) this.j);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.l) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.l = true;
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            try {
                this.i.accept((U) this.j, t);
            } catch (Throwable th) {
                this.k.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.k, disposable)) {
                this.k = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableCollectSingle(ObservableSource<T> observableSource, Callable<? extends U> callable, BiConsumer<? super U, ? super T> biConsumer) {
        this.h = observableSource;
        this.i = callable;
        this.j = biConsumer;
    }

    @Override // io.reactivex.internal.fuseable.FuseToObservable
    public Observable<U> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableCollect(this.h, this.i, this.j));
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super U> singleObserver) {
        try {
            this.h.subscribe(new a(singleObserver, ObjectHelper.requireNonNull(this.i.call(), "The initialSupplier returned a null value"), this.j));
        } catch (Throwable th) {
            EmptyDisposable.error(th, singleObserver);
        }
    }
}
