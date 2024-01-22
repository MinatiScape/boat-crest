package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.flowable.FlowableMap;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableMapPublisher<T, U> extends Flowable<U> {
    public final Publisher<T> i;
    public final Function<? super T, ? extends U> j;

    public FlowableMapPublisher(Publisher<T> publisher, Function<? super T, ? extends U> function) {
        this.i = publisher;
        this.j = function;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super U> subscriber) {
        this.i.subscribe(new FlowableMap.b(subscriber, this.j));
    }
}
