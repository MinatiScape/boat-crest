package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableAmb<T> extends Observable<T> {
    public final ObservableSource<? extends T>[] h;
    public final Iterable<? extends ObservableSource<? extends T>> i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Disposable {
        public final Observer<? super T> h;
        public final b<T>[] i;
        public final AtomicInteger j = new AtomicInteger();

        public a(Observer<? super T> observer, int i) {
            this.h = observer;
            this.i = new b[i];
        }

        public void a(ObservableSource<? extends T>[] observableSourceArr) {
            b<T>[] bVarArr = this.i;
            int length = bVarArr.length;
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                bVarArr[i] = new b<>(this, i2, this.h);
                i = i2;
            }
            this.j.lazySet(0);
            this.h.onSubscribe(this);
            for (int i3 = 0; i3 < length && this.j.get() == 0; i3++) {
                observableSourceArr[i3].subscribe(bVarArr[i3]);
            }
        }

        public boolean b(int i) {
            int i2 = this.j.get();
            int i3 = 0;
            if (i2 != 0) {
                return i2 == i;
            } else if (this.j.compareAndSet(0, i)) {
                b<T>[] bVarArr = this.i;
                int length = bVarArr.length;
                while (i3 < length) {
                    int i4 = i3 + 1;
                    if (i4 != i) {
                        bVarArr[i3].dispose();
                    }
                    i3 = i4;
                }
                return true;
            } else {
                return false;
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.j.get() != -1) {
                this.j.lazySet(-1);
                for (b<T> bVar : this.i) {
                    bVar.dispose();
                }
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.j.get() == -1;
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends AtomicReference<Disposable> implements Observer<T> {
        private static final long serialVersionUID = -1185974347409665484L;
        public final Observer<? super T> downstream;
        public final int index;
        public final a<T> parent;
        public boolean won;

        public b(a<T> aVar, int i, Observer<? super T> observer) {
            this.parent = aVar;
            this.index = i;
            this.downstream = observer;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.won) {
                this.downstream.onComplete();
            } else if (this.parent.b(this.index)) {
                this.won = true;
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.won) {
                this.downstream.onError(th);
            } else if (this.parent.b(this.index)) {
                this.won = true;
                this.downstream.onError(th);
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.won) {
                this.downstream.onNext(t);
            } else if (this.parent.b(this.index)) {
                this.won = true;
                this.downstream.onNext(t);
            } else {
                get().dispose();
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            DisposableHelper.setOnce(this, disposable);
        }
    }

    public ObservableAmb(ObservableSource<? extends T>[] observableSourceArr, Iterable<? extends ObservableSource<? extends T>> iterable) {
        this.h = observableSourceArr;
        this.i = iterable;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        int length;
        ObservableSource<? extends T>[] observableSourceArr = this.h;
        if (observableSourceArr == null) {
            observableSourceArr = new ObservableSource[8];
            try {
                length = 0;
                for (ObservableSource<? extends T> observableSource : this.i) {
                    if (observableSource == null) {
                        EmptyDisposable.error(new NullPointerException("One of the sources is null"), observer);
                        return;
                    }
                    if (length == observableSourceArr.length) {
                        ObservableSource<? extends T>[] observableSourceArr2 = new ObservableSource[(length >> 2) + length];
                        System.arraycopy(observableSourceArr, 0, observableSourceArr2, 0, length);
                        observableSourceArr = observableSourceArr2;
                    }
                    int i = length + 1;
                    observableSourceArr[length] = observableSource;
                    length = i;
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, observer);
                return;
            }
        } else {
            length = observableSourceArr.length;
        }
        if (length == 0) {
            EmptyDisposable.complete(observer);
        } else if (length == 1) {
            observableSourceArr[0].subscribe(observer);
        } else {
            new a(observer, length).a(observableSourceArr);
        }
    }
}
