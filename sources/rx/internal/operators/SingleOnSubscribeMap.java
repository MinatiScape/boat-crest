package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Func1;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class SingleOnSubscribeMap<T, R> implements Single.OnSubscribe<R> {
    public final Single<T> h;
    public final Func1<? super T, ? extends R> i;

    /* loaded from: classes13.dex */
    public static final class a<T, R> extends SingleSubscriber<T> {
        public final SingleSubscriber<? super R> i;
        public final Func1<? super T, ? extends R> j;
        public boolean k;

        public a(SingleSubscriber<? super R> singleSubscriber, Func1<? super T, ? extends R> func1) {
            this.i = singleSubscriber;
            this.j = func1;
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            if (this.k) {
                RxJavaHooks.onError(th);
                return;
            }
            this.k = true;
            this.i.onError(th);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            try {
                this.i.onSuccess(this.j.call(t));
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(OnErrorThrowable.addValueAsLastCause(th, t));
            }
        }
    }

    public SingleOnSubscribeMap(Single<T> single, Func1<? super T, ? extends R> func1) {
        this.h = single;
        this.i = func1;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super R> singleSubscriber) {
        a aVar = new a(singleSubscriber, this.i);
        singleSubscriber.add(aVar);
        this.h.subscribe(aVar);
    }
}
