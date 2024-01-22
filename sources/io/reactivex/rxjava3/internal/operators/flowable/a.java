package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.fuseable.HasUpstreamPublisher;
import java.util.Objects;
import org.reactivestreams.Publisher;
/* loaded from: classes12.dex */
public abstract class a<T, R> extends Flowable<R> implements HasUpstreamPublisher<T> {
    public final Flowable<T> source;

    public a(Flowable<T> flowable) {
        Objects.requireNonNull(flowable, "source is null");
        this.source = flowable;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.HasUpstreamPublisher
    public final Publisher<T> source() {
        return this.source;
    }
}
