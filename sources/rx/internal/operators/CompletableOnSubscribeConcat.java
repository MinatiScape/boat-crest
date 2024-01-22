package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.MissingBackpressureException;
import rx.internal.subscriptions.SequentialSubscription;
import rx.internal.util.unsafe.SpscArrayQueue;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class CompletableOnSubscribeConcat implements Completable.OnSubscribe {
    public final Observable<Completable> h;
    public final int i;

    /* loaded from: classes13.dex */
    public static final class a extends Subscriber<Completable> {
        public final CompletableSubscriber l;
        public final SequentialSubscription m;
        public final SpscArrayQueue<Completable> n;
        public final C0934a o;
        public final AtomicBoolean p;
        public volatile boolean q;
        public volatile boolean r;

        /* renamed from: rx.internal.operators.CompletableOnSubscribeConcat$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public final class C0934a extends AtomicInteger implements CompletableSubscriber {
            private static final long serialVersionUID = 7233503139645205620L;

            public C0934a() {
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                a.this.c();
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                a.this.d(th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                a.this.m.set(subscription);
            }
        }

        public a(CompletableSubscriber completableSubscriber, int i) {
            this.l = completableSubscriber;
            this.n = new SpscArrayQueue<>(i);
            SequentialSubscription sequentialSubscription = new SequentialSubscription();
            this.m = sequentialSubscription;
            this.o = new C0934a();
            this.p = new AtomicBoolean();
            add(sequentialSubscription);
            request(i);
        }

        public void b() {
            C0934a c0934a = this.o;
            if (c0934a.getAndIncrement() != 0) {
                return;
            }
            while (!isUnsubscribed()) {
                if (!this.r) {
                    boolean z = this.q;
                    Completable poll = this.n.poll();
                    boolean z2 = poll == null;
                    if (z && z2) {
                        this.l.onCompleted();
                        return;
                    } else if (!z2) {
                        this.r = true;
                        poll.subscribe(c0934a);
                        request(1L);
                    }
                }
                if (c0934a.decrementAndGet() == 0) {
                    return;
                }
            }
        }

        public void c() {
            this.r = false;
            b();
        }

        public void d(Throwable th) {
            unsubscribe();
            onError(th);
        }

        @Override // rx.Observer
        /* renamed from: e */
        public void onNext(Completable completable) {
            if (!this.n.offer(completable)) {
                onError(new MissingBackpressureException());
            } else {
                b();
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.q) {
                return;
            }
            this.q = true;
            b();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.p.compareAndSet(false, true)) {
                this.l.onError(th);
            } else {
                RxJavaHooks.onError(th);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CompletableOnSubscribeConcat(Observable<? extends Completable> observable, int i) {
        this.h = observable;
        this.i = i;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        a aVar = new a(completableSubscriber, this.i);
        completableSubscriber.onSubscribe(aVar);
        this.h.unsafeSubscribe(aVar);
    }
}
