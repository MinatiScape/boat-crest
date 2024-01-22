package rx.internal.operators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.util.atomic.SpscAtomicArrayQueue;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.internal.util.unsafe.UnsafeAccess;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OperatorEagerConcatMap<T, R> implements Observable.Operator<R, T> {
    public final Func1<? super T, ? extends Observable<? extends R>> h;
    public final int i;
    public final int j;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> {
        public final c<?, T> l;
        public final Queue<Object> m;
        public volatile boolean n;
        public Throwable o;

        public a(c<?, T> cVar, int i) {
            Queue<Object> spscAtomicArrayQueue;
            this.l = cVar;
            if (UnsafeAccess.isUnsafeAvailable()) {
                spscAtomicArrayQueue = new SpscArrayQueue<>(i);
            } else {
                spscAtomicArrayQueue = new SpscAtomicArrayQueue<>(i);
            }
            this.m = spscAtomicArrayQueue;
            request(i);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.n = true;
            this.l.c();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.o = th;
            this.n = true;
            this.l.c();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.m.offer(NotificationLite.next(t));
            this.l.c();
        }

        public void requestMore(long j) {
            request(j);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b extends AtomicLong implements Producer {
        private static final long serialVersionUID = -657299606803478389L;
        public final c<?, ?> parent;

        public b(c<?, ?> cVar) {
            this.parent = cVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalStateException("n >= 0 required but it was " + j);
            } else if (i > 0) {
                BackpressureUtils.getAndAddRequest(this, j);
                this.parent.c();
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T, R> extends Subscriber<T> {
        public final Func1<? super T, ? extends Observable<? extends R>> l;
        public final int m;
        public final Subscriber<? super R> n;
        public volatile boolean p;
        public Throwable q;
        public volatile boolean r;
        public b t;
        public final Queue<a<R>> o = new LinkedList();
        public final AtomicInteger s = new AtomicInteger();

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public a() {
            }

            @Override // rx.functions.Action0
            public void call() {
                c.this.r = true;
                if (c.this.s.getAndIncrement() == 0) {
                    c.this.b();
                }
            }
        }

        public c(Func1<? super T, ? extends Observable<? extends R>> func1, int i, int i2, Subscriber<? super R> subscriber) {
            this.l = func1;
            this.m = i;
            this.n = subscriber;
            request(i2 == Integer.MAX_VALUE ? Long.MAX_VALUE : i2);
        }

        public void b() {
            ArrayList<Subscription> arrayList;
            synchronized (this.o) {
                arrayList = new ArrayList(this.o);
                this.o.clear();
            }
            for (Subscription subscription : arrayList) {
                subscription.unsubscribe();
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:52:0x0087, code lost:
            if (r12 == 0) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x0090, code lost:
            if (r8 == Long.MAX_VALUE) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x0092, code lost:
            rx.internal.operators.BackpressureUtils.produced(r0, r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0095, code lost:
            if (r6 != false) goto L50;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x0097, code lost:
            r7.requestMore(r12);
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x009a, code lost:
            if (r6 == false) goto L51;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x009c, code lost:
            r4 = r17;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void c() {
            /*
                r18 = this;
                r1 = r18
                java.util.concurrent.atomic.AtomicInteger r0 = r1.s
                int r0 = r0.getAndIncrement()
                if (r0 == 0) goto Lb
                return
            Lb:
                rx.internal.operators.OperatorEagerConcatMap$b r0 = r1.t
                rx.Subscriber<? super R> r2 = r1.n
                r4 = 1
            L10:
                boolean r5 = r1.r
                if (r5 == 0) goto L18
                r18.b()
                return
            L18:
                boolean r5 = r1.p
                java.util.Queue<rx.internal.operators.OperatorEagerConcatMap$a<R>> r6 = r1.o
                monitor-enter(r6)
                java.util.Queue<rx.internal.operators.OperatorEagerConcatMap$a<R>> r7 = r1.o     // Catch: java.lang.Throwable -> Lc1
                java.lang.Object r7 = r7.peek()     // Catch: java.lang.Throwable -> Lc1
                rx.internal.operators.OperatorEagerConcatMap$a r7 = (rx.internal.operators.OperatorEagerConcatMap.a) r7     // Catch: java.lang.Throwable -> Lc1
                monitor-exit(r6)     // Catch: java.lang.Throwable -> Lc1
                r6 = 0
                if (r7 != 0) goto L2b
                r8 = 1
                goto L2c
            L2b:
                r8 = r6
            L2c:
                if (r5 == 0) goto L3f
                java.lang.Throwable r5 = r1.q
                if (r5 == 0) goto L39
                r18.b()
                r2.onError(r5)
                return
            L39:
                if (r8 == 0) goto L3f
                r2.onCompleted()
                return
            L3f:
                if (r8 != 0) goto Lb3
                long r8 = r0.get()
                java.util.Queue<java.lang.Object> r5 = r7.m
                r10 = 0
                r12 = r10
            L4a:
                boolean r14 = r7.n
                java.lang.Object r15 = r5.peek()
                r17 = r4
                if (r15 != 0) goto L57
                r16 = 1
                goto L59
            L57:
                r16 = r6
            L59:
                r3 = 1
                if (r14 == 0) goto L7e
                java.lang.Throwable r14 = r7.o
                if (r14 == 0) goto L68
                r18.b()
                r2.onError(r14)
                return
            L68:
                if (r16 == 0) goto L7e
                java.util.Queue<rx.internal.operators.OperatorEagerConcatMap$a<R>> r14 = r1.o
                monitor-enter(r14)
                java.util.Queue<rx.internal.operators.OperatorEagerConcatMap$a<R>> r5 = r1.o     // Catch: java.lang.Throwable -> L7b
                r5.poll()     // Catch: java.lang.Throwable -> L7b
                monitor-exit(r14)     // Catch: java.lang.Throwable -> L7b
                r7.unsubscribe()
                r1.request(r3)
                r6 = 1
                goto L85
            L7b:
                r0 = move-exception
                monitor-exit(r14)     // Catch: java.lang.Throwable -> L7b
                throw r0
            L7e:
                if (r16 == 0) goto L81
                goto L85
            L81:
                int r14 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1))
                if (r14 != 0) goto La0
            L85:
                int r3 = (r12 > r10 ? 1 : (r12 == r10 ? 0 : -1))
                if (r3 == 0) goto L9a
                r3 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r3 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
                if (r3 == 0) goto L95
                rx.internal.operators.BackpressureUtils.produced(r0, r12)
            L95:
                if (r6 != 0) goto L9a
                r7.requestMore(r12)
            L9a:
                if (r6 == 0) goto Lb5
                r4 = r17
                goto L10
            La0:
                r5.poll()
                java.lang.Object r14 = rx.internal.operators.NotificationLite.getValue(r15)     // Catch: java.lang.Throwable -> Lae
                r2.onNext(r14)     // Catch: java.lang.Throwable -> Lae
                long r12 = r12 + r3
                r4 = r17
                goto L4a
            Lae:
                r0 = move-exception
                rx.exceptions.Exceptions.throwOrReport(r0, r2, r15)
                return
            Lb3:
                r17 = r4
            Lb5:
                java.util.concurrent.atomic.AtomicInteger r3 = r1.s
                r4 = r17
                int r4 = -r4
                int r4 = r3.addAndGet(r4)
                if (r4 != 0) goto L10
                return
            Lc1:
                r0 = move-exception
                monitor-exit(r6)     // Catch: java.lang.Throwable -> Lc1
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorEagerConcatMap.c.c():void");
        }

        public void d() {
            this.t = new b(this);
            add(Subscriptions.create(new a()));
            this.n.add(this);
            this.n.setProducer(this.t);
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.p = true;
            c();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.q = th;
            this.p = true;
            c();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Observable<? extends R> call = this.l.call(t);
                if (this.r) {
                    return;
                }
                a<R> aVar = new a<>(this, this.m);
                synchronized (this.o) {
                    if (this.r) {
                        return;
                    }
                    this.o.add(aVar);
                    if (this.r) {
                        return;
                    }
                    call.unsafeSubscribe(aVar);
                    c();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.n, t);
            }
        }
    }

    public OperatorEagerConcatMap(Func1<? super T, ? extends Observable<? extends R>> func1, int i, int i2) {
        this.h = func1;
        this.i = i;
        this.j = i2;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        c cVar = new c(this.h, this.i, this.j, subscriber);
        cVar.d();
        return cVar;
    }
}
