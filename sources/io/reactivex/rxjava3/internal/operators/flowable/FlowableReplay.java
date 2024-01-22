package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.rxjava3.internal.subscribers.SubscriberResourceWrapper;
import io.reactivex.rxjava3.internal.subscriptions.EmptySubscription;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableReplay<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T> {
    public static final Supplier m = new b();
    public final Flowable<T> i;
    public final AtomicReference<i<T>> j;
    public final Supplier<? extends f<T>> k;
    public final Publisher<T> l;

    /* loaded from: classes12.dex */
    public static abstract class a<T> extends AtomicReference<e> implements f<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        public final boolean eagerTruncate;
        public long index;
        public int size;
        public e tail;

        public a(boolean z) {
            this.eagerTruncate = z;
            e eVar = new e(null, 0L);
            this.tail = eVar;
            set(eVar);
        }

        public final void addLast(e eVar) {
            this.tail.set(eVar);
            this.tail = eVar;
            this.size++;
        }

        public final void collect(Collection<? super T> collection) {
            e head = getHead();
            while (true) {
                head = head.get();
                if (head == null) {
                    return;
                }
                Object leaveTransform = leaveTransform(head.value);
                if (NotificationLite.isComplete(leaveTransform) || NotificationLite.isError(leaveTransform)) {
                    return;
                }
                collection.add((Object) NotificationLite.getValue(leaveTransform));
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.f
        public final void complete() {
            Object enterTransform = enterTransform(NotificationLite.complete(), true);
            long j = this.index + 1;
            this.index = j;
            addLast(new e(enterTransform, j));
            truncateFinal();
        }

        public Object enterTransform(Object obj, boolean z) {
            return obj;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.f
        public final void error(Throwable th) {
            Object enterTransform = enterTransform(NotificationLite.error(th), true);
            long j = this.index + 1;
            this.index = j;
            addLast(new e(enterTransform, j));
            truncateFinal();
        }

        public e getHead() {
            return get();
        }

        public boolean hasCompleted() {
            Object obj = this.tail.value;
            return obj != null && NotificationLite.isComplete(leaveTransform(obj));
        }

        public boolean hasError() {
            Object obj = this.tail.value;
            return obj != null && NotificationLite.isError(leaveTransform(obj));
        }

        public Object leaveTransform(Object obj) {
            return obj;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.f
        public final void next(T t) {
            Object enterTransform = enterTransform(NotificationLite.next(t), false);
            long j = this.index + 1;
            this.index = j;
            addLast(new e(enterTransform, j));
            truncate();
        }

        public final void removeFirst() {
            e eVar = get().get();
            if (eVar != null) {
                this.size--;
                setFirst(eVar);
                return;
            }
            throw new IllegalStateException("Empty list!");
        }

        public final void removeSome(int i) {
            e eVar = get();
            while (i > 0) {
                eVar = eVar.get();
                i--;
                this.size--;
            }
            setFirst(eVar);
            e eVar2 = get();
            if (eVar2.get() == null) {
                this.tail = eVar2;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:40:0x0082, code lost:
            if (r10 != 0) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x0088, code lost:
            if (r14.isDisposed() == false) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x008a, code lost:
            r14.index = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x008c, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x008f, code lost:
            if (r8 == 0) goto L54;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x0091, code lost:
            r14.index = r5;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0093, code lost:
            if (r0 != false) goto L54;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0095, code lost:
            r14.produced(r8);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0098, code lost:
            monitor-enter(r14);
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x009b, code lost:
            if (r14.missed != false) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x009d, code lost:
            r14.emitting = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x009f, code lost:
            monitor-exit(r14);
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00a0, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00a1, code lost:
            r14.missed = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00a3, code lost:
            monitor-exit(r14);
         */
        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.f
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final void replay(io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.c<T> r14) {
            /*
                r13 = this;
                monitor-enter(r14)
                boolean r0 = r14.emitting     // Catch: java.lang.Throwable -> La9
                r1 = 1
                if (r0 == 0) goto La
                r14.missed = r1     // Catch: java.lang.Throwable -> La9
                monitor-exit(r14)     // Catch: java.lang.Throwable -> La9
                return
            La:
                r14.emitting = r1     // Catch: java.lang.Throwable -> La9
                monitor-exit(r14)     // Catch: java.lang.Throwable -> La9
            Ld:
                long r2 = r14.get()
                r4 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                r4 = 0
                if (r0 != 0) goto L1d
                r0 = r1
                goto L1e
            L1d:
                r0 = r4
            L1e:
                java.lang.Object r5 = r14.index()
                io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay$e r5 = (io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.e) r5
                r6 = 0
                if (r5 != 0) goto L35
                io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay$e r5 = r13.getHead()
                r14.index = r5
                java.util.concurrent.atomic.AtomicLong r8 = r14.totalRequested
                long r9 = r5.index
                io.reactivex.rxjava3.internal.util.BackpressureHelper.add(r8, r9)
            L35:
                r8 = r6
            L36:
                int r10 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
                r11 = 0
                if (r10 == 0) goto L82
                boolean r12 = r14.isDisposed()
                if (r12 == 0) goto L44
                r14.index = r11
                return
            L44:
                java.lang.Object r12 = r5.get()
                io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay$e r12 = (io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.e) r12
                if (r12 == 0) goto L82
                java.lang.Object r5 = r12.value
                java.lang.Object r5 = r13.leaveTransform(r5)
                org.reactivestreams.Subscriber<? super T> r10 = r14.child     // Catch: java.lang.Throwable -> L63
                boolean r10 = io.reactivex.rxjava3.internal.util.NotificationLite.accept(r5, r10)     // Catch: java.lang.Throwable -> L63
                if (r10 == 0) goto L5d
                r14.index = r11     // Catch: java.lang.Throwable -> L63
                return
            L5d:
                r10 = 1
                long r8 = r8 + r10
                long r2 = r2 - r10
                r5 = r12
                goto L36
            L63:
                r0 = move-exception
                io.reactivex.rxjava3.exceptions.Exceptions.throwIfFatal(r0)
                r14.index = r11
                r14.dispose()
                boolean r1 = io.reactivex.rxjava3.internal.util.NotificationLite.isError(r5)
                if (r1 != 0) goto L7e
                boolean r1 = io.reactivex.rxjava3.internal.util.NotificationLite.isComplete(r5)
                if (r1 != 0) goto L7e
                org.reactivestreams.Subscriber<? super T> r14 = r14.child
                r14.onError(r0)
                goto L81
            L7e:
                io.reactivex.rxjava3.plugins.RxJavaPlugins.onError(r0)
            L81:
                return
            L82:
                if (r10 != 0) goto L8d
                boolean r2 = r14.isDisposed()
                if (r2 == 0) goto L8d
                r14.index = r11
                return
            L8d:
                int r2 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
                if (r2 == 0) goto L98
                r14.index = r5
                if (r0 != 0) goto L98
                r14.produced(r8)
            L98:
                monitor-enter(r14)
                boolean r0 = r14.missed     // Catch: java.lang.Throwable -> La6
                if (r0 != 0) goto La1
                r14.emitting = r4     // Catch: java.lang.Throwable -> La6
                monitor-exit(r14)     // Catch: java.lang.Throwable -> La6
                return
            La1:
                r14.missed = r4     // Catch: java.lang.Throwable -> La6
                monitor-exit(r14)     // Catch: java.lang.Throwable -> La6
                goto Ld
            La6:
                r0 = move-exception
                monitor-exit(r14)     // Catch: java.lang.Throwable -> La6
                throw r0
            La9:
                r0 = move-exception
                monitor-exit(r14)     // Catch: java.lang.Throwable -> La9
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.a.replay(io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay$c):void");
        }

        public final void setFirst(e eVar) {
            if (this.eagerTruncate) {
                e eVar2 = new e(null, eVar.index);
                eVar2.lazySet(eVar.get());
                eVar = eVar2;
            }
            set(eVar);
        }

        public final void trimHead() {
            e eVar = get();
            if (eVar.value != null) {
                e eVar2 = new e(null, 0L);
                eVar2.lazySet(eVar.get());
                set(eVar2);
            }
        }

        public abstract void truncate();

        public void truncateFinal() {
            trimHead();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b implements Supplier<Object> {
        @Override // io.reactivex.rxjava3.functions.Supplier
        public Object get() {
            return new m(16);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T> extends AtomicLong implements Subscription, Disposable {
        public static final long CANCELLED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        public final Subscriber<? super T> child;
        public boolean emitting;
        public Object index;
        public boolean missed;
        public final i<T> parent;
        public final AtomicLong totalRequested = new AtomicLong();

        public c(i<T> iVar, Subscriber<? super T> subscriber) {
            this.parent = iVar;
            this.child = subscriber;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            dispose();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.manageRequests();
                this.index = null;
            }
        }

        public <U> U index() {
            return (U) this.index;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return get() == Long.MIN_VALUE;
        }

        public long produced(long j) {
            return BackpressureHelper.producedCancel(this, j);
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (!SubscriptionHelper.validate(j) || BackpressureHelper.addCancel(this, j) == Long.MIN_VALUE) {
                return;
            }
            BackpressureHelper.add(this.totalRequested, j);
            this.parent.manageRequests();
            this.parent.buffer.replay(this);
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<R, U> extends Flowable<R> {
        public final Supplier<? extends ConnectableFlowable<U>> i;
        public final Function<? super Flowable<U>, ? extends Publisher<R>> j;

        /* loaded from: classes12.dex */
        public final class a implements Consumer<Disposable> {
            public final SubscriberResourceWrapper<R> h;

            public a(d dVar, SubscriberResourceWrapper<R> subscriberResourceWrapper) {
                this.h = subscriberResourceWrapper;
            }

            @Override // io.reactivex.rxjava3.functions.Consumer
            /* renamed from: a */
            public void accept(Disposable disposable) {
                this.h.setResource(disposable);
            }
        }

        public d(Supplier<? extends ConnectableFlowable<U>> supplier, Function<? super Flowable<U>, ? extends Publisher<R>> function) {
            this.i = supplier;
            this.j = function;
        }

        @Override // io.reactivex.rxjava3.core.Flowable
        public void subscribeActual(Subscriber<? super R> subscriber) {
            try {
                ConnectableFlowable connectableFlowable = (ConnectableFlowable) ExceptionHelper.nullCheck(this.i.get(), "The connectableFactory returned a null ConnectableFlowable.");
                try {
                    Publisher publisher = (Publisher) ExceptionHelper.nullCheck(this.j.apply(connectableFlowable), "The selector returned a null Publisher.");
                    SubscriberResourceWrapper subscriberResourceWrapper = new SubscriberResourceWrapper(subscriber);
                    publisher.subscribe(subscriberResourceWrapper);
                    connectableFlowable.connect(new a(this, subscriberResourceWrapper));
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    EmptySubscription.error(th, subscriber);
                }
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                EmptySubscription.error(th2, subscriber);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class e extends AtomicReference<e> {
        private static final long serialVersionUID = 245354315435971818L;
        public final long index;
        public final Object value;

        public e(Object obj, long j) {
            this.value = obj;
            this.index = j;
        }
    }

    /* loaded from: classes12.dex */
    public interface f<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(c<T> cVar);
    }

    /* loaded from: classes12.dex */
    public static final class g<T> implements Supplier<f<T>> {
        public final int h;
        public final boolean i;

        public g(int i, boolean z) {
            this.h = i;
            this.i = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public f<T> get() {
            return new l(this.h, this.i);
        }
    }

    /* loaded from: classes12.dex */
    public static final class h<T> implements Publisher<T> {
        public final AtomicReference<i<T>> h;
        public final Supplier<? extends f<T>> i;

        public h(AtomicReference<i<T>> atomicReference, Supplier<? extends f<T>> supplier) {
            this.h = atomicReference;
            this.i = supplier;
        }

        @Override // org.reactivestreams.Publisher
        public void subscribe(Subscriber<? super T> subscriber) {
            i<T> iVar;
            while (true) {
                iVar = this.h.get();
                if (iVar != null) {
                    break;
                }
                try {
                    i<T> iVar2 = new i<>(this.i.get());
                    if (this.h.compareAndSet(null, iVar2)) {
                        iVar = iVar2;
                        break;
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    EmptySubscription.error(th, subscriber);
                    return;
                }
            }
            c<T> cVar = new c<>(iVar, subscriber);
            subscriber.onSubscribe(cVar);
            iVar.add(cVar);
            if (cVar.isDisposed()) {
                iVar.remove(cVar);
                return;
            }
            iVar.manageRequests();
            iVar.buffer.replay(cVar);
        }
    }

    /* loaded from: classes12.dex */
    public static final class i<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Disposable {
        public static final c[] EMPTY = new c[0];
        public static final c[] TERMINATED = new c[0];
        private static final long serialVersionUID = 7224554242710036740L;
        public final f<T> buffer;
        public boolean done;
        public long requestedFromUpstream;
        public final AtomicInteger management = new AtomicInteger();
        public final AtomicReference<c<T>[]> subscribers = new AtomicReference<>(EMPTY);
        public final AtomicBoolean shouldConnect = new AtomicBoolean();

        public i(f<T> fVar) {
            this.buffer = fVar;
        }

        public boolean add(c<T> cVar) {
            c<T>[] cVarArr;
            c<T>[] cVarArr2;
            do {
                cVarArr = this.subscribers.get();
                if (cVarArr == TERMINATED) {
                    return false;
                }
                int length = cVarArr.length;
                cVarArr2 = new c[length + 1];
                System.arraycopy(cVarArr, 0, cVarArr2, 0, length);
                cVarArr2[length] = cVar;
            } while (!this.subscribers.compareAndSet(cVarArr, cVarArr2));
            return true;
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.subscribers.set(TERMINATED);
            SubscriptionHelper.cancel(this);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        public void manageRequests() {
            AtomicInteger atomicInteger = this.management;
            if (atomicInteger.getAndIncrement() != 0) {
                return;
            }
            int i = 1;
            while (!isDisposed()) {
                Subscription subscription = get();
                if (subscription != null) {
                    long j = this.requestedFromUpstream;
                    long j2 = j;
                    for (c<T> cVar : this.subscribers.get()) {
                        j2 = Math.max(j2, cVar.totalRequested.get());
                    }
                    long j3 = j2 - j;
                    if (j3 != 0) {
                        this.requestedFromUpstream = j2;
                        subscription.request(j3);
                    }
                }
                i = atomicInteger.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            this.buffer.complete();
            for (c<T> cVar : this.subscribers.getAndSet(TERMINATED)) {
                this.buffer.replay(cVar);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(th);
                for (c<T> cVar : this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(cVar);
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            this.buffer.next(t);
            for (c<T> cVar : this.subscribers.get()) {
                this.buffer.replay(cVar);
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                manageRequests();
                for (c<T> cVar : this.subscribers.get()) {
                    this.buffer.replay(cVar);
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void remove(c<T> cVar) {
            c<T>[] cVarArr;
            c[] cVarArr2;
            do {
                cVarArr = this.subscribers.get();
                int length = cVarArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (cVarArr[i2].equals(cVar)) {
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
                    cVarArr2 = EMPTY;
                } else {
                    c[] cVarArr3 = new c[length - 1];
                    System.arraycopy(cVarArr, 0, cVarArr3, 0, i);
                    System.arraycopy(cVarArr, i + 1, cVarArr3, i, (length - i) - 1);
                    cVarArr2 = cVarArr3;
                }
            } while (!this.subscribers.compareAndSet(cVarArr, cVarArr2));
        }
    }

    /* loaded from: classes12.dex */
    public static final class j<T> implements Supplier<f<T>> {
        public final int h;
        public final long i;
        public final TimeUnit j;
        public final Scheduler k;
        public final boolean l;

        public j(int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            this.h = i;
            this.i = j;
            this.j = timeUnit;
            this.k = scheduler;
            this.l = z;
        }

        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public f<T> get() {
            return new k(this.h, this.i, this.j, this.k, this.l);
        }
    }

    /* loaded from: classes12.dex */
    public static final class k<T> extends a<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        public final int limit;
        public final long maxAge;
        public final Scheduler scheduler;
        public final TimeUnit unit;

        public k(int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
            super(z);
            this.scheduler = scheduler;
            this.limit = i;
            this.maxAge = j;
            this.unit = timeUnit;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.a
        public Object enterTransform(Object obj, boolean z) {
            return new Timed(obj, z ? Long.MAX_VALUE : this.scheduler.now(this.unit), this.unit);
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.a
        public e getHead() {
            e eVar;
            long now = this.scheduler.now(this.unit) - this.maxAge;
            e eVar2 = get();
            e eVar3 = eVar2.get();
            while (true) {
                e eVar4 = eVar3;
                eVar = eVar2;
                eVar2 = eVar4;
                if (eVar2 != null) {
                    Timed timed = (Timed) eVar2.value;
                    if (NotificationLite.isComplete(timed.value()) || NotificationLite.isError(timed.value()) || timed.time() > now) {
                        break;
                    }
                    eVar3 = eVar2.get();
                } else {
                    break;
                }
            }
            return eVar;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.a
        public Object leaveTransform(Object obj) {
            return ((Timed) obj).value();
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.a
        public void truncate() {
            e eVar;
            long now = this.scheduler.now(this.unit) - this.maxAge;
            e eVar2 = get();
            e eVar3 = eVar2.get();
            int i = 0;
            while (true) {
                e eVar4 = eVar3;
                eVar = eVar2;
                eVar2 = eVar4;
                int i2 = this.size;
                if (i2 > 1) {
                    if (i2 > this.limit) {
                        i++;
                        this.size = i2 - 1;
                        eVar3 = eVar2.get();
                    } else if (((Timed) eVar2.value).time() > now) {
                        break;
                    } else {
                        i++;
                        this.size--;
                        eVar3 = eVar2.get();
                    }
                } else {
                    break;
                }
            }
            if (i != 0) {
                setFirst(eVar);
            }
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.a
        public void truncateFinal() {
            e eVar;
            long now = this.scheduler.now(this.unit) - this.maxAge;
            e eVar2 = get();
            e eVar3 = eVar2.get();
            int i = 0;
            while (true) {
                e eVar4 = eVar3;
                eVar = eVar2;
                eVar2 = eVar4;
                if (this.size <= 1 || ((Timed) eVar2.value).time() > now) {
                    break;
                }
                i++;
                this.size--;
                eVar3 = eVar2.get();
            }
            if (i != 0) {
                setFirst(eVar);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class l<T> extends a<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        public final int limit;

        public l(int i, boolean z) {
            super(z);
            this.limit = i;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.a
        public void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class m<T> extends ArrayList<Object> implements f<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        public volatile int size;

        public m(int i) {
            super(i);
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.f
        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.f
        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.f
        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        @Override // io.reactivex.rxjava3.internal.operators.flowable.FlowableReplay.f
        public void replay(c<T> cVar) {
            synchronized (cVar) {
                if (cVar.emitting) {
                    cVar.missed = true;
                    return;
                }
                cVar.emitting = true;
                Subscriber<? super T> subscriber = cVar.child;
                while (!cVar.isDisposed()) {
                    int i = this.size;
                    Integer num = (Integer) cVar.index();
                    int intValue = num != null ? num.intValue() : 0;
                    long j = cVar.get();
                    long j2 = j;
                    long j3 = 0;
                    while (j2 != 0 && intValue < i) {
                        Object obj = get(intValue);
                        try {
                            if (NotificationLite.accept(obj, subscriber) || cVar.isDisposed()) {
                                return;
                            }
                            intValue++;
                            j2--;
                            j3++;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            cVar.dispose();
                            if (!NotificationLite.isError(obj) && !NotificationLite.isComplete(obj)) {
                                subscriber.onError(th);
                                return;
                            } else {
                                RxJavaPlugins.onError(th);
                                return;
                            }
                        }
                    }
                    if (j3 != 0) {
                        cVar.index = Integer.valueOf(intValue);
                        if (j != Long.MAX_VALUE) {
                            cVar.produced(j3);
                        }
                    }
                    synchronized (cVar) {
                        if (!cVar.missed) {
                            cVar.emitting = false;
                            return;
                        }
                        cVar.missed = false;
                    }
                }
            }
        }
    }

    public FlowableReplay(Publisher<T> publisher, Flowable<T> flowable, AtomicReference<i<T>> atomicReference, Supplier<? extends f<T>> supplier) {
        this.l = publisher;
        this.i = flowable;
        this.j = atomicReference;
        this.k = supplier;
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> flowable, int i2, boolean z) {
        if (i2 == Integer.MAX_VALUE) {
            return createFrom(flowable);
        }
        return e(flowable, new g(i2, z));
    }

    public static <T> ConnectableFlowable<T> createFrom(Flowable<? extends T> flowable) {
        return e(flowable, m);
    }

    public static <T> ConnectableFlowable<T> e(Flowable<T> flowable, Supplier<? extends f<T>> supplier) {
        AtomicReference atomicReference = new AtomicReference();
        return RxJavaPlugins.onAssembly((ConnectableFlowable) new FlowableReplay(new h(atomicReference, supplier), flowable, atomicReference, supplier));
    }

    public static <U, R> Flowable<R> multicastSelector(Supplier<? extends ConnectableFlowable<U>> supplier, Function<? super Flowable<U>, ? extends Publisher<R>> function) {
        return new d(supplier, function);
    }

    @Override // io.reactivex.rxjava3.flowables.ConnectableFlowable
    public void connect(Consumer<? super Disposable> consumer) {
        i<T> iVar;
        while (true) {
            iVar = this.j.get();
            if (iVar != null && !iVar.isDisposed()) {
                break;
            }
            try {
                i<T> iVar2 = new i<>(this.k.get());
                if (this.j.compareAndSet(iVar, iVar2)) {
                    iVar = iVar2;
                    break;
                }
            } finally {
                Exceptions.throwIfFatal(th);
                RuntimeException wrapOrThrow = ExceptionHelper.wrapOrThrow(th);
            }
        }
        boolean z = !iVar.shouldConnect.get() && iVar.shouldConnect.compareAndSet(false, true);
        try {
            consumer.accept(iVar);
            if (z) {
                this.i.subscribe((FlowableSubscriber) iVar);
            }
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (z) {
                iVar.shouldConnect.compareAndSet(true, false);
            }
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    @Override // io.reactivex.rxjava3.flowables.ConnectableFlowable
    public void reset() {
        i<T> iVar = this.j.get();
        if (iVar == null || !iVar.isDisposed()) {
            return;
        }
        this.j.compareAndSet(iVar, null);
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.HasUpstreamPublisher
    public Publisher<T> source() {
        return this.i;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.l.subscribe(subscriber);
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> flowable, long j2, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return create(flowable, j2, timeUnit, scheduler, Integer.MAX_VALUE, z);
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> flowable, long j2, TimeUnit timeUnit, Scheduler scheduler, int i2, boolean z) {
        return e(flowable, new j(i2, j2, timeUnit, scheduler, z));
    }
}
