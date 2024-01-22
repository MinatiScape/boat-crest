package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OnSubscribeFilter<T> implements Observable.OnSubscribe<T> {
    public final Observable<T> h;
    public final Func1<? super T, Boolean> i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> {
        public final Subscriber<? super T> l;
        public final Func1<? super T, Boolean> m;
        public boolean n;

        public a(Subscriber<? super T> subscriber, Func1<? super T, Boolean> func1) {
            this.l = subscriber;
            this.m = func1;
            request(0L);
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
                if (this.m.call(t).booleanValue()) {
                    this.l.onNext(t);
                } else {
                    request(1L);
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            super.setProducer(producer);
            this.l.setProducer(producer);
        }
    }

    public OnSubscribeFilter(Observable<T> observable, Func1<? super T, Boolean> func1) {
        this.h = observable;
        this.i = func1;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        a aVar = new a(subscriber, this.i);
        subscriber.add(aVar);
        this.h.unsafeSubscribe(aVar);
    }
}
