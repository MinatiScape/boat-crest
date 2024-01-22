package rx.internal.operators;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import rx.Observable;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class OnSubscribeDetach<T> implements Observable.OnSubscribe<T> {
    public final Observable<T> h;

    /* loaded from: classes13.dex */
    public static final class a<T> implements Producer, Subscription {
        public final b<T> h;

        public a(b<T> bVar) {
            this.h = bVar;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.h.isUnsubscribed();
        }

        @Override // rx.Producer
        public void request(long j) {
            this.h.b(j);
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            this.h.c();
        }
    }

    /* loaded from: classes13.dex */
    public static final class b<T> extends Subscriber<T> {
        public final AtomicReference<Subscriber<? super T>> l;
        public final AtomicReference<Producer> m = new AtomicReference<>();
        public final AtomicLong n = new AtomicLong();

        public b(Subscriber<? super T> subscriber) {
            this.l = new AtomicReference<>(subscriber);
        }

        public void b(long j) {
            if (j >= 0) {
                Producer producer = this.m.get();
                if (producer != null) {
                    producer.request(j);
                    return;
                }
                BackpressureUtils.getAndAddRequest(this.n, j);
                Producer producer2 = this.m.get();
                if (producer2 == null || producer2 == c.INSTANCE) {
                    return;
                }
                producer2.request(this.n.getAndSet(0L));
                return;
            }
            throw new IllegalArgumentException("n >= 0 required but it was " + j);
        }

        public void c() {
            this.m.lazySet(c.INSTANCE);
            this.l.lazySet(null);
            unsubscribe();
        }

        @Override // rx.Observer
        public void onCompleted() {
            this.m.lazySet(c.INSTANCE);
            Subscriber<? super T> andSet = this.l.getAndSet(null);
            if (andSet != null) {
                andSet.onCompleted();
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            this.m.lazySet(c.INSTANCE);
            Subscriber<? super T> andSet = this.l.getAndSet(null);
            if (andSet != null) {
                andSet.onError(th);
            } else {
                RxJavaHooks.onError(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            Subscriber<? super T> subscriber = this.l.get();
            if (subscriber != null) {
                subscriber.onNext(t);
            }
        }

        @Override // rx.Subscriber, rx.observers.AssertableSubscriber
        public void setProducer(Producer producer) {
            if (this.m.compareAndSet(null, producer)) {
                producer.request(this.n.getAndSet(0L));
            } else if (this.m.get() != c.INSTANCE) {
                throw new IllegalStateException("Producer already set!");
            }
        }
    }

    /* loaded from: classes13.dex */
    public enum c implements Producer {
        INSTANCE;

        @Override // rx.Producer
        public void request(long j) {
        }
    }

    public OnSubscribeDetach(Observable<T> observable) {
        this.h = observable;
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        b bVar = new b(subscriber);
        a aVar = new a(bVar);
        subscriber.add(aVar);
        subscriber.setProducer(aVar);
        this.h.unsafeSubscribe(bVar);
    }
}
