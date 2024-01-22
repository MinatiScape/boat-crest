package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.fuseable.QueueDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;
/* loaded from: classes12.dex */
public final class ObservableFromStream<T> extends Observable<T> {
    public final Stream<T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements QueueDisposable<T> {
        public final Observer<? super T> h;
        public Iterator<T> i;
        public AutoCloseable j;
        public volatile boolean k;
        public boolean l;
        public boolean m;

        public a(Observer<? super T> observer, Iterator<T> it, AutoCloseable autoCloseable) {
            this.h = observer;
            this.i = it;
            this.j = autoCloseable;
        }

        public void a() {
            if (this.m) {
                return;
            }
            Iterator<T> it = this.i;
            Observer<? super T> observer = this.h;
            while (!this.k) {
                try {
                    T next = it.next();
                    Objects.requireNonNull(next, "The Stream's Iterator.next returned a null value");
                    if (!this.k) {
                        observer.onNext(next);
                        if (!this.k) {
                            try {
                                if (!it.hasNext()) {
                                    observer.onComplete();
                                    this.k = true;
                                }
                            } catch (Throwable th) {
                                Exceptions.throwIfFatal(th);
                                observer.onError(th);
                                this.k = true;
                            }
                        }
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    observer.onError(th2);
                    this.k = true;
                }
            }
            clear();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public void clear() {
            this.i = null;
            AutoCloseable autoCloseable = this.j;
            this.j = null;
            if (autoCloseable != null) {
                ObservableFromStream.d(autoCloseable);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.k = true;
            a();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.k;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean isEmpty() {
            Iterator<T> it = this.i;
            if (it != null) {
                if (!this.l || it.hasNext()) {
                    return false;
                }
                clear();
                return true;
            }
            return true;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean offer(@NonNull T t) {
            throw new UnsupportedOperationException();
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        @Nullable
        public T poll() {
            Iterator<T> it = this.i;
            if (it == null) {
                return null;
            }
            if (!this.l) {
                this.l = true;
            } else if (!it.hasNext()) {
                clear();
                return null;
            }
            T next = this.i.next();
            Objects.requireNonNull(next, "The Stream's Iterator.next() returned a null value");
            return next;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.QueueFuseable
        public int requestFusion(int i) {
            if ((i & 1) != 0) {
                this.m = true;
                return 1;
            }
            return 0;
        }

        @Override // io.reactivex.rxjava3.internal.fuseable.SimpleQueue
        public boolean offer(@NonNull T t, @NonNull T t2) {
            throw new UnsupportedOperationException();
        }
    }

    public ObservableFromStream(Stream<T> stream) {
        this.h = stream;
    }

    public static void d(AutoCloseable autoCloseable) {
        try {
            autoCloseable.close();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
        }
    }

    public static <T> void subscribeStream(Observer<? super T> observer, Stream<T> stream) {
        try {
            Iterator<T> it = stream.iterator();
            if (!it.hasNext()) {
                EmptyDisposable.complete(observer);
                d(stream);
                return;
            }
            a aVar = new a(observer, it, stream);
            observer.onSubscribe(aVar);
            aVar.a();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptyDisposable.error(th, observer);
            d(stream);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        subscribeStream(observer, this.h);
    }
}
