package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.fuseable.QueueSubscription;
import io.reactivex.rxjava3.internal.fuseable.SimpleQueue;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.QueueDrainHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowablePublishMulticast<T, R> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, R> {
    public final Function<? super Flowable<T>, ? extends Publisher<? extends R>> i;
    public final int j;
    public final boolean k;

    /* loaded from: classes12.dex */
    public static final class a<T> extends Flowable<T> implements FlowableSubscriber<T>, Disposable {
        public static final b[] t = new b[0];
        public static final b[] u = new b[0];
        public final int k;
        public final int l;
        public final boolean m;
        public volatile SimpleQueue<T> o;
        public int p;
        public volatile boolean q;
        public Throwable r;
        public int s;
        public final AtomicInteger i = new AtomicInteger();
        public final AtomicReference<Subscription> n = new AtomicReference<>();
        public final AtomicReference<b<T>[]> j = new AtomicReference<>(t);

        public a(int i, boolean z) {
            this.k = i;
            this.l = i - (i >> 2);
            this.m = z;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            SimpleQueue<T> simpleQueue;
            SubscriptionHelper.cancel(this.n);
            if (this.i.getAndIncrement() != 0 || (simpleQueue = this.o) == null) {
                return;
            }
            simpleQueue.clear();
        }

        public boolean e(b<T> bVar) {
            b<T>[] bVarArr;
            b<T>[] bVarArr2;
            do {
                bVarArr = this.j.get();
                if (bVarArr == u) {
                    return false;
                }
                int length = bVarArr.length;
                bVarArr2 = new b[length + 1];
                System.arraycopy(bVarArr, 0, bVarArr2, 0, length);
                bVarArr2[length] = bVar;
            } while (!this.j.compareAndSet(bVarArr, bVarArr2));
            return true;
        }

        public void f() {
            b<T>[] andSet;
            for (b<T> bVar : this.j.getAndSet(u)) {
                if (bVar.get() != Long.MIN_VALUE) {
                    bVar.downstream.onComplete();
                }
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:131:?, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x00e8, code lost:
            r8 = r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x00f8, code lost:
            if (r7 != 0) goto L111;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x00fe, code lost:
            if (isDisposed() == false) goto L89;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0100, code lost:
            r0.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x0103, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x0104, code lost:
            r5 = r24.q;
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x0106, code lost:
            if (r5 == false) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:86:0x010a, code lost:
            if (r24.m != false) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x010c, code lost:
            r6 = r24.r;
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x010e, code lost:
            if (r6 == null) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x0110, code lost:
            h(r6);
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x0113, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x0114, code lost:
            if (r5 == false) goto L111;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x011a, code lost:
            if (r0.isEmpty() == false) goto L111;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x011c, code lost:
            r0 = r24.r;
         */
        /* JADX WARN: Code restructure failed: missing block: B:95:0x011e, code lost:
            if (r0 == null) goto L106;
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x0120, code lost:
            h(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x0124, code lost:
            f();
         */
        /* JADX WARN: Code restructure failed: missing block: B:98:0x0127, code lost:
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void g() {
            /*
                Method dump skipped, instructions count: 326
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowablePublishMulticast.a.g():void");
        }

        public void h(Throwable th) {
            b<T>[] andSet;
            for (b<T> bVar : this.j.getAndSet(u)) {
                if (bVar.get() != Long.MIN_VALUE) {
                    bVar.downstream.onError(th);
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void i(b<T> bVar) {
            b<T>[] bVarArr;
            b[] bVarArr2;
            do {
                bVarArr = this.j.get();
                int length = bVarArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (bVarArr[i2] == bVar) {
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
                    bVarArr2 = t;
                } else {
                    b[] bVarArr3 = new b[length - 1];
                    System.arraycopy(bVarArr, 0, bVarArr3, 0, i);
                    System.arraycopy(bVarArr, i + 1, bVarArr3, i, (length - i) - 1);
                    bVarArr2 = bVarArr3;
                }
            } while (!this.j.compareAndSet(bVarArr, bVarArr2));
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.n.get() == SubscriptionHelper.CANCELLED;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.q) {
                return;
            }
            this.q = true;
            g();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.q) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.r = th;
            this.q = true;
            g();
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t2) {
            if (this.q) {
                return;
            }
            if (this.p == 0 && !this.o.offer(t2)) {
                this.n.get().cancel();
                onError(new MissingBackpressureException());
                return;
            }
            g();
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.n, subscription)) {
                if (subscription instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) subscription;
                    int requestFusion = queueSubscription.requestFusion(3);
                    if (requestFusion == 1) {
                        this.p = requestFusion;
                        this.o = queueSubscription;
                        this.q = true;
                        g();
                        return;
                    } else if (requestFusion == 2) {
                        this.p = requestFusion;
                        this.o = queueSubscription;
                        QueueDrainHelper.request(subscription, this.k);
                        return;
                    }
                }
                this.o = QueueDrainHelper.createQueue(this.k);
                QueueDrainHelper.request(subscription, this.k);
            }
        }

        @Override // io.reactivex.rxjava3.core.Flowable
        public void subscribeActual(Subscriber<? super T> subscriber) {
            b<T> bVar = new b<>(subscriber, this);
            subscriber.onSubscribe(bVar);
            if (e(bVar)) {
                if (bVar.isCancelled()) {
                    i(bVar);
                    return;
                } else {
                    g();
                    return;
                }
            }
            Throwable th = this.r;
            if (th != null) {
                subscriber.onError(th);
            } else {
                subscriber.onComplete();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 8664815189257569791L;
        public final Subscriber<? super T> downstream;
        public long emitted;
        public final a<T> parent;

        public b(Subscriber<? super T> subscriber, a<T> aVar) {
            this.downstream = subscriber;
            this.parent = aVar;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.i(this);
                this.parent.g();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.addCancel(this, j);
                this.parent.g();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<R> implements FlowableSubscriber<R>, Subscription {
        public final Subscriber<? super R> h;
        public final a<?> i;
        public Subscription j;

        public c(Subscriber<? super R> subscriber, a<?> aVar) {
            this.h = subscriber;
            this.i = aVar;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.j.cancel();
            this.i.dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.h.onComplete();
            this.i.dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.h.onError(th);
            this.i.dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(R r) {
            this.h.onNext(r);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.j, subscription)) {
                this.j = subscription;
                this.h.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.j.request(j);
        }
    }

    public FlowablePublishMulticast(Flowable<T> flowable, Function<? super Flowable<T>, ? extends Publisher<? extends R>> function, int i, boolean z) {
        super(flowable);
        this.i = function;
        this.j = i;
        this.k = z;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        a aVar = new a(this.j, this.k);
        try {
            Publisher<? extends R> apply = this.i.apply(aVar);
            Objects.requireNonNull(apply, "selector returned a null Publisher");
            apply.subscribe(new c(subscriber, aVar));
            this.source.subscribe((FlowableSubscriber) aVar);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            EmptySubscription.error(th, subscriber);
        }
    }
}
