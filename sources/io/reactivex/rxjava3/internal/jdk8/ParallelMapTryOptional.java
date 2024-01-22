package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.parallel.ParallelFailureHandling;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Optional;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class ParallelMapTryOptional<T, R> extends ParallelFlowable<R> {

    /* renamed from: a  reason: collision with root package name */
    public final ParallelFlowable<T> f13960a;
    public final Function<? super T, Optional<? extends R>> b;
    public final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> c;

    /* loaded from: classes12.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f13961a;

        static {
            int[] iArr = new int[ParallelFailureHandling.values().length];
            f13961a = iArr;
            try {
                iArr[ParallelFailureHandling.RETRY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13961a[ParallelFailureHandling.SKIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13961a[ParallelFailureHandling.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, R> implements ConditionalSubscriber<T>, Subscription {
        public final ConditionalSubscriber<? super R> h;
        public final Function<? super T, Optional<? extends R>> i;
        public final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> j;
        public Subscription k;
        public boolean l;

        public b(ConditionalSubscriber<? super R> conditionalSubscriber, Function<? super T, Optional<? extends R>> function, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            this.h = conditionalSubscriber;
            this.i = function;
            this.j = biFunction;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.k.cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.h.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.l) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.l = true;
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (tryOnNext(t) || this.l) {
                return;
            }
            this.k.request(1L);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.k, subscription)) {
                this.k = subscription;
                this.h.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.k.request(j);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x004f  */
        @Override // io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean tryOnNext(T r10) {
            /*
                r9 = this;
                boolean r0 = r9.l
                r1 = 0
                if (r0 == 0) goto L6
                return r1
            L6:
                r2 = 0
            L8:
                r0 = 1
                io.reactivex.rxjava3.functions.Function<? super T, java.util.Optional<? extends R>> r4 = r9.i     // Catch: java.lang.Throwable -> L2a
                java.lang.Object r4 = r4.apply(r10)     // Catch: java.lang.Throwable -> L2a
                java.lang.String r5 = "The mapper returned a null Optional"
                java.util.Objects.requireNonNull(r4, r5)     // Catch: java.lang.Throwable -> L2a
                java.util.Optional r4 = (java.util.Optional) r4     // Catch: java.lang.Throwable -> L2a
                boolean r10 = r4.isPresent()
                if (r10 == 0) goto L29
                io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber<? super R> r10 = r9.h
                java.lang.Object r2 = r4.get()
                boolean r10 = r10.tryOnNext(r2)
                if (r10 == 0) goto L29
                r1 = r0
            L29:
                return r1
            L2a:
                r4 = move-exception
                io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r4)
                r5 = 2
                io.reactivex.rxjava3.functions.BiFunction<? super java.lang.Long, ? super java.lang.Throwable, io.reactivex.rxjava3.parallel.ParallelFailureHandling> r6 = r9.j     // Catch: java.lang.Throwable -> L60
                r7 = 1
                long r2 = r2 + r7
                java.lang.Long r7 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L60
                java.lang.Object r6 = r6.apply(r7, r4)     // Catch: java.lang.Throwable -> L60
                java.lang.String r7 = "The errorHandler returned a null ParallelFailureHandling"
                java.util.Objects.requireNonNull(r6, r7)     // Catch: java.lang.Throwable -> L60
                io.reactivex.rxjava3.parallel.ParallelFailureHandling r6 = (io.reactivex.rxjava3.parallel.ParallelFailureHandling) r6     // Catch: java.lang.Throwable -> L60
                int[] r7 = io.reactivex.rxjava3.internal.jdk8.ParallelMapTryOptional.a.f13961a
                int r6 = r6.ordinal()
                r6 = r7[r6]
                if (r6 == r0) goto L8
                if (r6 == r5) goto L5f
                r10 = 3
                if (r6 == r10) goto L59
                r9.cancel()
                r9.onError(r4)
                return r1
            L59:
                r9.cancel()
                r9.onComplete()
            L5f:
                return r1
            L60:
                r10 = move-exception
                io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r10)
                r9.cancel()
                io.reactivex.rxjava3.exceptions.CompositeException r2 = new io.reactivex.rxjava3.exceptions.CompositeException
                java.lang.Throwable[] r3 = new java.lang.Throwable[r5]
                r3[r1] = r4
                r3[r0] = r10
                r2.<init>(r3)
                r9.onError(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.jdk8.ParallelMapTryOptional.b.tryOnNext(java.lang.Object):boolean");
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T, R> implements ConditionalSubscriber<T>, Subscription {
        public final Subscriber<? super R> h;
        public final Function<? super T, Optional<? extends R>> i;
        public final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> j;
        public Subscription k;
        public boolean l;

        public c(Subscriber<? super R> subscriber, Function<? super T, Optional<? extends R>> function, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            this.h = subscriber;
            this.i = function;
            this.j = biFunction;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.k.cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.h.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.l) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.l = true;
            this.h.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (tryOnNext(t) || this.l) {
                return;
            }
            this.k.request(1L);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.k, subscription)) {
                this.k = subscription;
                this.h.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.k.request(j);
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x004c  */
        @Override // io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean tryOnNext(T r10) {
            /*
                r9 = this;
                boolean r0 = r9.l
                r1 = 0
                if (r0 == 0) goto L6
                return r1
            L6:
                r2 = 0
            L8:
                r0 = 1
                io.reactivex.rxjava3.functions.Function<? super T, java.util.Optional<? extends R>> r4 = r9.i     // Catch: java.lang.Throwable -> L27
                java.lang.Object r4 = r4.apply(r10)     // Catch: java.lang.Throwable -> L27
                java.lang.String r5 = "The mapper returned a null Optional"
                java.util.Objects.requireNonNull(r4, r5)     // Catch: java.lang.Throwable -> L27
                java.util.Optional r4 = (java.util.Optional) r4     // Catch: java.lang.Throwable -> L27
                boolean r10 = r4.isPresent()
                if (r10 == 0) goto L26
                org.reactivestreams.Subscriber<? super R> r10 = r9.h
                java.lang.Object r1 = r4.get()
                r10.onNext(r1)
                return r0
            L26:
                return r1
            L27:
                r4 = move-exception
                io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r4)
                r5 = 2
                io.reactivex.rxjava3.functions.BiFunction<? super java.lang.Long, ? super java.lang.Throwable, io.reactivex.rxjava3.parallel.ParallelFailureHandling> r6 = r9.j     // Catch: java.lang.Throwable -> L5d
                r7 = 1
                long r2 = r2 + r7
                java.lang.Long r7 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L5d
                java.lang.Object r6 = r6.apply(r7, r4)     // Catch: java.lang.Throwable -> L5d
                java.lang.String r7 = "The errorHandler returned a null ParallelFailureHandling"
                java.util.Objects.requireNonNull(r6, r7)     // Catch: java.lang.Throwable -> L5d
                io.reactivex.rxjava3.parallel.ParallelFailureHandling r6 = (io.reactivex.rxjava3.parallel.ParallelFailureHandling) r6     // Catch: java.lang.Throwable -> L5d
                int[] r7 = io.reactivex.rxjava3.internal.jdk8.ParallelMapTryOptional.a.f13961a
                int r6 = r6.ordinal()
                r6 = r7[r6]
                if (r6 == r0) goto L8
                if (r6 == r5) goto L5c
                r10 = 3
                if (r6 == r10) goto L56
                r9.cancel()
                r9.onError(r4)
                return r1
            L56:
                r9.cancel()
                r9.onComplete()
            L5c:
                return r1
            L5d:
                r10 = move-exception
                io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r10)
                r9.cancel()
                io.reactivex.rxjava3.exceptions.CompositeException r2 = new io.reactivex.rxjava3.exceptions.CompositeException
                java.lang.Throwable[] r3 = new java.lang.Throwable[r5]
                r3[r1] = r4
                r3[r0] = r10
                r2.<init>(r3)
                r9.onError(r2)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.jdk8.ParallelMapTryOptional.c.tryOnNext(java.lang.Object):boolean");
        }
    }

    public ParallelMapTryOptional(ParallelFlowable<T> parallelFlowable, Function<? super T, Optional<? extends R>> function, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
        this.f13960a = parallelFlowable;
        this.b = function;
        this.c = biFunction;
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public int parallelism() {
        return this.f13960a.parallelism();
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super R>[] subscriberArr) {
        if (validate(subscriberArr)) {
            int length = subscriberArr.length;
            Subscriber<? super T>[] subscriberArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                Subscriber<? super R> subscriber = subscriberArr[i];
                if (subscriber instanceof ConditionalSubscriber) {
                    subscriberArr2[i] = new b((ConditionalSubscriber) subscriber, this.b, this.c);
                } else {
                    subscriberArr2[i] = new c(subscriber, this.b, this.c);
                }
            }
            this.f13960a.subscribe(subscriberArr2);
        }
    }
}
