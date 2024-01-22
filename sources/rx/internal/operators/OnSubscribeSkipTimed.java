package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public final class OnSubscribeSkipTimed<T> implements Observable.OnSubscribe<T> {
    public final long h;
    public final TimeUnit i;
    public final Scheduler j;
    public final Observable<T> k;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> implements Action0 {
        public final Subscriber<? super T> l;
        public volatile boolean m;

        public a(Subscriber<? super T> subscriber) {
            this.l = subscriber;
        }

        @Override // rx.functions.Action0
        public void call() {
            this.m = true;
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                this.l.onCompleted();
            } finally {
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            try {
                this.l.onError(th);
            } finally {
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.m) {
                this.l.onNext(t);
            }
        }
    }

    public OnSubscribeSkipTimed(Observable<T> observable, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.k = observable;
        this.h = j;
        this.i = timeUnit;
        this.j = scheduler;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.j.createWorker();
        a aVar = new a(subscriber);
        aVar.add(createWorker);
        subscriber.add(aVar);
        createWorker.schedule(aVar, this.h, this.i);
        this.k.unsafeSubscribe(aVar);
    }
}
