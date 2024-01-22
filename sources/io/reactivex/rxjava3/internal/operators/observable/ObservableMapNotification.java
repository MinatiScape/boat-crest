package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class ObservableMapNotification<T, R> extends io.reactivex.rxjava3.internal.operators.observable.a<T, ObservableSource<? extends R>> {
    public final Function<? super T, ? extends ObservableSource<? extends R>> h;
    public final Function<? super Throwable, ? extends ObservableSource<? extends R>> i;
    public final Supplier<? extends ObservableSource<? extends R>> j;

    /* loaded from: classes12.dex */
    public static final class a<T, R> implements Observer<T>, Disposable {
        public final Observer<? super ObservableSource<? extends R>> h;
        public final Function<? super T, ? extends ObservableSource<? extends R>> i;
        public final Function<? super Throwable, ? extends ObservableSource<? extends R>> j;
        public final Supplier<? extends ObservableSource<? extends R>> k;
        public Disposable l;

        public a(Observer<? super ObservableSource<? extends R>> observer, Function<? super T, ? extends ObservableSource<? extends R>> function, Function<? super Throwable, ? extends ObservableSource<? extends R>> function2, Supplier<? extends ObservableSource<? extends R>> supplier) {
            this.h = observer;
            this.i = function;
            this.j = function2;
            this.k = supplier;
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
            try {
                ObservableSource<? extends R> observableSource = this.k.get();
                Objects.requireNonNull(observableSource, "The onComplete ObservableSource returned is null");
                this.h.onNext(observableSource);
                this.h.onComplete();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            try {
                ObservableSource<? extends R> apply = this.j.apply(th);
                Objects.requireNonNull(apply, "The onError ObservableSource returned is null");
                this.h.onNext(apply);
                this.h.onComplete();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.h.onError(new CompositeException(th, th2));
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            try {
                ObservableSource<? extends R> apply = this.i.apply(t);
                Objects.requireNonNull(apply, "The onNext ObservableSource returned is null");
                this.h.onNext(apply);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.h.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.l, disposable)) {
                this.l = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableMapNotification(ObservableSource<T> observableSource, Function<? super T, ? extends ObservableSource<? extends R>> function, Function<? super Throwable, ? extends ObservableSource<? extends R>> function2, Supplier<? extends ObservableSource<? extends R>> supplier) {
        super(observableSource);
        this.h = function;
        this.i = function2;
        this.j = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super ObservableSource<? extends R>> observer) {
        this.source.subscribe(new a(observer, this.h, this.i, this.j));
    }
}
