package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class SerializedSubscriber<T> implements FlowableSubscriber<T>, Subscription {
    public final Subscriber<? super T> h;
    public final boolean i;
    public Subscription j;
    public boolean k;
    public AppendOnlyLinkedArrayList<Object> l;
    public volatile boolean m;

    public SerializedSubscriber(Subscriber<? super T> subscriber) {
        this(subscriber, false);
    }

    public void a() {
        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList;
        do {
            synchronized (this) {
                appendOnlyLinkedArrayList = this.l;
                if (appendOnlyLinkedArrayList == null) {
                    this.k = false;
                    return;
                }
                this.l = null;
            }
        } while (!appendOnlyLinkedArrayList.accept((Subscriber<? super T>) this.h));
    }

    @Override // org.reactivestreams.Subscription
    public void cancel() {
        this.j.cancel();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.m) {
            return;
        }
        synchronized (this) {
            if (this.m) {
                return;
            }
            if (this.k) {
                AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.l;
                if (appendOnlyLinkedArrayList == null) {
                    appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                    this.l = appendOnlyLinkedArrayList;
                }
                appendOnlyLinkedArrayList.add(NotificationLite.complete());
                return;
            }
            this.m = true;
            this.k = true;
            this.h.onComplete();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.m) {
            RxJavaPlugins.onError(th);
            return;
        }
        synchronized (this) {
            boolean z = true;
            if (!this.m) {
                if (this.k) {
                    this.m = true;
                    AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.l;
                    if (appendOnlyLinkedArrayList == null) {
                        appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                        this.l = appendOnlyLinkedArrayList;
                    }
                    Object error = NotificationLite.error(th);
                    if (this.i) {
                        appendOnlyLinkedArrayList.add(error);
                    } else {
                        appendOnlyLinkedArrayList.setFirst(error);
                    }
                    return;
                }
                this.m = true;
                this.k = true;
                z = false;
            }
            if (z) {
                RxJavaPlugins.onError(th);
            } else {
                this.h.onError(th);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        if (this.m) {
            return;
        }
        if (t == null) {
            this.j.cancel();
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return;
        }
        synchronized (this) {
            if (this.m) {
                return;
            }
            if (this.k) {
                AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.l;
                if (appendOnlyLinkedArrayList == null) {
                    appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                    this.l = appendOnlyLinkedArrayList;
                }
                appendOnlyLinkedArrayList.add(NotificationLite.next(t));
                return;
            }
            this.k = true;
            this.h.onNext(t);
            a();
        }
    }

    @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        if (SubscriptionHelper.validate(this.j, subscription)) {
            this.j = subscription;
            this.h.onSubscribe(this);
        }
    }

    @Override // org.reactivestreams.Subscription
    public void request(long j) {
        this.j.request(j);
    }

    public SerializedSubscriber(Subscriber<? super T> subscriber, boolean z) {
        this.h = subscriber;
        this.i = z;
    }
}
