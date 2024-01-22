package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func2;
import rx.internal.producers.ProducerArbiter;
import rx.schedulers.Schedulers;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public final class OperatorRetryWithPredicate<T> implements Observable.Operator<T, Observable<T>> {
    public final Func2<Integer, Throwable, Boolean> h;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<Observable<T>> {
        public final Subscriber<? super T> l;
        public final Func2<Integer, Throwable, Boolean> m;
        public final Scheduler.Worker n;
        public final SerialSubscription o;
        public final ProducerArbiter p;
        public final AtomicInteger q = new AtomicInteger();

        /* renamed from: rx.internal.operators.OperatorRetryWithPredicate$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0952a implements Action0 {
            public final /* synthetic */ Observable h;

            /* renamed from: rx.internal.operators.OperatorRetryWithPredicate$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public class C0953a extends Subscriber<T> {
                public boolean l;
                public final /* synthetic */ Action0 m;

                public C0953a(Action0 action0) {
                    this.m = action0;
                }

                @Override // rx.Observer
                public void onCompleted() {
                    if (this.l) {
                        return;
                    }
                    this.l = true;
                    a.this.l.onCompleted();
                }

                @Override // rx.Observer
                public void onError(Throwable th) {
                    if (this.l) {
                        return;
                    }
                    this.l = true;
                    a aVar = a.this;
                    if (aVar.m.call(Integer.valueOf(aVar.q.get()), th).booleanValue() && !a.this.n.isUnsubscribed()) {
                        a.this.n.schedule(this.m);
                    } else {
                        a.this.l.onError(th);
                    }
                }

                @Override // rx.Observer
                public void onNext(T t) {
                    if (this.l) {
                        return;
                    }
                    a.this.l.onNext(t);
                    a.this.p.produced(1L);
                }

                @Override // rx.Subscriber, rx.observers.AssertableSubscriber
                public void setProducer(Producer producer) {
                    a.this.p.setProducer(producer);
                }
            }

            public C0952a(Observable observable) {
                this.h = observable;
            }

            @Override // rx.functions.Action0
            public void call() {
                a.this.q.incrementAndGet();
                C0953a c0953a = new C0953a(this);
                a.this.o.set(c0953a);
                this.h.unsafeSubscribe(c0953a);
            }
        }

        public a(Subscriber<? super T> subscriber, Func2<Integer, Throwable, Boolean> func2, Scheduler.Worker worker, SerialSubscription serialSubscription, ProducerArbiter producerArbiter) {
            this.l = subscriber;
            this.m = func2;
            this.n = worker;
            this.o = serialSubscription;
            this.p = producerArbiter;
        }

        @Override // rx.Observer
        /* renamed from: b */
        public void onNext(Observable<T> observable) {
            this.n.schedule(new C0952a(observable));
        }

        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.onError(th);
        }
    }

    public OperatorRetryWithPredicate(Func2<Integer, Throwable, Boolean> func2) {
        this.h = func2;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super Observable<T>> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = Schedulers.trampoline().createWorker();
        subscriber.add(createWorker);
        SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        ProducerArbiter producerArbiter = new ProducerArbiter();
        subscriber.setProducer(producerArbiter);
        return new a(subscriber, this.h, createWorker, serialSubscription, producerArbiter);
    }
}
