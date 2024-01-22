package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableFromCompletable;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class CompletableToFlowable<T> extends Flowable<T> {
    public final CompletableSource i;

    public CompletableToFlowable(CompletableSource completableSource) {
        this.i = completableSource;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.i.subscribe(new FlowableFromCompletable.FromCompletableObserver(subscriber));
    }
}
