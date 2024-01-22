package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Notification;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subscribers.DisposableSubscriber;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Publisher;
/* loaded from: classes12.dex */
public final class BlockingFlowableNext<T> implements Iterable<T> {
    public final Publisher<? extends T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Iterator<T> {
        public final b<T> h;
        public final Publisher<? extends T> i;
        public T j;
        public boolean k = true;
        public boolean l = true;
        public Throwable m;
        public boolean n;

        public a(Publisher<? extends T> publisher, b<T> bVar) {
            this.i = publisher;
            this.h = bVar;
        }

        public final boolean a() {
            try {
                if (!this.n) {
                    this.n = true;
                    this.h.b();
                    Flowable.fromPublisher(this.i).materialize().subscribe((FlowableSubscriber<? super Notification<T>>) this.h);
                }
                Notification<T> c = this.h.c();
                if (c.isOnNext()) {
                    this.l = false;
                    this.j = c.getValue();
                    return true;
                }
                this.k = false;
                if (c.isOnComplete()) {
                    return false;
                }
                if (c.isOnError()) {
                    Throwable error = c.getError();
                    this.m = error;
                    throw ExceptionHelper.wrapOrThrow(error);
                }
                throw new IllegalStateException("Should not reach here");
            } catch (InterruptedException e) {
                this.h.dispose();
                this.m = e;
                throw ExceptionHelper.wrapOrThrow(e);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Throwable th = this.m;
            if (th == null) {
                if (this.k) {
                    return !this.l || a();
                }
                return false;
            }
            throw ExceptionHelper.wrapOrThrow(th);
        }

        @Override // java.util.Iterator
        public T next() {
            Throwable th = this.m;
            if (th == null) {
                if (hasNext()) {
                    this.l = true;
                    return this.j;
                }
                throw new NoSuchElementException("No more elements");
            }
            throw ExceptionHelper.wrapOrThrow(th);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read only iterator");
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends DisposableSubscriber<Notification<T>> {
        public final BlockingQueue<Notification<T>> i = new ArrayBlockingQueue(1);
        public final AtomicInteger j = new AtomicInteger();

        @Override // org.reactivestreams.Subscriber
        /* renamed from: a */
        public void onNext(Notification<T> notification) {
            if (this.j.getAndSet(0) == 1 || !notification.isOnNext()) {
                while (!this.i.offer(notification)) {
                    Notification<T> poll = this.i.poll();
                    if (poll != null && !poll.isOnNext()) {
                        notification = poll;
                    }
                }
            }
        }

        public void b() {
            this.j.set(1);
        }

        public Notification<T> c() throws InterruptedException {
            b();
            BlockingHelper.verifyNonBlocking();
            return this.i.take();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            RxJavaPlugins.onError(th);
        }
    }

    public BlockingFlowableNext(Publisher<? extends T> publisher) {
        this.h = publisher;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new a(this.h, new b());
    }
}
