package androidx.work.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.work.Configuration;
import androidx.work.Data;
import androidx.work.InputMerger;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.WorkerParameters;
import androidx.work.impl.background.systemalarm.RescheduleReceiver;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.model.WorkTagDao;
import androidx.work.impl.utils.PackageManagerHelper;
import androidx.work.impl.utils.WorkForegroundRunnable;
import androidx.work.impl.utils.WorkForegroundUpdater;
import androidx.work.impl.utils.WorkProgressUpdater;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class WorkerWrapper implements Runnable {
    public static final String A = Logger.tagWithPrefix("WorkerWrapper");
    public Context h;
    public String i;
    public List<Scheduler> j;
    public WorkerParameters.RuntimeExtras k;
    public WorkSpec l;
    public ListenableWorker m;
    public TaskExecutor n;
    public Configuration p;
    public ForegroundProcessor q;
    public WorkDatabase r;
    public WorkSpecDao s;
    public DependencyDao t;
    public WorkTagDao u;
    public List<String> v;
    public String w;
    public volatile boolean z;
    @NonNull
    public ListenableWorker.Result o = ListenableWorker.Result.failure();
    @NonNull
    public SettableFuture<Boolean> x = SettableFuture.create();
    @Nullable
    public ListenableFuture<ListenableWorker.Result> y = null;

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* loaded from: classes.dex */
    public static class Builder {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public Context f1802a;
        @Nullable
        public ListenableWorker b;
        @NonNull
        public ForegroundProcessor c;
        @NonNull
        public TaskExecutor d;
        @NonNull
        public Configuration e;
        @NonNull
        public WorkDatabase f;
        @NonNull
        public String g;
        public List<Scheduler> h;
        @NonNull
        public WorkerParameters.RuntimeExtras i = new WorkerParameters.RuntimeExtras();

        public Builder(@NonNull Context context, @NonNull Configuration configuration, @NonNull TaskExecutor taskExecutor, @NonNull ForegroundProcessor foregroundProcessor, @NonNull WorkDatabase workDatabase, @NonNull String str) {
            this.f1802a = context.getApplicationContext();
            this.d = taskExecutor;
            this.c = foregroundProcessor;
            this.e = configuration;
            this.f = workDatabase;
            this.g = str;
        }

        @NonNull
        public WorkerWrapper build() {
            return new WorkerWrapper(this);
        }

        @NonNull
        public Builder withRuntimeExtras(@Nullable WorkerParameters.RuntimeExtras runtimeExtras) {
            if (runtimeExtras != null) {
                this.i = runtimeExtras;
            }
            return this;
        }

        @NonNull
        public Builder withSchedulers(@NonNull List<Scheduler> list) {
            this.h = list;
            return this;
        }

        @NonNull
        @VisibleForTesting
        public Builder withWorker(@NonNull ListenableWorker listenableWorker) {
            this.b = listenableWorker;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ ListenableFuture h;
        public final /* synthetic */ SettableFuture i;

        public a(ListenableFuture listenableFuture, SettableFuture settableFuture) {
            this.h = listenableFuture;
            this.i = settableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.h.get();
                Logger.get().debug(WorkerWrapper.A, String.format("Starting work for %s", WorkerWrapper.this.l.workerClassName), new Throwable[0]);
                WorkerWrapper workerWrapper = WorkerWrapper.this;
                workerWrapper.y = workerWrapper.m.startWork();
                this.i.setFuture(WorkerWrapper.this.y);
            } catch (Throwable th) {
                this.i.setException(th);
            }
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ SettableFuture h;
        public final /* synthetic */ String i;

        public b(SettableFuture settableFuture, String str) {
            this.h = settableFuture;
            this.i = str;
        }

        @Override // java.lang.Runnable
        @SuppressLint({"SyntheticAccessor"})
        public void run() {
            try {
                try {
                    ListenableWorker.Result result = (ListenableWorker.Result) this.h.get();
                    if (result == null) {
                        Logger.get().error(WorkerWrapper.A, String.format("%s returned a null result. Treating it as a failure.", WorkerWrapper.this.l.workerClassName), new Throwable[0]);
                    } else {
                        Logger.get().debug(WorkerWrapper.A, String.format("%s returned a %s result.", WorkerWrapper.this.l.workerClassName, result), new Throwable[0]);
                        WorkerWrapper.this.o = result;
                    }
                } catch (InterruptedException e) {
                    e = e;
                    Logger.get().error(WorkerWrapper.A, String.format("%s failed because it threw an exception/error", this.i), e);
                } catch (CancellationException e2) {
                    Logger.get().info(WorkerWrapper.A, String.format("%s was cancelled", this.i), e2);
                } catch (ExecutionException e3) {
                    e = e3;
                    Logger.get().error(WorkerWrapper.A, String.format("%s failed because it threw an exception/error", this.i), e);
                }
            } finally {
                WorkerWrapper.this.d();
            }
        }
    }

    public WorkerWrapper(@NonNull Builder builder) {
        this.h = builder.f1802a;
        this.n = builder.d;
        this.q = builder.c;
        this.i = builder.g;
        this.j = builder.h;
        this.k = builder.i;
        this.m = builder.b;
        this.p = builder.e;
        WorkDatabase workDatabase = builder.f;
        this.r = workDatabase;
        this.s = workDatabase.workSpecDao();
        this.t = this.r.dependencyDao();
        this.u = this.r.workTagDao();
    }

    public final String a(List<String> list) {
        StringBuilder sb = new StringBuilder("Work [ id=");
        sb.append(this.i);
        sb.append(", tags={ ");
        boolean z = true;
        for (String str : list) {
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            sb.append(str);
        }
        sb.append(" } ]");
        return sb.toString();
    }

    public final void b(ListenableWorker.Result result) {
        if (result instanceof ListenableWorker.Result.Success) {
            Logger.get().info(A, String.format("Worker result SUCCESS for %s", this.w), new Throwable[0]);
            if (this.l.isPeriodic()) {
                f();
            } else {
                k();
            }
        } else if (result instanceof ListenableWorker.Result.Retry) {
            Logger.get().info(A, String.format("Worker result RETRY for %s", this.w), new Throwable[0]);
            e();
        } else {
            Logger.get().info(A, String.format("Worker result FAILURE for %s", this.w), new Throwable[0]);
            if (this.l.isPeriodic()) {
                f();
            } else {
                j();
            }
        }
    }

    public final void c(String str) {
        LinkedList linkedList = new LinkedList();
        linkedList.add(str);
        while (!linkedList.isEmpty()) {
            String str2 = (String) linkedList.remove();
            if (this.s.getState(str2) != WorkInfo.State.CANCELLED) {
                this.s.setState(WorkInfo.State.FAILED, str2);
            }
            linkedList.addAll(this.t.getDependentWorkIds(str2));
        }
    }

    public void d() {
        if (!l()) {
            this.r.beginTransaction();
            try {
                WorkInfo.State state = this.s.getState(this.i);
                this.r.workProgressDao().delete(this.i);
                if (state == null) {
                    g(false);
                } else if (state == WorkInfo.State.RUNNING) {
                    b(this.o);
                } else if (!state.isFinished()) {
                    e();
                }
                this.r.setTransactionSuccessful();
            } finally {
                this.r.endTransaction();
            }
        }
        List<Scheduler> list = this.j;
        if (list != null) {
            for (Scheduler scheduler : list) {
                scheduler.cancel(this.i);
            }
            Schedulers.schedule(this.p, this.r, this.j);
        }
    }

    public final void e() {
        this.r.beginTransaction();
        try {
            this.s.setState(WorkInfo.State.ENQUEUED, this.i);
            this.s.setPeriodStartTime(this.i, System.currentTimeMillis());
            this.s.markWorkSpecScheduled(this.i, -1L);
            this.r.setTransactionSuccessful();
        } finally {
            this.r.endTransaction();
            g(true);
        }
    }

    public final void f() {
        this.r.beginTransaction();
        try {
            this.s.setPeriodStartTime(this.i, System.currentTimeMillis());
            this.s.setState(WorkInfo.State.ENQUEUED, this.i);
            this.s.resetWorkSpecRunAttemptCount(this.i);
            this.s.markWorkSpecScheduled(this.i, -1L);
            this.r.setTransactionSuccessful();
        } finally {
            this.r.endTransaction();
            g(false);
        }
    }

    public final void g(boolean z) {
        ListenableWorker listenableWorker;
        this.r.beginTransaction();
        try {
            if (!this.r.workSpecDao().hasUnfinishedWork()) {
                PackageManagerHelper.setComponentEnabled(this.h, RescheduleReceiver.class, false);
            }
            if (z) {
                this.s.setState(WorkInfo.State.ENQUEUED, this.i);
                this.s.markWorkSpecScheduled(this.i, -1L);
            }
            if (this.l != null && (listenableWorker = this.m) != null && listenableWorker.isRunInForeground()) {
                this.q.stopForeground(this.i);
            }
            this.r.setTransactionSuccessful();
            this.r.endTransaction();
            this.x.set(Boolean.valueOf(z));
        } catch (Throwable th) {
            this.r.endTransaction();
            throw th;
        }
    }

    @NonNull
    public ListenableFuture<Boolean> getFuture() {
        return this.x;
    }

    public final void h() {
        WorkInfo.State state = this.s.getState(this.i);
        if (state == WorkInfo.State.RUNNING) {
            Logger.get().debug(A, String.format("Status for %s is RUNNING;not doing any work and rescheduling for later execution", this.i), new Throwable[0]);
            g(true);
            return;
        }
        Logger.get().debug(A, String.format("Status for %s is %s; not doing any work", this.i, state), new Throwable[0]);
        g(false);
    }

    public final void i() {
        Data merge;
        if (l()) {
            return;
        }
        this.r.beginTransaction();
        try {
            WorkSpec workSpec = this.s.getWorkSpec(this.i);
            this.l = workSpec;
            if (workSpec == null) {
                Logger.get().error(A, String.format("Didn't find WorkSpec for id %s", this.i), new Throwable[0]);
                g(false);
                this.r.setTransactionSuccessful();
            } else if (workSpec.state != WorkInfo.State.ENQUEUED) {
                h();
                this.r.setTransactionSuccessful();
                Logger.get().debug(A, String.format("%s is not in ENQUEUED state. Nothing more to do.", this.l.workerClassName), new Throwable[0]);
            } else {
                if (workSpec.isPeriodic() || this.l.isBackedOff()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    WorkSpec workSpec2 = this.l;
                    if (!(workSpec2.periodStartTime == 0) && currentTimeMillis < workSpec2.calculateNextRunTime()) {
                        Logger.get().debug(A, String.format("Delaying execution for %s because it is being executed before schedule.", this.l.workerClassName), new Throwable[0]);
                        g(true);
                        this.r.setTransactionSuccessful();
                        return;
                    }
                }
                this.r.setTransactionSuccessful();
                this.r.endTransaction();
                if (this.l.isPeriodic()) {
                    merge = this.l.input;
                } else {
                    InputMerger createInputMergerWithDefaultFallback = this.p.getInputMergerFactory().createInputMergerWithDefaultFallback(this.l.inputMergerClassName);
                    if (createInputMergerWithDefaultFallback == null) {
                        Logger.get().error(A, String.format("Could not create Input Merger %s", this.l.inputMergerClassName), new Throwable[0]);
                        j();
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(this.l.input);
                    arrayList.addAll(this.s.getInputsFromPrerequisites(this.i));
                    merge = createInputMergerWithDefaultFallback.merge(arrayList);
                }
                WorkerParameters workerParameters = new WorkerParameters(UUID.fromString(this.i), merge, this.v, this.k, this.l.runAttemptCount, this.p.getExecutor(), this.n, this.p.getWorkerFactory(), new WorkProgressUpdater(this.r, this.n), new WorkForegroundUpdater(this.r, this.q, this.n));
                if (this.m == null) {
                    this.m = this.p.getWorkerFactory().createWorkerWithDefaultFallback(this.h, this.l.workerClassName, workerParameters);
                }
                ListenableWorker listenableWorker = this.m;
                if (listenableWorker == null) {
                    Logger.get().error(A, String.format("Could not create Worker %s", this.l.workerClassName), new Throwable[0]);
                    j();
                } else if (listenableWorker.isUsed()) {
                    Logger.get().error(A, String.format("Received an already-used Worker %s; WorkerFactory should return new instances", this.l.workerClassName), new Throwable[0]);
                    j();
                } else {
                    this.m.setUsed();
                    if (m()) {
                        if (l()) {
                            return;
                        }
                        SettableFuture create = SettableFuture.create();
                        WorkForegroundRunnable workForegroundRunnable = new WorkForegroundRunnable(this.h, this.l, this.m, workerParameters.getForegroundUpdater(), this.n);
                        this.n.getMainThreadExecutor().execute(workForegroundRunnable);
                        ListenableFuture<Void> future = workForegroundRunnable.getFuture();
                        future.addListener(new a(future, create), this.n.getMainThreadExecutor());
                        create.addListener(new b(create, this.w), this.n.getBackgroundExecutor());
                        return;
                    }
                    h();
                }
            }
        } finally {
            this.r.endTransaction();
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void interrupt() {
        boolean z;
        this.z = true;
        l();
        ListenableFuture<ListenableWorker.Result> listenableFuture = this.y;
        if (listenableFuture != null) {
            z = listenableFuture.isDone();
            this.y.cancel(true);
        } else {
            z = false;
        }
        ListenableWorker listenableWorker = this.m;
        if (listenableWorker != null && !z) {
            listenableWorker.stop();
        } else {
            Logger.get().debug(A, String.format("WorkSpec %s is already done. Not interrupting.", this.l), new Throwable[0]);
        }
    }

    @VisibleForTesting
    public void j() {
        this.r.beginTransaction();
        try {
            c(this.i);
            this.s.setOutput(this.i, ((ListenableWorker.Result.Failure) this.o).getOutputData());
            this.r.setTransactionSuccessful();
        } finally {
            this.r.endTransaction();
            g(false);
        }
    }

    public final void k() {
        this.r.beginTransaction();
        try {
            this.s.setState(WorkInfo.State.SUCCEEDED, this.i);
            this.s.setOutput(this.i, ((ListenableWorker.Result.Success) this.o).getOutputData());
            long currentTimeMillis = System.currentTimeMillis();
            for (String str : this.t.getDependentWorkIds(this.i)) {
                if (this.s.getState(str) == WorkInfo.State.BLOCKED && this.t.hasCompletedAllPrerequisites(str)) {
                    Logger.get().info(A, String.format("Setting status to enqueued for %s", str), new Throwable[0]);
                    this.s.setState(WorkInfo.State.ENQUEUED, str);
                    this.s.setPeriodStartTime(str, currentTimeMillis);
                }
            }
            this.r.setTransactionSuccessful();
        } finally {
            this.r.endTransaction();
            g(false);
        }
    }

    public final boolean l() {
        if (this.z) {
            Logger.get().debug(A, String.format("Work interrupted for %s", this.w), new Throwable[0]);
            WorkInfo.State state = this.s.getState(this.i);
            if (state == null) {
                g(false);
            } else {
                g(!state.isFinished());
            }
            return true;
        }
        return false;
    }

    public final boolean m() {
        this.r.beginTransaction();
        try {
            boolean z = true;
            if (this.s.getState(this.i) == WorkInfo.State.ENQUEUED) {
                this.s.setState(WorkInfo.State.RUNNING, this.i);
                this.s.incrementWorkSpecRunAttemptCount(this.i);
            } else {
                z = false;
            }
            this.r.setTransactionSuccessful();
            return z;
        } finally {
            this.r.endTransaction();
        }
    }

    @Override // java.lang.Runnable
    @WorkerThread
    public void run() {
        List<String> tagsForWorkSpecId = this.u.getTagsForWorkSpecId(this.i);
        this.v = tagsForWorkSpecId;
        this.w = a(tagsForWorkSpecId);
        i();
    }
}
