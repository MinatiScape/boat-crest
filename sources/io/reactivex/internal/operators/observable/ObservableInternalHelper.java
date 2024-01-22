package io.reactivex.internal.operators.observable;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.observables.ConnectableObservable;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
/* loaded from: classes12.dex */
public final class ObservableInternalHelper {

    /* loaded from: classes12.dex */
    public static final class a<T> implements Callable<ConnectableObservable<T>> {
        public final Observable<T> h;
        public final int i;

        public a(Observable<T> observable, int i) {
            this.h = observable;
            this.i = i;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.h.replay(this.i);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> implements Callable<ConnectableObservable<T>> {
        public final Observable<T> h;
        public final int i;
        public final long j;
        public final TimeUnit k;
        public final Scheduler l;

        public b(Observable<T> observable, int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.h = observable;
            this.i = i;
            this.j = j;
            this.k = timeUnit;
            this.l = scheduler;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.h.replay(this.i, this.j, this.k, this.l);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T, U> implements Function<T, ObservableSource<U>> {
        public final Function<? super T, ? extends Iterable<? extends U>> h;

        public c(Function<? super T, ? extends Iterable<? extends U>> function) {
            this.h = function;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<U> apply(T t) throws Exception {
            return new ObservableFromIterable((Iterable) ObjectHelper.requireNonNull(this.h.apply(t), "The mapper returned a null Iterable"));
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

        @Override // io.reactivex.functions.Function
        public R apply(U u) throws Exception {
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

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<R> apply(T t) throws Exception {
            return new ObservableMap((ObservableSource) ObjectHelper.requireNonNull(this.i.apply(t), "The mapper returned a null ObservableSource"), new d(this.h, t));
        }
    }

    /* loaded from: classes12.dex */
    public static final class f<T, U> implements Function<T, ObservableSource<T>> {
        public final Function<? super T, ? extends ObservableSource<U>> h;

        public f(Function<? super T, ? extends ObservableSource<U>> function) {
            this.h = function;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<T> apply(T t) throws Exception {
            return new ObservableTake((ObservableSource) ObjectHelper.requireNonNull(this.h.apply(t), "The itemDelay returned a null ObservableSource"), 1L).map(Functions.justFunction(t)).defaultIfEmpty(t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class g<T> implements Action {
        public final Observer<T> h;

        public g(Observer<T> observer) {
            this.h = observer;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            this.h.onComplete();
        }
    }

    /* loaded from: classes12.dex */
    public static final class h<T> implements Consumer<Throwable> {
        public final Observer<T> h;

        public h(Observer<T> observer) {
            this.h = observer;
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            this.h.onError(th);
        }
    }

    /* loaded from: classes12.dex */
    public static final class i<T> implements Consumer<T> {
        public final Observer<T> h;

        public i(Observer<T> observer) {
            this.h = observer;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(T t) throws Exception {
            this.h.onNext(t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class j<T> implements Callable<ConnectableObservable<T>> {
        public final Observable<T> h;

        public j(Observable<T> observable) {
            this.h = observable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.h.replay();
        }
    }

    /* loaded from: classes12.dex */
    public static final class k<T, R> implements Function<Observable<T>, ObservableSource<R>> {
        public final Function<? super Observable<T>, ? extends ObservableSource<R>> h;
        public final Scheduler i;

        public k(Function<? super Observable<T>, ? extends ObservableSource<R>> function, Scheduler scheduler) {
            this.h = function;
            this.i = scheduler;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<R> apply(Observable<T> observable) throws Exception {
            return Observable.wrap((ObservableSource) ObjectHelper.requireNonNull(this.h.apply(observable), "The selector returned a null ObservableSource")).observeOn(this.i);
        }
    }

    /* loaded from: classes12.dex */
    public static final class l<T, S> implements BiFunction<S, Emitter<T>, S> {
        public final BiConsumer<S, Emitter<T>> h;

        public l(BiConsumer<S, Emitter<T>> biConsumer) {
            this.h = biConsumer;
        }

        @Override // io.reactivex.functions.BiFunction
        /* renamed from: a */
        public S apply(S s, Emitter<T> emitter) throws Exception {
            this.h.accept(s, emitter);
            return s;
        }
    }

    /* loaded from: classes12.dex */
    public static final class m<T, S> implements BiFunction<S, Emitter<T>, S> {
        public final Consumer<Emitter<T>> h;

        public m(Consumer<Emitter<T>> consumer) {
            this.h = consumer;
        }

        @Override // io.reactivex.functions.BiFunction
        /* renamed from: a */
        public S apply(S s, Emitter<T> emitter) throws Exception {
            this.h.accept(emitter);
            return s;
        }
    }

    /* loaded from: classes12.dex */
    public static final class n<T> implements Callable<ConnectableObservable<T>> {
        public final Observable<T> h;
        public final long i;
        public final TimeUnit j;
        public final Scheduler k;

        public n(Observable<T> observable, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.h = observable;
            this.i = j;
            this.j = timeUnit;
            this.k = scheduler;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.h.replay(this.i, this.j, this.k);
        }
    }

    /* loaded from: classes12.dex */
    public static final class o<T, R> implements Function<List<ObservableSource<? extends T>>, ObservableSource<? extends R>> {
        public final Function<? super Object[], ? extends R> h;

        public o(Function<? super Object[], ? extends R> function) {
            this.h = function;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public ObservableSource<? extends R> apply(List<ObservableSource<? extends T>> list) {
            return Observable.zipIterable(list, this.h, false, Observable.bufferSize());
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

    public static <T> Callable<ConnectableObservable<T>> replayCallable(Observable<T> observable) {
        return new j(observable);
    }

    public static <T, R> Function<Observable<T>, ObservableSource<R>> replayFunction(Function<? super Observable<T>, ? extends ObservableSource<R>> function, Scheduler scheduler) {
        return new k(function, scheduler);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleBiGenerator(BiConsumer<S, Emitter<T>> biConsumer) {
        return new l(biConsumer);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleGenerator(Consumer<Emitter<T>> consumer) {
        return new m(consumer);
    }

    public static <T, R> Function<List<ObservableSource<? extends T>>, ObservableSource<? extends R>> zipIterable(Function<? super Object[], ? extends R> function) {
        return new o(function);
    }

    public static <T> Callable<ConnectableObservable<T>> replayCallable(Observable<T> observable, int i2) {
        return new a(observable, i2);
    }

    public static <T> Callable<ConnectableObservable<T>> replayCallable(Observable<T> observable, int i2, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return new b(observable, i2, j2, timeUnit, scheduler);
    }

    public static <T> Callable<ConnectableObservable<T>> replayCallable(Observable<T> observable, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return new n(observable, j2, timeUnit, scheduler);
    }
}
