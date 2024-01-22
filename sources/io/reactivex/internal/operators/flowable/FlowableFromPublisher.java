package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableFromPublisher<T> extends Flowable<T> {
    public final Publisher<? extends T> i;

    public FlowableFromPublisher(Publisher<? extends T> publisher) {
        this.i = publisher;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.i.subscribe(subscriber);
    }
}
