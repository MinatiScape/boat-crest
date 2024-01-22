package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Notification;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
/* loaded from: classes12.dex */
public final class FlowableMaterialize<T> extends io.reactivex.internal.operators.flowable.a<T, Notification<T>> {

    /* loaded from: classes12.dex */
    public static final class a<T> extends SinglePostCompleteSubscriber<T, Notification<T>> {
        private static final long serialVersionUID = -3740826063558713822L;

        public a(Subscriber<? super Notification<T>> subscriber) {
            super(subscriber);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            complete(Notification.createOnComplete());
        }

        @Override // io.reactivex.internal.subscribers.SinglePostCompleteSubscriber
        public /* bridge */ /* synthetic */ void onDrop(Object obj) {
            onDrop((Notification) ((Notification) obj));
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            complete(Notification.createOnError(th));
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(Notification.createOnNext(t));
        }

        public void onDrop(Notification<T> notification) {
            if (notification.isOnError()) {
                RxJavaPlugins.onError(notification.getError());
            }
        }
    }

    public FlowableMaterialize(Flowable<T> flowable) {
        super(flowable);
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super Notification<T>> subscriber) {
        this.source.subscribe((FlowableSubscriber) new a(subscriber));
    }
}