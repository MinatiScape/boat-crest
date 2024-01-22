package io.reactivex.internal.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class SubscriberCompletableObserver<T> implements CompletableObserver, Subscription {
    public final Subscriber<? super T> h;
    public Disposable i;

    public SubscriberCompletableObserver(Subscriber<? super T> subscriber) {
        this.h = subscriber;
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.i.dispose();
    }

    @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
    public void onComplete() {
        this.h.onComplete();
    }

    @Override // io.reactivex.CompletableObserver
    public void onError(Throwable th) {
        this.h.onError(th);
    }

    @Override // io.reactivex.CompletableObserver
    public void onSubscribe(Disposable disposable) {
        if (DisposableHelper.validate(this.i, disposable)) {
            this.i = disposable;
            this.h.onSubscribe(this);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j) {
    }
}
