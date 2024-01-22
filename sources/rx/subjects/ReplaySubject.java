package rx.subjects;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.internal.operators.BackpressureUtils;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class ReplaySubject<T> extends Subject<T, T> {
    public static final Object[] j = new Object[0];
    public final e<T> i;

    /* loaded from: classes13.dex */
    public interface a<T> {
        void a(b<T> bVar);

        void complete();

        Throwable error();

        void error(Throwable th);

        boolean isEmpty();

        T last();

        void next(T t);

        int size();

        T[] toArray(T[] tArr);
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends AtomicInteger implements Producer, Subscription {
        private static final long serialVersionUID = -5006209596735204567L;
        public final Subscriber<? super T> actual;
        public int index;
        public Object node;
        public final AtomicLong requested = new AtomicLong();
        public final e<T> state;
        public int tailIndex;

        public b(Subscriber<? super T> subscriber, e<T> eVar) {
            this.actual = subscriber;
            this.state = eVar;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.actual.isUnsubscribed();
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i > 0) {
                BackpressureUtils.getAndAddRequest(this.requested, j);
                this.state.buffer.a(this);
            } else if (i >= 0) {
            } else {
                throw new IllegalArgumentException("n >= required but it was " + j);
            }
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.state.remove(this);
        }
    }

    /* loaded from: classes13.dex */
    public static final class e<T> extends AtomicReference<b<T>[]> implements Observable.OnSubscribe<T>, Observer<T> {
        public static final b[] EMPTY = new b[0];
        public static final b[] TERMINATED = new b[0];
        private static final long serialVersionUID = 5952362471246910544L;
        public final a<T> buffer;

        public e(a<T> aVar) {
            this.buffer = aVar;
            lazySet(EMPTY);
        }

        public boolean add(b<T> bVar) {
            b<T>[] bVarArr;
            b[] bVarArr2;
            do {
                bVarArr = get();
                if (bVarArr == TERMINATED) {
                    return false;
                }
                int length = bVarArr.length;
                bVarArr2 = new b[length + 1];
                System.arraycopy(bVarArr, 0, bVarArr2, 0, length);
                bVarArr2[length] = bVar;
            } while (!compareAndSet(bVarArr, bVarArr2));
            return true;
        }

        @Override // rx.functions.Action1
        public /* bridge */ /* synthetic */ void call(Object obj) {
            call((Subscriber) ((Subscriber) obj));
        }

        public boolean isTerminated() {
            return get() == TERMINATED;
        }

        @Override // rx.Observer
        public void onCompleted() {
            a<T> aVar = this.buffer;
            aVar.complete();
            for (b<T> bVar : getAndSet(TERMINATED)) {
                aVar.a(bVar);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            a<T> aVar = this.buffer;
            aVar.error(th);
            ArrayList arrayList = null;
            for (b<T> bVar : getAndSet(TERMINATED)) {
                try {
                    aVar.a(bVar);
                } catch (Throwable th2) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th2);
                }
            }
            Exceptions.throwIfAny(arrayList);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            a<T> aVar = this.buffer;
            aVar.next(t);
            for (b<T> bVar : get()) {
                aVar.a(bVar);
            }
        }

        public void remove(b<T> bVar) {
            b<T>[] bVarArr;
            b[] bVarArr2;
            do {
                bVarArr = get();
                if (bVarArr == TERMINATED || bVarArr == EMPTY) {
                    return;
                }
                int length = bVarArr.length;
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
                    bVarArr2 = EMPTY;
                } else {
                    b[] bVarArr3 = new b[length - 1];
                    System.arraycopy(bVarArr, 0, bVarArr3, 0, i);
                    System.arraycopy(bVarArr, i + 1, bVarArr3, i, (length - i) - 1);
                    bVarArr2 = bVarArr3;
                }
            } while (!compareAndSet(bVarArr, bVarArr2));
        }

        public void call(Subscriber<? super T> subscriber) {
            b<T> bVar = new b<>(subscriber, this);
            subscriber.add(bVar);
            subscriber.setProducer(bVar);
            if (add(bVar) && bVar.isUnsubscribed()) {
                remove(bVar);
            } else {
                this.buffer.a(bVar);
            }
        }
    }

    public ReplaySubject(e<T> eVar) {
        super(eVar);
        this.i = eVar;
    }

    public static <T> ReplaySubject<T> create() {
        return create(16);
    }

    public static <T> ReplaySubject<T> createWithSize(int i) {
        return new ReplaySubject<>(new e(new d(i)));
    }

    public static <T> ReplaySubject<T> createWithTime(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return createWithTimeAndSize(j2, timeUnit, Integer.MAX_VALUE, scheduler);
    }

    public static <T> ReplaySubject<T> createWithTimeAndSize(long j2, TimeUnit timeUnit, int i, Scheduler scheduler) {
        return new ReplaySubject<>(new e(new c(i, timeUnit.toMillis(j2), scheduler)));
    }

    public Throwable getThrowable() {
        if (this.i.isTerminated()) {
            return this.i.buffer.error();
        }
        return null;
    }

    public T getValue() {
        return this.i.buffer.last();
    }

    public T[] getValues(T[] tArr) {
        return this.i.buffer.toArray(tArr);
    }

    public boolean hasAnyValue() {
        return !this.i.buffer.isEmpty();
    }

    public boolean hasCompleted() {
        return this.i.isTerminated() && this.i.buffer.error() == null;
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        return this.i.get().length != 0;
    }

    public boolean hasThrowable() {
        return this.i.isTerminated() && this.i.buffer.error() != null;
    }

    public boolean hasValue() {
        return hasAnyValue();
    }

    @Override // rx.Observer
    public void onCompleted() {
        this.i.onCompleted();
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.i.onError(th);
    }

    @Override // rx.Observer
    public void onNext(T t) {
        this.i.onNext(t);
    }

    public int size() {
        return this.i.buffer.size();
    }

    /* loaded from: classes13.dex */
    public static final class d<T> implements a<T> {

        /* renamed from: a  reason: collision with root package name */
        public final int f15703a;
        public volatile a<T> b;
        public a<T> c;
        public int d;
        public volatile boolean e;
        public Throwable f;

        /* loaded from: classes13.dex */
        public static final class a<T> extends AtomicReference<a<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            public final T value;

            public a(T t) {
                this.value = t;
            }
        }

        public d(int i) {
            this.f15703a = i;
            a<T> aVar = new a<>(null);
            this.c = aVar;
            this.b = aVar;
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x005e, code lost:
            if (r12 != 0) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0064, code lost:
            if (r2.isUnsubscribed() == false) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0066, code lost:
            r18.node = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0068, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0069, code lost:
            r3 = r17.e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x006f, code lost:
            if (r7.get() != null) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0071, code lost:
            r13 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0072, code lost:
            if (r3 == false) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0074, code lost:
            if (r13 == false) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0076, code lost:
            r18.node = null;
            r1 = r17.f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007a, code lost:
            if (r1 == null) goto L42;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x007c, code lost:
            r2.onError(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0080, code lost:
            r2.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0083, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0086, code lost:
            if (r10 == 0) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x008f, code lost:
            if (r5 == Long.MAX_VALUE) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0091, code lost:
            rx.internal.operators.BackpressureUtils.produced(r18.requested, r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0096, code lost:
            r18.node = r7;
            r4 = r18.addAndGet(-r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
            return;
         */
        @Override // rx.subjects.ReplaySubject.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(rx.subjects.ReplaySubject.b<T> r18) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                int r2 = r18.getAndIncrement()
                if (r2 == 0) goto Lb
                return
            Lb:
                rx.Subscriber<? super T> r2 = r1.actual
                r4 = 1
            Le:
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r5 = r5.get()
                java.lang.Object r7 = r1.node
                rx.subjects.ReplaySubject$d$a r7 = (rx.subjects.ReplaySubject.d.a) r7
                r8 = 0
                if (r7 != 0) goto L1e
                rx.subjects.ReplaySubject$d$a<T> r7 = r0.b
            L1e:
                r10 = r8
            L1f:
                int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                r13 = 0
                r14 = 0
                if (r12 == 0) goto L5e
                boolean r15 = r2.isUnsubscribed()
                if (r15 == 0) goto L2e
                r1.node = r14
                return
            L2e:
                boolean r15 = r0.e
                java.lang.Object r16 = r7.get()
                r3 = r16
                rx.subjects.ReplaySubject$d$a r3 = (rx.subjects.ReplaySubject.d.a) r3
                if (r3 != 0) goto L3d
                r16 = 1
                goto L3f
            L3d:
                r16 = r13
            L3f:
                if (r15 == 0) goto L51
                if (r16 == 0) goto L51
                r1.node = r14
                java.lang.Throwable r1 = r0.f
                if (r1 == 0) goto L4d
                r2.onError(r1)
                goto L50
            L4d:
                r2.onCompleted()
            L50:
                return
            L51:
                if (r16 == 0) goto L54
                goto L5e
            L54:
                T r7 = r3.value
                r2.onNext(r7)
                r12 = 1
                long r10 = r10 + r12
                r7 = r3
                goto L1f
            L5e:
                if (r12 != 0) goto L84
                boolean r3 = r2.isUnsubscribed()
                if (r3 == 0) goto L69
                r1.node = r14
                return
            L69:
                boolean r3 = r0.e
                java.lang.Object r12 = r7.get()
                if (r12 != 0) goto L72
                r13 = 1
            L72:
                if (r3 == 0) goto L84
                if (r13 == 0) goto L84
                r1.node = r14
                java.lang.Throwable r1 = r0.f
                if (r1 == 0) goto L80
                r2.onError(r1)
                goto L83
            L80:
                r2.onCompleted()
            L83:
                return
            L84:
                int r3 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r3 == 0) goto L96
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r3 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r3 == 0) goto L96
                java.util.concurrent.atomic.AtomicLong r3 = r1.requested
                rx.internal.operators.BackpressureUtils.produced(r3, r10)
            L96:
                r1.node = r7
                int r3 = -r4
                int r4 = r1.addAndGet(r3)
                if (r4 != 0) goto Le
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.ReplaySubject.d.a(rx.subjects.ReplaySubject$b):void");
        }

        @Override // rx.subjects.ReplaySubject.a
        public void complete() {
            this.e = true;
        }

        @Override // rx.subjects.ReplaySubject.a
        public void error(Throwable th) {
            this.f = th;
            this.e = true;
        }

        @Override // rx.subjects.ReplaySubject.a
        public boolean isEmpty() {
            return this.b.get() == null;
        }

        @Override // rx.subjects.ReplaySubject.a
        public T last() {
            a<T> aVar = this.b;
            while (true) {
                a<T> aVar2 = aVar.get();
                if (aVar2 == null) {
                    return aVar.value;
                }
                aVar = aVar2;
            }
        }

        @Override // rx.subjects.ReplaySubject.a
        public void next(T t) {
            a<T> aVar = new a<>(t);
            this.c.set(aVar);
            this.c = aVar;
            int i = this.d;
            if (i == this.f15703a) {
                this.b = this.b.get();
            } else {
                this.d = i + 1;
            }
        }

        @Override // rx.subjects.ReplaySubject.a
        public int size() {
            a<T> aVar = this.b.get();
            int i = 0;
            while (aVar != null && i != Integer.MAX_VALUE) {
                aVar = aVar.get();
                i++;
            }
            return i;
        }

        @Override // rx.subjects.ReplaySubject.a
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (a<T> aVar = this.b.get(); aVar != null; aVar = aVar.get()) {
                arrayList.add(aVar.value);
            }
            return (T[]) arrayList.toArray(tArr);
        }

        @Override // rx.subjects.ReplaySubject.a
        public Throwable error() {
            return this.f;
        }
    }

    public static <T> ReplaySubject<T> create(int i) {
        if (i > 0) {
            return new ReplaySubject<>(new e(new f(i)));
        }
        throw new IllegalArgumentException("capacity > 0 required but it was " + i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Object[] getValues() {
        Object[] objArr = j;
        Object[] values = getValues(objArr);
        return values == objArr ? new Object[0] : values;
    }

    /* loaded from: classes13.dex */
    public static final class c<T> implements a<T> {

        /* renamed from: a  reason: collision with root package name */
        public final int f15702a;
        public final long b;
        public final Scheduler c;
        public volatile a<T> d;
        public a<T> e;
        public int f;
        public volatile boolean g;
        public Throwable h;

        /* loaded from: classes13.dex */
        public static final class a<T> extends AtomicReference<a<T>> {
            private static final long serialVersionUID = 3713592843205853725L;
            public final long timestamp;
            public final T value;

            public a(T t, long j) {
                this.value = t;
                this.timestamp = j;
            }
        }

        public c(int i, long j, Scheduler scheduler) {
            this.f15702a = i;
            a<T> aVar = new a<>(null, 0L);
            this.e = aVar;
            this.d = aVar;
            this.b = j;
            this.c = scheduler;
        }

        /* JADX WARN: Code restructure failed: missing block: B:30:0x0060, code lost:
            if (r12 != 0) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0066, code lost:
            if (r2.isUnsubscribed() == false) goto L32;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0068, code lost:
            r18.node = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x006a, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x006b, code lost:
            r3 = r17.g;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0071, code lost:
            if (r7.get() != null) goto L35;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x0073, code lost:
            r13 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0074, code lost:
            if (r3 == false) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0076, code lost:
            if (r13 == false) goto L47;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0078, code lost:
            r18.node = null;
            r1 = r17.h;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x007c, code lost:
            if (r1 == null) goto L42;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x007e, code lost:
            r2.onError(r1);
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x0082, code lost:
            r2.onCompleted();
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0085, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x0088, code lost:
            if (r10 == 0) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x0091, code lost:
            if (r5 == Long.MAX_VALUE) goto L52;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x0093, code lost:
            rx.internal.operators.BackpressureUtils.produced(r18.requested, r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x0098, code lost:
            r18.node = r7;
            r4 = r18.addAndGet(-r4);
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:?, code lost:
            return;
         */
        @Override // rx.subjects.ReplaySubject.a
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(rx.subjects.ReplaySubject.b<T> r18) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                int r2 = r18.getAndIncrement()
                if (r2 == 0) goto Lb
                return
            Lb:
                rx.Subscriber<? super T> r2 = r1.actual
                r4 = 1
            Le:
                java.util.concurrent.atomic.AtomicLong r5 = r1.requested
                long r5 = r5.get()
                java.lang.Object r7 = r1.node
                rx.subjects.ReplaySubject$c$a r7 = (rx.subjects.ReplaySubject.c.a) r7
                r8 = 0
                if (r7 != 0) goto L20
                rx.subjects.ReplaySubject$c$a r7 = r17.c()
            L20:
                r10 = r8
            L21:
                int r12 = (r10 > r5 ? 1 : (r10 == r5 ? 0 : -1))
                r13 = 0
                r14 = 0
                if (r12 == 0) goto L60
                boolean r15 = r2.isUnsubscribed()
                if (r15 == 0) goto L30
                r1.node = r14
                return
            L30:
                boolean r15 = r0.g
                java.lang.Object r16 = r7.get()
                r3 = r16
                rx.subjects.ReplaySubject$c$a r3 = (rx.subjects.ReplaySubject.c.a) r3
                if (r3 != 0) goto L3f
                r16 = 1
                goto L41
            L3f:
                r16 = r13
            L41:
                if (r15 == 0) goto L53
                if (r16 == 0) goto L53
                r1.node = r14
                java.lang.Throwable r1 = r0.h
                if (r1 == 0) goto L4f
                r2.onError(r1)
                goto L52
            L4f:
                r2.onCompleted()
            L52:
                return
            L53:
                if (r16 == 0) goto L56
                goto L60
            L56:
                T r7 = r3.value
                r2.onNext(r7)
                r12 = 1
                long r10 = r10 + r12
                r7 = r3
                goto L21
            L60:
                if (r12 != 0) goto L86
                boolean r3 = r2.isUnsubscribed()
                if (r3 == 0) goto L6b
                r1.node = r14
                return
            L6b:
                boolean r3 = r0.g
                java.lang.Object r12 = r7.get()
                if (r12 != 0) goto L74
                r13 = 1
            L74:
                if (r3 == 0) goto L86
                if (r13 == 0) goto L86
                r1.node = r14
                java.lang.Throwable r1 = r0.h
                if (r1 == 0) goto L82
                r2.onError(r1)
                goto L85
            L82:
                r2.onCompleted()
            L85:
                return
            L86:
                int r3 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
                if (r3 == 0) goto L98
                r8 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r3 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
                if (r3 == 0) goto L98
                java.util.concurrent.atomic.AtomicLong r3 = r1.requested
                rx.internal.operators.BackpressureUtils.produced(r3, r10)
            L98:
                r1.node = r7
                int r3 = -r4
                int r4 = r1.addAndGet(r3)
                if (r4 != 0) goto Le
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.subjects.ReplaySubject.c.a(rx.subjects.ReplaySubject$b):void");
        }

        public void b() {
            long now = this.c.now() - this.b;
            a<T> aVar = this.d;
            a<T> aVar2 = aVar;
            while (true) {
                a<T> aVar3 = aVar2.get();
                if (aVar3 == null || aVar3.timestamp > now) {
                    break;
                }
                aVar2 = aVar3;
            }
            if (aVar != aVar2) {
                this.d = aVar2;
            }
        }

        public a<T> c() {
            long now = this.c.now() - this.b;
            a<T> aVar = this.d;
            while (true) {
                a<T> aVar2 = aVar.get();
                if (aVar2 == null || aVar2.timestamp > now) {
                    break;
                }
                aVar = aVar2;
            }
            return aVar;
        }

        @Override // rx.subjects.ReplaySubject.a
        public void complete() {
            b();
            this.g = true;
        }

        @Override // rx.subjects.ReplaySubject.a
        public void error(Throwable th) {
            b();
            this.h = th;
            this.g = true;
        }

        @Override // rx.subjects.ReplaySubject.a
        public boolean isEmpty() {
            return c().get() == null;
        }

        @Override // rx.subjects.ReplaySubject.a
        public T last() {
            a<T> c = c();
            while (true) {
                a<T> aVar = c.get();
                if (aVar == null) {
                    return c.value;
                }
                c = aVar;
            }
        }

        @Override // rx.subjects.ReplaySubject.a
        public void next(T t) {
            a<T> aVar;
            long now = this.c.now();
            a<T> aVar2 = new a<>(t, now);
            this.e.set(aVar2);
            this.e = aVar2;
            long j = now - this.b;
            int i = this.f;
            a<T> aVar3 = this.d;
            if (i == this.f15702a) {
                aVar = aVar3.get();
            } else {
                i++;
                aVar = aVar3;
            }
            while (true) {
                a<T> aVar4 = aVar.get();
                if (aVar4 == null || aVar4.timestamp > j) {
                    break;
                }
                i--;
                aVar = aVar4;
            }
            this.f = i;
            if (aVar != aVar3) {
                this.d = aVar;
            }
        }

        @Override // rx.subjects.ReplaySubject.a
        public int size() {
            a<T> aVar = c().get();
            int i = 0;
            while (aVar != null && i != Integer.MAX_VALUE) {
                aVar = aVar.get();
                i++;
            }
            return i;
        }

        @Override // rx.subjects.ReplaySubject.a
        public T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList();
            for (a<T> aVar = c().get(); aVar != null; aVar = aVar.get()) {
                arrayList.add(aVar.value);
            }
            return (T[]) arrayList.toArray(tArr);
        }

        @Override // rx.subjects.ReplaySubject.a
        public Throwable error() {
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public static final class f<T> implements a<T> {

        /* renamed from: a  reason: collision with root package name */
        public final int f15704a;
        public volatile int b;
        public final Object[] c;
        public Object[] d;
        public int e;
        public volatile boolean f;
        public Throwable g;

        public f(int i) {
            this.f15704a = i;
            Object[] objArr = new Object[i + 1];
            this.c = objArr;
            this.d = objArr;
        }

        @Override // rx.subjects.ReplaySubject.a
        public void a(b<T> bVar) {
            int i;
            if (bVar.getAndIncrement() != 0) {
                return;
            }
            Subscriber<? super T> subscriber = bVar.actual;
            int i2 = this.f15704a;
            int i3 = 1;
            do {
                long j = bVar.requested.get();
                Object[] objArr = (Object[]) bVar.node;
                if (objArr == null) {
                    objArr = this.c;
                }
                int i4 = bVar.tailIndex;
                int i5 = bVar.index;
                long j2 = 0;
                while (true) {
                    i = (j2 > j ? 1 : (j2 == j ? 0 : -1));
                    if (i == 0) {
                        break;
                    } else if (subscriber.isUnsubscribed()) {
                        bVar.node = null;
                        return;
                    } else {
                        boolean z = this.f;
                        boolean z2 = i5 == this.b;
                        if (z && z2) {
                            bVar.node = null;
                            Throwable th = this.g;
                            if (th != null) {
                                subscriber.onError(th);
                                return;
                            } else {
                                subscriber.onCompleted();
                                return;
                            }
                        } else if (z2) {
                            break;
                        } else {
                            if (i4 == i2) {
                                objArr = (Object[]) objArr[i4];
                                i4 = 0;
                            }
                            subscriber.onNext(objArr[i4]);
                            j2++;
                            i4++;
                            i5++;
                        }
                    }
                }
                if (i == 0) {
                    if (subscriber.isUnsubscribed()) {
                        bVar.node = null;
                        return;
                    }
                    boolean z3 = this.f;
                    boolean z4 = i5 == this.b;
                    if (z3 && z4) {
                        bVar.node = null;
                        Throwable th2 = this.g;
                        if (th2 != null) {
                            subscriber.onError(th2);
                            return;
                        } else {
                            subscriber.onCompleted();
                            return;
                        }
                    }
                }
                if (j2 != 0 && j != Long.MAX_VALUE) {
                    BackpressureUtils.produced(bVar.requested, j2);
                }
                bVar.index = i5;
                bVar.tailIndex = i4;
                bVar.node = objArr;
                i3 = bVar.addAndGet(-i3);
            } while (i3 != 0);
        }

        @Override // rx.subjects.ReplaySubject.a
        public void complete() {
            this.f = true;
        }

        @Override // rx.subjects.ReplaySubject.a
        public void error(Throwable th) {
            if (this.f) {
                RxJavaHooks.onError(th);
                return;
            }
            this.g = th;
            this.f = true;
        }

        @Override // rx.subjects.ReplaySubject.a
        public boolean isEmpty() {
            return this.b == 0;
        }

        @Override // rx.subjects.ReplaySubject.a
        public T last() {
            int i = this.b;
            if (i == 0) {
                return null;
            }
            Object[] objArr = this.c;
            int i2 = this.f15704a;
            while (i >= i2) {
                objArr = objArr[i2];
                i -= i2;
            }
            return (T) objArr[i - 1];
        }

        @Override // rx.subjects.ReplaySubject.a
        public void next(T t) {
            if (this.f) {
                return;
            }
            int i = this.e;
            Object[] objArr = this.d;
            if (i == objArr.length - 1) {
                Object[] objArr2 = new Object[objArr.length];
                objArr2[0] = t;
                this.e = 1;
                objArr[i] = objArr2;
                this.d = objArr2;
            } else {
                objArr[i] = t;
                this.e = i + 1;
            }
            this.b++;
        }

        @Override // rx.subjects.ReplaySubject.a
        public int size() {
            return this.b;
        }

        @Override // rx.subjects.ReplaySubject.a
        public T[] toArray(T[] tArr) {
            int i = this.b;
            if (tArr.length < i) {
                tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), i));
            }
            Object[] objArr = this.c;
            int i2 = this.f15704a;
            int i3 = 0;
            while (true) {
                int i4 = i3 + i2;
                if (i4 >= i) {
                    break;
                }
                System.arraycopy(objArr, 0, tArr, i3, i2);
                objArr = objArr[i2];
                i3 = i4;
            }
            System.arraycopy(objArr, 0, tArr, i3, i - i3);
            if (tArr.length > i) {
                tArr[i] = null;
            }
            return tArr;
        }

        @Override // rx.subjects.ReplaySubject.a
        public Throwable error() {
            return this.g;
        }
    }
}
