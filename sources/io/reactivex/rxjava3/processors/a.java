package io.reactivex.rxjava3.processors;

import io.reactivex.rxjava3.annotations.Nullable;
import io.reactivex.rxjava3.internal.util.AppendOnlyLinkedArrayList;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class a<T> extends FlowableProcessor<T> {
    public final FlowableProcessor<T> i;
    public boolean j;
    public AppendOnlyLinkedArrayList<Object> k;
    public volatile boolean l;

    public a(FlowableProcessor<T> flowableProcessor) {
        this.i = flowableProcessor;
    }

    public void e() {
        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList;
        while (true) {
            synchronized (this) {
                appendOnlyLinkedArrayList = this.k;
                if (appendOnlyLinkedArrayList == null) {
                    this.j = false;
                    return;
                }
                this.k = null;
            }
            appendOnlyLinkedArrayList.accept(this.i);
        }
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    @Nullable
    public Throwable getThrowable() {
        return this.i.getThrowable();
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    public boolean hasComplete() {
        return this.i.hasComplete();
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    public boolean hasSubscribers() {
        return this.i.hasSubscribers();
    }

    @Override // io.reactivex.rxjava3.processors.FlowableProcessor
    public boolean hasThrowable() {
        return this.i.hasThrowable();
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        if (this.l) {
            return;
        }
        synchronized (this) {
            if (this.l) {
                return;
            }
            this.l = true;
            if (this.j) {
                AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.k;
                if (appendOnlyLinkedArrayList == null) {
                    appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                    this.k = appendOnlyLinkedArrayList;
                }
                appendOnlyLinkedArrayList.add(NotificationLite.complete());
                return;
            }
            this.j = true;
            this.i.onComplete();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.l) {
            RxJavaPlugins.onError(th);
            return;
        }
        synchronized (this) {
            boolean z = true;
            if (!this.l) {
                this.l = true;
                if (this.j) {
                    AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.k;
                    if (appendOnlyLinkedArrayList == null) {
                        appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                        this.k = appendOnlyLinkedArrayList;
                    }
                    appendOnlyLinkedArrayList.setFirst(NotificationLite.error(th));
                    return;
                }
                this.j = true;
                z = false;
            }
            if (z) {
                RxJavaPlugins.onError(th);
            } else {
                this.i.onError(th);
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        if (this.l) {
            return;
        }
        synchronized (this) {
            if (this.l) {
                return;
            }
            if (this.j) {
                AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.k;
                if (appendOnlyLinkedArrayList == null) {
                    appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                    this.k = appendOnlyLinkedArrayList;
                }
                appendOnlyLinkedArrayList.add(NotificationLite.next(t));
                return;
            }
            this.j = true;
            this.i.onNext(t);
            e();
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        boolean z = true;
        if (!this.l) {
            synchronized (this) {
                if (!this.l) {
                    if (this.j) {
                        AppendOnlyLinkedArrayList<Object> appendOnlyLinkedArrayList = this.k;
                        if (appendOnlyLinkedArrayList == null) {
                            appendOnlyLinkedArrayList = new AppendOnlyLinkedArrayList<>(4);
                            this.k = appendOnlyLinkedArrayList;
                        }
                        appendOnlyLinkedArrayList.add(NotificationLite.subscription(subscription));
                        return;
                    }
                    this.j = true;
                    z = false;
                }
            }
        }
        if (z) {
            subscription.cancel();
            return;
        }
        this.i.onSubscribe(subscription);
        e();
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.i.subscribe(subscriber);
    }
}
