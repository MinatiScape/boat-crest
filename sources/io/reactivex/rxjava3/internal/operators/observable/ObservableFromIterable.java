package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.observers.BasicQueueDisposable;
import java.util.Iterator;
import java.util.Objects;
/* loaded from: classes12.dex */
public final class ObservableFromIterable<T> extends Observable<T> {
    public final Iterable<? extends T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> extends BasicQueueDisposable<T> {
        public final Observer<? super T> h;
        public final Iterator<? extends T> i;
        public volatile boolean j;
        public boolean k;
        public boolean l;
        public boolean m;

        public a(Observer<? super T> observer, Iterator<? extends T> it) {
            this.h = observer;
            this.i = it;
        }

        public void a() {
            while (!isDisposed()) {
                try {
                    T next = this.i.next();
                    Objects.requireNonNull(next, "The iterator returned a null value");
                    this.h.onNext(next);
                    if (isDisposed()) {
                        return;
                    }
                    try {
                        if (!this.i.hasNext()) {
                            if (isDisposed()) {
                                return;
                            }
                            this.h.onComplete();
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.h.onError(th);
                        return;
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    this.h.onError(th2);
                    return;
                }
            }
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public void clear() {
            this.l = true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.j = true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.j;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            return this.l;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() {
            if (this.l) {
                return null;
            }
            if (this.m) {
                if (!this.i.hasNext()) {
                    this.l = true;
                    return null;
                }
            } else {
                this.m = true;
            }
            T next = this.i.next();
            Objects.requireNonNull(next, "The iterator returned a null value");
            return next;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 1) != 0) {
                this.k = true;
                return 1;
            }
            return 0;
        }
    }

    public ObservableFromIterable(Iterable<? extends T> iterable) {
        this.h = iterable;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        try {
            Iterator<? extends T> it = this.h.iterator();
            try {
                if (!it.hasNext()) {
                    EmptyDisposable.complete(observer);
                    return;
                }
                a aVar = new a(observer, it);
                observer.onSubscribe(aVar);
                if (aVar.k) {
                    return;
                }
                aVar.a();
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
