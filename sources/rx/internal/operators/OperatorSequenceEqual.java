package rx.internal.operators;

import rx.Observable;
import rx.functions.Func2;
import rx.internal.util.UtilityFunctions;
/* loaded from: classes13.dex */
public final class OperatorSequenceEqual {

    /* renamed from: a  reason: collision with root package name */
    public static final Object f15670a = new Object();

    /* loaded from: classes13.dex */
    public static class a implements Func2<Object, Object, Boolean> {
        public final /* synthetic */ Func2 h;

        public a(Func2 func2) {
            this.h = func2;
        }

        @Override // rx.functions.Func2
        /* renamed from: a */
        public Boolean call(Object obj, Object obj2) {
            Object obj3 = OperatorSequenceEqual.f15670a;
            boolean z = obj == obj3;
            boolean z2 = obj2 == obj3;
            if (z && z2) {
                return Boolean.TRUE;
            }
            if (!z && !z2) {
                return (Boolean) this.h.call(obj, obj2);
            }
            return Boolean.FALSE;
        }
    }

    public OperatorSequenceEqual() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Observable<Object> a(Observable<T> observable) {
        return Observable.concat(observable, Observable.just(f15670a));
    }

    public static <T> Observable<Boolean> sequenceEqual(Observable<? extends T> observable, Observable<? extends T> observable2, Func2<? super T, ? super T, Boolean> func2) {
        return Observable.zip(a(observable), a(observable2), new a(func2)).all(UtilityFunctions.identity());
    }
}
