package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzbl implements Runnable {
    public final /* synthetic */ int zza;
    public final /* synthetic */ zzbq zzb;

    public zzbl(zzbq zzbqVar, int i) {
        this.zzb = zzbqVar;
        this.zza = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzck zzckVar;
        zzckVar = this.zzb.zza;
        zzckVar.zzZ(this.zza * 1000);
    }
}
