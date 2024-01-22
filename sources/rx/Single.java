package rx;

import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import rx.Observable;
import rx.Scheduler;
import rx.annotations.Experimental;
import rx.exceptions.Exceptions;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.Func4;
import rx.functions.Func5;
import rx.functions.Func6;
import rx.functions.Func7;
import rx.functions.Func8;
import rx.functions.Func9;
import rx.functions.FuncN;
import rx.internal.observers.AssertableSubscriberObservable;
import rx.internal.operators.CompletableFlatMapSingleToCompletable;
import rx.internal.operators.SingleDelay;
import rx.internal.operators.SingleDoAfterTerminate;
import rx.internal.operators.SingleDoOnEvent;
import rx.internal.operators.SingleDoOnSubscribe;
import rx.internal.operators.SingleDoOnUnsubscribe;
import rx.internal.operators.SingleFromCallable;
import rx.internal.operators.SingleFromEmitter;
import rx.internal.operators.SingleFromFuture;
import rx.internal.operators.SingleFromObservable;
import rx.internal.operators.SingleLiftObservableOperator;
import rx.internal.operators.SingleObserveOn;
import rx.internal.operators.SingleOnErrorReturn;
import rx.internal.operators.SingleOnSubscribeDelaySubscriptionOther;
import rx.internal.operators.SingleOnSubscribeMap;
import rx.internal.operators.SingleOnSubscribeUsing;
import rx.internal.operators.SingleOperatorOnErrorResumeNext;
import rx.internal.operators.SingleOperatorZip;
import rx.internal.operators.SingleTakeUntilCompletable;
import rx.internal.operators.SingleTakeUntilObservable;
import rx.internal.operators.SingleTakeUntilSingle;
import rx.internal.operators.SingleTimeout;
import rx.internal.operators.SingleToObservable;
import rx.internal.util.ScalarSynchronousSingle;
import rx.internal.util.UtilityFunctions;
import rx.observers.AssertableSubscriber;
import rx.observers.SafeSubscriber;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;
import rx.singles.BlockingSingle;
import rx.subscriptions.Subscriptions;
/* loaded from: classes13.dex */
public class Single<T> {

    /* renamed from: a  reason: collision with root package name */
    public final OnSubscribe<T> f15650a;

    /* loaded from: classes13.dex */
    public interface OnSubscribe<T> extends Action1<SingleSubscriber<? super T>> {
    }

