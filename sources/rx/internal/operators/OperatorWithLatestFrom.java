package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func2;
import rx.observers.SerializedSubscriber;
/* loaded from: classes13.dex */
public final class OperatorWithLatestFrom<T, U, R> implements Observable.Operator<R, T> {
    public static final Object j = new Object();
    public final Func2<? super T, ? super U, ? extends R> h;
    public final Observable<? extends U> i;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final /* synthetic */ AtomicReference l;
        public final /* synthetic */ SerializedSubscriber m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, boolean z, AtomicReference atomicReference, SerializedSubscriber serializedSubscriber) {
            super(subscriber, z);
            this.l = atomicReference;
            this.m = serializedSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.m.onCompleted();
            this.m.unsubscribe();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
            this.m.unsubscribe();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            Object obj = this.l.get();
            if (obj != OperatorWithLatestFrom.j) {
                try {
                    this.m.onNext(OperatorWithLatestFrom.this.h.call(t, obj));
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, this);
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<U> {
        public final /* synthetic */ AtomicReference l;
        public final /* synthetic */ SerializedSubscriber m;

        public b(OperatorWithLatestFrom operatorWithLatestFrom, AtomicReference atomicReference, SerializedSubscriber serializedSubscriber) {
            this.l = atomicReference;
            this.m = serializedSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.l.get() == OperatorWithLatestFrom.j) {
                this.m.onCompleted();
                this.m.unsubscribe();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
            this.m.unsubscribe();
        }

        @Override // rx.Observer
        public void onNext(U u) {
            this.l.set(u);
        }
    }

    public OperatorWithLatestFrom(Observable<? extends U> observable, Func2<? super T, ? super U, ? extends R> func2) {
        this.i = observable;
        this.h = func2;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber, false);
        subscriber.add(serializedSubscriber);
        AtomicReference atomicReference = new AtomicReference(j);
        a aVar = new a(serializedSubscriber, true, atomicReference, serializedSubscriber);
        b bVar = new b(this, atomicReference, serializedSubscriber);
        serializedSubscriber.add(aVar);
        serializedSubscriber.add(bVar);
        this.i.unsafeSubscribe(bVar);
        return aVar;
    }
}
