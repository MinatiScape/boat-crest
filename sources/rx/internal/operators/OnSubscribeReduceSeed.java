package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func2;
/* loaded from: classes13.dex */
public final class OnSubscribeReduceSeed<T, R> implements Observable.OnSubscribe<R> {
    public final Observable<T> h;
    public final R i;
    public final Func2<R, ? super T, R> j;

    /* loaded from: classes13.dex */
    public static final class a<T, R> extends DeferredScalarSubscriber<T, R> {
        public final Func2<R, ? super T, R> m;

        public a(Subscriber<? super R> subscriber, R r, Func2<R, ? super T, R> func2) {
            super(subscriber);
            this.value = r;
            this.hasValue = true;
            this.m = func2;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                this.value = this.m.call(this.value, t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                this.actual.onError(th);
            }
        }
    }

    public OnSubscribeReduceSeed(Observable<T> observable, R r, Func2<R, ? super T, R> func2) {
        this.h = observable;
        this.i = r;
        this.j = func2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        new a(subscriber, this.i, this.j).subscribeTo(this.h);
    }
}
