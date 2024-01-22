package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.fuseable.HasUpstreamObservableSource;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class ObservableReplay<T> extends ConnectableObservable<T> implements HasUpstreamObservableSource<T> {
    public static final b l = new n();
    public final ObservableSource<T> h;
    public final AtomicReference<i<T>> i;
    public final b<T> j;
    public final ObservableSource<T> k;

    /* loaded from: classes12.dex */
    public static abstract class a<T> extends AtomicReference<f> implements g<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        public final boolean eagerTruncate;
        public int size;
        public f tail;

        public a(boolean z) {
            this.eagerTruncate = z;
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

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.g
        public final void complete() {
            addLast(new f(enterTransform(NotificationLite.complete())));
            truncateFinal();
        }

        public Object enterTransform(Object obj) {
            return obj;
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.g
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

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.g
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

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.g
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
            if (this.eagerTruncate) {
                f fVar2 = new f(null);
                fVar2.lazySet(fVar.get());
                fVar = fVar2;
            }
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
        g<T> call();
    }

    /* loaded from: classes12.dex */
    public static final class c<R> implements Consumer<Disposable> {
        public final ObserverResourceWrapper<R> h;

        public c(ObserverResourceWrapper<R> observerResourceWrapper) {
            this.h = observerResourceWrapper;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
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
        public final i<T> parent;

        public d(i<T> iVar, Observer<? super T> observer) {
            this.parent = iVar;
            this.child = observer;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
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

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.cancelled;
        }
    }

    /* loaded from: classes12.dex */
    public static final class e<R, U> extends Observable<R> {
        public final Supplier<? extends ConnectableObservable<U>> h;
        public final Function<? super Observable<U>, ? extends ObservableSource<R>> i;

        public e(Supplier<? extends ConnectableObservable<U>> supplier, Function<? super Observable<U>, ? extends ObservableSource<R>> function) {
            this.h = supplier;
            this.i = function;
        }

        @Override // io.reactivex.rxjava3.core.Observable
        public void subscribeActual(Observer<? super R> observer) {
            try {
                ConnectableObservable<U> connectableObservable = this.h.get();
                Objects.requireNonNull(connectableObservable, "The connectableFactory returned a null ConnectableObservable");
                ConnectableObservable<U> connectableObservable2 = connectableObservable;
                ObservableSource<R> apply = this.i.apply(connectableObservable2);
                Objects.requireNonNull(apply, "The selector returned a null ObservableSource");
                ObservableSource<R> observableSource = apply;
                ObserverResourceWrapper observerResourceWrapper = new ObserverResourceWrapper(observer);
                observableSource.subscribe(observerResourceWrapper);
                connectableObservable2.connect(new c(observerResourceWrapper));
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
    public interface g<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(d<T> dVar);
    }

    /* loaded from: classes12.dex */
    public static final class h<T> implements b<T> {

        /* renamed from: a  reason: collision with root package name */
        public final int f13969a;
        public final boolean b;

        public h(int i, boolean z) {
            this.f13969a = i;
            this.b = z;
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.b
        public g<T> call() {
            return new m(this.f13969a, this.b);
        }
    }

    /* loaded from: classes12.dex */
    public static final class i<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        public static final d[] EMPTY = new d[0];
        public static final d[] TERMINATED = new d[0];
        private static final long serialVersionUID = -533785617179540163L;
        public final g<T> buffer;
        public boolean done;
        public final AtomicReference<d[]> observers = new AtomicReference<>(EMPTY);
        public final AtomicBoolean shouldConnect = new AtomicBoolean();

        public i(g<T> gVar) {
            this.buffer = gVar;
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

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.observers.set(TERMINATED);
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.observers.get() == TERMINATED;
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.buffer.complete();
            replayFinal();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(th);
                replayFinal();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            this.buffer.next(t);
            replay();
        }

        @Override // io.reactivex.rxjava3.core.Observer
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
    public static final class j<T> implements ObservableSource<T> {
        public final AtomicReference<i<T>> h;
        public final b<T> i;

        public j(AtomicReference<i<T>> atomicReference, b<T> bVar) {
            this.h = atomicReference;
            this.i = bVar;
        }

        @Override // io.reactivex.rxjava3.core.ObservableSource
        public void subscribe(Observer<? super T> observer) {
            i<T> iVar;
            while (true) {
                iVar = this.h.get();
                if (iVar != null) {
                    break;
                }
                i<T> iVar2 = new i<>(this.i.call());
                if (this.h.compareAndSet(null, iVar2)) {
                    iVar = iVar2;
                    break;
                }
            }
            d<T> dVar = new d<>(iVar, observer);
            observer.onSubscribe(dVar);
            iVar.add(dVar);
            if (dVar.isDisposed()) {
                iVar.remove(dVar);
            } else {
                iVar.buffer.replay(dVar);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class k<T> implements b<T> {

        /* renamed from: a  reason: collision with root package name */
        public final int f13970a;
        public final long b;
        public final TimeUnit c;
        public final Scheduler d;
        public final boolean e;

        public k(int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.f13970a = i;
            this.b = j;
            this.c = timeUnit;
            this.d = scheduler;
            this.e = z;
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.b
        public g<T> call() {
            return new l(this.f13970a, this.b, this.c, this.d, this.e);
        }
    }

    /* loaded from: classes12.dex */
    public static final class l<T> extends a<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        public final int limit;
        public final long maxAge;
        public final Scheduler scheduler;
        public final TimeUnit unit;

        public l(int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            super(z);
            this.scheduler = scheduler;
            this.limit = i;
            this.maxAge = j;
            this.unit = timeUnit;
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.a
        public Object enterTransform(Object obj) {
            return new Timed(obj, this.scheduler.now(this.unit), this.unit);
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.a
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

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.a
        public Object leaveTransform(Object obj) {
            return ((Timed) obj).value();
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.a
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
                int i2 = this.size;
                if (i2 > 1) {
                    if (i2 > this.limit) {
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
                } else {
                    break;
                }
            }
            if (i != 0) {
                setFirst(fVar);
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.a
        public void truncateFinal() {
            f fVar;
            long now = this.scheduler.now(this.unit) - this.maxAge;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            int i = 0;
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (this.size <= 1 || ((Timed) fVar2.value).time() > now) {
                    break;
                }
                i++;
                this.size--;
                fVar3 = fVar2.get();
            }
            if (i != 0) {
                setFirst(fVar);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class m<T> extends a<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        public final int limit;

        public m(int i, boolean z) {
            super(z);
            this.limit = i;
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.a
        public void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class n implements b<Object> {
        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.b
        public g<Object> call() {
            return new o(16);
        }
    }

    /* loaded from: classes12.dex */
    public static final class o<T> extends ArrayList<Object> implements g<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        public volatile int size;

        public o(int i) {
            super(i);
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.g
        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.g
        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.g
        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        @Override // io.reactivex.rxjava3.internal.operators.observable.ObservableReplay.g
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

    public ObservableReplay(ObservableSource<T> observableSource, ObservableSource<T> observableSource2, AtomicReference<i<T>> atomicReference, b<T> bVar) {
        this.k = observableSource;
        this.h = observableSource2;
        this.i = atomicReference;
        this.j = bVar;
    }

    public static <T> ConnectableObservable<T> create(ObservableSource<T> observableSource, int i2, boolean z) {
        if (i2 == Integer.MAX_VALUE) {
            return createFrom(observableSource);
        }
        return d(observableSource, new h(i2, z));
    }

    public static <T> ConnectableObservable<T> createFrom(ObservableSource<? extends T> observableSource) {
        return d(observableSource, l);
    }

    public static <T> ConnectableObservable<T> d(ObservableSource<T> observableSource, b<T> bVar) {
        AtomicReference atomicReference = new AtomicReference();
        return RxJavaPlugins.onAssembly((ConnectableObservable) new ObservableReplay(new j(atomicReference, bVar), observableSource, atomicReference, bVar));
    }

    public static <U, R> Observable<R> multicastSelector(Supplier<? extends ConnectableObservable<U>> supplier, Function<? super Observable<U>, ? extends ObservableSource<R>> function) {
        return RxJavaPlugins.onAssembly(new e(supplier, function));
    }

    @Override // io.reactivex.rxjava3.observables.ConnectableObservable
    public void connect(Consumer<? super Disposable> consumer) {
        i<T> iVar;
        while (true) {
            iVar = this.i.get();
            if (iVar != null && !iVar.isDisposed()) {
                break;
            }
            i<T> iVar2 = new i<>(this.j.call());
            if (this.i.compareAndSet(iVar, iVar2)) {
                iVar = iVar2;
                break;
            }
        }
        boolean z = !iVar.shouldConnect.get() && iVar.shouldConnect.compareAndSet(false, true);
        try {
            consumer.accept(iVar);
            if (z) {
                this.h.subscribe(iVar);
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (z) {
                iVar.shouldConnect.compareAndSet(true, false);
            }
            Exceptions.throwIfFatal(th);
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    @Override // io.reactivex.rxjava3.observables.ConnectableObservable
    public void reset() {
        i<T> iVar = this.i.get();
        if (iVar == null || !iVar.isDisposed()) {
            return;
        }
        this.i.compareAndSet(iVar, null);
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.HasUpstreamObservableSource
    public ObservableSource<T> source() {
        return this.h;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public void subscribeActual(Observer<? super T> observer) {
        this.k.subscribe(observer);
    }

    public static <T> ConnectableObservable<T> create(ObservableSource<T> observableSource, long j2, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return create(observableSource, j2, timeUnit, scheduler, Integer.MAX_VALUE, z);
    }

    public static <T> ConnectableObservable<T> create(ObservableSource<T> observableSource, long j2, TimeUnit timeUnit, Scheduler scheduler, int i2, boolean z) {
        return d(observableSource, new k(i2, j2, timeUnit, scheduler, z));
    }
}
