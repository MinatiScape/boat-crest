package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ResettableConnectable;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
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
            this.parent.i(this);
        }

        @Override // io.reactivex.functions.Consumer
        public void accept(Disposable disposable) throws Exception {
            DisposableHelper.replace(this, disposable);
            synchronized (this.parent) {
                if (this.disconnectedEarly) {
                    ((ResettableConnectable) this.parent.i).resetIf(disposable);
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
                this.parent.h(this.connection);
                this.downstream.onComplete();
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (compareAndSet(false, true)) {
                this.parent.h(this.connection);
                this.downstream.onError(th);
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
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
                        i(aVar);
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
        Disposable disposable = aVar.timer;
        if (disposable != null) {
            disposable.dispose();
            aVar.timer = null;
        }
    }

    public void g(a aVar) {
        ConnectableFlowable<T> connectableFlowable = this.i;
        if (connectableFlowable instanceof Disposable) {
            ((Disposable) connectableFlowable).dispose();
        } else if (connectableFlowable instanceof ResettableConnectable) {
            ((ResettableConnectable) connectableFlowable).resetIf(aVar.get());
        }
    }

    public void h(a aVar) {
        synchronized (this) {
            if (this.i instanceof FlowablePublishClassic) {
                a aVar2 = this.n;
                if (aVar2 != null && aVar2 == aVar) {
                    this.n = null;
                    f(aVar);
                }
                long j = aVar.subscriberCount - 1;
                aVar.subscriberCount = j;
                if (j == 0) {
                    g(aVar);
                }
            } else {
                a aVar3 = this.n;
                if (aVar3 != null && aVar3 == aVar) {
                    f(aVar);
                    long j2 = aVar.subscriberCount - 1;
                    aVar.subscriberCount = j2;
                    if (j2 == 0) {
                        this.n = null;
                        g(aVar);
                    }
                }
            }
        }
    }

    public void i(a aVar) {
        synchronized (this) {
            if (aVar.subscriberCount == 0 && aVar == this.n) {
                this.n = null;
                Disposable disposable = aVar.get();
                DisposableHelper.dispose(aVar);
                ConnectableFlowable<T> connectableFlowable = this.i;
                if (connectableFlowable instanceof Disposable) {
                    ((Disposable) connectableFlowable).dispose();
                } else if (connectableFlowable instanceof ResettableConnectable) {
                    if (disposable == null) {
                        aVar.disconnectedEarly = true;
                    } else {
                        ((ResettableConnectable) connectableFlowable).resetIf(disposable);
                    }
                }
            }
        }
    }

    @Override // io.reactivex.Flowable
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
