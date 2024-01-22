package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.flowable.FlowableConcatMapEager;
import io.reactivex.internal.util.ErrorMode;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableConcatMapEagerPublisher<T, R> extends Flowable<R> {
    public final Publisher<T> i;
    public final Function<? super T, ? extends Publisher<? extends R>> j;
    public final int k;
    public final int l;
    public final ErrorMode m;

    public FlowableConcatMapEagerPublisher(Publisher<T> publisher, Function<? super T, ? extends Publisher<? extends R>> function, int i, int i2, ErrorMode errorMode) {
        this.i = publisher;
        this.j = function;
        this.k = i;
        this.l = i2;
        this.m = errorMode;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        this.i.subscribe(new FlowableConcatMapEager.a(subscriber, this.j, this.k, this.l, this.m));
    }
}
