package com.google.android.gms.internal.gtm;

import android.os.Handler;
/* loaded from: classes8.dex */
public final class zzfl implements zzcz {
    public final /* synthetic */ Runnable zza;
    public final /* synthetic */ zzfn zzb;

    public zzfl(zzfn zzfnVar, Runnable runnable) {
        this.zzb = zzfnVar;
        this.zza = runnable;
    }

    @Override // com.google.android.gms.internal.gtm.zzcz
    public final void zza(Throwable th) {
        Handler handler;
        handler = this.zzb.zzb;
        handler.post(this.zza);
    }
}
