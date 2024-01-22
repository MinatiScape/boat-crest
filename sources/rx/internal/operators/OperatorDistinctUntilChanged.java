package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.util.UtilityFunctions;
/* loaded from: classes13.dex */
public final class OperatorDistinctUntilChanged<T, U> implements Observable.Operator<T, T>, Func2<U, U, Boolean> {
    public final Func1<? super T, ? extends U> h;
    public final Func2<? super U, ? super U, Boolean> i;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public U l;
        public boolean m;
        public final /* synthetic */ Subscriber n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.n = subscriber2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.n.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.n.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            Object obj;
            try {
                U call = OperatorDistinctUntilChanged.this.h.call(t);
                U u = this.l;
                this.l = call;
                if (this.m) {
                    try {
                        if (!OperatorDistinctUntilChanged.this.i.call(u, call).booleanValue()) {
                            this.n.onNext(t);
                            return;
                        } else {
                            request(1L);
                            return;
                        }
                    } catch (Throwable th) {
                        Exceptions.throwOrReport(th, this.n, obj);
                        return;
                    }
                }
                this.m = true;
                this.n.onNext(t);
            } catch (Throwable th2) {
                Exceptions.throwOrReport(th2, this.n, t);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class b {

        /* renamed from: a  reason: collision with root package name */
        public static final OperatorDistinctUntilChanged<?, ?> f15662a = new OperatorDistinctUntilChanged<>(UtilityFunctions.identity());
    }

    public OperatorDistinctUntilChanged(Func1<? super T, ? extends U> func1) {
        this.h = func1;
        this.i = this;
    }

    public static <T> OperatorDistinctUntilChanged<T, T> instance() {
        return (OperatorDistinctUntilChanged<T, T>) b.f15662a;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // rx.functions.Func2
    public Boolean call(U u, U u2) {
        return Boolean.valueOf(u == u2 || (u != null && u.equals(u2)));
    }

    public OperatorDistinctUntilChanged(Func2<? super U, ? super U, Boolean> func2) {
        this.h = UtilityFunctions.identity();
        this.i = func2;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return new a(subscriber, subscriber);
    }
}
