package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public final class SingleOnErrorReturn<T> implements Single.OnSubscribe<T> {
    public final Single.OnSubscribe<T> h;
    public final Func1<Throwable, ? extends T> i;

    /* loaded from: classes13.dex */
    public static final class a<T> extends SingleSubscriber<T> {
        public final SingleSubscriber<? super T> i;
        public final Func1<Throwable, ? extends T> j;

        public a(SingleSubscriber<? super T> singleSubscriber, Func1<Throwable, ? extends T> func1) {
            this.i = singleSubscriber;
            this.j = func1;
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            try {
                this.i.onSuccess(this.j.call(th));
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.i.onError(th2);
            }
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.i.onSuccess(t);
        }
    }

    public SingleOnErrorReturn(Single.OnSubscribe<T> onSubscribe, Func1<Throwable, ? extends T> func1) {
        this.h = onSubscribe;
        this.i = func1;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        a aVar = new a(singleSubscriber, this.i);
        singleSubscriber.add(aVar);
        this.h.call(aVar);
    }
}
