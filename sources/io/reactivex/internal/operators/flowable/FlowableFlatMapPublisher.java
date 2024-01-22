package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableFlatMapPublisher<T, U> extends Flowable<U> {
    public final Publisher<T> i;
    public final Function<? super T, ? extends Publisher<? extends U>> j;
    public final boolean k;
    public final int l;
    public final int m;

    public FlowableFlatMapPublisher(Publisher<T> publisher, Function<? super T, ? extends Publisher<? extends U>> function, boolean z, int i, int i2) {
        this.i = publisher;
        this.j = function;
        this.k = z;
        this.l = i;
        this.m = i2;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super U> subscriber) {
        if (FlowableScalarXMap.tryScalarXMapSubscribe(this.i, subscriber, this.j)) {
            return;
        }
        this.i.subscribe(FlowableFlatMap.subscribe(subscriber, this.j, this.k, this.l, this.m));
    }
}
