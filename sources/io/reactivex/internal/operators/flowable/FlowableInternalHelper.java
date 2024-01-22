package io.reactivex.internal.operators.flowable;

import io.reactivex.Emitter;
import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.Functions;
import io.reactivex.internal.functions.ObjectHelper;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableInternalHelper {

    /* loaded from: classes12.dex */
    public enum RequestMax implements Consumer<Subscription> {
        INSTANCE;

        @Override // io.reactivex.functions.Consumer
        public void accept(Subscription subscription) throws Exception {
            subscription.request(Long.MAX_VALUE);
        }
    }

    /* loaded from: classes12.dex */
    public static final class a<T> implements Callable<ConnectableFlowable<T>> {
        public final Flowable<T> h;
        public final int i;

        public a(Flowable<T> flowable, int i) {
            this.h = flowable;
            this.i = i;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableFlowable<T> call() {
            return this.h.replay(this.i);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> implements Callable<ConnectableFlowable<T>> {
        public final Flowable<T> h;
        public final int i;
        public final long j;
        public final TimeUnit k;
        public final Scheduler l;

        public b(Flowable<T> flowable, int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.h = flowable;
            this.i = i;
            this.j = j;
            this.k = timeUnit;
            this.l = scheduler;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableFlowable<T> call() {
            return this.h.replay(this.i, this.j, this.k, this.l);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T, U> implements Function<T, Publisher<U>> {
        public final Function<? super T, ? extends Iterable<? extends U>> h;

        public c(Function<? super T, ? extends Iterable<? extends U>> function) {
            this.h = function;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Publisher<U> apply(T t) throws Exception {
            return new FlowableFromIterable((Iterable) ObjectHelper.requireNonNull(this.h.apply(t), "The mapper returned a null Iterable"));
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
    public static final class e<T, R, U> implements Function<T, Publisher<R>> {
        public final BiFunction<? super T, ? super U, ? extends R> h;
        public final Function<? super T, ? extends Publisher<? extends U>> i;

        public e(BiFunction<? super T, ? super U, ? extends R> biFunction, Function<? super T, ? extends Publisher<? extends U>> function) {
            this.h = biFunction;
            this.i = function;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Publisher<R> apply(T t) throws Exception {
            return new FlowableMapPublisher((Publisher) ObjectHelper.requireNonNull(this.i.apply(t), "The mapper returned a null Publisher"), new d(this.h, t));
        }
    }

    /* loaded from: classes12.dex */
    public static final class f<T, U> implements Function<T, Publisher<T>> {
        public final Function<? super T, ? extends Publisher<U>> h;

        public f(Function<? super T, ? extends Publisher<U>> function) {
            this.h = function;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Publisher<T> apply(T t) throws Exception {
            return new FlowableTakePublisher((Publisher) ObjectHelper.requireNonNull(this.h.apply(t), "The itemDelay returned a null Publisher"), 1L).map(Functions.justFunction(t)).defaultIfEmpty(t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class g<T> implements Callable<ConnectableFlowable<T>> {
        public final Flowable<T> h;

        public g(Flowable<T> flowable) {
            this.h = flowable;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableFlowable<T> call() {
            return this.h.replay();
        }
    }

    /* loaded from: classes12.dex */
    public static final class h<T, R> implements Function<Flowable<T>, Publisher<R>> {
        public final Function<? super Flowable<T>, ? extends Publisher<R>> h;
        public final Scheduler i;

        public h(Function<? super Flowable<T>, ? extends Publisher<R>> function, Scheduler scheduler) {
            this.h = function;
            this.i = scheduler;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Publisher<R> apply(Flowable<T> flowable) throws Exception {
            return Flowable.fromPublisher((Publisher) ObjectHelper.requireNonNull(this.h.apply(flowable), "The selector returned a null Publisher")).observeOn(this.i);
        }
    }

    /* loaded from: classes12.dex */
    public static final class i<T, S> implements BiFunction<S, Emitter<T>, S> {
        public final BiConsumer<S, Emitter<T>> h;

        public i(BiConsumer<S, Emitter<T>> biConsumer) {
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
    public static final class j<T, S> implements BiFunction<S, Emitter<T>, S> {
        public final Consumer<Emitter<T>> h;

        public j(Consumer<Emitter<T>> consumer) {
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
    public static final class k<T> implements Action {
        public final Subscriber<T> h;

        public k(Subscriber<T> subscriber) {
            this.h = subscriber;
        }

        @Override // io.reactivex.functions.Action
        public void run() throws Exception {
            this.h.onComplete();
        }
    }

    /* loaded from: classes12.dex */
    public static final class l<T> implements Consumer<Throwable> {
        public final Subscriber<T> h;

        public l(Subscriber<T> subscriber) {
            this.h = subscriber;
        }

        @Override // io.reactivex.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) throws Exception {
            this.h.onError(th);
        }
    }

    /* loaded from: classes12.dex */
    public static final class m<T> implements Consumer<T> {
        public final Subscriber<T> h;

        public m(Subscriber<T> subscriber) {
            this.h = subscriber;
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(T t) throws Exception {
            this.h.onNext(t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class n<T> implements Callable<ConnectableFlowable<T>> {
        public final Flowable<T> h;
        public final long i;
        public final TimeUnit j;
        public final Scheduler k;

        public n(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.h = flowable;
            this.i = j;
            this.j = timeUnit;
            this.k = scheduler;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableFlowable<T> call() {
            return this.h.replay(this.i, this.j, this.k);
        }
    }

    /* loaded from: classes12.dex */
    public static final class o<T, R> implements Function<List<Publisher<? extends T>>, Publisher<? extends R>> {
        public final Function<? super Object[], ? extends R> h;

        public o(Function<? super Object[], ? extends R> function) {
            this.h = function;
        }

        @Override // io.reactivex.functions.Function
        /* renamed from: a */
        public Publisher<? extends R> apply(List<Publisher<? extends T>> list) {
            return Flowable.zipIterable(list, this.h, false, Flowable.bufferSize());
        }
    }

    public FlowableInternalHelper() {
        throw new IllegalStateException("No instances!");
    }

    public static <T, U> Function<T, Publisher<U>> flatMapIntoIterable(Function<? super T, ? extends Iterable<? extends U>> function) {
        return new c(function);
    }

    public static <T, U, R> Function<T, Publisher<R>> flatMapWithCombiner(Function<? super T, ? extends Publisher<? extends U>> function, BiFunction<? super T, ? super U, ? extends R> biFunction) {
        return new e(biFunction, function);
    }

    public static <T, U> Function<T, Publisher<T>> itemDelay(Function<? super T, ? extends Publisher<U>> function) {
        return new f(function);
    }

    public static <T> Callable<ConnectableFlowable<T>> replayCallable(Flowable<T> flowable) {
        return new g(flowable);
    }

    public static <T, R> Function<Flowable<T>, Publisher<R>> replayFunction(Function<? super Flowable<T>, ? extends Publisher<R>> function, Scheduler scheduler) {
        return new h(function, scheduler);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleBiGenerator(BiConsumer<S, Emitter<T>> biConsumer) {
        return new i(biConsumer);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleGenerator(Consumer<Emitter<T>> consumer) {
        return new j(consumer);
    }

    public static <T> Action subscriberOnComplete(Subscriber<T> subscriber) {
        return new k(subscriber);
    }

    public static <T> Consumer<Throwable> subscriberOnError(Subscriber<T> subscriber) {
        return new l(subscriber);
    }

    public static <T> Consumer<T> subscriberOnNext(Subscriber<T> subscriber) {
        return new m(subscriber);
    }

    public static <T, R> Function<List<Publisher<? extends T>>, Publisher<? extends R>> zipIterable(Function<? super Object[], ? extends R> function) {
        return new o(function);
    }

    public static <T> Callable<ConnectableFlowable<T>> replayCallable(Flowable<T> flowable, int i2) {
        return new a(flowable, i2);
    }

    public static <T> Callable<ConnectableFlowable<T>> replayCallable(Flowable<T> flowable, int i2, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return new b(flowable, i2, j2, timeUnit, scheduler);
    }

    public static <T> Callable<ConnectableFlowable<T>> replayCallable(Flowable<T> flowable, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return new n(flowable, j2, timeUnit, scheduler);
    }
}
