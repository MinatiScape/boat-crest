package io.reactivex.internal.subscribers;

import io.reactivex.plugins.RxJavaPlugins;
/* loaded from: classes12.dex */
public final class BlockingFirstSubscriber<T> extends BlockingBaseSubscriber<T> {
    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.h == null) {
            this.i = th;
        } else {
            RxJavaPlugins.onError(th);
        }
        countDown();
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        if (this.h == null) {
            this.h = t;
            this.j.cancel();
            countDown();
        }
    }
}
