package com.google.android.gms.tagmanager;

import android.content.Context;
/* loaded from: classes10.dex */
public final class zzcb implements Runnable {
    public final /* synthetic */ long zza;
    public final /* synthetic */ String zzb;
    public final /* synthetic */ zzcc zzc;
    public final /* synthetic */ zzcc zzd;

    public zzcb(zzcc zzccVar, zzcc zzccVar2, long j, String str, byte[] bArr) {
        this.zzc = zzccVar;
        this.zzd = zzccVar2;
        this.zza = j;
        this.zzb = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzcd zzcdVar;
        zzcd zzcdVar2;
        Context context;
        zzcdVar = this.zzc.zze;
        if (zzcdVar == null) {
            zzff zzg = zzff.zzg();
            context = this.zzc.zzf;
            zzg.zzl(context, this.zzd);
            this.zzc.zze = zzg.zzf();
        }
        zzcdVar2 = this.zzc.zze;
        zzcdVar2.zzb(this.zza, this.zzb);
    }
}
