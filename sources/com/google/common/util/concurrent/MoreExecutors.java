package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ForwardingListenableFuture;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.GuardedBy;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
@GwtCompatible(emulated = true)
/* loaded from: classes10.dex */
public final class MoreExecutors {

    /* loaded from: classes10.dex */
    public class a implements Executor {
        public final /* synthetic */ Executor h;
        public final /* synthetic */ Supplier i;

        public a(Executor executor, Supplier supplier) {
            this.h = executor;
            this.i = supplier;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            this.h.execute(Callables.b(runnable, this.i));
        }
    }

    /* loaded from: classes10.dex */
    public class b extends y {
        public final /* synthetic */ Supplier j;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(ScheduledExecutorService scheduledExecutorService, Supplier supplier) {
            super(scheduledExecutorService);
            this.j = supplier;
        }

        @Override // com.google.common.util.concurrent.x
        public Runnable a(Runnable runnable) {
            return Callables.b(runnable, this.j);
        }

        @Override // com.google.common.util.concurrent.x
        public <T> Callable<T> b(Callable<T> callable) {
            return Callables.c(callable, this.j);
        }
    }

    /* loaded from: classes10.dex */
    public class c implements Executor {
        public final /* synthetic */ Executor h;
        public final /* synthetic */ AbstractFuture i;

        public c(Executor executor, AbstractFuture abstractFuture) {
            this.h = executor;
            this.i = abstractFuture;
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            try {
                this.h.execute(runnable);
            } catch (RejectedExecutionException e) {
                this.i.setException(e);
            }
        }
    }

    @VisibleForTesting
    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static class d {

        /* loaded from: classes10.dex */
        public class a implements Runnable {
            public final /* synthetic */ ExecutorService h;
            public final /* synthetic */ long i;
            public final /* synthetic */ TimeUnit j;

            public a(d dVar, ExecutorService executorService, long j, TimeUnit timeUnit) {
                this.h = executorService;
                this.i = j;
                this.j = timeUnit;
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.h.shutdown();
                    this.h.awaitTermination(this.i, this.j);
                } catch (InterruptedException unused) {
                }
            }
        }

        public final void a(ExecutorService executorService, long j, TimeUnit timeUnit) {
            Preconditions.checkNotNull(executorService);
            Preconditions.checkNotNull(timeUnit);
            String valueOf = String.valueOf(executorService);
            StringBuilder sb = new StringBuilder(valueOf.length() + 24);
            sb.append("DelayedShutdownHook-for-");
            sb.append(valueOf);
            b(MoreExecutors.c(sb.toString(), new a(this, executorService, j, timeUnit)));
        }

        @VisibleForTesting
        public void b(Thread thread) {
            Runtime.getRuntime().addShutdownHook(thread);
        }

        public final ExecutorService c(ThreadPoolExecutor threadPoolExecutor) {
            return d(threadPoolExecutor, 120L, TimeUnit.SECONDS);
        }

        public final ExecutorService d(ThreadPoolExecutor threadPoolExecutor, long j, TimeUnit timeUnit) {
            MoreExecutors.g(threadPoolExecutor);
            ExecutorService unconfigurableExecutorService = Executors.unconfigurableExecutorService(threadPoolExecutor);
            a(threadPoolExecutor, j, timeUnit);
            return unconfigurableExecutorService;
        }

        public final ScheduledExecutorService e(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
            return f(scheduledThreadPoolExecutor, 120L, TimeUnit.SECONDS);
        }

