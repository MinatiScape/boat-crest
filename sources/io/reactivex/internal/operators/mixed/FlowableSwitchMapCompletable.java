package io.reactivex.internal.operators.mixed;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;
/* loaded from: classes12.dex */
public final class FlowableSwitchMapCompletable<T> extends Completable {
    public final Flowable<T> h;
    public final Function<? super T, ? extends CompletableSource> i;
    public final boolean j;

    /* loaded from: classes12.dex */
    public static final class a<T> implements FlowableSubscriber<T>, Disposable {
        public static final C0768a o = new C0768a(null);
        public final CompletableObserver h;
        public final Function<? super T, ? extends CompletableSource> i;
        public final boolean j;
        public final AtomicThrowable k = new AtomicThrowable();
        public final AtomicReference<C0768a> l = new AtomicReference<>();
        public volatile boolean m;
        public Subscription n;

        /* renamed from: io.reactivex.internal.operators.mixed.FlowableSwitchMapCompletable$a$a  reason: collision with other inner class name */
        /* loaded from: classes12.dex */
        public static final class C0768a extends AtomicReference<Disposable> implements CompletableObserver {
            private static final long serialVersionUID = -8003404460084760287L;
            public final a<?> parent;

            public C0768a(a<?> aVar) {
                this.parent = aVar;
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }

            @Override // io.reactivex.CompletableObserver, io.reactivex.MaybeObserver
            public void onComplete() {
                this.parent.b(this);
            }

            @Override // io.reactivex.CompletableObserver
            public void onError(Throwable th) {
                this.parent.c(this, th);
            }

            @Override // io.reactivex.CompletableObserver
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
            AtomicReference<C0768a> atomicReference = this.l;
            C0768a c0768a = o;
            C0768a andSet = atomicReference.getAndSet(c0768a);
            if (andSet == null || andSet == c0768a) {
                return;
            }
            andSet.dispose();
        }

        public void b(C0768a c0768a) {
            if (this.l.compareAndSet(c0768a, null) && this.m) {
                Throwable terminate = this.k.terminate();
                if (terminate == null) {
                    this.h.onComplete();
                } else {
                    this.h.onError(terminate);
                }
            }
        }

        public void c(C0768a c0768a, Throwable th) {
            if (this.l.compareAndSet(c0768a, null) && this.k.addThrowable(th)) {
                if (this.j) {
                    if (this.m) {
                        this.h.onError(this.k.terminate());
                        return;
                    }
                    return;
                }
                dispose();
                Throwable terminate = this.k.terminate();
                if (terminate != ExceptionHelper.TERMINATED) {
                    this.h.onError(terminate);
                    return;
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.n.cancel();
            a();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.l.get() == o;
        }

        @Override // org.reactivestreams.Subscriber
        public void onComplete() {
            this.m = true;
            if (this.l.get() == null) {
                Throwable terminate = this.k.terminate();
                if (terminate == null) {
                    this.h.onComplete();
                } else {
                    this.h.onError(terminate);
                }
            }
        }

        @Override // org.reactivestreams.Subscriber
        public void onError(Throwable th) {
            if (this.k.addThrowable(th)) {
                if (this.j) {
                    onComplete();
                    return;
                }
                a();
                Throwable terminate = this.k.terminate();
                if (terminate != ExceptionHelper.TERMINATED) {
                    this.h.onError(terminate);
                    return;
                }
                return;
            }
            RxJavaPlugins.onError(th);
        }

        @Override // org.reactivestreams.Subscriber
        public void onNext(T t) {
            C0768a c0768a;
            try {
                CompletableSource completableSource = (CompletableSource) ObjectHelper.requireNonNull(this.i.apply(t), "The mapper returned a null CompletableSource");
                C0768a c0768a2 = new C0768a(this);
                do {
                    c0768a = this.l.get();
                    if (c0768a == o) {
                        return;
                    }
                } while (!this.l.compareAndSet(c0768a, c0768a2));
                if (c0768a != null) {
                    c0768a.dispose();
                }
                completableSource.subscribe(c0768a2);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.n.cancel();
                onError(th);
            }
        }

        @Override // io.reactivex.FlowableSubscriber, org.reactivestreams.Subscriber
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

    @Override // io.reactivex.Completable
    public void subscribeActual(CompletableObserver completableObserver) {
        this.h.subscribe((FlowableSubscriber) new a(completableObserver, this.i, this.j));
    }
}
