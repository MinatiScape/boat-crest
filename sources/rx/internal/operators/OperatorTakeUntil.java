package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;
/* loaded from: classes13.dex */
public final class OperatorTakeUntil<T, E> implements Observable.Operator<T, T> {
    public final Observable<? extends E> h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final /* synthetic */ Subscriber l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(OperatorTakeUntil operatorTakeUntil, Subscriber subscriber, boolean z, Subscriber subscriber2) {
            super(subscriber, z);
            this.l = subscriber2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            try {
                this.l.onCompleted();
            } finally {
                this.l.unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            try {
                this.l.onError(th);
            } finally {
                this.l.unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(t);
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<E> {
        public final /* synthetic */ Subscriber l;

        public b(OperatorTakeUntil operatorTakeUntil, Subscriber subscriber) {
            this.l = subscriber;
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
        public void onNext(E e) {
            onCompleted();
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    public OperatorTakeUntil(Observable<? extends E> observable) {
        this.h = observable;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
        a aVar = new a(this, serializedSubscriber, false, serializedSubscriber);
        b bVar = new b(this, aVar);
        serializedSubscriber.add(aVar);
        serializedSubscriber.add(bVar);
        subscriber.add(serializedSubscriber);
        this.h.unsafeSubscribe(bVar);
        return aVar;
    }
}
