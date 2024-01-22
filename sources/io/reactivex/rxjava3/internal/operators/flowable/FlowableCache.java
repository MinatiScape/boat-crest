package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.BackpressureHelper;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableCache<T> extends io.reactivex.rxjava3.internal.operators.flowable.a<T, T> implements FlowableSubscriber<T> {
    public static final a[] r = new a[0];
    public static final a[] s = new a[0];
    public final AtomicBoolean i;
    public final int j;
    public final AtomicReference<a<T>[]> k;
    public volatile long l;
    public final b<T> m;
    public b<T> n;
    public int o;
    public Throwable p;
    public volatile boolean q;

    /* loaded from: classes12.dex */
    public static final class a<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 6770240836423125754L;
        public final Subscriber<? super T> downstream;
        public long index;
        public b<T> node;
        public int offset;
        public final FlowableCache<T> parent;
        public final AtomicLong requested = new AtomicLong();

        public a(Subscriber<? super T> subscriber, FlowableCache<T> flowableCache) {
            this.downstream = subscriber;
            this.parent = flowableCache;
            this.node = flowableCache.m;
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.requested.getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.f(this);
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                BackpressureHelper.addCancel(this.requested, j);
                this.parent.g(this);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T> {

        /* renamed from: a  reason: collision with root package name */
        public final T[] f13962a;
        public volatile b<T> b;

        public b(int i) {
            this.f13962a = (T[]) new Object[i];
        }
    }

    public FlowableCache(Flowable<T> flowable, int i) {
        super(flowable);
        this.j = i;
        this.i = new AtomicBoolean();
        b<T> bVar = new b<>(i);
        this.m = bVar;
        this.n = bVar;
        this.k = new AtomicReference<>(r);
    }

    public void e(a<T> aVar) {
        a<T>[] aVarArr;
        a<T>[] aVarArr2;
        do {
            aVarArr = this.k.get();
            if (aVarArr == s) {
                return;
            }
            int length = aVarArr.length;
            aVarArr2 = new a[length + 1];
            System.arraycopy(aVarArr, 0, aVarArr2, 0, length);
            aVarArr2[length] = aVar;
        } while (!this.k.compareAndSet(aVarArr, aVarArr2));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void f(a<T> aVar) {
        a<T>[] aVarArr;
        a[] aVarArr2;
        do {
            aVarArr = this.k.get();
            int length = aVarArr.length;
            if (length == 0) {
                return;
            }
            int i = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (aVarArr[i2] == aVar) {
                    i = i2;
                    break;
                } else {
                    i2++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length == 1) {
                aVarArr2 = r;
            } else {
                a[] aVarArr3 = new a[length - 1];
                System.arraycopy(aVarArr, 0, aVarArr3, 0, i);
                System.arraycopy(aVarArr, i + 1, aVarArr3, i, (length - i) - 1);
                aVarArr2 = aVarArr3;
            }
        } while (!this.k.compareAndSet(aVarArr, aVarArr2));
    }

    public void g(a<T> aVar) {
        if (aVar.getAndIncrement() != 0) {
            return;
        }
        long j = aVar.index;
        int i = aVar.offset;
        b<T> bVar = aVar.node;
        AtomicLong atomicLong = aVar.requested;
        Subscriber<? super T> subscriber = aVar.downstream;
        int i2 = this.j;
        int i3 = 1;
        while (true) {
            boolean z = this.q;
            boolean z2 = this.l == j;
            if (z && z2) {
                aVar.node = null;
                Throwable th = this.p;
                if (th != null) {
                    subscriber.onError(th);
                    return;
                } else {
                    subscriber.onComplete();
                    return;
                }
            }
            if (!z2) {
                long j2 = atomicLong.get();
                if (j2 == Long.MIN_VALUE) {
                    aVar.node = null;
                    return;
                } else if (j2 != j) {
                    if (i == i2) {
                        bVar = bVar.b;
                        i = 0;
                    }
                    subscriber.onNext((Object) bVar.f13962a[i]);
                    i++;
                    j++;
                }
            }
            aVar.index = j;
            aVar.offset = i;
            aVar.node = bVar;
            i3 = aVar.addAndGet(-i3);
            if (i3 == 0) {
                return;
            }
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onComplete() {
        this.q = true;
        for (a<T> aVar : this.k.getAndSet(s)) {
            g(aVar);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onError(Throwable th) {
        if (this.q) {
            RxJavaPlugins.onError(th);
            return;
        }
        this.p = th;
        this.q = true;
        for (a<T> aVar : this.k.getAndSet(s)) {
            g(aVar);
        }
    }

    @Override // org.reactivestreams.Subscriber
    public void onNext(T t) {
        int i = this.o;
        if (i == this.j) {
            b<T> bVar = new b<>(i);
            bVar.f13962a[0] = t;
            this.o = 1;
            this.n.b = bVar;
            this.n = bVar;
        } else {
            this.n.f13962a[i] = t;
            this.o = i + 1;
        }
        this.l++;
        for (a<T> aVar : this.k.get()) {
            g(aVar);
        }
    }

    @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
    public void onSubscribe(Subscription subscription) {
        subscription.request(Long.MAX_VALUE);
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public void subscribeActual(Subscriber<? super T> subscriber) {
        a<T> aVar = new a<>(subscriber, this);
        subscriber.onSubscribe(aVar);
        e(aVar);
        if (!this.i.get() && this.i.compareAndSet(false, true)) {
            this.source.subscribe((FlowableSubscriber) this);
        } else {
            g(aVar);
        }
    }
}
