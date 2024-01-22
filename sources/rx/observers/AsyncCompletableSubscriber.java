package rx.observers;

import java.util.concurrent.atomic.AtomicReference;
import rx.CompletableSubscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public abstract class AsyncCompletableSubscriber implements CompletableSubscriber, Subscription {
    public static final a i = new a();
    public final AtomicReference<Subscription> h = new AtomicReference<>();

    /* loaded from: classes13.dex */
    public static final class a implements Subscription {
        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return true;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
        }
    }

    public final void clear() {
        this.h.set(i);
    }

    @Override // rx.Subscription
    public final boolean isUnsubscribed() {
        return this.h.get() == i;
    }

    public void onStart() {
    }

    @Override // rx.CompletableSubscriber
    public final void onSubscribe(Subscription subscription) {
        if (!this.h.compareAndSet(null, subscription)) {
            subscription.unsubscribe();
            if (this.h.get() != i) {
                RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
                return;
            }
            return;
        }
        onStart();
    }

    @Override // rx.Subscription
    public final void unsubscribe() {
        Subscription andSet;
        Subscription subscription = this.h.get();
        a aVar = i;
        if (subscription == aVar || (andSet = this.h.getAndSet(aVar)) == null || andSet == aVar) {
            return;
        }
        andSet.unsubscribe();
    }
}
