package rx.internal.util;

import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Producer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Func1;
import rx.internal.producers.SingleProducer;
import rx.internal.schedulers.EventLoopsScheduler;
import rx.observers.Subscribers;
import rx.plugins.RxJavaHooks;
/* loaded from: classes13.dex */
public final class ScalarSynchronousObservable<T> extends Observable<T> {
    public static final boolean j = Boolean.valueOf(System.getProperty("rx.just.strong-mode", "false")).booleanValue();
    public final T i;

    /* loaded from: classes13.dex */
    public class a implements Func1<Action0, Subscription> {
        public final /* synthetic */ EventLoopsScheduler h;

        public a(ScalarSynchronousObservable scalarSynchronousObservable, EventLoopsScheduler eventLoopsScheduler) {
            this.h = eventLoopsScheduler;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Subscription call(Action0 action0) {
            return this.h.scheduleDirect(action0);
        }
    }

    /* loaded from: classes13.dex */
    public class b implements Func1<Action0, Subscription> {
        public final /* synthetic */ Scheduler h;

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public final /* synthetic */ Action0 h;
            public final /* synthetic */ Scheduler.Worker i;

            public a(b bVar, Action0 action0, Scheduler.Worker worker) {
                this.h = action0;
                this.i = worker;
            }

            @Override // rx.functions.Action0
            public void call() {
                try {
                    this.h.call();
                } finally {
                    this.i.unsubscribe();
                }
            }
        }

        public b(ScalarSynchronousObservable scalarSynchronousObservable, Scheduler scheduler) {
            this.h = scheduler;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Subscription call(Action0 action0) {
            Scheduler.Worker createWorker = this.h.createWorker();
            createWorker.schedule(new a(this, action0, createWorker));
            return createWorker;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public class c<R> implements Observable.OnSubscribe<R> {
        public final /* synthetic */ Func1 h;

        public c(Func1 func1) {
            this.h = func1;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super R> subscriber) {
            Observable observable = (Observable) this.h.call(ScalarSynchronousObservable.this.i);
            if (observable instanceof ScalarSynchronousObservable) {
                subscriber.setProducer(ScalarSynchronousObservable.c(subscriber, ((ScalarSynchronousObservable) observable).i));
            } else {
                observable.unsafeSubscribe(Subscribers.wrap(subscriber));
            }
        }
    }

    /* loaded from: classes13.dex */
    public static final class d<T> implements Observable.OnSubscribe<T> {
        public final T h;

        public d(T t) {
            this.h = t;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            subscriber.setProducer(ScalarSynchronousObservable.c(subscriber, this.h));
        }
    }

    /* loaded from: classes13.dex */
    public static final class e<T> implements Observable.OnSubscribe<T> {
        public final T h;
        public final Func1<Action0, Subscription> i;

        public e(T t, Func1<Action0, Subscription> func1) {
            this.h = t;
            this.i = func1;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            subscriber.setProducer(new f(subscriber, this.h, this.i));
        }
    }

    /* loaded from: classes13.dex */
    public static final class f<T> extends AtomicBoolean implements Producer, Action0 {
        private static final long serialVersionUID = -2466317989629281651L;
        public final Subscriber<? super T> actual;
        public final Func1<Action0, Subscription> onSchedule;
        public final T value;

        public f(Subscriber<? super T> subscriber, T t, Func1<Action0, Subscription> func1) {
            this.actual = subscriber;
            this.value = t;
            this.onSchedule = func1;
        }

        @Override // rx.functions.Action0
        public void call() {
            Subscriber<? super T> subscriber = this.actual;
            if (subscriber.isUnsubscribed()) {
                return;
            }
            Object obj = (T) this.value;
            try {
                subscriber.onNext(obj);
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                subscriber.onCompleted();
            } catch (Throwable th) {
                Exceptions.throwOrReport(th, subscriber, obj);
            }
        }

        @Override // rx.Producer
        public void request(long j) {
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i >= 0) {
                if (i == 0 || !compareAndSet(false, true)) {
                    return;
                }
                this.actual.add(this.onSchedule.call(this));
                return;
            }
            throw new IllegalArgumentException("n >= 0 required but it was " + j);
        }

        @Override // java.util.concurrent.atomic.AtomicBoolean
        public String toString() {
            return "ScalarAsyncProducer[" + this.value + ", " + get() + "]";
        }
    }

    /* loaded from: classes13.dex */
    public static final class g<T> implements Producer {
        public final Subscriber<? super T> h;
        public final T i;
        public boolean j;

        public g(Subscriber<? super T> subscriber, T t) {
            this.h = subscriber;
            this.i = t;
        }

        @Override // rx.Producer
        public void request(long j) {
            if (this.j) {
                return;
            }
            int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
            if (i < 0) {
                throw new IllegalStateException("n >= required but it was " + j);
            } else if (i == 0) {
            } else {
                this.j = true;
                Subscriber<? super T> subscriber = this.h;
                if (subscriber.isUnsubscribed()) {
                    return;
                }
                Object obj = (T) this.i;
                try {
                    subscriber.onNext(obj);
                    if (subscriber.isUnsubscribed()) {
                        return;
                    }
                    subscriber.onCompleted();
                } catch (Throwable th) {
                    Exceptions.throwOrReport(th, subscriber, obj);
                }
            }
        }
    }

    public ScalarSynchronousObservable(T t) {
        super(RxJavaHooks.onCreate(new d(t)));
        this.i = t;
    }

    public static <T> Producer c(Subscriber<? super T> subscriber, T t) {
        if (j) {
            return new SingleProducer(subscriber, t);
        }
        return new g(subscriber, t);
    }

    public static <T> ScalarSynchronousObservable<T> create(T t) {
        return new ScalarSynchronousObservable<>(t);
    }

    public T get() {
        return this.i;
    }

    public <R> Observable<R> scalarFlatMap(Func1<? super T, ? extends Observable<? extends R>> func1) {
        return Observable.unsafeCreate(new c(func1));
    }

    public Observable<T> scalarScheduleOn(Scheduler scheduler) {
        Func1 bVar;
        if (scheduler instanceof EventLoopsScheduler) {
            bVar = new a(this, (EventLoopsScheduler) scheduler);
        } else {
            bVar = new b(this, scheduler);
        }
        return Observable.unsafeCreate(new e(this.i, bVar));
    }
}
