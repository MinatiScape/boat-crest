package io.reactivex.rxjava3.internal.jdk8;

import java.util.NoSuchElementException;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableFirstStageSubscriber<T> extends a<T> {
    public final boolean j;
    public final T k;

    public FlowableFirstStageSubscriber(boolean z, T t) {
        this.j = z;
        this.k = t;
    }

    @Override // io.reactivex.rxjava3.internal.jdk8.a
    public void afterSubscribe(Subscription subscription) {
        subscription.request(1L);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (isDone()) {
            return;
        }
        clear();
        if (this.j) {
            complete(this.k);
        } else {
            completeExceptionally(new NoSuchElementException());
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        complete(t);
    }
}
