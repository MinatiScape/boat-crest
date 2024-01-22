package io.reactivex.rxjava3.internal.jdk8;

import java.util.NoSuchElementException;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableSingleStageSubscriber<T> extends a<T> {
    public final boolean j;
    public final T k;

    public FlowableSingleStageSubscriber(boolean z, T t) {
        this.j = z;
        this.k = t;
    }

    @Override // io.reactivex.rxjava3.internal.jdk8.a
    public void afterSubscribe(Subscription subscription) {
        subscription.request(2L);
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (isDone()) {
            return;
        }
        T t = this.i;
        clear();
        if (t != null) {
            complete(t);
        } else if (this.j) {
            complete(this.k);
        } else {
            completeExceptionally(new NoSuchElementException());
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        if (this.i != null) {
            this.i = null;
            completeExceptionally(new IllegalArgumentException("Sequence contains more than one element!"));
            return;
        }
        this.i = t;
    }
}
