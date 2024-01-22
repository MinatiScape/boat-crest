package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.internal.util.atomic.SpscLinkedArrayQueue;
import rx.subjects.Subject;
import rx.subjects.UnicastSubject;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OperatorWindowWithSize<T> implements Observable.Operator<Observable<T>, T> {
    public final int h;
    public final int i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> implements Action0 {
        public final Subscriber<? super Observable<T>> l;
        public final int m;
        public final AtomicInteger n = new AtomicInteger(1);
        public final Subscription o;
        public int p;
        public Subject<T, T> q;

        /* renamed from: rx.internal.operators.OperatorWindowWithSize$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0959a implements Producer {
            public C0959a() {
            }

            @Override // rx.Producer
            public void request(long j) {
                int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                } else if (i != 0) {
                    a.this.request(BackpressureUtils.multiplyCap(a.this.m, j));
                }
            }
        }

        public a(Subscriber<? super Observable<T>> subscriber, int i) {
            this.l = subscriber;
            this.m = i;
            Subscription create = Subscriptions.create(this);
            this.o = create;
            add(create);
            request(0L);
        }

        public Producer c() {
            return new C0959a();
        }

        @Override // rx.functions.Action0
        public void call() {
            if (this.n.decrementAndGet() == 0) {
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            Subject<T, T> subject = this.q;
            if (subject != null) {
                this.q = null;
                subject.onCompleted();
            }
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            Subject<T, T> subject = this.q;
            if (subject != null) {
                this.q = null;
                subject.onError(th);
            }
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            int i = this.p;
            UnicastSubject unicastSubject = this.q;
            if (i == 0) {
                this.n.getAndIncrement();
                unicastSubject = UnicastSubject.create(this.m, this);
                this.q = unicastSubject;
                this.l.onNext(unicastSubject);
            }
            int i2 = i + 1;
            unicastSubject.onNext(t);
            if (i2 == this.m) {
                this.p = 0;
                this.q = null;
                unicastSubject.onCompleted();
                return;
            }
            this.p = i2;
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> implements Action0 {
        public final Subscriber<? super Observable<T>> l;
        public final int m;
        public final int n;
        public final Subscription p;
        public final Queue<Subject<T, T>> t;
        public Throwable u;
        public volatile boolean v;
        public int w;
        public int x;
        public final AtomicInteger o = new AtomicInteger(1);
        public final ArrayDeque<Subject<T, T>> q = new ArrayDeque<>();
        public final AtomicInteger s = new AtomicInteger();
        public final AtomicLong r = new AtomicLong();

        /* loaded from: classes13.dex */
        public final class a extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = 4625807964358024108L;

            public a() {
            }

            @Override // rx.Producer
            public void request(long j) {
                int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                } else if (i != 0) {
                    b bVar = b.this;
                    if (!get() && compareAndSet(false, true)) {
                        bVar.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(bVar.n, j - 1), bVar.m));
                    } else {
                        b.this.request(BackpressureUtils.multiplyCap(bVar.n, j));
                    }
                    BackpressureUtils.getAndAddRequest(bVar.r, j);
                    bVar.f();
                }
            }
        }

        public b(Subscriber<? super Observable<T>> subscriber, int i, int i2) {
            this.l = subscriber;
            this.m = i;
            this.n = i2;
            Subscription create = Subscriptions.create(this);
            this.p = create;
            add(create);
            request(0L);
            this.t = new SpscLinkedArrayQueue((i + (i2 - 1)) / i2);
        }

        @Override // rx.functions.Action0
        public void call() {
            if (this.o.decrementAndGet() == 0) {
                unsubscribe();
            }
        }

        public boolean d(boolean z, boolean z2, Subscriber<? super Subject<T, T>> subscriber, Queue<Subject<T, T>> queue) {
            if (subscriber.isUnsubscribed()) {
                queue.clear();
                return true;
            } else if (z) {
                Throwable th = this.u;
                if (th != null) {
                    queue.clear();
                    subscriber.onError(th);
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

        public Producer e() {
            return new a();
        }

        /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
            if (r11 != 0) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
            if (d(r15.v, r2.isEmpty(), r1, r2) == false) goto L22;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0048, code lost:
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:
            if (r9 == 0) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:26:0x0054, code lost:
            if (r5 == Long.MAX_VALUE) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0056, code lost:
            r15.r.addAndGet(-r9);
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x005c, code lost:
            r4 = r0.addAndGet(-r4);
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void f() {
            /*
                r15 = this;
                java.util.concurrent.atomic.AtomicInteger r0 = r15.s
                int r1 = r0.getAndIncrement()
                if (r1 == 0) goto L9
                return
            L9:
                rx.Subscriber<? super rx.Observable<T>> r1 = r15.l
                java.util.Queue<rx.subjects.Subject<T, T>> r2 = r15.t
                r3 = 1
                r4 = r3
            Lf:
                java.util.concurrent.atomic.AtomicLong r5 = r15.r
                long r5 = r5.get()
                r7 = 0
                r9 = r7
            L18:
                int r11 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
                if (r11 == 0) goto L3a
                boolean r12 = r15.v
                java.lang.Object r13 = r2.poll()
                rx.subjects.Subject r13 = (rx.subjects.Subject) r13
                if (r13 != 0) goto L28
                r14 = r3
                goto L29
            L28:
                r14 = 0
            L29:
                boolean r12 = r15.d(r12, r14, r1, r2)
                if (r12 == 0) goto L30
                return
            L30:
                if (r14 == 0) goto L33
                goto L3a
            L33:
                r1.onNext(r13)
                r11 = 1
                long r9 = r9 + r11
                goto L18
            L3a:
                if (r11 != 0) goto L49
                boolean r11 = r15.v
                boolean r12 = r2.isEmpty()
                boolean r11 = r15.d(r11, r12, r1, r2)
                if (r11 == 0) goto L49
                return
            L49:
                int r7 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r7 == 0) goto L5c
                r7 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                int r5 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r5 == 0) goto L5c
                java.util.concurrent.atomic.AtomicLong r5 = r15.r
                long r6 = -r9
                r5.addAndGet(r6)
            L5c:
                int r4 = -r4
                int r4 = r0.addAndGet(r4)
                if (r4 != 0) goto Lf
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorWindowWithSize.b.f():void");
        }

        @Override // rx.Observer
        public void onCompleted() {
            Iterator<Subject<T, T>> it = this.q.iterator();
            while (it.hasNext()) {
                it.next().onCompleted();
            }
            this.q.clear();
            this.v = true;
            f();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            Iterator<Subject<T, T>> it = this.q.iterator();
            while (it.hasNext()) {
                it.next().onError(th);
            }
            this.q.clear();
            this.u = th;
            this.v = true;
            f();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            int i = this.w;
            ArrayDeque<Subject<T, T>> arrayDeque = this.q;
            if (i == 0 && !this.l.isUnsubscribed()) {
                this.o.getAndIncrement();
                UnicastSubject create = UnicastSubject.create(16, this);
                arrayDeque.offer(create);
                this.t.offer(create);
                f();
            }
            Iterator<Subject<T, T>> it = this.q.iterator();
            while (it.hasNext()) {
                it.next().onNext(t);
            }
            int i2 = this.x + 1;
            if (i2 == this.m) {
                this.x = i2 - this.n;
                Subject<T, T> poll = arrayDeque.poll();
                if (poll != null) {
                    poll.onCompleted();
                }
            } else {
                this.x = i2;
            }
            int i3 = i + 1;
            if (i3 == this.n) {
                this.w = 0;
            } else {
                this.w = i3;
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends Subscriber<T> implements Action0 {
        public final Subscriber<? super Observable<T>> l;
        public final int m;
        public final int n;
        public final AtomicInteger o = new AtomicInteger(1);
        public final Subscription p;
        public int q;
        public Subject<T, T> r;

        /* loaded from: classes13.dex */
        public final class a extends AtomicBoolean implements Producer {
            private static final long serialVersionUID = 4625807964358024108L;

            public a() {
            }

            @Override // rx.Producer
            public void request(long j) {
                int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
                if (i < 0) {
                    throw new IllegalArgumentException("n >= 0 required but it was " + j);
                } else if (i != 0) {
                    c cVar = c.this;
                    if (get() || !compareAndSet(false, true)) {
                        cVar.request(BackpressureUtils.multiplyCap(j, cVar.n));
                    } else {
                        cVar.request(BackpressureUtils.addCap(BackpressureUtils.multiplyCap(j, cVar.m), BackpressureUtils.multiplyCap(cVar.n - cVar.m, j - 1)));
                    }
                }
            }
        }

        public c(Subscriber<? super Observable<T>> subscriber, int i, int i2) {
            this.l = subscriber;
            this.m = i;
            this.n = i2;
            Subscription create = Subscriptions.create(this);
            this.p = create;
            add(create);
            request(0L);
        }

        @Override // rx.functions.Action0
        public void call() {
            if (this.o.decrementAndGet() == 0) {
                unsubscribe();
            }
        }

        public Producer d() {
            return new a();
        }

        @Override // rx.Observer
        public void onCompleted() {
            Subject<T, T> subject = this.r;
            if (subject != null) {
                this.r = null;
                subject.onCompleted();
            }
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            Subject<T, T> subject = this.r;
            if (subject != null) {
                this.r = null;
                subject.onError(th);
            }
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            int i = this.q;
            UnicastSubject unicastSubject = this.r;
            if (i == 0) {
                this.o.getAndIncrement();
                unicastSubject = UnicastSubject.create(this.m, this);
                this.r = unicastSubject;
                this.l.onNext(unicastSubject);
            }
            int i2 = i + 1;
            if (unicastSubject != null) {
                unicastSubject.onNext(t);
            }
            if (i2 == this.m) {
                this.q = i2;
                this.r = null;
                unicastSubject.onCompleted();
            } else if (i2 == this.n) {
                this.q = 0;
            } else {
                this.q = i2;
            }
        }
    }

    public OperatorWindowWithSize(int i, int i2) {
        this.h = i;
        this.i = i2;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<T>> subscriber) {
        int i = this.i;
        int i2 = this.h;
        if (i == i2) {
            a aVar = new a(subscriber, i2);
            subscriber.add(aVar.o);
            subscriber.setProducer(aVar.c());
            return aVar;
        } else if (i > i2) {
            c cVar = new c(subscriber, i2, i);
            subscriber.add(cVar.p);
            subscriber.setProducer(cVar.d());
            return cVar;
        } else {
            b bVar = new b(subscriber, i2, i);
            subscriber.add(bVar.p);
            subscriber.setProducer(bVar.e());
            return bVar;
        }
    }
}
