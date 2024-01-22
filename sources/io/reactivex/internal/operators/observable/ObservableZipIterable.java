package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;
/* loaded from: classes12.dex */
public final class ObservableZipIterable<T, U, V> extends Observable<V> {
    public final Observable<? extends T> h;
    public final Iterable<U> i;
    public final BiFunction<? super T, ? super U, ? extends V> j;

    /* loaded from: classes12.dex */
    public static final class a<T, U, V> implements Observer<T>, Disposable {
        public final Observer<? super V> h;
        public final Iterator<U> i;
        public final BiFunction<? super T, ? super U, ? extends V> j;
        public Disposable k;
        public boolean l;

        public a(Observer<? super V> observer, Iterator<U> it, BiFunction<? super T, ? super U, ? extends V> biFunction) {
            this.h = observer;
            this.i = it;
            this.j = biFunction;
        }

        public void a(Throwable th) {
            this.l = true;
            this.k.dispose();
            this.h.onError(th);
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
                try {
                    this.h.onNext(ObjectHelper.requireNonNull(this.j.apply(t, ObjectHelper.requireNonNull(this.i.next(), "The iterator returned a null value")), "The zipper function returned a null value"));
                    try {
                        if (this.i.hasNext()) {
                            return;
                        }
                        this.l = true;
                        this.k.dispose();
                        this.h.onComplete();
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        a(th);
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    a(th2);
                }
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                a(th3);
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

    public ObservableZipIterable(Observable<? extends T> observable, Iterable<U> iterable, BiFunction<? super T, ? super U, ? extends V> biFunction) {
        this.h = observable;
        this.i = iterable;
        this.j = biFunction;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super V> observer) {
        try {
            Iterator it = (Iterator) ObjectHelper.requireNonNull(this.i.iterator(), "The iterator returned by other is null");
            try {
                if (!it.hasNext()) {
                    EmptyDisposable.complete(observer);
                } else {
                    this.h.subscribe(new a(observer, it, this.j));
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, observer);
            }
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            EmptyDisposable.error(th2, observer);
        }
    }
}
