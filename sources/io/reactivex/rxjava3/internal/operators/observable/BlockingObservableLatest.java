package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Notification;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.internal.util.BlockingHelper;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes12.dex */
public final class BlockingObservableLatest<T> implements Iterable<T> {
    public final ObservableSource<T> h;

    /* loaded from: classes12.dex */
    public static final class a<T> extends DisposableObserver<Notification<T>> implements Iterator<T> {
        public Notification<T> i;
        public final Semaphore j = new Semaphore(0);
        public final AtomicReference<Notification<T>> k = new AtomicReference<>();

        @Override // io.reactivex.rxjava3.core.Observer
        /* renamed from: a */
        public void onNext(Notification<T> notification) {
            if (this.k.getAndSet(notification) == null) {
                this.j.release();
            }
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            Notification<T> notification = this.i;
            if (notification != null && notification.isOnError()) {
                throw ExceptionHelper.wrapOrThrow(this.i.getError());
            }
            if (this.i == null) {
                try {
                    BlockingHelper.verifyNonBlocking();
                    this.j.acquire();
                    Notification<T> andSet = this.k.getAndSet(null);
                    this.i = andSet;
                    if (andSet.isOnError()) {
                        throw ExceptionHelper.wrapOrThrow(andSet.getError());
                    }
                } catch (InterruptedException e) {
                    dispose();
                    this.i = Notification.createOnError(e);
                    throw ExceptionHelper.wrapOrThrow(e);
                }
            }
            return this.i.isOnNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (hasNext()) {
                T value = this.i.getValue();
                this.i = null;
                return value;
            }
            throw new NoSuchElementException();
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.rxjava3.core.Observer
        public void onError(Throwable th) {
            RxJavaPlugins.onError(th);
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException("Read-only iterator.");
        }
    }

    public BlockingObservableLatest(ObservableSource<T> observableSource) {
        this.h = observableSource;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        a aVar = new a();
        Observable.wrap(this.h).materialize().subscribe(aVar);
        return aVar;
    }
}
