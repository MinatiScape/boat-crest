package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public final class OnSubscribeTimerOnce implements Observable.OnSubscribe<Long> {
    public final long h;
    public final TimeUnit i;
    public final Scheduler j;

    /* loaded from: classes13.dex */
    public class a implements Action0 {
        public final /* synthetic */ Subscriber h;

        public a(OnSubscribeTimerOnce onSubscribeTimerOnce, Subscriber subscriber) {
            this.h = subscriber;
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                this.h.onNext(0L);
                this.h.onCompleted();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.h);
            }
        }
    }

    public OnSubscribeTimerOnce(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.h = j;
        this.i = timeUnit;
        this.j = scheduler;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super Long> subscriber) {
        Scheduler.Worker createWorker = this.j.createWorker();
        subscriber.add(createWorker);
        createWorker.schedule(new a(this, subscriber), this.h, this.i);
    }
}
