package rx.internal.operators;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subjects.Subject;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class BufferUntilSubscriber<T> extends Subject<T, T> {
    public static final Observer k = new a();
    public final c<T> i;
    public boolean j;

    /* loaded from: classes13.dex */
    public static class a implements Observer {
        @Override // rx.Observer
        public void onCompleted() {
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
        }

        @Override // rx.Observer
        public void onNext(Object obj) {
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> implements Observable.OnSubscribe<T> {
        public final c<T> h;

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public a() {
            }

            @Override // rx.functions.Action0
            public void call() {
                b.this.h.set(BufferUntilSubscriber.k);
            }
        }

        public b(c<T> cVar) {
            this.h = cVar;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            boolean z;
            if (this.h.casObserverRef(null, subscriber)) {
                subscriber.add(Subscriptions.create(new a()));
                synchronized (this.h.guard) {
                    c<T> cVar = this.h;
                    z = true;
                    if (cVar.emitting) {
                        z = false;
                    } else {
                        cVar.emitting = true;
                    }
                }
                if (!z) {
                    return;
                }
                while (true) {
                    Object poll = this.h.buffer.poll();
                    if (poll != null) {
                        NotificationLite.accept(this.h.get(), poll);
                    } else {
                        synchronized (this.h.guard) {
                            if (this.h.buffer.isEmpty()) {
                                this.h.emitting = false;
                                return;
                            }
                        }
                    }
                }
            } else {
                subscriber.onError(new IllegalStateException("Only one subscriber allowed!"));
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class c<T> extends AtomicReference<Observer<? super T>> {
        private static final long serialVersionUID = 8026705089538090368L;
        public boolean emitting;
        public final Object guard = new Object();
        public final ConcurrentLinkedQueue<Object> buffer = new ConcurrentLinkedQueue<>();

        public boolean casObserverRef(Observer<? super T> observer, Observer<? super T> observer2) {
            return compareAndSet(observer, observer2);
        }
    }

    public BufferUntilSubscriber(c<T> cVar) {
        super(new b(cVar));
        this.i = cVar;
    }

    public static <T> BufferUntilSubscriber<T> create() {
        return new BufferUntilSubscriber<>(new c());
    }

    public final void c(Object obj) {
        synchronized (this.i.guard) {
            this.i.buffer.add(obj);
            if (this.i.get() != null) {
                c<T> cVar = this.i;
                if (!cVar.emitting) {
                    this.j = true;
                    cVar.emitting = true;
                }
            }
        }
        if (!this.j) {
            return;
        }
        while (true) {
            Object poll = this.i.buffer.poll();
            if (poll == null) {
                return;
            }
            NotificationLite.accept(this.i.get(), poll);
        }
    }

    @Override // rx.subjects.Subject
    public boolean hasObservers() {
        boolean z;
        synchronized (this.i.guard) {
            z = this.i.get() != null;
        }
        return z;
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.j) {
            this.i.get().onCompleted();
        } else {
            c(NotificationLite.completed());
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        if (this.j) {
            this.i.get().onError(th);
        } else {
            c(NotificationLite.error(th));
        }
    }

    @Override // rx.Observer
    public void onNext(T t) {
        if (this.j) {
            this.i.get().onNext(t);
        } else {
            c(NotificationLite.next(t));
        }
    }
}
