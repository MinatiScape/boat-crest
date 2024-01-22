package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.functions.Action1;
/* loaded from: classes13.dex */
public class OperatorDoOnRequest<T> implements Observable.Operator<T, T> {
    public final Action1<? super Long> h;

    /* loaded from: classes13.dex */
    public class a implements Producer {
        public final /* synthetic */ b h;

        public a(b bVar) {
            this.h = bVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            OperatorDoOnRequest.this.h.call(Long.valueOf(j));
            this.h.requestMore(j);
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> {
        public final Subscriber<? super T> l;

        public b(Subscriber<? super T> subscriber) {
            this.l = subscriber;
            request(0L);
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
            this.l.onNext(t);
        }

        public final void requestMore(long j) {
            request(j);
        }
    }

    public OperatorDoOnRequest(Action1<? super Long> action1) {
        this.h = action1;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        b bVar = new b(subscriber);
        subscriber.setProducer(new a(bVar));
        subscriber.add(bVar);
        return bVar;
    }
}
