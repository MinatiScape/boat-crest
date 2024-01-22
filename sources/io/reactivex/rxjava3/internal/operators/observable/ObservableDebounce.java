package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.observers.SerializedObserver;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableDebounce<T, U> extends io.reactivex.rxjava3.internal.operators.observable.a<T, T> {
    public final Function<? super T, ? extends ObservableSource<U>> h;

    /* loaded from: classes12.dex */
    public static final class a<T, U> implements Observer<T>, Disposable {
        public final Observer<? super T> h;
        public final Function<? super T, ? extends ObservableSource<U>> i;
        public Disposable j;
        public final AtomicReference<Disposable> k = new AtomicReference<>();
        public volatile long l;
        public boolean m;

        /* renamed from: io.reactivex.rxjava3.internal.operators.observable.ObservableDebounce$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0846a<T, U> extends DisposableObserver<U> {
            public final a<T, U> i;
            public final long j;
            public final T k;
            public boolean l;
            public final AtomicBoolean m = new AtomicBoolean();

            public C0846a(a<T, U> aVar, long j, T t) {
                this.i = aVar;
                this.j = j;
                this.k = t;
            }

            public void a() {
                if (this.m.compareAndSet(false, true)) {
                    this.i.a(this.j, this.k);
                }
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
                if (this.l) {
                    return;
                }
                this.l = true;
                a();
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(Throwable th) {
                if (this.l) {
                    RxJavaPlugins.onError(th);
                    return;
                }
                this.l = true;
                this.i.onError(th);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onNext(U u) {
                if (this.l) {
                    return;
                }
                this.l = true;
                dispose();
                a();
            }
        }

        public a(Observer<? super T> observer, Function<? super T, ? extends ObservableSource<U>> function) {
            this.h = observer;
            this.i = function;
        }

        public void a(long j, T t) {
            if (j == this.l) {
                this.h.onNext(t);
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.j.dispose();
            DisposableHelper.dispose(this.k);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.j.isDisposed();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.m) {
                return;
            }
            this.m = true;
            Disposable disposable = this.k.get();
            if (disposable != DisposableHelper.DISPOSED) {
                C0846a c0846a = (C0846a) disposable;
                if (c0846a != null) {
                    c0846a.a();
                }
                DisposableHelper.dispose(this.k);
                this.h.onComplete();
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            DisposableHelper.dispose(this.k);
            this.h.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.m) {
                return;
            }
            long j = this.l + 1;
            this.l = j;
            Disposable disposable = this.k.get();
            if (disposable != null) {
                disposable.dispose();
            }
            try {
                ObservableSource<U> apply = this.i.apply(t);
                Objects.requireNonNull(apply, "The ObservableSource supplied is null");
                ObservableSource<U> observableSource = apply;
                C0846a c0846a = new C0846a(this, j, t);
                if (this.k.compareAndSet(disposable, c0846a)) {
                    observableSource.subscribe(c0846a);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                dispose();
                this.h.onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.j, disposable)) {
                this.j = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableDebounce(ObservableSource<T> observableSource, Function<? super T, ? extends ObservableSource<U>> function) {
        super(observableSource);
        this.h = function;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new a(new SerializedObserver(observer), this.h));
    }
}
