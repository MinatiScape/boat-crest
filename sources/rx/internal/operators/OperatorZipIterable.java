package rx.internal.operators;

import java.util.Iterator;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func2;
import rx.observers.Subscribers;
/* loaded from: classes13.dex */
public final class OperatorZipIterable<T1, T2, R> implements Observable.Operator<R, T1> {
    public final Iterable<? extends T2> h;
    public final Func2<? super T1, ? super T2, ? extends R> i;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T1> {
        public boolean l;
        public final /* synthetic */ Subscriber m;
        public final /* synthetic */ Iterator n;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2, Iterator it) {
            super(subscriber);
            this.m = subscriber2;
            this.n = it;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.m.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.l) {
                Exceptions.throwIfFatal(th);
                return;
            }
            this.l = true;
            this.m.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T1 t1) {
            if (this.l) {
                return;
            }
            try {
                this.m.onNext(OperatorZipIterable.this.i.call(t1, (Object) this.n.next()));
                if (this.n.hasNext()) {
                    return;
                }
                onCompleted();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this);
            }
        }
    }

    public OperatorZipIterable(Iterable<? extends T2> iterable, Func2<? super T1, ? super T2, ? extends R> func2) {
        this.h = iterable;
        this.i = func2;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T1> call(Subscriber<? super R> subscriber) {
        Iterator<? extends T2> it = this.h.iterator();
        try {
            if (!it.hasNext()) {
                subscriber.onCompleted();
                return Subscribers.empty();
            }
            return new a(subscriber, subscriber, it);
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
            return Subscribers.empty();
        }
    }
}
