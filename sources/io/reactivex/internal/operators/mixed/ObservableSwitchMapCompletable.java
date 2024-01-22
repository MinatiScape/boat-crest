package io.reactivex.internal.operators.mixed;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableSwitchMapCompletable<T> extends Completable {
    public final Observable<T> h;
    public final Function<? super T, ? extends CompletableSource> i;
    public final boolean j;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Observer<T>, Disposable {
        public static final C0774a o = new C0774a(null);
        public final CompletableObserver h;
        public final Function<? super T, ? extends CompletableSource> i;
        public final boolean j;
        public final AtomicThrowable k = new AtomicThrowable();
        public final AtomicReference<C0774a> l = new AtomicReference<>();
        public volatile boolean m;
        public Disposable n;

        /* renamed from: io.reactivex.internal.operators.mixed.ObservableSwitchMapCompletable$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0774a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -8003404460084760287L;
            public final a<?> parent;

            public C0774a(a<?> aVar) {
                this.parent = aVar;
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
            public void onComplete() {
                this.parent.b(this);
            }

            @Override // io.reactivex.CompletableObserver
            public void onError(Throwable th) {
                this.parent.c(this, th);
            }

            @Override // io.reactivex.CompletableObserver
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
            AtomicReference<C0774a> atomicReference = this.l;
            C0774a c0774a = o;
            C0774a andSet = atomicReference.getAndSet(c0774a);
            if (andSet == null || andSet == c0774a) {
                return;
            }
            andSet.dispose();
        }

        public void b(C0774a c0774a) {
            if (this.l.compareAndSet(c0774a, null) && this.m) {
                Throwable terminate = this.k.terminate();
                if (terminate == null) {
                    this.h.onComplete();
                } else {
                    this.h.onError(terminate);
                }
            }
        }

        public void c(C0774a c0774a, Throwable th) {
            if (this.l.compareAndSet(c0774a, null) && this.k.addThrowable(th)) {
                if (this.j) {
                    if (this.m) {
                        this.h.onError(this.k.terminate());
                        return;
                    }
                    return;
                }
                dispose();
                Throwable terminate = this.k.terminate();
                if (terminate != ExceptionHelper.TERMINATED) {
                    this.h.onError(terminate);
                    return;
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.n.dispose();
            a();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.l.get() == o;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.m = true;
            if (this.l.get() == null) {
                Throwable terminate = this.k.terminate();
                if (terminate == null) {
                    this.h.onComplete();
                } else {
                    this.h.onError(terminate);
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.k.addThrowable(th)) {
                if (this.j) {
                    onComplete();
                    return;
                }
                a();
                Throwable terminate = this.k.terminate();
                if (terminate != ExceptionHelper.TERMINATED) {
                    this.h.onError(terminate);
                    return;
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            C0774a c0774a;
            try {
                CompletableSource completableSource = (CompletableSource) ObjectHelper.requireNonNull(this.i.apply(t), "The mapper returned a null CompletableSource");
                C0774a c0774a2 = new C0774a(this);
                do {
                    c0774a = this.l.get();
                    if (c0774a == o) {
                        return;
                    }
                } while (!this.l.compareAndSet(c0774a, c0774a2));
                if (c0774a != null) {
                    c0774a.dispose();
                }
                completableSource.subscribe(c0774a2);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.n.dispose();
                onError(th);
            }
        }

        @Override // io.reactivex.Observer
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

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        if (io.reactivex.internal.operators.mixed.a.a(this.h, this.i, completableObserver)) {
            return;
        }
        this.h.subscribe(new a(completableObserver, this.i, this.j));
    }
}
