package rx.internal.operators;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.SerializedObserver;
import rx.observers.SerializedSubscriber;
import rx.subjects.UnicastSubject;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OperatorWindowWithTime<T> implements Observable.Operator<Observable<T>, T> {
    public static final Object m = new Object();
    public final long h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;
    public final int l;

    /* loaded from: classes13.dex */
    public static final class a<T> {

        /* renamed from: a  reason: collision with root package name */
        public final Observer<T> f15677a;
        public final Observable<T> b;
        public int c;

        public a(Observer<T> observer, Observable<T> observable) {
            this.f15677a = new SerializedObserver(observer);
            this.b = observable;
        }
    }

    /* loaded from: classes13.dex */
    public final class b extends Subscriber<T> {
        public final Subscriber<? super Observable<T>> l;
        public final Scheduler.Worker m;
        public List<Object> o;
        public boolean p;
        public final Object n = new Object();
        public volatile d<T> q = d.c();

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public a(OperatorWindowWithTime operatorWindowWithTime) {
            }

            @Override // rx.functions.Action0
            public void call() {
                if (b.this.q.f15678a == null) {
                    b.this.unsubscribe();
                }
            }
        }

        /* renamed from: rx.internal.operators.OperatorWindowWithTime$b$b  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0960b implements Action0 {
            public C0960b() {
            }

            @Override // rx.functions.Action0
            public void call() {
                b.this.e();
            }
        }

        public b(Subscriber<? super Observable<T>> subscriber, Scheduler.Worker worker) {
            this.l = new SerializedSubscriber(subscriber);
            this.m = worker;
            subscriber.add(Subscriptions.create(new a(OperatorWindowWithTime.this)));
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x003d, code lost:
            return true;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public boolean b(java.util.List<java.lang.Object> r5) {
            /*
                r4 = this;
                r0 = 1
                if (r5 != 0) goto L4
                return r0
            L4:
                java.util.Iterator r5 = r5.iterator()
            L8:
                boolean r1 = r5.hasNext()
                if (r1 == 0) goto L3d
                java.lang.Object r1 = r5.next()
                java.lang.Object r2 = rx.internal.operators.OperatorWindowWithTime.m
                r3 = 0
                if (r1 != r2) goto L1e
                boolean r1 = r4.f()
                if (r1 != 0) goto L8
                return r3
            L1e:
                boolean r2 = rx.internal.operators.NotificationLite.isError(r1)
                if (r2 == 0) goto L2c
                java.lang.Throwable r5 = rx.internal.operators.NotificationLite.getError(r1)
                r4.d(r5)
                goto L3d
            L2c:
                boolean r2 = rx.internal.operators.NotificationLite.isCompleted(r1)
                if (r2 == 0) goto L36
                r4.complete()
                goto L3d
            L36:
                boolean r1 = r4.c(r1)
                if (r1 != 0) goto L8
                return r3
            L3d:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: rx.internal.operators.OperatorWindowWithTime.b.b(java.util.List):boolean");
        }

        public boolean c(T t) {
            d<T> d;
            d<T> dVar = this.q;
            if (dVar.f15678a == null) {
                if (!f()) {
                    return false;
                }
                dVar = this.q;
            }
            dVar.f15678a.onNext(t);
            if (dVar.c == OperatorWindowWithTime.this.l - 1) {
                dVar.f15678a.onCompleted();
                d = dVar.a();
            } else {
                d = dVar.d();
            }
            this.q = d;
            return true;
        }

        public void complete() {
            Observer<T> observer = this.q.f15678a;
            this.q = this.q.a();
            if (observer != null) {
                observer.onCompleted();
            }
            this.l.onCompleted();
            unsubscribe();
        }

        public void d(Throwable th) {
            Observer<T> observer = this.q.f15678a;
            this.q = this.q.a();
            if (observer != null) {
                observer.onError(th);
            }
            this.l.onError(th);
            unsubscribe();
        }

        public void e() {
            boolean z;
            List<Object> list;
            synchronized (this.n) {
                if (this.p) {
                    if (this.o == null) {
                        this.o = new ArrayList();
                    }
                    this.o.add(OperatorWindowWithTime.m);
                    return;
                }
                boolean z2 = true;
                this.p = true;
                try {
                    if (!f()) {
                        synchronized (this.n) {
                            this.p = false;
                        }
                        return;
                    }
                    do {
                        try {
                            synchronized (this.n) {
                                try {
                                    list = this.o;
                                    if (list == null) {
                                        this.p = false;
                                        return;
                                    }
                                    this.o = null;
                                } catch (Throwable th) {
                                    th = th;
                                    z2 = false;
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        z = z2;
                                        th = th2;
                                        if (!z) {
                                            synchronized (this.n) {
                                                this.p = false;
                                            }
                                        }
                                        throw th;
                                    }
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } while (b(list));
                    synchronized (this.n) {
                        this.p = false;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    z = false;
                }
            }
        }

        public boolean f() {
            Observer<T> observer = this.q.f15678a;
            if (observer != null) {
                observer.onCompleted();
            }
            if (this.l.isUnsubscribed()) {
                this.q = this.q.a();
                unsubscribe();
                return false;
            }
            UnicastSubject create = UnicastSubject.create();
            this.q = this.q.b(create, create);
            this.l.onNext(create);
            return true;
        }

        public void g() {
            Scheduler.Worker worker = this.m;
            C0960b c0960b = new C0960b();
            OperatorWindowWithTime operatorWindowWithTime = OperatorWindowWithTime.this;
            worker.schedulePeriodically(c0960b, 0L, operatorWindowWithTime.h, operatorWindowWithTime.j);
        }

        @Override // rx.Observer
        public void onCompleted() {
            synchronized (this.n) {
                if (this.p) {
                    if (this.o == null) {
                        this.o = new ArrayList();
                    }
                    this.o.add(NotificationLite.completed());
                    return;
                }
                List<Object> list = this.o;
                this.o = null;
                this.p = true;
                try {
                    b(list);
                    complete();
                } catch (Throwable th) {
                    d(th);
                }
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            synchronized (this.n) {
                if (this.p) {
                    this.o = Collections.singletonList(NotificationLite.error(th));
                    return;
                }
                this.o = null;
                this.p = true;
                d(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            List<Object> list;
            synchronized (this.n) {
                if (this.p) {
                    if (this.o == null) {
                        this.o = new ArrayList();
                    }
                    this.o.add(t);
                    return;
                }
                boolean z = true;
                this.p = true;
                try {
                    if (!c(t)) {
                        synchronized (this.n) {
                            this.p = false;
                        }
                        return;
                    }
                    do {
                        try {
                            synchronized (this.n) {
                                try {
                                    list = this.o;
                                    if (list == null) {
                                        this.p = false;
                                        return;
                                    }
                                    this.o = null;
                                } catch (Throwable th) {
                                    th = th;
                                    z = false;
                                    try {
                                        throw th;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        if (!z) {
                                            synchronized (this.n) {
                                                this.p = false;
                                            }
                                        }
                                        throw th;
                                    }
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                        }
                    } while (b(list));
                    synchronized (this.n) {
                        this.p = false;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    z = false;
                }
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    /* loaded from: classes13.dex */
    public final class c extends Subscriber<T> {
        public final Subscriber<? super Observable<T>> l;
        public final Scheduler.Worker m;
        public final Object n;
        public final List<a<T>> o;
        public boolean p;

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public a() {
            }

            @Override // rx.functions.Action0
            public void call() {
                c.this.d();
            }
        }

        /* loaded from: classes13.dex */
        public class b implements Action0 {
            public final /* synthetic */ a h;

            public b(a aVar) {
                this.h = aVar;
            }

            @Override // rx.functions.Action0
            public void call() {
                c.this.e(this.h);
            }
        }

        public c(Subscriber<? super Observable<T>> subscriber, Scheduler.Worker worker) {
            super(subscriber);
            this.l = subscriber;
            this.m = worker;
            this.n = new Object();
            this.o = new LinkedList();
        }

        public a<T> b() {
            UnicastSubject create = UnicastSubject.create();
            return new a<>(create, create);
        }

        public void c() {
            Scheduler.Worker worker = this.m;
            a aVar = new a();
            OperatorWindowWithTime operatorWindowWithTime = OperatorWindowWithTime.this;
            long j = operatorWindowWithTime.i;
            worker.schedulePeriodically(aVar, j, j, operatorWindowWithTime.j);
        }

        public void d() {
            a<T> b2 = b();
            synchronized (this.n) {
                if (this.p) {
                    return;
                }
                this.o.add(b2);
                try {
                    this.l.onNext(b2.b);
                    Scheduler.Worker worker = this.m;
                    b bVar = new b(b2);
                    OperatorWindowWithTime operatorWindowWithTime = OperatorWindowWithTime.this;
                    worker.schedule(bVar, operatorWindowWithTime.h, operatorWindowWithTime.j);
                } catch (Throwable th) {
                    onError(th);
                }
            }
        }

        public void e(a<T> aVar) {
            boolean z;
            synchronized (this.n) {
                if (this.p) {
                    return;
                }
                Iterator<a<T>> it = this.o.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (it.next() == aVar) {
                        z = true;
                        it.remove();
                        break;
                    }
                }
                if (z) {
                    aVar.f15677a.onCompleted();
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            synchronized (this.n) {
                if (this.p) {
                    return;
                }
                this.p = true;
                ArrayList<a> arrayList = new ArrayList(this.o);
                this.o.clear();
                for (a aVar : arrayList) {
                    aVar.f15677a.onCompleted();
                }
                this.l.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            synchronized (this.n) {
                if (this.p) {
                    return;
                }
                this.p = true;
                ArrayList<a> arrayList = new ArrayList(this.o);
                this.o.clear();
                for (a aVar : arrayList) {
                    aVar.f15677a.onError(th);
                }
                this.l.onError(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            synchronized (this.n) {
                if (this.p) {
                    return;
                }
                ArrayList<a> arrayList = new ArrayList(this.o);
                Iterator<a<T>> it = this.o.iterator();
                while (it.hasNext()) {
                    a<T> next = it.next();
                    int i = next.c + 1;
                    next.c = i;
                    if (i == OperatorWindowWithTime.this.l) {
                        it.remove();
                    }
                }
                for (a aVar : arrayList) {
                    aVar.f15677a.onNext(t);
                    if (aVar.c == OperatorWindowWithTime.this.l) {
                        aVar.f15677a.onCompleted();
                    }
                }
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    /* loaded from: classes13.dex */
    public static final class d<T> {
        public static final d<Object> d = new d<>(null, null, 0);

        /* renamed from: a  reason: collision with root package name */
        public final Observer<T> f15678a;
        public final Observable<T> b;
        public final int c;

        public d(Observer<T> observer, Observable<T> observable, int i) {
            this.f15678a = observer;
            this.b = observable;
            this.c = i;
        }

        public static <T> d<T> c() {
            return (d<T>) d;
        }

        public d<T> a() {
            return c();
        }

        public d<T> b(Observer<T> observer, Observable<T> observable) {
            return new d<>(observer, observable, 0);
        }

        public d<T> d() {
            return new d<>(this.f15678a, this.b, this.c + 1);
        }
    }

    public OperatorWindowWithTime(long j, long j2, TimeUnit timeUnit, int i, Scheduler scheduler) {
        this.h = j;
        this.i = j2;
        this.j = timeUnit;
        this.l = i;
        this.k = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super Observable<T>> subscriber) {
        Scheduler.Worker createWorker = this.k.createWorker();
        if (this.h == this.i) {
            b bVar = new b(subscriber, createWorker);
            bVar.add(createWorker);
            bVar.g();
            return bVar;
        }
        c cVar = new c(subscriber, createWorker);
        cVar.add(createWorker);
        cVar.d();
        cVar.c();
        return cVar;
    }
}
