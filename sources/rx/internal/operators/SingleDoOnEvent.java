package rx.internal.operators;

import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
/* loaded from: classes13.dex */
public final class SingleDoOnEvent<T> implements Single.OnSubscribe<T> {
    public final Single<T> h;
    public final Action1<? super T> i;
    public final Action1<Throwable> j;

    /* loaded from: classes13.dex */
    public static final class a<T> extends SingleSubscriber<T> {
        public final SingleSubscriber<? super T> i;
        public final Action1<? super T> j;
        public final Action1<Throwable> k;

        public a(SingleSubscriber<? super T> singleSubscriber, Action1<? super T> action1, Action1<Throwable> action12) {
            this.i = singleSubscriber;
            this.j = action1;
            this.k = action12;
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            try {
                this.k.call(th);
                this.i.onError(th);
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                this.i.onError(new CompositeException(th, th2));
            }
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            try {
                this.j.call(t);
                this.i.onSuccess(t);
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, this, t);
            }
        }
    }

    public SingleDoOnEvent(Single<T> single, Action1<? super T> action1, Action1<Throwable> action12) {
        this.h = single;
        this.i = action1;
        this.j = action12;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        a aVar = new a(singleSubscriber, this.i, this.j);
        singleSubscriber.add(aVar);
        this.h.subscribe(aVar);
    }
}
