package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.functions.Func2;
/* loaded from: classes13.dex */
public final class OperatorSkipWhile<T> implements Observable.Operator<T, T> {
    public final Func2<? super T, Integer, Boolean> h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public boolean l;
        public int m;
        public final /* synthetic */ Subscriber n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.n = subscriber2;
            this.l = true;
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
            if (!this.l) {
                this.n.onNext(t);
                return;
            }
            try {
                Func2<? super T, Integer, Boolean> func2 = OperatorSkipWhile.this.h;
                int i = this.m;
                this.m = i + 1;
                if (!func2.call(t, Integer.valueOf(i)).booleanValue()) {
                    this.l = false;
                    this.n.onNext(t);
                    return;
                }
                request(1L);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this.n, t);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements Func2<T, Integer, Boolean> {
        public final /* synthetic */ Func1 h;

        public b(Func1 func1) {
            this.h = func1;
        }

        @Override // rx.functions.Func2
        /* renamed from: a */
        public Boolean call(T t, Integer num) {
            return (Boolean) this.h.call(t);
        }
    }

    public OperatorSkipWhile(Func2<? super T, Integer, Boolean> func2) {
        this.h = func2;
    }

    public static <T> Func2<T, Integer, Boolean> toPredicate2(Func1<? super T, Boolean> func1) {
        return new b(func1);
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return new a(subscriber, subscriber);
    }
}
