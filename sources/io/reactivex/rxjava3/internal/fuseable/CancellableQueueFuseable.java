package io.reactivex.rxjava3.internal.fuseable;
/* loaded from: classes12.dex */
public final class CancellableQueueFuseable<T> extends AbstractEmptyQueueFuseable<T> {
    public volatile boolean h;

    @Override // io.reactivex.rxjava3.internal.fuseable.AbstractEmptyQueueFuseable, org.reactivestreams.Subscription
    public void cancel() {
        this.h = true;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.AbstractEmptyQueueFuseable, io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.h = true;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.AbstractEmptyQueueFuseable, io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.h;
    }
}
