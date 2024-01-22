package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class OperatorThrottleFirst<T> implements Observable.Operator<T, T> {
    public final long h;
    public final Scheduler i;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public long l;
        public final /* synthetic */ Subscriber m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.m = subscriber2;
            this.l = -1L;
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
            long now = OperatorThrottleFirst.this.i.now();
            long j = this.l;
            if (j == -1 || now < j || now - j >= OperatorThrottleFirst.this.h) {
                this.l = now;
                this.m.onNext(t);
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    public OperatorThrottleFirst(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.h = timeUnit.toMillis(j);
        this.i = scheduler;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return new a(subscriber, subscriber);
    }
}
