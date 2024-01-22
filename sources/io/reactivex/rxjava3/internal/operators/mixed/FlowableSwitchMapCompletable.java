package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableSwitchMapCompletable<T> extends Completable {
    public final Flowable<T> h;
    public final Function<? super T, ? extends CompletableSource> i;
    public final boolean j;

    /* loaded from: classes12.dex */
    public static final class a<T> implements FlowableSubscriber<T>, Disposable {
        public static final C0833a o = new C0833a(null);
        public final CompletableObserver h;
        public final Function<? super T, ? extends CompletableSource> i;
        public final boolean j;
        public final AtomicThrowable k = new AtomicThrowable();
        public final AtomicReference<C0833a> l = new AtomicReference<>();
        public volatile boolean m;
        public Subscription n;

        /* renamed from: io.reactivex.rxjava3.internal.operators.mixed.FlowableSwitchMapCompletable$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0833a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -8003404460084760287L;
            public final a<?> parent;

            public C0833a(a<?> aVar) {
                this.parent = aVar;
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onComplete() {
                this.parent.b(this);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onError(Throwable th) {
                this.parent.c(this, th);
            }

            @Override // io.reactivex.rxjava3.core.CompletableObserver
            public void onSubscribe(Disposable disposable) {
                DisposableHelper.setOnce(this, disposable);
            }
        }

        public a(CompletableObserver completableObserver, Function<? super T, ? extends CompletableSource> function, boolean z) {
            this.h = completableObserver;
            this.i = function;
            this.j = z;
        }

        public void a() {
            AtomicReference<C0833a> atomicReference = this.l;
            C0833a c0833a = o;
            C0833a andSet = atomicReference.getAndSet(c0833a);
            if (andSet == null || andSet == c0833a) {
                return;
            }
            andSet.dispose();
        }

        public void b(C0833a c0833a) {
            if (this.l.compareAndSet(c0833a, null) && this.m) {
                this.k.tryTerminateConsumer(this.h);
            }
        }

        public void c(C0833a c0833a, Throwable th) {
            if (this.l.compareAndSet(c0833a, null)) {
                if (this.k.tryAddThrowableOrReport(th)) {
                    if (this.j) {
                        if (this.m) {
                            this.k.tryTerminateConsumer(this.h);
                            return;
                        }
                        return;
                    }
                    this.n.cancel();
                    a();
                    this.k.tryTerminateConsumer(this.h);
                    return;
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public void dispose() {
            this.n.cancel();
            a();
            this.k.tryTerminateAndReport();
        }

        @Override // io.reactivex.rxjava3.disposables.Disposable
        public boolean isDisposed() {
            return this.l.get() == o;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.m = true;
            if (this.l.get() == null) {
                this.k.tryTerminateConsumer(this.h);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.k.tryAddThrowableOrReport(th)) {
                if (this.j) {
                    onComplete();
                    return;
                }
                a();
                this.k.tryTerminateConsumer(this.h);
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            C0833a c0833a;
            try {
                CompletableSource apply = this.i.apply(t);
                Objects.requireNonNull(apply, "The mapper returned a null CompletableSource");
                CompletableSource completableSource = apply;
                C0833a c0833a2 = new C0833a(this);
                do {
                    c0833a = this.l.get();
                    if (c0833a == o) {
                        return;
                    }
                } while (!this.l.compareAndSet(c0833a, c0833a2));
                if (c0833a != null) {
                    c0833a.dispose();
                }
                completableSource.subscribe(c0833a2);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.n.cancel();
                onError(th);
            }
        }

        @Override // io.reactivex.rxjava3.core.FlowableSubscriber, org.reactivestreams.Subscriber
        public void onSubscribe(Subscription subscription) {
            if (SubscriptionHelper.validate(this.n, subscription)) {
                this.n = subscription;
                this.h.onSubscribe(this);
                subscription.request(Long.MAX_VALUE);
            }
        }
    }

    public FlowableSwitchMapCompletable(Flowable<T> flowable, Function<? super T, ? extends CompletableSource> function, boolean z) {
        this.h = flowable;
        this.i = function;
        this.j = z;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.h.subscribe((FlowableSubscriber) new a(completableObserver, this.i, this.j));
    }
}
