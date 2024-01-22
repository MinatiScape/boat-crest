package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.Logger;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes10.dex */
public final class ExecutorUtils {

    /* loaded from: classes10.dex */
    public class a implements ThreadFactory {
        public final /* synthetic */ String h;
        public final /* synthetic */ AtomicLong i;

        /* renamed from: com.google.firebase.crashlytics.internal.common.ExecutorUtils$a$a  reason: collision with other inner class name */
        /* loaded from: classes10.dex */
        public class C0535a extends BackgroundPriorityRunnable {
            public final /* synthetic */ Runnable h;

            public C0535a(a aVar, Runnable runnable) {
                this.h = runnable;
            }

            @Override // com.google.firebase.crashlytics.internal.common.BackgroundPriorityRunnable
            public void onRun() {
                this.h.run();
            }
        }

        public a(String str, AtomicLong atomicLong) {
            this.h = str;
            this.i = atomicLong;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread newThread = Executors.defaultThreadFactory().newThread(new C0535a(this, runnable));
            newThread.setName(this.h + this.i.getAndIncrement());
            return newThread;
        }
    }

    /* loaded from: classes10.dex */
    public class b extends BackgroundPriorityRunnable {
        public final /* synthetic */ String h;
        public final /* synthetic */ ExecutorService i;
        public final /* synthetic */ long j;
        public final /* synthetic */ TimeUnit k;

        public b(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
            this.h = str;
            this.i = executorService;
            this.j = j;
            this.k = timeUnit;
        }

        @Override // com.google.firebase.crashlytics.internal.common.BackgroundPriorityRunnable
        public void onRun() {
            try {
                Logger logger = Logger.getLogger();
                logger.d("Executing shutdown hook for " + this.h);
                this.i.shutdown();
                if (this.i.awaitTermination(this.j, this.k)) {
                    return;
                }
                Logger logger2 = Logger.getLogger();
                logger2.d(this.h + " did not shut down in the allocated time. Requesting immediate shutdown.");
                this.i.shutdownNow();
            } catch (InterruptedException unused) {
                Logger.getLogger().d(String.format(Locale.US, "Interrupted while waiting for %s to shut down. Requesting immediate shutdown.", this.h));
                this.i.shutdownNow();
            }
        }
    }

    public static void a(String str, ExecutorService executorService) {
        b(str, executorService, 2L, TimeUnit.SECONDS);
    }

    public static void b(String str, ExecutorService executorService, long j, TimeUnit timeUnit) {
        Runtime runtime = Runtime.getRuntime();
        b bVar = new b(str, executorService, j, timeUnit);
        runtime.addShutdownHook(new Thread(bVar, "Crashlytics Shutdown Hook for " + str));
    }

    public static ExecutorService buildSingleThreadExecutorService(String str) {
        ExecutorService c = c(getNamedThreadFactory(str), new ThreadPoolExecutor.DiscardPolicy());
        a(str, c);
        return c;
    }

    public static ScheduledExecutorService buildSingleThreadScheduledExecutorService(String str) {
        ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(getNamedThreadFactory(str));
        a(str, newSingleThreadScheduledExecutor);
        return newSingleThreadScheduledExecutor;
    }

    public static ExecutorService c(ThreadFactory threadFactory, RejectedExecutionHandler rejectedExecutionHandler) {
        return Executors.unconfigurableExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), threadFactory, rejectedExecutionHandler));
    }

    public static ThreadFactory getNamedThreadFactory(String str) {
        return new a(str, new AtomicLong(1L));
    }
}
