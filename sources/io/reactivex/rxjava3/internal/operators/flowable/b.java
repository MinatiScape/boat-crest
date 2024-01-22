package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.processors.FlowableProcessor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class b<T> extends Flowable<T> {
    public final FlowableProcessor<T> i;
    public final AtomicBoolean j = new AtomicBoolean();

    public b(FlowableProcessor<T> flowableProcessor) {
        this.i = flowableProcessor;
    }

    public boolean e() {
        return !this.j.get() && this.j.compareAndSet(false, true);
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.i.subscribe(subscriber);
        this.j.set(true);
    }
}
