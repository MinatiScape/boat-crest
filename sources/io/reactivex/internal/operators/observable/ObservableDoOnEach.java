package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class ObservableDoOnEach<T> extends io.reactivex.internal.operators.observable.a<T, T> {
    public final Consumer<? super T> h;
    public final Consumer<? super Throwable> i;
    public final Action j;
    public final Action k;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public final Observer<? super T> h;
        public final Consumer<? super T> i;
        public final Consumer<? super Throwable> j;
        public final Action k;
        public final Action l;
        public Disposable m;
        public boolean n;

        public a(Observer<? super T> observer, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2) {
            this.h = observer;
            this.i = consumer;
            this.j = consumer2;
            this.k = action;
            this.l = action2;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.m.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.m.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.n) {
                return;
            }
            try {
                this.k.run();
                this.n = true;
                this.h.onComplete();
                try {
                    this.l.run();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                onError(th2);
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.n) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.n = true;
            try {
                this.j.accept(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.h.onError(th);
            try {
                this.l.run();
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                RxJavaPlugins.onError(th3);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.n) {
                return;
            }
            try {
                this.i.accept(t);
                this.h.onNext(t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.m.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.m, disposable)) {
                this.m = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableDoOnEach(ObservableSource<T> observableSource, Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Action action2) {
        super(observableSource);
        this.h = consumer;
        this.i = consumer2;
        this.j = action;
        this.k = action2;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new a(observer, this.h, this.i, this.j, this.k));
    }
}
