package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiConsumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class ObservableCollect<T, U> extends io.reactivex.internal.operators.observable.a<T, U> {
    public final Callable<? extends U> h;
    public final BiConsumer<? super U, ? super T> i;

    /* loaded from: classes12.dex */
    public static final class a<T, U> implements Observer<T>, Disposable {
        public final Observer<? super U> h;
        public final BiConsumer<? super U, ? super T> i;
        public final U j;
        public Disposable k;
        public boolean l;

        public a(Observer<? super U> observer, U u, BiConsumer<? super U, ? super T> biConsumer) {
            this.h = observer;
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
            this.h.onNext((U) this.j);
            this.h.onComplete();
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

    public ObservableCollect(ObservableSource<T> observableSource, Callable<? extends U> callable, BiConsumer<? super U, ? super T> biConsumer) {
        super(observableSource);
        this.h = callable;
        this.i = biConsumer;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super U> observer) {
        try {
            this.source.subscribe(new a(observer, ObjectHelper.requireNonNull(this.h.call(), "The initialSupplier returned a null value"), this.i));
        } catch (Throwable th) {
            EmptyDisposable.error(th, observer);
        }
    }
}
