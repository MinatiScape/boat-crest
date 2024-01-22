package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes8.dex */
public final class e0 extends zzj {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8883a;
    public final /* synthetic */ com.google.android.gms.location.zzr b;

    public e0(TaskCompletionSource taskCompletionSource, com.google.android.gms.location.zzr zzrVar) {
        this.f8883a = taskCompletionSource;
        this.b = zzrVar;
    }

    @Override // com.google.android.gms.internal.location.zzk
    public final void zzd(zzg zzgVar) {
        TaskUtil.setResultOrApiException(zzgVar.getStatus(), this.f8883a);
    }

    @Override // com.google.android.gms.internal.location.zzk
    public final void zze() throws RemoteException {
        this.b.zzf();
    }
}
