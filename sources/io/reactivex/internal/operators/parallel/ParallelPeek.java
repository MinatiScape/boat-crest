package io.reactivex.internal.operators.parallel;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.LongConsumer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class ParallelPeek<T> extends ParallelFlowable<T> {

    /* renamed from: a  reason: collision with root package name */
    public final ParallelFlowable<T> f13927a;
    public final Consumer<? super T> b;
    public final Consumer<? super T> c;
    public final Consumer<? super Throwable> d;
    public final Action e;
    public final Action f;
    public final Consumer<? super Subscription> g;
    public final LongConsumer h;
    public final Action i;

    /* loaded from: classes12.dex */
    public static final class a<T> implements FlowableSubscriber<T>, Subscription {
        public final Subscriber<? super T> h;
        public final ParallelPeek<T> i;
        public Subscription j;
        public boolean k;

        public a(Subscriber<? super T> subscriber, ParallelPeek<T> parallelPeek) {
            this.h = subscriber;
            this.i = parallelPeek;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            try {
                this.i.i.run();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
            this.j.cancel();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.k) {
                return;
            }
            this.k = true;
            try {
                this.i.e.run();
                this.h.onComplete();
                try {
                    this.i.f.run();
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    RxJavaPlugins.onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.h.onError(th2);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.k) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.k = true;
            try {
                this.i.d.accept(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                th = new CompositeException(th, th2);
            }
            this.h.onError(th);
            try {
                this.i.f.run();
            } catch (Throwable th3) {
                Exceptions.throwIfFatal(th3);
                RxJavaPlugins.onError(th3);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.k) {
                return;
            }
            try {
                this.i.b.accept(t);
                this.h.onNext(t);
                try {
                    this.i.c.accept(t);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    onError(th);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                onError(th2);
            }
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.j, subscription)) {
                this.j = subscription;
                try {
                    this.i.g.accept(subscription);
                    this.h.onSubscribe(this);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    subscription.cancel();
                    this.h.onSubscribe(EmptySubscription.INSTANCE);
                    onError(th);
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            try {
                this.i.h.accept(j);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaPlugins.onError(th);
            }
            this.j.request(j);
        }
    }

    public ParallelPeek(ParallelFlowable<T> parallelFlowable, Consumer<? super T> consumer, Consumer<? super T> consumer2, Consumer<? super Throwable> consumer3, Action action, Action action2, Consumer<? super Subscription> consumer4, LongConsumer longConsumer, Action action3) {
        this.f13927a = parallelFlowable;
        this.b = (Consumer) ObjectHelper.requireNonNull(consumer, "onNext is null");
        this.c = (Consumer) ObjectHelper.requireNonNull(consumer2, "onAfterNext is null");
        this.d = (Consumer) ObjectHelper.requireNonNull(consumer3, "onError is null");
        this.e = (Action) ObjectHelper.requireNonNull(action, "onComplete is null");
        this.f = (Action) ObjectHelper.requireNonNull(action2, "onAfterTerminated is null");
        this.g = (Consumer) ObjectHelper.requireNonNull(consumer4, "onSubscribe is null");
        this.h = (LongConsumer) ObjectHelper.requireNonNull(longConsumer, "onRequest is null");
        this.i = (Action) ObjectHelper.requireNonNull(action3, "onCancel is null");
    }

    @Override // io.reactivex.parallel.ParallelFlowable
    public int parallelism() {
        return this.f13927a.parallelism();
    }

    @Override // io.reactivex.parallel.ParallelFlowable
    public void subscribe(Subscriber<? super T>[] subscriberArr) {
        if (validate(subscriberArr)) {
            int length = subscriberArr.length;
            Subscriber<? super T>[] subscriberArr2 = new Subscriber[length];
            for (int i = 0; i < length; i++) {
                subscriberArr2[i] = new a(subscriberArr[i], this);
            }
            this.f13927a.subscribe(subscriberArr2);
        }
    }
}
