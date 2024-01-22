package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class SingleFromObservable<T> implements Single.OnSubscribe<T> {
    public final Observable.OnSubscribe<T> h;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> {
        public final SingleSubscriber<? super T> l;
        public T m;
        public int n;

        public a(SingleSubscriber<? super T> singleSubscriber) {
            this.l = singleSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            int i = this.n;
            if (i == 0) {
                this.l.onError(new NoSuchElementException());
            } else if (i == 1) {
                this.n = 2;
                T t = this.m;
                this.m = null;
                this.l.onSuccess(t);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.n == 2) {
                RxJavaHooks.onError(th);
                return;
            }
            this.m = null;
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            int i = this.n;
            if (i == 0) {
                this.n = 1;
                this.m = t;
            } else if (i == 1) {
                this.n = 2;
                this.l.onError(new IndexOutOfBoundsException("The upstream produced more than one value"));
            }
        }
    }

    public SingleFromObservable(Observable.OnSubscribe<T> onSubscribe) {
        this.h = onSubscribe;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        a aVar = new a(singleSubscriber);
        singleSubscriber.add(aVar);
        this.h.call(aVar);
    }
}
