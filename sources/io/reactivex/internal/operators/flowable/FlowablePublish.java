package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowablePublish<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T>, FlowablePublishClassic<T> {
    public final Flowable<T> i;
    public final AtomicReference<c<T>> j;
    public final int k;
    public final Publisher<T> l;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Publisher<T> {
        public final AtomicReference<c<T>> h;
        public final int i;

        public a(AtomicReference<c<T>> atomicReference, int i) {
            this.h = atomicReference;
            this.i = i;
        }

        @Override // org.reactivestreams.Publisher
        public void subscribe(Subscriber<? super T> subscriber) {
            c<T> cVar;
            b<T> bVar = new b<>(subscriber);
            subscriber.onSubscribe(bVar);
            while (true) {
                cVar = this.h.get();
                if (cVar == null || cVar.isDisposed()) {
                    c<T> cVar2 = new c<>(this.h, this.i);
                    if (this.h.compareAndSet(cVar, cVar2)) {
                        cVar = cVar2;
                    } else {
                        continue;
                    }
                }
                if (cVar.add(bVar)) {
                    break;
                }
            }
            if (bVar.get() == Long.MIN_VALUE) {
                cVar.remove(bVar);
            } else {
                bVar.parent = cVar;
            }
            cVar.dispatch();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = -4453897557930727610L;
        public final Subscriber<? super T> child;
        public long emitted;
        public volatile c<T> parent;

        public b(Subscriber<? super T> subscriber) {
            this.child = subscriber;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            c<T> cVar;
            if (get() == Long.MIN_VALUE || getAndSet(Long.MIN_VALUE) == Long.MIN_VALUE || (cVar = this.parent) == null) {
                return;
            }
            cVar.remove(this);
            cVar.dispatch();
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.addCancel(this, j);
                c<T> cVar = this.parent;
                if (cVar != null) {
                    cVar.dispatch();
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        public static final b[] EMPTY = new b[0];
        public static final b[] TERMINATED = new b[0];
        private static final long serialVersionUID = -202316842419149694L;
        public final int bufferSize;
        public final AtomicReference<c<T>> current;
        public volatile SimpleQueue<T> queue;
        public int sourceMode;
        public volatile Object terminalEvent;
        public final AtomicReference<Subscription> upstream = new AtomicReference<>();
        public final AtomicReference<b<T>[]> subscribers = new AtomicReference<>(EMPTY);
        public final AtomicBoolean shouldConnect = new AtomicBoolean();

        public c(AtomicReference<c<T>> atomicReference, int i) {
            this.current = atomicReference;
            this.bufferSize = i;
        }

        public boolean add(b<T> bVar) {
            b<T>[] bVarArr;
            b<T>[] bVarArr2;
            do {
                bVarArr = this.subscribers.get();
                if (bVarArr == TERMINATED) {
                    return false;
                }
                int length = bVarArr.length;
                bVarArr2 = new b[length + 1];
                System.arraycopy(bVarArr, 0, bVarArr2, 0, length);
                bVarArr2[length] = bVar;
            } while (!this.subscribers.compareAndSet(bVarArr, bVarArr2));
            return true;
        }

        public boolean checkTerminated(Object obj, boolean z) {
            int i = 0;
            if (obj != null) {
                if (!NotificationLite.isComplete(obj)) {
                    Throwable error = NotificationLite.getError(obj);
                    this.current.compareAndSet(this, null);
                    b<T>[] andSet = this.subscribers.getAndSet(TERMINATED);
                    if (andSet.length != 0) {
                        int length = andSet.length;
                        while (i < length) {
                            andSet[i].child.onError(error);
                            i++;
                        }
                    } else {
                        RxJavaPlugins.onError(error);
                    }
                    return true;
                } else if (z) {
                    this.current.compareAndSet(this, null);
                    b<T>[] andSet2 = this.subscribers.getAndSet(TERMINATED);
                    int length2 = andSet2.length;
                    while (i < length2) {
                        andSet2[i].child.onComplete();
                        i++;
                    }
                    return true;
                }
            }
            return false;
        }

        /* JADX WARN: Code restructure failed: missing block: B:111:0x0014, code lost:
            continue;
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x0124, code lost:
            if (r11 == 0) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:75:0x0129, code lost:
            if (r25.sourceMode == 1) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x012b, code lost:
            r25.upstream.get().request(r11);
         */
        /* JADX WARN: Code restructure failed: missing block: B:77:0x0137, code lost:
            r4 = r0;
            r3 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x013b, code lost:
            if (r11 == 0) goto L84;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x013d, code lost:
            r3 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x0140, code lost:
            if (r25.sourceMode == 1) goto L72;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x0142, code lost:
            r25.upstream.get().request(r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:82:0x014e, code lost:
            r3 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:84:0x0153, code lost:
            if (r14 == 0) goto L83;
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x0155, code lost:
            if (r8 != false) goto L75;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void dispatch() {
            /*
                Method dump skipped, instructions count: 362
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublish.c.dispatch():void");
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            b<T>[] bVarArr = this.subscribers.get();
            b<T>[] bVarArr2 = TERMINATED;
            if (bVarArr == bVarArr2 || this.subscribers.getAndSet(bVarArr2) == bVarArr2) {
                return;
            }
            this.current.compareAndSet(this, null);
            SubscriptionHelper.cancel(this.upstream);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.complete();
                dispatch();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.error(th);
                dispatch();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.sourceMode == 0 && !this.queue.offer(t)) {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            } else {
                dispatch();
            }
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this.upstream, subscription)) {
                if (subscription instanceof QueueSubscription) {
                    QueueSubscription queueSubscription = (QueueSubscription) subscription;
                    int requestFusion = queueSubscription.requestFusion(7);
                    if (requestFusion == 1) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        this.terminalEvent = NotificationLite.complete();
                        dispatch();
                        return;
                    } else if (requestFusion == 2) {
                        this.sourceMode = requestFusion;
                        this.queue = queueSubscription;
                        subscription.request(this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                subscription.request(this.bufferSize);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void remove(b<T> bVar) {
            b<T>[] bVarArr;
            b[] bVarArr2;
            do {
                bVarArr = this.subscribers.get();
                int length = bVarArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (bVarArr[i2].equals(bVar)) {
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
                    bVarArr2 = EMPTY;
                } else {
                    b[] bVarArr3 = new b[length - 1];
                    System.arraycopy(bVarArr, 0, bVarArr3, 0, i);
                    System.arraycopy(bVarArr, i + 1, bVarArr3, i, (length - i) - 1);
                    bVarArr2 = bVarArr3;
                }
            } while (!this.subscribers.compareAndSet(bVarArr, bVarArr2));
        }
    }

    public FlowablePublish(Publisher<T> publisher, Flowable<T> flowable, AtomicReference<c<T>> atomicReference, int i) {
        this.l = publisher;
        this.i = flowable;
        this.j = atomicReference;
        this.k = i;
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> flowable, int i) {
        AtomicReference atomicReference = new AtomicReference();
        return RxJavaPlugins.onAssembly((ConnectableFlowable) new FlowablePublish(new a(atomicReference, i), flowable, atomicReference, i));
    }

    @Override // io.reactivex.flowables.ConnectableFlowable
    public void connect(Consumer<? super Disposable> consumer) {
        c<T> cVar;
        while (true) {
            cVar = this.j.get();
            if (cVar != null && !cVar.isDisposed()) {
                break;
            }
            c<T> cVar2 = new c<>(this.j, this.k);
            if (this.j.compareAndSet(cVar, cVar2)) {
                cVar = cVar2;
                break;
            }
        }
        boolean z = true;
        if (cVar.shouldConnect.get() || !cVar.shouldConnect.compareAndSet(false, true)) {
            z = false;
        }
        try {
            consumer.accept(cVar);
            if (z) {
                this.i.subscribe((FlowableSubscriber) cVar);
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    @Override // io.reactivex.internal.operators.flowable.FlowablePublishClassic
    public int publishBufferSize() {
        return this.k;
    }

    @Override // io.reactivex.internal.operators.flowable.FlowablePublishClassic
    public Publisher<T> publishSource() {
        return this.i;
    }

    @Override // io.reactivex.internal.fuseable.HasUpstreamPublisher
    public Publisher<T> source() {
        return this.i;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.l.subscribe(subscriber);
    }
}
