package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableSwitchMapMaybe<T, R> extends Observable<R> {
    public final Observable<T> h;
    public final Function<? super T, ? extends MaybeSource<? extends R>> i;
    public final boolean j;

    /* loaded from: classes12.dex */
    public static final class a<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        public static final C0840a<Object> INNER_DISPOSED = new C0840a<>(null);
        private static final long serialVersionUID = -5402190102429853762L;
        public volatile boolean cancelled;
        public final boolean delayErrors;
        public volatile boolean done;
        public final Observer<? super R> downstream;
        public final AtomicThrowable errors = new AtomicThrowable();
        public final AtomicReference<C0840a<R>> inner = new AtomicReference<>();
        public final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        public Disposable upstream;

        /* renamed from: io.reactivex.rxjava3.internal.operators.mixed.ObservableSwitchMapMaybe$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0840a<R> extends AtomicReference<Disposable> implements MaybeObserver<R> {
            private static final long serialVersionUID = 8042919737683345351L;
            public volatile R item;
            public final a<?, R> parent;

            public C0840a(a<?, R> aVar) {
                this.parent = aVar;
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                this.parent.innerComplete(this);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                this.parent.innerError(this, th);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver, io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            @Override // io.reactivex.rxjava3.core.MaybeObserver, io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(R r) {
                this.item = r;
                this.parent.drain();
            }
        }

        public a(Observer<? super R> observer, Function<? super T, ? extends MaybeSource<? extends R>> function, boolean z) {
            this.downstream = observer;
            this.mapper = function;
            this.delayErrors = z;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            disposeInner();
            this.errors.tryTerminateAndReport();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void disposeInner() {
            C0840a<Object> c0840a = INNER_DISPOSED;
            C0840a<Object> c0840a2 = (C0840a) this.inner.getAndSet(c0840a);
            if (c0840a2 == null || c0840a2 == c0840a) {
                return;
            }
            c0840a2.dispose();
        }

        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Observer<? super R> observer = this.downstream;
            AtomicThrowable atomicThrowable = this.errors;
            AtomicReference<C0840a<R>> atomicReference = this.inner;
            int i = 1;
            while (!this.cancelled) {
                if (atomicThrowable.get() != null && !this.delayErrors) {
                    atomicThrowable.tryTerminateConsumer(observer);
                    return;
                }
                boolean z = this.done;
                C0840a<R> c0840a = atomicReference.get();
                boolean z2 = c0840a == null;
                if (z && z2) {
                    atomicThrowable.tryTerminateConsumer(observer);
                    return;
                } else if (!z2 && c0840a.item != null) {
                    atomicReference.compareAndSet(c0840a, null);
                    observer.onNext((R) c0840a.item);
                } else {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }

        public void innerComplete(C0840a<R> c0840a) {
            if (this.inner.compareAndSet(c0840a, null)) {
                drain();
            }
        }

        public void innerError(C0840a<R> c0840a, Throwable th) {
            if (this.inner.compareAndSet(c0840a, null)) {
                if (this.errors.tryAddThrowableOrReport(th)) {
                    if (!this.delayErrors) {
                        this.upstream.dispose();
                        disposeInner();
                    }
                    drain();
                    return;
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.errors.tryAddThrowableOrReport(th)) {
                if (!this.delayErrors) {
                    disposeInner();
                }
                this.done = true;
                drain();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            C0840a<R> c0840a;
            C0840a<R> c0840a2 = this.inner.get();
            if (c0840a2 != null) {
                c0840a2.dispose();
            }
            try {
                MaybeSource<? extends R> apply = this.mapper.apply(t);
                Objects.requireNonNull(apply, "The mapper returned a null MaybeSource");
                MaybeSource<? extends R> maybeSource = apply;
                C0840a<R> c0840a3 = new C0840a<>(this);
                do {
                    c0840a = this.inner.get();
                    if (c0840a == INNER_DISPOSED) {
                        return;
                    }
                } while (!this.inner.compareAndSet(c0840a, c0840a3));
                maybeSource.subscribe(c0840a3);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.dispose();
                this.inner.getAndSet(INNER_DISPOSED);
                onError(th);
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

    public ObservableSwitchMapMaybe(Observable<T> observable, Function<? super T, ? extends MaybeSource<? extends R>> function, boolean z) {
        this.h = observable;
        this.i = function;
        this.j = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super R> observer) {
        if (io.reactivex.rxjava3.internal.operators.mixed.a.b(this.h, this.i, observer)) {
            return;
        }
        this.h.subscribe(new a(observer, this.i, this.j));
    }
}
