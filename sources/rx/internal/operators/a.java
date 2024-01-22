package rx.internal.operators;

import java.util.concurrent.TimeoutException;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.internal.producers.ProducerArbiter;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public class a<T> implements Observable.Operator<T, T> {
    public final InterfaceC0966a<T> h;
    public final b<T> i;
    public final Observable<? extends T> j;
    public final Scheduler k;

    /* renamed from: rx.internal.operators.a$a  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public interface InterfaceC0966a<T> extends Func3<c<T>, Long, Scheduler.Worker, Subscription> {
    }

    /* loaded from: classes13.dex */
    public interface b<T> extends Func4<c<T>, Long, T, Scheduler.Worker, Subscription> {
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends Subscriber<T> {
        public final SerialSubscription l;
        public final SerializedSubscriber<T> m;
        public final b<T> n;
        public final Observable<? extends T> o;
        public final Scheduler.Worker p;
        public final ProducerArbiter q = new ProducerArbiter();
        public boolean r;
        public long s;

        /* renamed from: rx.internal.operators.a$c$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0967a extends Subscriber<T> {
            public C0967a() {
            }

            @Override // rx.Observer
            public void onCompleted() {
                c.this.m.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                c.this.m.onError(th);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                c.this.m.onNext(t);
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                c.this.q.setProducer(producer);
            }
        }

        public c(SerializedSubscriber<T> serializedSubscriber, b<T> bVar, SerialSubscription serialSubscription, Observable<? extends T> observable, Scheduler.Worker worker) {
            this.m = serializedSubscriber;
            this.n = bVar;
            this.l = serialSubscription;
            this.o = observable;
            this.p = worker;
        }

        public void b(long j) {
            boolean z;
            synchronized (this) {
                z = true;
                if (j != this.s || this.r) {
                    z = false;
                } else {
                    this.r = true;
                }
            }
            if (z) {
                if (this.o == null) {
                    this.m.onError(new TimeoutException());
                    return;
                }
                C0967a c0967a = new C0967a();
                this.o.unsafeSubscribe(c0967a);
                this.l.set(c0967a);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            boolean z;
            synchronized (this) {
                z = true;
                if (this.r) {
                    z = false;
                } else {
                    this.r = true;
                }
            }
            if (z) {
                this.l.unsubscribe();
                this.m.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            boolean z;
            synchronized (this) {
                z = true;
                if (this.r) {
                    z = false;
                } else {
                    this.r = true;
                }
            }
            if (z) {
                this.l.unsubscribe();
                this.m.onError(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long j;
            boolean z;
            synchronized (this) {
                if (!this.r) {
                    j = this.s + 1;
                    this.s = j;
                    z = true;
                } else {
                    j = this.s;
                    z = false;
                }
            }
            if (z) {
                this.m.onNext(t);
                this.l.set(this.n.call(this, Long.valueOf(j), t, this.p));
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.q.setProducer(producer);
        }
    }

    public a(InterfaceC0966a<T> interfaceC0966a, b<T> bVar, Observable<? extends T> observable, Scheduler scheduler) {
        this.h = interfaceC0966a;
        this.i = bVar;
        this.j = observable;
        this.k = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.k.createWorker();
        subscriber.add(createWorker);
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        SerialSubscription serialSubscription = new SerialSubscription();
        serializedSubscriber.add(serialSubscription);
        c cVar = new c(serializedSubscriber, this.i, serialSubscription, this.j, createWorker);
        serializedSubscriber.add(cVar);
        serializedSubscriber.setProducer(cVar.q);
        serialSubscription.set(this.h.call(cVar, 0L, createWorker));
        return cVar;
    }
}
