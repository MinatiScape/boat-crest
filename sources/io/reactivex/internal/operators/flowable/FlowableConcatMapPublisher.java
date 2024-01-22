package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.ErrorMode;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableConcatMapPublisher<T, R> extends Flowable<R> {
    public final Publisher<T> i;
    public final Function<? super T, ? extends Publisher<? extends R>> j;
    public final int k;
    public final ErrorMode l;

    public FlowableConcatMapPublisher(Publisher<T> publisher, Function<? super T, ? extends Publisher<? extends R>> function, int i, ErrorMode errorMode) {
        this.i = publisher;
        this.j = function;
        this.k = i;
        this.l = errorMode;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        if (FlowableScalarXMap.tryScalarXMapSubscribe(this.i, subscriber, this.j)) {
            return;
        }
        this.i.subscribe(FlowableConcatMap.subscribe(subscriber, this.j, this.k, this.l));
    }
}
