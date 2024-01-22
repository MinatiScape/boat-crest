package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.background.systemalarm.SystemAlarmDispatcher;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer;
import java.util.Collections;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class DelayMetCommandHandler implements WorkConstraintsCallback, ExecutionListener, WorkTimer.TimeLimitExceededListener {
    public static final String q = Logger.tagWithPrefix("DelayMetCommandHandler");
    public final Context h;
    public final int i;
    public final String j;
    public final SystemAlarmDispatcher k;
    public final WorkConstraintsTracker l;
    @Nullable
    public PowerManager.WakeLock o;
    public boolean p = false;
    public int n = 0;
    public final Object m = new Object();

    public DelayMetCommandHandler(@NonNull Context context, int i, @NonNull String str, @NonNull SystemAlarmDispatcher systemAlarmDispatcher) {
        this.h = context;
        this.i = i;
        this.k = systemAlarmDispatcher;
        this.j = str;
        this.l = new WorkConstraintsTracker(context, systemAlarmDispatcher.d(), this);
    }

    public final void a() {
        synchronized (this.m) {
            this.l.reset();
            this.k.f().stopTimer(this.j);
            PowerManager.WakeLock wakeLock = this.o;
            if (wakeLock != null && wakeLock.isHeld()) {
                Logger.get().debug(q, String.format("Releasing wakelock %s for WorkSpec %s", this.o, this.j), new Throwable[0]);
                this.o.release();
            }
        }
    }

    @WorkerThread
    public void b() {
        this.o = WakeLocks.newWakeLock(this.h, String.format("%s (%s)", this.j, Integer.valueOf(this.i)));
        Logger logger = Logger.get();
        String str = q;
        logger.debug(str, String.format("Acquiring wakelock %s for WorkSpec %s", this.o, this.j), new Throwable[0]);
        this.o.acquire();
        WorkSpec workSpec = this.k.e().getWorkDatabase().workSpecDao().getWorkSpec(this.j);
        if (workSpec == null) {
            c();
            return;
        }
        boolean hasConstraints = workSpec.hasConstraints();
        this.p = hasConstraints;
        if (!hasConstraints) {
            Logger.get().debug(str, String.format("No constraints for %s", this.j), new Throwable[0]);
            onAllConstraintsMet(Collections.singletonList(this.j));
            return;
        }
        this.l.replace(Collections.singletonList(workSpec));
    }

    public final void c() {
        synchronized (this.m) {
            if (this.n < 2) {
                this.n = 2;
                Logger logger = Logger.get();
                String str = q;
                logger.debug(str, String.format("Stopping work for WorkSpec %s", this.j), new Throwable[0]);
                Intent f = CommandHandler.f(this.h, this.j);
                SystemAlarmDispatcher systemAlarmDispatcher = this.k;
                systemAlarmDispatcher.i(new SystemAlarmDispatcher.b(systemAlarmDispatcher, f, this.i));
                if (this.k.c().isEnqueued(this.j)) {
                    Logger.get().debug(str, String.format("WorkSpec %s needs to be rescheduled", this.j), new Throwable[0]);
                    Intent e = CommandHandler.e(this.h, this.j);
                    SystemAlarmDispatcher systemAlarmDispatcher2 = this.k;
                    systemAlarmDispatcher2.i(new SystemAlarmDispatcher.b(systemAlarmDispatcher2, e, this.i));
                } else {
                    Logger.get().debug(str, String.format("Processor does not have WorkSpec %s. No need to reschedule ", this.j), new Throwable[0]);
                }
            } else {
                Logger.get().debug(q, String.format("Already stopped work for %s", this.j), new Throwable[0]);
            }
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(@NonNull List<String> list) {
        if (list.contains(this.j)) {
            synchronized (this.m) {
                if (this.n == 0) {
                    this.n = 1;
                    Logger.get().debug(q, String.format("onAllConstraintsMet for %s", this.j), new Throwable[0]);
                    if (this.k.c().startWork(this.j)) {
                        this.k.f().startTimer(this.j, 600000L, this);
                    } else {
                        a();
                    }
                } else {
                    Logger.get().debug(q, String.format("Already started work for %s", this.j), new Throwable[0]);
                }
            }
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(@NonNull List<String> list) {
        c();
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(@NonNull String str, boolean z) {
        Logger.get().debug(q, String.format("onExecuted %s, %s", str, Boolean.valueOf(z)), new Throwable[0]);
        a();
        if (z) {
            Intent e = CommandHandler.e(this.h, this.j);
            SystemAlarmDispatcher systemAlarmDispatcher = this.k;
            systemAlarmDispatcher.i(new SystemAlarmDispatcher.b(systemAlarmDispatcher, e, this.i));
        }
        if (this.p) {
            Intent a2 = CommandHandler.a(this.h);
            SystemAlarmDispatcher systemAlarmDispatcher2 = this.k;
            systemAlarmDispatcher2.i(new SystemAlarmDispatcher.b(systemAlarmDispatcher2, a2, this.i));
        }
    }

    @Override // androidx.work.impl.utils.WorkTimer.TimeLimitExceededListener
    public void onTimeLimitExceeded(@NonNull String str) {
        Logger.get().debug(q, String.format("Exceeded time limits on execution for %s", str), new Throwable[0]);
        c();
    }
}
