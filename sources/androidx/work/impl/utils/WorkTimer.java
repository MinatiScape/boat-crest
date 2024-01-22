package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class WorkTimer {
    public static final String f = Logger.tagWithPrefix("WorkTimer");

    /* renamed from: a  reason: collision with root package name */
    public final ThreadFactory f1838a;
    public final ScheduledExecutorService b;
    public final Map<String, WorkTimerRunnable> c;
    public final Map<String, TimeLimitExceededListener> d;
    public final Object e;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public interface TimeLimitExceededListener {
        void onTimeLimitExceeded(@NonNull String str);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static class WorkTimerRunnable implements Runnable {
        public final WorkTimer h;
        public final String i;

        public WorkTimerRunnable(@NonNull WorkTimer workTimer, @NonNull String str) {
            this.h = workTimer;
            this.i = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (this.h.e) {
                if (this.h.c.remove(this.i) != null) {
                    TimeLimitExceededListener remove = this.h.d.remove(this.i);
                    if (remove != null) {
                        remove.onTimeLimitExceeded(this.i);
                    }
                } else {
                    Logger.get().debug("WrkTimerRunnable", String.format("Timer with %s is already marked as complete.", this.i), new Throwable[0]);
                }
            }
        }
    }

    /* loaded from: classes.dex */
    public class a implements ThreadFactory {
        public int h = 0;

        public a(WorkTimer workTimer) {
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            Thread newThread = Executors.defaultThreadFactory().newThread(runnable);
            newThread.setName("WorkManager-WorkTimer-thread-" + this.h);
            this.h = this.h + 1;
            return newThread;
        }
    }

    public WorkTimer() {
        a aVar = new a(this);
        this.f1838a = aVar;
        this.c = new HashMap();
        this.d = new HashMap();
        this.e = new Object();
        this.b = Executors.newSingleThreadScheduledExecutor(aVar);
    }

    @NonNull
    @VisibleForTesting
    public ScheduledExecutorService getExecutorService() {
        return this.b;
    }

    @NonNull
    @VisibleForTesting
    public synchronized Map<String, TimeLimitExceededListener> getListeners() {
        return this.d;
    }

    @NonNull
    @VisibleForTesting
    public synchronized Map<String, WorkTimerRunnable> getTimerMap() {
        return this.c;
    }

    public void onDestroy() {
        if (this.b.isShutdown()) {
            return;
        }
        this.b.shutdownNow();
    }

    public void startTimer(@NonNull String str, long j, @NonNull TimeLimitExceededListener timeLimitExceededListener) {
        synchronized (this.e) {
            Logger.get().debug(f, String.format("Starting timer for %s", str), new Throwable[0]);
            stopTimer(str);
            WorkTimerRunnable workTimerRunnable = new WorkTimerRunnable(this, str);
            this.c.put(str, workTimerRunnable);
            this.d.put(str, timeLimitExceededListener);
            this.b.schedule(workTimerRunnable, j, TimeUnit.MILLISECONDS);
        }
    }

    public void stopTimer(@NonNull String str) {
        synchronized (this.e) {
            if (this.c.remove(str) != null) {
                Logger.get().debug(f, String.format("Stopping timer for %s", str), new Throwable[0]);
                this.d.remove(str);
            }
        }
    }
}
