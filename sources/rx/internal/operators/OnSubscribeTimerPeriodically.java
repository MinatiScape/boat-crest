package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public final class OnSubscribeTimerPeriodically implements Observable.OnSubscribe<Long> {
    public final long h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;

    /* loaded from: classes13.dex */
    public class a implements Action0 {
        public long h;
        public final /* synthetic */ Subscriber i;
        public final /* synthetic */ Scheduler.Worker j;

        public a(OnSubscribeTimerPeriodically onSubscribeTimerPeriodically, Subscriber subscriber, Scheduler.Worker worker) {
            this.i = subscriber;
            this.j = worker;
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                Subscriber subscriber = this.i;
                long j = this.h;
                this.h = 1 + j;
                subscriber.onNext(Long.valueOf(j));
            } catch (Throwable th) {
                try {
                    this.j.unsubscribe();
                } finally {
                    Exceptions.throwOrReport(th, this.i);
                }
            }
        }
    }

    public OnSubscribeTimerPeriodically(long j, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        this.h = j;
        this.i = j2;
        this.j = timeUnit;
        this.k = scheduler;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super Long> subscriber) {
        Scheduler.Worker createWorker = this.k.createWorker();
        subscriber.add(createWorker);
        createWorker.schedulePeriodically(new a(this, subscriber, createWorker), this.h, this.i, this.j);
    }
}
