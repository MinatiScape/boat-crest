package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzbk implements Runnable {
    public final /* synthetic */ String zza;
    public final /* synthetic */ Runnable zzb;
    public final /* synthetic */ zzbq zzc;

    public zzbk(zzbq zzbqVar, String str, Runnable runnable) {
        this.zzc = zzbqVar;
        this.zza = str;
        this.zzb = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzck zzckVar;
        zzckVar = this.zzc.zza;
        zzckVar.zzn(this.zza);
        this.zzb.run();
    }
}
