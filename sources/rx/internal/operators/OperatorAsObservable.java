package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class OperatorAsObservable<T> implements Observable.Operator<T, T> {

    /* loaded from: classes13.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorAsObservable<Object> f15657a = new OperatorAsObservable<>();
    }

    public static <T> OperatorAsObservable<T> instance() {
        return (OperatorAsObservable<T>) a.f15657a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return subscriber;
    }
}
