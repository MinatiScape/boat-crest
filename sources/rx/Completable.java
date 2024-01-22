package rx;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.exceptions.CompositeException;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.observers.AssertableSubscriberObservable;
import rx.internal.operators.CompletableFromEmitter;
import rx.internal.operators.CompletableOnSubscribeConcat;
import rx.internal.operators.CompletableOnSubscribeConcatArray;
import rx.internal.operators.CompletableOnSubscribeConcatIterable;
import rx.internal.operators.CompletableOnSubscribeMerge;
import rx.internal.operators.CompletableOnSubscribeMergeArray;
import rx.internal.operators.CompletableOnSubscribeMergeDelayErrorArray;
import rx.internal.operators.CompletableOnSubscribeMergeDelayErrorIterable;
import rx.internal.operators.CompletableOnSubscribeMergeIterable;
import rx.internal.operators.CompletableOnSubscribeTimeout;
import rx.internal.util.SubscriptionList;
import rx.internal.util.UtilityFunctions;
import rx.observers.AssertableSubscriber;
import rx.observers.SafeCompletableSubscriber;
import rx.observers.SafeSubscriber;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;
import rx.subscriptions.BooleanSubscription;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.MultipleAssignmentSubscription;
import rx.subscriptions.SerialSubscription;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public class Completable {
    public static final Completable b = new Completable(new k(), false);
    public static final Completable c = new Completable(new v(), false);

    /* renamed from: a  reason: collision with root package name */
    public final OnSubscribe f15648a;

    /* loaded from: classes13.dex */
    public interface OnSubscribe extends Action1<CompletableSubscriber> {
    }

    /* loaded from: classes13.dex */
    public interface Operator extends Func1<CompletableSubscriber, CompletableSubscriber> {
    }

    /* loaded from: classes13.dex */
    public interface Transformer extends Func1<Completable, Completable> {
    }

    /* loaded from: classes13.dex */
    public static class a implements OnSubscribe {
        public final /* synthetic */ Observable h;

        /* renamed from: rx.Completable$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0924a extends Subscriber<Object> {
            public final /* synthetic */ CompletableSubscriber l;

            public C0924a(a aVar, CompletableSubscriber completableSubscriber) {
                this.l = completableSubscriber;
            }

            @Override // rx.Observer
            public void onCompleted() {
                this.l.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                this.l.onError(th);
            }

            @Override // rx.Observer
            public void onNext(Object obj) {
            }
        }

        public a(Observable observable) {
            this.h = observable;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            C0924a c0924a = new C0924a(this, completableSubscriber);
            completableSubscriber.onSubscribe(c0924a);
            this.h.unsafeSubscribe(c0924a);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public class a0<T> implements Single.OnSubscribe<T> {
        public final /* synthetic */ Func0 h;

        /* loaded from: classes13.dex */
        public class a implements CompletableSubscriber {
            public final /* synthetic */ SingleSubscriber h;

            public a(SingleSubscriber singleSubscriber) {
                this.h = singleSubscriber;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                try {
                    Object call = a0.this.h.call();
                    if (call == null) {
                        this.h.onError(new NullPointerException("The value supplied is null"));
                    } else {
                        this.h.onSuccess(call);
                    }
                } catch (Throwable th) {
                    this.h.onError(th);
                }
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                this.h.onError(th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                this.h.add(subscription);
            }
        }

        public a0(Func0 func0) {
            this.h = func0;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            Completable.this.unsafeSubscribe(new a(singleSubscriber));
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements OnSubscribe {
        public final /* synthetic */ Single h;

        /* loaded from: classes13.dex */
        public class a extends SingleSubscriber<Object> {
            public final /* synthetic */ CompletableSubscriber i;

            public a(b bVar, CompletableSubscriber completableSubscriber) {
                this.i = completableSubscriber;
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                this.i.onError(th);
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(Object obj) {
                this.i.onCompleted();
            }
        }

        public b(Single single) {
            this.h = single;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            a aVar = new a(this, completableSubscriber);
            completableSubscriber.onSubscribe(aVar);
            this.h.subscribe(aVar);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public class b0<T> implements Func0<T> {
        public final /* synthetic */ Object h;

        public b0(Completable completable, Object obj) {
            this.h = obj;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        public T call() {
            return (T) this.h;
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements OnSubscribe {
        public final /* synthetic */ Scheduler h;
        public final /* synthetic */ long i;
        public final /* synthetic */ TimeUnit j;

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public final /* synthetic */ CompletableSubscriber h;
            public final /* synthetic */ Scheduler.Worker i;

            public a(c cVar, CompletableSubscriber completableSubscriber, Scheduler.Worker worker) {
                this.h = completableSubscriber;
                this.i = worker;
            }

            @Override // rx.functions.Action0
            public void call() {
                try {
                    this.h.onCompleted();
                } finally {
                    this.i.unsubscribe();
                }
            }
        }

        public c(Scheduler scheduler, long j, TimeUnit timeUnit) {
            this.h = scheduler;
            this.i = j;
            this.j = timeUnit;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
            completableSubscriber.onSubscribe(multipleAssignmentSubscription);
            if (multipleAssignmentSubscription.isUnsubscribed()) {
                return;
            }
            Scheduler.Worker createWorker = this.h.createWorker();
            multipleAssignmentSubscription.set(createWorker);
            createWorker.schedule(new a(this, completableSubscriber, createWorker), this.i, this.j);
        }
    }

    /* loaded from: classes13.dex */
    public class c0 implements OnSubscribe {
        public final /* synthetic */ Scheduler h;

        /* loaded from: classes13.dex */
        public class a implements CompletableSubscriber {
            public final /* synthetic */ CompletableSubscriber h;

            /* renamed from: rx.Completable$c0$a$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public class C0925a implements Action0 {
                public final /* synthetic */ Subscription h;

                /* renamed from: rx.Completable$c0$a$a$a  reason: collision with other inner class name */
                /* loaded from: classes13.dex */
                public class C0926a implements Action0 {
                    public final /* synthetic */ Scheduler.Worker h;

                    public C0926a(Scheduler.Worker worker) {
                        this.h = worker;
                    }

                    @Override // rx.functions.Action0
                    public void call() {
                        try {
                            C0925a.this.h.unsubscribe();
                        } finally {
                            this.h.unsubscribe();
                        }
                    }
                }

                public C0925a(Subscription subscription) {
                    this.h = subscription;
                }

                @Override // rx.functions.Action0
                public void call() {
                    Scheduler.Worker createWorker = c0.this.h.createWorker();
                    createWorker.schedule(new C0926a(createWorker));
                }
            }

            public a(CompletableSubscriber completableSubscriber) {
                this.h = completableSubscriber;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                this.h.onCompleted();
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                this.h.onError(th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                this.h.onSubscribe(Subscriptions.create(new C0925a(subscription)));
            }
        }

        public c0(Scheduler scheduler) {
            this.h = scheduler;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            Completable.this.unsafeSubscribe(new a(completableSubscriber));
        }
    }

    /* loaded from: classes13.dex */
    public static class d implements OnSubscribe {
        public final /* synthetic */ Func0 h;
        public final /* synthetic */ Func1 i;
        public final /* synthetic */ Action1 j;
        public final /* synthetic */ boolean k;

        /* loaded from: classes13.dex */
        public class a implements CompletableSubscriber {
            public Subscription h;
            public final /* synthetic */ AtomicBoolean i;
            public final /* synthetic */ Object j;
            public final /* synthetic */ CompletableSubscriber k;

            /* renamed from: rx.Completable$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public class C0927a implements Action0 {
                public C0927a() {
                }

                @Override // rx.functions.Action0
                public void call() {
                    a.this.a();
                }
            }

            public a(AtomicBoolean atomicBoolean, Object obj, CompletableSubscriber completableSubscriber) {
                this.i = atomicBoolean;
                this.j = obj;
                this.k = completableSubscriber;
            }

            public void a() {
                this.h.unsubscribe();
                if (this.i.compareAndSet(false, true)) {
                    try {
                        d.this.j.call(this.j);
                    } catch (Throwable th) {
                        RxJavaHooks.onError(th);
                    }
                }
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                if (d.this.k && this.i.compareAndSet(false, true)) {
                    try {
                        d.this.j.call(this.j);
                    } catch (Throwable th) {
                        this.k.onError(th);
                        return;
                    }
                }
                this.k.onCompleted();
                if (d.this.k) {
                    return;
                }
                a();
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                if (d.this.k && this.i.compareAndSet(false, true)) {
                    try {
                        d.this.j.call(this.j);
                    } catch (Throwable th2) {
                        th = new CompositeException(Arrays.asList(th, th2));
                    }
                }
                this.k.onError(th);
                if (d.this.k) {
                    return;
                }
                a();
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                this.h = subscription;
                this.k.onSubscribe(Subscriptions.create(new C0927a()));
            }
        }

        public d(Func0 func0, Func1 func1, Action1 action1, boolean z) {
            this.h = func0;
            this.i = func1;
            this.j = action1;
            this.k = z;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            try {
                Object call = this.h.call();
                try {
                    Completable completable = (Completable) this.i.call(call);
                    if (completable == null) {
                        try {
                            this.j.call(call);
                            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                            completableSubscriber.onError(new NullPointerException("The completable supplied is null"));
                            return;
                        } catch (Throwable th) {
                            Exceptions.throwIfFatal(th);
                            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                            completableSubscriber.onError(new CompositeException(Arrays.asList(new NullPointerException("The completable supplied is null"), th)));
                            return;
                        }
                    }
                    completable.unsafeSubscribe(new a(new AtomicBoolean(), call, completableSubscriber));
                } catch (Throwable th2) {
                    try {
                        this.j.call(call);
                        Exceptions.throwIfFatal(th2);
                        completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                        completableSubscriber.onError(th2);
                    } catch (Throwable th3) {
                        Exceptions.throwIfFatal(th2);
                        Exceptions.throwIfFatal(th3);
                        completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                        completableSubscriber.onError(new CompositeException(Arrays.asList(th2, th3)));
                    }
                }
            } catch (Throwable th4) {
                completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                completableSubscriber.onError(th4);
            }
        }
    }

    /* loaded from: classes13.dex */
    public static class d0 implements OnSubscribe {
        public final /* synthetic */ Iterable h;

        /* loaded from: classes13.dex */
        public class a implements CompletableSubscriber {
            public final /* synthetic */ AtomicBoolean h;
            public final /* synthetic */ CompositeSubscription i;
            public final /* synthetic */ CompletableSubscriber j;

            public a(d0 d0Var, AtomicBoolean atomicBoolean, CompositeSubscription compositeSubscription, CompletableSubscriber completableSubscriber) {
                this.h = atomicBoolean;
                this.i = compositeSubscription;
                this.j = completableSubscriber;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                if (this.h.compareAndSet(false, true)) {
                    this.i.unsubscribe();
                    this.j.onCompleted();
                }
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                if (this.h.compareAndSet(false, true)) {
                    this.i.unsubscribe();
                    this.j.onError(th);
                    return;
                }
                RxJavaHooks.onError(th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                this.i.add(subscription);
            }
        }

        public d0(Iterable iterable) {
            this.h = iterable;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            completableSubscriber.onSubscribe(compositeSubscription);
            try {
                Iterator it = this.h.iterator();
                if (it == null) {
                    completableSubscriber.onError(new NullPointerException("The iterator returned is null"));
                    return;
                }
                AtomicBoolean atomicBoolean = new AtomicBoolean();
                a aVar = new a(this, atomicBoolean, compositeSubscription, completableSubscriber);
                boolean z = true;
                while (!atomicBoolean.get() && !compositeSubscription.isUnsubscribed()) {
                    try {
                        if (!it.hasNext()) {
                            if (z) {
                                completableSubscriber.onCompleted();
                                return;
                            }
                            return;
                        } else if (atomicBoolean.get() || compositeSubscription.isUnsubscribed()) {
                            return;
                        } else {
                            try {
                                Completable completable = (Completable) it.next();
                                if (completable == null) {
                                    Throwable nullPointerException = new NullPointerException("One of the sources is null");
                                    if (atomicBoolean.compareAndSet(false, true)) {
                                        compositeSubscription.unsubscribe();
                                        completableSubscriber.onError(nullPointerException);
                                        return;
                                    }
                                    RxJavaHooks.onError(nullPointerException);
                                    return;
                                } else if (atomicBoolean.get() || compositeSubscription.isUnsubscribed()) {
                                    return;
                                } else {
                                    completable.unsafeSubscribe(aVar);
                                    z = false;
                                }
                            } catch (Throwable th) {
                                if (atomicBoolean.compareAndSet(false, true)) {
                                    compositeSubscription.unsubscribe();
                                    completableSubscriber.onError(th);
                                    return;
                                }
                                RxJavaHooks.onError(th);
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        if (atomicBoolean.compareAndSet(false, true)) {
                            compositeSubscription.unsubscribe();
                            completableSubscriber.onError(th2);
                            return;
                        }
                        RxJavaHooks.onError(th2);
                        return;
                    }
                }
            } catch (Throwable th3) {
                completableSubscriber.onError(th3);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class e implements CompletableSubscriber {
        public final /* synthetic */ CountDownLatch h;
        public final /* synthetic */ Throwable[] i;

        public e(Completable completable, CountDownLatch countDownLatch, Throwable[] thArr) {
            this.h = countDownLatch;
            this.i = thArr;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.h.countDown();
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            this.i[0] = th;
            this.h.countDown();
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
        }
    }

    /* loaded from: classes13.dex */
    public static class e0 implements OnSubscribe {
        public final /* synthetic */ Func0 h;

        public e0(Func0 func0) {
            this.h = func0;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            try {
                Completable completable = (Completable) this.h.call();
                if (completable == null) {
                    completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                    completableSubscriber.onError(new NullPointerException("The completable returned is null"));
                    return;
                }
                completable.unsafeSubscribe(completableSubscriber);
            } catch (Throwable th) {
                completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
                completableSubscriber.onError(th);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class f implements CompletableSubscriber {
        public final /* synthetic */ CountDownLatch h;
        public final /* synthetic */ Throwable[] i;

        public f(Completable completable, CountDownLatch countDownLatch, Throwable[] thArr) {
            this.h = countDownLatch;
            this.i = thArr;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.h.countDown();
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            this.i[0] = th;
            this.h.countDown();
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
        }
    }

    /* loaded from: classes13.dex */
    public static class f0 implements OnSubscribe {
        public final /* synthetic */ Func0 h;

        public f0(Func0 func0) {
            this.h = func0;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
            try {
                th = (Throwable) this.h.call();
            } catch (Throwable th) {
                th = th;
            }
            if (th == null) {
                th = new NullPointerException("The error supplied is null");
            }
            completableSubscriber.onError(th);
        }
    }

    /* loaded from: classes13.dex */
    public class g implements OnSubscribe {
        public final /* synthetic */ Scheduler h;
        public final /* synthetic */ long i;
        public final /* synthetic */ TimeUnit j;
        public final /* synthetic */ boolean k;

        /* loaded from: classes13.dex */
        public class a implements CompletableSubscriber {
            public final /* synthetic */ CompositeSubscription h;
            public final /* synthetic */ Scheduler.Worker i;
            public final /* synthetic */ CompletableSubscriber j;

            /* renamed from: rx.Completable$g$a$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public class C0928a implements Action0 {
                public C0928a() {
                }

                @Override // rx.functions.Action0
                public void call() {
                    try {
                        a.this.j.onCompleted();
                    } finally {
                        a.this.i.unsubscribe();
                    }
                }
            }

            /* loaded from: classes13.dex */
            public class b implements Action0 {
                public final /* synthetic */ Throwable h;

                public b(Throwable th) {
                    this.h = th;
                }

                @Override // rx.functions.Action0
                public void call() {
                    try {
                        a.this.j.onError(this.h);
                    } finally {
                        a.this.i.unsubscribe();
                    }
                }
            }

            public a(CompositeSubscription compositeSubscription, Scheduler.Worker worker, CompletableSubscriber completableSubscriber) {
                this.h = compositeSubscription;
                this.i = worker;
                this.j = completableSubscriber;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                CompositeSubscription compositeSubscription = this.h;
                Scheduler.Worker worker = this.i;
                C0928a c0928a = new C0928a();
                g gVar = g.this;
                compositeSubscription.add(worker.schedule(c0928a, gVar.i, gVar.j));
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                if (g.this.k) {
                    CompositeSubscription compositeSubscription = this.h;
                    Scheduler.Worker worker = this.i;
                    b bVar = new b(th);
                    g gVar = g.this;
                    compositeSubscription.add(worker.schedule(bVar, gVar.i, gVar.j));
                    return;
                }
                this.j.onError(th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                this.h.add(subscription);
                this.j.onSubscribe(this.h);
            }
        }

        public g(Scheduler scheduler, long j, TimeUnit timeUnit, boolean z) {
            this.h = scheduler;
            this.i = j;
            this.j = timeUnit;
            this.k = z;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            Scheduler.Worker createWorker = this.h.createWorker();
            compositeSubscription.add(createWorker);
            Completable.this.unsafeSubscribe(new a(compositeSubscription, createWorker, completableSubscriber));
        }
    }

    /* loaded from: classes13.dex */
    public static class g0 implements OnSubscribe {
        public final /* synthetic */ Throwable h;

        public g0(Throwable th) {
            this.h = th;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
            completableSubscriber.onError(this.h);
        }
    }

    /* loaded from: classes13.dex */
    public class h implements Action1<Throwable> {
        public final /* synthetic */ Action1 h;

        public h(Completable completable, Action1 action1) {
            this.h = action1;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Throwable th) {
            this.h.call(Notification.createOnError(th));
        }
    }

    /* loaded from: classes13.dex */
    public static class h0 implements OnSubscribe {
        public final /* synthetic */ Action0 h;

        public h0(Action0 action0) {
            this.h = action0;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            BooleanSubscription booleanSubscription = new BooleanSubscription();
            completableSubscriber.onSubscribe(booleanSubscription);
            try {
                this.h.call();
                if (booleanSubscription.isUnsubscribed()) {
                    return;
                }
                completableSubscriber.onCompleted();
            } catch (Throwable th) {
                if (booleanSubscription.isUnsubscribed()) {
                    return;
                }
                completableSubscriber.onError(th);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class i implements Action0 {
        public final /* synthetic */ Action1 h;

        public i(Completable completable, Action1 action1) {
            this.h = action1;
        }

        @Override // rx.functions.Action0
        public void call() {
            this.h.call(Notification.createOnCompleted());
        }
    }

    /* loaded from: classes13.dex */
    public static class i0 implements OnSubscribe {
        public final /* synthetic */ Callable h;

        public i0(Callable callable) {
            this.h = callable;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            BooleanSubscription booleanSubscription = new BooleanSubscription();
            completableSubscriber.onSubscribe(booleanSubscription);
            try {
                this.h.call();
                if (booleanSubscription.isUnsubscribed()) {
                    return;
                }
                completableSubscriber.onCompleted();
            } catch (Throwable th) {
                if (booleanSubscription.isUnsubscribed()) {
                    return;
                }
                completableSubscriber.onError(th);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class j implements OnSubscribe {
        public final /* synthetic */ Action0 h;
        public final /* synthetic */ Action0 i;
        public final /* synthetic */ Action1 j;
        public final /* synthetic */ Action1 k;
        public final /* synthetic */ Action0 l;

        /* loaded from: classes13.dex */
        public class a implements CompletableSubscriber {
            public final /* synthetic */ CompletableSubscriber h;

            /* renamed from: rx.Completable$j$a$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public class C0929a implements Action0 {
                public final /* synthetic */ Subscription h;

                public C0929a(Subscription subscription) {
                    this.h = subscription;
                }

                @Override // rx.functions.Action0
                public void call() {
                    try {
                        j.this.l.call();
                    } catch (Throwable th) {
                        RxJavaHooks.onError(th);
                    }
                    this.h.unsubscribe();
                }
            }

            public a(CompletableSubscriber completableSubscriber) {
                this.h = completableSubscriber;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                try {
                    j.this.h.call();
                    this.h.onCompleted();
                    try {
                        j.this.i.call();
                    } catch (Throwable th) {
                        RxJavaHooks.onError(th);
                    }
                } catch (Throwable th2) {
                    this.h.onError(th2);
                }
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                try {
                    j.this.j.call(th);
                } catch (Throwable th2) {
                    th = new CompositeException(Arrays.asList(th, th2));
                }
                this.h.onError(th);
                try {
                    j.this.i.call();
                } catch (Throwable th3) {
                    RxJavaHooks.onError(th3);
                }
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                try {
                    j.this.k.call(subscription);
                    this.h.onSubscribe(Subscriptions.create(new C0929a(subscription)));
                } catch (Throwable th) {
                    subscription.unsubscribe();
                    this.h.onSubscribe(Subscriptions.unsubscribed());
                    this.h.onError(th);
                }
            }
        }

        public j(Action0 action0, Action0 action02, Action1 action1, Action1 action12, Action0 action03) {
            this.h = action0;
            this.i = action02;
            this.j = action1;
            this.k = action12;
            this.l = action03;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            Completable.this.unsafeSubscribe(new a(completableSubscriber));
        }
    }

    /* loaded from: classes13.dex */
    public static class k implements OnSubscribe {
        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
            completableSubscriber.onCompleted();
        }
    }

    /* loaded from: classes13.dex */
    public class l implements Action1<Throwable> {
        public final /* synthetic */ Action0 h;

        public l(Completable completable, Action0 action0) {
            this.h = action0;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Throwable th) {
            this.h.call();
        }
    }

    /* loaded from: classes13.dex */
    public class m implements CompletableSubscriber {
        public final /* synthetic */ CountDownLatch h;
        public final /* synthetic */ Throwable[] i;

        public m(Completable completable, CountDownLatch countDownLatch, Throwable[] thArr) {
            this.h = countDownLatch;
            this.i = thArr;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.h.countDown();
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            this.i[0] = th;
            this.h.countDown();
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
        }
    }

    /* loaded from: classes13.dex */
    public class n implements CompletableSubscriber {
        public final /* synthetic */ CountDownLatch h;
        public final /* synthetic */ Throwable[] i;

        public n(Completable completable, CountDownLatch countDownLatch, Throwable[] thArr) {
            this.h = countDownLatch;
            this.i = thArr;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.h.countDown();
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            this.i[0] = th;
            this.h.countDown();
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
        }
    }

    /* loaded from: classes13.dex */
    public class o implements OnSubscribe {
        public final /* synthetic */ Operator h;

        public o(Operator operator) {
            this.h = operator;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            try {
                Completable.this.unsafeSubscribe(RxJavaHooks.onCompletableLift(this.h).call(completableSubscriber));
            } catch (NullPointerException e) {
                throw e;
            } catch (Throwable th) {
                throw Completable.c(th);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class p implements OnSubscribe {
        public final /* synthetic */ Scheduler h;

        /* loaded from: classes13.dex */
        public class a implements CompletableSubscriber {
            public final /* synthetic */ Scheduler.Worker h;
            public final /* synthetic */ CompletableSubscriber i;
            public final /* synthetic */ SubscriptionList j;

            /* renamed from: rx.Completable$p$a$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public class C0930a implements Action0 {
                public C0930a() {
                }

                @Override // rx.functions.Action0
                public void call() {
                    try {
                        a.this.i.onCompleted();
                    } finally {
                        a.this.j.unsubscribe();
                    }
                }
            }

            /* loaded from: classes13.dex */
            public class b implements Action0 {
                public final /* synthetic */ Throwable h;

                public b(Throwable th) {
                    this.h = th;
                }

                @Override // rx.functions.Action0
                public void call() {
                    try {
                        a.this.i.onError(this.h);
                    } finally {
                        a.this.j.unsubscribe();
                    }
                }
            }

            public a(p pVar, Scheduler.Worker worker, CompletableSubscriber completableSubscriber, SubscriptionList subscriptionList) {
                this.h = worker;
                this.i = completableSubscriber;
                this.j = subscriptionList;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                this.h.schedule(new C0930a());
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                this.h.schedule(new b(th));
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                this.j.add(subscription);
            }
        }

        public p(Scheduler scheduler) {
            this.h = scheduler;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            SubscriptionList subscriptionList = new SubscriptionList();
            Scheduler.Worker createWorker = this.h.createWorker();
            subscriptionList.add(createWorker);
            completableSubscriber.onSubscribe(subscriptionList);
            Completable.this.unsafeSubscribe(new a(this, createWorker, completableSubscriber, subscriptionList));
        }
    }

    /* loaded from: classes13.dex */
    public class q implements OnSubscribe {
        public final /* synthetic */ Func1 h;

        /* loaded from: classes13.dex */
        public class a implements CompletableSubscriber {
            public final /* synthetic */ CompletableSubscriber h;

            public a(CompletableSubscriber completableSubscriber) {
                this.h = completableSubscriber;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                this.h.onCompleted();
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                boolean z = false;
                try {
                    z = ((Boolean) q.this.h.call(th)).booleanValue();
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    th = new CompositeException(Arrays.asList(th, th2));
                }
                if (z) {
                    this.h.onCompleted();
                } else {
                    this.h.onError(th);
                }
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                this.h.onSubscribe(subscription);
            }
        }

        public q(Func1 func1) {
            this.h = func1;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            Completable.this.unsafeSubscribe(new a(completableSubscriber));
        }
    }

    /* loaded from: classes13.dex */
    public class r implements OnSubscribe {
        public final /* synthetic */ Func1 h;

        /* loaded from: classes13.dex */
        public class a implements CompletableSubscriber {
            public final /* synthetic */ CompletableSubscriber h;
            public final /* synthetic */ SerialSubscription i;

            /* renamed from: rx.Completable$r$a$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public class C0931a implements CompletableSubscriber {
                public C0931a() {
                }

                @Override // rx.CompletableSubscriber
                public void onCompleted() {
                    a.this.h.onCompleted();
                }

                @Override // rx.CompletableSubscriber
                public void onError(Throwable th) {
                    a.this.h.onError(th);
                }

                @Override // rx.CompletableSubscriber
                public void onSubscribe(Subscription subscription) {
                    a.this.i.set(subscription);
                }
            }

            public a(CompletableSubscriber completableSubscriber, SerialSubscription serialSubscription) {
                this.h = completableSubscriber;
                this.i = serialSubscription;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                this.h.onCompleted();
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                try {
                    Completable completable = (Completable) r.this.h.call(th);
                    if (completable == null) {
                        this.h.onError(new CompositeException(Arrays.asList(th, new NullPointerException("The completable returned is null"))));
                    } else {
                        completable.unsafeSubscribe(new C0931a());
                    }
                } catch (Throwable th2) {
                    this.h.onError(new CompositeException(Arrays.asList(th, th2)));
                }
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                this.i.set(subscription);
            }
        }

        public r(Func1 func1) {
            this.h = func1;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            SerialSubscription serialSubscription = new SerialSubscription();
            completableSubscriber.onSubscribe(serialSubscription);
            Completable.this.unsafeSubscribe(new a(completableSubscriber, serialSubscription));
        }
    }

    /* loaded from: classes13.dex */
    public class s implements CompletableSubscriber {
        public final /* synthetic */ MultipleAssignmentSubscription h;

        public s(Completable completable, MultipleAssignmentSubscription multipleAssignmentSubscription) {
            this.h = multipleAssignmentSubscription;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.h.unsubscribe();
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            RxJavaHooks.onError(th);
            this.h.unsubscribe();
            Completable.a(th);
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.h.set(subscription);
        }
    }

    /* loaded from: classes13.dex */
    public class t implements CompletableSubscriber {
        public boolean h;
        public final /* synthetic */ Action0 i;
        public final /* synthetic */ MultipleAssignmentSubscription j;

        public t(Completable completable, Action0 action0, MultipleAssignmentSubscription multipleAssignmentSubscription) {
            this.i = action0;
            this.j = multipleAssignmentSubscription;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            if (this.h) {
                return;
            }
            this.h = true;
            try {
                this.i.call();
            } finally {
                try {
                } finally {
                }
            }
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            RxJavaHooks.onError(th);
            this.j.unsubscribe();
            Completable.a(th);
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.j.set(subscription);
        }
    }

    /* loaded from: classes13.dex */
    public class u implements CompletableSubscriber {
        public boolean h;
        public final /* synthetic */ Action0 i;
        public final /* synthetic */ MultipleAssignmentSubscription j;
        public final /* synthetic */ Action1 k;

        public u(Completable completable, Action0 action0, MultipleAssignmentSubscription multipleAssignmentSubscription, Action1 action1) {
            this.i = action0;
            this.j = multipleAssignmentSubscription;
            this.k = action1;
        }

        public void a(Throwable th) {
            try {
                this.k.call(th);
            } finally {
                try {
                } finally {
                }
            }
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            if (this.h) {
                return;
            }
            this.h = true;
            try {
                this.i.call();
                this.j.unsubscribe();
            } catch (Throwable th) {
                a(th);
            }
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            if (!this.h) {
                this.h = true;
                a(th);
                return;
            }
            RxJavaHooks.onError(th);
            Completable.a(th);
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.j.set(subscription);
        }
    }

    /* loaded from: classes13.dex */
    public static class v implements OnSubscribe {
        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            completableSubscriber.onSubscribe(Subscriptions.unsubscribed());
        }
    }

    /* loaded from: classes13.dex */
    public static class w implements OnSubscribe {
        public final /* synthetic */ Completable[] h;

        /* loaded from: classes13.dex */
        public class a implements CompletableSubscriber {
            public final /* synthetic */ AtomicBoolean h;
            public final /* synthetic */ CompositeSubscription i;
            public final /* synthetic */ CompletableSubscriber j;

            public a(w wVar, AtomicBoolean atomicBoolean, CompositeSubscription compositeSubscription, CompletableSubscriber completableSubscriber) {
                this.h = atomicBoolean;
                this.i = compositeSubscription;
                this.j = completableSubscriber;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                if (this.h.compareAndSet(false, true)) {
                    this.i.unsubscribe();
                    this.j.onCompleted();
                }
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                if (this.h.compareAndSet(false, true)) {
                    this.i.unsubscribe();
                    this.j.onError(th);
                    return;
                }
                RxJavaHooks.onError(th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                this.i.add(subscription);
            }
        }

        public w(Completable[] completableArr) {
            this.h = completableArr;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            Completable[] completableArr;
            CompositeSubscription compositeSubscription = new CompositeSubscription();
            completableSubscriber.onSubscribe(compositeSubscription);
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            a aVar = new a(this, atomicBoolean, compositeSubscription, completableSubscriber);
            for (Completable completable : this.h) {
                if (compositeSubscription.isUnsubscribed()) {
                    return;
                }
                if (completable == null) {
                    Throwable nullPointerException = new NullPointerException("One of the sources is null");
                    if (atomicBoolean.compareAndSet(false, true)) {
                        compositeSubscription.unsubscribe();
                        completableSubscriber.onError(nullPointerException);
                        return;
                    }
                    RxJavaHooks.onError(nullPointerException);
                    return;
                } else if (atomicBoolean.get() || compositeSubscription.isUnsubscribed()) {
                    return;
                } else {
                    completable.unsafeSubscribe(aVar);
                }
            }
        }
    }

    /* loaded from: classes13.dex */
    public class x implements CompletableSubscriber {
        public final /* synthetic */ Subscriber h;

        public x(Completable completable, Subscriber subscriber) {
            this.h = subscriber;
        }

        @Override // rx.CompletableSubscriber
        public void onCompleted() {
            this.h.onCompleted();
        }

        @Override // rx.CompletableSubscriber
        public void onError(Throwable th) {
            this.h.onError(th);
        }

        @Override // rx.CompletableSubscriber
        public void onSubscribe(Subscription subscription) {
            this.h.add(subscription);
        }
    }

    /* loaded from: classes13.dex */
    public class y implements OnSubscribe {
        public final /* synthetic */ Scheduler h;

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public final /* synthetic */ CompletableSubscriber h;
            public final /* synthetic */ Scheduler.Worker i;

            public a(CompletableSubscriber completableSubscriber, Scheduler.Worker worker) {
                this.h = completableSubscriber;
                this.i = worker;
            }

            @Override // rx.functions.Action0
            public void call() {
                try {
                    Completable.this.unsafeSubscribe(this.h);
                } finally {
                    this.i.unsubscribe();
                }
            }
        }

        public y(Scheduler scheduler) {
            this.h = scheduler;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(CompletableSubscriber completableSubscriber) {
            Scheduler.Worker createWorker = this.h.createWorker();
            createWorker.schedule(new a(completableSubscriber, createWorker));
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* loaded from: classes13.dex */
    public class z<T> implements Observable.OnSubscribe<T> {
        public z() {
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Subscriber<? super T> subscriber) {
            Completable.this.unsafeSubscribe(subscriber);
        }
    }

    public Completable(OnSubscribe onSubscribe) {
        this.f15648a = RxJavaHooks.onCreate(onSubscribe);
    }

    public static void a(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static Completable amb(Completable... completableArr) {
        b(completableArr);
        if (completableArr.length == 0) {
            return complete();
        }
        if (completableArr.length == 1) {
            return completableArr[0];
        }
        return create(new w(completableArr));
    }

    public static <T> T b(T t2) {
        Objects.requireNonNull(t2);
        return t2;
    }

    public static NullPointerException c(Throwable th) {
        NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
        nullPointerException.initCause(th);
        return nullPointerException;
    }

    public static Completable complete() {
        Completable completable = b;
        OnSubscribe onCreate = RxJavaHooks.onCreate(completable.f15648a);
        return onCreate == completable.f15648a ? completable : new Completable(onCreate, false);
    }

    public static Completable concat(Completable... completableArr) {
        b(completableArr);
        if (completableArr.length == 0) {
            return complete();
        }
        if (completableArr.length == 1) {
            return completableArr[0];
        }
        return create(new CompletableOnSubscribeConcatArray(completableArr));
    }

    public static Completable create(OnSubscribe onSubscribe) {
        b(onSubscribe);
        try {
            return new Completable(onSubscribe);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            RxJavaHooks.onError(th);
            throw c(th);
        }
    }

    public static Completable defer(Func0<? extends Completable> func0) {
        b(func0);
        return create(new e0(func0));
    }

    public static Completable error(Func0<? extends Throwable> func0) {
        b(func0);
        return create(new f0(func0));
    }

    public static Completable fromAction(Action0 action0) {
        b(action0);
        return create(new h0(action0));
    }

    public static Completable fromCallable(Callable<?> callable) {
        b(callable);
        return create(new i0(callable));
    }

    public static Completable fromEmitter(Action1<CompletableEmitter> action1) {
        return create(new CompletableFromEmitter(action1));
    }

    public static Completable fromFuture(Future<?> future) {
        b(future);
        return fromObservable(Observable.from(future));
    }

    public static Completable fromObservable(Observable<?> observable) {
        b(observable);
        return create(new a(observable));
    }

    public static Completable fromSingle(Single<?> single) {
        b(single);
        return create(new b(single));
    }

    public static Completable merge(Completable... completableArr) {
        b(completableArr);
        if (completableArr.length == 0) {
            return complete();
        }
        if (completableArr.length == 1) {
            return completableArr[0];
        }
        return create(new CompletableOnSubscribeMergeArray(completableArr));
    }

    public static Completable merge0(Observable<? extends Completable> observable, int i2, boolean z2) {
        b(observable);
        if (i2 >= 1) {
            return create(new CompletableOnSubscribeMerge(observable, i2, z2));
        }
        throw new IllegalArgumentException("maxConcurrency > 0 required but it was " + i2);
    }

    public static Completable mergeDelayError(Completable... completableArr) {
        b(completableArr);
        return create(new CompletableOnSubscribeMergeDelayErrorArray(completableArr));
    }

    public static Completable never() {
        Completable completable = c;
        OnSubscribe onCreate = RxJavaHooks.onCreate(completable.f15648a);
        return onCreate == completable.f15648a ? completable : new Completable(onCreate, false);
    }

    public static Completable timer(long j2, TimeUnit timeUnit) {
        return timer(j2, timeUnit, Schedulers.computation());
    }

    public static <R> Completable using(Func0<R> func0, Func1<? super R, ? extends Completable> func1, Action1<? super R> action1) {
        return using(func0, func1, action1, true);
    }

    public final Completable ambWith(Completable completable) {
        b(completable);
        return amb(this, completable);
    }

    public final <T> Observable<T> andThen(Observable<T> observable) {
        b(observable);
        return observable.delaySubscription(toObservable());
    }

    public final void await() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Throwable[] thArr = new Throwable[1];
        unsafeSubscribe(new e(this, countDownLatch, thArr));
        if (countDownLatch.getCount() == 0) {
            if (thArr[0] != null) {
                Exceptions.propagate(thArr[0]);
                return;
            }
            return;
        }
        try {
            countDownLatch.await();
            if (thArr[0] != null) {
                Exceptions.propagate(thArr[0]);
            }
        } catch (InterruptedException e2) {
            throw Exceptions.propagate(e2);
        }
    }

    public final Completable compose(Transformer transformer) {
        return (Completable) to(transformer);
    }

    public final Completable concatWith(Completable completable) {
        b(completable);
        return concat(this, completable);
    }

    public final <T> void d(Subscriber<T> subscriber, boolean z2) {
        b(subscriber);
        if (z2) {
            try {
                subscriber.onStart();
            } catch (NullPointerException e2) {
                throw e2;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                Throwable onObservableError = RxJavaHooks.onObservableError(th);
                RxJavaHooks.onError(onObservableError);
                throw c(onObservableError);
            }
        }
        unsafeSubscribe(new x(this, subscriber));
        RxJavaHooks.onObservableReturn(subscriber);
    }

    public final Completable delay(long j2, TimeUnit timeUnit) {
        return delay(j2, timeUnit, Schedulers.computation(), false);
    }

    public final Completable doAfterTerminate(Action0 action0) {
        return doOnLifecycle(Actions.empty(), Actions.empty(), Actions.empty(), action0, Actions.empty());
    }

    public final Completable doOnCompleted(Action0 action0) {
        return doOnLifecycle(Actions.empty(), Actions.empty(), action0, Actions.empty(), Actions.empty());
    }

    public final Completable doOnEach(Action1<Notification<Object>> action1) {
        if (action1 != null) {
            return doOnLifecycle(Actions.empty(), new h(this, action1), new i(this, action1), Actions.empty(), Actions.empty());
        }
        throw new IllegalArgumentException("onNotification is null");
    }

    public final Completable doOnError(Action1<? super Throwable> action1) {
        return doOnLifecycle(Actions.empty(), action1, Actions.empty(), Actions.empty(), Actions.empty());
    }

    public final Completable doOnLifecycle(Action1<? super Subscription> action1, Action1<? super Throwable> action12, Action0 action0, Action0 action02, Action0 action03) {
        b(action1);
        b(action12);
        b(action0);
        b(action02);
        b(action03);
        return create(new j(action0, action02, action12, action1, action03));
    }

    public final Completable doOnSubscribe(Action1<? super Subscription> action1) {
        return doOnLifecycle(action1, Actions.empty(), Actions.empty(), Actions.empty(), Actions.empty());
    }

    public final Completable doOnTerminate(Action0 action0) {
        return doOnLifecycle(Actions.empty(), new l(this, action0), action0, Actions.empty(), Actions.empty());
    }

    public final Completable doOnUnsubscribe(Action0 action0) {
        return doOnLifecycle(Actions.empty(), Actions.empty(), Actions.empty(), Actions.empty(), action0);
    }

    public final Throwable get() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Throwable[] thArr = new Throwable[1];
        unsafeSubscribe(new m(this, countDownLatch, thArr));
        if (countDownLatch.getCount() == 0) {
            return thArr[0];
        }
        try {
            countDownLatch.await();
            return thArr[0];
        } catch (InterruptedException e2) {
            throw Exceptions.propagate(e2);
        }
    }

    public final Completable lift(Operator operator) {
        b(operator);
        return create(new o(operator));
    }

    public final Completable mergeWith(Completable completable) {
        b(completable);
        return merge(this, completable);
    }

    public final Completable observeOn(Scheduler scheduler) {
        b(scheduler);
        return create(new p(scheduler));
    }

    public final Completable onErrorComplete() {
        return onErrorComplete(UtilityFunctions.alwaysTrue());
    }

    public final Completable onErrorResumeNext(Func1<? super Throwable, ? extends Completable> func1) {
        b(func1);
        return create(new r(func1));
    }

    public final Completable repeat() {
        return fromObservable(toObservable().repeat());
    }

    public final Completable repeatWhen(Func1<? super Observable<? extends Void>, ? extends Observable<?>> func1) {
        b(func1);
        return fromObservable(toObservable().repeatWhen(func1));
    }

    public final Completable retry() {
        return fromObservable(toObservable().retry());
    }

    public final Completable retryWhen(Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return fromObservable(toObservable().retryWhen(func1));
    }

    public final Completable startWith(Completable completable) {
        b(completable);
        return concat(completable, this);
    }

    public final Subscription subscribe() {
        MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
        unsafeSubscribe(new s(this, multipleAssignmentSubscription));
        return multipleAssignmentSubscription;
    }

    public final Completable subscribeOn(Scheduler scheduler) {
        b(scheduler);
        return create(new y(scheduler));
    }

    public final AssertableSubscriber<Void> test() {
        AssertableSubscriberObservable create = AssertableSubscriberObservable.create(Long.MAX_VALUE);
        subscribe(create);
        return create;
    }

    public final Completable timeout(long j2, TimeUnit timeUnit) {
        return timeout0(j2, timeUnit, Schedulers.computation(), null);
    }

    public final Completable timeout0(long j2, TimeUnit timeUnit, Scheduler scheduler, Completable completable) {
        b(timeUnit);
        b(scheduler);
        return create(new CompletableOnSubscribeTimeout(this, j2, timeUnit, scheduler, completable));
    }

    public final <R> R to(Func1<? super Completable, R> func1) {
        return func1.call(this);
    }

    public final <T> Observable<T> toObservable() {
        return Observable.unsafeCreate(new z());
    }

    public final <T> Single<T> toSingle(Func0<? extends T> func0) {
        b(func0);
        return Single.create(new a0(func0));
    }

    public final <T> Single<T> toSingleDefault(T t2) {
        b(t2);
        return toSingle(new b0(this, t2));
    }

    public final void unsafeSubscribe(CompletableSubscriber completableSubscriber) {
        b(completableSubscriber);
        try {
            RxJavaHooks.onCompletableStart(this, this.f15648a).call(completableSubscriber);
        } catch (NullPointerException e2) {
            throw e2;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            Throwable onCompletableError = RxJavaHooks.onCompletableError(th);
            RxJavaHooks.onError(onCompletableError);
            throw c(onCompletableError);
        }
    }

    public final Completable unsubscribeOn(Scheduler scheduler) {
        b(scheduler);
        return create(new c0(scheduler));
    }

    public static Completable timer(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        b(timeUnit);
        b(scheduler);
        return create(new c(scheduler, j2, timeUnit));
    }

    public static <R> Completable using(Func0<R> func0, Func1<? super R, ? extends Completable> func1, Action1<? super R> action1, boolean z2) {
        b(func0);
        b(func1);
        b(action1);
        return create(new d(func0, func1, action1, z2));
    }

    public final Completable delay(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return delay(j2, timeUnit, scheduler, false);
    }

    public final Completable onErrorComplete(Func1<? super Throwable, Boolean> func1) {
        b(func1);
        return create(new q(func1));
    }

    public final Completable repeat(long j2) {
        return fromObservable(toObservable().repeat(j2));
    }

    public final Completable retry(Func2<Integer, Throwable, Boolean> func2) {
        return fromObservable(toObservable().retry(func2));
    }

    public final Completable timeout(long j2, TimeUnit timeUnit, Completable completable) {
        b(completable);
        return timeout0(j2, timeUnit, Schedulers.computation(), completable);
    }

    public Completable(OnSubscribe onSubscribe, boolean z2) {
        this.f15648a = z2 ? RxJavaHooks.onCreate(onSubscribe) : onSubscribe;
    }

    public static Completable error(Throwable th) {
        b(th);
        return create(new g0(th));
    }

    public static Completable mergeDelayError(Iterable<? extends Completable> iterable) {
        b(iterable);
        return create(new CompletableOnSubscribeMergeDelayErrorIterable(iterable));
    }

    public final <T> Single<T> andThen(Single<T> single) {
        b(single);
        return single.delaySubscription(toObservable());
    }

    public final Completable delay(long j2, TimeUnit timeUnit, Scheduler scheduler, boolean z2) {
        b(timeUnit);
        b(scheduler);
        return create(new g(scheduler, j2, timeUnit, z2));
    }

    public final Completable retry(long j2) {
        return fromObservable(toObservable().retry(j2));
    }

    public final <T> Observable<T> startWith(Observable<T> observable) {
        b(observable);
        return toObservable().startWith((Observable) observable);
    }

    public final Subscription subscribe(Action0 action0) {
        b(action0);
        MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
        unsafeSubscribe(new t(this, action0, multipleAssignmentSubscription));
        return multipleAssignmentSubscription;
    }

    public final Completable timeout(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return timeout0(j2, timeUnit, scheduler, null);
    }

    public static Completable mergeDelayError(Observable<? extends Completable> observable) {
        return merge0(observable, Integer.MAX_VALUE, true);
    }

    public final Completable andThen(Completable completable) {
        return concatWith(completable);
    }

    public final Completable timeout(long j2, TimeUnit timeUnit, Scheduler scheduler, Completable completable) {
        b(completable);
        return timeout0(j2, timeUnit, scheduler, completable);
    }

    public static Completable mergeDelayError(Observable<? extends Completable> observable, int i2) {
        return merge0(observable, i2, true);
    }

    public final Subscription subscribe(Action0 action0, Action1<? super Throwable> action1) {
        b(action0);
        b(action1);
        MultipleAssignmentSubscription multipleAssignmentSubscription = new MultipleAssignmentSubscription();
        unsafeSubscribe(new u(this, action0, multipleAssignmentSubscription, action1));
        return multipleAssignmentSubscription;
    }

    public static Completable amb(Iterable<? extends Completable> iterable) {
        b(iterable);
        return create(new d0(iterable));
    }

    public static Completable concat(Iterable<? extends Completable> iterable) {
        b(iterable);
        return create(new CompletableOnSubscribeConcatIterable(iterable));
    }

    public static Completable merge(Iterable<? extends Completable> iterable) {
        b(iterable);
        return create(new CompletableOnSubscribeMergeIterable(iterable));
    }

    public final Throwable get(long j2, TimeUnit timeUnit) {
        b(timeUnit);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Throwable[] thArr = new Throwable[1];
        unsafeSubscribe(new n(this, countDownLatch, thArr));
        if (countDownLatch.getCount() == 0) {
            return thArr[0];
        }
        try {
            if (countDownLatch.await(j2, timeUnit)) {
                return thArr[0];
            }
            Exceptions.propagate(new TimeoutException());
            return null;
        } catch (InterruptedException e2) {
            throw Exceptions.propagate(e2);
        }
    }

    public static Completable concat(Observable<? extends Completable> observable) {
        return concat(observable, 2);
    }

    public static Completable merge(Observable<? extends Completable> observable) {
        return merge0(observable, Integer.MAX_VALUE, false);
    }

    public final <T> void unsafeSubscribe(Subscriber<T> subscriber) {
        d(subscriber, true);
    }

    public static Completable concat(Observable<? extends Completable> observable, int i2) {
        b(observable);
        if (i2 >= 1) {
            return create(new CompletableOnSubscribeConcat(observable, i2));
        }
        throw new IllegalArgumentException("prefetch > 0 required but it was " + i2);
    }

    public static Completable merge(Observable<? extends Completable> observable, int i2) {
        return merge0(observable, i2, false);
    }

    public final boolean await(long j2, TimeUnit timeUnit) {
        b(timeUnit);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Throwable[] thArr = new Throwable[1];
        unsafeSubscribe(new f(this, countDownLatch, thArr));
        if (countDownLatch.getCount() == 0) {
            if (thArr[0] != null) {
                Exceptions.propagate(thArr[0]);
            }
            return true;
        }
        try {
            boolean await = countDownLatch.await(j2, timeUnit);
            if (await && thArr[0] != null) {
                Exceptions.propagate(thArr[0]);
            }
            return await;
        } catch (InterruptedException e2) {
            throw Exceptions.propagate(e2);
        }
    }

    public final void subscribe(CompletableSubscriber completableSubscriber) {
        if (!(completableSubscriber instanceof SafeCompletableSubscriber)) {
            completableSubscriber = new SafeCompletableSubscriber(completableSubscriber);
        }
        unsafeSubscribe(completableSubscriber);
    }

    public final <T> void subscribe(Subscriber<T> subscriber) {
        subscriber.onStart();
        if (!(subscriber instanceof SafeSubscriber)) {
            subscriber = new SafeSubscriber(subscriber);
        }
        d(subscriber, false);
    }
}
