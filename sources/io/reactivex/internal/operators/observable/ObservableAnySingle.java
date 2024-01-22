package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableAnySingle<T> extends Single<Boolean> implements FuseToObservable<Boolean> {
    public final ObservableSource<T> h;
    public final Predicate<? super T> i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final SingleObserver<? super Boolean> h;
        public final Predicate<? super T> i;
        public Disposable j;
        public boolean k;

        public a(SingleObserver<? super Boolean> singleObserver, Predicate<? super T> predicate) {
            this.h = singleObserver;
            this.i = predicate;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.j.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.j.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.k) {
                return;
            }
            this.k = true;
            this.h.onSuccess(Boolean.FALSE);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.k) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.k = true;
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.k) {
                return;
            }
            try {
                if (this.i.test(t)) {
                    this.k = true;
                    this.j.dispose();
                    this.h.onSuccess(Boolean.TRUE);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.j.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableAnySingle(ObservableSource<T> observableSource, Predicate<? super T> predicate) {
        this.h = observableSource;
        this.i = predicate;
    }

    @Override // io.reactivex.internal.fuseable.FuseToObservable
    public Observable<Boolean> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableAny(this.h, this.i));
    }

    @Override // io.reactivex.Single
    public void subscribeActual(SingleObserver<? super Boolean> singleObserver) {
        this.h.subscribe(new a(singleObserver, this.i));
    }
}
