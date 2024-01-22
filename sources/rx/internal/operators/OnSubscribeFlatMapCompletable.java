package rx.internal.operators;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class OnSubscribeFlatMapCompletable<T> implements Observable.OnSubscribe<T> {
    public final Observable<T> h;
    public final Func1<? super T, ? extends Completable> i;
    public final boolean j;
    public final int k;

    /* loaded from: classes13.dex */
    public static final class a<T> extends Subscriber<T> {
        public final Subscriber<? super T> l;
        public final Func1<? super T, ? extends Completable> m;
        public final boolean n;
        public final int o;
        public final AtomicInteger p = new AtomicInteger(1);
        public final AtomicReference<Throwable> r = new AtomicReference<>();
        public final CompositeSubscription q = new CompositeSubscription();

        /* renamed from: rx.internal.operators.OnSubscribeFlatMapCompletable$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public final class C0937a extends AtomicReference<Subscription> implements CompletableSubscriber, Subscription {
            private static final long serialVersionUID = -8588259593722659900L;

            public C0937a() {
            }

            @Override // rx.Subscription
            public boolean isUnsubscribed() {
                return get() == this;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                a.this.c(this);
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                a.this.d(this, th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                if (compareAndSet(null, subscription)) {
                    return;
                }
                subscription.unsubscribe();
                if (get() != this) {
                    RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
                }
            }

            @Override // rx.Subscription
            public void unsubscribe() {
                Subscription andSet = getAndSet(this);
                if (andSet == null || andSet == this) {
                    return;
                }
                andSet.unsubscribe();
            }
        }

        public a(Subscriber<? super T> subscriber, Func1<? super T, ? extends Completable> func1, boolean z, int i) {
            this.l = subscriber;
            this.m = func1;
            this.n = z;
            this.o = i;
            request(i != Integer.MAX_VALUE ? i : Long.MAX_VALUE);
        }

        public boolean b() {
            if (this.p.decrementAndGet() == 0) {
                Throwable terminate = ExceptionsUtils.terminate(this.r);
                if (terminate != null) {
                    this.l.onError(terminate);
                    return true;
                }
                this.l.onCompleted();
                return true;
            }
            return false;
        }

        public void c(a<T>.C0937a c0937a) {
            this.q.remove(c0937a);
            if (b() || this.o == Integer.MAX_VALUE) {
                return;
            }
            request(1L);
        }

        public void d(a<T>.C0937a c0937a, Throwable th) {
            this.q.remove(c0937a);
            if (this.n) {
                ExceptionsUtils.addThrowable(this.r, th);
                if (b() || this.o == Integer.MAX_VALUE) {
                    return;
                }
                request(1L);
                return;
            }
            this.q.unsubscribe();
            unsubscribe();
            if (this.r.compareAndSet(null, th)) {
                this.l.onError(ExceptionsUtils.terminate(this.r));
            } else {
                RxJavaHooks.onError(th);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            b();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.n) {
                ExceptionsUtils.addThrowable(this.r, th);
                onCompleted();
                return;
            }
            this.q.unsubscribe();
            if (this.r.compareAndSet(null, th)) {
                this.l.onError(ExceptionsUtils.terminate(this.r));
            } else {
                RxJavaHooks.onError(th);
            }
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Completable call = this.m.call(t);
                if (call != null) {
                    C0937a c0937a = new C0937a();
                    this.q.add(c0937a);
                    this.p.getAndIncrement();
                    call.unsafeSubscribe(c0937a);
                    return;
                }
                throw new NullPointerException("The mapper returned a null Completable");
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }
    }

    public OnSubscribeFlatMapCompletable(Observable<T> observable, Func1<? super T, ? extends Completable> func1, boolean z, int i) {
        Objects.requireNonNull(func1, "mapper is null");
        if (i > 0) {
            this.h = observable;
            this.i = func1;
            this.j = z;
            this.k = i;
            return;
        }
        throw new IllegalArgumentException("maxConcurrency > 0 required but it was " + i);
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public void call(Subscriber<? super T> subscriber) {
        a aVar = new a(subscriber, this.i, this.j, this.k);
        subscriber.add(aVar);
        subscriber.add(aVar.q);
        this.h.unsafeSubscribe(aVar);
    }
}
