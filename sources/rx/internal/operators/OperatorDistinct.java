package rx.internal.operators;

import java.util.HashSet;
import java.util.Set;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.internal.util.UtilityFunctions;
/* loaded from: classes13.dex */
public final class OperatorDistinct<T, U> implements Observable.Operator<T, T> {
    public final Func1<? super T, ? extends U> h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public Set<U> l;
        public final /* synthetic */ Subscriber m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.m = subscriber2;
            this.l = new HashSet();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l = null;
            this.m.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l = null;
            this.m.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.l.add(OperatorDistinct.this.h.call(t))) {
                this.m.onNext(t);
            } else {
                request(1L);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorDistinct<?, ?> f15661a = new OperatorDistinct<>(UtilityFunctions.identity());
    }

    public OperatorDistinct(Func1<? super T, ? extends U> func1) {
        this.h = func1;
    }

    public static <T> OperatorDistinct<T, T> instance() {
        return (OperatorDistinct<T, T>) b.f15661a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return new a(subscriber, subscriber);
    }
}
