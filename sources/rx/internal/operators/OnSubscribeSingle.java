package rx.internal.operators;

import java.util.NoSuchElementException;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
/* loaded from: classes13.dex */
public class OnSubscribeSingle<T> implements Single.OnSubscribe<T> {
    public final Observable<T> h;

    /* loaded from: classes13.dex */
    public class a extends Subscriber<T> {
        public boolean l;
        public boolean m;
        public T n;
        public final /* synthetic */ SingleSubscriber o;

        public a(OnSubscribeSingle onSubscribeSingle, SingleSubscriber singleSubscriber) {
            this.o = singleSubscriber;
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.l) {
                return;
            }
            if (this.m) {
                this.o.onSuccess(this.n);
            } else {
                this.o.onError(new NoSuchElementException("Observable emitted no items"));
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.o.onError(th);
            unsubscribe();
        }

        @Override // rx.Observer
        public void onNext(T t) {
            if (this.m) {
                this.l = true;
                this.o.onError(new IllegalArgumentException("Observable emitted too many elements"));
                unsubscribe();
                return;
            }
            this.m = true;
            this.n = t;
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(2L);
        }
    }

    public OnSubscribeSingle(Observable<T> observable) {
        this.h = observable;
    }

    public static <T> OnSubscribeSingle<T> create(Observable<T> observable) {
        return new OnSubscribeSingle<>(observable);
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        a aVar = new a(this, singleSubscriber);
        singleSubscriber.add(aVar);
        this.h.unsafeSubscribe(aVar);
    }
}
