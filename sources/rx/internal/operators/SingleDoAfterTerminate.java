package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class SingleDoAfterTerminate<T> implements Single.OnSubscribe<T> {
    public final Single<T> h;
    public final Action0 i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends SingleSubscriber<T> {
        public final SingleSubscriber<? super T> i;
        public final Action0 j;

        public a(SingleSubscriber<? super T> singleSubscriber, Action0 action0) {
            this.i = singleSubscriber;
            this.j = action0;
        }

        public void a() {
            try {
                this.j.call();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                RxJavaHooks.onError(th);
            }
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            try {
                this.i.onError(th);
            } finally {
                a();
            }
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            try {
                this.i.onSuccess(t);
            } finally {
                a();
            }
        }
    }

    public SingleDoAfterTerminate(Single<T> single, Action0 action0) {
        this.h = single;
        this.i = action0;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        a aVar = new a(singleSubscriber, this.i);
        singleSubscriber.add(aVar);
        this.h.subscribe(aVar);
    }
}
