package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableInternalHelper {

    /* loaded from: classes12.dex */
    public enum RequestMax implements Consumer<Subscription> {
        INSTANCE;

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Subscription subscription) {
            subscription.request(Long.MAX_VALUE);
        }
    }

    /* loaded from: classes12.dex */
    public static final class a<T> implements Supplier<ConnectableFlowable<T>> {
        public final Flowable<T> h;
        public final int i;
        public final boolean j;

        public a(Flowable<T> flowable, int i, boolean z) {
            this.h = flowable;
            this.i = i;
            this.j = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public ConnectableFlowable<T> get() {
            return this.h.replay(this.i, this.j);
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> implements Supplier<ConnectableFlowable<T>> {
        public final Flowable<T> h;
        public final int i;
        public final long j;
        public final TimeUnit k;
        public final Scheduler l;
        public final boolean m;

        public b(Flowable<T> flowable, int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.h = flowable;
            this.i = i;
            this.j = j;
            this.k = timeUnit;
            this.l = scheduler;
            this.m = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public ConnectableFlowable<T> get() {
            return this.h.replay(this.i, this.j, this.k, this.l, this.m);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T, U> implements Function<T, Publisher<U>> {
        public final Function<? super T, ? extends Iterable<? extends U>> h;

        public c(Function<? super T, ? extends Iterable<? extends U>> function) {
            this.h = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: a */
        public Publisher<U> apply(T t) throws Throwable {
            Iterable<? extends U> apply = this.h.apply(t);
            Objects.requireNonNull(apply, "The mapper returned a null Iterable");
            return new FlowableFromIterable(apply);
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
    public static final class e<T, R, U> implements Function<T, Publisher<R>> {
        public final BiFunction<? super T, ? super U, ? extends R> h;
        public final Function<? super T, ? extends Publisher<? extends U>> i;

        public e(BiFunction<? super T, ? super U, ? extends R> biFunction, Function<? super T, ? extends Publisher<? extends U>> function) {
            this.h = biFunction;
            this.i = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: a */
        public Publisher<R> apply(T t) throws Throwable {
            Publisher<? extends U> apply = this.i.apply(t);
            Objects.requireNonNull(apply, "The mapper returned a null Publisher");
            return new FlowableMapPublisher(apply, new d(this.h, t));
        }
    }

    /* loaded from: classes12.dex */
    public static final class f<T, U> implements Function<T, Publisher<T>> {
        public final Function<? super T, ? extends Publisher<U>> h;

        public f(Function<? super T, ? extends Publisher<U>> function) {
            this.h = function;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: a */
        public Publisher<T> apply(T t) throws Throwable {
            Publisher<U> apply = this.h.apply(t);
            Objects.requireNonNull(apply, "The itemDelay returned a null Publisher");
            return new FlowableTakePublisher(apply, 1L).map(Functions.justFunction(t)).defaultIfEmpty(t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class g<T> implements Supplier<ConnectableFlowable<T>> {
        public final Flowable<T> h;

        public g(Flowable<T> flowable) {
            this.h = flowable;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public ConnectableFlowable<T> get() {
            return this.h.replay();
        }
    }

    /* loaded from: classes12.dex */
    public static final class h<T, S> implements BiFunction<S, Emitter<T>, S> {
        public final BiConsumer<S, Emitter<T>> h;

        public h(BiConsumer<S, Emitter<T>> biConsumer) {
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
    public static final class i<T, S> implements BiFunction<S, Emitter<T>, S> {
        public final Consumer<Emitter<T>> h;

        public i(Consumer<Emitter<T>> consumer) {
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
    public static final class j<T> implements Action {
        public final Subscriber<T> h;

        public j(Subscriber<T> subscriber) {
            this.h = subscriber;
        }

        @Override // io.reactivex.rxjava3.functions.Action
        public void run() {
            this.h.onComplete();
        }
    }

    /* loaded from: classes12.dex */
    public static final class k<T> implements Consumer<Throwable> {
        public final Subscriber<T> h;

        public k(Subscriber<T> subscriber) {
            this.h = subscriber;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        /* renamed from: a */
        public void accept(Throwable th) {
            this.h.onError(th);
        }
    }

    /* loaded from: classes12.dex */
    public static final class l<T> implements Consumer<T> {
        public final Subscriber<T> h;

        public l(Subscriber<T> subscriber) {
            this.h = subscriber;
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(T t) {
            this.h.onNext(t);
        }
    }

    /* loaded from: classes12.dex */
    public static final class m<T> implements Supplier<ConnectableFlowable<T>> {
        public final Flowable<T> h;
        public final long i;
        public final TimeUnit j;
        public final Scheduler k;
        public final boolean l;

        public m(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.h = flowable;
            this.i = j;
            this.j = timeUnit;
            this.k = scheduler;
            this.l = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public ConnectableFlowable<T> get() {
            return this.h.replay(this.i, this.j, this.k, this.l);
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

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(Flowable<T> flowable) {
        return new g(flowable);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleBiGenerator(BiConsumer<S, Emitter<T>> biConsumer) {
        return new h(biConsumer);
    }

    public static <T, S> BiFunction<S, Emitter<T>, S> simpleGenerator(Consumer<Emitter<T>> consumer) {
        return new i(consumer);
    }

    public static <T> Action subscriberOnComplete(Subscriber<T> subscriber) {
        return new j(subscriber);
    }

    public static <T> Consumer<Throwable> subscriberOnError(Subscriber<T> subscriber) {
        return new k(subscriber);
    }

    public static <T> Consumer<T> subscriberOnNext(Subscriber<T> subscriber) {
        return new l(subscriber);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(Flowable<T> flowable, int i2, boolean z) {
        return new a(flowable, i2, z);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(Flowable<T> flowable, int i2, long j2, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return new b(flowable, i2, j2, timeUnit, scheduler, z);
    }

    public static <T> Supplier<ConnectableFlowable<T>> replaySupplier(Flowable<T> flowable, long j2, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return new m(flowable, j2, timeUnit, scheduler, z);
    }
}
