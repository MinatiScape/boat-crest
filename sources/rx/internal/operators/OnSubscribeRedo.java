package rx.internal.operators;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import rx.Notification;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.producers.ProducerArbiter;
import rx.observers.Subscribers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;
import rx.subscriptions.SerialSubscription;
/* loaded from: classes13.dex */
public final class OnSubscribeRedo<T> implements Observable.OnSubscribe<T> {
    public static final Func1<Observable<? extends Notification<?>>, Observable<?>> m = new a();
    public final Observable<T> h;
    public final Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> i;
    public final boolean j;
    public final boolean k;
    public final Scheduler l;

    /* loaded from: classes13.dex */
    public static final class RedoFinite implements Func1<Observable<? extends Notification<?>>, Observable<?>> {
        public final long h;

        /* loaded from: classes13.dex */
        public class a implements Func1<Notification<?>, Notification<?>> {
            public int h;

            public a() {
            }

            @Override // rx.functions.Func1
            /* renamed from: a */
            public Notification<?> call(Notification<?> notification) {
                long j = RedoFinite.this.h;
                if (j == 0) {
                    return notification;
                }
                int i = this.h + 1;
                this.h = i;
                return ((long) i) <= j ? Notification.createOnNext(Integer.valueOf(i)) : notification;
            }
        }

        public RedoFinite(long j) {
            this.h = j;
        }

        @Override // rx.functions.Func1
        public Observable<?> call(Observable<? extends Notification<?>> observable) {
            return observable.map(new a()).dematerialize();
        }
    }

    /* loaded from: classes13.dex */
    public static final class RetryWithPredicate implements Func1<Observable<? extends Notification<?>>, Observable<? extends Notification<?>>> {
        public final Func2<Integer, Throwable, Boolean> h;

        /* loaded from: classes13.dex */
        public class a implements Func2<Notification<Integer>, Notification<?>, Notification<Integer>> {
            public a() {
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.functions.Func2
            /* renamed from: a */
            public Notification<Integer> call(Notification<Integer> notification, Notification<?> notification2) {
                int intValue = notification.getValue().intValue();
                return RetryWithPredicate.this.h.call(Integer.valueOf(intValue), notification2.getThrowable()).booleanValue() ? Notification.createOnNext(Integer.valueOf(intValue + 1)) : notification2;
            }
        }

        public RetryWithPredicate(Func2<Integer, Throwable, Boolean> func2) {
            this.h = func2;
        }

        @Override // rx.functions.Func1
        public Observable<? extends Notification<?>> call(Observable<? extends Notification<?>> observable) {
            return observable.scan(Notification.createOnNext(0), new a());
        }
    }

    /* loaded from: classes13.dex */
    public static class a implements Func1<Observable<? extends Notification<?>>, Observable<?>> {

        /* renamed from: rx.internal.operators.OnSubscribeRedo$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0943a implements Func1<Notification<?>, Notification<?>> {
            public C0943a(a aVar) {
            }

            @Override // rx.functions.Func1
            /* renamed from: a */
            public Notification<?> call(Notification<?> notification) {
                return Notification.createOnNext(null);
            }
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable<?> call(Observable<? extends Notification<?>> observable) {
            return observable.map(new C0943a(this));
        }
    }

    /* loaded from: classes13.dex */
    public class b implements Action0 {
        public final /* synthetic */ Subscriber h;
        public final /* synthetic */ Subject i;
        public final /* synthetic */ ProducerArbiter j;
        public final /* synthetic */ AtomicLong k;
        public final /* synthetic */ SerialSubscription l;

        /* loaded from: classes13.dex */
        public class a extends Subscriber<T> {
            public boolean l;

            public a() {
            }

            public final void b() {
                long j;
                do {
                    j = b.this.k.get();
                    if (j == Long.MAX_VALUE) {
                        return;
                    }
                } while (!b.this.k.compareAndSet(j, j - 1));
            }

