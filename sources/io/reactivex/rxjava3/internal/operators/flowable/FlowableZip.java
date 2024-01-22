package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.fuseable.QueueSubscription;
import io.reactivex.rxjava3.internal.fuseable.SimpleQueue;
import io.reactivex.rxjava3.internal.queue.SpscArrayQueue;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableZip<T, R> extends Flowable<R> {
    public final Publisher<? extends T>[] i;
    public final Iterable<? extends Publisher<? extends T>> j;
    public final Function<? super Object[], ? extends R> k;
    public final int l;
    public final boolean m;

    /* loaded from: classes12.dex */
    public static final class a<T, R> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = -2434867452883857743L;
        public volatile boolean cancelled;
        public final Object[] current;
        public final boolean delayErrors;
        public final Subscriber<? super R> downstream;
        public final AtomicThrowable errors;
        public final AtomicLong requested;
        public final b<T, R>[] subscribers;
        public final Function<? super Object[], ? extends R> zipper;

        public a(Subscriber<? super R> subscriber, Function<? super Object[], ? extends R> function, int i, int i2, boolean z) {
            this.downstream = subscriber;
            this.zipper = function;
            this.delayErrors = z;
            b<T, R>[] bVarArr = new b[i];
            for (int i3 = 0; i3 < i; i3++) {
                bVarArr[i3] = new b<>(this, i2);
            }
            this.current = new Object[i];
            this.subscribers = bVarArr;
            this.requested = new AtomicLong();
            this.errors = new AtomicThrowable();
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            cancelAll();
        }

        public void cancelAll() {
            for (b<T, R> bVar : this.subscribers) {
                bVar.cancel();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:51:0x00b8, code lost:
            if (r14 != 0) goto L104;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00bc, code lost:
            if (r19.cancelled == false) goto L63;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00be, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00c1, code lost:
            if (r19.delayErrors != false) goto L70;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00c9, code lost:
            if (r19.errors.get() == null) goto L70;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x00cb, code lost:
            cancelAll();
            r19.errors.tryTerminateConsumer(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:60:0x00d3, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x00d4, code lost:
            r6 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00d6, code lost:
            if (r6 >= r4) goto L101;
         */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x00d8, code lost:
            r0 = r3[r6];
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00dc, code lost:
            if (r5[r6] != null) goto L100;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00de, code lost:
            r10 = r0.done;
            r0 = r0.queue;
         */
        /* JADX WARN: Code restructure failed: missing block: B:66:0x00e2, code lost:
            if (r0 == null) goto L76;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00e4, code lost:
            r0 = r0.poll();
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x00e9, code lost:
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x00ea, code lost:
            io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r0);
            r19.errors.tryAddThrowableOrReport(r0);
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x00f5, code lost:
            if (r19.delayErrors == false) goto L97;
         */
        /* JADX WARN: Code restructure failed: missing block: B:72:0x00f7, code lost:
            cancelAll();
            r19.errors.tryTerminateConsumer(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x00ff, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x0100, code lost:
            r0 = null;
            r10 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x0103, code lost:
            r0 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x0104, code lost:
            if (r0 != null) goto L89;
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0106, code lost:
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0108, code lost:
            r11 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x010a, code lost:
            if (r10 == false) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x010c, code lost:
            if (r11 == false) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x010e, code lost:
            cancelAll();
            r19.errors.tryTerminateConsumer(r2);
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x0116, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x0117, code lost:
            if (r11 != false) goto L88;
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x0119, code lost:
            r5[r6] = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x011b, code lost:
            r6 = r6 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0122, code lost:
            if (r12 == 0) goto L113;
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x0124, code lost:
            r0 = r3.length;
            r6 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:89:0x0127, code lost:
            if (r6 >= r0) goto L109;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x0129, code lost:
            r3[r6].request(r12);
            r6 = r6 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:92:0x0138, code lost:
            if (r8 == Long.MAX_VALUE) goto L113;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x013a, code lost:
            r19.requested.addAndGet(-r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0140, code lost:
            r7 = addAndGet(-r7);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void drain() {
            /*
                Method dump skipped, instructions count: 328
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableZip.a.drain():void");
        }

        public void error(b<T, R> bVar, Throwable th) {
            if (this.errors.tryAddThrowableOrReport(th)) {
                bVar.done = true;
                drain();
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.add(this.requested, j);
                drain();
            }
        }

        public void subscribe(Publisher<? extends T>[] publisherArr, int i) {
            b<T, R>[] bVarArr = this.subscribers;
            for (int i2 = 0; i2 < i && !this.cancelled; i2++) {
                if (!this.delayErrors && this.errors.get() != null) {
                    return;
                }
                publisherArr[i2].subscribe(bVarArr[i2]);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, R> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -4627193790118206028L;
        public volatile boolean done;
        public final int limit;
        public final a<T, R> parent;
        public final int prefetch;
        public long produced;
        public SimpleQueue<T> queue;
        public int sourceMode;

        public b(a<T, R> aVar, int i) {
            this.parent = aVar;
            this.prefetch = i;
            this.limit = i - (i >> 2);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            SubscriptionHelper.cancel(this);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.parent.error(this, th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            this.parent.drain();
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                if (subscription instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) subscription;
                    int requestFusion = queueSubscription.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        subscription.request(this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                subscription.request(this.prefetch);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (this.sourceMode != 1) {
                long j2 = this.produced + j;
                if (j2 >= this.limit) {
                    this.produced = 0L;
                    get().request(j2);
                    return;
                }
                this.produced = j2;
            }
        }
    }

    public FlowableZip(Publisher<? extends T>[] publisherArr, Iterable<? extends Publisher<? extends T>> iterable, Function<? super Object[], ? extends R> function, int i, boolean z) {
        this.i = publisherArr;
        this.j = iterable;
        this.k = function;
        this.l = i;
        this.m = z;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        int length;
        Publisher<? extends T>[] publisherArr = this.i;
        if (publisherArr == null) {
            publisherArr = new Publisher[8];
            length = 0;
            for (Publisher<? extends T> publisher : this.j) {
                if (length == publisherArr.length) {
                    Publisher<? extends T>[] publisherArr2 = new Publisher[(length >> 2) + length];
                    System.arraycopy(publisherArr, 0, publisherArr2, 0, length);
                    publisherArr = publisherArr2;
                }
                publisherArr[length] = publisher;
                length++;
            }
        } else {
            length = publisherArr.length;
        }
        int i = length;
        if (i == 0) {
            EmptySubscription.complete(subscriber);
            return;
        }
        a aVar = new a(subscriber, this.k, i, this.l, this.m);
        subscriber.onSubscribe(aVar);
        aVar.subscribe(publisherArr, i);
    }
}
