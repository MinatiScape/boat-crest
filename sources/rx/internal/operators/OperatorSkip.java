package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class OperatorSkip<T> implements Observable.Operator<T, T> {
    public final int h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public int l;
        public final /* synthetic */ Subscriber m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Subscriber subscriber, Subscriber subscriber2) {
            super(subscriber);
            this.m = subscriber2;
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.m.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            int i = this.l;
            if (i >= OperatorSkip.this.h) {
                this.m.onNext(t);
            } else {
                this.l = i + 1;
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.m.setProducer(producer);
            producer.request(OperatorSkip.this.h);
        }
    }

    public OperatorSkip(int i) {
        if (i >= 0) {
            this.h = i;
            return;
        }
        throw new IllegalArgumentException("n >= 0 required but it was " + i);
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return new a(subscriber, subscriber);
    }
}
