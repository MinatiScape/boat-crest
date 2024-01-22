package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.subscribers.SerializedSubscriber;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableDelay<T> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, T> {
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;
    public final boolean l;

    /* loaded from: classes12.dex */
    public static final class a<T> implements FlowableSubscriber<T>, Subscription {
        public final Subscriber<? super T> h;
        public final long i;
        public final TimeUnit j;
        public final Scheduler.Worker k;
        public final boolean l;
        public Subscription m;

        /* renamed from: io.reactivex.rxjava3.internal.operators.flowable.FlowableDelay$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public final class RunnableC0808a implements Runnable {
            public RunnableC0808a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.h.onComplete();
                } finally {
                    a.this.k.dispose();
                }
            }
        }

        /* loaded from: classes12.dex */
        public final class b implements Runnable {
            public final Throwable h;

            public b(Throwable th) {
                this.h = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.this.h.onError(this.h);
                } finally {
                    a.this.k.dispose();
                }
            }
        }

        /* loaded from: classes12.dex */
        public final class c implements Runnable {
            public final T h;

            public c(T t) {
                this.h = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                a.this.h.onNext((T) this.h);
            }
        }

        public a(Subscriber<? super T> subscriber, long j, TimeUnit timeUnit, Scheduler.Worker worker, boolean z) {
            this.h = subscriber;
            this.i = j;
            this.j = timeUnit;
            this.k = worker;
            this.l = z;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.m.cancel();
            this.k.dispose();
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.k.schedule(new RunnableC0808a(), this.i, this.j);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.k.schedule(new b(th), this.l ? this.i : 0L, this.j);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.k.schedule(new c(t), this.i, this.j);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.m, subscription)) {
                this.m = subscription;
                this.h.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.m.request(j);
        }
    }

    public FlowableDelay(Flowable<T> flowable, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        super(flowable);
        this.i = j;
        this.j = timeUnit;
        this.k = scheduler;
        this.l = z;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        this.source.subscribe((FlowableSubscriber) new a(this.l ? subscriber : new SerializedSubscriber(subscriber), this.i, this.j, this.k.createWorker(), this.l));
    }
}
