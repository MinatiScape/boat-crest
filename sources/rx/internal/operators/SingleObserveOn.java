package rx.internal.operators;

import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;
/* loaded from: classes13.dex */
public final class SingleObserveOn<T> implements Single.OnSubscribe<T> {
    public final Single.OnSubscribe<T> h;
    public final Scheduler i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends SingleSubscriber<T> implements Action0 {
        public final SingleSubscriber<? super T> i;
        public final Scheduler.Worker j;
        public T k;
        public Throwable l;

        public a(SingleSubscriber<? super T> singleSubscriber, Scheduler.Worker worker) {
            this.i = singleSubscriber;
            this.j = worker;
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                Throwable th = this.l;
                if (th != null) {
                    this.l = null;
                    this.i.onError(th);
                } else {
                    T t = this.k;
                    this.k = null;
                    this.i.onSuccess(t);
                }
            } finally {
                this.j.unsubscribe();
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            this.l = th;
            this.j.schedule(this);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.k = t;
            this.j.schedule(this);
        }
    }

    public SingleObserveOn(Single.OnSubscribe<T> onSubscribe, Scheduler scheduler) {
        this.h = onSubscribe;
        this.i = scheduler;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        Scheduler.Worker createWorker = this.i.createWorker();
        a aVar = new a(singleSubscriber, createWorker);
        singleSubscriber.add(createWorker);
        singleSubscriber.add(aVar);
        this.h.call(aVar);
    }
}
