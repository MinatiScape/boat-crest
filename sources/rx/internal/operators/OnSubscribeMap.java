package rx.internal.operators;

import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OnSubscribeMap<T, R> implements Observable.OnSubscribe<R> {
    public final Observable<T> h;
    public final Func1<? super T, ? extends R> i;

    /* loaded from: classes13.dex */
    public static final class a<T, R> extends Subscriber<T> {
        public final Subscriber<? super R> l;
        public final Func1<? super T, ? extends R> m;
        public boolean n;

        public a(Subscriber<? super R> subscriber, Func1<? super T, ? extends R> func1) {
            this.l = subscriber;
            this.m = func1;
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
                this.l.onNext(this.m.call(t));
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

    public OnSubscribeMap(Observable<T> observable, Func1<? super T, ? extends R> func1) {
        this.h = observable;
        this.i = func1;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        a aVar = new a(subscriber, this.i);
        subscriber.add(aVar);
        this.h.unsafeSubscribe(aVar);
    }
}
