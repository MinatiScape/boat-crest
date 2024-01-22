package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscribers.QueueDrainSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableWindowBoundarySelector<T, B, V> extends io.reactivex.internal.operators.flowable.a<T, Flowable<T>> {
    public final Publisher<B> i;
    public final Function<? super B, ? extends Publisher<V>> j;
    public final int k;

    /* loaded from: classes12.dex */
    public static final class a<T, V> extends DisposableSubscriber<V> {
        public final c<T, ?, V> i;
        public final UnicastProcessor<T> j;
        public boolean k;

        public a(c<T, ?, V> cVar, UnicastProcessor<T> unicastProcessor) {
            this.i = cVar;
            this.j = unicastProcessor;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.k) {
                return;
            }
            this.k = true;
            this.i.a(this);
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.k) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.k = true;
            this.i.c(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(V v) {
            cancel();
            onComplete();
        }
    }

    /* loaded from: classes12.dex */
    public static final class b<T, B> extends DisposableSubscriber<B> {
        public final c<T, B, ?> i;

        public b(c<T, B, ?> cVar) {
            this.i = cVar;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.i.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            this.i.c(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(B b) {
            this.i.d(b);
        }
    }

    /* loaded from: classes12.dex */
    public static final class c<T, B, V> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Subscription {
        public final Publisher<B> j;
        public final Function<? super B, ? extends Publisher<V>> k;
        public final int l;
        public final CompositeDisposable m;
        public Subscription n;
        public final AtomicReference<Disposable> o;
        public final List<UnicastProcessor<T>> p;
        public final AtomicLong q;
        public final AtomicBoolean r;

        public c(Subscriber<? super Flowable<T>> subscriber, Publisher<B> publisher, Function<? super B, ? extends Publisher<V>> function, int i) {
            super(subscriber, new MpscLinkedQueue());
            this.o = new AtomicReference<>();
            AtomicLong atomicLong = new AtomicLong();
            this.q = atomicLong;
            this.r = new AtomicBoolean();
            this.j = publisher;
            this.k = function;
            this.l = i;
            this.m = new CompositeDisposable();
            this.p = new ArrayList();
            atomicLong.lazySet(1L);
        }

        public void a(a<T, V> aVar) {
            this.m.delete(aVar);
            this.queue.offer(new d(aVar.j, null));
            if (enter()) {
                b();
            }
        }

        @Override // io.reactivex.internal.subscribers.QueueDrainSubscriber, io.reactivex.internal.util.QueueDrain
        public boolean accept(Subscriber<? super Flowable<T>> subscriber, Object obj) {
            return false;
        }

        public void b() {
            SimpleQueue simpleQueue = this.queue;
            Subscriber<? super V> subscriber = this.downstream;
            List<UnicastProcessor<T>> list = this.p;
            int i = 1;
            while (true) {
                boolean z = this.done;
                Object poll = simpleQueue.poll();
                boolean z2 = poll == null;
                if (z && z2) {
                    dispose();
                    Throwable th = this.error;
                    if (th != null) {
                        for (UnicastProcessor<T> unicastProcessor : list) {
                            unicastProcessor.onError(th);
                        }
                    } else {
                        for (UnicastProcessor<T> unicastProcessor2 : list) {
                            unicastProcessor2.onComplete();
                        }
                    }
                    list.clear();
                    return;
                } else if (z2) {
                    i = leave(-i);
                    if (i == 0) {
                        return;
                    }
                } else if (poll instanceof d) {
                    d dVar = (d) poll;
                    UnicastProcessor<T> unicastProcessor3 = dVar.f13907a;
                    if (unicastProcessor3 != null) {
                        if (list.remove(unicastProcessor3)) {
                            dVar.f13907a.onComplete();
                            if (this.q.decrementAndGet() == 0) {
                                dispose();
                                return;
                            }
                        } else {
                            continue;
                        }
                    } else if (!this.r.get()) {
                        UnicastProcessor<T> create = UnicastProcessor.create(this.l);
                        long requested = requested();
                        if (requested != 0) {
                            list.add(create);
                            subscriber.onNext(create);
                            if (requested != Long.MAX_VALUE) {
                                produced(1L);
                            }
                            try {
                                Publisher publisher = (Publisher) ObjectHelper.requireNonNull(this.k.apply((B) dVar.b), "The publisher supplied is null");
                                a aVar = new a(this, create);
                                if (this.m.add(aVar)) {
                                    this.q.getAndIncrement();
                                    publisher.subscribe(aVar);
                                }
                            } catch (Throwable th2) {
                                cancel();
                                subscriber.onError(th2);
                            }
                        } else {
                            cancel();
                            subscriber.onError(new MissingBackpressureException("Could not deliver new window due to lack of requests"));
                        }
                    }
                } else {
                    for (UnicastProcessor<T> unicastProcessor4 : list) {
                        unicastProcessor4.onNext((T) NotificationLite.getValue(poll));
                    }
                }
            }
        }

        public void c(Throwable th) {
            this.n.cancel();
            this.m.dispose();
            DisposableHelper.dispose(this.o);
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscription
        public void cancel() {
            if (this.r.compareAndSet(false, true)) {
                DisposableHelper.dispose(this.o);
                if (this.q.decrementAndGet() == 0) {
                    this.n.cancel();
                }
            }
        }

        public void d(B b) {
            this.queue.offer(new d(null, b));
            if (enter()) {
                b();
            }
        }

        public void dispose() {
            this.m.dispose();
            DisposableHelper.dispose(this.o);
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            if (enter()) {
                b();
            }
            if (this.q.decrementAndGet() == 0) {
                this.m.dispose();
            }
            this.downstream.onComplete();
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
                return;
            }
            this.error = th;
            this.done = true;
            if (enter()) {
                b();
            }
            if (this.q.decrementAndGet() == 0) {
                this.m.dispose();
            }
            this.downstream.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (fastEnter()) {
                for (UnicastProcessor<T> unicastProcessor : this.p) {
                    unicastProcessor.onNext(t);
                }
                if (leave(-1) == 0) {
                    return;
                }
            } else {
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
            }
            b();
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.n, subscription)) {
                this.n = subscription;
                this.downstream.onSubscribe(this);
                if (this.r.get()) {
                    return;
                }
                b bVar = new b(this);
                if (this.o.compareAndSet(null, bVar)) {
                    subscription.request(Long.MAX_VALUE);
                    this.j.subscribe(bVar);
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public void request(long j) {
            requested(j);
        }
    }

    /* loaded from: classes12.dex */
    public static final class d<T, B> {

        /* renamed from: a  reason: collision with root package name */
        public final UnicastProcessor<T> f13907a;
        public final B b;

        public d(UnicastProcessor<T> unicastProcessor, B b) {
            this.f13907a = unicastProcessor;
            this.b = b;
        }
    }

    public FlowableWindowBoundarySelector(Flowable<T> flowable, Publisher<B> publisher, Function<? super B, ? extends Publisher<V>> function, int i) {
        super(flowable);
        this.i = publisher;
        this.j = function;
        this.k = i;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super Flowable<T>> subscriber) {
        this.source.subscribe((FlowableSubscriber) new c(new SerializedSubscriber(subscriber), this.i, this.j, this.k));
    }
}
