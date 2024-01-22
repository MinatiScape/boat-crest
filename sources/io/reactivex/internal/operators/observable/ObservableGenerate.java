package io.reactivex.internal.operators.observable;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class ObservableGenerate<T, S> extends Observable<T> {
    public final Callable<S> h;
    public final BiFunction<S, Emitter<T>, S> i;
    public final Consumer<? super S> j;

    /* loaded from: classes12.dex */
    public static final class a<T, S> implements Emitter<T>, Disposable {
        public final Observer<? super T> h;
        public final BiFunction<S, ? super Emitter<T>, S> i;
        public final Consumer<? super S> j;
        public S k;
        public volatile boolean l;
        public boolean m;
        public boolean n;

        public a(Observer<? super T> observer, BiFunction<S, ? super Emitter<T>, S> biFunction, Consumer<? super S> consumer, S s) {
            this.h = observer;
            this.i = biFunction;
            this.j = consumer;
            this.k = s;
        }

        public final void a(S s) {
            try {
                this.j.accept(s);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
        }

        public void b() {
            S s = this.k;
            if (this.l) {
                this.k = null;
                a(s);
                return;
            }
            BiFunction<S, ? super Emitter<T>, S> biFunction = this.i;
            while (!this.l) {
                this.n = false;
                try {
                    s = biFunction.apply(s, this);
                    if (this.m) {
                        this.l = true;
                        this.k = null;
                        a(s);
                        return;
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.k = null;
                    this.l = true;
                    onError(th);
                    a(s);
                    return;
                }
            }
            this.k = null;
            a(s);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.l = true;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.l;
        }

        @Override // io.reactivex.Emitter
        public void onComplete() {
            if (this.m) {
                return;
            }
            this.m = true;
            this.h.onComplete();
        }

        @Override // io.reactivex.Emitter
        public void onError(Throwable th) {
            if (this.m) {
                RxJavaPlugins.onError(th);
                return;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.m = true;
            this.h.onError(th);
        }

        @Override // io.reactivex.Emitter
        public void onNext(T t) {
            if (this.m) {
                return;
            }
            if (this.n) {
                onError(new IllegalStateException("onNext already called in this generate turn"));
            } else if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                this.n = true;
                this.h.onNext(t);
            }
        }
    }

    public ObservableGenerate(Callable<S> callable, BiFunction<S, Emitter<T>, S> biFunction, Consumer<? super S> consumer) {
        this.h = callable;
        this.i = biFunction;
        this.j = consumer;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        try {
            a aVar = new a(observer, this.i, this.j, this.h.call());
            observer.onSubscribe(aVar);
            aVar.b();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
        }
    }
}
