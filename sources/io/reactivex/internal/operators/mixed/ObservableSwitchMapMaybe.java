package io.reactivex.internal.operators.mixed;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableSwitchMapMaybe<T, R> extends Observable<R> {
    public final Observable<T> h;
    public final Function<? super T, ? extends MaybeSource<? extends R>> i;
    public final boolean j;

    /* loaded from: classes12.dex */
    public static final class a<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        public static final C0775a<Object> INNER_DISPOSED = new C0775a<>(null);
        private static final long serialVersionUID = -5402190102429853762L;
        public volatile boolean cancelled;
        public final boolean delayErrors;
        public volatile boolean done;
        public final Observer<? super R> downstream;
        public final AtomicThrowable errors = new AtomicThrowable();
        public final AtomicReference<C0775a<R>> inner = new AtomicReference<>();
        public final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        public Disposable upstream;

        /* renamed from: io.reactivex.internal.operators.mixed.ObservableSwitchMapMaybe$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0775a<R> extends AtomicReference<Disposable> implements MaybeObserver<R> {
            private static final long serialVersionUID = 8042919737683345351L;
            public volatile R item;
            public final a<?, R> parent;

            public C0775a(a<?, R> aVar) {
                this.parent = aVar;
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // io.reactivex.MaybeObserver
            public void onComplete() {
                this.parent.innerComplete(this);
            }

            @Override // io.reactivex.MaybeObserver
            public void onError(Throwable th) {
                this.parent.innerError(this, th);
            }

            @Override // io.reactivex.MaybeObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }

            @Override // io.reactivex.MaybeObserver
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

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            disposeInner();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void disposeInner() {
            C0775a<Object> c0775a = INNER_DISPOSED;
            C0775a<Object> c0775a2 = (C0775a) this.inner.getAndSet(c0775a);
            if (c0775a2 == null || c0775a2 == c0775a) {
                return;
            }
            c0775a2.dispose();
        }

        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Observer<? super R> observer = this.downstream;
            AtomicThrowable atomicThrowable = this.errors;
            AtomicReference<C0775a<R>> atomicReference = this.inner;
            int i = 1;
            while (!this.cancelled) {
                if (atomicThrowable.get() != null && !this.delayErrors) {
                    observer.onError(atomicThrowable.terminate());
                    return;
                }
                boolean z = this.done;
                C0775a<R> c0775a = atomicReference.get();
                boolean z2 = c0775a == null;
                if (z && z2) {
                    Throwable terminate = atomicThrowable.terminate();
                    if (terminate != null) {
                        observer.onError(terminate);
                        return;
                    } else {
                        observer.onComplete();
                        return;
                    }
                } else if (!z2 && c0775a.item != null) {
                    atomicReference.compareAndSet(c0775a, null);
                    observer.onNext((R) c0775a.item);
                } else {
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }

        public void innerComplete(C0775a<R> c0775a) {
            if (this.inner.compareAndSet(c0775a, null)) {
                drain();
            }
        }

        public void innerError(C0775a<R> c0775a, Throwable th) {
            if (this.inner.compareAndSet(c0775a, null) && this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    this.upstream.dispose();
                    disposeInner();
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.errors.addThrowable(th)) {
                if (!this.delayErrors) {
                    disposeInner();
                }
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.Observer
        public void onNext(T t) {
            C0775a<R> c0775a;
            C0775a<R> c0775a2 = this.inner.get();
            if (c0775a2 != null) {
                c0775a2.dispose();
            }
            try {
                MaybeSource maybeSource = (MaybeSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                C0775a<R> c0775a3 = new C0775a<>(this);
                do {
                    c0775a = this.inner.get();
                    if (c0775a == INNER_DISPOSED) {
                        return;
                    }
                } while (!this.inner.compareAndSet(c0775a, c0775a3));
                maybeSource.subscribe(c0775a3);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.upstream.dispose();
                this.inner.getAndSet(INNER_DISPOSED);
                onError(th);
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

    public ObservableSwitchMapMaybe(Observable<T> observable, Function<? super T, ? extends MaybeSource<? extends R>> function, boolean z) {
        this.h = observable;
        this.i = function;
        this.j = z;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super R> observer) {
        if (io.reactivex.internal.operators.mixed.a.b(this.h, this.i, observer)) {
            return;
        }
        this.h.subscribe(new a(observer, this.i, this.j));
    }
}
