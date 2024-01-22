package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.internal.fuseable.SimplePlainQueue;
import io.reactivex.rxjava3.internal.queue.MpscLinkedQueue;
import io.reactivex.rxjava3.subjects.UnicastSubject;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public final class ObservableWindowTimed<T> extends io.reactivex.rxjava3.internal.operators.observable.a<T, Observable<T>> {
    public final long h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;
    public final long l;
    public final int m;
    public final boolean n;

    /* loaded from: classes12.dex */
    public static abstract class a<T> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 5724293814035355511L;
        public final int bufferSize;
        public volatile boolean done;
        public final Observer<? super Observable<T>> downstream;
        public long emitted;
        public Throwable error;
        public final long timespan;
        public final TimeUnit unit;
        public Disposable upstream;
        public volatile boolean upstreamCancelled;
        public final SimplePlainQueue<Object> queue = new MpscLinkedQueue();
        public final AtomicBoolean downstreamCancelled = new AtomicBoolean();
        public final AtomicInteger windowCount = new AtomicInteger(1);

        public a(Observer<? super Observable<T>> observer, long j, TimeUnit timeUnit, int i) {
            this.downstream = observer;
            this.timespan = j;
            this.unit = timeUnit;
            this.bufferSize = i;
        }

        abstract void cleanupResources();

        abstract void createFirstWindow();

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public final void dispose() {
            if (this.downstreamCancelled.compareAndSet(false, true)) {
                windowDone();
            }
        }

        abstract void drain();

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public final boolean isDisposed() {
            return this.downstreamCancelled.get();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onComplete() {
            this.done = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public final void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
                createFirstWindow();
            }
        }

        final void windowDone() {
            if (this.windowCount.decrementAndGet() == 0) {
                cleanupResources();
                this.upstream.dispose();
                this.upstreamCancelled = true;
                drain();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends a<T> implements Runnable {
        private static final long serialVersionUID = -6130475889925953722L;
        public long count;
        public final long maxSize;
        public final boolean restartTimerOnMaxSize;
        public final Scheduler scheduler;
        public final SequentialDisposable timer;
        public UnicastSubject<T> window;
        public final Scheduler.Worker worker;

        /* loaded from: classes12.dex */
        public static final class a implements Runnable {
            public final b<?> h;
            public final long i;

            public a(b<?> bVar, long j) {
                this.h = bVar;
                this.i = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.boundary(this);
            }
        }

        public b(Observer<? super Observable<T>> observer, long j, TimeUnit timeUnit, Scheduler scheduler, int i, long j2, boolean z) {
            super(observer, j, timeUnit, i);
            this.scheduler = scheduler;
            this.maxSize = j2;
            this.restartTimerOnMaxSize = z;
            if (z) {
                this.worker = scheduler.createWorker();
            } else {
                this.worker = null;
            }
            this.timer = new SequentialDisposable();
        }

        public void boundary(a aVar) {
            this.queue.offer(aVar);
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed.a
        public void cleanupResources() {
            this.timer.dispose();
            Scheduler.Worker worker = this.worker;
            if (worker != null) {
                worker.dispose();
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed.a
        public void createFirstWindow() {
            if (this.downstreamCancelled.get()) {
                return;
            }
            this.emitted = 1L;
            this.windowCount.getAndIncrement();
            UnicastSubject<T> create = UnicastSubject.create(this.bufferSize, this);
            this.window = create;
            io.reactivex.rxjava3.internal.operators.observable.b bVar = new io.reactivex.rxjava3.internal.operators.observable.b(create);
            this.downstream.onNext(bVar);
            a aVar = new a(this, 1L);
            if (this.restartTimerOnMaxSize) {
                SequentialDisposable sequentialDisposable = this.timer;
                Scheduler.Worker worker = this.worker;
                long j = this.timespan;
                sequentialDisposable.replace(worker.schedulePeriodically(aVar, j, j, this.unit));
            } else {
                SequentialDisposable sequentialDisposable2 = this.timer;
                Scheduler scheduler = this.scheduler;
                long j2 = this.timespan;
                sequentialDisposable2.replace(scheduler.schedulePeriodicallyDirect(aVar, j2, j2, this.unit));
            }
            if (bVar.d()) {
                this.window.onComplete();
            }
        }

        public UnicastSubject<T> createNewWindow(UnicastSubject<T> unicastSubject) {
            if (unicastSubject != null) {
                unicastSubject.onComplete();
                unicastSubject = null;
            }
            if (this.downstreamCancelled.get()) {
                cleanupResources();
            } else {
                long j = this.emitted + 1;
                this.emitted = j;
                this.windowCount.getAndIncrement();
                unicastSubject = UnicastSubject.create(this.bufferSize, this);
                this.window = unicastSubject;
                io.reactivex.rxjava3.internal.operators.observable.b bVar = new io.reactivex.rxjava3.internal.operators.observable.b(unicastSubject);
                this.downstream.onNext(bVar);
                if (this.restartTimerOnMaxSize) {
                    SequentialDisposable sequentialDisposable = this.timer;
                    Scheduler.Worker worker = this.worker;
                    a aVar = new a(this, j);
                    long j2 = this.timespan;
                    sequentialDisposable.update(worker.schedulePeriodically(aVar, j2, j2, this.unit));
                }
                if (bVar.d()) {
                    unicastSubject.onComplete();
                }
            }
            return unicastSubject;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed.a
        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            SimplePlainQueue<Object> simplePlainQueue = this.queue;
            Observer<? super Observable<T>> observer = this.downstream;
            UnicastSubject<T> unicastSubject = this.window;
            int i = 1;
            while (true) {
                if (this.upstreamCancelled) {
                    simplePlainQueue.clear();
                    this.window = null;
                    unicastSubject = null;
                } else {
                    boolean z = this.done;
                    Object poll = simplePlainQueue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            if (unicastSubject != 0) {
                                unicastSubject.onError(th);
                            }
                            observer.onError(th);
                        } else {
                            if (unicastSubject != 0) {
                                unicastSubject.onComplete();
                            }
                            observer.onComplete();
                        }
                        cleanupResources();
                        this.upstreamCancelled = true;
                    } else if (!z2) {
                        if (poll instanceof a) {
                            if (((a) poll).i == this.emitted || !this.restartTimerOnMaxSize) {
                                this.count = 0L;
                                unicastSubject = createNewWindow(unicastSubject);
                            }
                        } else if (unicastSubject != null) {
                            unicastSubject.onNext(poll);
                            long j = this.count + 1;
                            if (j == this.maxSize) {
                                this.count = 0L;
                                unicastSubject = createNewWindow(unicastSubject);
                            } else {
                                this.count = j;
                            }
                        }
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            windowDone();
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T> extends a<T> implements Runnable {
        public static final Object NEXT_WINDOW = new Object();
        private static final long serialVersionUID = 1155822639622580836L;
        public final Scheduler scheduler;
        public final SequentialDisposable timer;
        public UnicastSubject<T> window;
        public final Runnable windowRunnable;

        /* loaded from: classes12.dex */
        public final class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.windowDone();
            }
        }

        public c(Observer<? super Observable<T>> observer, long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
            super(observer, j, timeUnit, i);
            this.scheduler = scheduler;
            this.timer = new SequentialDisposable();
            this.windowRunnable = new a();
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed.a
        public void cleanupResources() {
            this.timer.dispose();
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed.a
        public void createFirstWindow() {
            if (this.downstreamCancelled.get()) {
                return;
            }
            this.windowCount.getAndIncrement();
            UnicastSubject<T> create = UnicastSubject.create(this.bufferSize, this.windowRunnable);
            this.window = create;
            this.emitted = 1L;
            io.reactivex.rxjava3.internal.operators.observable.b bVar = new io.reactivex.rxjava3.internal.operators.observable.b(create);
            this.downstream.onNext(bVar);
            SequentialDisposable sequentialDisposable = this.timer;
            Scheduler scheduler = this.scheduler;
            long j = this.timespan;
            sequentialDisposable.replace(scheduler.schedulePeriodicallyDirect(this, j, j, this.unit));
            if (bVar.d()) {
                this.window.onComplete();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed.a
        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            SimplePlainQueue<Object> simplePlainQueue = this.queue;
            Observer<? super Observable<T>> observer = this.downstream;
            UnicastSubject<T> unicastSubject = this.window;
            int i = 1;
            while (true) {
                if (this.upstreamCancelled) {
                    simplePlainQueue.clear();
                    this.window = null;
                    unicastSubject = (UnicastSubject<T>) null;
                } else {
                    boolean z = this.done;
                    Object poll = simplePlainQueue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            if (unicastSubject != null) {
                                unicastSubject.onError(th);
                            }
                            observer.onError(th);
                        } else {
                            if (unicastSubject != null) {
                                unicastSubject.onComplete();
                            }
                            observer.onComplete();
                        }
                        cleanupResources();
                        this.upstreamCancelled = true;
                    } else if (!z2) {
                        if (poll == NEXT_WINDOW) {
                            if (unicastSubject != null) {
                                unicastSubject.onComplete();
                                this.window = null;
                                unicastSubject = (UnicastSubject<T>) null;
                            }
                            if (this.downstreamCancelled.get()) {
                                this.timer.dispose();
                            } else {
                                this.emitted++;
                                this.windowCount.getAndIncrement();
                                unicastSubject = (UnicastSubject<T>) UnicastSubject.create(this.bufferSize, this.windowRunnable);
                                this.window = unicastSubject;
                                io.reactivex.rxjava3.internal.operators.observable.b bVar = new io.reactivex.rxjava3.internal.operators.observable.b(unicastSubject);
                                observer.onNext(bVar);
                                if (bVar.d()) {
                                    unicastSubject.onComplete();
                                }
                            }
                        } else if (unicastSubject != null) {
                            unicastSubject.onNext(poll);
                        }
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            this.queue.offer(NEXT_WINDOW);
            drain();
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<T> extends a<T> implements Runnable {
        private static final long serialVersionUID = -7852870764194095894L;
        public final long timeskip;
        public final List<UnicastSubject<T>> windows;
        public final Scheduler.Worker worker;
        public static final Object WINDOW_OPEN = new Object();
        public static final Object WINDOW_CLOSE = new Object();

        /* loaded from: classes12.dex */
        public static final class a implements Runnable {
            public final d<?> h;
            public final boolean i;

            public a(d<?> dVar, boolean z) {
                this.h = dVar;
                this.i = z;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.h.boundary(this.i);
            }
        }

        public d(Observer<? super Observable<T>> observer, long j, long j2, TimeUnit timeUnit, Scheduler.Worker worker, int i) {
            super(observer, j, timeUnit, i);
            this.timeskip = j2;
            this.worker = worker;
            this.windows = new LinkedList();
        }

        public void boundary(boolean z) {
            this.queue.offer(z ? WINDOW_OPEN : WINDOW_CLOSE);
            drain();
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed.a
        public void cleanupResources() {
            this.worker.dispose();
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed.a
        public void createFirstWindow() {
            if (this.downstreamCancelled.get()) {
                return;
            }
            this.emitted = 1L;
            this.windowCount.getAndIncrement();
            UnicastSubject<T> create = UnicastSubject.create(this.bufferSize, this);
            this.windows.add(create);
            io.reactivex.rxjava3.internal.operators.observable.b bVar = new io.reactivex.rxjava3.internal.operators.observable.b(create);
            this.downstream.onNext(bVar);
            this.worker.schedule(new a(this, false), this.timespan, this.unit);
            Scheduler.Worker worker = this.worker;
            a aVar = new a(this, true);
            long j = this.timeskip;
            worker.schedulePeriodically(aVar, j, j, this.unit);
            if (bVar.d()) {
                create.onComplete();
                this.windows.remove(create);
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableWindowTimed.a
        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            SimplePlainQueue<Object> simplePlainQueue = this.queue;
            Observer<? super Observable<T>> observer = this.downstream;
            List<UnicastSubject<T>> list = this.windows;
            int i = 1;
            while (true) {
                if (this.upstreamCancelled) {
                    simplePlainQueue.clear();
                    list.clear();
                } else {
                    boolean z = this.done;
                    Object poll = simplePlainQueue.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            for (UnicastSubject<T> unicastSubject : list) {
                                unicastSubject.onError(th);
                            }
                            observer.onError(th);
                        } else {
                            for (UnicastSubject<T> unicastSubject2 : list) {
                                unicastSubject2.onComplete();
                            }
                            observer.onComplete();
                        }
                        cleanupResources();
                        this.upstreamCancelled = true;
                    } else if (!z2) {
                        if (poll == WINDOW_OPEN) {
                            if (!this.downstreamCancelled.get()) {
                                this.emitted++;
                                this.windowCount.getAndIncrement();
                                UnicastSubject<T> create = UnicastSubject.create(this.bufferSize, this);
                                list.add(create);
                                io.reactivex.rxjava3.internal.operators.observable.b bVar = new io.reactivex.rxjava3.internal.operators.observable.b(create);
                                observer.onNext(bVar);
                                this.worker.schedule(new a(this, false), this.timespan, this.unit);
                                if (bVar.d()) {
                                    create.onComplete();
                                }
                            }
                        } else if (poll == WINDOW_CLOSE) {
                            if (!list.isEmpty()) {
                                list.remove(0).onComplete();
                            }
                        } else {
                            for (UnicastSubject<T> unicastSubject3 : list) {
                                unicastSubject3.onNext(poll);
                            }
                        }
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            windowDone();
        }
    }

    public ObservableWindowTimed(Observable<T> observable, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, long j3, int i, boolean z) {
        super(observable);
        this.h = j;
        this.i = j2;
        this.j = timeUnit;
        this.k = scheduler;
        this.l = j3;
        this.m = i;
        this.n = z;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super Observable<T>> observer) {
        if (this.h == this.i) {
            if (this.l == Long.MAX_VALUE) {
                this.source.subscribe(new c(observer, this.h, this.j, this.k, this.m));
                return;
            } else {
                this.source.subscribe(new b(observer, this.h, this.j, this.k, this.m, this.l, this.n));
                return;
            }
        }
        this.source.subscribe(new d(observer, this.h, this.i, this.j, this.k.createWorker(), this.m));
    }
}
