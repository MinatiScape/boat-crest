package rx.internal.operators;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.MissingBackpressureException;
import rx.exceptions.OnErrorThrowable;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.atomic.SpscExactAtomicArrayQueue;
import rx.internal.util.atomic.SpscUnboundedAtomicArrayQueue;
import rx.internal.util.unsafe.Pow2;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class OperatorMerge<T> implements Observable.Operator<T, Observable<? extends T>> {
    public final boolean h;
    public final int i;

    /* loaded from: classes13.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorMerge<Object> f15665a = new OperatorMerge<>(true, Integer.MAX_VALUE);
    }

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorMerge<Object> f15666a = new OperatorMerge<>(false, Integer.MAX_VALUE);
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends Subscriber<T> {
        public static final int q = RxRingBuffer.SIZE / 4;
        public final e<T> l;
        public final long m;
        public volatile boolean n;
        public volatile RxRingBuffer o;
        public int p;

        public c(e<T> eVar, long j) {
            this.l = eVar;
            this.m = j;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.n = true;
            this.l.d();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.n = true;
            this.l.j().offer(th);
            this.l.d();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.q(this, t);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            int i = RxRingBuffer.SIZE;
            this.p = i;
            request(i);
        }

        public void requestMore(long j) {
            int i = this.p - ((int) j);
            if (i > q) {
                this.p = i;
                return;
            }
            int i2 = RxRingBuffer.SIZE;
            this.p = i2;
            int i3 = i2 - i;
            if (i3 > 0) {
                request(i3);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class d<T> extends AtomicLong implements Producer {
        private static final long serialVersionUID = -1214379189873595503L;
        public final e<T> subscriber;

        public d(e<T> eVar) {
            this.subscriber = eVar;
        }

        public long produced(int i) {
            return addAndGet(-i);
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i <= 0) {
                if (i < 0) {
                    throw new IllegalArgumentException("n >= 0 required");
                }
            } else if (get() == Long.MAX_VALUE) {
            } else {
                BackpressureUtils.getAndAddRequest(this, j);
                this.subscriber.d();
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class e<T> extends Subscriber<Observable<? extends T>> {
        public static final c<?>[] C = new c[0];
        public final int A;
        public int B;
        public final Subscriber<? super T> l;
        public final boolean m;
        public final int n;
        public d<T> o;
        public volatile Queue<Object> p;
        public volatile CompositeSubscription q;
        public volatile ConcurrentLinkedQueue<Throwable> r;
        public volatile boolean s;
        public boolean t;
        public boolean u;
        public final Object v = new Object();
        public volatile c<?>[] w = C;
        public long x;
        public long y;
        public int z;

        public e(Subscriber<? super T> subscriber, boolean z, int i) {
            this.l = subscriber;
            this.m = z;
            this.n = i;
            if (i == Integer.MAX_VALUE) {
                this.A = Integer.MAX_VALUE;
                request(Long.MAX_VALUE);
                return;
            }
            this.A = Math.max(1, i >> 1);
            request(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void b(c<T> cVar) {
            i().add(cVar);
            synchronized (this.v) {
                c<?>[] cVarArr = this.w;
                int length = cVarArr.length;
                c<?>[] cVarArr2 = new c[length + 1];
                System.arraycopy(cVarArr, 0, cVarArr2, 0, length);
                cVarArr2[length] = cVar;
                this.w = cVarArr2;
            }
        }

        public boolean c() {
            if (this.l.isUnsubscribed()) {
                return true;
            }
            ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.r;
            if (this.m || concurrentLinkedQueue == null || concurrentLinkedQueue.isEmpty()) {
                return false;
            }
            try {
                o();
                return true;
            } finally {
                unsubscribe();
            }
        }

        public void d() {
            synchronized (this) {
                if (this.t) {
                    this.u = true;
                    return;
                }
                this.t = true;
                f();
            }
        }

        public void e() {
            int i = this.B + 1;
            if (i == this.A) {
                this.B = 0;
                requestMore(i);
                return;
            }
            this.B = i;
        }

        public void f() {
            boolean z;
            long j;
            int i;
            boolean z2;
            int i2;
            try {
                Subscriber<? super T> subscriber = this.l;
                while (!c()) {
                    Queue<Object> queue = this.p;
                    long j2 = this.o.get();
                    boolean z3 = j2 == Long.MAX_VALUE;
                    if (queue != null) {
                        int i3 = 0;
                        while (true) {
                            j = j2;
                            i = i3;
                            int i4 = 0;
                            Object obj = null;
                            while (true) {
                                if (j <= 0) {
                                    break;
                                }
                                Object poll = queue.poll();
                                if (c()) {
                                    return;
                                }
                                if (poll == null) {
                                    obj = poll;
                                    break;
                                }
                                subscriber.onNext((Object) NotificationLite.getValue(poll));
                                i++;
                                i4++;
                                j--;
                                obj = poll;
                            }
                            if (i4 > 0) {
                                j = z3 ? Long.MAX_VALUE : this.o.produced(i4);
                            }
                            if (j == 0 || obj == null) {
                                break;
                            }
                            i3 = i;
                            j2 = j;
                        }
                    } else {
                        j = j2;
                        i = 0;
                    }
                    boolean z4 = this.s;
                    Queue<Object> queue2 = this.p;
                    c<?>[] cVarArr = this.w;
                    int length = cVarArr.length;
                    if (z4 && ((queue2 == null || queue2.isEmpty()) && length == 0)) {
                        ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.r;
                        if (concurrentLinkedQueue != null && !concurrentLinkedQueue.isEmpty()) {
                            o();
                            return;
                        }
                        subscriber.onCompleted();
                        return;
                    }
                    if (length > 0) {
                        long j3 = this.y;
                        int i5 = this.z;
                        if (length <= i5 || cVarArr[i5].m != j3) {
                            if (length <= i5) {
                                i5 = 0;
                            }
                            for (int i6 = 0; i6 < length && cVarArr[i5].m != j3; i6++) {
                                i5++;
                                if (i5 == length) {
                                    i5 = 0;
                                }
                            }
                            this.z = i5;
                            this.y = cVarArr[i5].m;
                        }
                        z2 = false;
                        for (int i7 = 0; i7 < length; i7++) {
                            if (c()) {
                                return;
                            }
                            c<?> cVar = cVarArr[i5];
                            Object obj2 = null;
                            do {
                                int i8 = 0;
                                while (j > 0) {
                                    if (c()) {
                                        return;
                                    }
                                    RxRingBuffer rxRingBuffer = cVar.o;
                                    if (rxRingBuffer == null || (obj2 = rxRingBuffer.poll()) == null) {
                                        break;
                                    }
                                    try {
                                        subscriber.onNext((Object) NotificationLite.getValue(obj2));
                                        j--;
                                        i8++;
                                    }
                                }
                                if (i8 > 0) {
                                    j = !z3 ? this.o.produced(i8) : Long.MAX_VALUE;
                                    cVar.requestMore(i8);
                                }
                                i2 = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                                if (i2 == 0) {
                                    break;
                                }
                            } while (obj2 != null);
                            boolean z5 = cVar.n;
                            RxRingBuffer rxRingBuffer2 = cVar.o;
                            if (z5 && (rxRingBuffer2 == null || rxRingBuffer2.isEmpty())) {
                                n(cVar);
                                if (c()) {
                                    return;
                                }
                                i++;
                                z2 = true;
                            }
                            if (i2 == 0) {
                                break;
                            }
                            i5++;
                            if (i5 == length) {
                                i5 = 0;
                            }
                        }
                        this.z = i5;
                        this.y = cVarArr[i5].m;
                    } else {
                        z2 = false;
                    }
                    if (i > 0) {
                        request(i);
                    }
                    if (!z2) {
                        synchronized (this) {
                            try {
                                if (!this.u) {
                                    try {
                                        this.t = false;
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
                                this.u = false;
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
                                    this.t = false;
                                }
                            }
                            throw th;
                        }
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                z = false;
            }
        }

        public void g(T t, long j) {
            try {
                this.l.onNext(t);
                if (j != Long.MAX_VALUE) {
                    this.o.produced(1);
                }
                int i = this.B + 1;
                if (i == this.A) {
                    this.B = 0;
                    requestMore(i);
                } else {
                    this.B = i;
                }
                synchronized (this) {
                    try {
                        if (!this.u) {
                            this.t = false;
                            return;
                        }
                        this.u = false;
                        f();
                    }
                }
            }
        }

        public void h(c<T> cVar, T t, long j) {
            try {
                this.l.onNext(t);
                if (j != Long.MAX_VALUE) {
                    this.o.produced(1);
                }
                cVar.requestMore(1L);
                synchronized (this) {
                    try {
                        if (!this.u) {
                            this.t = false;
                            return;
                        }
                        this.u = false;
                        f();
                    }
                }
            }
        }

        public CompositeSubscription i() {
            CompositeSubscription compositeSubscription;
            CompositeSubscription compositeSubscription2 = this.q;
            if (compositeSubscription2 == null) {
                boolean z = false;
                synchronized (this) {
                    compositeSubscription = this.q;
                    if (compositeSubscription == null) {
                        CompositeSubscription compositeSubscription3 = new CompositeSubscription();
                        this.q = compositeSubscription3;
                        compositeSubscription = compositeSubscription3;
                        z = true;
                    }
                }
                if (z) {
                    add(compositeSubscription);
                }
                return compositeSubscription;
            }
            return compositeSubscription2;
        }

        public Queue<Throwable> j() {
            ConcurrentLinkedQueue<Throwable> concurrentLinkedQueue = this.r;
            if (concurrentLinkedQueue == null) {
                synchronized (this) {
                    concurrentLinkedQueue = this.r;
                    if (concurrentLinkedQueue == null) {
                        concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
                        this.r = concurrentLinkedQueue;
                    }
                }
            }
            return concurrentLinkedQueue;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.Observer
        /* renamed from: k */
        public void onNext(Observable<? extends T> observable) {
            if (observable == null) {
                return;
            }
            if (observable == Observable.empty()) {
                e();
            } else if (observable instanceof ScalarSynchronousObservable) {
                p(((ScalarSynchronousObservable) observable).get());
            } else {
                long j = this.x;
                this.x = 1 + j;
                c cVar = new c(this, j);
                b(cVar);
                observable.unsafeSubscribe(cVar);
                d();
            }
        }

        public void l(T t) {
            Queue<Object> spscExactAtomicArrayQueue;
            Queue<Object> queue = this.p;
            if (queue == null) {
                int i = this.n;
                if (i == Integer.MAX_VALUE) {
                    queue = new SpscUnboundedAtomicArrayQueue<>(RxRingBuffer.SIZE);
                } else {
                    if (Pow2.isPowerOfTwo(i)) {
                        if (UnsafeAccess.isUnsafeAvailable()) {
                            spscExactAtomicArrayQueue = new SpscArrayQueue<>(i);
                        } else {
                            spscExactAtomicArrayQueue = new SpscAtomicArrayQueue<>(i);
                        }
                    } else {
                        spscExactAtomicArrayQueue = new SpscExactAtomicArrayQueue<>(i);
                    }
                    queue = spscExactAtomicArrayQueue;
                }
                this.p = queue;
            }
            if (queue.offer(NotificationLite.next(t))) {
                return;
            }
            unsubscribe();
            onError(OnErrorThrowable.addValueAsLastCause(new MissingBackpressureException(), t));
        }

        public void m(c<T> cVar, T t) {
            RxRingBuffer rxRingBuffer = cVar.o;
            if (rxRingBuffer == null) {
                rxRingBuffer = RxRingBuffer.getSpscInstance();
                cVar.add(rxRingBuffer);
                cVar.o = rxRingBuffer;
            }
            try {
                rxRingBuffer.onNext(NotificationLite.next(t));
            } catch (IllegalStateException e) {
                if (cVar.isUnsubscribed()) {
                    return;
                }
                cVar.unsubscribe();
                cVar.onError(e);
            } catch (MissingBackpressureException e2) {
                cVar.unsubscribe();
                cVar.onError(e2);
            }
        }

        public void n(c<T> cVar) {
            RxRingBuffer rxRingBuffer = cVar.o;
            if (rxRingBuffer != null) {
                rxRingBuffer.release();
            }
            this.q.remove(cVar);
            synchronized (this.v) {
                c<?>[] cVarArr = this.w;
                int length = cVarArr.length;
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (cVar.equals(cVarArr[i2])) {
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
                    this.w = C;
                    return;
                }
                c<?>[] cVarArr2 = new c[length - 1];
                System.arraycopy(cVarArr, 0, cVarArr2, 0, i);
                System.arraycopy(cVarArr, i + 1, cVarArr2, i, (length - i) - 1);
                this.w = cVarArr2;
            }
        }

        public final void o() {
            ArrayList arrayList = new ArrayList(this.r);
            if (arrayList.size() == 1) {
                this.l.onError((Throwable) arrayList.get(0));
            } else {
                this.l.onError(new CompositeException(arrayList));
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.s = true;
            d();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            j().offer(th);
            this.s = true;
            d();
        }

        public void p(T t) {
            long j = this.o.get();
            boolean z = false;
            if (j != 0) {
                synchronized (this) {
                    j = this.o.get();
                    if (!this.t && j != 0) {
                        this.t = true;
                        z = true;
                    }
                }
            }
            if (z) {
                Queue<Object> queue = this.p;
                if (queue != null && !queue.isEmpty()) {
                    l(t);
                    f();
                    return;
                }
                g(t, j);
                return;
            }
            l(t);
            d();
        }

        public void q(c<T> cVar, T t) {
            long j = this.o.get();
            boolean z = false;
            if (j != 0) {
                synchronized (this) {
                    j = this.o.get();
                    if (!this.t && j != 0) {
                        this.t = true;
                        z = true;
                    }
                }
            }
            if (z) {
                RxRingBuffer rxRingBuffer = cVar.o;
                if (rxRingBuffer != null && !rxRingBuffer.isEmpty()) {
                    m(cVar, t);
                    f();
                    return;
                }
                h(cVar, t, j);
                return;
            }
            m(cVar, t);
            d();
        }

        public void requestMore(long j) {
            request(j);
        }
    }

    public OperatorMerge(boolean z, int i) {
        this.h = z;
        this.i = i;
    }

    public static <T> OperatorMerge<T> instance(boolean z) {
        if (z) {
            return (OperatorMerge<T>) a.f15665a;
        }
        return (OperatorMerge<T>) b.f15666a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<Observable<? extends T>> call(Subscriber<? super T> subscriber) {
        e eVar = new e(subscriber, this.h, this.i);
        d<T> dVar = new d<>(eVar);
        eVar.o = dVar;
        subscriber.add(eVar);
        subscriber.setProducer(dVar);
        return eVar;
    }

    public static <T> OperatorMerge<T> instance(boolean z, int i) {
        if (i > 0) {
            if (i == Integer.MAX_VALUE) {
                return instance(z);
            }
            return new OperatorMerge<>(z, i);
        }
        throw new IllegalArgumentException("maxConcurrent > 0 required but it was " + i);
    }
}
