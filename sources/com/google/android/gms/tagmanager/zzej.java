package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.gtm.zzfz;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
/* loaded from: classes10.dex */
public final class zzej implements zzaj {
    public final String zza;
    public final Context zzb;
    public ScheduledFuture<?> zzd;
    public boolean zze;
    public final zzap zzf;
    public String zzg;
    public zzdg<com.google.android.gms.internal.gtm.zzai> zzh;
    public final ScheduledExecutorService zzc = zzfz.zza().zzb(1, 2);
    public final zzei zzi = new zzei(this);

    @VisibleForTesting
    public zzej(Context context, String str, zzap zzapVar, zzeh zzehVar, zzei zzeiVar, byte[] bArr) {
        this.zzf = zzapVar;
        this.zzb = context;
        this.zza = str;
    }

    @Override // com.google.android.gms.common.api.Releasable
    public final synchronized void release() {
        zzd();
        ScheduledFuture<?> scheduledFuture = this.zzd;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.zzc.shutdown();
        this.zze = true;
    }

    @Override // com.google.android.gms.tagmanager.zzaj
    public final synchronized void zza(long j, String str) {
        String str2 = this.zza;
        StringBuilder sb = new StringBuilder(String.valueOf(str2).length() + 55);
        sb.append("loadAfterDelay: containerId=");
        sb.append(str2);
        sb.append(" delay=");
        sb.append(j);
        zzdh.zzb.zzd(sb.toString());
        zzd();
        if (this.zzh != null) {
            ScheduledFuture<?> scheduledFuture = this.zzd;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            ScheduledExecutorService scheduledExecutorService = this.zzc;
            zzei zzeiVar = this.zzi;
            zzap zzapVar = this.zzf;
            zzej zzejVar = zzeiVar.zza;
            zzeg zzegVar = new zzeg(zzejVar.zzb, zzejVar.zza, zzapVar);
            zzegVar.zzb(this.zzh);
            zzegVar.zza(this.zzg);
            zzegVar.zzc(str);
            this.zzd = scheduledExecutorService.schedule(zzegVar, j, TimeUnit.MILLISECONDS);
        } else {
            throw new IllegalStateException("callback must be set before loadAfterDelay() is called.");
        }
    }

    @Override // com.google.android.gms.tagmanager.zzaj
    public final synchronized void zzb(String str) {
        zzd();
        this.zzg = str;
    }

    @Override // com.google.android.gms.tagmanager.zzaj
    public final synchronized void zzc(zzdg<com.google.android.gms.internal.gtm.zzai> zzdgVar) {
        zzd();
        this.zzh = zzdgVar;
    }

    public final synchronized void zzd() {
        if (this.zze) {
            throw new IllegalStateException("called method after closed");
        }
    }
}
