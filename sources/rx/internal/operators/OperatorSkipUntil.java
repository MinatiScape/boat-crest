package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;
/* loaded from: classes13.dex */
public final class OperatorSkipUntil<T, U> implements Observable.Operator<T, T> {
    public final Observable<U> h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<U> {
        public final /* synthetic */ AtomicBoolean l;
        public final /* synthetic */ SerializedSubscriber m;

        public a(OperatorSkipUntil operatorSkipUntil, AtomicBoolean atomicBoolean, SerializedSubscriber serializedSubscriber) {
            this.l = atomicBoolean;
            this.m = serializedSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            unsubscribe();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
            this.m.unsubscribe();
        }

        @Override // rx.Observer
        public void onNext(U u) {
            this.l.set(true);
            unsubscribe();
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<T> {
        public final /* synthetic */ AtomicBoolean l;
        public final /* synthetic */ SerializedSubscriber m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(OperatorSkipUntil operatorSkipUntil, Subscriber subscriber, AtomicBoolean atomicBoolean, SerializedSubscriber serializedSubscriber) {
            super(subscriber);
            this.l = atomicBoolean;
            this.m = serializedSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.m.onCompleted();
            unsubscribe();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
            unsubscribe();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.l.get()) {
                this.m.onNext(t);
            } else {
                request(1L);
            }
        }
    }

    public OperatorSkipUntil(Observable<U> observable) {
        this.h = observable;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        a aVar = new a(this, atomicBoolean, serializedSubscriber);
        subscriber.add(aVar);
        this.h.unsafeSubscribe(aVar);
        return new b(this, subscriber, atomicBoolean, serializedSubscriber);
    }
}
