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
import java.util.Collection;
import java.util.concurrent.Callable;
/* loaded from: classes12.dex */
public final class ObservableBufferExactBoundary<T, U extends Collection<? super T>, B> extends io.reactivex.internal.operators.observable.a<T, U> {
    public final ObservableSource<B> h;
    public final Callable<U> i;

    /* loaded from: classes12.dex */
    public static final class a<T, U extends Collection<? super T>, B> extends DisposableObserver<B> {
        public final b<T, U, B> i;

        public a(b<T, U, B> bVar) {
            this.i = bVar;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.i.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.i.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(B b) {
            this.i.b();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, U extends Collection<? super T>, B> extends QueueDrainObserver<T, U, U> implements Observer<T> {
        public final Callable<U> i;
        public final ObservableSource<B> j;
        public Disposable k;
        public Disposable l;
        public U m;

        public b(Observer<? super U> observer, Callable<U> callable, ObservableSource<B> observableSource) {
            super(observer, new MpscLinkedQueue());
            this.i = callable;
            this.j = observableSource;
        }

        @Override // io.reactivex.internal.observers.QueueDrainObserver, io.reactivex.internal.util.ObservableQueueDrain
        /* renamed from: a */
        public void accept(Observer<? super U> observer, U u) {
            this.downstream.onNext(u);
        }

        public void b() {
            try {
                U u = (U) ObjectHelper.requireNonNull(this.i.call(), "The buffer supplied is null");
                synchronized (this) {
                    U u2 = this.m;
                    if (u2 == null) {
                        return;
                    }
                    this.m = u;
                    fastPathEmit(u2, false, this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                dispose();
                this.downstream.onError(th);
            }
        }

        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.l.dispose();
            this.k.dispose();
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
                try {
                    this.m = (U) ObjectHelper.requireNonNull(this.i.call(), "The buffer supplied is null");
                    a aVar = new a(this);
                    this.l = aVar;
                    this.downstream.onSubscribe(this);
                    if (this.cancelled) {
                        return;
                    }
                    this.j.subscribe(aVar);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.cancelled = true;
                    disposable.dispose();
                    EmptyDisposable.error(th, this.downstream);
                }
            }
        }
    }

    public ObservableBufferExactBoundary(ObservableSource<T> observableSource, ObservableSource<B> observableSource2, Callable<U> callable) {
        super(observableSource);
        this.h = observableSource2;
        this.i = callable;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super U> observer) {
        this.source.subscribe(new b(new SerializedObserver(observer), this.i, this.h));
    }
}
