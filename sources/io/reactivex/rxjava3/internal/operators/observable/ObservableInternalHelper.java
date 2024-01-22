package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class ObservableInternalHelper {

    /* loaded from: classes12.dex */
    public static final class a<T> implements Supplier<ConnectableObservable<T>> {
        public final Observable<T> h;
        public final int i;
        public final boolean j;

        public a(Observable<T> observable, int i, boolean z) {
            this.h = observable;
            this.i = i;
            this.j = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public ConnectableObservable<T> get() {
            return this.h.replay(this.i, this.j);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> implements Supplier<ConnectableObservable<T>> {
        public final Observable<T> h;
        public final int i;
        public final long j;
        public final TimeUnit k;
        public final Scheduler l;
        public final boolean m;

        public b(Observable<T> observable, int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.h = observable;
            this.i = i;
            this.j = j;
            this.k = timeUnit;
            this.l = scheduler;
            this.m = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public ConnectableObservable<T> get() {
            return this.h.replay(this.i, this.j, this.k, this.l, this.m);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T, U> implements Function<T, ObservableSource<U>> {
        public final Function<? super T, ? extends Iterable<? extends U>> h;

        public c(Function<? super T, ? extends Iterable<? extends U>> function) {
            this.h = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: a */
        public ObservableSource<U> apply(T t) throws Throwable {
            Iterable<? extends U> apply = this.h.apply(t);
            Objects.requireNonNull(apply, "The mapper returned a null Iterable");
            return new ObservableFromIterable(apply);
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<U, R, T> implements Function<U, R> {
        public final BiFunction<? super T, ? super U, ? extends R> h;
        public final T i;

        public d(BiFunction<? super T, ? super U, ? extends R> biFunction, T t) {
            this.h = biFunction;
            this.i = t;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        public R apply(U u) throws Throwable {
            return this.h.apply((T) this.i, u);
        }
    }

    /* loaded from: classes12.dex */
    public static final class e<T, R, U> implements Function<T, ObservableSource<R>> {
        public final BiFunction<? super T, ? super U, ? extends R> h;
        public final Function<? super T, ? extends ObservableSource<? extends U>> i;

        public e(BiFunction<? super T, ? super U, ? extends R> biFunction, Function<? super T, ? extends ObservableSource<? extends U>> function) {
            this.h = biFunction;
            this.i = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: a */
        public ObservableSource<R> apply(T t) throws Throwable {
            ObservableSource<? extends U> apply = this.i.apply(t);
            Objects.requireNonNull(apply, "The mapper returned a null ObservableSource");
            return new ObservableMap(apply, new d(this.h, t));
        }
    }

    /* loaded from: classes12.dex */
    public static final class f<T, U> implements Function<T, ObservableSource<T>> {
        public final Function<? super T, ? extends ObservableSource<U>> h;

        public f(Function<? super T, ? extends ObservableSource<U>> function) {
            this.h = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: a */
        public ObservableSource<T> apply(T t) throws Throwable {
            ObservableSource<U> apply = this.h.apply(t);
            Objects.requireNonNull(apply, "The itemDelay returned a null ObservableSource");
            return new ObservableTake(apply, 1L).map(Functions.justFunction(t)).defaultIfEmpty(t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class g<T> implements Action {
        public final Observer<T> h;

        public g(Observer<T> observer) {
            this.h = observer;
        }

        @Override // io.reactivex.rxjava3.functions.Action
        public void run() {
            this.h.onComplete();
        }
    }

    /* loaded from: classes12.dex */
    public static final class h<T> implements Consumer<Throwable> {
        public final Observer<T> h;

        public h(Observer<T> observer) {
            this.h = observer;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) {
            this.h.onError(th);
        }
    }

    /* loaded from: classes12.dex */
    public static final class i<T> implements Consumer<T> {
        public final Observer<T> h;

        public i(Observer<T> observer) {
            this.h = observer;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(T t) {
            this.h.onNext(t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class j<T> implements Supplier<ConnectableObservable<T>> {
        public final Observable<T> h;

        public j(Observable<T> observable) {
            this.h = observable;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public ConnectableObservable<T> get() {
            return this.h.replay();
        }
    }

    /* loaded from: classes12.dex */
    public static final class k<T, S> implements BiFunction<S, Emitter<T>, S> {
        public final BiConsumer<S, Emitter<T>> h;

        public k(BiConsumer<S, Emitter<T>> biConsumer) {
            this.h = biConsumer;
        }

        @Override // io.reactivex.rxjava3.functions.BiFunction
        /* renamed from: a */
        public S apply(S s, Emitter<T> emitter) throws Throwable {
            this.h.accept(s, emitter);
            return s;
        }
    }

    /* loaded from: classes12.dex */
    public static final class l<T, S> implements BiFunction<S, Emitter<T>, S> {
        public final Consumer<Emitter<T>> h;

        public l(Consumer<Emitter<T>> consumer) {
            this.h = consumer;
        }

        @Override // io.reactivex.rxjava3.functions.BiFunction
        /* renamed from: a */
        public S apply(S s, Emitter<T> emitter) throws Throwable {
            this.h.accept(emitter);
            return s;
        }
    }

    /* loaded from: classes12.dex */
    public static final class m<T> implements Supplier<ConnectableObservable<T>> {
        public final Observable<T> h;
        public final long i;
        public final TimeUnit j;
        public final Scheduler k;
        public final boolean l;

        public m(Observable<T> observable, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.h = observable;
            this.i = j;
            this.j = timeUnit;
            this.k = scheduler;
            this.l = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public ConnectableObservable<T> get() {
            return this.h.replay(this.i, this.j, this.k, this.l);
        }
    }

    public ObservableInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, U> Function<T, ObservableSource<U>> flatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> function) {
        return new c(function);
    }

    public static <T, U, R> Function<T, ObservableSource<R>> flatMapWithCombiner(Function<? super T, ? extends ObservableSource<? extends U>> function, BiFunction<? super T, ? super U, ? extends R> biFunction) {
        return new e(biFunction, function);
    }

    public static <T, U> Function<T, ObservableSource<T>> itemDelay(Function<? super T, ? extends ObservableSource<U>> function) {
        return new f(function);
    }

    public static <T> Action observerOnComplete(Observer<T> observer) {
        return new g(observer);
    }

    public static <T> Consumer<Throwable> observerOnError(Observer<T> observer) {
        return new h(observer);
    }

    public static <T> Consumer<T> observerOnNext(Observer<T> observer) {
        return new i(observer);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(Observable<T> observable) {
        return new j(observable);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleBiGenerator(BiConsumer<S, Emitter<T>> biConsumer) {
        return new k(biConsumer);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleGenerator(Consumer<Emitter<T>> consumer) {
        return new l(consumer);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(Observable<T> observable, int i2, boolean z) {
        return new a(observable, i2, z);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(Observable<T> observable, int i2, long j2, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return new b(observable, i2, j2, timeUnit, scheduler, z);
    }

    public static <T> Supplier<ConnectableObservable<T>> replaySupplier(Observable<T> observable, long j2, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return new m(observable, j2, timeUnit, scheduler, z);
    }
}
