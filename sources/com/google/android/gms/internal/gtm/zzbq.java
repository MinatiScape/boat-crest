package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
/* loaded from: classes8.dex */
public final class zzbq extends zzbs {
    public final zzck zza;

    public zzbq(zzbv zzbvVar, zzbw zzbwVar) {
        super(zzbvVar);
        Preconditions.checkNotNull(zzbwVar);
        this.zza = new zzck(zzbvVar, zzbwVar);
    }

    public final long zza(zzbx zzbxVar) {
        zzW();
        Preconditions.checkNotNull(zzbxVar);
        com.google.android.gms.analytics.zzr.zzh();
        long zzb = this.zza.zzb(zzbxVar, true);
        if (zzb == 0) {
            this.zza.zzk(zzbxVar);
        }
        return zzb;
    }

    public final void zzc() {
        zzW();
        Context zzo = zzo();
        if (zzfi.zza(zzo) && zzfn.zzh(zzo)) {
            Intent intent = new Intent("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            intent.setComponent(new ComponentName(zzo, "com.google.android.gms.analytics.AnalyticsService"));
            zzo.startService(intent);
            return;
        }
        zze(null);
    }

    @Override // com.google.android.gms.internal.gtm.zzbs
    public final void zzd() {
        this.zza.zzX();
    }

    public final void zze(zzcz zzczVar) {
        zzW();
        zzq().zzi(new zzbo(this, zzczVar));
    }

    public final void zzf(String str, Runnable runnable) {
        Preconditions.checkNotEmpty(str, "campaign param can't be empty");
        zzq().zzi(new zzbk(this, str, runnable));
    }

    public final void zzg() {
        zzW();
        zzE();
        zzq().zzi(new zzbn(this));
    }

    public final void zzh(zzex zzexVar) {
        Preconditions.checkNotNull(zzexVar);
        zzW();
        zzG("Hit delivery requested", zzexVar);
        zzq().zzi(new zzbm(this, zzexVar));
    }

    public final void zzi() {
        com.google.android.gms.analytics.zzr.zzh();
        this.zza.zzl();
    }

    public final void zzj() {
        com.google.android.gms.analytics.zzr.zzh();
        this.zza.zzm();
    }

    public final void zzk() {
        zzW();
        com.google.android.gms.analytics.zzr.zzh();
        zzck zzckVar = this.zza;
        com.google.android.gms.analytics.zzr.zzh();
        zzckVar.zzW();
        zzckVar.zzO("Service disconnected");
    }

    public final void zzl(int i) {
        zzW();
        zzG("setLocalDispatchPeriod (sec)", Integer.valueOf(i));
        zzq().zzi(new zzbl(this, i));
    }

    public final void zzm() {
        this.zza.zzaa();
    }

    public final boolean zzn() {
        zzW();
        try {
            zzq().zzg(new zzbp(this)).get(4L, TimeUnit.SECONDS);
            return true;
        } catch (InterruptedException e) {
            zzS("syncDispatchLocalHits interrupted", e);
            return false;
        } catch (ExecutionException e2) {
            zzK("syncDispatchLocalHits failed", e2);
            return false;
        } catch (TimeoutException e3) {
            zzS("syncDispatchLocalHits timed out", e3);
            return false;
        }
    }
}
