package com.google.android.gms.fido.fido2;

import android.os.RemoteException;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public final class e extends com.google.android.gms.internal.fido.zzd {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8408a;

    public e(Fido2ApiClient fido2ApiClient, TaskCompletionSource taskCompletionSource) {
        this.f8408a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.fido.zze
    public final void zzb(boolean z) throws RemoteException {
        this.f8408a.setResult(Boolean.valueOf(z));
    }

    @Override // com.google.android.gms.internal.fido.zze
    public final void zzc(Status status) throws RemoteException {
        this.f8408a.trySetException(new ApiException(status));
    }
}
