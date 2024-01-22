package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public class OperatorCast<T, R> implements Observable.Operator<R, T> {
    public final Class<R> h;

    /* loaded from: classes13.dex */
    public static final class a<T, R> extends Subscriber<T> {
        public final Subscriber<? super R> l;
        public final Class<R> m;
        public boolean n;

        public a(Subscriber<? super R> subscriber, Class<R> cls) {
            this.l = subscriber;
            this.m = cls;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.n) {
                return;
            }
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.n) {
                RxJavaHooks.onError(th);
                return;
            }
            this.n = true;
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                this.l.onNext(this.m.cast(t));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            this.l.setProducer(producer);
        }
    }

    public OperatorCast(Class<R> cls) {
        this.h = cls;
    }

    @Override // rx.functions.Func1
    public /* bridge */ /* synthetic */ Object call(Object obj) {
        return call((Subscriber) ((Subscriber) obj));
    }

    public Subscriber<? super T> call(Subscriber<? super R> subscriber) {
        a aVar = new a(subscriber, this.h);
        subscriber.add(aVar);
        return aVar;
    }
}
