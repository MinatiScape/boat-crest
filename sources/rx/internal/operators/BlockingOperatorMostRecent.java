package rx.internal.operators;

import java.util.Iterator;
import java.util.NoSuchElementException;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
/* loaded from: classes13.dex */
public final class BlockingOperatorMostRecent {

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public static class a<T> implements Iterable<T> {
        public final /* synthetic */ Object h;
        public final /* synthetic */ Observable i;

        public a(Object obj, Observable observable) {
            this.h = obj;
            this.i = observable;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            b bVar = new b(this.h);
            this.i.subscribe((Subscriber) bVar);
            return bVar.b();
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> {
        public volatile Object l;

        /* loaded from: classes13.dex */
        public class a implements Iterator<T> {
            public Object h;

            public a() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                Object obj = b.this.l;
                this.h = obj;
                return !NotificationLite.isCompleted(obj);
            }

            @Override // java.util.Iterator
            public T next() {
                try {
                    if (this.h == null) {
                        this.h = b.this.l;
                    }
                    if (!NotificationLite.isCompleted(this.h)) {
                        if (!NotificationLite.isError(this.h)) {
                            return (T) NotificationLite.getValue(this.h);
                        }
                        throw Exceptions.propagate(NotificationLite.getError(this.h));
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

        public b(T t) {
            this.l = NotificationLite.next(t);
        }

        public Iterator<T> b() {
            return new a();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.l = NotificationLite.completed();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.l = NotificationLite.error(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l = NotificationLite.next(t);
        }
    }

    public BlockingOperatorMostRecent() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Iterable<T> mostRecent(Observable<? extends T> observable, T t) {
        return new a(t, observable);
    }
}
