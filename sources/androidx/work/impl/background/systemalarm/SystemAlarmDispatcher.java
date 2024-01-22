package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.utils.SerialExecutor;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.ArrayList;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class SystemAlarmDispatcher implements ExecutionListener {
    public static final String r = Logger.tagWithPrefix("SystemAlarmDispatcher");
    public final Context h;
    public final TaskExecutor i;
    public final WorkTimer j;
    public final Processor k;
    public final WorkManagerImpl l;
    public final CommandHandler m;
    public final Handler n;
    public final List<Intent> o;
    public Intent p;
    @Nullable
    public c q;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            SystemAlarmDispatcher systemAlarmDispatcher;
            d dVar;
            synchronized (SystemAlarmDispatcher.this.o) {
                SystemAlarmDispatcher systemAlarmDispatcher2 = SystemAlarmDispatcher.this;
                systemAlarmDispatcher2.p = systemAlarmDispatcher2.o.get(0);
            }
            Intent intent = SystemAlarmDispatcher.this.p;
            if (intent != null) {
                String action = intent.getAction();
                int intExtra = SystemAlarmDispatcher.this.p.getIntExtra("KEY_START_ID", 0);
                Logger logger = Logger.get();
                String str = SystemAlarmDispatcher.r;
                logger.debug(str, String.format("Processing command %s, %s", SystemAlarmDispatcher.this.p, Integer.valueOf(intExtra)), new Throwable[0]);
                PowerManager.WakeLock newWakeLock = WakeLocks.newWakeLock(SystemAlarmDispatcher.this.h, String.format("%s (%s)", action, Integer.valueOf(intExtra)));
                try {
                    Logger.get().debug(str, String.format("Acquiring operation wake lock (%s) %s", action, newWakeLock), new Throwable[0]);
                    newWakeLock.acquire();
                    SystemAlarmDispatcher systemAlarmDispatcher3 = SystemAlarmDispatcher.this;
                    systemAlarmDispatcher3.m.o(systemAlarmDispatcher3.p, intExtra, systemAlarmDispatcher3);
                    Logger.get().debug(str, String.format("Releasing operation wake lock (%s) %s", action, newWakeLock), new Throwable[0]);
                    newWakeLock.release();
                    systemAlarmDispatcher = SystemAlarmDispatcher.this;
                    dVar = new d(systemAlarmDispatcher);
                } catch (Throwable th) {
                    try {
                        Logger logger2 = Logger.get();
                        String str2 = SystemAlarmDispatcher.r;
                        logger2.error(str2, "Unexpected error in onHandleIntent", th);
                        Logger.get().debug(str2, String.format("Releasing operation wake lock (%s) %s", action, newWakeLock), new Throwable[0]);
                        newWakeLock.release();
                        systemAlarmDispatcher = SystemAlarmDispatcher.this;
                        dVar = new d(systemAlarmDispatcher);
                    } catch (Throwable th2) {
                        Logger.get().debug(SystemAlarmDispatcher.r, String.format("Releasing operation wake lock (%s) %s", action, newWakeLock), new Throwable[0]);
                        newWakeLock.release();
                        SystemAlarmDispatcher systemAlarmDispatcher4 = SystemAlarmDispatcher.this;
                        systemAlarmDispatcher4.i(new d(systemAlarmDispatcher4));
                        throw th2;
                    }
                }
                systemAlarmDispatcher.i(dVar);
            }
        }
    }

    /* loaded from: classes.dex */
    public static class b implements Runnable {
        public final SystemAlarmDispatcher h;
        public final Intent i;
        public final int j;

        public b(@NonNull SystemAlarmDispatcher systemAlarmDispatcher, @NonNull Intent intent, int i) {
            this.h = systemAlarmDispatcher;
            this.i = intent;
            this.j = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.add(this.i, this.j);
        }
    }

    /* loaded from: classes.dex */
    public interface c {
        void onAllCommandsCompleted();
    }

    /* loaded from: classes.dex */
    public static class d implements Runnable {
        public final SystemAlarmDispatcher h;

        public d(@NonNull SystemAlarmDispatcher systemAlarmDispatcher) {
            this.h = systemAlarmDispatcher;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.b();
        }
    }

    public SystemAlarmDispatcher(@NonNull Context context) {
        this(context, null, null);
    }

    public final void a() {
        if (this.n.getLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }

    @MainThread
    public boolean add(@NonNull Intent intent, int i) {
        Logger logger = Logger.get();
        String str = r;
        logger.debug(str, String.format("Adding command %s (%s)", intent, Integer.valueOf(i)), new Throwable[0]);
        a();
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            Logger.get().warning(str, "Unknown command. Ignoring", new Throwable[0]);
            return false;
        } else if ("ACTION_CONSTRAINTS_CHANGED".equals(action) && g("ACTION_CONSTRAINTS_CHANGED")) {
            return false;
        } else {
            intent.putExtra("KEY_START_ID", i);
            synchronized (this.o) {
                boolean z = this.o.isEmpty() ? false : true;
                this.o.add(intent);
                if (!z) {
                    j();
                }
            }
            return true;
        }
    }

    @MainThread
    public void b() {
        Logger logger = Logger.get();
        String str = r;
        logger.debug(str, "Checking if commands are complete.", new Throwable[0]);
        a();
        synchronized (this.o) {
            if (this.p != null) {
                Logger.get().debug(str, String.format("Removing command %s", this.p), new Throwable[0]);
                if (this.o.remove(0).equals(this.p)) {
                    this.p = null;
                } else {
                    throw new IllegalStateException("Dequeue-d command is not the first.");
                }
            }
            SerialExecutor backgroundExecutor = this.i.getBackgroundExecutor();
            if (!this.m.n() && this.o.isEmpty() && !backgroundExecutor.hasPendingTasks()) {
                Logger.get().debug(str, "No more commands & intents.", new Throwable[0]);
                c cVar = this.q;
                if (cVar != null) {
                    cVar.onAllCommandsCompleted();
                }
            } else if (!this.o.isEmpty()) {
                j();
            }
        }
    }

    public Processor c() {
        return this.k;
    }

    public TaskExecutor d() {
        return this.i;
    }

    public WorkManagerImpl e() {
        return this.l;
    }

    public WorkTimer f() {
        return this.j;
    }

    @MainThread
    public final boolean g(@NonNull String str) {
        a();
        synchronized (this.o) {
            for (Intent intent : this.o) {
                if (str.equals(intent.getAction())) {
                    return true;
                }
            }
            return false;
        }
    }

    public void h() {
        Logger.get().debug(r, "Destroying SystemAlarmDispatcher", new Throwable[0]);
        this.k.removeExecutionListener(this);
        this.j.onDestroy();
        this.q = null;
    }

    public void i(@NonNull Runnable runnable) {
        this.n.post(runnable);
    }

    @MainThread
    public final void j() {
        a();
        PowerManager.WakeLock newWakeLock = WakeLocks.newWakeLock(this.h, "ProcessCommand");
        try {
            newWakeLock.acquire();
            this.l.getWorkTaskExecutor().executeOnBackgroundThread(new a());
        } finally {
            newWakeLock.release();
        }
    }

    public void k(@NonNull c cVar) {
        if (this.q != null) {
            Logger.get().error(r, "A completion listener for SystemAlarmDispatcher already exists.", new Throwable[0]);
        } else {
            this.q = cVar;
        }
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(@NonNull String str, boolean z) {
        i(new b(this, CommandHandler.c(this.h, str, z), 0));
    }

    @VisibleForTesting
    public SystemAlarmDispatcher(@NonNull Context context, @Nullable Processor processor, @Nullable WorkManagerImpl workManagerImpl) {
        Context applicationContext = context.getApplicationContext();
        this.h = applicationContext;
        this.m = new CommandHandler(applicationContext);
        this.j = new WorkTimer();
        workManagerImpl = workManagerImpl == null ? WorkManagerImpl.getInstance(context) : workManagerImpl;
        this.l = workManagerImpl;
        processor = processor == null ? workManagerImpl.getProcessor() : processor;
        this.k = processor;
        this.i = workManagerImpl.getWorkTaskExecutor();
        processor.addExecutionListener(this);
        this.o = new ArrayList();
        this.p = null;
        this.n = new Handler(Looper.getMainLooper());
    }
}
