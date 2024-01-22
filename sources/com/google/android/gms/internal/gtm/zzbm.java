package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzbm implements Runnable {
    public final /* synthetic */ zzex zza;
    public final /* synthetic */ zzbq zzb;

    public zzbm(zzbq zzbqVar, zzex zzexVar) {
        this.zzb = zzbqVar;
        this.zza = zzexVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzck zzckVar;
        zzckVar = this.zzb.zza;
        zzckVar.zzj(this.zza);
    }
}
