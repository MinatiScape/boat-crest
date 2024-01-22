package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.subscribers.DefaultSubscriber;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes12.dex */
public final class BlockingFlowableMostRecent<T> implements Iterable<T> {
    public final Flowable<T> h;
    public final T i;

    /* loaded from: classes12.dex */
    public static final class a<T> extends DefaultSubscriber<T> {
        public volatile Object i;

        /* renamed from: io.reactivex.internal.operators.flowable.BlockingFlowableMostRecent$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class C0740a implements Iterator<T> {
            public Object h;

            public C0740a() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                Object obj = a.this.i;
                this.h = obj;
                return !NotificationLite.isComplete(obj);
            }

            @Override // java.util.Iterator
            public T next() {
                try {
                    if (this.h == null) {
                        this.h = a.this.i;
                    }
                    if (!NotificationLite.isComplete(this.h)) {
                        if (!NotificationLite.isError(this.h)) {
                            return (T) NotificationLite.getValue(this.h);
                        }
                        throw ExceptionHelper.wrapOrThrow(NotificationLite.getError(this.h));
                    }
                    throw new NoSuchElementException();
                } finally {
                    this.h = null;
                }
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Read only iterator");
            }
        }

        public a(T t) {
            this.i = NotificationLite.next(t);
        }

        public a<T>.C0740a a() {
            return new C0740a();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.i = NotificationLite.complete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.i = NotificationLite.error(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.i = NotificationLite.next(t);
        }
    }

    public BlockingFlowableMostRecent(Flowable<T> flowable, T t) {
        this.h = flowable;
        this.i = t;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        a aVar = new a(this.i);
        this.h.subscribe((FlowableSubscriber) aVar);
        return aVar.a();
    }
}
