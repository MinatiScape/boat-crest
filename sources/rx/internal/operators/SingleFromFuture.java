package rx.internal.operators;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.Single;
import rx.SingleSubscriber;
import rx.exceptions.Exceptions;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class SingleFromFuture<T> implements Single.OnSubscribe<T> {
    public final Future<? extends T> h;
    public final long i;
    public final TimeUnit j;

    public SingleFromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        this.h = future;
        this.i = j;
        this.j = timeUnit;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((SingleSubscriber) ((SingleSubscriber) obj));
    }

    public void call(SingleSubscriber<? super T> singleSubscriber) {
        Object obj;
        Future<? extends T> future = this.h;
        singleSubscriber.add(Subscriptions.from(future));
        try {
            long j = this.i;
            if (j == 0) {
                obj = (T) future.get();
            } else {
                obj = (T) future.get(j, this.j);
            }
            singleSubscriber.onSuccess(obj);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            singleSubscriber.onError(th);
        }
    }
}
