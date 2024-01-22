package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.observers.SerializedSubscriber;
import rx.observers.Subscribers;
import rx.subjects.PublishSubject;
/* loaded from: classes13.dex */
public final class OperatorDelayWithSelector<T, V> implements Observable.Operator<T, T> {
    public final Func1<? super T, ? extends Observable<V>> h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final /* synthetic */ PublishSubject l;
        public final /* synthetic */ SerializedSubscriber m;

        /* renamed from: rx.internal.operators.OperatorDelayWithSelector$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0951a implements Func1<V, T> {
            public final /* synthetic */ Object h;

            public C0951a(a aVar, Object obj) {
                this.h = obj;
            }

            @Override // rx.functions.Func1
            public T call(V v) {
                return (T) this.h;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, PublishSubject publishSubject, SerializedSubscriber serializedSubscriber) {
            super(subscriber);
            this.l = publishSubject;
            this.m = serializedSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.Observer
        public void onNext(T t) {
            try {
                this.l.onNext(OperatorDelayWithSelector.this.h.call(t).take(1).defaultIfEmpty(null).map(new C0951a(this, t)));
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this);
            }
        }
    }

    public OperatorDelayWithSelector(Observable<? extends T> observable, Func1<? super T, ? extends Observable<V>> func1) {
        this.h = func1;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        PublishSubject create = PublishSubject.create();
        subscriber.add(Observable.merge(create).unsafeSubscribe(Subscribers.from(serializedSubscriber)));
        return new a(subscriber, create, serializedSubscriber);
    }
}
