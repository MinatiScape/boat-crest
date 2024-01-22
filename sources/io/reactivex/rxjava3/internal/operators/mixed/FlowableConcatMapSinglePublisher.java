package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.operators.mixed.FlowableConcatMapSingle;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableConcatMapSinglePublisher<T, R> extends Flowable<R> {
    public final Publisher<T> i;
    public final Function<? super T, ? extends SingleSource<? extends R>> j;
    public final ErrorMode k;
    public final int l;

    public FlowableConcatMapSinglePublisher(Publisher<T> publisher, Function<? super T, ? extends SingleSource<? extends R>> function, ErrorMode errorMode, int i) {
        this.i = publisher;
        this.j = function;
        this.k = errorMode;
        this.l = i;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        this.i.subscribe(new FlowableConcatMapSingle.a(subscriber, this.j, this.l, this.k));
    }
}
