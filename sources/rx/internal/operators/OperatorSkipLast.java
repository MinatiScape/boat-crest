package rx.internal.operators;

import java.util.ArrayDeque;
import java.util.Deque;
import rx.Observable;
import rx.Subscriber;
/* loaded from: classes13.dex */
public class OperatorSkipLast<T> implements Observable.Operator<T, T> {
    public final int h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final Deque<Object> l;
        public final /* synthetic */ Subscriber m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.m = subscriber2;
            this.l = new ArrayDeque();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.m.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.Observer
        public void onNext(T t) {
            if (OperatorSkipLast.this.h == 0) {
                this.m.onNext(t);
                return;
            }
            if (this.l.size() == OperatorSkipLast.this.h) {
                this.m.onNext(NotificationLite.getValue(this.l.removeFirst()));
            } else {
                request(1L);
            }
            this.l.offerLast(NotificationLite.next(t));
        }
    }

    public OperatorSkipLast(int i) {
        if (i >= 0) {
            this.h = i;
            return;
        }
        throw new IndexOutOfBoundsException("count could not be negative");
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return new a(subscriber, subscriber);
    }
}
