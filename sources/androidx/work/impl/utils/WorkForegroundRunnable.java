package androidx.work.impl.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.core.os.BuildCompat;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class WorkForegroundRunnable implements Runnable {
    public static final String n = Logger.tagWithPrefix("WorkForegroundRunnable");
    public final SettableFuture<Void> h = SettableFuture.create();
    public final Context i;
    public final WorkSpec j;
    public final ListenableWorker k;
    public final ForegroundUpdater l;
    public final TaskExecutor m;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ SettableFuture h;

        public a(SettableFuture settableFuture) {
            this.h = settableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.h.setFuture(WorkForegroundRunnable.this.k.getForegroundInfoAsync());
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ SettableFuture h;

        public b(SettableFuture settableFuture) {
            this.h = settableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ForegroundInfo foregroundInfo = (ForegroundInfo) this.h.get();
                if (foregroundInfo != null) {
                    Logger.get().debug(WorkForegroundRunnable.n, String.format("Updating notification for %s", WorkForegroundRunnable.this.j.workerClassName), new Throwable[0]);
                    WorkForegroundRunnable.this.k.setRunInForeground(true);
                    WorkForegroundRunnable workForegroundRunnable = WorkForegroundRunnable.this;
                    workForegroundRunnable.h.setFuture(workForegroundRunnable.l.setForegroundAsync(workForegroundRunnable.i, workForegroundRunnable.k.getId(), foregroundInfo));
                    return;
                }
                throw new IllegalStateException(String.format("Worker was marked important (%s) but did not provide ForegroundInfo", WorkForegroundRunnable.this.j.workerClassName));
            } catch (Throwable th) {
                WorkForegroundRunnable.this.h.setException(th);
            }
        }
    }

    @SuppressLint({"LambdaLast"})
    public WorkForegroundRunnable(@NonNull Context context, @NonNull WorkSpec workSpec, @NonNull ListenableWorker listenableWorker, @NonNull ForegroundUpdater foregroundUpdater, @NonNull TaskExecutor taskExecutor) {
        this.i = context;
        this.j = workSpec;
        this.k = listenableWorker;
        this.l = foregroundUpdater;
        this.m = taskExecutor;
    }

    @NonNull
    public ListenableFuture<Void> getFuture() {
        return this.h;
    }

    @Override // java.lang.Runnable
    @SuppressLint({"UnsafeExperimentalUsageError"})
    public void run() {
        if (this.j.expedited && !BuildCompat.isAtLeastS()) {
            SettableFuture create = SettableFuture.create();
            this.m.getMainThreadExecutor().execute(new a(create));
            create.addListener(new b(create), this.m.getMainThreadExecutor());
            return;
        }
        this.h.set(null);
    }
}
