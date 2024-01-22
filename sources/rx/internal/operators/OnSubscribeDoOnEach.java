package rx.internal.operators;

import java.util.Arrays;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public class OnSubscribeDoOnEach<T> implements Observable.OnSubscribe<T> {
    public final Observer<? super T> h;
    public final Observable<T> i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> {
        public final Subscriber<? super T> l;
        public final Observer<? super T> m;
        public boolean n;

        public a(Subscriber<? super T> subscriber, Observer<? super T> observer) {
            super(subscriber);
            this.l = subscriber;
            this.m = observer;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.n) {
                return;
            }
            try {
                this.m.onCompleted();
                this.n = true;
                this.l.onCompleted();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.n) {
                RxJavaHooks.onError(th);
                return;
            }
            this.n = true;
            try {
                this.m.onError(th);
                this.l.onError(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.l.onError(new CompositeException(Arrays.asList(th, th2)));
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.n) {
                return;
            }
            try {
                this.m.onNext(t);
                this.l.onNext(t);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this, t);
            }
        }
    }

    public OnSubscribeDoOnEach(Observable<T> observable, Observer<? super T> observer) {
        this.i = observable;
        this.h = observer;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        this.i.unsafeSubscribe(new a(subscriber, this.h));
    }
}
