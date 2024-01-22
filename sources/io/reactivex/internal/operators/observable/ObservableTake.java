package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableTake<T> extends io.reactivex.internal.operators.observable.a<T, T> {
    public final long h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final Observer<? super T> h;
        public boolean i;
        public Disposable j;
        public long k;

        public a(Observer<? super T> observer, long j) {
            this.h = observer;
            this.k = j;
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
            if (this.i) {
                return;
            }
            this.i = true;
            this.j.dispose();
            this.h.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.i) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.i = true;
            this.j.dispose();
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.i) {
                return;
            }
            long j = this.k;
            long j2 = j - 1;
            this.k = j2;
            if (j > 0) {
                boolean z = j2 == 0;
                this.h.onNext(t);
                if (z) {
                    onComplete();
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                if (this.k == 0) {
                    this.i = true;
                    disposable.dispose();
                    EmptyDisposable.complete(this.h);
                    return;
                }
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableTake(ObservableSource<T> observableSource, long j) {
        super(observableSource);
        this.h = j;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new a(observer, this.h));
    }
}
