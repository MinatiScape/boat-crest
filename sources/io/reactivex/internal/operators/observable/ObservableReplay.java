package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.HasUpstreamObservableSource;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableReplay<T> extends ConnectableObservable<T> implements HasUpstreamObservableSource<T>, ResettableConnectable {
    public static final b l = new o();
    public final ObservableSource<T> h;
    public final AtomicReference<j<T>> i;
    public final b<T> j;
    public final ObservableSource<T> k;

    /* loaded from: classes12.dex */
    public static abstract class a<T> extends AtomicReference<f> implements h<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        public int size;
        public f tail;

        public a() {
            f fVar = new f(null);
            this.tail = fVar;
            set(fVar);
        }

        public final void addLast(f fVar) {
            this.tail.set(fVar);
            this.tail = fVar;
            this.size++;
        }

        public final void collect(Collection<? super T> collection) {
            f head = getHead();
            while (true) {
                head = head.get();
                if (head == null) {
                    return;
                }
                Object leaveTransform = leaveTransform(head.value);
                if (NotificationLite.isComplete(leaveTransform) || NotificationLite.isError(leaveTransform)) {
                    return;
                }
                collection.add((Object) NotificationLite.getValue(leaveTransform));
            }
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.h
        public final void complete() {
            addLast(new f(enterTransform(NotificationLite.complete())));
            truncateFinal();
        }

        public Object enterTransform(Object obj) {
            return obj;
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.h
        public final void error(Throwable th) {
            addLast(new f(enterTransform(NotificationLite.error(th))));
            truncateFinal();
        }

        public f getHead() {
            return get();
        }

        public boolean hasCompleted() {
            Object obj = this.tail.value;
            return obj != null && NotificationLite.isComplete(leaveTransform(obj));
        }

        public boolean hasError() {
            Object obj = this.tail.value;
            return obj != null && NotificationLite.isError(leaveTransform(obj));
        }

        public Object leaveTransform(Object obj) {
            return obj;
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.h
        public final void next(T t) {
            addLast(new f(enterTransform(NotificationLite.next(t))));
            truncate();
        }

        public final void removeFirst() {
            this.size--;
            setFirst(get().get());
        }

        public final void removeSome(int i) {
            f fVar = get();
            while (i > 0) {
                fVar = fVar.get();
                i--;
                this.size--;
            }
            setFirst(fVar);
            f fVar2 = get();
            if (fVar2.get() == null) {
                this.tail = fVar2;
            }
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.h
        public final void replay(d<T> dVar) {
            if (dVar.getAndIncrement() != 0) {
                return;
            }
            int i = 1;
            do {
                f fVar = (f) dVar.index();
                if (fVar == null) {
                    fVar = getHead();
                    dVar.index = fVar;
                }
                while (!dVar.isDisposed()) {
                    f fVar2 = fVar.get();
                    if (fVar2 != null) {
                        if (NotificationLite.accept(leaveTransform(fVar2.value), dVar.child)) {
                            dVar.index = null;
                            return;
                        }
                        fVar = fVar2;
                    } else {
                        dVar.index = fVar;
                        i = dVar.addAndGet(-i);
                    }
                }
                dVar.index = null;
                return;
            } while (i != 0);
        }

        public final void setFirst(f fVar) {
            set(fVar);
        }

        public final void trimHead() {
            f fVar = get();
            if (fVar.value != null) {
                f fVar2 = new f(null);
                fVar2.lazySet(fVar.get());
                set(fVar2);
            }
        }

        public abstract void truncate();

        public void truncateFinal() {
            trimHead();
        }
    }

    /* loaded from: classes12.dex */
    public interface b<T> {
        h<T> call();
    }

    /* loaded from: classes12.dex */
    public static final class c<R> implements Consumer<Disposable> {
        public final ObserverResourceWrapper<R> h;

        public c(ObserverResourceWrapper<R> observerResourceWrapper) {
            this.h = observerResourceWrapper;
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Disposable disposable) {
            this.h.setResource(disposable);
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<T> extends AtomicInteger implements Disposable {
        private static final long serialVersionUID = 2728361546769921047L;
        public volatile boolean cancelled;
        public final Observer<? super T> child;
        public Object index;
        public final j<T> parent;

        public d(j<T> jVar, Observer<? super T> observer) {
            this.parent = jVar;
            this.child = observer;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.parent.remove(this);
            this.index = null;
        }

        public <U> U index() {
            return (U) this.index;
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }
    }

    /* loaded from: classes12.dex */
    public static final class e<R, U> extends Observable<R> {
        public final Callable<? extends ConnectableObservable<U>> h;
        public final Function<? super Observable<U>, ? extends ObservableSource<R>> i;

        public e(Callable<? extends ConnectableObservable<U>> callable, Function<? super Observable<U>, ? extends ObservableSource<R>> function) {
            this.h = callable;
            this.i = function;
        }

        @Override // io.reactivex.Observable
        public void subscribeActual(Observer<? super R> observer) {
            try {
                ConnectableObservable connectableObservable = (ConnectableObservable) ObjectHelper.requireNonNull(this.h.call(), "The connectableFactory returned a null ConnectableObservable");
                ObservableSource observableSource = (ObservableSource) ObjectHelper.requireNonNull(this.i.apply(connectableObservable), "The selector returned a null ObservableSource");
                ObserverResourceWrapper observerResourceWrapper = new ObserverResourceWrapper(observer);
                observableSource.subscribe(observerResourceWrapper);
                connectableObservable.connect(new c(observerResourceWrapper));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                EmptyDisposable.error(th, observer);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class f extends AtomicReference<f> {
        private static final long serialVersionUID = 245354315435971818L;
        public final Object value;

        public f(Object obj) {
            this.value = obj;
        }
    }

    /* loaded from: classes12.dex */
    public static final class g<T> extends ConnectableObservable<T> {
        public final ConnectableObservable<T> h;
        public final Observable<T> i;

        public g(ConnectableObservable<T> connectableObservable, Observable<T> observable) {
            this.h = connectableObservable;
            this.i = observable;
        }

        @Override // io.reactivex.observables.ConnectableObservable
        public void connect(Consumer<? super Disposable> consumer) {
            this.h.connect(consumer);
        }

        @Override // io.reactivex.Observable
        public void subscribeActual(Observer<? super T> observer) {
            this.i.subscribe(observer);
        }
    }

    /* loaded from: classes12.dex */
    public interface h<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(d<T> dVar);
    }

    /* loaded from: classes12.dex */
    public static final class i<T> implements b<T> {

        /* renamed from: a  reason: collision with root package name */
        public final int f13910a;

        public i(int i) {
            this.f13910a = i;
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.b
        public h<T> call() {
            return new n(this.f13910a);
        }
    }

    /* loaded from: classes12.dex */
    public static final class j<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        public static final d[] EMPTY = new d[0];
        public static final d[] TERMINATED = new d[0];
        private static final long serialVersionUID = -533785617179540163L;
        public final h<T> buffer;
        public boolean done;
        public final AtomicReference<d[]> observers = new AtomicReference<>(EMPTY);
        public final AtomicBoolean shouldConnect = new AtomicBoolean();

        public j(h<T> hVar) {
            this.buffer = hVar;
        }

        public boolean add(d<T> dVar) {
            d[] dVarArr;
            d[] dVarArr2;
            do {
                dVarArr = this.observers.get();
                if (dVarArr == TERMINATED) {
                    return false;
                }
                int length = dVarArr.length;
                dVarArr2 = new d[length + 1];
                System.arraycopy(dVarArr, 0, dVarArr2, 0, length);
                dVarArr2[length] = dVar;
            } while (!this.observers.compareAndSet(dVarArr, dVarArr2));
            return true;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.observers.set(TERMINATED);
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.observers.get() == TERMINATED;
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.buffer.complete();
            replayFinal();
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(th);
                replayFinal();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            this.buffer.next(t);
            replay();
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable)) {
                replay();
            }
        }

        public void remove(d<T> dVar) {
            d[] dVarArr;
            d[] dVarArr2;
            do {
                dVarArr = this.observers.get();
                int length = dVarArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (dVarArr[i2].equals(dVar)) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    dVarArr2 = EMPTY;
                } else {
                    d[] dVarArr3 = new d[length - 1];
                    System.arraycopy(dVarArr, 0, dVarArr3, 0, i);
                    System.arraycopy(dVarArr, i + 1, dVarArr3, i, (length - i) - 1);
                    dVarArr2 = dVarArr3;
                }
            } while (!this.observers.compareAndSet(dVarArr, dVarArr2));
        }

        public void replay() {
            for (d<T> dVar : this.observers.get()) {
                this.buffer.replay(dVar);
            }
        }

        public void replayFinal() {
            for (d<T> dVar : this.observers.getAndSet(TERMINATED)) {
                this.buffer.replay(dVar);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class k<T> implements ObservableSource<T> {
        public final AtomicReference<j<T>> h;
        public final b<T> i;

        public k(AtomicReference<j<T>> atomicReference, b<T> bVar) {
            this.h = atomicReference;
            this.i = bVar;
        }

        @Override // io.reactivex.ObservableSource
        public void subscribe(Observer<? super T> observer) {
            j<T> jVar;
            while (true) {
                jVar = this.h.get();
                if (jVar != null) {
                    break;
                }
                j<T> jVar2 = new j<>(this.i.call());
                if (this.h.compareAndSet(null, jVar2)) {
                    jVar = jVar2;
                    break;
                }
            }
            d<T> dVar = new d<>(jVar, observer);
            observer.onSubscribe(dVar);
            jVar.add(dVar);
            if (dVar.isDisposed()) {
                jVar.remove(dVar);
            } else {
                jVar.buffer.replay(dVar);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class l<T> implements b<T> {

        /* renamed from: a  reason: collision with root package name */
        public final int f13911a;
        public final long b;
        public final TimeUnit c;
        public final Scheduler d;

        public l(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.f13911a = i;
            this.b = j;
            this.c = timeUnit;
            this.d = scheduler;
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.b
        public h<T> call() {
            return new m(this.f13911a, this.b, this.c, this.d);
        }
    }

    /* loaded from: classes12.dex */
    public static final class m<T> extends a<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        public final int limit;
        public final long maxAge;
        public final Scheduler scheduler;
        public final TimeUnit unit;

        public m(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.scheduler = scheduler;
            this.limit = i;
            this.maxAge = j;
            this.unit = timeUnit;
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.a
        public Object enterTransform(Object obj) {
            return new Timed(obj, this.scheduler.now(this.unit), this.unit);
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.a
        public f getHead() {
            f fVar;
            long now = this.scheduler.now(this.unit) - this.maxAge;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 != null) {
                    Timed timed = (Timed) fVar2.value;
                    if (NotificationLite.isComplete(timed.value()) || NotificationLite.isError(timed.value()) || timed.time() > now) {
                        break;
                    }
                    fVar3 = fVar2.get();
                } else {
                    break;
                }
            }
            return fVar;
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.a
        public Object leaveTransform(Object obj) {
            return ((Timed) obj).value();
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.a
        public void truncate() {
            f fVar;
            long now = this.scheduler.now(this.unit) - this.maxAge;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            int i = 0;
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 == null) {
                    break;
                }
                int i2 = this.size;
                if (i2 > this.limit && i2 > 1) {
                    i++;
                    this.size = i2 - 1;
                    fVar3 = fVar2.get();
                } else if (((Timed) fVar2.value).time() > now) {
                    break;
                } else {
                    i++;
                    this.size--;
                    fVar3 = fVar2.get();
                }
            }
            if (i != 0) {
                setFirst(fVar);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x003e, code lost:
            setFirst(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0041, code lost:
            return;
         */
        @Override // io.reactivex.internal.operators.observable.ObservableReplay.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void truncateFinal() {
            /*
                r10 = this;
                io.reactivex.Scheduler r0 = r10.scheduler
                java.util.concurrent.TimeUnit r1 = r10.unit
                long r0 = r0.now(r1)
                long r2 = r10.maxAge
                long r0 = r0 - r2
                java.lang.Object r2 = r10.get()
                io.reactivex.internal.operators.observable.ObservableReplay$f r2 = (io.reactivex.internal.operators.observable.ObservableReplay.f) r2
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.observable.ObservableReplay$f r3 = (io.reactivex.internal.operators.observable.ObservableReplay.f) r3
                r4 = 0
            L18:
                r9 = r3
                r3 = r2
                r2 = r9
                if (r2 == 0) goto L3c
                int r5 = r10.size
                r6 = 1
                if (r5 <= r6) goto L3c
                java.lang.Object r5 = r2.value
                io.reactivex.schedulers.Timed r5 = (io.reactivex.schedulers.Timed) r5
                long r7 = r5.time()
                int r5 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r5 > 0) goto L3c
                int r4 = r4 + 1
                int r3 = r10.size
                int r3 = r3 - r6
                r10.size = r3
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.observable.ObservableReplay$f r3 = (io.reactivex.internal.operators.observable.ObservableReplay.f) r3
                goto L18
            L3c:
                if (r4 == 0) goto L41
                r10.setFirst(r3)
            L41:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableReplay.m.truncateFinal():void");
        }
    }

    /* loaded from: classes12.dex */
    public static final class n<T> extends a<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        public final int limit;

        public n(int i) {
            this.limit = i;
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.a
        public void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class o implements b<Object> {
        @Override // io.reactivex.internal.operators.observable.ObservableReplay.b
        public h<Object> call() {
            return new p(16);
        }
    }

    /* loaded from: classes12.dex */
    public static final class p<T> extends ArrayList<Object> implements h<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        public volatile int size;

        public p(int i) {
            super(i);
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.h
        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.h
        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.h
        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        @Override // io.reactivex.internal.operators.observable.ObservableReplay.h
        public void replay(d<T> dVar) {
            if (dVar.getAndIncrement() != 0) {
                return;
            }
            Observer<? super T> observer = dVar.child;
            int i = 1;
            while (!dVar.isDisposed()) {
                int i2 = this.size;
                Integer num = (Integer) dVar.index();
                int intValue = num != null ? num.intValue() : 0;
                while (intValue < i2) {
                    if (NotificationLite.accept(get(intValue), observer) || dVar.isDisposed()) {
                        return;
                    }
                    intValue++;
                }
                dVar.index = Integer.valueOf(intValue);
                i = dVar.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }
    }

    public ObservableReplay(ObservableSource<T> observableSource, ObservableSource<T> observableSource2, AtomicReference<j<T>> atomicReference, b<T> bVar) {
        this.k = observableSource;
        this.h = observableSource2;
        this.i = atomicReference;
        this.j = bVar;
    }

    public static <T> ConnectableObservable<T> create(ObservableSource<T> observableSource, int i2) {
        if (i2 == Integer.MAX_VALUE) {
            return createFrom(observableSource);
        }
        return e(observableSource, new i(i2));
    }

    public static <T> ConnectableObservable<T> createFrom(ObservableSource<? extends T> observableSource) {
        return e(observableSource, l);
    }

    public static <T> ConnectableObservable<T> e(ObservableSource<T> observableSource, b<T> bVar) {
        AtomicReference atomicReference = new AtomicReference();
        return RxJavaPlugins.onAssembly((ConnectableObservable) new ObservableReplay(new k(atomicReference, bVar), observableSource, atomicReference, bVar));
    }

    public static <U, R> Observable<R> multicastSelector(Callable<? extends ConnectableObservable<U>> callable, Function<? super Observable<U>, ? extends ObservableSource<R>> function) {
        return RxJavaPlugins.onAssembly(new e(callable, function));
    }

    public static <T> ConnectableObservable<T> observeOn(ConnectableObservable<T> connectableObservable, Scheduler scheduler) {
        return RxJavaPlugins.onAssembly((ConnectableObservable) new g(connectableObservable, connectableObservable.observeOn(scheduler)));
    }

    @Override // io.reactivex.observables.ConnectableObservable
    public void connect(Consumer<? super Disposable> consumer) {
        j<T> jVar;
        while (true) {
            jVar = this.i.get();
            if (jVar != null && !jVar.isDisposed()) {
                break;
            }
            j<T> jVar2 = new j<>(this.j.call());
            if (this.i.compareAndSet(jVar, jVar2)) {
                jVar = jVar2;
                break;
            }
        }
        boolean z = !jVar.shouldConnect.get() && jVar.shouldConnect.compareAndSet(false, true);
        try {
            consumer.accept(jVar);
            if (z) {
                this.h.subscribe(jVar);
            }
        } catch (Throwable th) {
            if (z) {
                jVar.shouldConnect.compareAndSet(true, false);
            }
            Exceptions.throwIfFatal(th);
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    @Override // io.reactivex.internal.disposables.ResettableConnectable
    public void resetIf(Disposable disposable) {
        this.i.compareAndSet((j) disposable, null);
    }

    @Override // io.reactivex.internal.fuseable.HasUpstreamObservableSource
    public ObservableSource<T> source() {
        return this.h;
    }

    @Override // io.reactivex.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.k.subscribe(observer);
    }

    public static <T> ConnectableObservable<T> create(ObservableSource<T> observableSource, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return create(observableSource, j2, timeUnit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableObservable<T> create(ObservableSource<T> observableSource, long j2, TimeUnit timeUnit, Scheduler scheduler, int i2) {
        return e(observableSource, new l(i2, j2, timeUnit, scheduler));
    }
}