            @Override // rx.Observer
            public void onCompleted() {
                if (this.l) {
                    return;
                }
                this.l = true;
                unsubscribe();
                b.this.i.onNext(Notification.createOnCompleted());
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                if (this.l) {
                    return;
                }
                this.l = true;
                unsubscribe();
                b.this.i.onNext(Notification.createOnError(th));
            }

            @Override // rx.Observer
            public void onNext(T t) {
                if (this.l) {
                    return;
                }
                b.this.h.onNext(t);
                b();
                b.this.j.produced(1L);
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                b.this.j.setProducer(producer);
            }
        }

        public b(Subscriber subscriber, Subject subject, ProducerArbiter producerArbiter, AtomicLong atomicLong, SerialSubscription serialSubscription) {
            this.h = subscriber;
            this.i = subject;
            this.j = producerArbiter;
            this.k = atomicLong;
            this.l = serialSubscription;
        }

        @Override // rx.functions.Action0
        public void call() {
            if (this.h.isUnsubscribed()) {
                return;
            }
            a aVar = new a();
            this.l.set(aVar);
            OnSubscribeRedo.this.h.unsafeSubscribe(aVar);
        }
    }

    /* loaded from: classes13.dex */
    public class c implements Observable.Operator<Notification<?>, Notification<?>> {

        /* loaded from: classes13.dex */
        public class a extends Subscriber<Notification<?>> {
            public final /* synthetic */ Subscriber l;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(Subscriber subscriber, Subscriber subscriber2) {
                super(subscriber);
                this.l = subscriber2;
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.l.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                this.l.onError(th);
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                producer.request(Long.MAX_VALUE);
            }

            @Override // rx.Observer
            public void onNext(Notification<?> notification) {
                if (notification.isOnCompleted() && OnSubscribeRedo.this.j) {
                    this.l.onCompleted();
                } else if (notification.isOnError() && OnSubscribeRedo.this.k) {
                    this.l.onError(notification.getThrowable());
                } else {
                    this.l.onNext(notification);
                }
            }
        }

        public c() {
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Subscriber<? super Notification<?>> call(Subscriber<? super Notification<?>> subscriber) {
            return new a(subscriber, subscriber);
        }
    }

    /* loaded from: classes13.dex */
    public class d implements Action0 {
        public final /* synthetic */ Observable h;
        public final /* synthetic */ Subscriber i;
        public final /* synthetic */ AtomicLong j;
        public final /* synthetic */ Scheduler.Worker k;
        public final /* synthetic */ Action0 l;
        public final /* synthetic */ AtomicBoolean m;

        /* loaded from: classes13.dex */
        public class a extends Subscriber<Object> {
            public a(Subscriber subscriber) {
                super(subscriber);
            }

            @Override // rx.Observer
            public void onCompleted() {
                d.this.i.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                d.this.i.onError(th);
            }

            @Override // rx.Observer
            public void onNext(Object obj) {
                if (d.this.i.isUnsubscribed()) {
                    return;
                }
                if (d.this.j.get() > 0) {
                    d dVar = d.this;
                    dVar.k.schedule(dVar.l);
                    return;
                }
                d.this.m.compareAndSet(false, true);
            }

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void setProducer(Producer producer) {
                producer.request(Long.MAX_VALUE);
            }
        }

        public d(OnSubscribeRedo onSubscribeRedo, Observable observable, Subscriber subscriber, AtomicLong atomicLong, Scheduler.Worker worker, Action0 action0, AtomicBoolean atomicBoolean) {
            this.h = observable;
            this.i = subscriber;
            this.j = atomicLong;
            this.k = worker;
            this.l = action0;
            this.m = atomicBoolean;
        }

        @Override // rx.functions.Action0
        public void call() {
            this.h.unsafeSubscribe(new a(this.i));
        }
    }

    /* loaded from: classes13.dex */
    public class e implements Producer {
        public final /* synthetic */ AtomicLong h;
        public final /* synthetic */ ProducerArbiter i;
        public final /* synthetic */ AtomicBoolean j;
        public final /* synthetic */ Scheduler.Worker k;
        public final /* synthetic */ Action0 l;

