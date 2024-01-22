package rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class SingleTakeUntilObservable<T, U> implements Single.OnSubscribe<T> {
    public final Single.OnSubscribe<T> h;
    public final Observable<? extends U> i;

    /* loaded from: classes13.dex */
    public static final class a<T, U> extends SingleSubscriber<T> {
        public final SingleSubscriber<? super T> i;
        public final AtomicBoolean j = new AtomicBoolean();
        public final Subscriber<U> k;

        /* renamed from: rx.internal.operators.SingleTakeUntilObservable$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public final class C0963a extends Subscriber<U> {
            public C0963a() {
            }

            @Override // rx.Observer
            public void onCompleted() {
                onError(new CancellationException("Single::takeUntil(Observable) - Stream was canceled before emitting a terminal event."));
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                a.this.onError(th);
            }

            @Override // rx.Observer
            public void onNext(U u) {
                onCompleted();
            }
        }

        public a(SingleSubscriber<? super T> singleSubscriber) {
            this.i = singleSubscriber;
            C0963a c0963a = new C0963a();
            this.k = c0963a;
            add(c0963a);
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            if (this.j.compareAndSet(false, true)) {
                unsubscribe();
                this.i.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            if (this.j.compareAndSet(false, true)) {
                unsubscribe();
                this.i.onSuccess(t);
            }
        }
    }

    public SingleTakeUntilObservable(Single.OnSubscribe<T> onSubscribe, Observable<? extends U> observable) {
        this.h = onSubscribe;
        this.i = observable;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        a aVar = new a(singleSubscriber);
        singleSubscriber.add(aVar);
        this.i.subscribe((Subscriber<? super Object>) aVar.k);
        this.h.call(aVar);
    }
}
