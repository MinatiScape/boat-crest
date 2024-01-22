package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.observers.Subscribers;
/* loaded from: classes13.dex */
public final class OnSubscribeDelaySubscription<T> implements Observable.OnSubscribe<T> {
    public final Observable<? extends T> h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;

    /* loaded from: classes13.dex */
    public class a implements Action0 {
        public final /* synthetic */ Subscriber h;

        public a(Subscriber subscriber) {
            this.h = subscriber;
        }

        @Override // rx.functions.Action0
        public void call() {
            if (this.h.isUnsubscribed()) {
                return;
            }
            OnSubscribeDelaySubscription.this.h.unsafeSubscribe(Subscribers.wrap(this.h));
        }
    }

    public OnSubscribeDelaySubscription(Observable<? extends T> observable, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.h = observable;
        this.i = j;
        this.j = timeUnit;
        this.k = scheduler;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        Scheduler.Worker createWorker = this.k.createWorker();
        subscriber.add(createWorker);
        createWorker.schedule(new a(subscriber), this.i, this.j);
    }
}
