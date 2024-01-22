package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class OperatorTake<T> implements Observable.Operator<T, T> {
    public final int h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public int l;
        public boolean m;
        public final /* synthetic */ Subscriber n;

        /* renamed from: rx.internal.operators.OperatorTake$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0956a implements Producer {
            public final AtomicLong h = new AtomicLong(0);
            public final /* synthetic */ Producer i;

            public C0956a(Producer producer) {
                this.i = producer;
            }

            @Override // rx.Producer
            public void request(long j) {
                long j2;
                long min;
                if (j <= 0 || a.this.m) {
                    return;
                }
                do {
                    j2 = this.h.get();
                    min = Math.min(j, OperatorTake.this.h - j2);
                    if (min == 0) {
                        return;
                    }
                } while (!this.h.compareAndSet(j2, j2 + min));
                this.i.request(min);
            }
        }

        public a(Subscriber subscriber) {
            this.n = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.m) {
                return;
            }
            this.m = true;
            this.n.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.m) {
                return;
            }
            this.m = true;
            try {
                this.n.onError(th);
            } finally {
                unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (isUnsubscribed()) {
                return;
            }
            int i = this.l;
            int i2 = i + 1;
            this.l = i2;
            int i3 = OperatorTake.this.h;
            if (i < i3) {
                boolean z = i2 == i3;
                this.n.onNext(t);
                if (!z || this.m) {
                    return;
                }
                this.m = true;
                try {
                    this.n.onCompleted();
                } finally {
                    unsubscribe();
                }
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.n.setProducer(new C0956a(producer));
        }
    }

    public OperatorTake(int i) {
        if (i >= 0) {
            this.h = i;
            return;
        }
        throw new IllegalArgumentException("limit >= 0 required but it was " + i);
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        a aVar = new a(subscriber);
        if (this.h == 0) {
            subscriber.onCompleted();
            aVar.unsubscribe();
        }
        subscriber.add(aVar);
        return aVar;
    }
}
