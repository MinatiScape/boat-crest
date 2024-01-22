package androidx.work.impl;

import android.content.Context;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.work.Configuration;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkerWrapper;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class Processor implements ExecutionListener, ForegroundProcessor {
    public static final String s = Logger.tagWithPrefix("Processor");
    public Context i;
    public Configuration j;
    public TaskExecutor k;
    public WorkDatabase l;
    public List<Scheduler> o;
    public Map<String, WorkerWrapper> n = new HashMap();
    public Map<String, WorkerWrapper> m = new HashMap();
    public Set<String> p = new HashSet();
    public final List<ExecutionListener> q = new ArrayList();
    @Nullable
    public PowerManager.WakeLock h = null;
    public final Object r = new Object();

    /* loaded from: classes.dex */
    public static class a implements Runnable {
        @NonNull
        public ExecutionListener h;
        @NonNull
        public String i;
        @NonNull
        public ListenableFuture<Boolean> j;

        public a(@NonNull ExecutionListener executionListener, @NonNull String str, @NonNull ListenableFuture<Boolean> listenableFuture) {
            this.h = executionListener;
            this.i = str;
            this.j = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            boolean z;
            try {
                z = this.j.get().booleanValue();
            } catch (InterruptedException | ExecutionException unused) {
                z = true;
            }
            this.h.onExecuted(this.i, z);
        }
    }

    public Processor(@NonNull Context context, @NonNull Configuration configuration, @NonNull TaskExecutor taskExecutor, @NonNull WorkDatabase workDatabase, @NonNull List<Scheduler> list) {
        this.i = context;
        this.j = configuration;
        this.k = taskExecutor;
        this.l = workDatabase;
        this.o = list;
    }

    public static boolean a(@NonNull String str, @Nullable WorkerWrapper workerWrapper) {
        if (workerWrapper != null) {
            workerWrapper.interrupt();
            Logger.get().debug(s, String.format("WorkerWrapper interrupted for %s", str), new Throwable[0]);
            return true;
        }
        Logger.get().debug(s, String.format("WorkerWrapper could not be found for %s", str), new Throwable[0]);
        return false;
    }

    public void addExecutionListener(@NonNull ExecutionListener executionListener) {
        synchronized (this.r) {
            this.q.add(executionListener);
        }
    }

    public final void b() {
        synchronized (this.r) {
            if (!(!this.m.isEmpty())) {
                this.i.startService(SystemForegroundDispatcher.createStopForegroundIntent(this.i));
                PowerManager.WakeLock wakeLock = this.h;
                if (wakeLock != null) {
                    wakeLock.release();
                    this.h = null;
                }
            }
        }
    }

    public boolean hasWork() {
        boolean z;
        synchronized (this.r) {
            z = (this.n.isEmpty() && this.m.isEmpty()) ? false : true;
        }
        return z;
    }

    public boolean isCancelled(@NonNull String str) {
        boolean contains;
        synchronized (this.r) {
            contains = this.p.contains(str);
        }
        return contains;
    }

    public boolean isEnqueued(@NonNull String str) {
        boolean z;
        synchronized (this.r) {
            z = this.n.containsKey(str) || this.m.containsKey(str);
        }
        return z;
    }

    public boolean isEnqueuedInForeground(@NonNull String str) {
        boolean containsKey;
        synchronized (this.r) {
            containsKey = this.m.containsKey(str);
        }
        return containsKey;
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(@NonNull String str, boolean z) {
        synchronized (this.r) {
            this.n.remove(str);
            Logger.get().debug(s, String.format("%s %s executed; reschedule = %s", getClass().getSimpleName(), str, Boolean.valueOf(z)), new Throwable[0]);
            for (ExecutionListener executionListener : this.q) {
                executionListener.onExecuted(str, z);
            }
        }
    }

    public void removeExecutionListener(@NonNull ExecutionListener executionListener) {
        synchronized (this.r) {
            this.q.remove(executionListener);
        }
    }

    @Override // androidx.work.impl.foreground.ForegroundProcessor
    public void startForeground(@NonNull String str, @NonNull ForegroundInfo foregroundInfo) {
        synchronized (this.r) {
            Logger.get().info(s, String.format("Moving WorkSpec (%s) to the foreground", str), new Throwable[0]);
            WorkerWrapper remove = this.n.remove(str);
            if (remove != null) {
                if (this.h == null) {
                    PowerManager.WakeLock newWakeLock = WakeLocks.newWakeLock(this.i, "ProcessorForegroundLck");
                    this.h = newWakeLock;
                    newWakeLock.acquire();
                }
                this.m.put(str, remove);
                ContextCompat.startForegroundService(this.i, SystemForegroundDispatcher.createStartForegroundIntent(this.i, str, foregroundInfo));
            }
        }
    }

    public boolean startWork(@NonNull String str) {
        return startWork(str, null);
    }

    public boolean stopAndCancelWork(@NonNull String str) {
        boolean a2;
        synchronized (this.r) {
            boolean z = true;
            Logger.get().debug(s, String.format("Processor cancelling %s", str), new Throwable[0]);
            this.p.add(str);
            WorkerWrapper remove = this.m.remove(str);
            if (remove == null) {
                z = false;
            }
            if (remove == null) {
                remove = this.n.remove(str);
            }
            a2 = a(str, remove);
            if (z) {
                b();
            }
        }
        return a2;
    }

    @Override // androidx.work.impl.foreground.ForegroundProcessor
    public void stopForeground(@NonNull String str) {
        synchronized (this.r) {
            this.m.remove(str);
            b();
        }
    }

    public boolean stopForegroundWork(@NonNull String str) {
        boolean a2;
        synchronized (this.r) {
            Logger.get().debug(s, String.format("Processor stopping foreground work %s", str), new Throwable[0]);
            a2 = a(str, this.m.remove(str));
        }
        return a2;
    }

    public boolean stopWork(@NonNull String str) {
        boolean a2;
        synchronized (this.r) {
            Logger.get().debug(s, String.format("Processor stopping background work %s", str), new Throwable[0]);
            a2 = a(str, this.n.remove(str));
        }
        return a2;
    }

    public boolean startWork(@NonNull String str, @Nullable WorkerParameters.RuntimeExtras runtimeExtras) {
        synchronized (this.r) {
            if (isEnqueued(str)) {
                Logger.get().debug(s, String.format("Work %s is already enqueued for processing", str), new Throwable[0]);
                return false;
            }
            WorkerWrapper build = new WorkerWrapper.Builder(this.i, this.j, this.k, this, this.l, str).withSchedulers(this.o).withRuntimeExtras(runtimeExtras).build();
            ListenableFuture<Boolean> future = build.getFuture();
            future.addListener(new a(this, str, future), this.k.getMainThreadExecutor());
            this.n.put(str, build);
            this.k.getBackgroundExecutor().execute(build);
            Logger.get().debug(s, String.format("%s: processing %s", getClass().getSimpleName(), str), new Throwable[0]);
            return true;
        }
    }
}
