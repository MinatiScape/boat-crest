package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class OperatorTakeUntilPredicate<T> implements Observable.Operator<T, T> {
    public final Func1<? super T, Boolean> h;

    /* loaded from: classes13.dex */
    public class a implements Producer {
        public final /* synthetic */ b h;

        public a(OperatorTakeUntilPredicate operatorTakeUntilPredicate, b bVar) {
            this.h = bVar;
        }

        @Override // rx.Producer
        public void request(long j) {
            this.h.b(j);
        }
    }

    /* loaded from: classes13.dex */
    public final class b extends Subscriber<T> {
        public final Subscriber<? super T> l;
        public boolean m;

        public b(Subscriber<? super T> subscriber) {
            this.l = subscriber;
        }

        public void b(long j) {
            request(j);
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.m) {
                return;
            }
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.m) {
                return;
            }
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(t);
            try {
                if (OperatorTakeUntilPredicate.this.h.call(t).booleanValue()) {
                    this.m = true;
                    this.l.onCompleted();
                    unsubscribe();
                }
            } catch (Throwable th) {
                this.m = true;
                Exceptions.throwOrReport(th, this.l, t);
                unsubscribe();
            }
        }
    }

    public OperatorTakeUntilPredicate(Func1<? super T, Boolean> func1) {
        this.h = func1;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        b bVar = new b(subscriber);
        subscriber.add(bVar);
        subscriber.setProducer(new a(this, bVar));
        return bVar;
    }
}
