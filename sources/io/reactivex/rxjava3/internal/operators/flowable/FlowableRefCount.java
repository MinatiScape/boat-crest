package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.flowables.ConnectableFlowable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableRefCount<T> extends Flowable<T> {
    public final ConnectableFlowable<T> i;
    public final int j;
    public final long k;
    public final TimeUnit l;
    public final Scheduler m;
    public a n;

    /* loaded from: classes12.dex */
    public static final class a extends AtomicReference<Disposable> implements Runnable, Consumer<Disposable> {
        private static final long serialVersionUID = -4552101107598366241L;
        public boolean connected;
        public boolean disconnectedEarly;
        public final FlowableRefCount<?> parent;
        public long subscriberCount;
        public Disposable timer;

        public a(FlowableRefCount<?> flowableRefCount) {
            this.parent = flowableRefCount;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.parent.g(this);
        }

        @Override // io.reactivex.rxjava3.functions.Consumer
        public void accept(Disposable disposable) {
            DisposableHelper.replace(this, disposable);
            synchronized (this.parent) {
                if (this.disconnectedEarly) {
                    this.parent.i.reset();
                }
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> extends AtomicBoolean implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -7419642935409022375L;
        public final a connection;
        public final Subscriber<? super T> downstream;
        public final FlowableRefCount<T> parent;
        public Subscription upstream;

        public b(Subscriber<? super T> subscriber, FlowableRefCount<T> flowableRefCount, a aVar) {
            this.downstream = subscriber;
            this.parent = flowableRefCount;
            this.connection = aVar;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            this.upstream.cancel();
            if (compareAndSet(false, true)) {
                this.parent.e(this.connection);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (compareAndSet(false, true)) {
                this.parent.f(this.connection);
                this.downstream.onComplete();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.parent.f(this.connection);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.upstream, subscription)) {
                this.upstream = subscription;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            this.upstream.request(j);
        }
    }

    public FlowableRefCount(ConnectableFlowable<T> connectableFlowable) {
        this(connectableFlowable, 1, 0L, TimeUnit.NANOSECONDS, null);
    }

    public void e(a aVar) {
        synchronized (this) {
            a aVar2 = this.n;
            if (aVar2 != null && aVar2 == aVar) {
                long j = aVar.subscriberCount - 1;
                aVar.subscriberCount = j;
                if (j == 0 && aVar.connected) {
                    if (this.k == 0) {
                        g(aVar);
                        return;
                    }
                    SequentialDisposable sequentialDisposable = new SequentialDisposable();
                    aVar.timer = sequentialDisposable;
                    sequentialDisposable.replace(this.m.scheduleDirect(aVar, this.k, this.l));
                }
            }
        }
    }

    public void f(a aVar) {
        synchronized (this) {
            if (this.n == aVar) {
                Disposable disposable = aVar.timer;
                if (disposable != null) {
                    disposable.dispose();
                    aVar.timer = null;
                }
                long j = aVar.subscriberCount - 1;
                aVar.subscriberCount = j;
                if (j == 0) {
                    this.n = null;
                    this.i.reset();
                }
            }
        }
    }

    public void g(a aVar) {
        synchronized (this) {
            if (aVar.subscriberCount == 0 && aVar == this.n) {
                this.n = null;
                Disposable disposable = aVar.get();
                DisposableHelper.dispose(aVar);
                if (disposable == null) {
                    aVar.disconnectedEarly = true;
                } else {
                    this.i.reset();
                }
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        a aVar;
        boolean z;
        Disposable disposable;
        synchronized (this) {
            aVar = this.n;
            if (aVar == null) {
                aVar = new a(this);
                this.n = aVar;
            }
            long j = aVar.subscriberCount;
            if (j == 0 && (disposable = aVar.timer) != null) {
                disposable.dispose();
            }
            long j2 = j + 1;
            aVar.subscriberCount = j2;
            z = true;
            if (aVar.connected || j2 != this.j) {
                z = false;
            } else {
                aVar.connected = true;
            }
        }
        this.i.subscribe((FlowableSubscriber) new b(subscriber, this, aVar));
        if (z) {
            this.i.connect(aVar);
        }
    }

    public FlowableRefCount(ConnectableFlowable<T> connectableFlowable, int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.i = connectableFlowable;
        this.j = i;
        this.k = j;
        this.l = timeUnit;
        this.m = scheduler;
    }
}
