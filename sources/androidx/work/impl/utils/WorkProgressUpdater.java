package androidx.work.impl.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Data;
import androidx.work.Logger;
import androidx.work.ProgressUpdater;
import androidx.work.WorkInfo;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkProgress;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class WorkProgressUpdater implements ProgressUpdater {
    public static final String c = Logger.tagWithPrefix("WorkProgressUpdater");

    /* renamed from: a  reason: collision with root package name */
    public final WorkDatabase f1837a;
    public final TaskExecutor b;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ UUID h;
        public final /* synthetic */ Data i;
        public final /* synthetic */ SettableFuture j;

        public a(UUID uuid, Data data, SettableFuture settableFuture) {
            this.h = uuid;
            this.i = data;
            this.j = settableFuture;
        }

        @Override // java.lang.Runnable
        public void run() {
            WorkSpec workSpec;
            String uuid = this.h.toString();
            Logger logger = Logger.get();
            String str = WorkProgressUpdater.c;
            logger.debug(str, String.format("Updating progress for %s (%s)", this.h, this.i), new Throwable[0]);
            WorkProgressUpdater.this.f1837a.beginTransaction();
            try {
                workSpec = WorkProgressUpdater.this.f1837a.workSpecDao().getWorkSpec(uuid);
            } finally {
                try {
                    return;
                } finally {
                }
            }
            if (workSpec != null) {
                if (workSpec.state == WorkInfo.State.RUNNING) {
                    WorkProgressUpdater.this.f1837a.workProgressDao().insert(new WorkProgress(uuid, this.i));
                } else {
                    Logger.get().warning(str, String.format("Ignoring setProgressAsync(...). WorkSpec (%s) is not in a RUNNING state.", uuid), new Throwable[0]);
                }
                this.j.set(null);
                WorkProgressUpdater.this.f1837a.setTransactionSuccessful();
                return;
            }
            throw new IllegalStateException("Calls to setProgressAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
        }
    }

    public WorkProgressUpdater(@NonNull WorkDatabase workDatabase, @NonNull TaskExecutor taskExecutor) {
        this.f1837a = workDatabase;
        this.b = taskExecutor;
    }

    @Override // androidx.work.ProgressUpdater
    @NonNull
    public ListenableFuture<Void> updateProgress(@NonNull Context context, @NonNull UUID uuid, @NonNull Data data) {
        SettableFuture create = SettableFuture.create();
        this.b.executeOnBackgroundThread(new a(uuid, data, create));
        return create;
    }
}
