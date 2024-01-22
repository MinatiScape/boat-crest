package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.internal.util.BlockingHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
/* loaded from: classes12.dex */
public final class BlockingFlowableLatest<T> implements Iterable<T> {
    public final Publisher<? extends T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> extends DisposableSubscriber<Notification<T>> implements Iterator<T> {
        public final Semaphore i = new Semaphore(0);
        public final AtomicReference<Notification<T>> j = new AtomicReference<>();
        public Notification<T> k;

        @Override // org.reactivestreams.Subscriber
        /* renamed from: a */
        public void onNext(Notification<T> notification) {
            if (this.j.getAndSet(notification) == null) {
                this.i.release();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Notification<T> notification = this.k;
            if (notification != null && notification.isOnError()) {
                throw ExceptionHelper.wrapOrThrow(this.k.getError());
            }
            Notification<T> notification2 = this.k;
            if ((notification2 == null || notification2.isOnNext()) && this.k == null) {
                try {
                    BlockingHelper.verifyNonBlocking();
                    this.i.acquire();
                    Notification<T> andSet = this.j.getAndSet(null);
                    this.k = andSet;
                    if (andSet.isOnError()) {
                        throw ExceptionHelper.wrapOrThrow(andSet.getError());
                    }
                } catch (InterruptedException e) {
                    dispose();
                    this.k = Notification.createOnError(e);
                    throw ExceptionHelper.wrapOrThrow(e);
                }
            }
            return this.k.isOnNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext() && this.k.isOnNext()) {
                T value = this.k.getValue();
                this.k = null;
                return value;
            }
            throw new NoSuchElementException();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            RxJavaPlugins.onError(th);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }

    public BlockingFlowableLatest(Publisher<? extends T> publisher) {
        this.h = publisher;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        a aVar = new a();
        Flowable.fromPublisher(this.h).materialize().subscribe((FlowableSubscriber<? super Notification<T>>) aVar);
        return aVar;
    }
}
