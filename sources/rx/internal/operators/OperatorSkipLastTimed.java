package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Timestamped;
/* loaded from: classes13.dex */
public class OperatorSkipLastTimed<T> implements Observable.Operator<T, T> {
    public final long h;
    public final Scheduler i;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public Deque<Timestamped<T>> l;
        public final /* synthetic */ Subscriber m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.m = subscriber2;
            this.l = new ArrayDeque();
        }

        public final void b(long j) {
            long j2 = j - OperatorSkipLastTimed.this.h;
            while (!this.l.isEmpty()) {
                Timestamped<T> first = this.l.getFirst();
                if (first.getTimestampMillis() >= j2) {
                    return;
                }
                this.l.removeFirst();
                this.m.onNext(first.getValue());
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            b(OperatorSkipLastTimed.this.i.now());
            this.m.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            long now = OperatorSkipLastTimed.this.i.now();
            b(now);
            this.l.offerLast(new Timestamped<>(now, t));
        }
    }

    public OperatorSkipLastTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
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
