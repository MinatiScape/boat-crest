package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.SerializedSubscriber;
/* loaded from: classes13.dex */
public final class OperatorTakeTimed<T> implements Observable.Operator<T, T> {
    public final long h;
    public final TimeUnit i;
    public final Scheduler j;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> implements Action0 {
        public final Subscriber<? super T> l;

        public a(Subscriber<? super T> subscriber) {
            super(subscriber);
            this.l = subscriber;
        }

        @Override // rx.functions.Action0
        public void call() {
            onCompleted();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.onCompleted();
            unsubscribe();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.onError(th);
            unsubscribe();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(t);
        }
    }

    public OperatorTakeTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.h = j;
        this.i = timeUnit;
        this.j = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.j.createWorker();
        subscriber.add(createWorker);
        a aVar = new a(new SerializedSubscriber(subscriber));
        createWorker.schedule(aVar, this.h, this.i);
        return aVar;
    }
}
