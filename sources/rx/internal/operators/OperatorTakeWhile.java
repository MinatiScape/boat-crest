package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
/* loaded from: classes13.dex */
public final class OperatorTakeWhile<T> implements Observable.Operator<T, T> {
    public final Func2<? super T, ? super Integer, Boolean> h;

    /* loaded from: classes13.dex */
    public class a implements Func2<T, Integer, Boolean> {
        public final /* synthetic */ Func1 h;

        public a(Func1 func1) {
            this.h = func1;
        }

        @Override // rx.functions.Func2
        /* renamed from: a */
        public Boolean call(T t, Integer num) {
            return (Boolean) this.h.call(t);
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<T> {
        public int l;
        public boolean m;
        public final /* synthetic */ Subscriber n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Subscriber subscriber, boolean z, Subscriber subscriber2) {
            super(subscriber, z);
            this.n = subscriber2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.m) {
                return;
            }
            this.n.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.m) {
                return;
            }
            this.n.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Func2<? super T, ? super Integer, Boolean> func2 = OperatorTakeWhile.this.h;
                int i = this.l;
                this.l = i + 1;
                if (func2.call(t, Integer.valueOf(i)).booleanValue()) {
                    this.n.onNext(t);
                    return;
                }
                this.m = true;
                this.n.onCompleted();
                unsubscribe();
            } catch (Throwable th) {
                this.m = true;
                Exceptions.throwOrReport(th, this.n, t);
                unsubscribe();
            }
        }
    }

    public OperatorTakeWhile(Func1<? super T, Boolean> func1) {
        this(new a(func1));
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public OperatorTakeWhile(Func2<? super T, ? super Integer, Boolean> func2) {
        this.h = func2;
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        b bVar = new b(subscriber, false, subscriber);
        subscriber.add(bVar);
        return bVar;
    }
}
