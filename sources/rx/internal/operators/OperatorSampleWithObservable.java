package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.observers.SerializedSubscriber;
/* loaded from: classes13.dex */
public final class OperatorSampleWithObservable<T, U> implements Observable.Operator<T, T> {
    public static final Object i = new Object();
    public final Observable<U> h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<U> {
        public final /* synthetic */ AtomicReference l;
        public final /* synthetic */ SerializedSubscriber m;
        public final /* synthetic */ AtomicReference n;

        public a(OperatorSampleWithObservable operatorSampleWithObservable, AtomicReference atomicReference, SerializedSubscriber serializedSubscriber, AtomicReference atomicReference2) {
            this.l = atomicReference;
            this.m = serializedSubscriber;
            this.n = atomicReference2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            onNext(null);
            this.m.onCompleted();
            ((Subscription) this.n.get()).unsubscribe();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
            ((Subscription) this.n.get()).unsubscribe();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.Observer
        public void onNext(U u) {
            AtomicReference atomicReference = this.l;
            Object obj = OperatorSampleWithObservable.i;
            Object andSet = atomicReference.getAndSet(obj);
            if (andSet != obj) {
                this.m.onNext(andSet);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<T> {
        public final /* synthetic */ AtomicReference l;
        public final /* synthetic */ SerializedSubscriber m;
        public final /* synthetic */ Subscriber n;

        public b(OperatorSampleWithObservable operatorSampleWithObservable, AtomicReference atomicReference, SerializedSubscriber serializedSubscriber, Subscriber subscriber) {
            this.l = atomicReference;
            this.m = serializedSubscriber;
            this.n = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.n.onNext(null);
            this.m.onCompleted();
            this.n.unsubscribe();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
            this.n.unsubscribe();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.set(t);
        }
    }

    public OperatorSampleWithObservable(Observable<U> observable) {
        this.h = observable;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        AtomicReference atomicReference = new AtomicReference(i);
        AtomicReference atomicReference2 = new AtomicReference();
        a aVar = new a(this, atomicReference, serializedSubscriber, atomicReference2);
        b bVar = new b(this, atomicReference, serializedSubscriber, aVar);
        atomicReference2.lazySet(bVar);
        subscriber.add(bVar);
        subscriber.add(aVar);
        this.h.unsafeSubscribe(aVar);
        return bVar;
    }
}
