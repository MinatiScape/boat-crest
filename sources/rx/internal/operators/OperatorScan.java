package rx.internal.operators;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func0;
import rx.functions.Func2;
import rx.internal.util.atomic.SpscLinkedAtomicQueue;
import rx.internal.util.unsafe.SpscLinkedQueue;
import rx.internal.util.unsafe.UnsafeAccess;
/* loaded from: classes13.dex */
public final class OperatorScan<R, T> implements Observable.Operator<R, T> {
    public static final Object j = new Object();
    public final Func0<R> h;
    public final Func2<R, ? super T, R> i;

    /* loaded from: classes13.dex */
    public class a implements Func0<R> {
        public final /* synthetic */ Object h;

        public a(Object obj) {
            this.h = obj;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public R call() {
            return (R) this.h;
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<T> {
        public boolean l;
        public R m;
        public final /* synthetic */ Subscriber n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.n = subscriber2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.n.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.n.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            R r;
            if (!this.l) {
                this.l = true;
                r = t;
            } else {
                try {
                    r = OperatorScan.this.i.call(this.m, t);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this.n, t);
                    return;
                }
            }
            this.m = r;
            this.n.onNext(r);
        }
    }

    /* loaded from: classes13.dex */
    public class c extends Subscriber<T> {
        public R l;
        public final /* synthetic */ Object m;
        public final /* synthetic */ d n;

        /* JADX WARN: Multi-variable type inference failed */
        public c(Object obj, d dVar) {
            this.m = obj;
            this.n = dVar;
            this.l = obj;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.n.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.n.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                R call = OperatorScan.this.i.call(this.l, t);
                this.l = call;
                this.n.onNext(call);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this, t);
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.n.setProducer(producer);
        }
    }

    /* loaded from: classes13.dex */
    public static final class d<R> implements Producer, Observer<R> {
        public final Subscriber<? super R> h;
        public final Queue<Object> i;
        public boolean j;
        public boolean k;
        public long l;
        public final AtomicLong m;
        public volatile Producer n;
        public volatile boolean o;
        public Throwable p;

        public d(R r, Subscriber<? super R> subscriber) {
            Queue<Object> spscLinkedAtomicQueue;
            this.h = subscriber;
            if (UnsafeAccess.isUnsafeAvailable()) {
                spscLinkedAtomicQueue = new SpscLinkedQueue<>();
            } else {
                spscLinkedAtomicQueue = new SpscLinkedAtomicQueue<>();
            }
            this.i = spscLinkedAtomicQueue;
            spscLinkedAtomicQueue.offer(NotificationLite.next(r));
            this.m = new AtomicLong();
        }

        public boolean a(boolean z, boolean z2, Subscriber<? super R> subscriber) {
            if (subscriber.isUnsubscribed()) {
                return true;
            }
            if (z) {
                Throwable th = this.p;
                if (th != null) {
                    subscriber.onError(th);
                    return true;
                } else if (z2) {
                    subscriber.onCompleted();
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        public void b() {
            synchronized (this) {
                if (this.j) {
                    this.k = true;
                    return;
                }
                this.j = true;
                c();
            }
        }

        public void c() {
            Subscriber<? super R> subscriber = this.h;
            Queue<Object> queue = this.i;
            AtomicLong atomicLong = this.m;
            long j = atomicLong.get();
            while (!a(this.o, queue.isEmpty(), subscriber)) {
                long j2 = 0;
                while (j2 != j) {
                    boolean z = this.o;
                    Object poll = queue.poll();
                    boolean z2 = poll == null;
                    if (a(z, z2, subscriber)) {
                        return;
                    }
                    if (z2) {
                        break;
                    }
                    Object obj = (Object) NotificationLite.getValue(poll);
                    try {
                        subscriber.onNext(obj);
                        j2++;
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, subscriber, obj);
                        return;
                    }
                }
                if (j2 != 0 && j != Long.MAX_VALUE) {
                    j = BackpressureUtils.produced(atomicLong, j2);
                }
                synchronized (this) {
                    if (!this.k) {
                        this.j = false;
                        return;
                    }
                    this.k = false;
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.o = true;
            b();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.p = th;
            this.o = true;
            b();
        }

        @Override // rx.Observer
        public void onNext(R r) {
            this.i.offer(NotificationLite.next(r));
            b();
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalArgumentException("n >= required but it was " + j);
            } else if (i != 0) {
                BackpressureUtils.getAndAddRequest(this.m, j);
                Producer producer = this.n;
                if (producer == null) {
                    synchronized (this.m) {
                        producer = this.n;
                        if (producer == null) {
                            this.l = BackpressureUtils.addCap(this.l, j);
                        }
                    }
                }
                if (producer != null) {
                    producer.request(j);
                }
                b();
            }
        }

        public void setProducer(Producer producer) {
            long j;
            Objects.requireNonNull(producer);
            synchronized (this.m) {
                if (this.n == null) {
                    j = this.l;
                    if (j != Long.MAX_VALUE) {
                        j--;
                    }
                    this.l = 0L;
                    this.n = producer;
                } else {
                    throw new IllegalStateException("Can't set more than one Producer!");
                }
            }
            if (j > 0) {
                producer.request(j);
            }
            b();
        }
    }

    public OperatorScan(R r, Func2<R, ? super T, R> func2) {
        this((Func0) new a(r), (Func2) func2);
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorScan(Func0<R> func0, Func2<R, ? super T, R> func2) {
        this.h = func0;
        this.i = func2;
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        R call = this.h.call();
        if (call == j) {
            return new b(subscriber, subscriber);
        }
        d dVar = new d(call, subscriber);
        c cVar = new c(call, dVar);
        subscriber.add(cVar);
        subscriber.setProducer(dVar);
        return cVar;
    }

    public OperatorScan(Func2<R, ? super T, R> func2) {
        this(j, func2);
    }
}
