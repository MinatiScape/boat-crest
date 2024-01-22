package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableElementAtMaybe;
import org.reactivestreams.Publisher;
/* loaded from: classes12.dex */
public final class FlowableElementAtMaybePublisher<T> extends Maybe<T> {
    public final Publisher<T> h;
    public final long i;

    public FlowableElementAtMaybePublisher(Publisher<T> publisher, long j) {
        this.h = publisher;
        this.i = j;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        this.h.subscribe(new FlowableElementAtMaybe.a(maybeObserver, this.i));
    }
}
