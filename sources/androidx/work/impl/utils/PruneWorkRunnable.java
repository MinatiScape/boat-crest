package androidx.work.impl.utils;

import androidx.annotation.RestrictTo;
import androidx.work.Operation;
import androidx.work.impl.OperationImpl;
import androidx.work.impl.WorkManagerImpl;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class PruneWorkRunnable implements Runnable {
    public final WorkManagerImpl h;
    public final OperationImpl i = new OperationImpl();

    public PruneWorkRunnable(WorkManagerImpl workManagerImpl) {
        this.h = workManagerImpl;
    }

    public Operation getOperation() {
        return this.i;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            this.h.getWorkDatabase().workSpecDao().pruneFinishedWorkWithZeroDependentsIgnoringKeepForAtLeast();
            this.i.setState(Operation.SUCCESS);
        } catch (Throwable th) {
            this.i.setState(new Operation.State.FAILURE(th));
        }
    }
}
