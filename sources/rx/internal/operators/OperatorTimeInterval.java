package rx.internal.operators;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.TimeInterval;
/* loaded from: classes13.dex */
public final class OperatorTimeInterval<T> implements Observable.Operator<TimeInterval<T>, T> {
    public final Scheduler h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public long l;
        public final /* synthetic */ Subscriber m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.m = subscriber2;
            this.l = OperatorTimeInterval.this.h.now();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.m.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long now = OperatorTimeInterval.this.h.now();
            this.m.onNext(new TimeInterval(now - this.l, t));
            this.l = now;
        }
    }

    public OperatorTimeInterval(Scheduler scheduler) {
        this.h = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super TimeInterval<T>> subscriber) {
        return new a(subscriber, subscriber);
    }
}
