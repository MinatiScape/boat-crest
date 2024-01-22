package com.google.android.gms.internal.gtm;

import android.os.Looper;
/* loaded from: classes8.dex */
public final class zzcv implements Runnable {
    public final /* synthetic */ zzcw zza;

    public zzcv(zzcw zzcwVar) {
        this.zza = zzcwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbv zzbvVar;
        if (Looper.myLooper() == Looper.getMainLooper()) {
            zzbvVar = this.zza.zzb;
            zzbvVar.zzd().zzi(this);
            return;
        }
        boolean zzh = this.zza.zzh();
        this.zza.zzd = 0L;
        if (zzh) {
            this.zza.zza();
        }
    }
}