    /* loaded from: classes13.dex */
    public interface Transformer<T, R> extends Func1<Single<T>, Single<R>> {
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class a<R> implements FuncN<R> {
        public final /* synthetic */ Func9 h;

        public a(Func9 func9) {
            this.h = func9;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7], objArr[8]);
        }
    }

    /* loaded from: classes13.dex */
    public class b extends SingleSubscriber<T> {
        public final /* synthetic */ Action1 i;
        public final /* synthetic */ Action1 j;

        public b(Single single, Action1 action1, Action1 action12) {
            this.i = action1;
            this.j = action12;
        }

        @Override // rx.SingleSubscriber
        public final void onError(Throwable th) {
            try {
                this.i.call(th);
            } finally {
                unsubscribe();
            }
        }

        @Override // rx.SingleSubscriber
        public final void onSuccess(T t) {
            try {
                this.j.call(t);
            } finally {
                unsubscribe();
            }
        }
    }

    /* loaded from: classes13.dex */
    public class c extends SingleSubscriber<T> {
        public final /* synthetic */ Observer i;

        public c(Single single, Observer observer) {
            this.i = observer;
        }

        @Override // rx.SingleSubscriber
        public void onError(Throwable th) {
            this.i.onError(th);
        }

        @Override // rx.SingleSubscriber
        public void onSuccess(T t) {
            this.i.onNext(t);
            this.i.onCompleted();
        }
    }

    /* loaded from: classes13.dex */
    public class d implements OnSubscribe<T> {
        public final /* synthetic */ Scheduler h;

        /* loaded from: classes13.dex */
        public class a implements Action0 {
            public final /* synthetic */ SingleSubscriber h;
            public final /* synthetic */ Scheduler.Worker i;

            /* renamed from: rx.Single$d$a$a  reason: collision with other inner class name */
            /* loaded from: classes13.dex */
            public class C0932a extends SingleSubscriber<T> {
                public C0932a() {
                }

                @Override // rx.SingleSubscriber
                public void onError(Throwable th) {
                    try {
                        a.this.h.onError(th);
                    } finally {
                        a.this.i.unsubscribe();
                    }
                }

                @Override // rx.SingleSubscriber
                public void onSuccess(T t) {
                    try {
                        a.this.h.onSuccess(t);
                    } finally {
                        a.this.i.unsubscribe();
                    }
                }
            }

            public a(SingleSubscriber singleSubscriber, Scheduler.Worker worker) {
                this.h = singleSubscriber;
                this.i = worker;
            }

            @Override // rx.functions.Action0
            public void call() {
                C0932a c0932a = new C0932a();
                this.h.add(c0932a);
                Single.this.subscribe(c0932a);
            }
        }

        public d(Scheduler scheduler) {
            this.h = scheduler;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            Scheduler.Worker createWorker = this.h.createWorker();
            singleSubscriber.add(createWorker);
            createWorker.schedule(new a(singleSubscriber, createWorker));
        }
    }

    /* loaded from: classes13.dex */
    public class e implements Func0<Single<T>> {
        public e(Single single) {
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        /* renamed from: a */
        public Single<T> call() {
            return Single.error(new TimeoutException());
        }
    }

    /* loaded from: classes13.dex */
    public class f implements Action1<Throwable> {
        public final /* synthetic */ Action1 h;

        public f(Single single, Action1 action1) {
            this.h = action1;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Throwable th) {
            this.h.call(th);
        }
    }

    /* loaded from: classes13.dex */
    public class g implements Action1<T> {
        public final /* synthetic */ Action1 h;

        public g(Single single, Action1 action1) {
            this.h = action1;
        }

        @Override // rx.functions.Action1
        public void call(T t) {
            this.h.call(Notification.createOnNext(t));
        }
    }

    /* loaded from: classes13.dex */
    public class h implements Action1<Throwable> {
        public final /* synthetic */ Action1 h;

        public h(Single single, Action1 action1) {
            this.h = action1;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Throwable th) {
            this.h.call(Notification.createOnError(th));
        }
    }

    /* loaded from: classes13.dex */
    public static class i implements OnSubscribe<T> {
        public final /* synthetic */ Callable h;

        public i(Callable callable) {
            this.h = callable;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            try {
                ((Single) this.h.call()).subscribe(singleSubscriber);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                singleSubscriber.onError(th);
            }
        }
    }

    /* loaded from: classes13.dex */
    public class j implements OnSubscribe<T> {
        public final /* synthetic */ Scheduler h;

        /* loaded from: classes13.dex */
        public class a extends SingleSubscriber<T> {
            public final /* synthetic */ SingleSubscriber i;

            public a(j jVar, SingleSubscriber singleSubscriber) {
                this.i = singleSubscriber;
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                this.i.onError(th);
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(T t) {
                this.i.onSuccess(t);
            }
        }

        /* loaded from: classes13.dex */
        public class b implements Action0 {
            public final /* synthetic */ SingleSubscriber h;

            /* loaded from: classes13.dex */
            public class a implements Action0 {
                public final /* synthetic */ Scheduler.Worker h;

                public a(Scheduler.Worker worker) {
                    this.h = worker;
                }

                @Override // rx.functions.Action0
                public void call() {
                    try {
                        b.this.h.unsubscribe();
                    } finally {
                        this.h.unsubscribe();
                    }
                }
            }

            public b(SingleSubscriber singleSubscriber) {
                this.h = singleSubscriber;
            }

            @Override // rx.functions.Action0
            public void call() {
                Scheduler.Worker createWorker = j.this.h.createWorker();
                createWorker.schedule(new a(createWorker));
            }
        }

        public j(Scheduler scheduler) {
            this.h = scheduler;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            a aVar = new a(this, singleSubscriber);
            singleSubscriber.add(Subscriptions.create(new b(aVar)));
            Single.this.subscribe(aVar);
        }
    }

    /* loaded from: classes13.dex */
    public static class k implements OnSubscribe<T> {
        public final /* synthetic */ Throwable h;

        public k(Throwable th) {
            this.h = th;
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            singleSubscriber.onError(this.h);
        }
    }

    /* loaded from: classes13.dex */
    public static class l implements OnSubscribe<T> {

        /* loaded from: classes13.dex */
        public class a extends SingleSubscriber<Single<? extends T>> {
            public final /* synthetic */ SingleSubscriber i;

            public a(l lVar, SingleSubscriber singleSubscriber) {
                this.i = singleSubscriber;
            }

            @Override // rx.SingleSubscriber
            /* renamed from: a */
            public void onSuccess(Single<? extends T> single) {
                single.subscribe(this.i);
            }

            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                this.i.onError(th);
            }
        }

        public l() {
        }

        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            a aVar = new a(this, singleSubscriber);
            singleSubscriber.add(aVar);
            Single.this.subscribe(aVar);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class m<R> implements FuncN<R> {
        public final /* synthetic */ Func2 h;

        public m(Func2 func2) {
            this.h = func2;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            return (R) this.h.call(objArr[0], objArr[1]);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class n<R> implements FuncN<R> {
        public final /* synthetic */ Func3 h;

        public n(Func3 func3) {
            this.h = func3;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            return (R) this.h.call(objArr[0], objArr[1], objArr[2]);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class o<R> implements FuncN<R> {
        public final /* synthetic */ Func4 h;

        public o(Func4 func4) {
            this.h = func4;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3]);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class p<R> implements FuncN<R> {
        public final /* synthetic */ Func5 h;

        public p(Func5 func5) {
            this.h = func5;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4]);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class q<R> implements FuncN<R> {
        public final /* synthetic */ Func6 h;

        public q(Func6 func6) {
            this.h = func6;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5]);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class r<R> implements FuncN<R> {
        public final /* synthetic */ Func7 h;

        public r(Func7 func7) {
            this.h = func7;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6]);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* loaded from: classes13.dex */
    public static class s<R> implements FuncN<R> {
        public final /* synthetic */ Func8 h;

        public s(Func8 func8) {
            this.h = func8;
        }

        @Override // rx.functions.FuncN
        public R call(Object... objArr) {
            return (R) this.h.call(objArr[0], objArr[1], objArr[2], objArr[3], objArr[4], objArr[5], objArr[6], objArr[7]);
        }
    }

    public Single(OnSubscribe<T> onSubscribe) {
        this.f15650a = RxJavaHooks.onCreate(onSubscribe);
    }

    public static <T> Observable<T> a(Single<T> single) {
        return Observable.unsafeCreate(new SingleToObservable(single.f15650a));
    }

    public static <T> Single<? extends T>[] b(Iterable<? extends Single<? extends T>> iterable) {
        if (iterable instanceof Collection) {
            Collection collection = (Collection) iterable;
            return (Single[]) collection.toArray(new Single[collection.size()]);
        }
        Single<? extends T>[] singleArr = new Single[8];
        int i2 = 0;
        for (Single<? extends T> single : iterable) {
            if (i2 == singleArr.length) {
                Single<? extends T>[] singleArr2 = new Single[(i2 >> 2) + i2];
                System.arraycopy(singleArr, 0, singleArr2, 0, i2);
                singleArr = singleArr2;
            }
            singleArr[i2] = single;
            i2++;
        }
        if (singleArr.length == i2) {
            return singleArr;
        }
        Single<? extends T>[] singleArr3 = new Single[i2];
        System.arraycopy(singleArr, 0, singleArr3, 0, i2);
        return singleArr3;
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2) {
        return Observable.concat(a(single), a(single2));
    }

    public static <T> Single<T> create(OnSubscribe<T> onSubscribe) {
        return new Single<>(onSubscribe);
    }

    public static <T> Single<T> defer(Callable<Single<T>> callable) {
        return create(new i(callable));
    }

    public static <T> Single<T> error(Throwable th) {
        return create(new k(th));
    }

    public static <T> Single<T> from(Future<? extends T> future) {
        return create(new SingleFromFuture(future, 0L, null));
    }

    public static <T> Single<T> fromCallable(Callable<? extends T> callable) {
        return create(new SingleFromCallable(callable));
    }

    public static <T> Single<T> fromEmitter(Action1<SingleEmitter<T>> action1) {
        Objects.requireNonNull(action1, "producer is null");
        return create(new SingleFromEmitter(action1));
    }

    public static <T> Single<T> just(T t) {
        return ScalarSynchronousSingle.create(t);
    }

    public static <T> Single<T> merge(Single<? extends Single<? extends T>> single) {
        if (single instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) single).scalarFlatMap(UtilityFunctions.identity());
        }
        return create(new l());
    }

    public static <T> Observable<T> mergeDelayError(Observable<? extends Single<? extends T>> observable) {
        return merge(observable, Integer.MAX_VALUE);
    }

    public static <T, Resource> Single<T> using(Func0<Resource> func0, Func1<? super Resource, ? extends Single<? extends T>> func1, Action1<? super Resource> action1) {
        return using(func0, func1, action1, false);
    }

    public static <T1, T2, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Func2<? super T1, ? super T2, ? extends R> func2) {
        return SingleOperatorZip.zip(new Single[]{single, single2}, new m(func2));
    }

    public final Subscription c(Subscriber<? super T> subscriber, boolean z) {
        if (z) {
            try {
                subscriber.onStart();
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                try {
                    subscriber.onError(RxJavaHooks.onSingleError(th));
                    return Subscriptions.unsubscribed();
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    RuntimeException runtimeException = new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                    RxJavaHooks.onSingleError(runtimeException);
                    throw runtimeException;
                }
            }
        }
        RxJavaHooks.onSingleStart(this, this.f15650a).call(SingleLiftObservableOperator.wrap(subscriber));
        return RxJavaHooks.onSingleReturn(subscriber);
    }

    public final Single<T> cache() {
        return toObservable().cacheWithInitialCapacity(1).toSingle();
    }

    public <R> Single<R> compose(Transformer<? super T, ? extends R> transformer) {
        return (Single) transformer.call(this);
    }

    public final Observable<T> concatWith(Single<? extends T> single) {
        return concat(this, single);
    }

    public final Single<T> delay(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return create(new SingleDelay(this.f15650a, j2, timeUnit, scheduler));
    }

    public final Single<T> delaySubscription(Observable<?> observable) {
        Objects.requireNonNull(observable);
        return create(new SingleOnSubscribeDelaySubscriptionOther(this, observable));
    }

    public final Single<T> doAfterTerminate(Action0 action0) {
        return create(new SingleDoAfterTerminate(this, action0));
    }

    public final Single<T> doOnEach(Action1<Notification<? extends T>> action1) {
        if (action1 != null) {
            return create(new SingleDoOnEvent(this, new g(this, action1), new h(this, action1)));
        }
        throw new IllegalArgumentException("onNotification is null");
    }

    public final Single<T> doOnError(Action1<Throwable> action1) {
        if (action1 != null) {
            return create(new SingleDoOnEvent(this, Actions.empty(), new f(this, action1)));
        }
        throw new IllegalArgumentException("onError is null");
    }

    public final Single<T> doOnSubscribe(Action0 action0) {
        return create(new SingleDoOnSubscribe(this.f15650a, action0));
    }

    public final Single<T> doOnSuccess(Action1<? super T> action1) {
        if (action1 != null) {
            return create(new SingleDoOnEvent(this, action1, Actions.empty()));
        }
        throw new IllegalArgumentException("onSuccess is null");
    }

    public final Single<T> doOnUnsubscribe(Action0 action0) {
        return create(new SingleDoOnUnsubscribe(this.f15650a, action0));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Single<R> flatMap(Func1<? super T, ? extends Single<? extends R>> func1) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarFlatMap(func1);
        }
        return merge(map(func1));
    }

    public final Completable flatMapCompletable(Func1<? super T, ? extends Completable> func1) {
        return Completable.create(new CompletableFlatMapSingleToCompletable(this, func1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> flatMapObservable(Func1<? super T, ? extends Observable<? extends R>> func1) {
        return Observable.merge(a(map(func1)));
    }

    public final <R> Single<R> lift(Observable.Operator<? extends R, ? super T> operator) {
        return create(new SingleLiftObservableOperator(this.f15650a, operator));
    }

    public final <R> Single<R> map(Func1<? super T, ? extends R> func1) {
        return create(new SingleOnSubscribeMap(this, func1));
    }

    public final Observable<T> mergeWith(Single<? extends T> single) {
        return merge(this, single);
    }

    public final Single<T> observeOn(Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarScheduleOn(scheduler);
        }
        Objects.requireNonNull(scheduler, "scheduler is null");
        return create(new SingleObserveOn(this.f15650a, scheduler));
    }

    public final Single<T> onErrorResumeNext(Single<? extends T> single) {
        return new Single<>(SingleOperatorOnErrorResumeNext.withOther(this, single));
    }

    public final Single<T> onErrorReturn(Func1<Throwable, ? extends T> func1) {
        return create(new SingleOnErrorReturn(this.f15650a, func1));
    }

    public final Single<T> retry() {
        return toObservable().retry().toSingle();
    }

    public final Single<T> retryWhen(Func1<Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return toObservable().retryWhen(func1).toSingle();
    }

    public final Subscription subscribe() {
        return subscribe(Actions.empty(), Actions.errorNotImplemented());
    }

    public final Single<T> subscribeOn(Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarScheduleOn(scheduler);
        }
        return create(new d(scheduler));
    }

    public final Single<T> takeUntil(Completable completable) {
        return create(new SingleTakeUntilCompletable(this.f15650a, completable));
    }

    public final AssertableSubscriber<T> test() {
        AssertableSubscriberObservable create = AssertableSubscriberObservable.create(Long.MAX_VALUE);
        subscribe((Subscriber) create);
        return create;
    }

    public final Single<T> timeout(long j2, TimeUnit timeUnit) {
        return timeout(j2, timeUnit, null, Schedulers.computation());
    }

    public final <R> R to(Func1<? super Single<T>, R> func1) {
        return func1.call(this);
    }

    public final BlockingSingle<T> toBlocking() {
        return BlockingSingle.from(this);
    }

    public final Completable toCompletable() {
        return Completable.fromSingle(this);
    }

    public final Observable<T> toObservable() {
        return a(this);
    }

    public final Subscription unsafeSubscribe(Subscriber<? super T> subscriber) {
        return c(subscriber, true);
    }

    @Experimental
    public final Single<T> unsubscribeOn(Scheduler scheduler) {
        return create(new j(scheduler));
    }

    public final <T2, R> Single<R> zipWith(Single<? extends T2> single, Func2<? super T, ? super T2, ? extends R> func2) {
        return zip(this, single, func2);
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3) {
        return Observable.concat(a(single), a(single2), a(single3));
    }

    public static <T> Single<T> from(Future<? extends T> future, long j2, TimeUnit timeUnit) {
        Objects.requireNonNull(timeUnit, "unit is null");
        return create(new SingleFromFuture(future, j2, timeUnit));
    }

    public static <T> Observable<T> mergeDelayError(Observable<? extends Single<? extends T>> observable, int i2) {
        return (Observable<T>) observable.flatMapSingle(UtilityFunctions.identity(), true, i2);
    }

    public static <T, Resource> Single<T> using(Func0<Resource> func0, Func1<? super Resource, ? extends Single<? extends T>> func1, Action1<? super Resource> action1, boolean z) {
        Objects.requireNonNull(func0, "resourceFactory is null");
        Objects.requireNonNull(func1, "singleFactory is null");
        Objects.requireNonNull(action1, "disposeAction is null");
        return create(new SingleOnSubscribeUsing(func0, func1, action1, z));
    }

    public static <T1, T2, T3, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Func3<? super T1, ? super T2, ? super T3, ? extends R> func3) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3}, new n(func3));
    }

    public final Single<T> delay(long j2, TimeUnit timeUnit) {
        return delay(j2, timeUnit, Schedulers.computation());
    }

    public final Single<T> onErrorResumeNext(Func1<Throwable, ? extends Single<? extends T>> func1) {
        return new Single<>(SingleOperatorOnErrorResumeNext.withFunction(this, func1));
    }

    public final Single<T> retry(long j2) {
        return toObservable().retry(j2).toSingle();
    }

    public final Subscription subscribe(Action1<? super T> action1) {
        return subscribe(action1, Actions.errorNotImplemented());
    }

    public final <E> Single<T> takeUntil(Observable<? extends E> observable) {
        return create(new SingleTakeUntilObservable(this.f15650a, observable));
    }

    public final Single<T> timeout(long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return timeout(j2, timeUnit, null, scheduler);
    }

    @Deprecated
    public Single(Observable.OnSubscribe<T> onSubscribe) {
        this.f15650a = RxJavaHooks.onCreate(new SingleFromObservable(onSubscribe));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4) {
        return Observable.concat(a(single), a(single2), a(single3), a(single4));
    }

    public static <T1, T2, T3, T4, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Func4<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> func4) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4}, new o(func4));
    }

    public final Single<T> retry(Func2<Integer, Throwable, Boolean> func2) {
        return toObservable().retry(func2).toSingle();
    }

    public final Subscription subscribe(Action1<? super T> action1, Action1<Throwable> action12) {
        if (action1 != null) {
            if (action12 != null) {
                return subscribe(new b(this, action12, action1));
            }
            throw new IllegalArgumentException("onError can not be null");
        }
        throw new IllegalArgumentException("onSuccess can not be null");
    }

    public final <E> Single<T> takeUntil(Single<? extends E> single) {
        return create(new SingleTakeUntilSingle(this.f15650a, single));
    }

    public final Single<T> timeout(long j2, TimeUnit timeUnit, Single<? extends T> single) {
        return timeout(j2, timeUnit, single, Schedulers.computation());
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5) {
        return Observable.concat(a(single), a(single2), a(single3), a(single4), a(single5));
    }

    public static <T> Single<T> from(Future<? extends T> future, Scheduler scheduler) {
        return from(future).subscribeOn(scheduler);
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2) {
        return Observable.merge(a(single), a(single2));
    }

    public static <T1, T2, T3, T4, T5, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Func5<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? extends R> func5) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5}, new p(func5));
    }

    public final Single<T> timeout(long j2, TimeUnit timeUnit, Single<? extends T> single, Scheduler scheduler) {
        if (single == null) {
            single = defer(new e(this));
        }
        return create(new SingleTimeout(this.f15650a, j2, timeUnit, scheduler, single.f15650a));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6) {
        return Observable.concat(a(single), a(single2), a(single3), a(single4), a(single5), a(single6));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3) {
        return Observable.merge(a(single), a(single2), a(single3));
    }

    public static <T1, T2, T3, T4, T5, T6, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, Func6<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? extends R> func6) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6}, new q(func6));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7) {
        return Observable.concat(a(single), a(single2), a(single3), a(single4), a(single5), a(single6), a(single7));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4) {
        return Observable.merge(a(single), a(single2), a(single3), a(single4));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, Single<? extends T7> single7, Func7<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? extends R> func7) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6, single7}, new r(func7));
    }

    public final Subscription subscribe(Observer<? super T> observer) {
        Objects.requireNonNull(observer, "observer is null");
        return subscribe(new c(this, observer));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8) {
        return Observable.concat(a(single), a(single2), a(single3), a(single4), a(single5), a(single6), a(single7), a(single8));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5) {
        return Observable.merge(a(single), a(single2), a(single3), a(single4), a(single5));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, Single<? extends T7> single7, Single<? extends T8> single8, Func8<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? extends R> func8) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6, single7, single8}, new s(func8));
    }

    public static <T> Observable<T> concat(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8, Single<? extends T> single9) {
        return Observable.concat(a(single), a(single2), a(single3), a(single4), a(single5), a(single6), a(single7), a(single8), a(single9));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6) {
        return Observable.merge(a(single), a(single2), a(single3), a(single4), a(single5), a(single6));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Single<R> zip(Single<? extends T1> single, Single<? extends T2> single2, Single<? extends T3> single3, Single<? extends T4> single4, Single<? extends T5> single5, Single<? extends T6> single6, Single<? extends T7> single7, Single<? extends T8> single8, Single<? extends T9> single9, Func9<? super T1, ? super T2, ? super T3, ? super T4, ? super T5, ? super T6, ? super T7, ? super T8, ? super T9, ? extends R> func9) {
        return SingleOperatorZip.zip(new Single[]{single, single2, single3, single4, single5, single6, single7, single8, single9}, new a(func9));
    }

    public final Subscription subscribe(Subscriber<? super T> subscriber) {
        if (subscriber != null) {
            subscriber.onStart();
            if (!(subscriber instanceof SafeSubscriber)) {
                return c(new SafeSubscriber(subscriber), false);
            }
            return c(subscriber, true);
        }
        throw new IllegalArgumentException("observer can not be null");
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7) {
        return Observable.merge(a(single), a(single2), a(single3), a(single4), a(single5), a(single6), a(single7));
    }

    public static <R> Single<R> zip(Iterable<? extends Single<?>> iterable, FuncN<? extends R> funcN) {
        return SingleOperatorZip.zip(b(iterable), funcN);
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8) {
        return Observable.merge(a(single), a(single2), a(single3), a(single4), a(single5), a(single6), a(single7), a(single8));
    }

    public static <T> Observable<T> merge(Single<? extends T> single, Single<? extends T> single2, Single<? extends T> single3, Single<? extends T> single4, Single<? extends T> single5, Single<? extends T> single6, Single<? extends T> single7, Single<? extends T> single8, Single<? extends T> single9) {
        return Observable.merge(a(single), a(single2), a(single3), a(single4), a(single5), a(single6), a(single7), a(single8), a(single9));
    }

    public static <T> Observable<T> merge(Observable<? extends Single<? extends T>> observable) {
        return merge(observable, Integer.MAX_VALUE);
    }

    public static <T> Observable<T> merge(Observable<? extends Single<? extends T>> observable, int i2) {
        return (Observable<T>) observable.flatMapSingle(UtilityFunctions.identity(), false, i2);
    }

    public final Subscription subscribe(SingleSubscriber<? super T> singleSubscriber) {
        if (singleSubscriber != null) {
            try {
                RxJavaHooks.onSingleStart(this, this.f15650a).call(singleSubscriber);
                return RxJavaHooks.onSingleReturn(singleSubscriber);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                try {
                    singleSubscriber.onError(RxJavaHooks.onSingleError(th));
                    return Subscriptions.empty();
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    RuntimeException runtimeException = new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                    RxJavaHooks.onSingleError(runtimeException);
                    throw runtimeException;
                }
            }
        }
        throw new IllegalArgumentException("te is null");
    }
}
