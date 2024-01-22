package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableDebounceTimed<T> extends io.reactivex.internal.operators.observable.a<T, T> {
    public final long h;
    public final TimeUnit i;
    public final Scheduler j;

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 6812032969491025141L;
        public final long idx;
        public final AtomicBoolean once = new AtomicBoolean();
        public final b<T> parent;
        public final T value;

        public a(T t, long j, b<T> bVar) {
            this.value = t;
            this.idx = j;
            this.parent = bVar;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.a(this.idx, this.value, this);
            }
        }

        public void setResource(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> implements Observer<T>, Disposable {
        public final Observer<? super T> h;
        public final long i;
        public final TimeUnit j;
        public final Scheduler.Worker k;
        public Disposable l;
        public Disposable m;
        public volatile long n;
        public boolean o;

        public b(Observer<? super T> observer, long j, TimeUnit timeUnit, Scheduler.Worker worker) {
            this.h = observer;
            this.i = j;
            this.j = timeUnit;
            this.k = worker;
        }

        public void a(long j, T t, a<T> aVar) {
            if (j == this.n) {
                this.h.onNext(t);
                aVar.dispose();
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.l.dispose();
            this.k.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.k.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.o) {
                return;
            }
            this.o = true;
            Disposable disposable = this.m;
            if (disposable != null) {
                disposable.dispose();
            }
            a aVar = (a) disposable;
            if (aVar != null) {
                aVar.run();
            }
            this.h.onComplete();
            this.k.dispose();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (this.o) {
                RxJavaPlugins.onError(th);
                return;
            }
            Disposable disposable = this.m;
            if (disposable != null) {
                disposable.dispose();
            }
            this.o = true;
            this.h.onError(th);
            this.k.dispose();
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.o) {
                return;
            }
            long j = this.n + 1;
            this.n = j;
            Disposable disposable = this.m;
            if (disposable != null) {
                disposable.dispose();
            }
            a aVar = new a(t, j, this);
            this.m = aVar;
            aVar.setResource(this.k.schedule(aVar, this.i, this.j));
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.l, disposable)) {
                this.l = disposable;
                this.h.onSubscribe(this);
            }
        }
    }

    public ObservableDebounceTimed(ObservableSource<T> observableSource, long j, TimeUnit timeUnit, Scheduler scheduler) {
        super(observableSource);
        this.h = j;
        this.i = timeUnit;
        this.j = scheduler;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new b(new SerializedObserver(observer), this.h, this.i, this.j.createWorker()));
    }
}
