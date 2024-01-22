package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class OperatorElementAt<T> implements Observable.Operator<T, T> {
    public final int h;
    public final boolean i;
    public final T j;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public int l;
        public final /* synthetic */ Subscriber m;

        public a(Subscriber subscriber) {
            this.m = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            int i = this.l;
            OperatorElementAt operatorElementAt = OperatorElementAt.this;
            if (i <= operatorElementAt.h) {
                if (operatorElementAt.i) {
                    this.m.onNext(operatorElementAt.j);
                    this.m.onCompleted();
                    return;
                }
                Subscriber subscriber = this.m;
                subscriber.onError(new IndexOutOfBoundsException(OperatorElementAt.this.h + " is out of bounds"));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            int i = this.l;
            this.l = i + 1;
            if (i == OperatorElementAt.this.h) {
                this.m.onNext(t);
                this.m.onCompleted();
                unsubscribe();
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.m.setProducer(new b(producer));
        }
    }

    /* loaded from: classes13.dex */
    public static class b extends AtomicBoolean implements Producer {
        private static final long serialVersionUID = 1;
        public final Producer actual;

        public b(Producer producer) {
            this.actual = producer;
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i >= 0) {
                if (i <= 0 || !compareAndSet(false, true)) {
                    return;
                }
                this.actual.request(Long.MAX_VALUE);
                return;
            }
            throw new IllegalArgumentException("n >= 0 required");
        }
    }

    public OperatorElementAt(int i) {
        this(i, null, false);
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorElementAt(int i, T t) {
        this(i, t, true);
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        a aVar = new a(subscriber);
        subscriber.add(aVar);
        return aVar;
    }

    public OperatorElementAt(int i, T t, boolean z) {
        if (i >= 0) {
            this.h = i;
            this.j = t;
            this.i = z;
            return;
        }
        throw new IndexOutOfBoundsException(i + " is out of bounds");
    }
}
