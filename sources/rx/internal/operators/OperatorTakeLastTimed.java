package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class OperatorTakeLastTimed<T> implements Observable.Operator<T, T> {
    public final long h;
    public final Scheduler i;
    public final int j;

    /* loaded from: classes13.dex */
    public class a implements Producer {
        public final /* synthetic */ b h;

        public a(OperatorTakeLastTimed operatorTakeLastTimed, b bVar) {
            this.h = bVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            this.h.requestMore(j);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> implements Func1<Object, T> {
        public final Subscriber<? super T> l;
        public final long m;
        public final Scheduler n;
        public final int o;
        public final AtomicLong p = new AtomicLong();
        public final ArrayDeque<Object> q = new ArrayDeque<>();
        public final ArrayDeque<Long> r = new ArrayDeque<>();

        public b(Subscriber<? super T> subscriber, int i, long j, Scheduler scheduler) {
            this.l = subscriber;
            this.o = i;
            this.m = j;
            this.n = scheduler;
        }

        public void b(long j) {
            long j2 = j - this.m;
            while (true) {
                Long peek = this.r.peek();
                if (peek == null || peek.longValue() >= j2) {
                    return;
                }
                this.q.poll();
                this.r.poll();
            }
        }

        @Override // rx.functions.Func1
        public T call(Object obj) {
            return (T) NotificationLite.getValue(obj);
        }

        @Override // rx.Observer
        public void onCompleted() {
            b(this.n.now());
            this.r.clear();
            BackpressureUtils.postCompleteDone(this.p, this.q, this.l, this);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.q.clear();
            this.r.clear();
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.o != 0) {
                long now = this.n.now();
                if (this.q.size() == this.o) {
                    this.q.poll();
                    this.r.poll();
                }
                b(now);
                this.q.offer(NotificationLite.next(t));
                this.r.offer(Long.valueOf(now));
            }
        }

        public void requestMore(long j) {
            BackpressureUtils.postCompleteRequest(this.p, j, this.q, this.l, this);
        }
    }

    public OperatorTakeLastTimed(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.h = timeUnit.toMillis(j);
        this.i = scheduler;
        this.j = -1;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        b bVar = new b(subscriber, this.j, this.h, this.i);
        subscriber.add(bVar);
        subscriber.setProducer(new a(this, bVar));
        return bVar;
    }

    public OperatorTakeLastTimed(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        if (i >= 0) {
            this.h = timeUnit.toMillis(j);
            this.i = scheduler;
            this.j = i;
            return;
        }
        throw new IndexOutOfBoundsException("count could not be negative");
    }
}
