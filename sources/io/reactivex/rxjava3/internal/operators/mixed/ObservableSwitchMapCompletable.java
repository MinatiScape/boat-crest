package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableSwitchMapCompletable<T> extends Completable {
    public final Observable<T> h;
    public final Function<? super T, ? extends CompletableSource> i;
    public final boolean j;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public static final C0839a o = new C0839a(null);
        public final CompletableObserver h;
        public final Function<? super T, ? extends CompletableSource> i;
        public final boolean j;
        public final AtomicThrowable k = new AtomicThrowable();
        public final AtomicReference<C0839a> l = new AtomicReference<>();
        public volatile boolean m;
        public Disposable n;

        /* renamed from: io.reactivex.rxjava3.internal.operators.mixed.ObservableSwitchMapCompletable$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0839a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -8003404460084760287L;
            public final a<?> parent;

            public C0839a(a<?> aVar) {
                this.parent = aVar;
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                this.parent.b(this);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                this.parent.c(this, th);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }
        }

        public a(CompletableObserver completableObserver, Function<? super T, ? extends CompletableSource> function, boolean z) {
            this.h = completableObserver;
            this.i = function;
            this.j = z;
        }

        public void a() {
            AtomicReference<C0839a> atomicReference = this.l;
            C0839a c0839a = o;
            C0839a andSet = atomicReference.getAndSet(c0839a);
            if (andSet == null || andSet == c0839a) {
                return;
            }
            andSet.dispose();
        }

        public void b(C0839a c0839a) {
            if (this.l.compareAndSet(c0839a, null) && this.m) {
                this.k.tryTerminateConsumer(this.h);
            }
        }

        public void c(C0839a c0839a, Throwable th) {
            if (this.l.compareAndSet(c0839a, null)) {
                if (this.k.tryAddThrowableOrReport(th)) {
                    if (this.j) {
                        if (this.m) {
                            this.k.tryTerminateConsumer(this.h);
                            return;
                        }
                        return;
                    }
                    this.n.dispose();
                    a();
                    this.k.tryTerminateConsumer(this.h);
                    return;
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.n.dispose();
            a();
            this.k.tryTerminateAndReport();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.l.get() == o;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            this.m = true;
            if (this.l.get() == null) {
                this.k.tryTerminateConsumer(this.h);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (this.k.tryAddThrowableOrReport(th)) {
                if (this.j) {
                    onComplete();
                    return;
                }
                a();
                this.k.tryTerminateConsumer(this.h);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            C0839a c0839a;
            try {
                CompletableSource apply = this.i.apply(t);
                Objects.requireNonNull(apply, "The mapper returned a null CompletableSource");
                CompletableSource completableSource = apply;
                C0839a c0839a2 = new C0839a(this);
                do {
                    c0839a = this.l.get();
                    if (c0839a == o) {
                        return;
                    }
                } while (!this.l.compareAndSet(c0839a, c0839a2));
                if (c0839a != null) {
                    c0839a.dispose();
                }
                completableSource.subscribe(c0839a2);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.n.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.n, disposable)) {
                this.n = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableSwitchMapCompletable(Observable<T> observable, Function<? super T, ? extends CompletableSource> function, boolean z) {
        this.h = observable;
        this.i = function;
        this.j = z;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        if (io.reactivex.rxjava3.internal.operators.mixed.a.a(this.h, this.i, completableObserver)) {
            return;
        }
        this.h.subscribe(new a(completableObserver, this.i, this.j));
    }
}
