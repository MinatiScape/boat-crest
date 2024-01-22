package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.SingleDelayedProducer;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OperatorAll<T> implements Observable.Operator<Boolean, T> {
    public final Func1<? super T, Boolean> h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public boolean l;
        public final /* synthetic */ SingleDelayedProducer m;
        public final /* synthetic */ Subscriber n;

        public a(SingleDelayedProducer singleDelayedProducer, Subscriber subscriber) {
            this.m = singleDelayedProducer;
            this.n = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.l) {
                return;
            }
            this.l = true;
            this.m.setValue(Boolean.TRUE);
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!this.l) {
                this.l = true;
                this.n.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.l) {
                return;
            }
            try {
                if (OperatorAll.this.h.call(t).booleanValue()) {
                    return;
                }
                this.l = true;
                this.m.setValue(Boolean.FALSE);
                unsubscribe();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this, t);
            }
        }
    }

    public OperatorAll(Func1<? super T, Boolean> func1) {
        this.h = func1;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super Boolean> subscriber) {
        SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        a aVar = new a(singleDelayedProducer, subscriber);
        subscriber.add(aVar);
        subscriber.setProducer(singleDelayedProducer);
        return aVar;
    }
}
