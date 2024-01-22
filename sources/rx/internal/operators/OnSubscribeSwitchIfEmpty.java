package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.internal.producers.ProducerArbiter;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public final class OnSubscribeSwitchIfEmpty<T> implements Observable.OnSubscribe<T> {
    public final Observable<? extends T> h;
    public final Observable<? extends T> i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> {
        public final ProducerArbiter l;
        public final Subscriber<? super T> m;

        public a(Subscriber<? super T> subscriber, ProducerArbiter producerArbiter) {
            this.m = subscriber;
            this.l = producerArbiter;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.m.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.m.onNext(t);
            this.l.produced(1L);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.l.setProducer(producer);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> {
        public final Subscriber<? super T> m;
        public final SerialSubscription n;
        public final ProducerArbiter o;
        public final Observable<? extends T> p;
        public volatile boolean r;
        public boolean l = true;
        public final AtomicInteger q = new AtomicInteger();

        public b(Subscriber<? super T> subscriber, SerialSubscription serialSubscription, ProducerArbiter producerArbiter, Observable<? extends T> observable) {
            this.m = subscriber;
            this.n = serialSubscription;
            this.o = producerArbiter;
            this.p = observable;
        }

        public void b(Observable<? extends T> observable) {
            if (this.q.getAndIncrement() == 0) {
                while (!this.m.isUnsubscribed()) {
                    if (!this.r) {
                        if (observable == null) {
                            a aVar = new a(this.m, this.o);
                            this.n.set(aVar);
                            this.r = true;
                            this.p.unsafeSubscribe(aVar);
                        } else {
                            this.r = true;
                            observable.unsafeSubscribe(this);
                            observable = null;
                        }
                    }
                    if (this.q.decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (!this.l) {
                this.m.onCompleted();
            } else if (this.m.isUnsubscribed()) {
            } else {
                this.r = false;
                b(null);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l = false;
            this.m.onNext(t);
            this.o.produced(1L);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.o.setProducer(producer);
        }
    }

    public OnSubscribeSwitchIfEmpty(Observable<? extends T> observable, Observable<? extends T> observable2) {
        this.h = observable;
        this.i = observable2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        SerialSubscription serialSubscription = new SerialSubscription();
        ProducerArbiter producerArbiter = new ProducerArbiter();
        b bVar = new b(subscriber, serialSubscription, producerArbiter, this.i);
        serialSubscription.set(bVar);
        subscriber.add(serialSubscription);
        subscriber.setProducer(producerArbiter);
        bVar.b(this.h);
    }
}
