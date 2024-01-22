package rx.internal.schedulers;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.operators.BufferUntilSubscriber;
import rx.observers.SerializedObserver;
import rx.subjects.PublishSubject;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public class SchedulerWhen extends Scheduler implements Subscription {
    public static final Subscription k = new c();
    public static final Subscription l = Subscriptions.unsubscribed();
    public final Scheduler h;
    public final Observer<Observable<Completable>> i;
    public final Subscription j;

    /* loaded from: classes13.dex */
    public class a implements Func1<g, Completable> {
        public final /* synthetic */ Scheduler.Worker h;

        /* renamed from: rx.internal.schedulers.SchedulerWhen$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0971a implements Completable.OnSubscribe {
            public final /* synthetic */ g h;

            public C0971a(g gVar) {
                this.h = gVar;
            }

            @Override // rx.functions.Action1
            /* renamed from: a */
            public void call(CompletableSubscriber completableSubscriber) {
                completableSubscriber.onSubscribe(this.h);
                this.h.call(a.this.h, completableSubscriber);
            }
        }

        public a(SchedulerWhen schedulerWhen, Scheduler.Worker worker) {
            this.h = worker;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Completable call(g gVar) {
            return Completable.create(new C0971a(gVar));
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements Subscription {
        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return false;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
        }
    }

    /* loaded from: classes13.dex */
    public static class d extends g {
        private final Action0 action;
        private final long delayTime;
        private final TimeUnit unit;

        public d(Action0 action0, long j, TimeUnit timeUnit) {
            this.action = action0;
            this.delayTime = j;
            this.unit = timeUnit;
        }

        @Override // rx.internal.schedulers.SchedulerWhen.g
        public Subscription callActual(Scheduler.Worker worker, CompletableSubscriber completableSubscriber) {
            return worker.schedule(new f(this.action, completableSubscriber), this.delayTime, this.unit);
        }
    }

    /* loaded from: classes13.dex */
    public static class e extends g {
        private final Action0 action;

        public e(Action0 action0) {
            this.action = action0;
        }

        @Override // rx.internal.schedulers.SchedulerWhen.g
        public Subscription callActual(Scheduler.Worker worker, CompletableSubscriber completableSubscriber) {
            return worker.schedule(new f(this.action, completableSubscriber));
        }
    }

    /* loaded from: classes13.dex */
    public static class f implements Action0 {
        public CompletableSubscriber h;
        public Action0 i;

        public f(Action0 action0, CompletableSubscriber completableSubscriber) {
            this.i = action0;
            this.h = completableSubscriber;
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                this.i.call();
            } finally {
                this.h.onCompleted();
            }
        }
    }

    /* loaded from: classes13.dex */
    public static abstract class g extends AtomicReference<Subscription> implements Subscription {
        public g() {
            super(SchedulerWhen.k);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void call(Scheduler.Worker worker, CompletableSubscriber completableSubscriber) {
            Subscription subscription;
            Subscription subscription2 = get();
            if (subscription2 != SchedulerWhen.l && subscription2 == (subscription = SchedulerWhen.k)) {
                Subscription callActual = callActual(worker, completableSubscriber);
                if (compareAndSet(subscription, callActual)) {
                    return;
                }
                callActual.unsubscribe();
            }
        }

        public abstract Subscription callActual(Scheduler.Worker worker, CompletableSubscriber completableSubscriber);

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return get().isUnsubscribed();
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            Subscription subscription;
            Subscription subscription2 = SchedulerWhen.l;
            do {
                subscription = get();
                if (subscription == SchedulerWhen.l) {
                    return;
                }
            } while (!compareAndSet(subscription, subscription2));
            if (subscription != SchedulerWhen.k) {
                subscription.unsubscribe();
            }
        }
    }

    public SchedulerWhen(Func1<Observable<Observable<Completable>>, Completable> func1, Scheduler scheduler) {
        this.h = scheduler;
        PublishSubject create = PublishSubject.create();
        this.i = new SerializedObserver(create);
        this.j = func1.call(create.onBackpressureBuffer()).subscribe();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // rx.Scheduler
    public Scheduler.Worker createWorker() {
        Scheduler.Worker createWorker = this.h.createWorker();
        BufferUntilSubscriber create = BufferUntilSubscriber.create();
        SerializedObserver serializedObserver = new SerializedObserver(create);
        Object map = create.map(new a(this, createWorker));
        b bVar = new b(this, createWorker, serializedObserver);
        this.i.onNext(map);
        return bVar;
    }

    @Override // rx.Subscription
    public boolean isUnsubscribed() {
        return this.j.isUnsubscribed();
    }

    @Override // rx.Subscription
    public void unsubscribe() {
        this.j.unsubscribe();
    }

    /* loaded from: classes13.dex */
    public class b extends Scheduler.Worker {
        public final AtomicBoolean h = new AtomicBoolean();
        public final /* synthetic */ Scheduler.Worker i;
        public final /* synthetic */ Observer j;

        public b(SchedulerWhen schedulerWhen, Scheduler.Worker worker, Observer observer) {
            this.i = worker;
            this.j = observer;
        }

        @Override // rx.Subscription
        public boolean isUnsubscribed() {
            return this.h.get();
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0, long j, TimeUnit timeUnit) {
            d dVar = new d(action0, j, timeUnit);
            this.j.onNext(dVar);
            return dVar;
        }

        @Override // rx.Subscription
        public void unsubscribe() {
            if (this.h.compareAndSet(false, true)) {
                this.i.unsubscribe();
                this.j.onCompleted();
            }
        }

        @Override // rx.Scheduler.Worker
        public Subscription schedule(Action0 action0) {
            e eVar = new e(action0);
            this.j.onNext(eVar);
            return eVar;
        }
    }
}
