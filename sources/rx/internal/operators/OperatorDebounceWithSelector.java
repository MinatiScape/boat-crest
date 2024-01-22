package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.operators.OperatorDebounceWithTime;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public final class OperatorDebounceWithSelector<T, U> implements Observable.Operator<T, T> {
    public final Func1<? super T, ? extends Observable<U>> h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final OperatorDebounceWithTime.b<T> l;
        public final Subscriber<?> m;
        public final /* synthetic */ SerializedSubscriber n;
        public final /* synthetic */ SerialSubscription o;

        /* renamed from: rx.internal.operators.OperatorDebounceWithSelector$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0948a extends Subscriber<U> {
            public final /* synthetic */ int l;

            public C0948a(int i) {
                this.l = i;
            }

            @Override // rx.Observer
            public void onCompleted() {
                a aVar = a.this;
                aVar.l.b(this.l, aVar.n, aVar.m);
                unsubscribe();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                a.this.m.onError(th);
            }

            @Override // rx.Observer
            public void onNext(U u) {
                onCompleted();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, SerializedSubscriber serializedSubscriber, SerialSubscription serialSubscription) {
            super(subscriber);
            this.n = serializedSubscriber;
            this.o = serialSubscription;
            this.l = new OperatorDebounceWithTime.b<>();
            this.m = this;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l.c(this.n, this);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.n.onError(th);
            unsubscribe();
            this.l.a();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Observable<U> call = OperatorDebounceWithSelector.this.h.call(t);
                C0948a c0948a = new C0948a(this.l.d(t));
                this.o.set(c0948a);
                call.unsafeSubscribe(c0948a);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this);
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(Long.MAX_VALUE);
        }
    }

    public OperatorDebounceWithSelector(Func1<? super T, ? extends Observable<U>> func1) {
        this.h = func1;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        SerializedSubscriber serializedSubscriber = new SerializedSubscriber(subscriber);
        SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        return new a(subscriber, serializedSubscriber, serialSubscription);
    }
}
