package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.producers.SingleDelayedProducer;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OperatorAny<T> implements Observable.Operator<Boolean, T> {
    public final Func1<? super T, Boolean> h;
    public final boolean i;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public boolean l;
        public boolean m;
        public final /* synthetic */ SingleDelayedProducer n;
        public final /* synthetic */ Subscriber o;

        public a(SingleDelayedProducer singleDelayedProducer, Subscriber subscriber) {
            this.n = singleDelayedProducer;
            this.o = subscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.m) {
                return;
            }
            this.m = true;
            if (this.l) {
                this.n.setValue(Boolean.FALSE);
            } else {
                this.n.setValue(Boolean.valueOf(OperatorAny.this.i));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (!this.m) {
                this.m = true;
                this.o.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.m) {
                return;
            }
            this.l = true;
            try {
                if (OperatorAny.this.h.call(t).booleanValue()) {
                    this.m = true;
                    this.n.setValue(Boolean.valueOf(true ^ OperatorAny.this.i));
                    unsubscribe();
                }
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this, t);
            }
        }
    }

    public OperatorAny(Func1<? super T, Boolean> func1, boolean z) {
        this.h = func1;
        this.i = z;
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
