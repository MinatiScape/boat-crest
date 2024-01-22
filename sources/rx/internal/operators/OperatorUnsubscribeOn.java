package rx.internal.operators;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public class OperatorUnsubscribeOn<T> implements Observable.Operator<T, T> {
    public final Scheduler h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final /* synthetic */ Subscriber l;

        public a(OperatorUnsubscribeOn operatorUnsubscribeOn, Subscriber subscriber) {
            this.l = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(t);
        }
    }

    /* loaded from: classes13.dex */
    public class b implements Action0 {
        public final /* synthetic */ Subscriber h;

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public final /* synthetic */ Scheduler.Worker h;

            public a(Scheduler.Worker worker) {
                this.h = worker;
            }

            @Override // rx.functions.Action0
            public void call() {
                b.this.h.unsubscribe();
                this.h.unsubscribe();
            }
        }

        public b(Subscriber subscriber) {
            this.h = subscriber;
        }

        @Override // rx.functions.Action0
        public void call() {
            Scheduler.Worker createWorker = OperatorUnsubscribeOn.this.h.createWorker();
            createWorker.schedule(new a(createWorker));
        }
    }

    public OperatorUnsubscribeOn(Scheduler scheduler) {
        this.h = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        a aVar = new a(this, subscriber);
        subscriber.add(Subscriptions.create(new b(aVar)));
        return aVar;
    }
}
