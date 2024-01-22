package rx.internal.operators;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.CompositeException;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class CompletableOnSubscribeMerge implements Completable.OnSubscribe {
    public final Observable<Completable> h;
    public final int i;
    public final boolean j;

    /* loaded from: classes13.dex */
    public static final class a extends Subscriber<Completable> {
        public final CompletableSubscriber l;
        public final boolean n;
        public volatile boolean o;
        public final CompositeSubscription m = new CompositeSubscription();
        public final AtomicInteger r = new AtomicInteger(1);
        public final AtomicBoolean q = new AtomicBoolean();
        public final AtomicReference<Queue<Throwable>> p = new AtomicReference<>();

        /* renamed from: rx.internal.operators.CompletableOnSubscribeMerge$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0935a implements CompletableSubscriber {
            public Subscription h;
            public boolean i;

            public C0935a() {
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                if (this.i) {
                    return;
                }
                this.i = true;
                a.this.m.remove(this.h);
                a.this.f();
                if (a.this.o) {
                    return;
                }
                a.this.request(1L);
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                if (this.i) {
                    RxJavaHooks.onError(th);
                    return;
                }
                this.i = true;
                a.this.m.remove(this.h);
                a.this.d().offer(th);
                a.this.f();
                a aVar = a.this;
                if (!aVar.n || aVar.o) {
                    return;
                }
                a.this.request(1L);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                this.h = subscription;
                a.this.m.add(subscription);
            }
        }

        public a(CompletableSubscriber completableSubscriber, int i, boolean z) {
            this.l = completableSubscriber;
            this.n = z;
            if (i == Integer.MAX_VALUE) {
                request(Long.MAX_VALUE);
            } else {
                request(i);
            }
        }

        public Queue<Throwable> d() {
            Queue<Throwable> queue = this.p.get();
            if (queue != null) {
                return queue;
            }
            ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
            return this.p.compareAndSet(null, concurrentLinkedQueue) ? concurrentLinkedQueue : this.p.get();
        }

        @Override // rx.Observer
        /* renamed from: e */
        public void onNext(Completable completable) {
            if (this.o) {
                return;
            }
            this.r.getAndIncrement();
            completable.unsafeSubscribe(new C0935a());
        }

        public void f() {
            Queue<Throwable> queue;
            if (this.r.decrementAndGet() == 0) {
                Queue<Throwable> queue2 = this.p.get();
                if (queue2 != null && !queue2.isEmpty()) {
                    Throwable collectErrors = CompletableOnSubscribeMerge.collectErrors(queue2);
                    if (this.q.compareAndSet(false, true)) {
                        this.l.onError(collectErrors);
                        return;
                    } else {
                        RxJavaHooks.onError(collectErrors);
                        return;
                    }
                }
                this.l.onCompleted();
            } else if (this.n || (queue = this.p.get()) == null || queue.isEmpty()) {
            } else {
                Throwable collectErrors2 = CompletableOnSubscribeMerge.collectErrors(queue);
                if (this.q.compareAndSet(false, true)) {
                    this.l.onError(collectErrors2);
                } else {
                    RxJavaHooks.onError(collectErrors2);
                }
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            if (this.o) {
                return;
            }
            this.o = true;
            f();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.o) {
                RxJavaHooks.onError(th);
                return;
            }
            d().offer(th);
            this.o = true;
            f();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public CompletableOnSubscribeMerge(Observable<? extends Completable> observable, int i, boolean z) {
        this.h = observable;
        this.i = i;
        this.j = z;
    }

    public static Throwable collectErrors(Queue<Throwable> queue) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            Throwable poll = queue.poll();
            if (poll == null) {
                break;
            }
            arrayList.add(poll);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (Throwable) arrayList.get(0);
        }
        return new CompositeException(arrayList);
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        a aVar = new a(completableSubscriber, this.i, this.j);
        completableSubscriber.onSubscribe(aVar);
        this.h.unsafeSubscribe(aVar);
    }
}
