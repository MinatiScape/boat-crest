package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.subscribers.SerializedSubscriber;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableSerialized<T> extends a<T, T> {
    public FlowableSerialized(Flowable<T> flowable) {
        super(flowable);
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new SerializedSubscriber(subscriber));
    }
}
