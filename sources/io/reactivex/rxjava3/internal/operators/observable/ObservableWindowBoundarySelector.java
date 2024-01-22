package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue;
import io.reactivex.rxjava3.internal.queue.MpscLinkedQueue;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.subjects.UnicastSubject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableWindowBoundarySelector<T, B, V> extends io.reactivex.rxjava3.internal.operators.observable.a<T, Observable<T>> {
    public final ObservableSource<B> h;
    public final Function<? super B, ? extends ObservableSource<V>> i;
    public final int j;

    /* loaded from: classes12.dex */
    public static final class a<T, B, V> extends AtomicInteger implements Observer<T>, Disposable, Runnable {
        private static final long serialVersionUID = 8646217640096099753L;
        public final int bufferSize;
        public final Function<? super B, ? extends ObservableSource<V>> closingIndicator;
        public final Observer<? super Observable<T>> downstream;
        public long emitted;
        public final ObservableSource<B> open;
        public volatile boolean openDone;
        public Disposable upstream;
        public volatile boolean upstreamCanceled;
        public volatile boolean upstreamDone;
        public final SimplePlainQueue<Object> queue = new MpscLinkedQueue();
        public final CompositeDisposable resources = new CompositeDisposable();
        public final List<UnicastSubject<T>> windows = new ArrayList();
        public final AtomicLong windowCount = new AtomicLong(1);
        public final AtomicBoolean downstreamDisposed = new AtomicBoolean();
        public final AtomicThrowable error = new AtomicThrowable();
        public final c<B> startObserver = new c<>(this);
        public final AtomicLong requested = new AtomicLong();

        /* renamed from: io.reactivex.rxjava3.internal.operators.observable.ObservableWindowBoundarySelector$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0860a<T, V> extends Observable<T> implements Observer<V>, Disposable {
            public final a<T, ?, V> h;
            public final UnicastSubject<T> i;
            public final AtomicReference<Disposable> j = new AtomicReference<>();
            public final AtomicBoolean k = new AtomicBoolean();

            public C0860a(a<T, ?, V> aVar, UnicastSubject<T> unicastSubject) {
                this.h = aVar;
                this.i = unicastSubject;
            }

            public boolean d() {
                return !this.k.get() && this.k.compareAndSet(false, true);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public void dispose() {
                DisposableHelper.dispose(this.j);
            }

            @Override // io.reactivex.rxjava3.disposables.Disposable
            public boolean isDisposed() {
                return this.j.get() == DisposableHelper.DISPOSED;
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
                this.h.close(this);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(Throwable th) {
                if (isDisposed()) {
                    RxJavaPlugins.onError(th);
                } else {
                    this.h.closeError(th);
                }
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onNext(V v) {
                if (DisposableHelper.dispose(this.j)) {
                    this.h.close(this);
                }
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this.j, disposable);
            }

            @Override // io.reactivex.rxjava3.core.Observable
            public void subscribeActual(Observer<? super T> observer) {
                this.i.subscribe(observer);
                this.k.set(true);
            }
        }

        /* loaded from: classes12.dex */
        public static final class b<B> {

            /* renamed from: a  reason: collision with root package name */
            public final B f13971a;

            public b(B b) {
                this.f13971a = b;
            }
        }

        /* loaded from: classes12.dex */
        public static final class c<B> extends AtomicReference<Disposable> implements Observer<B> {
            private static final long serialVersionUID = -3326496781427702834L;
            public final a<?, B, ?> parent;

            public c(a<?, B, ?> aVar) {
                this.parent = aVar;
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onComplete() {
                this.parent.openComplete();
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onError(Throwable th) {
                this.parent.openError(th);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onNext(B b) {
                this.parent.open(b);
            }

            @Override // io.reactivex.rxjava3.core.Observer
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }
        }

        public a(Observer<? super Observable<T>> observer, ObservableSource<B> observableSource, Function<? super B, ? extends ObservableSource<V>> function, int i) {
            this.downstream = observer;
            this.open = observableSource;
            this.closingIndicator = function;
            this.bufferSize = i;
        }

        public void close(C0860a<T, V> c0860a) {
            this.queue.offer(c0860a);
            drain();
        }

        public void closeError(Throwable th) {
            this.upstream.dispose();
            this.startObserver.dispose();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.downstreamDisposed.compareAndSet(false, true)) {
                if (this.windowCount.decrementAndGet() == 0) {
                    this.upstream.dispose();
                    this.startObserver.dispose();
                    this.resources.dispose();
                    this.error.tryTerminateAndReport();
                    this.upstreamCanceled = true;
                    drain();
                    return;
                }
                this.startObserver.dispose();
            }
        }

        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Observer<? super Observable<T>> observer = this.downstream;
            SimplePlainQueue<Object> simplePlainQueue = this.queue;
            List<UnicastSubject<T>> list = this.windows;
            int i = 1;
            while (true) {
                if (this.upstreamCanceled) {
                    simplePlainQueue.clear();
                    list.clear();
                } else {
                    boolean z = this.upstreamDone;
                    Object poll = simplePlainQueue.poll();
                    boolean z2 = poll == null;
                    if (z && (z2 || this.error.get() != null)) {
                        terminateDownstream(observer);
                        this.upstreamCanceled = true;
                    } else if (!z2) {
                        if (poll instanceof b) {
                            if (!this.downstreamDisposed.get()) {
                                try {
                                    ObservableSource<V> apply = this.closingIndicator.apply(((b) poll).f13971a);
                                    Objects.requireNonNull(apply, "The closingIndicator returned a null ObservableSource");
                                    ObservableSource<V> observableSource = apply;
                                    this.windowCount.getAndIncrement();
                                    UnicastSubject<T> create = UnicastSubject.create(this.bufferSize, this);
                                    C0860a c0860a = new C0860a(this, create);
                                    observer.onNext(c0860a);
                                    if (c0860a.d()) {
                                        create.onComplete();
                                    } else {
                                        list.add(create);
                                        this.resources.add(c0860a);
                                        observableSource.subscribe(c0860a);
                                    }
                                } catch (Throwable th) {
                                    Exceptions.throwIfFatal(th);
                                    this.upstream.dispose();
                                    this.startObserver.dispose();
                                    this.resources.dispose();
                                    Exceptions.throwIfFatal(th);
                                    this.error.tryAddThrowableOrReport(th);
                                    this.upstreamDone = true;
                                }
                            }
                        } else if (poll instanceof C0860a) {
                            UnicastSubject<T> unicastSubject = ((C0860a) poll).i;
                            list.remove(unicastSubject);
                            this.resources.delete((Disposable) poll);
                            unicastSubject.onComplete();
                        } else {
                            for (UnicastSubject<T> unicastSubject2 : list) {
                                unicastSubject2.onNext(poll);
                            }
                        }
                    } else if (this.openDone && list.size() == 0) {
                        this.upstream.dispose();
                        this.startObserver.dispose();
                        this.resources.dispose();
                        terminateDownstream(observer);
                        this.upstreamCanceled = true;
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.downstreamDisposed.get();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.startObserver.dispose();
            this.resources.dispose();
            this.upstreamDone = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.startObserver.dispose();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
                this.open.subscribe(this.startObserver);
            }
        }

        public void open(B b2) {
            this.queue.offer(new b(b2));
            drain();
        }

        public void openComplete() {
            this.openDone = true;
            drain();
        }

        public void openError(Throwable th) {
            this.upstream.dispose();
            this.resources.dispose();
            if (this.error.tryAddThrowableOrReport(th)) {
                this.upstreamDone = true;
                drain();
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.windowCount.decrementAndGet() == 0) {
                this.upstream.dispose();
                this.startObserver.dispose();
                this.resources.dispose();
                this.error.tryTerminateAndReport();
                this.upstreamCanceled = true;
                drain();
            }
        }

        public void terminateDownstream(Observer<?> observer) {
            Throwable terminate = this.error.terminate();
            if (terminate == null) {
                for (UnicastSubject<T> unicastSubject : this.windows) {
                    unicastSubject.onComplete();
                }
                observer.onComplete();
            } else if (terminate != ExceptionHelper.TERMINATED) {
                for (UnicastSubject<T> unicastSubject2 : this.windows) {
                    unicastSubject2.onError(terminate);
                }
                observer.onError(terminate);
            }
        }
    }

    public ObservableWindowBoundarySelector(ObservableSource<T> observableSource, ObservableSource<B> observableSource2, Function<? super B, ? extends ObservableSource<V>> function, int i) {
        super(observableSource);
        this.h = observableSource2;
        this.i = function;
        this.j = i;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super Observable<T>> observer) {
        this.source.subscribe(new a(observer, this.h, this.i, this.j));
    }
}
