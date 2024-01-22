package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.functions.Consumer;
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

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.i.subscribe((Subscriber<? super Object>) subscriber);
        if (this.l.incrementAndGet() == this.j) {
            this.i.connect(this.k);
        }
    }
}
