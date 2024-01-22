package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class ObservableBuffer<T, U extends Collection<? super T>> extends io.reactivex.internal.operators.observable.a<T, U> {
    public final int h;
    public final int i;
    public final Callable<U> j;

    /* loaded from: classes12.dex */
    public static final class a<T, U extends Collection<? super T>> implements Observer<T>, Disposable {
        public final Observer<? super U> h;
        public final int i;
        public final Callable<U> j;
        public U k;
        public int l;
        public Disposable m;

        public a(Observer<? super U> observer, int i, Callable<U> callable) {
            this.h = observer;
            this.i = i;
            this.j = callable;
        }

        public boolean a() {
            try {
                this.k = (U) ObjectHelper.requireNonNull(this.j.call(), "Empty buffer supplied");
                return true;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.k = null;
                Disposable disposable = this.m;
                if (disposable == null) {
                    EmptyDisposable.error(th, this.h);
                    return false;
                }
                disposable.dispose();
                this.h.onError(th);
                return false;
            }
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
            U u = this.k;
            if (u != null) {
                this.k = null;
                if (!u.isEmpty()) {
                    this.h.onNext(u);
                }
                this.h.onComplete();
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.k = null;
            this.h.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            U u = this.k;
            if (u != null) {
                u.add(t);
                int i = this.l + 1;
                this.l = i;
                if (i >= this.i) {
                    this.h.onNext(u);
                    this.l = 0;
                    a();
                }
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

    /* loaded from: classes12.dex */
    public static final class b<T, U extends Collection<? super T>> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = -8223395059921494546L;
        public final Callable<U> bufferSupplier;
        public final ArrayDeque<U> buffers = new ArrayDeque<>();
        public final int count;
        public final Observer<? super U> downstream;
        public long index;
        public final int skip;
        public Disposable upstream;

        public b(Observer<? super U> observer, int i, int i2, Callable<U> callable) {
            this.downstream = observer;
            this.count = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            while (!this.buffers.isEmpty()) {
                this.downstream.onNext(this.buffers.poll());
            }
            this.downstream.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.buffers.clear();
            this.downstream.onError(th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.Observer
        public void onNext(T t) {
            long j = this.index;
            this.index = 1 + j;
            if (j % this.skip == 0) {
                try {
                    this.buffers.offer((Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources."));
                } catch (Throwable th) {
                    this.buffers.clear();
                    this.upstream.dispose();
                    this.downstream.onError(th);
                    return;
                }
            }
            Iterator<U> it = this.buffers.iterator();
            while (it.hasNext()) {
                U next = it.next();
                next.add(t);
                if (this.count <= next.size()) {
                    it.remove();
                    this.downstream.onNext(next);
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }
    }

    public ObservableBuffer(ObservableSource<T> observableSource, int i, int i2, Callable<U> callable) {
        super(observableSource);
        this.h = i;
        this.i = i2;
        this.j = callable;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super U> observer) {
        int i = this.i;
        int i2 = this.h;
        if (i == i2) {
            a aVar = new a(observer, i2, this.j);
            if (aVar.a()) {
                this.source.subscribe(aVar);
                return;
            }
            return;
        }
        this.source.subscribe(new b(observer, this.h, this.i, this.j));
    }
}
