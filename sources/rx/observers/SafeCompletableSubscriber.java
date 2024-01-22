package rx.observers;

import rx.CompletableSubscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.exceptions.OnCompletedFailedException;
import rx.exceptions.OnErrorFailedException;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class SafeCompletableSubscriber implements CompletableSubscriber, Subscription {
    public final CompletableSubscriber h;
    public Subscription i;
    public boolean j;

    public SafeCompletableSubscriber(CompletableSubscriber completableSubscriber) {
        this.h = completableSubscriber;
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.j || this.i.isUnsubscribed();
    }

    @Override // rx.CompletableSubscriber
    public void onCompleted() {
        if (this.j) {
            return;
        }
        this.j = true;
        try {
            this.h.onCompleted();
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            throw new OnCompletedFailedException(th);
        }
    }

    @Override // rx.CompletableSubscriber
    public void onError(Throwable th) {
        RxJavaHooks.onError(th);
        if (this.j) {
            return;
        }
        this.j = true;
        try {
            this.h.onError(th);
        } catch (Throwable th2) {
            Exceptions.throwIfFatal(th2);
            throw new OnErrorFailedException(new CompositeException(th, th2));
        }
    }

    @Override // rx.CompletableSubscriber
    public void onSubscribe(Subscription subscription) {
        this.i = subscription;
        try {
            this.h.onSubscribe(this);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            subscription.unsubscribe();
            onError(th);
        }
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.i.unsubscribe();
    }
}
