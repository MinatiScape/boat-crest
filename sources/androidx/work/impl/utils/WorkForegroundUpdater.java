package androidx.work.impl.utils;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.ForegroundInfo;
import androidx.work.ForegroundUpdater;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.foreground.ForegroundProcessor;
import androidx.work.impl.foreground.SystemForegroundDispatcher;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.futures.SettableFuture;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.UUID;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class WorkForegroundUpdater implements ForegroundUpdater {

    /* renamed from: a  reason: collision with root package name */
    public final TaskExecutor f1836a;
    public final ForegroundProcessor b;
    public final WorkSpecDao c;

    /* loaded from: classes.dex */
    public class a implements Runnable {
        public final /* synthetic */ SettableFuture h;
        public final /* synthetic */ UUID i;
        public final /* synthetic */ ForegroundInfo j;
        public final /* synthetic */ Context k;

        public a(SettableFuture settableFuture, UUID uuid, ForegroundInfo foregroundInfo, Context context) {
            this.h = settableFuture;
            this.i = uuid;
            this.j = foregroundInfo;
            this.k = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!this.h.isCancelled()) {
                    String uuid = this.i.toString();
                    WorkInfo.State state = WorkForegroundUpdater.this.c.getState(uuid);
                    if (state != null && !state.isFinished()) {
                        WorkForegroundUpdater.this.b.startForeground(uuid, this.j);
                        this.k.startService(SystemForegroundDispatcher.createNotifyIntent(this.k, uuid, this.j));
                    } else {
                        throw new IllegalStateException("Calls to setForegroundAsync() must complete before a ListenableWorker signals completion of work by returning an instance of Result.");
                    }
                }
                this.h.set(null);
            } catch (Throwable th) {
                this.h.setException(th);
            }
        }
    }

    static {
        Logger.tagWithPrefix("WMFgUpdater");
    }

    public WorkForegroundUpdater(@NonNull WorkDatabase workDatabase, @NonNull ForegroundProcessor foregroundProcessor, @NonNull TaskExecutor taskExecutor) {
        this.b = foregroundProcessor;
        this.f1836a = taskExecutor;
        this.c = workDatabase.workSpecDao();
    }

    @Override // androidx.work.ForegroundUpdater
    @NonNull
    public ListenableFuture<Void> setForegroundAsync(@NonNull Context context, @NonNull UUID uuid, @NonNull ForegroundInfo foregroundInfo) {
        SettableFuture create = SettableFuture.create();
        this.f1836a.executeOnBackgroundThread(new a(create, uuid, foregroundInfo, context));
        return create;
    }
}
