package rx.plugins;

import java.io.PrintStream;
import java.util.concurrent.ScheduledExecutorService;
import rx.Completable;
import rx.Observable;
import rx.Scheduler;
import rx.Single;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.internal.operators.OnSubscribeOnAssembly;
import rx.internal.operators.OnSubscribeOnAssemblyCompletable;
import rx.internal.operators.OnSubscribeOnAssemblySingle;
import rx.internal.operators.SingleFromObservable;
import rx.internal.operators.SingleToObservable;
/* loaded from: classes13.dex */
public final class RxJavaHooks {

    /* renamed from: a  reason: collision with root package name */
    public static volatile boolean f15691a;
    public static volatile Action1<Throwable> b;
    public static volatile Func1<Observable.OnSubscribe, Observable.OnSubscribe> c;
    public static volatile Func1<Single.OnSubscribe, Single.OnSubscribe> d;
    public static volatile Func1<Completable.OnSubscribe, Completable.OnSubscribe> e;
    public static volatile Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> f;
    public static volatile Func2<Single, Single.OnSubscribe, Single.OnSubscribe> g;
    public static volatile Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> h;
    public static volatile Func1<Scheduler, Scheduler> i;
    public static volatile Func1<Scheduler, Scheduler> j;
    public static volatile Func1<Scheduler, Scheduler> k;
    public static volatile Func1<Action0, Action0> l;
    public static volatile Func1<Subscription, Subscription> m;
    public static volatile Func1<Subscription, Subscription> n;
    public static volatile Func0<? extends ScheduledExecutorService> o;
    public static volatile Func1<Throwable, Throwable> p;
    public static volatile Func1<Throwable, Throwable> q;
    public static volatile Func1<Throwable, Throwable> r;
    public static volatile Func1<Observable.Operator, Observable.Operator> s;
    public static volatile Func1<Observable.Operator, Observable.Operator> t;
    public static volatile Func1<Completable.Operator, Completable.Operator> u;

