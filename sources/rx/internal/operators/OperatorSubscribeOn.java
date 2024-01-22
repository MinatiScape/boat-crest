package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public final class OperatorSubscribeOn<T> implements Observable.OnSubscribe<T> {
    public final Scheduler h;
    public final Observable<T> i;
    public final boolean j;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> implements Action0 {
        public final Subscriber<? super T> l;
        public final boolean m;
        public final Scheduler.Worker n;
        public Observable<T> o;
        public Thread p;

        /* renamed from: rx.internal.operators.OperatorSubscribeOn$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0954a implements Producer {
            public final /* synthetic */ Producer h;

            /* renamed from: rx.internal.operators.OperatorSubscribeOn$a$a$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public class C0955a implements Action0 {
                public final /* synthetic */ long h;

                public C0955a(long j) {
                    this.h = j;
                }

                @Override // rx.functions.Action0
                public void call() {
                    C0954a.this.h.request(this.h);
                }
            }

            public C0954a(Producer producer) {
                this.h = producer;
            }

            @Override // rx.Producer
            public void request(long j) {
                if (a.this.p != Thread.currentThread()) {
                    a aVar = a.this;
                    if (aVar.m) {
                        aVar.n.schedule(new C0955a(j));
                        return;
                    }
                }
                this.h.request(j);
            }
        }

        public a(Subscriber<? super T> subscriber, boolean z, Scheduler.Worker worker, Observable<T> observable) {
            this.l = subscriber;
            this.m = z;
            this.n = worker;
            this.o = observable;
        }

        @Override // rx.functions.Action0
        public void call() {
            Observable<T> observable = this.o;
            this.o = null;
            this.p = Thread.currentThread();
            observable.unsafeSubscribe(this);
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                this.l.onCompleted();
            } finally {
                this.n.unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            try {
                this.l.onError(th);
            } finally {
                this.n.unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(t);
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.l.setProducer(new C0954a(producer));
        }
    }

    public OperatorSubscribeOn(Observable<T> observable, Scheduler scheduler, boolean z) {
        this.h = scheduler;
        this.i = observable;
        this.j = z;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.h.createWorker();
        a aVar = new a(subscriber, this.j, createWorker, this.i);
        subscriber.add(aVar);
        subscriber.add(createWorker);
        createWorker.schedule(aVar);
    }
}
