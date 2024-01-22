package io.reactivex.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.annotations.Experimental;
import io.reactivex.annotations.NonNull;
import io.reactivex.internal.schedulers.ComputationScheduler;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.internal.schedulers.IoScheduler;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.internal.schedulers.SchedulerPoolFactory;
import io.reactivex.internal.schedulers.SingleScheduler;
import io.reactivex.internal.schedulers.TrampolineScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
/* loaded from: classes12.dex */
public final class Schedulers {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public static final Scheduler f14004a = RxJavaPlugins.initSingleScheduler(new h());
    @NonNull
    public static final Scheduler b = RxJavaPlugins.initComputationScheduler(new b());
    @NonNull
    public static final Scheduler c = RxJavaPlugins.initIoScheduler(new c());
    @NonNull
    public static final Scheduler d = TrampolineScheduler.instance();
    @NonNull
    public static final Scheduler e = RxJavaPlugins.initNewThreadScheduler(new f());

    /* loaded from: classes12.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        public static final Scheduler f14005a = new ComputationScheduler();
    }

    /* loaded from: classes12.dex */
    public static final class b implements Callable<Scheduler> {
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Scheduler call() throws Exception {
            return a.f14005a;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c implements Callable<Scheduler> {
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Scheduler call() throws Exception {
            return d.f14006a;
        }
    }

    /* loaded from: classes12.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        public static final Scheduler f14006a = new IoScheduler();
    }

    /* loaded from: classes12.dex */
    public static final class e {

        /* renamed from: a  reason: collision with root package name */
        public static final Scheduler f14007a = new NewThreadScheduler();
    }

    /* loaded from: classes12.dex */
    public static final class f implements Callable<Scheduler> {
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Scheduler call() throws Exception {
            return e.f14007a;
        }
    }

    /* loaded from: classes12.dex */
    public static final class g {

        /* renamed from: a  reason: collision with root package name */
        public static final Scheduler f14008a = new SingleScheduler();
    }

    /* loaded from: classes12.dex */
    public static final class h implements Callable<Scheduler> {
        @Override // java.util.concurrent.Callable
        /* renamed from: a */
        public Scheduler call() throws Exception {
            return g.f14008a;
        }
    }

    public Schedulers() {
        throw new IllegalStateException("No instances!");
    }

    @NonNull
    public static Scheduler computation() {
        return RxJavaPlugins.onComputationScheduler(b);
    }

    @NonNull
    public static Scheduler from(@NonNull Executor executor) {
        return new ExecutorScheduler(executor, false);
    }

    @NonNull
    public static Scheduler io() {
        return RxJavaPlugins.onIoScheduler(c);
    }

    @NonNull
    public static Scheduler newThread() {
        return RxJavaPlugins.onNewThreadScheduler(e);
    }

    public static void shutdown() {
        computation().shutdown();
        io().shutdown();
        newThread().shutdown();
        single().shutdown();
        trampoline().shutdown();
        SchedulerPoolFactory.shutdown();
    }

    @NonNull
    public static Scheduler single() {
        return RxJavaPlugins.onSingleScheduler(f14004a);
    }

    public static void start() {
        computation().start();
        io().start();
        newThread().start();
        single().start();
        trampoline().start();
        SchedulerPoolFactory.start();
    }

    @NonNull
    public static Scheduler trampoline() {
        return d;
    }

    @Experimental
    @NonNull
    public static Scheduler from(@NonNull Executor executor, boolean z) {
        return new ExecutorScheduler(executor, z);
    }
}
