package rx.internal.operators;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Timestamped;
/* loaded from: classes13.dex */
public final class OperatorTimestamp<T> implements Observable.Operator<Timestamped<T>, T> {
    public final Scheduler h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final /* synthetic */ Subscriber l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.l = subscriber2;
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
            this.l.onNext(new Timestamped(OperatorTimestamp.this.h.now(), t));
        }
    }

    public OperatorTimestamp(Scheduler scheduler) {
        this.h = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super Timestamped<T>> subscriber) {
        return new a(subscriber, subscriber);
    }
}
