package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.observers.SerializedSubscriber;
/* loaded from: classes13.dex */
public final class OperatorSerialize<T> implements Observable.Operator<T, T> {

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final /* synthetic */ Subscriber l;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(OperatorSerialize operatorSerialize, Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.l = subscriber2;
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
    }

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorSerialize<Object> f15671a = new OperatorSerialize<>();
    }

    public static <T> OperatorSerialize<T> instance() {
        return (OperatorSerialize<T>) b.f15671a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return new SerializedSubscriber(new a(this, subscriber, subscriber));
    }
}
