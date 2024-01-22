package androidx.work.impl.background.greedy;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.ProcessUtils;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.clevertap.android.sdk.Constants;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class GreedyScheduler implements Scheduler, WorkConstraintsCallback, ExecutionListener {
    public static final String p = Logger.tagWithPrefix("GreedyScheduler");
    public final Context h;
    public final WorkManagerImpl i;
    public final WorkConstraintsTracker j;
    public DelayedWorkTracker l;
    public boolean m;
    public Boolean o;
    public final Set<WorkSpec> k = new HashSet();
    public final Object n = new Object();

    public GreedyScheduler(@NonNull Context context, @NonNull Configuration configuration, @NonNull TaskExecutor taskExecutor, @NonNull WorkManagerImpl workManagerImpl) {
        this.h = context;
        this.i = workManagerImpl;
        this.j = new WorkConstraintsTracker(context, taskExecutor, this);
        this.l = new DelayedWorkTracker(this, configuration.getRunnableScheduler());
    }

    public final void a() {
        this.o = Boolean.valueOf(ProcessUtils.isDefaultProcess(this.h, this.i.getConfiguration()));
    }

    public final void b() {
        if (this.m) {
            return;
        }
        this.i.getProcessor().addExecutionListener(this);
        this.m = true;
    }

    public final void c(@NonNull String str) {
        synchronized (this.n) {
            Iterator<WorkSpec> it = this.k.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WorkSpec next = it.next();
                if (next.id.equals(str)) {
                    Logger.get().debug(p, String.format("Stopping tracking for %s", str), new Throwable[0]);
                    this.k.remove(next);
                    this.j.replace(this.k);
                    break;
                }
            }
        }
    }

    @Override // androidx.work.impl.Scheduler
    public void cancel(@NonNull String str) {
        if (this.o == null) {
            a();
        }
        if (!this.o.booleanValue()) {
            Logger.get().info(p, "Ignoring schedule request in non-main process", new Throwable[0]);
            return;
        }
        b();
        Logger.get().debug(p, String.format("Cancelling work ID %s", str), new Throwable[0]);
        DelayedWorkTracker delayedWorkTracker = this.l;
        if (delayedWorkTracker != null) {
            delayedWorkTracker.unschedule(str);
        }
        this.i.stopWork(str);
    }

    @Override // androidx.work.impl.Scheduler
    public boolean hasLimitedSchedulingSlots() {
        return false;
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(@NonNull List<String> list) {
        for (String str : list) {
            Logger.get().debug(p, String.format("Constraints met: Scheduling work ID %s", str), new Throwable[0]);
            this.i.startWork(str);
        }
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(@NonNull List<String> list) {
        for (String str : list) {
            Logger.get().debug(p, String.format("Constraints not met: Cancelling work ID %s", str), new Throwable[0]);
            this.i.stopWork(str);
        }
    }

    @Override // androidx.work.impl.ExecutionListener
    public void onExecuted(@NonNull String str, boolean z) {
        c(str);
    }

    @Override // androidx.work.impl.Scheduler
    public void schedule(@NonNull WorkSpec... workSpecArr) {
        if (this.o == null) {
            a();
        }
        if (!this.o.booleanValue()) {
            Logger.get().info(p, "Ignoring schedule request in a secondary process", new Throwable[0]);
            return;
        }
        b();
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (WorkSpec workSpec : workSpecArr) {
            long calculateNextRunTime = workSpec.calculateNextRunTime();
            long currentTimeMillis = System.currentTimeMillis();
            if (workSpec.state == WorkInfo.State.ENQUEUED) {
                if (currentTimeMillis < calculateNextRunTime) {
                    DelayedWorkTracker delayedWorkTracker = this.l;
                    if (delayedWorkTracker != null) {
                        delayedWorkTracker.schedule(workSpec);
                    }
                } else if (workSpec.hasConstraints()) {
                    int i = Build.VERSION.SDK_INT;
                    if (i >= 23 && workSpec.constraints.requiresDeviceIdle()) {
                        Logger.get().debug(p, String.format("Ignoring WorkSpec %s, Requires device idle.", workSpec), new Throwable[0]);
                    } else if (i >= 24 && workSpec.constraints.hasContentUriTriggers()) {
                        Logger.get().debug(p, String.format("Ignoring WorkSpec %s, Requires ContentUri triggers.", workSpec), new Throwable[0]);
                    } else {
                        hashSet.add(workSpec);
                        hashSet2.add(workSpec.id);
                    }
                } else {
                    Logger.get().debug(p, String.format("Starting work for %s", workSpec.id), new Throwable[0]);
                    this.i.startWork(workSpec.id);
                }
            }
        }
        synchronized (this.n) {
            if (!hashSet.isEmpty()) {
                Logger.get().debug(p, String.format("Starting tracking for [%s]", TextUtils.join(Constants.SEPARATOR_COMMA, hashSet2)), new Throwable[0]);
                this.k.addAll(hashSet);
                this.j.replace(this.k);
            }
        }
    }

    @VisibleForTesting
    public void setDelayedWorkTracker(@NonNull DelayedWorkTracker delayedWorkTracker) {
        this.l = delayedWorkTracker;
    }

    @VisibleForTesting
    public GreedyScheduler(@NonNull Context context, @NonNull WorkManagerImpl workManagerImpl, @NonNull WorkConstraintsTracker workConstraintsTracker) {
        this.h = context;
        this.i = workManagerImpl;
        this.j = workConstraintsTracker;
    }
}
