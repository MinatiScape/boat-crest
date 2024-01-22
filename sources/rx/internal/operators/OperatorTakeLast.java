package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class OperatorTakeLast<T> implements Observable.Operator<T, T> {
    public final int h;

    /* loaded from: classes13.dex */
    public class a implements Producer {
        public final /* synthetic */ b h;

        public a(OperatorTakeLast operatorTakeLast, b bVar) {
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
        public final AtomicLong m = new AtomicLong();
        public final ArrayDeque<Object> n = new ArrayDeque<>();
        public final int o;

        public b(Subscriber<? super T> subscriber, int i) {
            this.l = subscriber;
            this.o = i;
        }

        @Override // rx.functions.Func1
        public T call(Object obj) {
            return (T) NotificationLite.getValue(obj);
        }

        @Override // rx.Observer
        public void onCompleted() {
            BackpressureUtils.postCompleteDone(this.m, this.n, this.l, this);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.n.clear();
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.n.size() == this.o) {
                this.n.poll();
            }
            this.n.offer(NotificationLite.next(t));
        }

        public void requestMore(long j) {
            if (j > 0) {
                BackpressureUtils.postCompleteRequest(this.m, j, this.n, this.l, this);
            }
        }
    }

    public OperatorTakeLast(int i) {
        if (i >= 0) {
            this.h = i;
            return;
        }
        throw new IndexOutOfBoundsException("count cannot be negative");
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        b bVar = new b(subscriber, this.h);
        subscriber.add(bVar);
        subscriber.setProducer(new a(this, bVar));
        return bVar;
    }
}
