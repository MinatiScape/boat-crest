package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.internal.util.RxRingBuffer;
/* loaded from: classes13.dex */
public final class BlockingOperatorToIterator {

    /* loaded from: classes13.dex */
    public static final class SubscriberIterator<T> extends Subscriber<Notification<? extends T>> implements Iterator<T> {
        public static final int o = (RxRingBuffer.SIZE * 3) / 4;
        public final BlockingQueue<Notification<? extends T>> l = new LinkedBlockingQueue();
        public Notification<? extends T> m;
        public int n;

        public final Notification<? extends T> b() {
            try {
                Notification<? extends T> poll = this.l.poll();
                return poll != null ? poll : this.l.take();
            } catch (InterruptedException e) {
                unsubscribe();
                throw Exceptions.propagate(e);
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.m == null) {
                this.m = b();
                int i = this.n + 1;
                this.n = i;
                if (i >= o) {
                    request(i);
                    this.n = 0;
                }
            }
            if (!this.m.isOnError()) {
                return !this.m.isOnCompleted();
            }
            throw Exceptions.propagate(this.m.getThrowable());
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T value = this.m.getValue();
                this.m = null;
                return value;
            }
            throw new NoSuchElementException();
        }

        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l.offer(Notification.createOnError(th));
        }

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Notification) ((Notification) obj));
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void onStart() {
            request(RxRingBuffer.SIZE);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator");
        }

        public void onNext(Notification<? extends T> notification) {
            this.l.offer(notification);
        }
    }

    public BlockingOperatorToIterator() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterator<T> toIterator(Observable<? extends T> observable) {
        SubscriberIterator subscriberIterator = new SubscriberIterator();
        observable.materialize().subscribe((Subscriber<? super Notification<? extends T>>) subscriberIterator);
        return subscriberIterator;
    }
}
