package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class ObservableCollect<T, U> extends io.reactivex.rxjava3.internal.operators.observable.a<T, U> {
    public final Supplier<? extends U> h;
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

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.k.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.k.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.h.onNext((U) this.j);
            this.h.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.l) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.l = true;
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            try {
                this.i.accept((U) this.j, t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.k.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.k, disposable)) {
                this.k = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableCollect(ObservableSource<T> observableSource, Supplier<? extends U> supplier, BiConsumer<? super U, ? super T> biConsumer) {
        super(observableSource);
        this.h = supplier;
        this.i = biConsumer;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super U> observer) {
        try {
            U u = this.h.get();
            Objects.requireNonNull(u, "The initialSupplier returned a null value");
            this.source.subscribe(new a(observer, u, this.i));
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
        }
    }
}
