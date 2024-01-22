package androidx.work.impl.workers;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.work.ListenableWorker;
import androidx.work.Logger;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.WorkConstraintsCallback;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Collections;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class ConstraintTrackingWorker extends ListenableWorker implements WorkConstraintsCallback {
    public static final String ARGUMENT_CLASS_NAME = "androidx.work.impl.workers.ConstraintTrackingWorker.ARGUMENT_CLASS_NAME";
    public static final String r = Logger.tagWithPrefix("ConstraintTrkngWrkr");
    public WorkerParameters m;
    public final Object n;
    public volatile boolean o;
    public SettableFuture<ListenableWorker.Result> p;
    @Nullable
    public ListenableWorker q;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ConstraintTrackingWorker.this.c();
        }
    }

    /* loaded from: classes.dex */
    public class b implements Runnable {
        public final /* synthetic */ ListenableFuture h;

        public b(ListenableFuture listenableFuture) {
            this.h = listenableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (ConstraintTrackingWorker.this.n) {
                if (ConstraintTrackingWorker.this.o) {
                    ConstraintTrackingWorker.this.b();
                } else {
                    ConstraintTrackingWorker.this.p.setFuture(this.h);
                }
            }
        }
    }

    public ConstraintTrackingWorker(@NonNull Context context, @NonNull WorkerParameters workerParameters) {
        super(context, workerParameters);
        this.m = workerParameters;
        this.n = new Object();
        this.o = false;
        this.p = SettableFuture.create();
    }

    public void a() {
        this.p.set(ListenableWorker.Result.failure());
    }

    public void b() {
        this.p.set(ListenableWorker.Result.retry());
    }

    public void c() {
        String string = getInputData().getString(ARGUMENT_CLASS_NAME);
        if (TextUtils.isEmpty(string)) {
            Logger.get().error(r, "No worker to delegate to.", new Throwable[0]);
            a();
            return;
        }
        ListenableWorker createWorkerWithDefaultFallback = getWorkerFactory().createWorkerWithDefaultFallback(getApplicationContext(), string, this.m);
        this.q = createWorkerWithDefaultFallback;
        if (createWorkerWithDefaultFallback == null) {
            Logger.get().debug(r, "No worker to delegate to.", new Throwable[0]);
            a();
            return;
        }
        WorkSpec workSpec = getWorkDatabase().workSpecDao().getWorkSpec(getId().toString());
        if (workSpec == null) {
            a();
            return;
        }
        WorkConstraintsTracker workConstraintsTracker = new WorkConstraintsTracker(getApplicationContext(), getTaskExecutor(), this);
        workConstraintsTracker.replace(Collections.singletonList(workSpec));
        if (workConstraintsTracker.areAllConstraintsMet(getId().toString())) {
            Logger.get().debug(r, String.format("Constraints met for delegate %s", string), new Throwable[0]);
            try {
                ListenableFuture<ListenableWorker.Result> startWork = this.q.startWork();
                startWork.addListener(new b(startWork), getBackgroundExecutor());
                return;
            } catch (Throwable th) {
                Logger logger = Logger.get();
                String str = r;
                logger.debug(str, String.format("Delegated worker %s threw exception in startWork.", string), th);
                synchronized (this.n) {
                    if (this.o) {
                        Logger.get().debug(str, "Constraints were unmet, Retrying.", new Throwable[0]);
                        b();
                    } else {
                        a();
                    }
                    return;
                }
            }
        }
        Logger.get().debug(r, String.format("Constraints not met for delegate %s. Requesting retry.", string), new Throwable[0]);
        b();
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public ListenableWorker getDelegate() {
        return this.q;
    }

    @Override // androidx.work.ListenableWorker
    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public TaskExecutor getTaskExecutor() {
        return WorkManagerImpl.getInstance(getApplicationContext()).getWorkTaskExecutor();
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    @VisibleForTesting
    public WorkDatabase getWorkDatabase() {
        return WorkManagerImpl.getInstance(getApplicationContext()).getWorkDatabase();
    }

    @Override // androidx.work.ListenableWorker
    public boolean isRunInForeground() {
        ListenableWorker listenableWorker = this.q;
        return listenableWorker != null && listenableWorker.isRunInForeground();
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsMet(@NonNull List<String> list) {
    }

    @Override // androidx.work.impl.constraints.WorkConstraintsCallback
    public void onAllConstraintsNotMet(@NonNull List<String> list) {
        Logger.get().debug(r, String.format("Constraints changed for %s", list), new Throwable[0]);
        synchronized (this.n) {
            this.o = true;
        }
    }

    @Override // androidx.work.ListenableWorker
    public void onStopped() {
        super.onStopped();
        ListenableWorker listenableWorker = this.q;
        if (listenableWorker == null || listenableWorker.isStopped()) {
            return;
        }
        this.q.stop();
    }

    @Override // androidx.work.ListenableWorker
    @NonNull
    public ListenableFuture<ListenableWorker.Result> startWork() {
        getBackgroundExecutor().execute(new a());
        return this.p;
    }
}
