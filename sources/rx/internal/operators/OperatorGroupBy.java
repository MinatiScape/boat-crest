package rx.internal.operators;

import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.UtilityFunctions;
import rx.observables.GroupedObservable;
import rx.observers.Subscribers;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OperatorGroupBy<T, K, V> implements Observable.Operator<GroupedObservable<K, V>, T> {
    public final Func1<? super T, ? extends K> h;
    public final Func1<? super T, ? extends V> i;
    public final int j;
    public final boolean k;
    public final Func1<Action1<K>, Map<K, Object>> l;

    /* loaded from: classes13.dex */
    public static final class GroupByProducer implements Producer {
        public final GroupBySubscriber<?, ?, ?> h;

        public GroupByProducer(GroupBySubscriber<?, ?, ?> groupBySubscriber) {
            this.h = groupBySubscriber;
        }

        @Override // rx.Producer
        public void request(long j) {
            this.h.requestMore(j);
        }
    }

    /* loaded from: classes13.dex */
    public class a implements Action0 {
        public final /* synthetic */ GroupBySubscriber h;

        public a(OperatorGroupBy operatorGroupBy, GroupBySubscriber groupBySubscriber) {
            this.h = groupBySubscriber;
        }

        @Override // rx.functions.Action0
        public void call() {
            this.h.cancel();
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<K, T> extends GroupedObservable<K, T> {
        public final c<T, K> j;

        public b(K k, c<T, K> cVar) {
            super(k, cVar);
            this.j = cVar;
        }

        public static <T, K> b<K, T> c(K k, int i, GroupBySubscriber<?, K, T> groupBySubscriber, boolean z) {
            return new b<>(k, new c(i, groupBySubscriber, k, z));
        }

        public void d() {
            this.j.onComplete();
        }

        public void onError(Throwable th) {
            this.j.onError(th);
        }

        public void onNext(T t) {
            this.j.onNext(t);
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T, K> extends AtomicInteger implements Producer, Subscription, Observable.OnSubscribe<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        public final boolean delayError;
        public volatile boolean done;
        public Throwable error;
        public final K key;
        public final GroupBySubscriber<?, K, T> parent;
        public final Queue<Object> queue = new ConcurrentLinkedQueue();
        public final AtomicBoolean cancelled = new AtomicBoolean();
        public final AtomicReference<Subscriber<? super T>> actual = new AtomicReference<>();
        public final AtomicBoolean once = new AtomicBoolean();
        public final AtomicLong requested = new AtomicLong();

        public c(int i, GroupBySubscriber<?, K, T> groupBySubscriber, K k, boolean z) {
            this.parent = groupBySubscriber;
            this.key = k;
            this.delayError = z;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public boolean checkTerminated(boolean z, boolean z2, Subscriber<? super T> subscriber, boolean z3) {
            if (this.cancelled.get()) {
                this.queue.clear();
                this.parent.cancel(this.key);
                return true;
            } else if (z) {
                if (z3) {
                    if (z2) {
                        Throwable th = this.error;
                        if (th != null) {
                            subscriber.onError(th);
                        } else {
                            subscriber.onCompleted();
                        }
                        return true;
                    }
                    return false;
                }
                Throwable th2 = this.error;
                if (th2 != null) {
                    this.queue.clear();
                    subscriber.onError(th2);
                    return true;
                } else if (z2) {
                    subscriber.onCompleted();
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        public void drain() {
            if (getAndIncrement() != 0) {
                return;
            }
            Queue<Object> queue = this.queue;
            boolean z = this.delayError;
            Subscriber<? super T> subscriber = this.actual.get();
            int i = 1;
            while (true) {
                if (subscriber != null) {
                    if (checkTerminated(this.done, queue.isEmpty(), subscriber, z)) {
                        return;
                    }
                    long j = this.requested.get();
                    long j2 = 0;
                    while (j2 != j) {
                        boolean z2 = this.done;
                        Object poll = queue.poll();
                        boolean z3 = poll == null;
                        if (checkTerminated(z2, z3, subscriber, z)) {
                            return;
                        }
                        if (z3) {
                            break;
                        }
                        subscriber.onNext((Object) NotificationLite.getValue(poll));
                        j2++;
                    }
                    if (j2 != 0) {
                        if (j != Long.MAX_VALUE) {
                            BackpressureUtils.produced(this.requested, j2);
                        }
                        this.parent.u.request(j2);
                    }
                }
                i = addAndGet(-i);
                if (i == 0) {
                    return;
                }
                if (subscriber == null) {
                    subscriber = this.actual.get();
                }
            }
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.cancelled.get();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void onError(Throwable th) {
            this.error = th;
            this.done = true;
            drain();
        }

        public void onNext(T t) {
            if (t == null) {
                this.error = new NullPointerException();
                this.done = true;
            } else {
                this.queue.offer(NotificationLite.next(t));
            }
            drain();
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            } else if (i != 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                drain();
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (this.cancelled.compareAndSet(false, true) && getAndIncrement() == 0) {
                this.parent.cancel(this.key);
            }
        }

        public void call(Subscriber<? super T> subscriber) {
            if (this.once.compareAndSet(false, true)) {
                subscriber.add(this);
                subscriber.setProducer(this);
                this.actual.lazySet(subscriber);
                drain();
                return;
            }
            subscriber.onError(new IllegalStateException("Only one Subscriber allowed!"));
        }
    }

    public OperatorGroupBy(Func1<? super T, ? extends K> func1) {
        this(func1, UtilityFunctions.identity(), RxRingBuffer.SIZE, false, null);
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorGroupBy(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12) {
        this(func1, func12, RxRingBuffer.SIZE, false, null);
    }

    public Subscriber<? super T> call(Subscriber<? super GroupedObservable<K, V>> subscriber) {
        try {
            GroupBySubscriber groupBySubscriber = new GroupBySubscriber(subscriber, this.h, this.i, this.j, this.k, this.l);
            subscriber.add(Subscriptions.create(new a(this, groupBySubscriber)));
            subscriber.setProducer(groupBySubscriber.s);
            return groupBySubscriber;
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
            Subscriber<? super T> empty = Subscribers.empty();
            empty.unsubscribe();
            return empty;
        }
    }

    /* loaded from: classes13.dex */
    public static final class GroupBySubscriber<T, K, V> extends Subscriber<T> {
        public static final Object B = new Object();
        public final AtomicInteger A;
        public final Subscriber<? super GroupedObservable<K, V>> l;
        public final Func1<? super T, ? extends K> m;
        public final Func1<? super T, ? extends V> n;
        public final int o;
        public final boolean p;
        public final Map<Object, b<K, V>> q;
        public final Queue<GroupedObservable<K, V>> r = new ConcurrentLinkedQueue();
        public final GroupByProducer s;
        public final Queue<K> t;
        public final ProducerArbiter u;
        public final AtomicBoolean v;
        public final AtomicLong w;
        public final AtomicInteger x;
        public Throwable y;
        public volatile boolean z;

        /* loaded from: classes13.dex */
        public static class a<K> implements Action1<K> {
            public final Queue<K> h;

            public a(Queue<K> queue) {
                this.h = queue;
            }

            @Override // rx.functions.Action1
            public void call(K k) {
                this.h.offer(k);
            }
        }

        public GroupBySubscriber(Subscriber<? super GroupedObservable<K, V>> subscriber, Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, int i, boolean z, Func1<Action1<K>, Map<K, Object>> func13) {
            this.l = subscriber;
            this.m = func1;
            this.n = func12;
            this.o = i;
            this.p = z;
            ProducerArbiter producerArbiter = new ProducerArbiter();
            this.u = producerArbiter;
            producerArbiter.request(i);
            this.s = new GroupByProducer(this);
            this.v = new AtomicBoolean();
            this.w = new AtomicLong();
            this.x = new AtomicInteger(1);
            this.A = new AtomicInteger();
            if (func13 == null) {
                this.q = new ConcurrentHashMap();
                this.t = null;
                return;
            }
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            this.t = concurrentLinkedQueue;
            this.q = c(func13, new a(concurrentLinkedQueue));
        }

        public boolean b(boolean z, boolean z2, Subscriber<? super GroupedObservable<K, V>> subscriber, Queue<?> queue) {
            if (z) {
                Throwable th = this.y;
                if (th != null) {
                    e(subscriber, queue, th);
                    return true;
                } else if (z2) {
                    this.l.onCompleted();
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        public final Map<Object, b<K, V>> c(Func1<Action1<K>, Map<K, Object>> func1, Action1<K> action1) {
            return func1.call(action1);
        }

        public void cancel() {
            if (this.v.compareAndSet(false, true) && this.x.decrementAndGet() == 0) {
                unsubscribe();
            }
        }

        public void d() {
            if (this.A.getAndIncrement() != 0) {
                return;
            }
            Queue<GroupedObservable<K, V>> queue = this.r;
            Subscriber<? super GroupedObservable<K, V>> subscriber = this.l;
            int i = 1;
            while (!b(this.z, queue.isEmpty(), subscriber, queue)) {
                long j = this.w.get();
                long j2 = 0;
                while (j2 != j) {
                    boolean z = this.z;
                    GroupedObservable<K, V> poll = queue.poll();
                    boolean z2 = poll == null;
                    if (b(z, z2, subscriber, queue)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    subscriber.onNext(poll);
                    j2++;
                }
                if (j2 != 0) {
                    if (j != Long.MAX_VALUE) {
                        BackpressureUtils.produced(this.w, j2);
                    }
                    this.u.request(j2);
                }
                i = this.A.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }

        public void e(Subscriber<? super GroupedObservable<K, V>> subscriber, Queue<?> queue, Throwable th) {
            queue.clear();
            ArrayList<b> arrayList = new ArrayList(this.q.values());
            this.q.clear();
            Queue<K> queue2 = this.t;
            if (queue2 != null) {
                queue2.clear();
            }
            for (b bVar : arrayList) {
                bVar.onError(th);
            }
            subscriber.onError(th);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.z) {
                return;
            }
            for (b<K, V> bVar : this.q.values()) {
                bVar.d();
            }
            this.q.clear();
            Queue<K> queue = this.t;
            if (queue != null) {
                queue.clear();
            }
            this.z = true;
            this.x.decrementAndGet();
            d();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.z) {
                RxJavaHooks.onError(th);
                return;
            }
            this.y = th;
            this.z = true;
            this.x.decrementAndGet();
            d();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.z) {
                return;
            }
            Queue<?> queue = this.r;
            Subscriber<? super GroupedObservable<K, V>> subscriber = this.l;
            try {
                K call = this.m.call(t);
                boolean z = false;
                Object obj = call != null ? call : B;
                b bVar = this.q.get(obj);
                if (bVar == null) {
                    if (this.v.get()) {
                        return;
                    }
                    bVar = b.c(call, this.o, this, this.p);
                    this.q.put(obj, bVar);
                    this.x.getAndIncrement();
                    z = true;
                }
                try {
                    bVar.onNext(this.n.call(t));
                    if (this.t != null) {
                        while (true) {
                            K poll = this.t.poll();
                            if (poll == null) {
                                break;
                            }
                            b<K, V> bVar2 = this.q.get(poll);
                            if (bVar2 != null) {
                                bVar2.d();
                            }
                        }
                    }
                    if (z) {
                        queue.offer(bVar);
                        d();
                    }
                } catch (Throwable th) {
                    unsubscribe();
                    e(subscriber, queue, th);
                }
            } catch (Throwable th2) {
                unsubscribe();
                e(subscriber, queue, th2);
            }
        }

        public void requestMore(long j) {
            if (j >= 0) {
                BackpressureUtils.getAndAddRequest(this.w, j);
                d();
                return;
            }
            throw new IllegalArgumentException("n >= 0 required but it was " + j);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.u.setProducer(producer);
        }

        public void cancel(K k) {
            if (k == null) {
                k = (K) B;
            }
            if (this.q.remove(k) == null || this.x.decrementAndGet() != 0) {
                return;
            }
            unsubscribe();
        }
    }

    public OperatorGroupBy(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, Func1<Action1<K>, Map<K, Object>> func13) {
        this(func1, func12, RxRingBuffer.SIZE, false, func13);
    }

    public OperatorGroupBy(Func1<? super T, ? extends K> func1, Func1<? super T, ? extends V> func12, int i, boolean z, Func1<Action1<K>, Map<K, Object>> func13) {
        this.h = func1;
        this.i = func12;
        this.j = i;
        this.k = z;
        this.l = func13;
    }
}