        public final ScheduledExecutorService f(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, long j, TimeUnit timeUnit) {
            MoreExecutors.g(scheduledThreadPoolExecutor);
            ScheduledExecutorService unconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(scheduledThreadPoolExecutor);
            a(scheduledThreadPoolExecutor, j, timeUnit);
            return unconfigurableScheduledExecutorService;
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static class f extends AbstractListeningExecutorService {
        public final ExecutorService h;

        public f(ExecutorService executorService) {
            this.h = (ExecutorService) Preconditions.checkNotNull(executorService);
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.h.awaitTermination(j, timeUnit);
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            this.h.execute(runnable);
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean isShutdown() {
            return this.h.isShutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public final boolean isTerminated() {
            return this.h.isTerminated();
        }

        @Override // java.util.concurrent.ExecutorService
        public final void shutdown() {
            this.h.shutdown();
        }

        @Override // java.util.concurrent.ExecutorService
        public final List<Runnable> shutdownNow() {
            return this.h.shutdownNow();
        }
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static final class g extends f implements ListeningScheduledExecutorService {
        public final ScheduledExecutorService i;

        /* loaded from: classes10.dex */
        public static final class a<V> extends ForwardingListenableFuture.SimpleForwardingListenableFuture<V> implements ListenableScheduledFuture<V> {
            public final ScheduledFuture<?> i;

            public a(ListenableFuture<V> listenableFuture, ScheduledFuture<?> scheduledFuture) {
                super(listenableFuture);
                this.i = scheduledFuture;
            }

            @Override // com.google.common.util.concurrent.ForwardingFuture, java.util.concurrent.Future
            public boolean cancel(boolean z) {
                boolean cancel = super.cancel(z);
                if (cancel) {
                    this.i.cancel(z);
                }
                return cancel;
            }

            @Override // java.lang.Comparable
            /* renamed from: d */
            public int compareTo(Delayed delayed) {
                return this.i.compareTo(delayed);
            }

            @Override // java.util.concurrent.Delayed
            public long getDelay(TimeUnit timeUnit) {
                return this.i.getDelay(timeUnit);
            }
        }

        @GwtIncompatible
        /* loaded from: classes10.dex */
        public static final class b extends AbstractFuture.j<Void> implements Runnable {
            public final Runnable o;

            public b(Runnable runnable) {
                this.o = (Runnable) Preconditions.checkNotNull(runnable);
            }

            @Override // java.lang.Runnable
            public void run() {
                try {
                    this.o.run();
                } catch (Throwable th) {
                    setException(th);
                    throw Throwables.propagate(th);
                }
            }
        }

        public g(ScheduledExecutorService scheduledExecutorService) {
            super(scheduledExecutorService);
            this.i = (ScheduledExecutorService) Preconditions.checkNotNull(scheduledExecutorService);
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            b bVar = new b(runnable);
            return new a(bVar, this.i.scheduleAtFixedRate(bVar, j, j2, timeUnit));
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
            b bVar = new b(runnable);
            return new a(bVar, this.i.scheduleWithFixedDelay(bVar, j, j2, timeUnit));
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public ListenableScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
            w z = w.z(runnable, null);
            return new a(z, this.i.schedule(z, j, timeUnit));
        }

        @Override // java.util.concurrent.ScheduledExecutorService
        public <V> ListenableScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
            w A = w.A(callable);
            return new a(A, this.i.schedule(A, j, timeUnit));
        }
    }

    @Beta
    @GwtIncompatible
    public static void addDelayedShutdownHook(ExecutorService executorService, long j, TimeUnit timeUnit) {
        new d().a(executorService, j, timeUnit);
    }

    @GwtIncompatible
    public static boolean b() {
        if (System.getProperty("com.google.appengine.runtime.environment") == null) {
            return false;
        }
        try {
            Class.forName("com.google.appengine.api.utils.SystemProperty");
            return Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", new Class[0]).invoke(null, new Object[0]) != null;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    @GwtIncompatible
    public static Thread c(String str, Runnable runnable) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(runnable);
        Thread newThread = platformThreadFactory().newThread(runnable);
        try {
            newThread.setName(str);
        } catch (SecurityException unused) {
        }
        return newThread;
    }

    public static Executor d(Executor executor, AbstractFuture<?> abstractFuture) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(abstractFuture);
        return executor == directExecutor() ? executor : new c(executor, abstractFuture);
    }

    public static Executor directExecutor() {
        return com.google.common.util.concurrent.g.INSTANCE;
    }

    @GwtIncompatible
    public static Executor e(Executor executor, Supplier<String> supplier) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(supplier);
        return new a(executor, supplier);
    }

    @GwtIncompatible
    public static ScheduledExecutorService f(ScheduledExecutorService scheduledExecutorService, Supplier<String> supplier) {
        Preconditions.checkNotNull(scheduledExecutorService);
        Preconditions.checkNotNull(supplier);
        return new b(scheduledExecutorService, supplier);
    }

    @GwtIncompatible
    public static void g(ThreadPoolExecutor threadPoolExecutor) {
        threadPoolExecutor.setThreadFactory(new ThreadFactoryBuilder().setDaemon(true).setThreadFactory(threadPoolExecutor.getThreadFactory()).build());
    }

    @Beta
    @GwtIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor, long j, TimeUnit timeUnit) {
        return new d().d(threadPoolExecutor, j, timeUnit);
    }

    @Beta
    @GwtIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, long j, TimeUnit timeUnit) {
        return new d().f(scheduledThreadPoolExecutor, j, timeUnit);
    }

