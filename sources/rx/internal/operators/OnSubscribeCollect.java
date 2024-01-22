package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action2;
import rx.functions.Func0;
/* loaded from: classes13.dex */
public final class OnSubscribeCollect<T, R> implements Observable.OnSubscribe<R> {
    public final Observable<T> h;
    public final Func0<R> i;
    public final Action2<R, ? super T> j;

    /* loaded from: classes13.dex */
    public static final class a<T, R> extends DeferredScalarSubscriberSafe<T, R> {
        public final Action2<R, ? super T> m;

        public a(Subscriber<? super R> subscriber, R r, Action2<R, ? super T> action2) {
            super(subscriber);
            this.value = r;
            this.hasValue = true;
            this.m = action2;
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            try {
                this.m.call(this.value, t);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }
    }

    public OnSubscribeCollect(Observable<T> observable, Func0<R> func0, Action2<R, ? super T> action2) {
        this.h = observable;
        this.i = func0;
        this.j = action2;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super R> subscriber) {
        try {
            new a(subscriber, this.i.call(), this.j).subscribeTo(this.h);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscriber.onError(th);
        }
    }
}
