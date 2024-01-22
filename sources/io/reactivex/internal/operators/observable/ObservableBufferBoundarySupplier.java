package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableBufferBoundarySupplier<T, U extends Collection<? super T>, B> extends io.reactivex.internal.operators.observable.a<T, U> {
    public final Callable<? extends ObservableSource<B>> h;
    public final Callable<U> i;

    /* loaded from: classes12.dex */
    public static final class a<T, U extends Collection<? super T>, B> extends DisposableObserver<B> {
        public final b<T, U, B> i;
        public boolean j;

        public a(b<T, U, B> bVar) {
            this.i = bVar;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.j) {
                return;
            }
            this.j = true;
            this.i.c();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.j) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.j = true;
            this.i.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(B b) {
            if (this.j) {
                return;
            }
            this.j = true;
            dispose();
            this.i.c();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, U extends Collection<? super T>, B> extends QueueDrainObserver<T, U, U> implements Observer<T> {
        public final Callable<U> i;
        public final Callable<? extends ObservableSource<B>> j;
        public Disposable k;
        public final AtomicReference<Disposable> l;
        public U m;

        public b(Observer<? super U> observer, Callable<U> callable, Callable<? extends ObservableSource<B>> callable2) {
            super(observer, new MpscLinkedQueue());
            this.l = new AtomicReference<>();
            this.i = callable;
            this.j = callable2;
        }

        @Override // io.reactivex.internal.observers.QueueDrainObserver, io.reactivex.internal.util.ObservableQueueDrain
        /* renamed from: a */
        public void accept(Observer<? super U> observer, U u) {
            this.downstream.onNext(u);
        }

        public void b() {
            DisposableHelper.dispose(this.l);
        }

        public void c() {
            try {
                U u = (U) ObjectHelper.requireNonNull(this.i.call(), "The buffer supplied is null");
                try {
                    ObservableSource observableSource = (ObservableSource) ObjectHelper.requireNonNull(this.j.call(), "The boundary ObservableSource supplied is null");
                    a aVar = new a(this);
                    if (DisposableHelper.replace(this.l, aVar)) {
                        synchronized (this) {
                            U u2 = this.m;
                            if (u2 == null) {
                                return;
                            }
                            this.m = u;
                            observableSource.subscribe(aVar);
                            fastPathEmit(u2, false, this);
                        }
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.cancelled = true;
                    this.k.dispose();
                    this.downstream.onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                dispose();
                this.downstream.onError(th2);
            }
        }

        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.k.dispose();
            b();
            if (enter()) {
                this.queue.clear();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            synchronized (this) {
                U u = this.m;
                if (u == null) {
                    return;
                }
                this.m = null;
                this.queue.offer(u);
                this.done = true;
                if (enter()) {
                    QueueDrainHelper.drainLoop(this.queue, this.downstream, false, this, this);
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            dispose();
            this.downstream.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            synchronized (this) {
                U u = this.m;
                if (u == null) {
                    return;
                }
                u.add(t);
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.k, disposable)) {
                this.k = disposable;
                Observer<? super V> observer = this.downstream;
                try {
                    this.m = (U) ObjectHelper.requireNonNull(this.i.call(), "The buffer supplied is null");
                    try {
                        ObservableSource observableSource = (ObservableSource) ObjectHelper.requireNonNull(this.j.call(), "The boundary ObservableSource supplied is null");
                        a aVar = new a(this);
                        this.l.set(aVar);
                        observer.onSubscribe(this);
                        if (this.cancelled) {
                            return;
                        }
                        observableSource.subscribe(aVar);
                    } catch (Throwable th) {
                        Exceptions.throwIfFatal(th);
                        this.cancelled = true;
                        disposable.dispose();
                        EmptyDisposable.error(th, observer);
                    }
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    this.cancelled = true;
                    disposable.dispose();
                    EmptyDisposable.error(th2, observer);
                }
            }
        }
    }

    public ObservableBufferBoundarySupplier(ObservableSource<T> observableSource, Callable<? extends ObservableSource<B>> callable, Callable<U> callable2) {
        super(observableSource);
        this.h = callable;
        this.i = callable2;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super U> observer) {
        this.source.subscribe(new b(new SerializedObserver(observer), this.i, this.h));
    }
}
