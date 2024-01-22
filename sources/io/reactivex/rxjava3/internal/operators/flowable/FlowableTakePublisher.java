package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableTake;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableTakePublisher<T> extends Flowable<T> {
    public final Publisher<T> i;
    public final long j;

    public FlowableTakePublisher(Publisher<T> publisher, long j) {
        this.i = publisher;
        this.j = j;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.i.subscribe(new FlowableTake.a(subscriber, this.j));
    }
}
