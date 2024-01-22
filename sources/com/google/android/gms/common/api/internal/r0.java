package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public abstract class r0 extends zac {
    public final TaskCompletionSource zaa;

    public r0(int i, TaskCompletionSource taskCompletionSource) {
        super(i);
        this.zaa = taskCompletionSource;
    }

    public abstract void zac(zabq zabqVar) throws RemoteException;

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zad(@NonNull Status status) {
        this.zaa.trySetException(new ApiException(status));
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zae(@NonNull Exception exc) {
        this.zaa.trySetException(exc);
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public final void zaf(zabq zabqVar) throws DeadObjectException {
        try {
            zac(zabqVar);
        } catch (DeadObjectException e) {
            zad(zai.a(e));
            throw e;
        } catch (RemoteException e2) {
            zad(zai.a(e2));
        } catch (RuntimeException e3) {
            this.zaa.trySetException(e3);
        }
    }

    @Override // com.google.android.gms.common.api.internal.zai
    public abstract void zag(@NonNull zaad zaadVar, boolean z);
}
