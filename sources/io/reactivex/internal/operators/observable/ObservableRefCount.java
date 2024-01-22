package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableRefCount<T> extends Observable<T> {
    public final ConnectableObservable<T> h;
    public final int i;
    public final long j;
    public final TimeUnit k;
    public final Scheduler l;
    public a m;

    /* loaded from: classes12.dex */
    public static final class a extends AtomicReference<Disposable> implements Runnable, Consumer<Disposable> {
        private static final long serialVersionUID = -4552101107598366241L;
        public boolean connected;
        public boolean disconnectedEarly;
        public final ObservableRefCount<?> parent;
        public long subscriberCount;
        public Disposable timer;

        public a(ObservableRefCount<?> observableRefCount) {
            this.parent = observableRefCount;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.parent.h(this);
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(Disposable disposable) throws Exception {
            DisposableHelper.replace(this, disposable);
            synchronized (this.parent) {
                if (this.disconnectedEarly) {
                    ((ResettableConnectable) this.parent.h).resetIf(disposable);
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends AtomicBoolean implements Observer<T>, Disposable {
        private static final long serialVersionUID = -7419642935409022375L;
        public final a connection;
        public final Observer<? super T> downstream;
        public final ObservableRefCount<T> parent;
        public Disposable upstream;

        public b(Observer<? super T> observer, ObservableRefCount<T> observableRefCount, a aVar) {
            this.downstream = observer;
            this.parent = observableRefCount;
            this.connection = aVar;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
            if (compareAndSet(false, true)) {
                this.parent.d(this.connection);
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.parent.g(this.connection);
                this.downstream.onComplete();
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.parent.g(this.connection);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }
    }

    public ObservableRefCount(ConnectableObservable<T> connectableObservable) {
        this(connectableObservable, 1, 0L, TimeUnit.NANOSECONDS, null);
    }

    public void d(a aVar) {
        synchronized (this) {
            a aVar2 = this.m;
            if (aVar2 != null && aVar2 == aVar) {
                long j = aVar.subscriberCount - 1;
                aVar.subscriberCount = j;
                if (j == 0 && aVar.connected) {
                    if (this.j == 0) {
                        h(aVar);
                        return;
                    }
                    SequentialDisposable sequentialDisposable = new SequentialDisposable();
                    aVar.timer = sequentialDisposable;
                    sequentialDisposable.replace(this.l.scheduleDirect(aVar, this.j, this.k));
                }
            }
        }
    }

    public void e(a aVar) {
        Disposable disposable = aVar.timer;
        if (disposable != null) {
            disposable.dispose();
            aVar.timer = null;
        }
    }

    public void f(a aVar) {
        ConnectableObservable<T> connectableObservable = this.h;
        if (connectableObservable instanceof Disposable) {
            ((Disposable) connectableObservable).dispose();
        } else if (connectableObservable instanceof ResettableConnectable) {
            ((ResettableConnectable) connectableObservable).resetIf(aVar.get());
        }
    }

    public void g(a aVar) {
        synchronized (this) {
            if (this.h instanceof ObservablePublishClassic) {
                a aVar2 = this.m;
                if (aVar2 != null && aVar2 == aVar) {
                    this.m = null;
                    e(aVar);
                }
                long j = aVar.subscriberCount - 1;
                aVar.subscriberCount = j;
                if (j == 0) {
                    f(aVar);
                }
            } else {
                a aVar3 = this.m;
                if (aVar3 != null && aVar3 == aVar) {
                    e(aVar);
                    long j2 = aVar.subscriberCount - 1;
                    aVar.subscriberCount = j2;
                    if (j2 == 0) {
                        this.m = null;
                        f(aVar);
                    }
                }
            }
        }
    }

    public void h(a aVar) {
        synchronized (this) {
            if (aVar.subscriberCount == 0 && aVar == this.m) {
                this.m = null;
                Disposable disposable = aVar.get();
                DisposableHelper.dispose(aVar);
                ConnectableObservable<T> connectableObservable = this.h;
                if (connectableObservable instanceof Disposable) {
                    ((Disposable) connectableObservable).dispose();
                } else if (connectableObservable instanceof ResettableConnectable) {
                    if (disposable == null) {
                        aVar.disconnectedEarly = true;
                    } else {
                        ((ResettableConnectable) connectableObservable).resetIf(disposable);
                    }
                }
            }
        }
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        a aVar;
        boolean z;
        Disposable disposable;
        synchronized (this) {
            aVar = this.m;
            if (aVar == null) {
                aVar = new a(this);
                this.m = aVar;
            }
            long j = aVar.subscriberCount;
            if (j == 0 && (disposable = aVar.timer) != null) {
                disposable.dispose();
            }
            long j2 = j + 1;
            aVar.subscriberCount = j2;
            z = true;
            if (aVar.connected || j2 != this.i) {
                z = false;
            } else {
                aVar.connected = true;
            }
        }
        this.h.subscribe(new b(observer, this, aVar));
        if (z) {
            this.h.connect(aVar);
        }
    }

    public ObservableRefCount(ConnectableObservable<T> connectableObservable, int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.h = connectableObservable;
        this.i = i;
        this.j = j;
        this.k = timeUnit;
        this.l = scheduler;
    }
}
