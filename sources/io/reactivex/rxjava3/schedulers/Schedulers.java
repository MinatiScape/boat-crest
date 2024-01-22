package io.reactivex.rxjava3.schedulers;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.schedulers.ComputationScheduler;
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler;
import io.reactivex.rxjava3.internal.schedulers.IoScheduler;
import io.reactivex.rxjava3.internal.schedulers.NewThreadScheduler;
import io.reactivex.rxjava3.internal.schedulers.SchedulerPoolFactory;
import io.reactivex.rxjava3.internal.schedulers.SingleScheduler;
import io.reactivex.rxjava3.internal.schedulers.TrampolineScheduler;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.Executor;
/* loaded from: classes12.dex */
public final class Schedulers {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public static final Scheduler f13998a = RxJavaPlugins.initSingleScheduler(new h());
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
        public static final Scheduler f13999a = new ComputationScheduler();
    }

    /* loaded from: classes12.dex */
    public static final class b implements Supplier<Scheduler> {
        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public Scheduler get() {
            return a.f13999a;
        }
    }

    /* loaded from: classes12.dex */
    public static final class c implements Supplier<Scheduler> {
        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public Scheduler get() {
            return d.f14000a;
        }
    }

    /* loaded from: classes12.dex */
    public static final class d {

        /* renamed from: a  reason: collision with root package name */
        public static final Scheduler f14000a = new IoScheduler();
    }

    /* loaded from: classes12.dex */
    public static final class e {

        /* renamed from: a  reason: collision with root package name */
        public static final Scheduler f14001a = new NewThreadScheduler();
    }

    /* loaded from: classes12.dex */
    public static final class f implements Supplier<Scheduler> {
        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public Scheduler get() {
            return e.f14001a;
        }
    }

    /* loaded from: classes12.dex */
    public static final class g {

        /* renamed from: a  reason: collision with root package name */
        public static final Scheduler f14002a = new SingleScheduler();
    }

    /* loaded from: classes12.dex */
    public static final class h implements Supplier<Scheduler> {
        @Override // io.reactivex.rxjava3.functions.Supplier
        /* renamed from: a */
        public Scheduler get() {
            return g.f14002a;
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
        return new ExecutorScheduler(executor, false, false);
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
        return RxJavaPlugins.onSingleScheduler(f13998a);
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

    @NonNull
    public static Scheduler from(@NonNull Executor executor, boolean z) {
        return new ExecutorScheduler(executor, z, false);
    }

    @NonNull
    public static Scheduler from(@NonNull Executor executor, boolean z, boolean z2) {
        return new ExecutorScheduler(executor, z, z2);
    }
}
