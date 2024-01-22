package rx.internal.operators;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Single;
import rx.SingleSubscriber;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class SingleTakeUntilSingle<T, U> implements Single.OnSubscribe<T> {
    public final Single.OnSubscribe<T> h;
    public final Single<? extends U> i;

    /* loaded from: classes13.dex */
    public static final class a<T, U> extends SingleSubscriber<T> {
        public final SingleSubscriber<? super T> i;
        public final AtomicBoolean j = new AtomicBoolean();
        public final SingleSubscriber<U> k;

        /* renamed from: rx.internal.operators.SingleTakeUntilSingle$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public final class C0964a extends SingleSubscriber<U> {
            public C0964a() {
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                a.this.onError(th);
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(U u) {
                onError(new CancellationException("Single::takeUntil(Single) - Stream was canceled before emitting a terminal event."));
            }
        }

        public a(SingleSubscriber<? super T> singleSubscriber) {
            this.i = singleSubscriber;
            C0964a c0964a = new C0964a();
            this.k = c0964a;
            add(c0964a);
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

    public SingleTakeUntilSingle(Single.OnSubscribe<T> onSubscribe, Single<? extends U> single) {
        this.h = onSubscribe;
        this.i = single;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        a aVar = new a(singleSubscriber);
        singleSubscriber.add(aVar);
        this.i.subscribe((SingleSubscriber<? super Object>) aVar.k);
        this.h.call(aVar);
    }
}
