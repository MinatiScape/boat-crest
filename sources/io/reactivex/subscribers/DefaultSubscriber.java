package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public abstract class DefaultSubscriber<T> implements FlowableSubscriber<T> {
    public Subscription h;

    public final void cancel() {
        Subscription subscription = this.h;
        this.h = SubscriptionHelper.CANCELLED;
        subscription.cancel();
    }

    public void onStart() {
        request(Long.MAX_VALUE);
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public final void onSubscribe(Subscription subscription) {
        if (EndConsumerHelper.validate(this.h, subscription, getClass())) {
            this.h = subscription;
            onStart();
        }
    }

    public final void request(long j) {
        Subscription subscription = this.h;
        if (subscription != null) {
            subscription.request(j);
        }
    }
}
