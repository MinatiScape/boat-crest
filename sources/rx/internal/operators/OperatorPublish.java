package rx.internal.operators;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.observables.ConnectableObservable;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OperatorPublish<T> extends ConnectableObservable<T> {
    public final Observable<? extends T> i;
    public final AtomicReference<d<T>> j;

    /* loaded from: classes13.dex */
    public static class a implements Observable.OnSubscribe<T> {
        public final /* synthetic */ AtomicReference h;

        public a(AtomicReference atomicReference) {
            this.h = atomicReference;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            while (true) {
                d dVar = (d) this.h.get();
                if (dVar == null || dVar.isUnsubscribed()) {
                    d dVar2 = new d(this.h);
                    dVar2.e();
                    if (this.h.compareAndSet(dVar, dVar2)) {
                        dVar = dVar2;
                    } else {
                        continue;
                    }
                }
                c<T> cVar = new c<>(dVar, subscriber);
                if (dVar.b(cVar)) {
                    subscriber.add(cVar);
                    subscriber.setProducer(cVar);
                    return;
                }
            }
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class b<R> implements Observable.OnSubscribe<R> {
        public final /* synthetic */ boolean h;
        public final /* synthetic */ Func1 i;
        public final /* synthetic */ Observable j;

        /* loaded from: classes13.dex */
        public class a extends Subscriber<R> {
            public final /* synthetic */ Subscriber l;
            public final /* synthetic */ OnSubscribePublishMulticast m;

            public a(b bVar, Subscriber subscriber, OnSubscribePublishMulticast onSubscribePublishMulticast) {
                this.l = subscriber;
                this.m = onSubscribePublishMulticast;
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.m.unsubscribe();
                this.l.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                this.m.unsubscribe();
                this.l.onError(th);
            }

            @Override // rx.Observer
            public void onNext(R r) {
                this.l.onNext(r);
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                this.l.setProducer(producer);
            }
        }

        public b(boolean z, Func1 func1, Observable observable) {
            this.h = z;
            this.i = func1;
            this.j = observable;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super R> subscriber) {
            OnSubscribePublishMulticast onSubscribePublishMulticast = new OnSubscribePublishMulticast(RxRingBuffer.SIZE, this.h);
            a aVar = new a(this, subscriber, onSubscribePublishMulticast);
            subscriber.add(onSubscribePublishMulticast);
            subscriber.add(aVar);
            ((Observable) this.i.call(Observable.unsafeCreate(onSubscribePublishMulticast))).unsafeSubscribe(aVar);
            this.j.unsafeSubscribe(onSubscribePublishMulticast.subscriber());
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends AtomicLong implements Producer, Subscription {
        public static final long NOT_REQUESTED = -4611686018427387904L;
        public static final long UNSUBSCRIBED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        public final Subscriber<? super T> child;
        public final d<T> parent;

        public c(d<T> dVar, Subscriber<? super T> subscriber) {
            this.parent = dVar;
            this.child = subscriber;
            lazySet(-4611686018427387904L);
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get() == Long.MIN_VALUE;
        }

        public long produced(long j) {
            long j2;
            long j3;
            if (j > 0) {
                do {
                    j2 = get();
                    if (j2 == -4611686018427387904L) {
                        throw new IllegalStateException("Produced without request");
                    }
                    if (j2 == Long.MIN_VALUE) {
                        return Long.MIN_VALUE;
                    }
                    j3 = j2 - j;
                    if (j3 < 0) {
                        throw new IllegalStateException("More produced (" + j + ") than requested (" + j2 + ")");
                    }
                } while (!compareAndSet(j2, j3));
                return j3;
            }
            throw new IllegalArgumentException("Cant produce zero or less");
        }

        @Override // rx.Producer
        public void request(long j) {
            long j2;
            long j3;
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                return;
            }
            do {
                j2 = get();
                if (j2 == Long.MIN_VALUE) {
                    return;
                }
                if (j2 >= 0 && i == 0) {
                    return;
                }
                if (j2 == -4611686018427387904L) {
                    j3 = j;
                } else {
                    j3 = j2 + j;
                    if (j3 < 0) {
                        j3 = Long.MAX_VALUE;
                    }
                }
            } while (!compareAndSet(j2, j3));
            this.parent.d();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (get() == Long.MIN_VALUE || getAndSet(Long.MIN_VALUE) == Long.MIN_VALUE) {
                return;
            }
            this.parent.f(this);
            this.parent.d();
        }
    }

    /* loaded from: classes13.dex */
    public static final class d<T> extends Subscriber<T> {
        public static final c[] s = new c[0];
        public static final c[] t = new c[0];
        public final Queue<Object> l;
        public final AtomicReference<d<T>> m;
        public volatile Object n;
        public final AtomicReference<c[]> o;
        public final AtomicBoolean p;
        public boolean q;
        public boolean r;

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public a() {
            }

            @Override // rx.functions.Action0
            public void call() {
                d.this.o.getAndSet(d.t);
                d<T> dVar = d.this;
                dVar.m.compareAndSet(dVar, null);
            }
        }

        public d(AtomicReference<d<T>> atomicReference) {
            this.l = UnsafeAccess.isUnsafeAvailable() ? new SpscArrayQueue<>(RxRingBuffer.SIZE) : new SpscAtomicArrayQueue<>(RxRingBuffer.SIZE);
            this.o = new AtomicReference<>(s);
            this.m = atomicReference;
            this.p = new AtomicBoolean();
        }

        public boolean b(c<T> cVar) {
            c[] cVarArr;
            c[] cVarArr2;
            Objects.requireNonNull(cVar);
            do {
                cVarArr = this.o.get();
                if (cVarArr == t) {
                    return false;
                }
                int length = cVarArr.length;
                cVarArr2 = new c[length + 1];
                System.arraycopy(cVarArr, 0, cVarArr2, 0, length);
                cVarArr2[length] = cVar;
            } while (!this.o.compareAndSet(cVarArr, cVarArr2));
            return true;
        }

        public boolean c(Object obj, boolean z) {
            int i = 0;
            if (obj != null) {
                if (!NotificationLite.isCompleted(obj)) {
                    Throwable error = NotificationLite.getError(obj);
                    this.m.compareAndSet(this, null);
                    try {
                        c[] andSet = this.o.getAndSet(t);
                        int length = andSet.length;
                        while (i < length) {
                            andSet[i].child.onError(error);
                            i++;
                        }
                        return true;
                    } finally {
                    }
                } else if (z) {
                    this.m.compareAndSet(this, null);
                    try {
                        c[] andSet2 = this.o.getAndSet(t);
                        int length2 = andSet2.length;
                        while (i < length2) {
                            andSet2[i].child.onCompleted();
                            i++;
                        }
                        return true;
                    } finally {
                    }
                }
            }
            return false;
        }

        public void d() {
            boolean z;
            long j;
            synchronized (this) {
                boolean z2 = true;
                if (this.q) {
                    this.r = true;
                    return;
                }
                this.q = true;
                this.r = false;
                while (true) {
                    try {
                        Object obj = this.n;
                        boolean isEmpty = this.l.isEmpty();
                        if (c(obj, isEmpty)) {
                            return;
                        }
                        if (!isEmpty) {
                            c[] cVarArr = this.o.get();
                            int length = cVarArr.length;
                            long j2 = Long.MAX_VALUE;
                            int i = 0;
                            for (c cVar : cVarArr) {
                                long j3 = cVar.get();
                                if (j3 >= 0) {
                                    j2 = Math.min(j2, j3);
                                } else if (j3 == Long.MIN_VALUE) {
                                    i++;
                                }
                            }
                            if (length == i) {
                                if (c(this.n, this.l.poll() == null ? z2 : false)) {
                                    return;
                                }
                                request(1L);
                            } else {
                                int i2 = 0;
                                while (true) {
                                    j = i2;
                                    if (j >= j2) {
                                        break;
                                    }
                                    Object obj2 = this.n;
                                    Object poll = this.l.poll();
                                    boolean z3 = poll == null ? z2 : false;
                                    if (c(obj2, z3)) {
                                        return;
                                    }
                                    if (z3) {
                                        isEmpty = z3;
                                        break;
                                    }
                                    Object value = NotificationLite.getValue(poll);
                                    for (c cVar2 : cVarArr) {
                                        if (cVar2.get() > 0) {
                                            cVar2.child.onNext(value);
                                            cVar2.produced(1L);
                                        }
                                    }
                                    i2++;
                                    isEmpty = z3;
                                    z2 = true;
                                }
                                if (i2 > 0) {
                                    request(j);
                                }
                                z2 = (j2 == 0 || isEmpty) ? true : true;
                            }
                        }
                        synchronized (this) {
                            try {
                                if (!this.r) {
                                    this.q = false;
                                    try {
                                        return;
                                    } catch (Throwable th) {
                                        th = th;
                                        z = true;
                                        while (true) {
                                            try {
                                                break;
                                            } catch (Throwable th2) {
                                                th = th2;
                                            }
                                        }
                                        throw th;
                                    }
                                }
                                this.r = false;
                            } catch (Throwable th3) {
                                th = th3;
                                z = false;
                            }
                        }
                        try {
                            break;
                            throw th;
                        } catch (Throwable th4) {
                            th = th4;
                            if (!z) {
                                synchronized (this) {
                                    this.q = false;
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        z = false;
                    }
                }
            }
        }

        public void e() {
            add(Subscriptions.create(new a()));
        }

        public void f(c<T> cVar) {
            c[] cVarArr;
            c[] cVarArr2;
            do {
                cVarArr = this.o.get();
                if (cVarArr == s || cVarArr == t) {
                    return;
                }
                int i = -1;
                int length = cVarArr.length;
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
                    cVarArr2 = s;
                } else {
                    c[] cVarArr3 = new c[length - 1];
                    System.arraycopy(cVarArr, 0, cVarArr3, 0, i);
                    System.arraycopy(cVarArr, i + 1, cVarArr3, i, (length - i) - 1);
                    cVarArr2 = cVarArr3;
                }
            } while (!this.o.compareAndSet(cVarArr, cVarArr2));
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.n == null) {
                this.n = NotificationLite.completed();
                d();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.n == null) {
                this.n = NotificationLite.error(th);
                d();
            }
        }

        @Override // rx.Observer
        public void onNext(T t2) {
            if (!this.l.offer(NotificationLite.next(t2))) {
                onError(new MissingBackpressureException());
            } else {
                d();
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(RxRingBuffer.SIZE);
        }
    }

    public OperatorPublish(Observable.OnSubscribe<T> onSubscribe, Observable<? extends T> observable, AtomicReference<d<T>> atomicReference) {
        super(onSubscribe);
        this.i = observable;
        this.j = atomicReference;
    }

    public static <T> ConnectableObservable<T> create(Observable<? extends T> observable) {
        AtomicReference atomicReference = new AtomicReference();
        return new OperatorPublish(new a(atomicReference), observable, atomicReference);
    }

    @Override // rx.observables.ConnectableObservable
    public void connect(Action1<? super Subscription> action1) {
        d<T> dVar;
        while (true) {
            dVar = this.j.get();
            if (dVar != null && !dVar.isUnsubscribed()) {
                break;
            }
            d<T> dVar2 = new d<>(this.j);
            dVar2.e();
            if (this.j.compareAndSet(dVar, dVar2)) {
                dVar = dVar2;
                break;
            }
        }
        boolean z = true;
        if (dVar.p.get() || !dVar.p.compareAndSet(false, true)) {
            z = false;
        }
        action1.call(dVar);
        if (z) {
            this.i.unsafeSubscribe(dVar);
        }
    }

    public static <T, R> Observable<R> create(Observable<? extends T> observable, Func1<? super Observable<T>, ? extends Observable<R>> func1) {
        return create(observable, func1, false);
    }

    public static <T, R> Observable<R> create(Observable<? extends T> observable, Func1<? super Observable<T>, ? extends Observable<R>> func1, boolean z) {
        return Observable.unsafeCreate(new b(z, func1, observable));
    }
}
