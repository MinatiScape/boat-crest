package rx.internal.operators;

import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
/* loaded from: classes13.dex */
public abstract class DeferredScalarSubscriber<T, R> extends Subscriber<T> {
    public final Subscriber<? super R> actual;
    public boolean hasValue;
    public final AtomicInteger l = new AtomicInteger();
    public R value;

    /* loaded from: classes13.dex */
    public static final class a implements Producer {
        public final DeferredScalarSubscriber<?, ?> h;

        public a(DeferredScalarSubscriber<?, ?> deferredScalarSubscriber) {
            this.h = deferredScalarSubscriber;
        }

        @Override // rx.Producer
        public void request(long j) {
            this.h.b(j);
        }
    }

    public DeferredScalarSubscriber(Subscriber<? super R> subscriber) {
        this.actual = subscriber;
    }

    public final void b(long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i < 0) {
            throw new IllegalArgumentException("n >= 0 required but it was " + j);
        } else if (i != 0) {
            Subscriber<? super R> subscriber = this.actual;
            do {
                int i2 = this.l.get();
                if (i2 == 1 || i2 == 3 || subscriber.isUnsubscribed()) {
                    return;
                }
                if (i2 == 2) {
                    if (this.l.compareAndSet(2, 3)) {
                        subscriber.onNext((R) this.value);
                        if (subscriber.isUnsubscribed()) {
                            return;
                        }
                        subscriber.onCompleted();
                        return;
                    }
                    return;
                }
            } while (!this.l.compareAndSet(0, 1));
        }
    }

    public final void c() {
        Subscriber<? super R> subscriber = this.actual;
        subscriber.add(this);
        subscriber.setProducer(new a(this));
    }

    public final void complete() {
        this.actual.onCompleted();
    }

    @Override // rx.Observer
    public void onCompleted() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            complete();
        }
    }

    @Override // rx.Observer
    public void onError(Throwable th) {
        this.value = null;
        this.actual.onError(th);
    }

    @Override // rx.Subscriber, rx.observers.AssertableSubscriber
    public final void setProducer(Producer producer) {
        producer.request(Long.MAX_VALUE);
    }

    public final void subscribeTo(Observable<? extends T> observable) {
        c();
        observable.unsafeSubscribe(this);
    }

    public final void complete(R r) {
        Subscriber<? super R> subscriber = this.actual;
        do {
            int i = this.l.get();
            if (i == 2 || i == 3 || subscriber.isUnsubscribed()) {
                return;
            }
            if (i == 1) {
                subscriber.onNext(r);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
                this.l.lazySet(3);
                return;
            }
            this.value = r;
        } while (!this.l.compareAndSet(0, 2));
    }
}
