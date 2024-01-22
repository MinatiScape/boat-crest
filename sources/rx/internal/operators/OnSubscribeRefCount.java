package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.observables.ConnectableObservable;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public final class OnSubscribeRefCount<T> implements Observable.OnSubscribe<T> {
    public final ConnectableObservable<? extends T> h;
    public volatile CompositeSubscription i = new CompositeSubscription();
    public final AtomicInteger j = new AtomicInteger(0);
    public final ReentrantLock k = new ReentrantLock();

    /* loaded from: classes13.dex */
    public class a implements Action1<Subscription> {
        public final /* synthetic */ Subscriber h;
        public final /* synthetic */ AtomicBoolean i;

        public a(Subscriber subscriber, AtomicBoolean atomicBoolean) {
            this.h = subscriber;
            this.i = atomicBoolean;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscription subscription) {
            try {
                OnSubscribeRefCount.this.i.add(subscription);
                OnSubscribeRefCount onSubscribeRefCount = OnSubscribeRefCount.this;
                onSubscribeRefCount.c(this.h, onSubscribeRefCount.i);
            } finally {
                OnSubscribeRefCount.this.k.unlock();
                this.i.set(false);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class b extends Subscriber<T> {
        public final /* synthetic */ Subscriber l;
        public final /* synthetic */ CompositeSubscription m;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(Subscriber subscriber, Subscriber subscriber2, CompositeSubscription compositeSubscription) {
            super(subscriber);
            this.l = subscriber2;
            this.m = compositeSubscription;
        }

        public void b() {
            OnSubscribeRefCount.this.k.lock();
            try {
                if (OnSubscribeRefCount.this.i == this.m) {
                    if (OnSubscribeRefCount.this.h instanceof Subscription) {
                        ((Subscription) OnSubscribeRefCount.this.h).unsubscribe();
                    }
                    OnSubscribeRefCount.this.i.unsubscribe();
                    OnSubscribeRefCount.this.i = new CompositeSubscription();
                    OnSubscribeRefCount.this.j.set(0);
                }
            } finally {
                OnSubscribeRefCount.this.k.unlock();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            b();
            this.l.onCompleted();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            b();
            this.l.onError(th);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            this.l.onNext(t);
        }
    }

    /* loaded from: classes13.dex */
    public class c implements Action0 {
        public final /* synthetic */ CompositeSubscription h;

        public c(CompositeSubscription compositeSubscription) {
            this.h = compositeSubscription;
        }

        @Override // rx.functions.Action0
        public void call() {
            OnSubscribeRefCount.this.k.lock();
            try {
                if (OnSubscribeRefCount.this.i == this.h && OnSubscribeRefCount.this.j.decrementAndGet() == 0) {
                    if (OnSubscribeRefCount.this.h instanceof Subscription) {
                        ((Subscription) OnSubscribeRefCount.this.h).unsubscribe();
                    }
                    OnSubscribeRefCount.this.i.unsubscribe();
                    OnSubscribeRefCount.this.i = new CompositeSubscription();
                }
            } finally {
                OnSubscribeRefCount.this.k.unlock();
            }
        }
    }

    public OnSubscribeRefCount(ConnectableObservable<? extends T> connectableObservable) {
        this.h = connectableObservable;
    }

    public final Subscription b(CompositeSubscription compositeSubscription) {
        return Subscriptions.create(new c(compositeSubscription));
    }

    public void c(Subscriber<? super T> subscriber, CompositeSubscription compositeSubscription) {
        subscriber.add(b(compositeSubscription));
        this.h.unsafeSubscribe(new b(subscriber, subscriber, compositeSubscription));
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public final Action1<Subscription> d(Subscriber<? super T> subscriber, AtomicBoolean atomicBoolean) {
        return new a(subscriber, atomicBoolean);
    }

    public void call(Subscriber<? super T> subscriber) {
        boolean z;
        this.k.lock();
        if (this.j.incrementAndGet() == 1) {
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);
            try {
                this.h.connect(d(subscriber, atomicBoolean));
                if (z) {
                    return;
                }
                return;
            } finally {
                if (atomicBoolean.get()) {
                }
            }
        }
        try {
            c(subscriber, this.i);
        } finally {
            this.k.unlock();
        }
    }
}