    /* loaded from: classes13.dex */
    public static class a implements Func1<Throwable, Throwable> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Throwable call(Throwable th) {
            return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeError(th);
        }
    }

    /* loaded from: classes13.dex */
    public static class b implements Func1<Observable.Operator, Observable.Operator> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable.Operator call(Observable.Operator operator) {
            return RxJavaPlugins.getInstance().getSingleExecutionHook().onLift(operator);
        }
    }

    /* loaded from: classes13.dex */
    public static class c implements Func1<Throwable, Throwable> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Throwable call(Throwable th) {
            return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeError(th);
        }
    }

    /* loaded from: classes13.dex */
    public static class d implements Func1<Completable.Operator, Completable.Operator> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Completable.Operator call(Completable.Operator operator) {
            return RxJavaPlugins.getInstance().getCompletableExecutionHook().onLift(operator);
        }
    }

    /* loaded from: classes13.dex */
    public static class e implements Func1<Observable.OnSubscribe, Observable.OnSubscribe> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable.OnSubscribe call(Observable.OnSubscribe onSubscribe) {
            return RxJavaPlugins.getInstance().getObservableExecutionHook().onCreate(onSubscribe);
        }
    }

    /* loaded from: classes13.dex */
    public static class f implements Func1<Single.OnSubscribe, Single.OnSubscribe> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Single.OnSubscribe call(Single.OnSubscribe onSubscribe) {
            return RxJavaPlugins.getInstance().getSingleExecutionHook().onCreate(onSubscribe);
        }
    }

    /* loaded from: classes13.dex */
    public static class g implements Func1<Completable.OnSubscribe, Completable.OnSubscribe> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Completable.OnSubscribe call(Completable.OnSubscribe onSubscribe) {
            return RxJavaPlugins.getInstance().getCompletableExecutionHook().onCreate(onSubscribe);
        }
    }

    /* loaded from: classes13.dex */
    public static class h implements Func1<Observable.OnSubscribe, Observable.OnSubscribe> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable.OnSubscribe call(Observable.OnSubscribe onSubscribe) {
            return new OnSubscribeOnAssembly(onSubscribe);
        }
    }

    /* loaded from: classes13.dex */
    public static class i implements Func1<Single.OnSubscribe, Single.OnSubscribe> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Single.OnSubscribe call(Single.OnSubscribe onSubscribe) {
            return new OnSubscribeOnAssemblySingle(onSubscribe);
        }
    }

    /* loaded from: classes13.dex */
    public static class j implements Func1<Completable.OnSubscribe, Completable.OnSubscribe> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Completable.OnSubscribe call(Completable.OnSubscribe onSubscribe) {
            return new OnSubscribeOnAssemblyCompletable(onSubscribe);
        }
    }

    /* loaded from: classes13.dex */
    public static class k implements Action1<Throwable> {
        @Override // rx.functions.Action1
        /* renamed from: a */
        public void call(Throwable th) {
            RxJavaPlugins.getInstance().getErrorHandler().handleError(th);
        }
    }

    /* loaded from: classes13.dex */
    public static class l implements Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> {
        @Override // rx.functions.Func2
        /* renamed from: a */
        public Observable.OnSubscribe call(Observable observable, Observable.OnSubscribe onSubscribe) {
            return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeStart(observable, onSubscribe);
        }
    }

    /* loaded from: classes13.dex */
    public static class m implements Func1<Subscription, Subscription> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Subscription call(Subscription subscription) {
            return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeReturn(subscription);
        }
    }

    /* loaded from: classes13.dex */
    public static class n implements Func2<Single, Single.OnSubscribe, Single.OnSubscribe> {
        @Override // rx.functions.Func2
        /* renamed from: a */
        public Single.OnSubscribe call(Single single, Single.OnSubscribe onSubscribe) {
            RxJavaSingleExecutionHook singleExecutionHook = RxJavaPlugins.getInstance().getSingleExecutionHook();
            return singleExecutionHook == rx.plugins.b.a() ? onSubscribe : new SingleFromObservable(singleExecutionHook.onSubscribeStart(single, new SingleToObservable(onSubscribe)));
        }
    }

    /* loaded from: classes13.dex */
    public static class o implements Func1<Subscription, Subscription> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Subscription call(Subscription subscription) {
            return RxJavaPlugins.getInstance().getSingleExecutionHook().onSubscribeReturn(subscription);
        }
    }

    /* loaded from: classes13.dex */
    public static class p implements Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> {
        @Override // rx.functions.Func2
        /* renamed from: a */
        public Completable.OnSubscribe call(Completable completable, Completable.OnSubscribe onSubscribe) {
            return RxJavaPlugins.getInstance().getCompletableExecutionHook().onSubscribeStart(completable, onSubscribe);
        }
    }

    /* loaded from: classes13.dex */
    public static class q implements Func1<Action0, Action0> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Action0 call(Action0 action0) {
            return RxJavaPlugins.getInstance().getSchedulersHook().onSchedule(action0);
        }
    }

    /* loaded from: classes13.dex */
    public static class r implements Func1<Throwable, Throwable> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Throwable call(Throwable th) {
            return RxJavaPlugins.getInstance().getObservableExecutionHook().onSubscribeError(th);
        }
    }

    /* loaded from: classes13.dex */
    public static class s implements Func1<Observable.Operator, Observable.Operator> {
        @Override // rx.functions.Func1
        /* renamed from: a */
        public Observable.Operator call(Observable.Operator operator) {
            return RxJavaPlugins.getInstance().getObservableExecutionHook().onLift(operator);
        }
    }

    static {
        a();
    }

    public RxJavaHooks() {
        throw new IllegalStateException("No instances!");
    }

    public static void a() {
        b = new k();
        f = new l();
        m = new m();
        g = new n();
        n = new o();
        h = new p();
        l = new q();
        p = new r();
        s = new s();
        q = new a();
        t = new b();
        r = new c();
        u = new d();
        b();
    }

    public static void b() {
        c = new e();
        d = new f();
        e = new g();
    }

    public static void c(Throwable th) {
        Thread currentThread = Thread.currentThread();
        currentThread.getUncaughtExceptionHandler().uncaughtException(currentThread, th);
    }

    public static void clear() {
        if (f15691a) {
            return;
        }
        b = null;
        c = null;
        f = null;
        m = null;
        p = null;
        s = null;
        d = null;
        g = null;
        n = null;
        q = null;
        t = null;
        e = null;
        h = null;
        r = null;
        u = null;
        i = null;
        j = null;
        k = null;
        l = null;
        o = null;
    }

    public static void clearAssemblyTracking() {
        if (f15691a) {
            return;
        }
        c = null;
        d = null;
        e = null;
    }

    public static void enableAssemblyTracking() {
        if (f15691a) {
            return;
        }
        c = new h();
        d = new i();
        e = new j();
    }

    public static Func1<Completable.OnSubscribe, Completable.OnSubscribe> getOnCompletableCreate() {
        return e;
    }

    public static Func1<Completable.Operator, Completable.Operator> getOnCompletableLift() {
        return u;
    }

    public static Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> getOnCompletableStart() {
        return h;
    }

    public static Func1<Throwable, Throwable> getOnCompletableSubscribeError() {
        return r;
    }

    public static Func1<Scheduler, Scheduler> getOnComputationScheduler() {
        return i;
    }

    public static Action1<Throwable> getOnError() {
        return b;
    }

    public static Func0<? extends ScheduledExecutorService> getOnGenericScheduledExecutorService() {
        return o;
    }

    public static Func1<Scheduler, Scheduler> getOnIOScheduler() {
        return j;
    }

    public static Func1<Scheduler, Scheduler> getOnNewThreadScheduler() {
        return k;
    }

    public static Func1<Observable.OnSubscribe, Observable.OnSubscribe> getOnObservableCreate() {
        return c;
    }

    public static Func1<Observable.Operator, Observable.Operator> getOnObservableLift() {
        return s;
    }

    public static Func1<Subscription, Subscription> getOnObservableReturn() {
        return m;
    }

    public static Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> getOnObservableStart() {
        return f;
    }

    public static Func1<Throwable, Throwable> getOnObservableSubscribeError() {
        return p;
    }

    public static Func1<Action0, Action0> getOnScheduleAction() {
        return l;
    }

    public static Func1<Single.OnSubscribe, Single.OnSubscribe> getOnSingleCreate() {
        return d;
    }

    public static Func1<Observable.Operator, Observable.Operator> getOnSingleLift() {
        return t;
    }

    public static Func1<Subscription, Subscription> getOnSingleReturn() {
        return n;
    }

    public static Func2<Single, Single.OnSubscribe, Single.OnSubscribe> getOnSingleStart() {
        return g;
    }

    public static Func1<Throwable, Throwable> getOnSingleSubscribeError() {
        return q;
    }

    public static boolean isLockdown() {
        return f15691a;
    }

    public static void lockdown() {
        f15691a = true;
    }

    public static Throwable onCompletableError(Throwable th) {
        Func1<Throwable, Throwable> func1 = r;
        return func1 != null ? func1.call(th) : th;
    }

    public static <T, R> Completable.Operator onCompletableLift(Completable.Operator operator) {
        Func1<Completable.Operator, Completable.Operator> func1 = u;
        return func1 != null ? func1.call(operator) : operator;
    }

    public static <T> Completable.OnSubscribe onCompletableStart(Completable completable, Completable.OnSubscribe onSubscribe) {
        Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> func2 = h;
        return func2 != null ? func2.call(completable, onSubscribe) : onSubscribe;
    }

    public static Scheduler onComputationScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> func1 = i;
        return func1 != null ? func1.call(scheduler) : scheduler;
    }

    public static <T> Observable.OnSubscribe<T> onCreate(Observable.OnSubscribe<T> onSubscribe) {
        Func1<Observable.OnSubscribe, Observable.OnSubscribe> func1 = c;
        return func1 != null ? func1.call(onSubscribe) : onSubscribe;
    }

    public static void onError(Throwable th) {
        Action1<Throwable> action1 = b;
        if (action1 != null) {
            try {
                action1.call(th);
                return;
            } catch (Throwable th2) {
                PrintStream printStream = System.err;
                printStream.println("The onError handler threw an Exception. It shouldn't. => " + th2.getMessage());
                th2.printStackTrace();
                c(th2);
            }
        }
        c(th);
    }

    public static Scheduler onIOScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> func1 = j;
        return func1 != null ? func1.call(scheduler) : scheduler;
    }

    public static Scheduler onNewThreadScheduler(Scheduler scheduler) {
        Func1<Scheduler, Scheduler> func1 = k;
        return func1 != null ? func1.call(scheduler) : scheduler;
    }

    public static Throwable onObservableError(Throwable th) {
        Func1<Throwable, Throwable> func1 = p;
        return func1 != null ? func1.call(th) : th;
    }

    public static <T, R> Observable.Operator<R, T> onObservableLift(Observable.Operator<R, T> operator) {
        Func1<Observable.Operator, Observable.Operator> func1 = s;
        return func1 != null ? func1.call(operator) : operator;
    }

    public static Subscription onObservableReturn(Subscription subscription) {
        Func1<Subscription, Subscription> func1 = m;
        return func1 != null ? func1.call(subscription) : subscription;
    }

    public static <T> Observable.OnSubscribe<T> onObservableStart(Observable<T> observable, Observable.OnSubscribe<T> onSubscribe) {
        Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> func2 = f;
        return func2 != null ? func2.call(observable, onSubscribe) : onSubscribe;
    }

    public static Action0 onScheduledAction(Action0 action0) {
        Func1<Action0, Action0> func1 = l;
        return func1 != null ? func1.call(action0) : action0;
    }

    public static Throwable onSingleError(Throwable th) {
        Func1<Throwable, Throwable> func1 = q;
        return func1 != null ? func1.call(th) : th;
    }

    public static <T, R> Observable.Operator<R, T> onSingleLift(Observable.Operator<R, T> operator) {
        Func1<Observable.Operator, Observable.Operator> func1 = t;
        return func1 != null ? func1.call(operator) : operator;
    }

    public static Subscription onSingleReturn(Subscription subscription) {
        Func1<Subscription, Subscription> func1 = n;
        return func1 != null ? func1.call(subscription) : subscription;
    }

    public static <T> Single.OnSubscribe<T> onSingleStart(Single<T> single, Single.OnSubscribe<T> onSubscribe) {
        Func2<Single, Single.OnSubscribe, Single.OnSubscribe> func2 = g;
        return func2 != null ? func2.call(single, onSubscribe) : onSubscribe;
    }

    public static void reset() {
        if (f15691a) {
            return;
        }
        a();
        i = null;
        j = null;
        k = null;
        o = null;
    }

    public static void resetAssemblyTracking() {
        if (f15691a) {
            return;
        }
        b();
    }

    public static void setOnCompletableCreate(Func1<Completable.OnSubscribe, Completable.OnSubscribe> func1) {
        if (f15691a) {
            return;
        }
        e = func1;
    }

    public static void setOnCompletableLift(Func1<Completable.Operator, Completable.Operator> func1) {
        if (f15691a) {
            return;
        }
        u = func1;
    }

    public static void setOnCompletableStart(Func2<Completable, Completable.OnSubscribe, Completable.OnSubscribe> func2) {
        if (f15691a) {
            return;
        }
        h = func2;
    }

    public static void setOnCompletableSubscribeError(Func1<Throwable, Throwable> func1) {
        if (f15691a) {
            return;
        }
        r = func1;
    }

    public static void setOnComputationScheduler(Func1<Scheduler, Scheduler> func1) {
        if (f15691a) {
            return;
        }
        i = func1;
    }

    public static void setOnError(Action1<Throwable> action1) {
        if (f15691a) {
            return;
        }
        b = action1;
    }

    public static void setOnGenericScheduledExecutorService(Func0<? extends ScheduledExecutorService> func0) {
        if (f15691a) {
            return;
        }
        o = func0;
    }

    public static void setOnIOScheduler(Func1<Scheduler, Scheduler> func1) {
        if (f15691a) {
            return;
        }
        j = func1;
    }

    public static void setOnNewThreadScheduler(Func1<Scheduler, Scheduler> func1) {
        if (f15691a) {
            return;
        }
        k = func1;
    }

    public static void setOnObservableCreate(Func1<Observable.OnSubscribe, Observable.OnSubscribe> func1) {
        if (f15691a) {
            return;
        }
        c = func1;
    }

    public static void setOnObservableLift(Func1<Observable.Operator, Observable.Operator> func1) {
        if (f15691a) {
            return;
        }
        s = func1;
    }

    public static void setOnObservableReturn(Func1<Subscription, Subscription> func1) {
        if (f15691a) {
            return;
        }
        m = func1;
    }

    public static void setOnObservableStart(Func2<Observable, Observable.OnSubscribe, Observable.OnSubscribe> func2) {
        if (f15691a) {
            return;
        }
        f = func2;
    }

    public static void setOnObservableSubscribeError(Func1<Throwable, Throwable> func1) {
        if (f15691a) {
            return;
        }
        p = func1;
    }

    public static void setOnScheduleAction(Func1<Action0, Action0> func1) {
        if (f15691a) {
            return;
        }
        l = func1;
    }

    public static void setOnSingleCreate(Func1<Single.OnSubscribe, Single.OnSubscribe> func1) {
        if (f15691a) {
            return;
        }
        d = func1;
    }

    public static void setOnSingleLift(Func1<Observable.Operator, Observable.Operator> func1) {
        if (f15691a) {
            return;
        }
        t = func1;
    }

    public static void setOnSingleReturn(Func1<Subscription, Subscription> func1) {
        if (f15691a) {
            return;
        }
        n = func1;
    }

    public static void setOnSingleStart(Func2<Single, Single.OnSubscribe, Single.OnSubscribe> func2) {
        if (f15691a) {
            return;
        }
        g = func2;
    }

    public static void setOnSingleSubscribeError(Func1<Throwable, Throwable> func1) {
        if (f15691a) {
            return;
        }
        q = func1;
    }

    public static <T> Single.OnSubscribe<T> onCreate(Single.OnSubscribe<T> onSubscribe) {
        Func1<Single.OnSubscribe, Single.OnSubscribe> func1 = d;
        return func1 != null ? func1.call(onSubscribe) : onSubscribe;
    }

    public static Completable.OnSubscribe onCreate(Completable.OnSubscribe onSubscribe) {
        Func1<Completable.OnSubscribe, Completable.OnSubscribe> func1 = e;
        return func1 != null ? func1.call(onSubscribe) : onSubscribe;
    }
}
