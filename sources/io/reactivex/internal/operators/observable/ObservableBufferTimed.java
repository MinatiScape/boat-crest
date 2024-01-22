package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.observers.SerializedObserver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableBufferTimed<T, U extends Collection<? super T>> extends io.reactivex.internal.operators.observable.a<T, U> {
    public final long h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;
    public final Callable<U> l;
    public final int m;
    public final boolean n;

    /* loaded from: classes12.dex */
    public static final class a<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        public final Callable<U> i;
        public final long j;
        public final TimeUnit k;
        public final int l;
        public final boolean m;
        public final Scheduler.Worker n;
        public U o;
        public Disposable p;
        public Disposable q;
        public long r;
        public long s;

        public a(Observer<? super U> observer, Callable<U> callable, long j, TimeUnit timeUnit, int i, boolean z, Scheduler.Worker worker) {
            super(observer, new MpscLinkedQueue());
            this.i = callable;
            this.j = j;
            this.k = timeUnit;
            this.l = i;
            this.m = z;
            this.n = worker;
        }

        @Override // io.reactivex.internal.observers.QueueDrainObserver, io.reactivex.internal.util.ObservableQueueDrain
        /* renamed from: a */
        public void accept(Observer<? super U> observer, U u) {
            observer.onNext(u);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.q.dispose();
            this.n.dispose();
            synchronized (this) {
                this.o = null;
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            U u;
            this.n.dispose();
            synchronized (this) {
                u = this.o;
                this.o = null;
            }
            if (u != null) {
                this.queue.offer(u);
                this.done = true;
                if (enter()) {
                    QueueDrainHelper.drainLoop(this.queue, this.downstream, false, this, this);
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                this.o = null;
            }
            this.downstream.onError(th);
            this.n.dispose();
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            synchronized (this) {
                U u = this.o;
                if (u == null) {
                    return;
                }
                u.add(t);
                if (u.size() < this.l) {
                    return;
                }
                this.o = null;
                this.r++;
                if (this.m) {
                    this.p.dispose();
                }
                fastPathOrderedEmit(u, false, this);
                try {
                    U u2 = (U) ObjectHelper.requireNonNull(this.i.call(), "The buffer supplied is null");
                    synchronized (this) {
                        this.o = u2;
                        this.s++;
                    }
                    if (this.m) {
                        Scheduler.Worker worker = this.n;
                        long j = this.j;
                        this.p = worker.schedulePeriodically(this, j, j, this.k);
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.downstream.onError(th);
                    dispose();
                }
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.q, disposable)) {
                this.q = disposable;
                try {
                    this.o = (U) ObjectHelper.requireNonNull(this.i.call(), "The buffer supplied is null");
                    this.downstream.onSubscribe(this);
                    Scheduler.Worker worker = this.n;
                    long j = this.j;
                    this.p = worker.schedulePeriodically(this, j, j, this.k);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    disposable.dispose();
                    EmptyDisposable.error(th, this.downstream);
                    this.n.dispose();
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                U u = (U) ObjectHelper.requireNonNull(this.i.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    U u2 = this.o;
                    if (u2 != null && this.r == this.s) {
                        this.o = u;
                        fastPathOrderedEmit(u2, false, this);
                    }
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                dispose();
                this.downstream.onError(th);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        public final Callable<U> i;
        public final long j;
        public final TimeUnit k;
        public final Scheduler l;
        public Disposable m;
        public U n;
        public final AtomicReference<Disposable> o;

        public b(Observer<? super U> observer, Callable<U> callable, long j, TimeUnit timeUnit, Scheduler scheduler) {
            super(observer, new MpscLinkedQueue());
            this.o = new AtomicReference<>();
            this.i = callable;
            this.j = j;
            this.k = timeUnit;
            this.l = scheduler;
        }

        @Override // io.reactivex.internal.observers.QueueDrainObserver, io.reactivex.internal.util.ObservableQueueDrain
        /* renamed from: a */
        public void accept(Observer<? super U> observer, U u) {
            this.downstream.onNext(u);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.o);
            this.m.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.o.get() == DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            U u;
            synchronized (this) {
                u = this.n;
                this.n = null;
            }
            if (u != null) {
                this.queue.offer(u);
                this.done = true;
                if (enter()) {
                    QueueDrainHelper.drainLoop(this.queue, this.downstream, false, null, this);
                }
            }
            DisposableHelper.dispose(this.o);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                this.n = null;
            }
            this.downstream.onError(th);
            DisposableHelper.dispose(this.o);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            synchronized (this) {
                U u = this.n;
                if (u == null) {
                    return;
                }
                u.add(t);
            }
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.m, disposable)) {
                this.m = disposable;
                try {
                    this.n = (U) ObjectHelper.requireNonNull(this.i.call(), "The buffer supplied is null");
                    this.downstream.onSubscribe(this);
                    if (this.cancelled) {
                        return;
                    }
                    Scheduler scheduler = this.l;
                    long j = this.j;
                    Disposable schedulePeriodicallyDirect = scheduler.schedulePeriodicallyDirect(this, j, j, this.k);
                    if (this.o.compareAndSet(null, schedulePeriodicallyDirect)) {
                        return;
                    }
                    schedulePeriodicallyDirect.dispose();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    dispose();
                    EmptyDisposable.error(th, this.downstream);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            U u;
            try {
                U u2 = (U) ObjectHelper.requireNonNull(this.i.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    u = this.n;
                    if (u != null) {
                        this.n = u2;
                    }
                }
                if (u == null) {
                    DisposableHelper.dispose(this.o);
                } else {
                    fastPathEmit(u, false, this);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
                dispose();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        public final Callable<U> i;
        public final long j;
        public final long k;
        public final TimeUnit l;
        public final Scheduler.Worker m;
        public final List<U> n;
        public Disposable o;

        /* loaded from: classes12.dex */
        public final class a implements Runnable {
            public final U h;

            public a(U u) {
                this.h = u;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (c.this) {
                    c.this.n.remove(this.h);
                }
                c cVar = c.this;
                cVar.fastPathOrderedEmit(this.h, false, cVar.m);
            }
        }

        /* loaded from: classes12.dex */
        public final class b implements Runnable {
            public final U h;

            public b(U u) {
                this.h = u;
            }

            @Override // java.lang.Runnable
            public void run() {
                synchronized (c.this) {
                    c.this.n.remove(this.h);
                }
                c cVar = c.this;
                cVar.fastPathOrderedEmit(this.h, false, cVar.m);
            }
        }

        public c(Observer<? super U> observer, Callable<U> callable, long j, long j2, TimeUnit timeUnit, Scheduler.Worker worker) {
            super(observer, new MpscLinkedQueue());
            this.i = callable;
            this.j = j;
            this.k = j2;
            this.l = timeUnit;
            this.m = worker;
            this.n = new LinkedList();
        }

        @Override // io.reactivex.internal.observers.QueueDrainObserver, io.reactivex.internal.util.ObservableQueueDrain
        /* renamed from: a */
        public void accept(Observer<? super U> observer, U u) {
            observer.onNext(u);
        }

        public void d() {
            synchronized (this) {
                this.n.clear();
            }
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            d();
            this.o.dispose();
            this.m.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            ArrayList<Collection> arrayList;
            synchronized (this) {
                arrayList = new ArrayList(this.n);
                this.n.clear();
            }
            for (Collection collection : arrayList) {
                this.queue.offer(collection);
            }
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainLoop(this.queue, this.downstream, false, this.m, this);
            }
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.done = true;
            d();
            this.downstream.onError(th);
            this.m.dispose();
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            synchronized (this) {
                for (U u : this.n) {
                    u.add(t);
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.o, disposable)) {
                this.o = disposable;
                try {
                    Collection collection = (Collection) ObjectHelper.requireNonNull(this.i.call(), "The buffer supplied is null");
                    this.n.add(collection);
                    this.downstream.onSubscribe(this);
                    Scheduler.Worker worker = this.m;
                    long j = this.k;
                    worker.schedulePeriodically(this, j, j, this.l);
                    this.m.schedule(new b(collection), this.j, this.l);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    disposable.dispose();
                    EmptyDisposable.error(th, this.downstream);
                    this.m.dispose();
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public void run() {
            if (this.cancelled) {
                return;
            }
            try {
                Collection collection = (Collection) ObjectHelper.requireNonNull(this.i.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    if (this.cancelled) {
                        return;
                    }
                    this.n.add(collection);
                    this.m.schedule(new a(collection), this.j, this.l);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
                dispose();
            }
        }
    }

    public ObservableBufferTimed(ObservableSource<T> observableSource, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, Callable<U> callable, int i, boolean z) {
        super(observableSource);
        this.h = j;
        this.i = j2;
        this.j = timeUnit;
        this.k = scheduler;
        this.l = callable;
        this.m = i;
        this.n = z;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super U> observer) {
        if (this.h == this.i && this.m == Integer.MAX_VALUE) {
            this.source.subscribe(new b(new SerializedObserver(observer), this.l, this.h, this.j, this.k));
            return;
        }
        Scheduler.Worker createWorker = this.k.createWorker();
        if (this.h == this.i) {
            this.source.subscribe(new a(new SerializedObserver(observer), this.l, this.h, this.j, this.m, this.n, createWorker));
        } else {
            this.source.subscribe(new c(new SerializedObserver(observer), this.l, this.h, this.i, this.j, createWorker));
        }
    }
}
