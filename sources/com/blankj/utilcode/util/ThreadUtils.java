package com.blankj.utilcode.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.CallSuper;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import java.lang.Thread;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
/* loaded from: classes.dex */
public final class ThreadUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final Handler f2290a = new Handler(Looper.getMainLooper());
    public static final Map<Integer, Map<Integer, ExecutorService>> b = new HashMap();
    public static final Map<Task, ExecutorService> c = new ConcurrentHashMap();
    public static final int d = Runtime.getRuntime().availableProcessors();
    public static final Timer e = new Timer();
    public static Executor f;

    /* loaded from: classes.dex */
    public static abstract class SimpleTask<T> extends Task<T> {
        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public void onCancel() {
            Log.e("ThreadUtils", "onCancel: " + Thread.currentThread());
        }

        @Override // com.blankj.utilcode.util.ThreadUtils.Task
        public void onFail(Throwable th) {
            Log.e("ThreadUtils", "onFail: ", th);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Task<T> implements Runnable {
        public final AtomicInteger h = new AtomicInteger(0);
        public volatile boolean i;
        public volatile Thread j;
        public Timer k;
        public long l;
        public OnTimeoutListener m;
        public Executor n;

        /* loaded from: classes.dex */
        public interface OnTimeoutListener {
            void onTimeout();
        }

        /* loaded from: classes.dex */
        public class a extends TimerTask {
            public a() {
            }

            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                if (Task.this.isDone() || Task.this.m == null) {
                    return;
                }
                Task.this.f();
                Task.this.m.onTimeout();
                Task.this.onDone();
            }
        }

        /* loaded from: classes.dex */
        public class b implements Runnable {
            public final /* synthetic */ Object h;

            public b(Object obj) {
                this.h = obj;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                Task.this.onSuccess(this.h);
            }
        }

        /* loaded from: classes.dex */
        public class c implements Runnable {
            public final /* synthetic */ Object h;

            public c(Object obj) {
                this.h = obj;
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                Task.this.onSuccess(this.h);
                Task.this.onDone();
            }
        }

        /* loaded from: classes.dex */
        public class d implements Runnable {
            public final /* synthetic */ Throwable h;

            public d(Throwable th) {
                this.h = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                Task.this.onFail(this.h);
                Task.this.onDone();
            }
        }

        /* loaded from: classes.dex */
        public class e implements Runnable {
            public e() {
            }

            @Override // java.lang.Runnable
            public void run() {
                Task.this.onCancel();
                Task.this.onDone();
            }
        }

        public void cancel() {
            cancel(true);
        }

        public final Executor d() {
            Executor executor = this.n;
            return executor == null ? ThreadUtils.b() : executor;
        }

        public abstract T doInBackground() throws Throwable;

        public final void e(boolean z) {
            this.i = z;
        }

        public final void f() {
            synchronized (this.h) {
                if (this.h.get() > 1) {
                    return;
                }
                this.h.set(6);
                if (this.j != null) {
                    this.j.interrupt();
                }
            }
        }

        public boolean isCanceled() {
            return this.h.get() >= 4;
        }

        public boolean isDone() {
            return this.h.get() > 1;
        }

        public abstract void onCancel();

        @CallSuper
        public void onDone() {
            ThreadUtils.c.remove(this);
            Timer timer = this.k;
            if (timer != null) {
                timer.cancel();
                this.k = null;
                this.m = null;
            }
        }

        public abstract void onFail(Throwable th);

        public abstract void onSuccess(T t);

        @Override // java.lang.Runnable
        public void run() {
            if (this.i) {
                if (this.j == null) {
                    if (!this.h.compareAndSet(0, 1)) {
                        return;
                    }
                    this.j = Thread.currentThread();
                    if (this.m != null) {
                        Log.w("ThreadUtils", "Scheduled task doesn't support timeout.");
                    }
                } else if (this.h.get() != 1) {
                    return;
                }
            } else if (!this.h.compareAndSet(0, 1)) {
                return;
            } else {
                this.j = Thread.currentThread();
                if (this.m != null) {
                    Timer timer = new Timer();
                    this.k = timer;
                    timer.schedule(new a(), this.l);
                }
            }
            try {
                T doInBackground = doInBackground();
                if (this.i) {
                    if (this.h.get() != 1) {
                        return;
                    }
                    d().execute(new b(doInBackground));
                } else if (this.h.compareAndSet(1, 3)) {
                    d().execute(new c(doInBackground));
                }
            } catch (InterruptedException unused) {
                this.h.compareAndSet(4, 5);
            } catch (Throwable th) {
                if (this.h.compareAndSet(1, 2)) {
                    d().execute(new d(th));
                }
            }
        }

        public Task<T> setDeliver(Executor executor) {
            this.n = executor;
            return this;
        }

        public Task<T> setTimeout(long j, OnTimeoutListener onTimeoutListener) {
            this.l = j;
            this.m = onTimeoutListener;
            return this;
        }

        public void cancel(boolean z) {
            synchronized (this.h) {
                if (this.h.get() > 1) {
                    return;
                }
                this.h.set(4);
                if (z && this.j != null) {
                    this.j.interrupt();
                }
                d().execute(new e());
            }
        }
    }

    /* loaded from: classes.dex */
    public static class a extends TimerTask {
        public final /* synthetic */ ExecutorService h;
        public final /* synthetic */ Task i;

        public a(ExecutorService executorService, Task task) {
            this.h = executorService;
            this.i = task;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.h.execute(this.i);
        }
    }

    /* loaded from: classes.dex */
    public static class b extends TimerTask {
        public final /* synthetic */ ExecutorService h;
        public final /* synthetic */ Task i;

        public b(ExecutorService executorService, Task task) {
            this.h = executorService;
            this.i = task;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            this.h.execute(this.i);
        }
    }

    /* loaded from: classes.dex */
    public static class c implements Executor {
        @Override // java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            Objects.requireNonNull(runnable, "Argument 'command' of type Runnable (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            ThreadUtils.runOnUiThread(runnable);
        }
    }

    /* loaded from: classes.dex */
    public static final class d extends LinkedBlockingQueue<Runnable> {
        private int mCapacity;
        private volatile e mPool;

        public d() {
            this.mCapacity = Integer.MAX_VALUE;
        }

        @Override // java.util.concurrent.LinkedBlockingQueue, java.util.Queue, java.util.concurrent.BlockingQueue
        public boolean offer(@NonNull Runnable runnable) {
            Objects.requireNonNull(runnable, "Argument 'runnable' of type Runnable (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            if (this.mCapacity > size() || this.mPool == null || this.mPool.getPoolSize() >= this.mPool.getMaximumPoolSize()) {
                return super.offer((d) runnable);
            }
            return false;
        }

        public d(boolean z) {
            this.mCapacity = Integer.MAX_VALUE;
            if (z) {
                this.mCapacity = 0;
            }
        }

        public d(int i) {
            this.mCapacity = Integer.MAX_VALUE;
            this.mCapacity = i;
        }
    }

    /* loaded from: classes.dex */
    public static final class e extends ThreadPoolExecutor {
        public final AtomicInteger h;
        public d i;

        public e(int i, int i2, long j, TimeUnit timeUnit, d dVar, ThreadFactory threadFactory) {
            super(i, i2, j, timeUnit, dVar, threadFactory);
            this.h = new AtomicInteger();
            dVar.mPool = this;
            this.i = dVar;
        }

        public static ExecutorService b(int i, int i2) {
            if (i != -8) {
                if (i != -4) {
                    if (i != -2) {
                        if (i != -1) {
                            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
                            d dVar = new d();
                            return new e(i, i, 0L, timeUnit, dVar, new f("fixed(" + i + ")", i2));
                        }
                        return new e(1, 1, 0L, TimeUnit.MILLISECONDS, new d(), new f("single", i2));
                    }
                    return new e(0, 128, 60L, TimeUnit.SECONDS, new d(true), new f("cached", i2));
                }
                return new e((ThreadUtils.d * 2) + 1, (ThreadUtils.d * 2) + 1, 30L, TimeUnit.SECONDS, new d(), new f("io", i2));
            }
            return new e(ThreadUtils.d + 1, (ThreadUtils.d * 2) + 1, 30L, TimeUnit.SECONDS, new d(true), new f("cpu", i2));
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public void afterExecute(Runnable runnable, Throwable th) {
            this.h.decrementAndGet();
            super.afterExecute(runnable, th);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
        public void execute(@NonNull Runnable runnable) {
            Objects.requireNonNull(runnable, "Argument 'command' of type Runnable (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            if (isShutdown()) {
                return;
            }
            this.h.incrementAndGet();
            try {
                super.execute(runnable);
            } catch (RejectedExecutionException unused) {
                Log.e("ThreadUtils", "This will not happen!");
                this.i.offer(runnable);
            } catch (Throwable unused2) {
                this.h.decrementAndGet();
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class f extends AtomicLong implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private static final long serialVersionUID = -9209200509960368598L;
        private final boolean isDaemon;
        private final String namePrefix;
        private final int priority;

        /* loaded from: classes.dex */
        public class a extends Thread {
            public a(f fVar, Runnable runnable, String str) {
                super(runnable, str);
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    super.run();
                } catch (Throwable th) {
                    Log.e("ThreadUtils", "Request threw uncaught throwable", th);
                }
            }
        }

        /* loaded from: classes.dex */
        public class b implements Thread.UncaughtExceptionHandler {
            public b(f fVar) {
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                System.out.println(th);
            }
        }

        public f(String str, int i) {
            this(str, i, false);
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            Objects.requireNonNull(runnable, "Argument 'r' of type Runnable (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
            a aVar = new a(this, runnable, this.namePrefix + getAndIncrement());
            aVar.setDaemon(this.isDaemon);
            aVar.setUncaughtExceptionHandler(new b(this));
            aVar.setPriority(this.priority);
            return aVar;
        }

        public f(String str, int i, boolean z) {
            this.namePrefix = str + "-pool-" + POOL_NUMBER.getAndIncrement() + "-thread-";
            this.priority = i;
            this.isDaemon = z;
        }
    }

    public static /* synthetic */ Executor b() {
        return h();
    }

    public static void cancel(Task task) {
        if (task == null) {
            return;
        }
        task.cancel();
    }

    public static <T> void d(ExecutorService executorService, Task<T> task) {
        e(executorService, task, 0L, 0L, null);
    }

    public static <T> void e(ExecutorService executorService, Task<T> task, long j, long j2, TimeUnit timeUnit) {
        Map<Task, ExecutorService> map = c;
        synchronized (map) {
            if (map.get(task) != null) {
                Log.e("ThreadUtils", "Task can only be executed once.");
                return;
            }
            map.put(task, executorService);
            if (j2 != 0) {
                task.e(true);
                e.scheduleAtFixedRate(new b(executorService, task), timeUnit.toMillis(j), timeUnit.toMillis(j2));
            } else if (j == 0) {
                executorService.execute(task);
            } else {
                e.schedule(new a(executorService, task), timeUnit.toMillis(j));
            }
        }
    }

    public static <T> void executeByCached(Task<T> task) {
        d(i(-2), task);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        f(i(-2), task, 0L, j, timeUnit);
    }

    public static <T> void executeByCachedWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        g(i(-2), task, j, timeUnit);
    }

    public static <T> void executeByCpu(Task<T> task) {
        d(i(-8), task);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        f(i(-8), task, 0L, j, timeUnit);
    }

    public static <T> void executeByCpuWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        g(i(-8), task, j, timeUnit);
    }

    public static <T> void executeByCustom(ExecutorService executorService, Task<T> task) {
        d(executorService, task);
    }

    public static <T> void executeByCustomAtFixRate(ExecutorService executorService, Task<T> task, long j, TimeUnit timeUnit) {
        f(executorService, task, 0L, j, timeUnit);
    }

    public static <T> void executeByCustomWithDelay(ExecutorService executorService, Task<T> task, long j, TimeUnit timeUnit) {
        g(executorService, task, j, timeUnit);
    }

    public static <T> void executeByFixed(@IntRange(from = 1) int i, Task<T> task) {
        d(i(i), task);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit) {
        f(i(i), task, 0L, j, timeUnit);
    }

    public static <T> void executeByFixedWithDelay(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit) {
        g(i(i), task, j, timeUnit);
    }

    public static <T> void executeByIo(Task<T> task) {
        d(i(-4), task);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        f(i(-4), task, 0L, j, timeUnit);
    }

    public static <T> void executeByIoWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        g(i(-4), task, j, timeUnit);
    }

    public static <T> void executeBySingle(Task<T> task) {
        d(i(-1), task);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, TimeUnit timeUnit) {
        f(i(-1), task, 0L, j, timeUnit);
    }

    public static <T> void executeBySingleWithDelay(Task<T> task, long j, TimeUnit timeUnit) {
        g(i(-1), task, j, timeUnit);
    }

    public static <T> void f(ExecutorService executorService, Task<T> task, long j, long j2, TimeUnit timeUnit) {
        e(executorService, task, j, j2, timeUnit);
    }

    public static <T> void g(ExecutorService executorService, Task<T> task, long j, TimeUnit timeUnit) {
        e(executorService, task, j, 0L, timeUnit);
    }

    public static ExecutorService getCachedPool() {
        return i(-2);
    }

    public static ExecutorService getCpuPool() {
        return i(-8);
    }

    public static ExecutorService getFixedPool(@IntRange(from = 1) int i) {
        return i(i);
    }

    public static ExecutorService getIoPool() {
        return i(-4);
    }

    public static Handler getMainHandler() {
        return f2290a;
    }

    public static ExecutorService getSinglePool() {
        return i(-1);
    }

    public static Executor h() {
        if (f == null) {
            f = new c();
        }
        return f;
    }

    public static ExecutorService i(int i) {
        return j(i, 5);
    }

    public static boolean isMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static ExecutorService j(int i, int i2) {
        ExecutorService executorService;
        Map<Integer, Map<Integer, ExecutorService>> map = b;
        synchronized (map) {
            Map<Integer, ExecutorService> map2 = map.get(Integer.valueOf(i));
            if (map2 == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                executorService = e.b(i, i2);
                concurrentHashMap.put(Integer.valueOf(i2), executorService);
                map.put(Integer.valueOf(i), concurrentHashMap);
            } else {
                executorService = map2.get(Integer.valueOf(i2));
                if (executorService == null) {
                    executorService = e.b(i, i2);
                    map2.put(Integer.valueOf(i2), executorService);
                }
            }
        }
        return executorService;
    }

    public static void runOnUiThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            f2290a.post(runnable);
        }
    }

    public static void runOnUiThreadDelayed(Runnable runnable, long j) {
        f2290a.postDelayed(runnable, j);
    }

    public static void setDeliver(Executor executor) {
        f = executor;
    }

    public static void cancel(Task... taskArr) {
        if (taskArr == null || taskArr.length == 0) {
            return;
        }
        for (Task task : taskArr) {
            if (task != null) {
                task.cancel();
            }
        }
    }

    public static <T> void executeByCached(Task<T> task, @IntRange(from = 1, to = 10) int i) {
        d(j(-2, i), task);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        f(j(-2, i), task, 0L, j, timeUnit);
    }

    public static <T> void executeByCachedWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        g(j(-2, i), task, j, timeUnit);
    }

    public static <T> void executeByCpu(Task<T> task, @IntRange(from = 1, to = 10) int i) {
        d(j(-8, i), task);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        f(j(-8, i), task, 0L, j, timeUnit);
    }

    public static <T> void executeByCpuWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        g(j(-8, i), task, j, timeUnit);
    }

    public static <T> void executeByCustomAtFixRate(ExecutorService executorService, Task<T> task, long j, long j2, TimeUnit timeUnit) {
        f(executorService, task, j, j2, timeUnit);
    }

    public static <T> void executeByFixed(@IntRange(from = 1) int i, Task<T> task, @IntRange(from = 1, to = 10) int i2) {
        d(j(i, i2), task);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i2) {
        f(j(i, i2), task, 0L, j, timeUnit);
    }

    public static <T> void executeByFixedWithDelay(@IntRange(from = 1) int i, Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i2) {
        g(j(i, i2), task, j, timeUnit);
    }

    public static <T> void executeByIo(Task<T> task, @IntRange(from = 1, to = 10) int i) {
        d(j(-4, i), task);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        f(j(-4, i), task, 0L, j, timeUnit);
    }

    public static <T> void executeByIoWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        g(j(-4, i), task, j, timeUnit);
    }

    public static <T> void executeBySingle(Task<T> task, @IntRange(from = 1, to = 10) int i) {
        d(j(-1, i), task);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        f(j(-1, i), task, 0L, j, timeUnit);
    }

    public static <T> void executeBySingleWithDelay(Task<T> task, long j, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        g(j(-1, i), task, j, timeUnit);
    }

    public static ExecutorService getCachedPool(@IntRange(from = 1, to = 10) int i) {
        return j(-2, i);
    }

    public static ExecutorService getCpuPool(@IntRange(from = 1, to = 10) int i) {
        return j(-8, i);
    }

    public static ExecutorService getFixedPool(@IntRange(from = 1) int i, @IntRange(from = 1, to = 10) int i2) {
        return j(i, i2);
    }

    public static ExecutorService getIoPool(@IntRange(from = 1, to = 10) int i) {
        return j(-4, i);
    }

    public static ExecutorService getSinglePool(@IntRange(from = 1, to = 10) int i) {
        return j(-1, i);
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        f(i(-2), task, j, j2, timeUnit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        f(i(-8), task, j, j2, timeUnit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, long j2, TimeUnit timeUnit) {
        f(i(i), task, j, j2, timeUnit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        f(i(-4), task, j, j2, timeUnit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit) {
        f(i(-1), task, j, j2, timeUnit);
    }

    /* loaded from: classes.dex */
    public static class SyncValue<T> {

        /* renamed from: a  reason: collision with root package name */
        public CountDownLatch f2291a = new CountDownLatch(1);
        public AtomicBoolean b = new AtomicBoolean();
        public T c;

        public T getValue() {
            if (!this.b.get()) {
                try {
                    this.f2291a.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return this.c;
        }

        public void setValue(T t) {
            if (this.b.compareAndSet(false, true)) {
                this.c = t;
                this.f2291a.countDown();
            }
        }

        public T getValue(long j, TimeUnit timeUnit, T t) {
            if (!this.b.get()) {
                try {
                    this.f2291a.await(j, timeUnit);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return t;
                }
            }
            return this.c;
        }
    }

    public static <T> void executeByCachedAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        f(j(-2, i), task, j, j2, timeUnit);
    }

    public static <T> void executeByCpuAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        f(j(-8, i), task, j, j2, timeUnit);
    }

    public static <T> void executeByFixedAtFixRate(@IntRange(from = 1) int i, Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i2) {
        f(j(i, i2), task, j, j2, timeUnit);
    }

    public static <T> void executeByIoAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        f(j(-4, i), task, j, j2, timeUnit);
    }

    public static <T> void executeBySingleAtFixRate(Task<T> task, long j, long j2, TimeUnit timeUnit, @IntRange(from = 1, to = 10) int i) {
        f(j(-1, i), task, j, j2, timeUnit);
    }

    public static void cancel(List<Task> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        for (Task task : list) {
            if (task != null) {
                task.cancel();
            }
        }
    }

    public static void cancel(ExecutorService executorService) {
        if (executorService instanceof e) {
            for (Map.Entry<Task, ExecutorService> entry : c.entrySet()) {
                if (entry.getValue() == executorService) {
                    cancel(entry.getKey());
                }
            }
            return;
        }
        Log.e("ThreadUtils", "The executorService is not ThreadUtils's pool.");
    }
}
