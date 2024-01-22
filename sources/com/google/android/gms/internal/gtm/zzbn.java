package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzbn implements Runnable {
    public final /* synthetic */ zzbq zza;

    public zzbn(zzbq zzbqVar) {
        this.zza = zzbqVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzck zzckVar;
        zzckVar = this.zza.zza;
        zzckVar.zzh();
    }
}
