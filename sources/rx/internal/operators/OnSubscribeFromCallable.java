package rx.internal.operators;

import java.util.concurrent.Callable;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.producers.SingleDelayedProducer;
/* loaded from: classes13.dex */
public final class OnSubscribeFromCallable<T> implements Observable.OnSubscribe<T> {
    public final Callable<? extends T> h;

    public OnSubscribeFromCallable(Callable<? extends T> callable) {
        this.h = callable;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        SingleDelayedProducer singleDelayedProducer = new SingleDelayedProducer(subscriber);
        subscriber.setProducer(singleDelayedProducer);
        try {
            singleDelayedProducer.setValue(this.h.call());
        } catch (Throwable th) {
            Exceptions.throwOrReport(th, subscriber);
        }
    }
}
