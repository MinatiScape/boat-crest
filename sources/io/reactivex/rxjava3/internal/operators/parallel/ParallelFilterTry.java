package io.reactivex.rxjava3.internal.operators.parallel;

import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.parallel.ParallelFailureHandling;
import io.reactivex.rxjava3.parallel.ParallelFlowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class ParallelFilterTry<T> extends ParallelFlowable<T> {

    /* renamed from: a  reason: collision with root package name */
    public final ParallelFlowable<T> f13977a;
    public final Predicate<? super T> b;
    public final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> c;

    /* loaded from: classes12.dex */
    public static /* synthetic */ class a {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f13978a;

        static {
            int[] iArr = new int[ParallelFailureHandling.values().length];
            f13978a = iArr;
            try {
                iArr[ParallelFailureHandling.RETRY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f13978a[ParallelFailureHandling.SKIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f13978a[ParallelFailureHandling.STOP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public static abstract class b<T> implements ConditionalSubscriber<T>, Subscription {
        public final Predicate<? super T> h;
        public final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> i;
        public Subscription j;
        public boolean k;

        public b(Predicate<? super T> predicate, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            this.h = predicate;
            this.i = biFunction;
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            this.j.cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public final void onNext(T t) {
            if (tryOnNext(t) || this.k) {
                return;
            }
            this.j.request(1L);
        }

        @Override // org.reactivestreams.Subscription
        public final void request(long j) {
            this.j.request(j);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T> extends b<T> {
        public final ConditionalSubscriber<? super T> l;

        public c(ConditionalSubscriber<? super T> conditionalSubscriber, Predicate<? super T> predicate, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            super(predicate, biFunction);
            this.l = conditionalSubscriber;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.k) {
                return;
            }
            this.k = true;
            this.l.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.k) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.k = true;
            this.l.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.j, subscription)) {
                this.j = subscription;
                this.l.onSubscribe(this);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:18:0x003f  */
        @Override // io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean tryOnNext(T r10) {
            /*
                r9 = this;
                boolean r0 = r9.k
                r1 = 0
                if (r0 != 0) goto L65
                r2 = 0
            L7:
                r0 = 1
                io.reactivex.rxjava3.functions.Predicate<? super T> r4 = r9.h     // Catch: java.lang.Throwable -> L1a
                boolean r2 = r4.test(r10)     // Catch: java.lang.Throwable -> L1a
                if (r2 == 0) goto L19
                io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber<? super T> r2 = r9.l
                boolean r10 = r2.tryOnNext(r10)
                if (r10 == 0) goto L19
                r1 = r0
            L19:
                return r1
            L1a:
                r4 = move-exception
                io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r4)
                r5 = 2
                io.reactivex.rxjava3.functions.BiFunction<? super java.lang.Long, ? super java.lang.Throwable, io.reactivex.rxjava3.parallel.ParallelFailureHandling> r6 = r9.i     // Catch: java.lang.Throwable -> L50
                r7 = 1
                long r2 = r2 + r7
                java.lang.Long r7 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L50
                java.lang.Object r6 = r6.apply(r7, r4)     // Catch: java.lang.Throwable -> L50
                java.lang.String r7 = "The errorHandler returned a null ParallelFailureHandling"
                java.util.Objects.requireNonNull(r6, r7)     // Catch: java.lang.Throwable -> L50
                io.reactivex.rxjava3.parallel.ParallelFailureHandling r6 = (io.reactivex.rxjava3.parallel.ParallelFailureHandling) r6     // Catch: java.lang.Throwable -> L50
                int[] r7 = io.reactivex.rxjava3.internal.operators.parallel.ParallelFilterTry.a.f13978a
                int r6 = r6.ordinal()
                r6 = r7[r6]
                if (r6 == r0) goto L7
                if (r6 == r5) goto L4f
                r10 = 3
                if (r6 == r10) goto L49
                r9.cancel()
                r9.onError(r4)
                return r1
            L49:
                r9.cancel()
                r9.onComplete()
            L4f:
                return r1
            L50:
                r10 = move-exception
                io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r10)
                r9.cancel()
                io.reactivex.rxjava3.exceptions.CompositeException r2 = new io.reactivex.rxjava3.exceptions.CompositeException
                java.lang.Throwable[] r3 = new java.lang.Throwable[r5]
                r3[r1] = r4
                r3[r0] = r10
                r2.<init>(r3)
                r9.onError(r2)
            L65:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.parallel.ParallelFilterTry.c.tryOnNext(java.lang.Object):boolean");
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<T> extends b<T> {
        public final Subscriber<? super T> l;

        public d(Subscriber<? super T> subscriber, Predicate<? super T> predicate, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
            super(predicate, biFunction);
            this.l = subscriber;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.k) {
                return;
            }
            this.k = true;
            this.l.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.k) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.k = true;
            this.l.onError(th);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.j, subscription)) {
                this.j = subscription;
                this.l.onSubscribe(this);
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
        @Override // io.reactivex.rxjava3.internal.fuseable.ConditionalSubscriber
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean tryOnNext(T r10) {
            /*
                r9 = this;
                boolean r0 = r9.k
                r1 = 0
                if (r0 != 0) goto L62
                r2 = 0
            L7:
                r0 = 1
                io.reactivex.rxjava3.functions.Predicate<? super T> r4 = r9.h     // Catch: java.lang.Throwable -> L17
                boolean r2 = r4.test(r10)     // Catch: java.lang.Throwable -> L17
                if (r2 == 0) goto L16
                org.reactivestreams.Subscriber<? super T> r1 = r9.l
                r1.onNext(r10)
                return r0
            L16:
                return r1
            L17:
                r4 = move-exception
                io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r4)
                r5 = 2
                io.reactivex.rxjava3.functions.BiFunction<? super java.lang.Long, ? super java.lang.Throwable, io.reactivex.rxjava3.parallel.ParallelFailureHandling> r6 = r9.i     // Catch: java.lang.Throwable -> L4d
                r7 = 1
                long r2 = r2 + r7
                java.lang.Long r7 = java.lang.Long.valueOf(r2)     // Catch: java.lang.Throwable -> L4d
                java.lang.Object r6 = r6.apply(r7, r4)     // Catch: java.lang.Throwable -> L4d
                java.lang.String r7 = "The errorHandler returned a null ParallelFailureHandling"
                java.util.Objects.requireNonNull(r6, r7)     // Catch: java.lang.Throwable -> L4d
                io.reactivex.rxjava3.parallel.ParallelFailureHandling r6 = (io.reactivex.rxjava3.parallel.ParallelFailureHandling) r6     // Catch: java.lang.Throwable -> L4d
                int[] r7 = io.reactivex.rxjava3.internal.operators.parallel.ParallelFilterTry.a.f13978a
                int r6 = r6.ordinal()
                r6 = r7[r6]
                if (r6 == r0) goto L7
                if (r6 == r5) goto L4c
                r10 = 3
                if (r6 == r10) goto L46
                r9.cancel()
                r9.onError(r4)
                return r1
            L46:
                r9.cancel()
                r9.onComplete()
            L4c:
                return r1
            L4d:
                r10 = move-exception
                io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r10)
                r9.cancel()
                io.reactivex.rxjava3.exceptions.CompositeException r2 = new io.reactivex.rxjava3.exceptions.CompositeException
                java.lang.Throwable[] r3 = new java.lang.Throwable[r5]
                r3[r1] = r4
                r3[r0] = r10
                r2.<init>(r3)
                r9.onError(r2)
            L62:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.parallel.ParallelFilterTry.d.tryOnNext(java.lang.Object):boolean");
        }
    }

    public ParallelFilterTry(ParallelFlowable<T> parallelFlowable, Predicate<? super T> predicate, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> biFunction) {
        this.f13977a = parallelFlowable;
        this.b = predicate;
        this.c = biFunction;
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public int parallelism() {
        return this.f13977a.parallelism();
    }

    @Override // io.reactivex.rxjava3.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super T>[] subscriberArr) {
        if (validate(subscriberArr)) {
            int length = subscriberArr.length;
            Subscriber<? super T>[] subscriberArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                Subscriber<? super T> subscriber = subscriberArr[i];
                if (subscriber instanceof ConditionalSubscriber) {
                    subscriberArr2[i] = new c((ConditionalSubscriber) subscriber, this.b, this.c);
                } else {
                    subscriberArr2[i] = new d(subscriber, this.b, this.c);
                }
            }
            this.f13977a.subscribe(subscriberArr2);
        }
    }
}
