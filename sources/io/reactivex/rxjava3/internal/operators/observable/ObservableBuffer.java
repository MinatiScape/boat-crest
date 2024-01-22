package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public final class ObservableBuffer<T, U extends Collection<? super T>> extends io.reactivex.rxjava3.internal.operators.observable.a<T, U> {
    public final int h;
    public final int i;
    public final Supplier<U> j;

    /* loaded from: classes12.dex */
    public static final class a<T, U extends Collection<? super T>> implements Observer<T>, Disposable {
        public final Observer<? super U> h;
        public final int i;
        public final Supplier<U> j;
        public U k;
        public int l;
        public Disposable m;

        public a(Observer<? super U> observer, int i, Supplier<U> supplier) {
            this.h = observer;
            this.i = i;
            this.j = supplier;
        }

        public boolean a() {
            try {
                U u = this.j.get();
                Objects.requireNonNull(u, "Empty buffer supplied");
                this.k = u;
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

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.m.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.m.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
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

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.k = null;
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
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

        @Override // io.reactivex.rxjava3.core.Observer
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
        public final Supplier<U> bufferSupplier;
        public final ArrayDeque<U> buffers = new ArrayDeque<>();
        public final int count;
        public final Observer<? super U> downstream;
        public long index;
        public final int skip;
        public Disposable upstream;

        public b(Observer<? super U> observer, int i, int i2, Supplier<U> supplier) {
            this.downstream = observer;
            this.count = i;
            this.skip = i2;
            this.bufferSupplier = supplier;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            while (!this.buffers.isEmpty()) {
                this.downstream.onNext(this.buffers.poll());
            }
            this.downstream.onComplete();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.buffers.clear();
            this.downstream.onError(th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            long j = this.index;
            this.index = 1 + j;
            if (j % this.skip == 0) {
                try {
                    this.buffers.offer((Collection) ExceptionHelper.nullCheck(this.bufferSupplier.get(), "The bufferSupplier returned a null Collection."));
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
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

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }
    }

    public ObservableBuffer(ObservableSource<T> observableSource, int i, int i2, Supplier<U> supplier) {
        super(observableSource);
        this.h = i;
        this.i = i2;
        this.j = supplier;
    }

    @Override // io.reactivex.rxjava3.core.Observable
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
