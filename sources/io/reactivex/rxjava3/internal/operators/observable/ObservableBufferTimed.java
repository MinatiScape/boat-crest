package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.observers.QueueDrainObserver;
import io.reactivex.rxjava3.internal.queue.MpscLinkedQueue;
import io.reactivex.rxjava3.internal.util.QueueDrainHelper;
import io.reactivex.rxjava3.observers.SerializedObserver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableBufferTimed<T, U extends Collection<? super T>> extends io.reactivex.rxjava3.internal.operators.observable.a<T, U> {
    public final long h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;
    public final Supplier<U> l;
    public final int m;
    public final boolean n;

    /* loaded from: classes12.dex */
    public static final class a<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        public final Supplier<U> i;
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

        public a(Observer<? super U> observer, Supplier<U> supplier, long j, TimeUnit timeUnit, int i, boolean z, Scheduler.Worker worker) {
            super(observer, new MpscLinkedQueue());
            this.i = supplier;
            this.j = j;
            this.k = timeUnit;
            this.l = i;
            this.m = z;
            this.n = worker;
        }

        @Override // io.reactivex.rxjava3.internal.observers.QueueDrainObserver, io.reactivex.rxjava3.internal.util.ObservableQueueDrain
        /* renamed from: a */
        public void accept(Observer<? super U> observer, U u) {
            observer.onNext(u);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
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

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.rxjava3.core.Observer
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

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                this.o = null;
            }
            this.downstream.onError(th);
            this.n.dispose();
        }

        @Override // io.reactivex.rxjava3.core.Observer
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
                    U u2 = this.i.get();
                    Objects.requireNonNull(u2, "The buffer supplied is null");
                    U u3 = u2;
                    synchronized (this) {
                        this.o = u3;
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

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.q, disposable)) {
                this.q = disposable;
                try {
                    U u = this.i.get();
                    Objects.requireNonNull(u, "The buffer supplied is null");
                    this.o = u;
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
                U u = this.i.get();
                Objects.requireNonNull(u, "The bufferSupplier returned a null buffer");
                U u2 = u;
                synchronized (this) {
                    U u3 = this.o;
                    if (u3 != null && this.r == this.s) {
                        this.o = u2;
                        fastPathOrderedEmit(u3, false, this);
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
        public final Supplier<U> i;
        public final long j;
        public final TimeUnit k;
        public final Scheduler l;
        public Disposable m;
        public U n;
        public final AtomicReference<Disposable> o;

        public b(Observer<? super U> observer, Supplier<U> supplier, long j, TimeUnit timeUnit, Scheduler scheduler) {
            super(observer, new MpscLinkedQueue());
            this.o = new AtomicReference<>();
            this.i = supplier;
            this.j = j;
            this.k = timeUnit;
            this.l = scheduler;
        }

        @Override // io.reactivex.rxjava3.internal.observers.QueueDrainObserver, io.reactivex.rxjava3.internal.util.ObservableQueueDrain
        /* renamed from: a */
        public void accept(Observer<? super U> observer, U u) {
            this.downstream.onNext(u);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            DisposableHelper.dispose(this.o);
            this.m.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.o.get() == DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.rxjava3.core.Observer
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

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                this.n = null;
            }
            this.downstream.onError(th);
            DisposableHelper.dispose(this.o);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            synchronized (this) {
                U u = this.n;
                if (u == null) {
                    return;
                }
                u.add(t);
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.m, disposable)) {
                this.m = disposable;
                try {
                    U u = this.i.get();
                    Objects.requireNonNull(u, "The buffer supplied is null");
                    this.n = u;
                    this.downstream.onSubscribe(this);
                    if (DisposableHelper.isDisposed(this.o.get())) {
                        return;
                    }
                    Scheduler scheduler = this.l;
                    long j = this.j;
                    DisposableHelper.set(this.o, scheduler.schedulePeriodicallyDirect(this, j, j, this.k));
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
                U u2 = this.i.get();
                Objects.requireNonNull(u2, "The bufferSupplier returned a null buffer");
                U u3 = u2;
                synchronized (this) {
                    u = this.n;
                    if (u != null) {
                        this.n = u3;
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
        public final Supplier<U> i;
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

        public c(Observer<? super U> observer, Supplier<U> supplier, long j, long j2, TimeUnit timeUnit, Scheduler.Worker worker) {
            super(observer, new MpscLinkedQueue());
            this.i = supplier;
            this.j = j;
            this.k = j2;
            this.l = timeUnit;
            this.m = worker;
            this.n = new LinkedList();
        }

        @Override // io.reactivex.rxjava3.internal.observers.QueueDrainObserver, io.reactivex.rxjava3.internal.util.ObservableQueueDrain
        /* renamed from: a */
        public void accept(Observer<? super U> observer, U u) {
            observer.onNext(u);
        }

        public void d() {
            synchronized (this) {
                this.n.clear();
            }
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            d();
            this.o.dispose();
            this.m.dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.rxjava3.core.Observer
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

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            this.done = true;
            d();
            this.downstream.onError(th);
            this.m.dispose();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            synchronized (this) {
                for (U u : this.n) {
                    u.add(t);
                }
            }
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.o, disposable)) {
                this.o = disposable;
                try {
                    U u = this.i.get();
                    Objects.requireNonNull(u, "The buffer supplied is null");
                    U u2 = u;
                    this.n.add(u2);
                    this.downstream.onSubscribe(this);
                    Scheduler.Worker worker = this.m;
                    long j = this.k;
                    worker.schedulePeriodically(this, j, j, this.l);
                    this.m.schedule(new b(u2), this.j, this.l);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    disposable.dispose();
                    EmptyDisposable.error(th, this.downstream);
                    this.m.dispose();
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.cancelled) {
                return;
            }
            try {
                U u = this.i.get();
                Objects.requireNonNull(u, "The bufferSupplier returned a null buffer");
                U u2 = u;
                synchronized (this) {
                    if (this.cancelled) {
                        return;
                    }
                    this.n.add(u2);
                    this.m.schedule(new a(u2), this.j, this.l);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
                dispose();
            }
        }
    }

    public ObservableBufferTimed(ObservableSource<T> observableSource, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, Supplier<U> supplier, int i, boolean z) {
        super(observableSource);
        this.h = j;
        this.i = j2;
        this.j = timeUnit;
        this.k = scheduler;
        this.l = supplier;
        this.m = i;
        this.n = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
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
