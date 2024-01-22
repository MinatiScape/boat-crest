package rx.internal.operators;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.internal.producers.SingleProducer;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OnSubscribeToObservableFuture {
    public OnSubscribeToObservableFuture() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Observable.OnSubscribe<T> toObservableFuture(Future<? extends T> future) {
        return new a(future);
    }

    public static <T> Observable.OnSubscribe<T> toObservableFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        return new a(future, j, timeUnit);
    }

    /* loaded from: classes13.dex */
    public static class a<T> implements Observable.OnSubscribe<T> {
        public final Future<? extends T> h;
        public final long i;
        public final TimeUnit j;

        /* renamed from: rx.internal.operators.OnSubscribeToObservableFuture$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0944a implements Action0 {
            public C0944a() {
            }

            @Override // rx.functions.Action0
            public void call() {
                a.this.h.cancel(true);
            }
        }

        public a(Future<? extends T> future) {
            this.h = future;
            this.i = 0L;
            this.j = null;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            subscriber.add(Subscriptions.create(new C0944a()));
            try {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                TimeUnit timeUnit = this.j;
                subscriber.setProducer(new SingleProducer(subscriber, timeUnit == null ? this.h.get() : this.h.get(this.i, timeUnit)));
            } catch (Throwable th) {
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                Exceptions.throwOrReport(th, subscriber);
            }
        }

        public a(Future<? extends T> future, long j, TimeUnit timeUnit) {
            this.h = future;
            this.i = j;
            this.j = timeUnit;
        }
    }
}
