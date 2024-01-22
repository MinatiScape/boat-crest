package io.reactivex.internal.operators.observable;

import io.reactivex.Notification;
import io.reactivex.ObservableSource;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes12.dex */
public final class BlockingObservableNext<T> implements Iterable<T> {
    public final ObservableSource<T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> implements Iterator<T> {
        public final b<T> h;
        public final ObservableSource<T> i;
        public T j;
        public boolean k = true;
        public boolean l = true;
        public Throwable m;
        public boolean n;

        public a(ObservableSource<T> observableSource, b<T> bVar) {
            this.i = observableSource;
            this.h = bVar;
        }

        public final boolean a() {
            if (!this.n) {
                this.n = true;
                this.h.b();
                new ObservableMaterialize(this.i).subscribe(this.h);
            }
            try {
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
                Throwable error = c.getError();
                this.m = error;
                throw ExceptionHelper.wrapOrThrow(error);
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
    public static final class b<T> extends DisposableObserver<Notification<T>> {
        public final BlockingQueue<Notification<T>> i = new ArrayBlockingQueue(1);
        public final AtomicInteger j = new AtomicInteger();

        @Override // io.reactivex.Observer
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

        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            RxJavaPlugins.onError(th);
        }
    }

    public BlockingObservableNext(ObservableSource<T> observableSource) {
        this.h = observableSource;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return new a(this.h, new b());
    }
}
