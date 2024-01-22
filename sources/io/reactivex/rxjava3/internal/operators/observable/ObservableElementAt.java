package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
/* loaded from: classes12.dex */
public final class ObservableElementAt<T> extends io.reactivex.rxjava3.internal.operators.observable.a<T, T> {
    public final long h;
    public final T i;
    public final boolean j;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final Observer<? super T> h;
        public final long i;
        public final T j;
        public final boolean k;
        public Disposable l;
        public long m;
        public boolean n;

        public a(Observer<? super T> observer, long j, T t, boolean z) {
            this.h = observer;
            this.i = j;
            this.j = t;
            this.k = z;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.l.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.l.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.n) {
                return;
            }
            this.n = true;
            T t = this.j;
            if (t == null && this.k) {
                this.h.onError(new NoSuchElementException());
                return;
            }
            if (t != null) {
                this.h.onNext(t);
            }
            this.h.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.n) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.n = true;
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.n) {
                return;
            }
            long j = this.m;
            if (j == this.i) {
                this.n = true;
                this.l.dispose();
                this.h.onNext(t);
                this.h.onComplete();
                return;
            }
            this.m = j + 1;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.l, disposable)) {
                this.l = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableElementAt(ObservableSource<T> observableSource, long j, T t, boolean z) {
        super(observableSource);
        this.h = j;
        this.i = t;
        this.j = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new a(observer, this.h, this.i, this.j));
    }
}
