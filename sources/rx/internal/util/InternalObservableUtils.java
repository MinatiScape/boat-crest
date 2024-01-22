package rx.internal.util;

import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Notification;
import rx.Observable;
import rx.Scheduler;
import rx.exceptions.OnErrorNotImplementedException;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.OperatorAny;
import rx.observables.ConnectableObservable;
/* loaded from: classes13.dex */
public enum InternalObservableUtils {
    ;
    
    public static final h LONG_COUNTER = new h();
    public static final f OBJECT_EQUALS = new f();
    public static final q TO_ARRAY = new q();
    public static final o RETURNS_VOID = new o();
    public static final g COUNTER = new g();
    public static final e ERROR_EXTRACTOR = new e();
    public static final Action1<Throwable> ERROR_NOT_IMPLEMENTED = new Action1<Throwable>() { // from class: rx.internal.util.InternalObservableUtils.c
        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }
    };
    public static final Observable.Operator<Boolean, Object> IS_EMPTY = new OperatorAny(UtilityFunctions.alwaysTrue(), true);

    /* loaded from: classes13.dex */
    public static final class a<T, R> implements Func2<R, T, R> {
        public final Action2<R, ? super T> h;

        public a(Action2<R, ? super T> action2) {
            this.h = action2;
        }

        @Override // rx.functions.Func2
        public R call(R r, T t) {
            this.h.call(r, t);
            return r;
        }
    }

    /* loaded from: classes13.dex */
    public static final class b implements Func1<Object, Boolean> {
        public final Object h;

        public b(Object obj) {
            this.h = obj;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Boolean call(Object obj) {
            Object obj2 = this.h;
            return Boolean.valueOf(obj == obj2 || (obj != null && obj.equals(obj2)));
        }
    }

    /* loaded from: classes13.dex */
    public static final class d implements Func1<Object, Boolean> {
        public final Class<?> h;

        public d(Class<?> cls) {
            this.h = cls;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Boolean call(Object obj) {
            return Boolean.valueOf(this.h.isInstance(obj));
        }
    }

    /* loaded from: classes13.dex */
    public static final class e implements Func1<Notification<?>, Throwable> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Throwable call(Notification<?> notification) {
            return notification.getThrowable();
        }
    }

    /* loaded from: classes13.dex */
    public static final class f implements Func2<Object, Object, Boolean> {
        @Override // rx.functions.Func2
        /* renamed from: a */
        public Boolean call(Object obj, Object obj2) {
            return Boolean.valueOf(obj == obj2 || (obj != null && obj.equals(obj2)));
        }
    }

    /* loaded from: classes13.dex */
    public static final class g implements Func2<Integer, Object, Integer> {
        @Override // rx.functions.Func2
        /* renamed from: a */
        public Integer call(Integer num, Object obj) {
            return Integer.valueOf(num.intValue() + 1);
        }
    }

    /* loaded from: classes13.dex */
    public static final class h implements Func2<Long, Object, Long> {
        @Override // rx.functions.Func2
        /* renamed from: a */
        public Long call(Long l, Object obj) {
            return Long.valueOf(l.longValue() + 1);
        }
    }

    /* loaded from: classes13.dex */
    public static final class i implements Func1<Observable<? extends Notification<?>>, Observable<?>> {
        public final Func1<? super Observable<? extends Void>, ? extends Observable<?>> h;

        public i(Func1<? super Observable<? extends Void>, ? extends Observable<?>> func1) {
            this.h = func1;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable<?> call(Observable<? extends Notification<?>> observable) {
            return this.h.call(observable.map(InternalObservableUtils.RETURNS_VOID));
        }
    }

    /* loaded from: classes13.dex */
    public static final class j<T> implements Func0<ConnectableObservable<T>> {
        public final Observable<T> h;
        public final int i;

        public j(Observable<T> observable, int i) {
            this.h = observable;
            this.i = i;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.h.replay(this.i);
        }
    }

    /* loaded from: classes13.dex */
    public static final class k<T> implements Func0<ConnectableObservable<T>> {
        public final TimeUnit h;
        public final Observable<T> i;
        public final long j;
        public final Scheduler k;

        public k(Observable<T> observable, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.h = timeUnit;
            this.i = observable;
            this.j = j;
            this.k = scheduler;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.i.replay(this.j, this.h, this.k);
        }
    }

    /* loaded from: classes13.dex */
    public static final class l<T> implements Func0<ConnectableObservable<T>> {
        public final Observable<T> h;

        public l(Observable<T> observable) {
            this.h = observable;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.h.replay();
        }
    }

    /* loaded from: classes13.dex */
    public static final class m<T> implements Func0<ConnectableObservable<T>> {
        public final long h;
        public final TimeUnit i;
        public final Scheduler j;
        public final int k;
        public final Observable<T> l;

        public m(Observable<T> observable, int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
            this.h = j;
            this.i = timeUnit;
            this.j = scheduler;
            this.k = i;
            this.l = observable;
        }

        @Override // rx.functions.Func0, java.util.concurrent.Callable
        /* renamed from: a */
        public ConnectableObservable<T> call() {
            return this.l.replay(this.k, this.h, this.i, this.j);
        }
    }

    /* loaded from: classes13.dex */
    public static final class n implements Func1<Observable<? extends Notification<?>>, Observable<?>> {
        public final Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> h;

        public n(Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> func1) {
            this.h = func1;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable<?> call(Observable<? extends Notification<?>> observable) {
            return this.h.call(observable.map(InternalObservableUtils.ERROR_EXTRACTOR));
        }
    }

    /* loaded from: classes13.dex */
    public static final class o implements Func1<Object, Void> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Void call(Object obj) {
            return null;
        }
    }

    /* loaded from: classes13.dex */
    public static final class p<T, R> implements Func1<Observable<T>, Observable<R>> {
        public final Func1<? super Observable<T>, ? extends Observable<R>> h;
        public final Scheduler i;

        public p(Func1<? super Observable<T>, ? extends Observable<R>> func1, Scheduler scheduler) {
            this.h = func1;
            this.i = scheduler;
        }

        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable<R> call(Observable<T> observable) {
            return this.h.call(observable).observeOn(this.i);
        }
    }

    /* loaded from: classes13.dex */
    public static final class q implements Func1<List<? extends Observable<?>>, Observable<?>[]> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable<?>[] call(List<? extends Observable<?>> list) {
            return (Observable[]) list.toArray(new Observable[list.size()]);
        }
    }

    public static <T, R> Func2<R, T, R> createCollectorCaller(Action2<R, ? super T> action2) {
        return new a(action2);
    }

    public static Func1<Observable<? extends Notification<?>>, Observable<?>> createRepeatDematerializer(Func1<? super Observable<? extends Void>, ? extends Observable<?>> func1) {
        return new i(func1);
    }

    public static <T, R> Func1<Observable<T>, Observable<R>> createReplaySelectorAndObserveOn(Func1<? super Observable<T>, ? extends Observable<R>> func1, Scheduler scheduler) {
        return new p(func1, scheduler);
    }

    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(Observable<T> observable) {
        return new l(observable);
    }

    public static Func1<Observable<? extends Notification<?>>, Observable<?>> createRetryDematerializer(Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return new n(func1);
    }

    public static Func1<Object, Boolean> equalsWith(Object obj) {
        return new b(obj);
    }

    public static Func1<Object, Boolean> isInstanceOf(Class<?> cls) {
        return new d(cls);
    }

    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(Observable<T> observable, int i2) {
        return new j(observable, i2);
    }

    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(Observable<T> observable, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return new k(observable, j2, timeUnit, scheduler);
    }

    public static <T> Func0<ConnectableObservable<T>> createReplaySupplier(Observable<T> observable, int i2, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return new m(observable, i2, j2, timeUnit, scheduler);
    }
}