        public e(OnSubscribeRedo onSubscribeRedo, AtomicLong atomicLong, ProducerArbiter producerArbiter, AtomicBoolean atomicBoolean, Scheduler.Worker worker, Action0 action0) {
            this.h = atomicLong;
            this.i = producerArbiter;
            this.j = atomicBoolean;
            this.k = worker;
            this.l = action0;
        }

        @Override // rx.Producer
        public void request(long j) {
            if (j > 0) {
                BackpressureUtils.getAndAddRequest(this.h, j);
                this.i.request(j);
                if (this.j.compareAndSet(true, false)) {
                    this.k.schedule(this.l);
                }
            }
        }
    }

    public OnSubscribeRedo(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, boolean z, boolean z2, Scheduler scheduler) {
        this.h = observable;
        this.i = func1;
        this.j = z;
        this.k = z2;
        this.l = scheduler;
    }

    public static <T> Observable<T> redo(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, Scheduler scheduler) {
        return Observable.unsafeCreate(new OnSubscribeRedo(observable, func1, false, false, scheduler));
    }

    public static <T> Observable<T> repeat(Observable<T> observable) {
        return repeat(observable, Schedulers.trampoline());
    }

    public static <T> Observable<T> retry(Observable<T> observable) {
        return retry(observable, m);
    }

    @Override // rx.functions.Action1
    public /* bridge */ /* synthetic */ void call(Object obj) {
        call((Subscriber) ((Subscriber) obj));
    }

    public static <T> Observable<T> repeat(Observable<T> observable, Scheduler scheduler) {
        return repeat(observable, m, scheduler);
    }

    public static <T> Observable<T> retry(Observable<T> observable, long j) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i >= 0) {
            return i == 0 ? observable : retry(observable, new RedoFinite(j));
        }
        throw new IllegalArgumentException("count >= 0 expected");
    }

    public void call(Subscriber<? super T> subscriber) {
        AtomicBoolean atomicBoolean = new AtomicBoolean(true);
        AtomicLong atomicLong = new AtomicLong();
        Scheduler.Worker createWorker = this.l.createWorker();
        subscriber.add(createWorker);
        SerialSubscription serialSubscription = new SerialSubscription();
        subscriber.add(serialSubscription);
        SerializedSubject<T, T> serialized = BehaviorSubject.create().toSerialized();
        serialized.subscribe((Subscriber) Subscribers.empty());
        ProducerArbiter producerArbiter = new ProducerArbiter();
        b bVar = new b(subscriber, serialized, producerArbiter, atomicLong, serialSubscription);
        createWorker.schedule(new d(this, this.i.call(serialized.lift(new c())), subscriber, atomicLong, createWorker, bVar, atomicBoolean));
        subscriber.setProducer(new e(this, atomicLong, producerArbiter, atomicBoolean, createWorker, bVar));
    }

    public static <T> Observable<T> repeat(Observable<T> observable, long j) {
        return repeat(observable, j, Schedulers.trampoline());
    }

    public static <T> Observable<T> repeat(Observable<T> observable, long j, Scheduler scheduler) {
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (i == 0) {
            return Observable.empty();
        }
        if (i >= 0) {
            return repeat(observable, new RedoFinite(j - 1), scheduler);
        }
        throw new IllegalArgumentException("count >= 0 expected");
    }

    public static <T> Observable<T> retry(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1) {
        return Observable.unsafeCreate(new OnSubscribeRedo(observable, func1, true, false, Schedulers.trampoline()));
    }

    public static <T> Observable<T> retry(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, Scheduler scheduler) {
        return Observable.unsafeCreate(new OnSubscribeRedo(observable, func1, true, false, scheduler));
    }

    public static <T> Observable<T> repeat(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1) {
        return Observable.unsafeCreate(new OnSubscribeRedo(observable, func1, false, true, Schedulers.trampoline()));
    }

    public static <T> Observable<T> repeat(Observable<T> observable, Func1<? super Observable<? extends Notification<?>>, ? extends Observable<?>> func1, Scheduler scheduler) {
        return Observable.unsafeCreate(new OnSubscribeRedo(observable, func1, false, true, scheduler));
    }
}
