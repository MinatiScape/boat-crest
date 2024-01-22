package androidx.work.impl.utils;

import androidx.annotation.RestrictTo;
import androidx.work.WorkerParameters;
import androidx.work.impl.WorkManagerImpl;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class StartWorkRunnable implements Runnable {
    public WorkManagerImpl h;
    public String i;
    public WorkerParameters.RuntimeExtras j;

    public StartWorkRunnable(WorkManagerImpl workManagerImpl, String str, WorkerParameters.RuntimeExtras runtimeExtras) {
        this.h = workManagerImpl;
        this.i = str;
        this.j = runtimeExtras;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.h.getProcessor().startWork(this.i, this.j);
    }
}
