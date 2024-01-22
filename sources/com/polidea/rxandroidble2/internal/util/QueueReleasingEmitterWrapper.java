package com.polidea.rxandroidble2.internal.util;

import com.polidea.rxandroidble2.internal.serialization.QueueReleaseInterface;
import io.reactivex.ObservableEmitter;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes12.dex */
public class QueueReleasingEmitterWrapper<T> implements Observer<T>, Cancellable {
    public final AtomicBoolean h = new AtomicBoolean(false);
    public final ObservableEmitter<T> i;
    public final QueueReleaseInterface j;

    public QueueReleasingEmitterWrapper(ObservableEmitter<T> observableEmitter, QueueReleaseInterface queueReleaseInterface) {
        this.i = observableEmitter;
        this.j = queueReleaseInterface;
        observableEmitter.setCancellable(this);
    }

    @Override // io.reactivex.functions.Cancellable
    public synchronized void cancel() {
        this.h.set(true);
    }

    public synchronized boolean isWrappedEmitterUnsubscribed() {
        return this.h.get();
    }

    @Override // io.reactivex.Observer
    public void onComplete() {
        this.j.release();
        this.i.onComplete();
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        this.j.release();
        this.i.tryOnError(th);
    }

    @Override // io.reactivex.Observer
    public void onNext(T t) {
        this.i.onNext(t);
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
    }
}
