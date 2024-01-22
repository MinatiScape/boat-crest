package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
/* loaded from: classes13.dex */
public final class BlockingOperatorNext {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class a<T> implements Iterable<T> {
        public final /* synthetic */ Observable h;

        public a(Observable observable) {
            this.h = observable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return new b(this.h, new c());
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> implements Iterator<T> {
        public final c<T> h;
        public final Observable<? extends T> i;
        public T j;
        public boolean k = true;
        public boolean l = true;
        public Throwable m;
        public boolean n;

        public b(Observable<? extends T> observable, c<T> cVar) {
            this.i = observable;
            this.h = cVar;
        }

        public final boolean a() {
            try {
                if (!this.n) {
                    this.n = true;
                    this.h.b(1);
                    this.i.materialize().subscribe((Subscriber<? super Notification<? extends T>>) this.h);
                }
                Notification<? extends T> c = this.h.c();
                if (c.isOnNext()) {
                    this.l = false;
                    this.j = c.getValue();
                    return true;
                }
                this.k = false;
                if (c.isOnCompleted()) {
                    return false;
                }
                if (c.isOnError()) {
                    Throwable throwable = c.getThrowable();
                    this.m = throwable;
                    throw Exceptions.propagate(throwable);
                }
                throw new IllegalStateException("Should not reach here");
            } catch (InterruptedException e) {
                this.h.unsubscribe();
                Thread.currentThread().interrupt();
                this.m = e;
                throw Exceptions.propagate(e);
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
            throw Exceptions.propagate(th);
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
            throw Exceptions.propagate(th);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read only iterator");
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends Subscriber<Notification<? extends T>> {
        public final BlockingQueue<Notification<? extends T>> l = new ArrayBlockingQueue(1);
        public final AtomicInteger m = new AtomicInteger();

        public void b(int i) {
            this.m.set(i);
        }

        public Notification<? extends T> c() throws InterruptedException {
            b(1);
            return this.l.take();
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

        public void onNext(Notification<? extends T> notification) {
            if (this.m.getAndSet(0) == 1 || !notification.isOnNext()) {
                while (!this.l.offer(notification)) {
                    Notification<? extends T> poll = this.l.poll();
                    if (poll != null && !poll.isOnNext()) {
                        notification = poll;
                    }
                }
            }
        }
    }

    public BlockingOperatorNext() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> next(Observable<? extends T> observable) {
        return new a(observable);
    }
}
