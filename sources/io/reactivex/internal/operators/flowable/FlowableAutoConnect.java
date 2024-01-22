package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableAutoConnect<T> extends Flowable<T> {
    public final ConnectableFlowable<? extends T> i;
    public final int j;
    public final Consumer<? super Disposable> k;
    public final AtomicInteger l = new AtomicInteger();

    public FlowableAutoConnect(ConnectableFlowable<? extends T> connectableFlowable, int i, Consumer<? super Disposable> consumer) {
        this.i = connectableFlowable;
        this.j = i;
        this.k = consumer;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.i.subscribe((Subscriber<? super Object>) subscriber);
        if (this.l.incrementAndGet() == this.j) {
            this.i.connect(this.k);
        }
    }
}
