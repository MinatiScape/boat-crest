package com.google.common.util.concurrent;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.util.concurrent.Service;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes10.dex */
public abstract class AbstractScheduledService implements Service {
    public static final Logger b = Logger.getLogger(AbstractScheduledService.class.getName());

    /* renamed from: a  reason: collision with root package name */
    public final AbstractService f10748a = new c(this, null);

    /* loaded from: classes10.dex */
    public static abstract class CustomScheduler extends Scheduler {

        /* loaded from: classes10.dex */
        public static final class Schedule {

            /* renamed from: a  reason: collision with root package name */
            public final long f10749a;
            public final TimeUnit b;

            public Schedule(long j, TimeUnit timeUnit) {
                this.f10749a = j;
                this.b = (TimeUnit) Preconditions.checkNotNull(timeUnit);
            }
        }

        /* loaded from: classes10.dex */
        public class a extends ForwardingFuture<Void> implements Callable<Void> {
            public final Runnable h;
            public final ScheduledExecutorService i;
            public final AbstractService j;
            public final ReentrantLock k = new ReentrantLock();
            @NullableDecl
            @GuardedBy("lock")
            public Future<Void> l;

            public a(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                this.h = runnable;
                this.i = scheduledExecutorService;
                this.j = abstractService;
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
            public boolean cancel(boolean z) {
                this.k.lock();
                try {
                    return this.l.cancel(z);
                } finally {
                    this.k.unlock();
                }
            }

            @Override // java.util.concurrent.Callable
            /* renamed from: d */
            public Void call() throws Exception {
                this.h.run();
                e();
                return null;
            }

            public void e() {
                try {
                    Schedule nextSchedule = CustomScheduler.this.getNextSchedule();
                    Throwable th = null;
                    this.k.lock();
                    try {
                        Future<Void> future = this.l;
                        if (future == null || !future.isCancelled()) {
                            this.l = this.i.schedule(this, nextSchedule.f10749a, nextSchedule.b);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    this.k.unlock();
                    if (th != null) {
                        this.j.notifyFailed(th);
                    }
                } catch (Throwable th3) {
                    this.j.notifyFailed(th3);
                }
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
            public boolean isCancelled() {
                this.k.lock();
                try {
                    return this.l.isCancelled();
                } finally {
                    this.k.unlock();
                }
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture, com.google.common.collect.ForwardingObject
            public Future<Void> delegate() {
                throw new UnsupportedOperationException("Only cancel and isCancelled is supported by this future");
            }
        }

        public CustomScheduler() {
            super(null);
        }

        @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
        public final Future<?> a(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
            a aVar = new a(abstractService, scheduledExecutorService, runnable);
            aVar.e();
            return aVar;
        }

        public abstract Schedule getNextSchedule() throws Exception;
    }

    /* loaded from: classes10.dex */
    public static abstract class Scheduler {

        /* loaded from: classes10.dex */
        public class a extends Scheduler {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ long f10750a;
            public final /* synthetic */ long b;
            public final /* synthetic */ TimeUnit c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public a(long j, long j2, TimeUnit timeUnit) {
                super(null);
                this.f10750a = j;
                this.b = j2;
                this.c = timeUnit;
            }

            @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
            public Future<?> a(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                return scheduledExecutorService.scheduleWithFixedDelay(runnable, this.f10750a, this.b, this.c);
            }
        }

        /* loaded from: classes10.dex */
        public class b extends Scheduler {

            /* renamed from: a  reason: collision with root package name */
            public final /* synthetic */ long f10751a;
            public final /* synthetic */ long b;
            public final /* synthetic */ TimeUnit c;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public b(long j, long j2, TimeUnit timeUnit) {
                super(null);
                this.f10751a = j;
                this.b = j2;
                this.c = timeUnit;
            }

            @Override // com.google.common.util.concurrent.AbstractScheduledService.Scheduler
            public Future<?> a(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable) {
                return scheduledExecutorService.scheduleAtFixedRate(runnable, this.f10751a, this.b, this.c);
            }
        }

        public /* synthetic */ Scheduler(a aVar) {
            this();
        }

        public static Scheduler newFixedDelaySchedule(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkNotNull(timeUnit);
            Preconditions.checkArgument(j2 > 0, "delay must be > 0, found %s", j2);
            return new a(j, j2, timeUnit);
        }

        public static Scheduler newFixedRateSchedule(long j, long j2, TimeUnit timeUnit) {
            Preconditions.checkNotNull(timeUnit);
            Preconditions.checkArgument(j2 > 0, "period must be > 0, found %s", j2);
            return new b(j, j2, timeUnit);
        }

        public abstract Future<?> a(AbstractService abstractService, ScheduledExecutorService scheduledExecutorService, Runnable runnable);

        public Scheduler() {
        }
    }

    /* loaded from: classes10.dex */
    public class a extends Service.Listener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ ScheduledExecutorService f10752a;

        public a(AbstractScheduledService abstractScheduledService, ScheduledExecutorService scheduledExecutorService) {
            this.f10752a = scheduledExecutorService;
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void failed(Service.State state, Throwable th) {
            this.f10752a.shutdown();
        }

        @Override // com.google.common.util.concurrent.Service.Listener
        public void terminated(Service.State state) {
            this.f10752a.shutdown();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements ThreadFactory {
        public b() {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return MoreExecutors.c(AbstractScheduledService.this.serviceName(), runnable);
        }
    }

    @Override // com.google.common.util.concurrent.Service
    public final void addListener(Service.Listener listener, Executor executor) {
        this.f10748a.addListener(listener, executor);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning() {
        this.f10748a.awaitRunning();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated() {
        this.f10748a.awaitTerminated();
    }

    public ScheduledExecutorService executor() {
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(new b());
        addListener(new a(this, newSingleThreadScheduledExecutor), MoreExecutors.directExecutor());
        return newSingleThreadScheduledExecutor;
    }

    @Override // com.google.common.util.concurrent.Service
    public final Throwable failureCause() {
        return this.f10748a.failureCause();
    }

    @Override // com.google.common.util.concurrent.Service
    public final boolean isRunning() {
        return this.f10748a.isRunning();
    }

    public abstract void runOneIteration() throws Exception;

    public abstract Scheduler scheduler();

    public String serviceName() {
        return getClass().getSimpleName();
    }

    public void shutDown() throws Exception {
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service startAsync() {
        this.f10748a.startAsync();
        return this;
    }

    public void startUp() throws Exception {
    }

    @Override // com.google.common.util.concurrent.Service
    public final Service.State state() {
        return this.f10748a.state();
    }

    @Override // com.google.common.util.concurrent.Service
    @CanIgnoreReturnValue
    public final Service stopAsync() {
        this.f10748a.stopAsync();
        return this;
    }

    public String toString() {
        String serviceName = serviceName();
        String valueOf = String.valueOf(state());
        StringBuilder sb = new StringBuilder(String.valueOf(serviceName).length() + 3 + valueOf.length());
        sb.append(serviceName);
        sb.append(" [");
        sb.append(valueOf);
        sb.append("]");
        return sb.toString();
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitRunning(long j, TimeUnit timeUnit) throws TimeoutException {
        this.f10748a.awaitRunning(j, timeUnit);
    }

    @Override // com.google.common.util.concurrent.Service
    public final void awaitTerminated(long j, TimeUnit timeUnit) throws TimeoutException {
        this.f10748a.awaitTerminated(j, timeUnit);
    }

    /* loaded from: classes10.dex */
    public final class c extends AbstractService {
        @NullableDecl
        public volatile Future<?> p;
        @NullableDecl
        public volatile ScheduledExecutorService q;
        public final ReentrantLock r;
        public final Runnable s;

        /* loaded from: classes10.dex */
        public class a implements Supplier<String> {
            public a() {
            }

            @Override // com.google.common.base.Supplier
            /* renamed from: a */
            public String get() {
                String serviceName = AbstractScheduledService.this.serviceName();
                String valueOf = String.valueOf(c.this.state());
                StringBuilder sb = new StringBuilder(String.valueOf(serviceName).length() + 1 + valueOf.length());
                sb.append(serviceName);
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb.append(valueOf);
                return sb.toString();
            }
        }

        /* loaded from: classes10.dex */
        public class b implements Runnable {
            public b() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.r.lock();
                try {
                    AbstractScheduledService.this.startUp();
                    c cVar = c.this;
                    cVar.p = AbstractScheduledService.this.scheduler().a(AbstractScheduledService.this.f10748a, c.this.q, c.this.s);
                    c.this.notifyStarted();
                } finally {
                    try {
                    } finally {
                    }
                }
            }
        }

        /* renamed from: com.google.common.util.concurrent.AbstractScheduledService$c$c  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class RunnableC0523c implements Runnable {
            public RunnableC0523c() {
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    c.this.r.lock();
                    if (c.this.state() != Service.State.STOPPING) {
                        c.this.r.unlock();
                        return;
                    }
                    AbstractScheduledService.this.shutDown();
                    c.this.r.unlock();
                    c.this.notifyStopped();
                } catch (Throwable th) {
                    c.this.notifyFailed(th);
                }
            }
        }

        /* loaded from: classes10.dex */
        public class d implements Runnable {
            public d() {
            }

            @Override // java.lang.Runnable
            public void run() {
                c.this.r.lock();
                try {
                } finally {
                    try {
                    } finally {
                    }
                }
                if (c.this.p.isCancelled()) {
                    return;
                }
                AbstractScheduledService.this.runOneIteration();
            }
        }

        public c() {
            this.r = new ReentrantLock();
            this.s = new d();
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStart() {
            this.q = MoreExecutors.f(AbstractScheduledService.this.executor(), new a());
            this.q.execute(new b());
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public final void doStop() {
            this.p.cancel(false);
            this.q.execute(new RunnableC0523c());
        }

        @Override // com.google.common.util.concurrent.AbstractService
        public String toString() {
            return AbstractScheduledService.this.toString();
        }

        public /* synthetic */ c(AbstractScheduledService abstractScheduledService, a aVar) {
            this();
        }
    }
}
