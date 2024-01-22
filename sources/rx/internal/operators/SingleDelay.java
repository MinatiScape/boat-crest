package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public final class SingleDelay<T> implements Single.OnSubscribe<T> {
    public final Single.OnSubscribe<T> h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;

    /* loaded from: classes13.dex */
    public static final class a<T> extends SingleSubscriber<T> implements Action0 {
        public final SingleSubscriber<? super T> i;
        public final Scheduler.Worker j;
        public final long k;
        public final TimeUnit l;
        public T m;
        public Throwable n;

        public a(SingleSubscriber<? super T> singleSubscriber, Scheduler.Worker worker, long j, TimeUnit timeUnit) {
            this.i = singleSubscriber;
            this.j = worker;
            this.k = j;
            this.l = timeUnit;
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                Throwable th = this.n;
                if (th != null) {
                    this.n = null;
                    this.i.onError(th);
                } else {
                    T t = this.m;
                    this.m = null;
                    this.i.onSuccess(t);
                }
            } finally {
                this.j.unsubscribe();
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            this.n = th;
            this.j.schedule(this, this.k, this.l);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.m = t;
            this.j.schedule(this, this.k, this.l);
        }
    }

    public SingleDelay(Single.OnSubscribe<T> onSubscribe, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.h = onSubscribe;
        this.k = scheduler;
        this.i = j;
        this.j = timeUnit;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        Scheduler.Worker createWorker = this.k.createWorker();
        a aVar = new a(singleSubscriber, createWorker, this.i, this.j);
        singleSubscriber.add(createWorker);
        singleSubscriber.add(aVar);
        this.h.call(aVar);
    }
}
