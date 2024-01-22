package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.subjects.UnicastSubject;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class ObservableWindowTimed<T> extends io.reactivex.internal.operators.observable.a<T, Observable<T>> {
    public final long h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;
    public final long l;
    public final int m;
    public final boolean n;

    /* loaded from: classes12.dex */
    public static final class a<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable {
        public final long i;
        public final TimeUnit j;
        public final Scheduler k;
        public final int l;
        public final boolean m;
        public final long n;
        public final Scheduler.Worker o;
        public long p;
        public long q;
        public Disposable r;
        public UnicastSubject<T> s;
        public volatile boolean t;
        public final SequentialDisposable u;

        /* renamed from: io.reactivex.internal.operators.observable.ObservableWindowTimed$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class RunnableC0794a implements Runnable {
            public final long h;
            public final a<?> i;

            public RunnableC0794a(long j, a<?> aVar) {
                this.h = j;
                this.i = aVar;
            }

            @Override // java.lang.Runnable
            public void run() {
                a<?> aVar = this.i;
                if (!aVar.cancelled) {
                    aVar.queue.offer(this);
                } else {
                    aVar.t = true;
                }
                if (aVar.enter()) {
                    aVar.d();
                }
            }
        }

        public a(Observer<? super Observable<T>> observer, long j, TimeUnit timeUnit, Scheduler scheduler, int i, long j2, boolean z) {
            super(observer, new MpscLinkedQueue());
            this.u = new SequentialDisposable();
            this.i = j;
            this.j = timeUnit;
            this.k = scheduler;
            this.l = i;
            this.n = j2;
            this.m = z;
            if (z) {
                this.o = scheduler.createWorker();
            } else {
                this.o = null;
            }
        }

        public void c() {
            DisposableHelper.dispose(this.u);
            Scheduler.Worker worker = this.o;
            if (worker != null) {
                worker.dispose();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void d() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) this.queue;
            Observer<? super V> observer = this.downstream;
            UnicastSubject<T> unicastSubject = this.s;
            int i = 1;
            while (!this.t) {
                boolean z = this.done;
                Object poll = mpscLinkedQueue.poll();
                boolean z2 = poll == null;
                boolean z3 = poll instanceof RunnableC0794a;
                if (z && (z2 || z3)) {
                    this.s = null;
                    mpscLinkedQueue.clear();
                    Throwable th = this.error;
                    if (th != null) {
                        unicastSubject.onError(th);
                    } else {
                        unicastSubject.onComplete();
                    }
                    c();
                    return;
                } else if (z2) {
                    i = leave(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (z3) {
                    RunnableC0794a runnableC0794a = (RunnableC0794a) poll;
                    if (!this.m || this.q == runnableC0794a.h) {
                        unicastSubject.onComplete();
                        this.p = 0L;
                        unicastSubject = (UnicastSubject<T>) UnicastSubject.create(this.l);
                        this.s = unicastSubject;
                        observer.onNext(unicastSubject);
                    }
                } else {
                    unicastSubject.onNext(NotificationLite.getValue(poll));
                    long j = this.p + 1;
                    if (j >= this.n) {
                        this.q++;
                        this.p = 0L;
                        unicastSubject.onComplete();
                        unicastSubject = (UnicastSubject<T>) UnicastSubject.create(this.l);
                        this.s = unicastSubject;
                        this.downstream.onNext(unicastSubject);
                        if (this.m) {
                            Disposable disposable = this.u.get();
                            disposable.dispose();
                            Scheduler.Worker worker = this.o;
                            RunnableC0794a runnableC0794a2 = new RunnableC0794a(this.q, this);
                            long j2 = this.i;
                            Disposable schedulePeriodically = worker.schedulePeriodically(runnableC0794a2, j2, j2, this.j);
                            if (!this.u.compareAndSet(disposable, schedulePeriodically)) {
                                schedulePeriodically.dispose();
                            }
                        }
                    } else {
                        this.p = j;
                    }
                }
            }
            this.r.dispose();
            mpscLinkedQueue.clear();
            c();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.cancelled = true;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.done = true;
            if (enter()) {
                d();
            }
            this.downstream.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            if (enter()) {
                d();
            }
            this.downstream.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.t) {
                return;
            }
            if (fastEnter()) {
                UnicastSubject<T> unicastSubject = this.s;
                unicastSubject.onNext(t);
                long j = this.p + 1;
                if (j >= this.n) {
                    this.q++;
                    this.p = 0L;
                    unicastSubject.onComplete();
                    UnicastSubject<T> create = UnicastSubject.create(this.l);
                    this.s = create;
                    this.downstream.onNext(create);
                    if (this.m) {
                        this.u.get().dispose();
                        Scheduler.Worker worker = this.o;
                        RunnableC0794a runnableC0794a = new RunnableC0794a(this.q, this);
                        long j2 = this.i;
                        DisposableHelper.replace(this.u, worker.schedulePeriodically(runnableC0794a, j2, j2, this.j));
                    }
                } else {
                    this.p = j;
                }
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
            }
            d();
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            Disposable schedulePeriodicallyDirect;
            if (DisposableHelper.validate(this.r, disposable)) {
                this.r = disposable;
                Observer<? super V> observer = this.downstream;
                observer.onSubscribe(this);
                if (this.cancelled) {
                    return;
                }
                UnicastSubject<T> create = UnicastSubject.create(this.l);
                this.s = create;
                observer.onNext(create);
                RunnableC0794a runnableC0794a = new RunnableC0794a(this.q, this);
                if (this.m) {
                    Scheduler.Worker worker = this.o;
                    long j = this.i;
                    schedulePeriodicallyDirect = worker.schedulePeriodically(runnableC0794a, j, j, this.j);
                } else {
                    Scheduler scheduler = this.k;
                    long j2 = this.i;
                    schedulePeriodicallyDirect = scheduler.schedulePeriodicallyDirect(runnableC0794a, j2, j2, this.j);
                }
                this.u.replace(schedulePeriodicallyDirect);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Observer<T>, Disposable {
        public static final Object q = new Object();
        public final long i;
        public final TimeUnit j;
        public final Scheduler k;
        public final int l;
        public Disposable m;
        public UnicastSubject<T> n;
        public final SequentialDisposable o;
        public volatile boolean p;

        public b(Observer<? super Observable<T>> observer, long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
            super(observer, new MpscLinkedQueue());
            this.o = new SequentialDisposable();
            this.i = j;
            this.j = timeUnit;
            this.k = scheduler;
            this.l = i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:10:0x0023, code lost:
            r2.onError(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0027, code lost:
            r2.onComplete();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
            r7.o.dispose();
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x002f, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0019, code lost:
            r7.n = null;
            r0.clear();
            r0 = r7.error;
         */
        /* JADX WARN: Code restructure failed: missing block: B:9:0x0021, code lost:
            if (r0 == null) goto L14;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a() {
            /*
                r7 = this;
                io.reactivex.internal.fuseable.SimplePlainQueue<U> r0 = r7.queue
                io.reactivex.internal.queue.MpscLinkedQueue r0 = (io.reactivex.internal.queue.MpscLinkedQueue) r0
                io.reactivex.Observer<? super V> r1 = r7.downstream
                io.reactivex.subjects.UnicastSubject<T> r2 = r7.n
                r3 = 1
            L9:
                boolean r4 = r7.p
                boolean r5 = r7.done
                java.lang.Object r6 = r0.poll()
                if (r5 == 0) goto L30
                if (r6 == 0) goto L19
                java.lang.Object r5 = io.reactivex.internal.operators.observable.ObservableWindowTimed.b.q
                if (r6 != r5) goto L30
            L19:
                r1 = 0
                r7.n = r1
                r0.clear()
                java.lang.Throwable r0 = r7.error
                if (r0 == 0) goto L27
                r2.onError(r0)
                goto L2a
            L27:
                r2.onComplete()
            L2a:
                io.reactivex.internal.disposables.SequentialDisposable r0 = r7.o
                r0.dispose()
                return
            L30:
                if (r6 != 0) goto L3a
                int r3 = -r3
                int r3 = r7.leave(r3)
                if (r3 != 0) goto L9
                return
            L3a:
                java.lang.Object r5 = io.reactivex.internal.operators.observable.ObservableWindowTimed.b.q
                if (r6 != r5) goto L55
                r2.onComplete()
                if (r4 != 0) goto L4f
                int r2 = r7.l
                io.reactivex.subjects.UnicastSubject r2 = io.reactivex.subjects.UnicastSubject.create(r2)
                r7.n = r2
                r1.onNext(r2)
                goto L9
            L4f:
                io.reactivex.disposables.Disposable r4 = r7.m
                r4.dispose()
                goto L9
            L55:
                java.lang.Object r4 = io.reactivex.internal.util.NotificationLite.getValue(r6)
                r2.onNext(r4)
                goto L9
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableWindowTimed.b.a():void");
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.cancelled = true;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.done = true;
            if (enter()) {
                a();
            }
            this.downstream.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            if (enter()) {
                a();
            }
            this.downstream.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.p) {
                return;
            }
            if (fastEnter()) {
                this.n.onNext(t);
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
            }
            a();
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.m, disposable)) {
                this.m = disposable;
                this.n = UnicastSubject.create(this.l);
                Observer<? super V> observer = this.downstream;
                observer.onSubscribe(this);
                observer.onNext(this.n);
                if (this.cancelled) {
                    return;
                }
                Scheduler scheduler = this.k;
                long j = this.i;
                this.o.replace(scheduler.schedulePeriodicallyDirect(this, j, j, this.j));
            }
        }

        public void run() {
            if (this.cancelled) {
                this.p = true;
            }
            this.queue.offer(q);
            if (enter()) {
                a();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable, Runnable {
        public final long i;
        public final long j;
        public final TimeUnit k;
        public final Scheduler.Worker l;
        public final int m;
        public final List<UnicastSubject<T>> n;
        public Disposable o;
        public volatile boolean p;

        /* loaded from: classes12.dex */
        public final class a implements Runnable {
            public final UnicastSubject<T> h;

            public a(UnicastSubject<T> unicastSubject) {
                this.h = unicastSubject;
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.a(this.h);
            }
        }

        /* loaded from: classes12.dex */
        public static final class b<T> {

            /* renamed from: a  reason: collision with root package name */
            public final UnicastSubject<T> f13913a;
            public final boolean b;

            public b(UnicastSubject<T> unicastSubject, boolean z) {
                this.f13913a = unicastSubject;
                this.b = z;
            }
        }

        public c(Observer<? super Observable<T>> observer, long j, long j2, TimeUnit timeUnit, Scheduler.Worker worker, int i) {
            super(observer, new MpscLinkedQueue());
            this.i = j;
            this.j = j2;
            this.k = timeUnit;
            this.l = worker;
            this.m = i;
            this.n = new LinkedList();
        }

        public void a(UnicastSubject<T> unicastSubject) {
            this.queue.offer(new b(unicastSubject, false));
            if (enter()) {
                b();
            }
        }

        public void b() {
            MpscLinkedQueue mpscLinkedQueue = (MpscLinkedQueue) this.queue;
            Observer<? super V> observer = this.downstream;
            List<UnicastSubject<T>> list = this.n;
            int i = 1;
            while (!this.p) {
                boolean z = this.done;
                T t = (T) mpscLinkedQueue.poll();
                boolean z2 = t == null;
                boolean z3 = t instanceof b;
                if (z && (z2 || z3)) {
                    mpscLinkedQueue.clear();
                    Throwable th = this.error;
                    if (th != null) {
                        for (UnicastSubject<T> unicastSubject : list) {
                            unicastSubject.onError(th);
                        }
                    } else {
                        for (UnicastSubject<T> unicastSubject2 : list) {
                            unicastSubject2.onComplete();
                        }
                    }
                    list.clear();
                    this.l.dispose();
                    return;
                } else if (z2) {
                    i = leave(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (z3) {
                    b bVar = (b) t;
                    if (bVar.b) {
                        if (!this.cancelled) {
                            UnicastSubject<T> create = UnicastSubject.create(this.m);
                            list.add(create);
                            observer.onNext(create);
                            this.l.schedule(new a(create), this.i, this.k);
                        }
                    } else {
                        list.remove(bVar.f13913a);
                        bVar.f13913a.onComplete();
                        if (list.isEmpty() && this.cancelled) {
                            this.p = true;
                        }
                    }
                } else {
                    for (UnicastSubject<T> unicastSubject3 : list) {
                        unicastSubject3.onNext(t);
                    }
                }
            }
            this.o.dispose();
            mpscLinkedQueue.clear();
            list.clear();
            this.l.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.cancelled = true;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.done = true;
            if (enter()) {
                b();
            }
            this.downstream.onComplete();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            if (enter()) {
                b();
            }
            this.downstream.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (fastEnter()) {
                for (UnicastSubject<T> unicastSubject : this.n) {
                    unicastSubject.onNext(t);
                }
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(t);
                if (!enter()) {
                    return;
                }
            }
            b();
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.o, disposable)) {
                this.o = disposable;
                this.downstream.onSubscribe(this);
                if (this.cancelled) {
                    return;
                }
                UnicastSubject<T> create = UnicastSubject.create(this.m);
                this.n.add(create);
                this.downstream.onNext(create);
                this.l.schedule(new a(create), this.i, this.k);
                Scheduler.Worker worker = this.l;
                long j = this.j;
                worker.schedulePeriodically(this, j, j, this.k);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            b bVar = new b(UnicastSubject.create(this.m), true);
            if (!this.cancelled) {
                this.queue.offer(bVar);
            }
            if (enter()) {
                b();
            }
        }
    }

    public ObservableWindowTimed(ObservableSource<T> observableSource, long j, long j2, TimeUnit timeUnit, Scheduler scheduler, long j3, int i, boolean z) {
        super(observableSource);
        this.h = j;
        this.i = j2;
        this.j = timeUnit;
        this.k = scheduler;
        this.l = j3;
        this.m = i;
        this.n = z;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super Observable<T>> observer) {
        SerializedObserver serializedObserver = new SerializedObserver(observer);
        long j = this.h;
        long j2 = this.i;
        if (j == j2) {
            long j3 = this.l;
            if (j3 == Long.MAX_VALUE) {
                this.source.subscribe(new b(serializedObserver, this.h, this.j, this.k, this.m));
                return;
            } else {
                this.source.subscribe(new a(serializedObserver, j, this.j, this.k, this.m, j3, this.n));
                return;
            }
        }
        this.source.subscribe(new c(serializedObserver, j, j2, this.j, this.k.createWorker(), this.m));
    }
}
