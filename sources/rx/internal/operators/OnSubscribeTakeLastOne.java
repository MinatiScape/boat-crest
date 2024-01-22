package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;
/* loaded from: classes13.dex */
public final class OnSubscribeTakeLastOne<T> implements Observable.OnSubscribe<T> {
    public final Observable<T> h;

    /* loaded from: classes13.dex */
    public static final class a<T> extends DeferredScalarSubscriber<T, T> {
        public static final Object m = new Object();

        /* JADX WARN: Type inference failed for: r1v1, types: [R, java.lang.Object] */
        public a(Subscriber<? super T> subscriber) {
            super(subscriber);
            this.value = m;
        }

        @Override // rx.internal.operators.DeferredScalarSubscriber, rx.Observer
        public void onCompleted() {
            Object obj = this.value;
            if (obj == m) {
                complete();
            } else {
                complete(obj);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // rx.Observer
        public void onNext(T t) {
            this.value = t;
        }
    }

    public OnSubscribeTakeLastOne(Observable<T> observable) {
        this.h = observable;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        new a(subscriber).subscribeTo(this.h);
    }
}
