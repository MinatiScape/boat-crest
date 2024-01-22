package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableAll<T> extends io.reactivex.internal.operators.observable.a<T, Boolean> {
    public final Predicate<? super T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final Observer<? super Boolean> h;
        public final Predicate<? super T> i;
        public Disposable j;
        public boolean k;

        public a(Observer<? super Boolean> observer, Predicate<? super T> predicate) {
            this.h = observer;
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
            this.h.onNext(Boolean.TRUE);
            this.h.onComplete();
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
                    return;
                }
                this.k = true;
                this.j.dispose();
                this.h.onNext(Boolean.FALSE);
                this.h.onComplete();
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

    public ObservableAll(ObservableSource<T> observableSource, Predicate<? super T> predicate) {
        super(observableSource);
        this.h = predicate;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Boolean> observer) {
        this.source.subscribe(new a(observer, this.h));
    }
}
