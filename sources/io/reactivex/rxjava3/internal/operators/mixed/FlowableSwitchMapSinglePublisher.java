package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.operators.mixed.FlowableSwitchMapSingle;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableSwitchMapSinglePublisher<T, R> extends Flowable<R> {
    public final Publisher<T> i;
    public final Function<? super T, ? extends SingleSource<? extends R>> j;
    public final boolean k;

    public FlowableSwitchMapSinglePublisher(Publisher<T> publisher, Function<? super T, ? extends SingleSource<? extends R>> function, boolean z) {
        this.i = publisher;
        this.j = function;
        this.k = z;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super R> subscriber) {
        this.i.subscribe(new FlowableSwitchMapSingle.a(subscriber, this.j, this.k));
    }
}
