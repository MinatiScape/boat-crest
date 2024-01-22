package rx.internal.operators;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;
/* loaded from: classes13.dex */
public final class CompletableOnSubscribeTimeout implements Completable.OnSubscribe {
    public final Completable h;
    public final long i;
    public final TimeUnit j;
    public final Scheduler k;
    public final Completable l;

    /* loaded from: classes13.dex */
    public class a implements Action0 {
        public final /* synthetic */ AtomicBoolean h;
        public final /* synthetic */ CompositeSubscription i;
        public final /* synthetic */ CompletableSubscriber j;

        /* renamed from: rx.internal.operators.CompletableOnSubscribeTimeout$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0936a implements CompletableSubscriber {
            public C0936a() {
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                a.this.i.unsubscribe();
                a.this.j.onCompleted();
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                a.this.i.unsubscribe();
                a.this.j.onError(th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                a.this.i.add(subscription);
            }
        }

        public a(AtomicBoolean atomicBoolean, CompositeSubscription compositeSubscription, CompletableSubscriber completableSubscriber) {
            this.h = atomicBoolean;
            this.i = compositeSubscription;
            this.j = completableSubscriber;
        }

        @Override // rx.functions.Action0
        public void call() {
            if (this.h.compareAndSet(false, true)) {
                this.i.clear();
                Completable completable = CompletableOnSubscribeTimeout.this.l;
                if (completable == null) {
                    this.j.onError(new TimeoutException());
                } else {
                    completable.unsafeSubscribe(new C0936a());
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public class b implements CompletableSubscriber {
        public final /* synthetic */ CompositeSubscription h;
        public final /* synthetic */ AtomicBoolean i;
        public final /* synthetic */ CompletableSubscriber j;

        public b(CompletableOnSubscribeTimeout completableOnSubscribeTimeout, CompositeSubscription compositeSubscription, AtomicBoolean atomicBoolean, CompletableSubscriber completableSubscriber) {
            this.h = compositeSubscription;
            this.i = atomicBoolean;
            this.j = completableSubscriber;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            if (this.i.compareAndSet(false, true)) {
                this.h.unsubscribe();
                this.j.onCompleted();
            }
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            if (this.i.compareAndSet(false, true)) {
                this.h.unsubscribe();
                this.j.onError(th);
                return;
            }
            RxJavaHooks.onError(th);
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.h.add(subscription);
        }
    }

    public CompletableOnSubscribeTimeout(Completable completable, long j, TimeUnit timeUnit, Scheduler scheduler, Completable completable2) {
        this.h = completable;
        this.i = j;
        this.j = timeUnit;
        this.k = scheduler;
        this.l = completable2;
    }

    @Override // rx.functions.Action1
    public void call(CompletableSubscriber completableSubscriber) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();
        completableSubscriber.onSubscribe(compositeSubscription);
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        Scheduler.Worker createWorker = this.k.createWorker();
        compositeSubscription.add(createWorker);
        createWorker.schedule(new a(atomicBoolean, compositeSubscription, completableSubscriber), this.i, this.j);
        this.h.unsafeSubscribe(new b(this, compositeSubscription, atomicBoolean, completableSubscriber));
    }
}
