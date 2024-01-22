package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
/* loaded from: classes13.dex */
public final class BlockingOperatorLatest {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class a<T> implements Iterable<T> {
        public final /* synthetic */ Observable h;

        public a(Observable observable) {
            this.h = observable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            b bVar = new b();
            this.h.materialize().subscribe((Subscriber<? super Notification<T>>) bVar);
            return bVar;
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<Notification<? extends T>> implements Iterator<T> {
        public final Semaphore l = new Semaphore(0);
        public final AtomicReference<Notification<? extends T>> m = new AtomicReference<>();
        public Notification<? extends T> n;

        @Override // java.util.Iterator
        public boolean hasNext() {
            Notification<? extends T> notification = this.n;
            if (notification != null && notification.isOnError()) {
                throw Exceptions.propagate(this.n.getThrowable());
            }
            Notification<? extends T> notification2 = this.n;
            if ((notification2 == null || !notification2.isOnCompleted()) && this.n == null) {
                try {
                    this.l.acquire();
                    Notification<? extends T> andSet = this.m.getAndSet(null);
                    this.n = andSet;
                    if (andSet.isOnError()) {
                        throw Exceptions.propagate(this.n.getThrowable());
                    }
                } catch (InterruptedException e) {
                    unsubscribe();
                    Thread.currentThread().interrupt();
                    this.n = Notification.createOnError(e);
                    throw Exceptions.propagate(e);
                }
            }
            return !this.n.isOnCompleted();
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext() && this.n.isOnNext()) {
                T value = this.n.getValue();
                this.n = null;
                return value;
            }
            throw new NoSuchElementException();
        }

        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
        }

        @Override // rx.Observer
        public /* bridge */ /* synthetic */ void onNext(Object obj) {
            onNext((Notification) ((Notification) obj));
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }

        public void onNext(Notification<? extends T> notification) {
            if (this.m.getAndSet(notification) == null) {
                this.l.release();
            }
        }
    }

    public BlockingOperatorLatest() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> latest(Observable<? extends T> observable) {
        return new a(observable);
    }
}
