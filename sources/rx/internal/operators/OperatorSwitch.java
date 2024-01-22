package rx.internal.operators;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.functions.Action0;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OperatorSwitch<T> implements Observable.Operator<T, Observable<? extends T>> {
    public final boolean h;

    /* loaded from: classes13.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorSwitch<Object> f15673a = new OperatorSwitch<>(false);
    }

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorSwitch<Object> f15674a = new OperatorSwitch<>(true);
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends Subscriber<T> {
        public final long l;
        public final d<T> m;

        public c(long j, d<T> dVar) {
            this.l = j;
            this.m = dVar;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.m.e(this.l);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.h(th, this.l);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.m.g(t, this);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.m.j(producer, this.l);
        }
    }

    /* loaded from: classes13.dex */
    public static final class d<T> extends Subscriber<Observable<? extends T>> {
        public static final Throwable x = new Throwable("Terminal error");
        public final Subscriber<? super T> l;
        public final boolean n;
        public boolean q;
        public boolean r;
        public long s;
        public Producer t;
        public volatile boolean u;
        public Throwable v;
        public boolean w;
        public final SerialSubscription m = new SerialSubscription();
        public final AtomicLong o = new AtomicLong();
        public final SpscLinkedArrayQueue<Object> p = new SpscLinkedArrayQueue<>(RxRingBuffer.SIZE);

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public a() {
            }

            @Override // rx.functions.Action0
            public void call() {
                d.this.d();
            }
        }

        /* loaded from: classes13.dex */
        public class b implements Producer {
            public b() {
            }

            @Override // rx.Producer
            public void request(long j) {
                int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i > 0) {
                    d.this.c(j);
                } else if (i >= 0) {
                } else {
                    throw new IllegalArgumentException("n >= 0 expected but it was " + j);
                }
            }
        }

        public d(Subscriber<? super T> subscriber, boolean z) {
            this.l = subscriber;
            this.n = z;
        }

        public boolean b(boolean z, boolean z2, Throwable th, SpscLinkedArrayQueue<Object> spscLinkedArrayQueue, Subscriber<? super T> subscriber, boolean z3) {
            if (this.n) {
                if (z && !z2 && z3) {
                    if (th != null) {
                        subscriber.onError(th);
                    } else {
                        subscriber.onCompleted();
                    }
                    return true;
                }
                return false;
            } else if (th != null) {
                spscLinkedArrayQueue.clear();
                subscriber.onError(th);
                return true;
            } else if (z && !z2 && z3) {
                subscriber.onCompleted();
                return true;
            } else {
                return false;
            }
        }

        public void c(long j) {
            Producer producer;
            synchronized (this) {
                producer = this.t;
                this.s = BackpressureUtils.addCap(this.s, j);
            }
            if (producer != null) {
                producer.request(j);
            }
            f();
        }

        public void d() {
            synchronized (this) {
                this.t = null;
            }
        }

        public void e(long j) {
            synchronized (this) {
                if (this.o.get() != j) {
                    return;
                }
                this.w = false;
                this.t = null;
                f();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0074, code lost:
            if (r18 != 0) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x007a, code lost:
            if (r11.isUnsubscribed() == false) goto L38;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x007c, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x008d, code lost:
            if (b(r20.u, r0, r14, r9, r11, r9.isEmpty()) == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x008f, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x0090, code lost:
            monitor-enter(r20);
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0091, code lost:
            r0 = r20.s;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x009a, code lost:
            if (r0 == Long.MAX_VALUE) goto L49;
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x009c, code lost:
            r0 = r0 - r16;
            r20.s = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x00a0, code lost:
            r12 = r0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00a4, code lost:
            if (r20.r != false) goto L51;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00a6, code lost:
            r20.q = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:46:0x00a8, code lost:
            monitor-exit(r20);
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00a9, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:48:0x00aa, code lost:
            r20.r = false;
            r15 = r20.u;
            r0 = r20.w;
            r14 = r20.v;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00b2, code lost:
            if (r14 == null) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00b4, code lost:
            r1 = rx.internal.operators.OperatorSwitch.d.x;
         */
        /* JADX WARN: Code restructure failed: missing block: B:51:0x00b6, code lost:
            if (r14 == r1) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00ba, code lost:
            if (r20.n != false) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00bc, code lost:
            r20.v = r1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x00be, code lost:
            monitor-exit(r20);
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void f() {
            /*
                Method dump skipped, instructions count: 199
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorSwitch.d.f():void");
        }

        public void g(T t, c<T> cVar) {
            synchronized (this) {
                if (this.o.get() != cVar.l) {
                    return;
                }
                this.p.offer(cVar, NotificationLite.next(t));
                f();
            }
        }

        public void h(Throwable th, long j) {
            boolean z;
            synchronized (this) {
                if (this.o.get() == j) {
                    z = m(th);
                    this.w = false;
                    this.t = null;
                } else {
                    z = true;
                }
            }
            if (z) {
                f();
            } else {
                l(th);
            }
        }

        public void i() {
            this.l.add(this.m);
            this.l.add(Subscriptions.create(new a()));
            this.l.setProducer(new b());
        }

        public void j(Producer producer, long j) {
            synchronized (this) {
                if (this.o.get() != j) {
                    return;
                }
                long j2 = this.s;
                this.t = producer;
                producer.request(j2);
            }
        }

        @Override // rx.Observer
        /* renamed from: k */
        public void onNext(Observable<? extends T> observable) {
            c cVar;
            long incrementAndGet = this.o.incrementAndGet();
            Subscription subscription = this.m.get();
            if (subscription != null) {
                subscription.unsubscribe();
            }
            synchronized (this) {
                cVar = new c(incrementAndGet, this);
                this.w = true;
                this.t = null;
            }
            this.m.set(cVar);
            observable.unsafeSubscribe(cVar);
        }

        public void l(Throwable th) {
            RxJavaHooks.onError(th);
        }

        public boolean m(Throwable th) {
            Throwable th2 = this.v;
            if (th2 == x) {
                return false;
            }
            if (th2 == null) {
                this.v = th;
            } else if (th2 instanceof CompositeException) {
                ArrayList arrayList = new ArrayList(((CompositeException) th2).getExceptions());
                arrayList.add(th);
                this.v = new CompositeException(arrayList);
            } else {
                this.v = new CompositeException(th2, th);
            }
            return true;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.u = true;
            f();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            boolean m;
            synchronized (this) {
                m = m(th);
            }
            if (m) {
                this.u = true;
                f();
                return;
            }
            l(th);
        }
    }

    public OperatorSwitch(boolean z) {
        this.h = z;
    }

    public static <T> OperatorSwitch<T> instance(boolean z) {
        if (z) {
            return (OperatorSwitch<T>) b.f15674a;
        }
        return (OperatorSwitch<T>) a.f15673a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super Observable<? extends T>> call(Subscriber<? super T> subscriber) {
        d dVar = new d(subscriber, this.h);
        subscriber.add(dVar);
        dVar.i();
        return dVar;
    }
}
