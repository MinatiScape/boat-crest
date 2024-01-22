package rx.internal.operators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;
/* loaded from: classes13.dex */
public final class OperatorBufferWithTime<T> implements Observable.Operator<List<T>, T> {
    public final long h;
    public final long i;
    public final TimeUnit j;
    public final int k;
    public final Scheduler l;

    /* loaded from: classes13.dex */
    public final class a extends Subscriber<T> {
        public final Subscriber<? super List<T>> l;
        public final Scheduler.Worker m;
        public List<T> n = new ArrayList();
        public boolean o;

        /* renamed from: rx.internal.operators.OperatorBufferWithTime$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0946a implements Action0 {
            public C0946a() {
            }

            @Override // rx.functions.Action0
            public void call() {
                a.this.b();
            }
        }

        public a(Subscriber<? super List<T>> subscriber, Scheduler.Worker worker) {
            this.l = subscriber;
            this.m = worker;
        }

        public void b() {
            synchronized (this) {
                if (this.o) {
                    return;
                }
                List<T> list = this.n;
                this.n = new ArrayList();
                try {
                    this.l.onNext(list);
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }

        public void c() {
            Scheduler.Worker worker = this.m;
            C0946a c0946a = new C0946a();
            OperatorBufferWithTime operatorBufferWithTime = OperatorBufferWithTime.this;
            long j = operatorBufferWithTime.h;
            worker.schedulePeriodically(c0946a, j, j, operatorBufferWithTime.j);
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                this.m.unsubscribe();
                synchronized (this) {
                    if (this.o) {
                        return;
                    }
                    this.o = true;
                    List<T> list = this.n;
                    this.n = null;
                    this.l.onNext(list);
                    this.l.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.l);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                if (this.o) {
                    return;
                }
                this.o = true;
                this.n = null;
                this.l.onError(th);
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            List<T> list;
            synchronized (this) {
                if (this.o) {
                    return;
                }
                this.n.add(t);
                if (this.n.size() == OperatorBufferWithTime.this.k) {
                    list = this.n;
                    this.n = new ArrayList();
                } else {
                    list = null;
                }
                if (list != null) {
                    this.l.onNext(list);
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public final class b extends Subscriber<T> {
        public final Subscriber<? super List<T>> l;
        public final Scheduler.Worker m;
        public final List<List<T>> n = new LinkedList();
        public boolean o;

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public a() {
            }

            @Override // rx.functions.Action0
            public void call() {
                b.this.d();
            }
        }

        /* renamed from: rx.internal.operators.OperatorBufferWithTime$b$b  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0947b implements Action0 {
            public final /* synthetic */ List h;

            public C0947b(List list) {
                this.h = list;
            }

            @Override // rx.functions.Action0
            public void call() {
                b.this.b(this.h);
            }
        }

        public b(Subscriber<? super List<T>> subscriber, Scheduler.Worker worker) {
            this.l = subscriber;
            this.m = worker;
        }

        public void b(List<T> list) {
            boolean z;
            synchronized (this) {
                if (this.o) {
                    return;
                }
                Iterator<List<T>> it = this.n.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (it.next() == list) {
                        it.remove();
                        z = true;
                        break;
                    }
                }
                if (z) {
                    try {
                        this.l.onNext(list);
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, this);
                    }
                }
            }
        }

        public void c() {
            Scheduler.Worker worker = this.m;
            a aVar = new a();
            OperatorBufferWithTime operatorBufferWithTime = OperatorBufferWithTime.this;
            long j = operatorBufferWithTime.i;
            worker.schedulePeriodically(aVar, j, j, operatorBufferWithTime.j);
        }

        public void d() {
            ArrayList arrayList = new ArrayList();
            synchronized (this) {
                if (this.o) {
                    return;
                }
                this.n.add(arrayList);
                Scheduler.Worker worker = this.m;
                C0947b c0947b = new C0947b(arrayList);
                OperatorBufferWithTime operatorBufferWithTime = OperatorBufferWithTime.this;
                worker.schedule(c0947b, operatorBufferWithTime.h, operatorBufferWithTime.j);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                synchronized (this) {
                    if (this.o) {
                        return;
                    }
                    this.o = true;
                    LinkedList<List> linkedList = new LinkedList(this.n);
                    this.n.clear();
                    for (List list : linkedList) {
                        this.l.onNext(list);
                    }
                    this.l.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.l);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            synchronized (this) {
                if (this.o) {
                    return;
                }
                this.o = true;
                this.n.clear();
                this.l.onError(th);
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            synchronized (this) {
                if (this.o) {
                    return;
                }
                Iterator<List<T>> it = this.n.iterator();
                LinkedList<List> linkedList = null;
                while (it.hasNext()) {
                    List<T> next = it.next();
                    next.add(t);
                    if (next.size() == OperatorBufferWithTime.this.k) {
                        it.remove();
                        if (linkedList == null) {
                            linkedList = new LinkedList();
                        }
                        linkedList.add(next);
                    }
                }
                if (linkedList != null) {
                    for (List list : linkedList) {
                        this.l.onNext(list);
                    }
                }
            }
        }
    }

    public OperatorBufferWithTime(long j, long j2, TimeUnit timeUnit, int i, Scheduler scheduler) {
        this.h = j;
        this.i = j2;
        this.j = timeUnit;
        this.k = i;
        this.l = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super List<T>> subscriber) {
        Scheduler.Worker createWorker = this.l.createWorker();
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        if (this.h == this.i) {
            a aVar = new a(serializedSubscriber, createWorker);
            aVar.add(createWorker);
            subscriber.add(aVar);
            aVar.c();
            return aVar;
        }
        b bVar = new b(serializedSubscriber, createWorker);
        bVar.add(createWorker);
        subscriber.add(bVar);
        bVar.d();
        bVar.c();
        return bVar;
    }
}
