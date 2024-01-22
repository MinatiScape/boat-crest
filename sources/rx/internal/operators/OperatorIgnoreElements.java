package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
/* loaded from: classes13.dex */
public class OperatorIgnoreElements<T> implements Observable.Operator<T, T> {

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public final /* synthetic */ Subscriber l;

        public a(OperatorIgnoreElements operatorIgnoreElements, Subscriber subscriber) {
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
        public void onNext(T t) {
        }
    }

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorIgnoreElements<?> f15663a = new OperatorIgnoreElements<>();
    }

    public static <T> OperatorIgnoreElements<T> instance() {
        return (OperatorIgnoreElements<T>) b.f15663a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        a aVar = new a(this, subscriber);
        subscriber.add(aVar);
        return aVar;
    }
}
