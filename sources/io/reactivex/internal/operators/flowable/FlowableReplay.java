package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.subscribers.SubscriberResourceWrapper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableReplay<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T>, ResettableConnectable {
    public static final Callable m = new c();
    public final Flowable<T> i;
    public final AtomicReference<j<T>> j;
    public final Callable<? extends g<T>> k;
    public final Publisher<T> l;

    /* loaded from: classes12.dex */
    public static class a<T> extends AtomicReference<f> implements g<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        public long index;
        public int size;
        public f tail;

        public a() {
            f fVar = new f(null, 0L);
            this.tail = fVar;
            set(fVar);
        }

        public final void addLast(f fVar) {
            this.tail.set(fVar);
            this.tail = fVar;
            this.size++;
        }

        public final void collect(Collection<? super T> collection) {
            f head = getHead();
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

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.g
        public final void complete() {
            Object enterTransform = enterTransform(NotificationLite.complete());
            long j = this.index + 1;
            this.index = j;
            addLast(new f(enterTransform, j));
            truncateFinal();
        }

        public Object enterTransform(Object obj) {
            return obj;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.g
        public final void error(Throwable th) {
            Object enterTransform = enterTransform(NotificationLite.error(th));
            long j = this.index + 1;
            this.index = j;
            addLast(new f(enterTransform, j));
            truncateFinal();
        }

        public f getHead() {
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

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.g
        public final void next(T t) {
            Object enterTransform = enterTransform(NotificationLite.next(t));
            long j = this.index + 1;
            this.index = j;
            addLast(new f(enterTransform, j));
            truncate();
        }

        public final void removeFirst() {
            f fVar = get().get();
            if (fVar != null) {
                this.size--;
                setFirst(fVar);
                return;
            }
            throw new IllegalStateException("Empty list!");
        }

        public final void removeSome(int i) {
            f fVar = get();
            while (i > 0) {
                fVar = fVar.get();
                i--;
                this.size--;
            }
            setFirst(fVar);
            f fVar2 = get();
            if (fVar2.get() == null) {
                this.tail = fVar2;
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.g
        public final void replay(d<T> dVar) {
            f fVar;
            synchronized (dVar) {
                if (dVar.emitting) {
                    dVar.missed = true;
                    return;
                }
                dVar.emitting = true;
                while (!dVar.isDisposed()) {
                    long j = dVar.get();
                    boolean z = j == Long.MAX_VALUE;
                    f fVar2 = (f) dVar.index();
                    if (fVar2 == null) {
                        fVar2 = getHead();
                        dVar.index = fVar2;
                        BackpressureHelper.add(dVar.totalRequested, fVar2.index);
                    }
                    long j2 = 0;
                    while (j != 0 && (fVar = fVar2.get()) != null) {
                        Object leaveTransform = leaveTransform(fVar.value);
                        try {
                            if (NotificationLite.accept(leaveTransform, dVar.child)) {
                                dVar.index = null;
                                return;
                            }
                            j2++;
                            j--;
                            if (dVar.isDisposed()) {
                                dVar.index = null;
                                return;
                            }
                            fVar2 = fVar;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            dVar.index = null;
                            dVar.dispose();
                            if (NotificationLite.isError(leaveTransform) || NotificationLite.isComplete(leaveTransform)) {
                                return;
                            }
                            dVar.child.onError(th);
                            return;
                        }
                    }
                    if (j2 != 0) {
                        dVar.index = fVar2;
                        if (!z) {
                            dVar.produced(j2);
                        }
                    }
                    synchronized (dVar) {
                        if (!dVar.missed) {
                            dVar.emitting = false;
                            return;
                        }
                        dVar.missed = false;
                    }
                }
                dVar.index = null;
            }
        }

        public final void setFirst(f fVar) {
            set(fVar);
        }

        public final void trimHead() {
            f fVar = get();
            if (fVar.value != null) {
                f fVar2 = new f(null, 0L);
                fVar2.lazySet(fVar.get());
                set(fVar2);
            }
        }

        public void truncate() {
        }

        public void truncateFinal() {
            trimHead();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends ConnectableFlowable<T> {
        public final ConnectableFlowable<T> i;
        public final Flowable<T> j;

        public b(ConnectableFlowable<T> connectableFlowable, Flowable<T> flowable) {
            this.i = connectableFlowable;
            this.j = flowable;
        }

        @Override // io.reactivex.flowables.ConnectableFlowable
        public void connect(Consumer<? super Disposable> consumer) {
            this.i.connect(consumer);
        }

        @Override // io.reactivex.Flowable
        public void subscribeActual(Subscriber<? super T> subscriber) {
            this.j.subscribe(subscriber);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c implements Callable<Object> {
        @Override // java.util.concurrent.Callable
        public Object call() {
            return new n(16);
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<T> extends AtomicLong implements Subscription, Disposable {
        public static final long CANCELLED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        public final Subscriber<? super T> child;
        public boolean emitting;
        public Object index;
        public boolean missed;
        public final j<T> parent;
        public final AtomicLong totalRequested = new AtomicLong();

        public d(j<T> jVar, Subscriber<? super T> subscriber) {
            this.parent = jVar;
            this.child = subscriber;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            dispose();
        }

        @Override // io.reactivex.disposables.Disposable
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

        @Override // io.reactivex.disposables.Disposable
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
    public static final class e<R, U> extends Flowable<R> {
        public final Callable<? extends ConnectableFlowable<U>> i;
        public final Function<? super Flowable<U>, ? extends Publisher<R>> j;

        /* loaded from: classes12.dex */
        public final class a implements Consumer<Disposable> {
            public final SubscriberResourceWrapper<R> h;

            public a(e eVar, SubscriberResourceWrapper<R> subscriberResourceWrapper) {
                this.h = subscriberResourceWrapper;
            }

            @Override // io.reactivex.functions.Consumer
            /* renamed from: a */
            public void accept(Disposable disposable) {
                this.h.setResource(disposable);
            }
        }

        public e(Callable<? extends ConnectableFlowable<U>> callable, Function<? super Flowable<U>, ? extends Publisher<R>> function) {
            this.i = callable;
            this.j = function;
        }

        @Override // io.reactivex.Flowable
        public void subscribeActual(Subscriber<? super R> subscriber) {
            try {
                ConnectableFlowable connectableFlowable = (ConnectableFlowable) ObjectHelper.requireNonNull(this.i.call(), "The connectableFactory returned null");
                try {
                    Publisher publisher = (Publisher) ObjectHelper.requireNonNull(this.j.apply(connectableFlowable), "The selector returned a null Publisher");
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
    public static final class f extends AtomicReference<f> {
        private static final long serialVersionUID = 245354315435971818L;
        public final long index;
        public final Object value;

        public f(Object obj, long j) {
            this.value = obj;
            this.index = j;
        }
    }

    /* loaded from: classes12.dex */
    public interface g<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(d<T> dVar);
    }

    /* loaded from: classes12.dex */
    public static final class h<T> implements Callable<g<T>> {
        public final int h;

        public h(int i) {
            this.h = i;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public g<T> call() {
            return new m(this.h);
        }
    }

    /* loaded from: classes12.dex */
    public static final class i<T> implements Publisher<T> {
        public final AtomicReference<j<T>> h;
        public final Callable<? extends g<T>> i;

        public i(AtomicReference<j<T>> atomicReference, Callable<? extends g<T>> callable) {
            this.h = atomicReference;
            this.i = callable;
        }

        @Override // org.reactivestreams.Publisher
        public void subscribe(Subscriber<? super T> subscriber) {
            j<T> jVar;
            while (true) {
                jVar = this.h.get();
                if (jVar != null) {
                    break;
                }
                try {
                    j<T> jVar2 = new j<>(this.i.call());
                    if (this.h.compareAndSet(null, jVar2)) {
                        jVar = jVar2;
                        break;
                    }
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    EmptySubscription.error(th, subscriber);
                    return;
                }
            }
            d<T> dVar = new d<>(jVar, subscriber);
            subscriber.onSubscribe(dVar);
            jVar.add(dVar);
            if (dVar.isDisposed()) {
                jVar.remove(dVar);
                return;
            }
            jVar.manageRequests();
            jVar.buffer.replay(dVar);
        }
    }

    /* loaded from: classes12.dex */
    public static final class j<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Disposable {
        public static final d[] EMPTY = new d[0];
        public static final d[] TERMINATED = new d[0];
        private static final long serialVersionUID = 7224554242710036740L;
        public final g<T> buffer;
        public boolean done;
        public long maxChildRequested;
        public long maxUpstreamRequested;
        public final AtomicInteger management = new AtomicInteger();
        public final AtomicReference<d<T>[]> subscribers = new AtomicReference<>(EMPTY);
        public final AtomicBoolean shouldConnect = new AtomicBoolean();

        public j(g<T> gVar) {
            this.buffer = gVar;
        }

        public boolean add(d<T> dVar) {
            d<T>[] dVarArr;
            d<T>[] dVarArr2;
            Objects.requireNonNull(dVar);
            do {
                dVarArr = this.subscribers.get();
                if (dVarArr == TERMINATED) {
                    return false;
                }
                int length = dVarArr.length;
                dVarArr2 = new d[length + 1];
                System.arraycopy(dVarArr, 0, dVarArr2, 0, length);
                dVarArr2[length] = dVar;
            } while (!this.subscribers.compareAndSet(dVarArr, dVarArr2));
            return true;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.subscribers.set(TERMINATED);
            SubscriptionHelper.cancel(this);
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        public void manageRequests() {
            if (this.management.getAndIncrement() != 0) {
                return;
            }
            int i = 1;
            while (!isDisposed()) {
                d<T>[] dVarArr = this.subscribers.get();
                long j = this.maxChildRequested;
                long j2 = j;
                for (d<T> dVar : dVarArr) {
                    j2 = Math.max(j2, dVar.totalRequested.get());
                }
                long j3 = this.maxUpstreamRequested;
                Subscription subscription = get();
                long j4 = j2 - j;
                if (j4 != 0) {
                    this.maxChildRequested = j2;
                    if (subscription == null) {
                        long j5 = j3 + j4;
                        if (j5 < 0) {
                            j5 = Long.MAX_VALUE;
                        }
                        this.maxUpstreamRequested = j5;
                    } else if (j3 != 0) {
                        this.maxUpstreamRequested = 0L;
                        subscription.request(j3 + j4);
                    } else {
                        subscription.request(j4);
                    }
                } else if (j3 != 0 && subscription != null) {
                    this.maxUpstreamRequested = 0L;
                    subscription.request(j3);
                }
                i = this.management.addAndGet(-i);
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
            for (d<T> dVar : this.subscribers.getAndSet(TERMINATED)) {
                this.buffer.replay(dVar);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (!this.done) {
                this.done = true;
                this.buffer.error(th);
                for (d<T> dVar : this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(dVar);
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
            for (d<T> dVar : this.subscribers.get()) {
                this.buffer.replay(dVar);
            }
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.setOnce(this, subscription)) {
                manageRequests();
                for (d<T> dVar : this.subscribers.get()) {
                    this.buffer.replay(dVar);
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void remove(d<T> dVar) {
            d<T>[] dVarArr;
            d[] dVarArr2;
            do {
                dVarArr = this.subscribers.get();
                int length = dVarArr.length;
                if (length == 0) {
                    return;
                }
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (dVarArr[i2].equals(dVar)) {
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
                    dVarArr2 = EMPTY;
                } else {
                    d[] dVarArr3 = new d[length - 1];
                    System.arraycopy(dVarArr, 0, dVarArr3, 0, i);
                    System.arraycopy(dVarArr, i + 1, dVarArr3, i, (length - i) - 1);
                    dVarArr2 = dVarArr3;
                }
            } while (!this.subscribers.compareAndSet(dVarArr, dVarArr2));
        }
    }

    /* loaded from: classes12.dex */
    public static final class k<T> implements Callable<g<T>> {
        public final int h;
        public final long i;
        public final TimeUnit j;
        public final Scheduler k;

        public k(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.h = i;
            this.i = j;
            this.j = timeUnit;
            this.k = scheduler;
        }

        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public g<T> call() {
            return new l(this.h, this.i, this.j, this.k);
        }
    }

    /* loaded from: classes12.dex */
    public static final class l<T> extends a<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        public final int limit;
        public final long maxAge;
        public final Scheduler scheduler;
        public final TimeUnit unit;

        public l(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.scheduler = scheduler;
            this.limit = i;
            this.maxAge = j;
            this.unit = timeUnit;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.a
        public Object enterTransform(Object obj) {
            return new Timed(obj, this.scheduler.now(this.unit), this.unit);
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.a
        public f getHead() {
            f fVar;
            long now = this.scheduler.now(this.unit) - this.maxAge;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 != null) {
                    Timed timed = (Timed) fVar2.value;
                    if (NotificationLite.isComplete(timed.value()) || NotificationLite.isError(timed.value()) || timed.time() > now) {
                        break;
                    }
                    fVar3 = fVar2.get();
                } else {
                    break;
                }
            }
            return fVar;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.a
        public Object leaveTransform(Object obj) {
            return ((Timed) obj).value();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.a
        public void truncate() {
            f fVar;
            long now = this.scheduler.now(this.unit) - this.maxAge;
            f fVar2 = get();
            f fVar3 = fVar2.get();
            int i = 0;
            while (true) {
                f fVar4 = fVar3;
                fVar = fVar2;
                fVar2 = fVar4;
                if (fVar2 == null) {
                    break;
                }
                int i2 = this.size;
                if (i2 > this.limit && i2 > 1) {
                    i++;
                    this.size = i2 - 1;
                    fVar3 = fVar2.get();
                } else if (((Timed) fVar2.value).time() > now) {
                    break;
                } else {
                    i++;
                    this.size--;
                    fVar3 = fVar2.get();
                }
            }
            if (i != 0) {
                setFirst(fVar);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x003e, code lost:
            setFirst(r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0041, code lost:
            return;
         */
        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void truncateFinal() {
            /*
                r10 = this;
                io.reactivex.Scheduler r0 = r10.scheduler
                java.util.concurrent.TimeUnit r1 = r10.unit
                long r0 = r0.now(r1)
                long r2 = r10.maxAge
                long r0 = r0 - r2
                java.lang.Object r2 = r10.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$f r2 = (io.reactivex.internal.operators.flowable.FlowableReplay.f) r2
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$f r3 = (io.reactivex.internal.operators.flowable.FlowableReplay.f) r3
                r4 = 0
            L18:
                r9 = r3
                r3 = r2
                r2 = r9
                if (r2 == 0) goto L3c
                int r5 = r10.size
                r6 = 1
                if (r5 <= r6) goto L3c
                java.lang.Object r5 = r2.value
                io.reactivex.schedulers.Timed r5 = (io.reactivex.schedulers.Timed) r5
                long r7 = r5.time()
                int r5 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
                if (r5 > 0) goto L3c
                int r4 = r4 + 1
                int r3 = r10.size
                int r3 = r3 - r6
                r10.size = r3
                java.lang.Object r3 = r2.get()
                io.reactivex.internal.operators.flowable.FlowableReplay$f r3 = (io.reactivex.internal.operators.flowable.FlowableReplay.f) r3
                goto L18
            L3c:
                if (r4 == 0) goto L41
                r10.setFirst(r3)
            L41:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.l.truncateFinal():void");
        }
    }

    /* loaded from: classes12.dex */
    public static final class m<T> extends a<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        public final int limit;

        public m(int i) {
            this.limit = i;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.a
        public void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class n<T> extends ArrayList<Object> implements g<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        public volatile int size;

        public n(int i) {
            super(i);
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.g
        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.g
        public void error(Throwable th) {
            add(NotificationLite.error(th));
            this.size++;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.g
        public void next(T t) {
            add(NotificationLite.next(t));
            this.size++;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableReplay.g
        public void replay(d<T> dVar) {
            synchronized (dVar) {
                if (dVar.emitting) {
                    dVar.missed = true;
                    return;
                }
                dVar.emitting = true;
                Subscriber<? super T> subscriber = dVar.child;
                while (!dVar.isDisposed()) {
                    int i = this.size;
                    Integer num = (Integer) dVar.index();
                    int intValue = num != null ? num.intValue() : 0;
                    long j = dVar.get();
                    long j2 = j;
                    long j3 = 0;
                    while (j2 != 0 && intValue < i) {
                        Object obj = get(intValue);
                        try {
                            if (NotificationLite.accept(obj, subscriber) || dVar.isDisposed()) {
                                return;
                            }
                            intValue++;
                            j2--;
                            j3++;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            dVar.dispose();
                            if (NotificationLite.isError(obj) || NotificationLite.isComplete(obj)) {
                                return;
                            }
                            subscriber.onError(th);
                            return;
                        }
                    }
                    if (j3 != 0) {
                        dVar.index = Integer.valueOf(intValue);
                        if (j != Long.MAX_VALUE) {
                            dVar.produced(j3);
                        }
                    }
                    synchronized (dVar) {
                        if (!dVar.missed) {
                            dVar.emitting = false;
                            return;
                        }
                        dVar.missed = false;
                    }
                }
            }
        }
    }

    public FlowableReplay(Publisher<T> publisher, Flowable<T> flowable, AtomicReference<j<T>> atomicReference, Callable<? extends g<T>> callable) {
        this.l = publisher;
        this.i = flowable;
        this.j = atomicReference;
        this.k = callable;
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> flowable, int i2) {
        if (i2 == Integer.MAX_VALUE) {
            return createFrom(flowable);
        }
        return f(flowable, new h(i2));
    }

    public static <T> ConnectableFlowable<T> createFrom(Flowable<? extends T> flowable) {
        return f(flowable, m);
    }

    public static <T> ConnectableFlowable<T> f(Flowable<T> flowable, Callable<? extends g<T>> callable) {
        AtomicReference atomicReference = new AtomicReference();
        return RxJavaPlugins.onAssembly((ConnectableFlowable) new FlowableReplay(new i(atomicReference, callable), flowable, atomicReference, callable));
    }

    public static <U, R> Flowable<R> multicastSelector(Callable<? extends ConnectableFlowable<U>> callable, Function<? super Flowable<U>, ? extends Publisher<R>> function) {
        return new e(callable, function);
    }

    public static <T> ConnectableFlowable<T> observeOn(ConnectableFlowable<T> connectableFlowable, Scheduler scheduler) {
        return RxJavaPlugins.onAssembly((ConnectableFlowable) new b(connectableFlowable, connectableFlowable.observeOn(scheduler)));
    }

    @Override // io.reactivex.flowables.ConnectableFlowable
    public void connect(Consumer<? super Disposable> consumer) {
        j<T> jVar;
        while (true) {
            jVar = this.j.get();
            if (jVar != null && !jVar.isDisposed()) {
                break;
            }
            try {
                j<T> jVar2 = new j<>(this.k.call());
                if (this.j.compareAndSet(jVar, jVar2)) {
                    jVar = jVar2;
                    break;
                }
            } finally {
                Exceptions.throwIfFatal(th);
                RuntimeException wrapOrThrow = ExceptionHelper.wrapOrThrow(th);
            }
        }
        boolean z = !jVar.shouldConnect.get() && jVar.shouldConnect.compareAndSet(false, true);
        try {
            consumer.accept(jVar);
            if (z) {
                this.i.subscribe((FlowableSubscriber) jVar);
            }
        } catch (Throwable th) {
            if (z) {
                jVar.shouldConnect.compareAndSet(true, false);
            }
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    @Override // io.reactivex.internal.disposables.ResettableConnectable
    public void resetIf(Disposable disposable) {
        this.j.compareAndSet((j) disposable, null);
    }

    @Override // io.reactivex.internal.fuseable.HasUpstreamPublisher
    public Publisher<T> source() {
        return this.i;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.l.subscribe(subscriber);
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> flowable, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return create(flowable, j2, timeUnit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> flowable, long j2, TimeUnit timeUnit, Scheduler scheduler, int i2) {
        return f(flowable, new k(i2, j2, timeUnit, scheduler));
    }
}
