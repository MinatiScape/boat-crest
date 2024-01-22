package androidx.work.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.Logger;
import androidx.work.WorkInfo;
import androidx.work.impl.Processor;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkSpecDao;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class StopWorkRunnable implements Runnable {
    public static final String k = Logger.tagWithPrefix("StopWorkRunnable");
    public final WorkManagerImpl h;
    public final String i;
    public final boolean j;

    public StopWorkRunnable(@NonNull WorkManagerImpl workManagerImpl, @NonNull String str, boolean z) {
        this.h = workManagerImpl;
        this.i = str;
        this.j = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean stopWork;
        WorkDatabase workDatabase = this.h.getWorkDatabase();
        Processor processor = this.h.getProcessor();
        WorkSpecDao workSpecDao = workDatabase.workSpecDao();
        workDatabase.beginTransaction();
        try {
            boolean isEnqueuedInForeground = processor.isEnqueuedInForeground(this.i);
            if (this.j) {
                stopWork = this.h.getProcessor().stopForegroundWork(this.i);
            } else {
                if (!isEnqueuedInForeground && workSpecDao.getState(this.i) == WorkInfo.State.RUNNING) {
                    workSpecDao.setState(WorkInfo.State.ENQUEUED, this.i);
                }
                stopWork = this.h.getProcessor().stopWork(this.i);
            }
            Logger.get().debug(k, String.format("StopWorkRunnable for %s; Processor.stopWork = %s", this.i, Boolean.valueOf(stopWork)), new Throwable[0]);
            workDatabase.setTransactionSuccessful();
        } finally {
            workDatabase.endTransaction();
        }
    }
}
