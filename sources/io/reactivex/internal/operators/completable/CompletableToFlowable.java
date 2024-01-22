package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.internal.observers.SubscriberCompletableObserver;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class CompletableToFlowable<T> extends Flowable<T> {
    public final CompletableSource i;

    public CompletableToFlowable(CompletableSource completableSource) {
        this.i = completableSource;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.i.subscribe(new SubscriberCompletableObserver(subscriber));
    }
}
