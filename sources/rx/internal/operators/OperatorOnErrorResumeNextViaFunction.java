package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.ProducerArbiter;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public final class OperatorOnErrorResumeNextViaFunction<T> implements Observable.Operator<T, T> {
    public final Func1<? super Throwable, ? extends Observable<? extends T>> h;

    /* loaded from: classes13.dex */
    public static class a implements Func1<Throwable, Observable<? extends T>> {
        public final /* synthetic */ Func1 h;

        public a(Func1 func1) {
            this.h = func1;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable<? extends T> call(Throwable th) {
            return Observable.just(this.h.call(th));
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements Func1<Throwable, Observable<? extends T>> {
        public final /* synthetic */ Observable h;

        public b(Observable observable) {
            this.h = observable;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable<? extends T> call(Throwable th) {
            return this.h;
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements Func1<Throwable, Observable<? extends T>> {
        public final /* synthetic */ Observable h;

        public c(Observable observable) {
            this.h = observable;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable<? extends T> call(Throwable th) {
            if (th instanceof Exception) {
                return this.h;
            }
            return Observable.error(th);
        }
    }

    /* loaded from: classes13.dex */
    public class d extends Subscriber<T> {
        public boolean l;
        public long m;
        public final /* synthetic */ Subscriber n;
        public final /* synthetic */ ProducerArbiter o;
        public final /* synthetic */ SerialSubscription p;

        /* loaded from: classes13.dex */
        public class a extends Subscriber<T> {
            public a() {
            }

            @Override // rx.Observer
            public void onCompleted() {
                d.this.n.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                d.this.n.onError(th);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                d.this.n.onNext(t);
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                d.this.o.setProducer(producer);
            }
        }

        public d(Subscriber subscriber, ProducerArbiter producerArbiter, SerialSubscription serialSubscription) {
            this.n = subscriber;
            this.o = producerArbiter;
            this.p = serialSubscription;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.n.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.l) {
                Exceptions.throwIfFatal(th);
                RxJavaHooks.onError(th);
                return;
            }
            this.l = true;
            try {
                unsubscribe();
                a aVar = new a();
                this.p.set(aVar);
                long j = this.m;
                if (j != 0) {
                    this.o.produced(j);
                }
                OperatorOnErrorResumeNextViaFunction.this.h.call(th).unsafeSubscribe(aVar);
            } catch (Throwable th2) {
                Exceptions.throwOrReport(th2, this.n);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            this.m++;
            this.n.onNext(t);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.o.setProducer(producer);
        }
    }

    public OperatorOnErrorResumeNextViaFunction(Func1<? super Throwable, ? extends Observable<? extends T>> func1) {
        this.h = func1;
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withException(Observable<? extends T> observable) {
        return new OperatorOnErrorResumeNextViaFunction<>(new c(observable));
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withOther(Observable<? extends T> observable) {
        return new OperatorOnErrorResumeNextViaFunction<>(new b(observable));
    }

    public static <T> OperatorOnErrorResumeNextViaFunction<T> withSingle(Func1<? super Throwable, ? extends T> func1) {
        return new OperatorOnErrorResumeNextViaFunction<>(new a(func1));
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        ProducerArbiter producerArbiter = new ProducerArbiter();
        SerialSubscription serialSubscription = new SerialSubscription();
        d dVar = new d(subscriber, producerArbiter, serialSubscription);
        serialSubscription.set(dVar);
        subscriber.add(serialSubscription);
        subscriber.setProducer(producerArbiter);
        return dVar;
    }
}