    @GwtIncompatible
    public static ListeningExecutorService listeningDecorator(ExecutorService executorService) {
        ListeningExecutorService fVar;
        if (executorService instanceof ListeningExecutorService) {
            return (ListeningExecutorService) executorService;
        }
        if (executorService instanceof ScheduledExecutorService) {
            fVar = new g((ScheduledExecutorService) executorService);
        } else {
            fVar = new f(executorService);
        }
        return fVar;
    }

    @GwtIncompatible
    public static ListeningExecutorService newDirectExecutorService() {
        return new e(null);
    }

    @Beta
    @GwtIncompatible
    public static Executor newSequentialExecutor(Executor executor) {
        return new t(executor);
    }

    @Beta
    @GwtIncompatible
    public static ThreadFactory platformThreadFactory() {
        if (!b()) {
            return Executors.defaultThreadFactory();
        }
        try {
            return (ThreadFactory) Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", new Class[0]).invoke(null, new Object[0]);
        } catch (ClassNotFoundException e2) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e2);
        } catch (IllegalAccessException e3) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e4);
        } catch (InvocationTargetException e5) {
            throw Throwables.propagate(e5.getCause());
        }
    }

    @CanIgnoreReturnValue
    @Beta
    @GwtIncompatible
    public static boolean shutdownAndAwaitTermination(ExecutorService executorService, long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j) / 2;
        executorService.shutdown();
        try {
            TimeUnit timeUnit2 = TimeUnit.NANOSECONDS;
            if (!executorService.awaitTermination(nanos, timeUnit2)) {
                executorService.shutdownNow();
                executorService.awaitTermination(nanos, timeUnit2);
            }
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
            executorService.shutdownNow();
        }
        return executorService.isTerminated();
    }

    @Beta
    @GwtIncompatible
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor) {
        return new d().c(threadPoolExecutor);
    }

    @Beta
    @GwtIncompatible
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        return new d().e(scheduledThreadPoolExecutor);
    }

    @GwtIncompatible
    /* loaded from: classes10.dex */
    public static final class e extends AbstractListeningExecutorService {
        public final Object h;
        @GuardedBy("lock")
        public int i;
        @GuardedBy("lock")
        public boolean j;

        public e() {
            this.h = new Object();
            this.i = 0;
            this.j = false;
        }

        public final void a() {
            synchronized (this.h) {
                int i = this.i - 1;
                this.i = i;
                if (i == 0) {
                    this.h.notifyAll();
                }
            }
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
            long nanos = timeUnit.toNanos(j);
            synchronized (this.h) {
                while (true) {
                    if (this.j && this.i == 0) {
                        return true;
                    }
                    if (nanos <= 0) {
                        return false;
                    }
                    long nanoTime = System.nanoTime();
                    TimeUnit.NANOSECONDS.timedWait(this.h, nanos);
                    nanos -= System.nanoTime() - nanoTime;
                }
            }
        }

        public final void b() {
            synchronized (this.h) {
                if (!this.j) {
                    this.i++;
                } else {
                    throw new RejectedExecutionException("Executor already shutdown");
                }
            }
        }

        @Override // java.util.concurrent.Executor
        public void execute(Runnable runnable) {
            b();
            try {
                runnable.run();
            } finally {
                a();
            }
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isShutdown() {
            boolean z;
            synchronized (this.h) {
                z = this.j;
            }
            return z;
        }

        @Override // java.util.concurrent.ExecutorService
        public boolean isTerminated() {
            boolean z;
            synchronized (this.h) {
                z = this.j && this.i == 0;
            }
            return z;
        }

        @Override // java.util.concurrent.ExecutorService
        public void shutdown() {
            synchronized (this.h) {
                this.j = true;
                if (this.i == 0) {
                    this.h.notifyAll();
                }
            }
        }

        @Override // java.util.concurrent.ExecutorService
        public List<Runnable> shutdownNow() {
            shutdown();
            return Collections.emptyList();
        }

        public /* synthetic */ e(q qVar) {
            this();
        }
    }

    @GwtIncompatible
    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService scheduledExecutorService) {
        if (scheduledExecutorService instanceof ListeningScheduledExecutorService) {
            return (ListeningScheduledExecutorService) scheduledExecutorService;
        }
        return new g(scheduledExecutorService);
    }
}
