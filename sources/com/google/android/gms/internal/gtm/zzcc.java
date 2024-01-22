package com.google.android.gms.internal.gtm;

import android.content.ComponentName;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Collections;
@VisibleForTesting
/* loaded from: classes8.dex */
public final class zzcc extends zzbs {
    public final zzcb zza;
    public final zzcw zzb;
    public final zzfo zzc;
    public zzey zzd;

    public zzcc(zzbv zzbvVar) {
        super(zzbvVar);
        this.zzc = new zzfo(zzbvVar.zzr());
        this.zza = new zzcb(this);
        this.zzb = new zzby(this, zzbvVar);
    }

    public static /* bridge */ /* synthetic */ void zzb(zzcc zzccVar, ComponentName componentName) {
        com.google.android.gms.analytics.zzr.zzh();
        if (zzccVar.zzd != null) {
            zzccVar.zzd = null;
            zzccVar.zzP("Disconnected from device AnalyticsService", componentName);
            zzccVar.zzs().zzk();
        }
    }

    public static /* bridge */ /* synthetic */ void zzi(zzcc zzccVar, zzey zzeyVar) {
        com.google.android.gms.analytics.zzr.zzh();
        zzccVar.zzd = zzeyVar;
        zzccVar.zzj();
        zzccVar.zzs().zzj();
    }

    public final void zzc() {
        com.google.android.gms.analytics.zzr.zzh();
        zzW();
        try {
            ConnectionTracker.getInstance().unbindService(zzo(), this.zza);
        } catch (IllegalArgumentException | IllegalStateException unused) {
        }
        if (this.zzd != null) {
            this.zzd = null;
            zzs().zzk();
        }
    }

    @Override // com.google.android.gms.internal.gtm.zzbs
    public final void zzd() {
    }

    public final boolean zze() {
        com.google.android.gms.analytics.zzr.zzh();
        zzW();
        zzey zzeyVar = this.zzd;
        if (zzeyVar == null) {
            return false;
        }
        try {
            zzeyVar.zze();
            zzj();
            return true;
        } catch (RemoteException unused) {
            zzO("Failed to clear hits from AnalyticsService");
            return false;
        }
    }

    public final boolean zzf() {
        com.google.android.gms.analytics.zzr.zzh();
        zzW();
        if (this.zzd != null) {
            return true;
        }
        zzey zza = this.zza.zza();
        if (zza != null) {
            this.zzd = zza;
            zzj();
            return true;
        }
        return false;
    }

    public final boolean zzg() {
        com.google.android.gms.analytics.zzr.zzh();
        zzW();
        return this.zzd != null;
    }

    public final boolean zzh(zzex zzexVar) {
        String zzk;
        Preconditions.checkNotNull(zzexVar);
        com.google.android.gms.analytics.zzr.zzh();
        zzW();
        zzey zzeyVar = this.zzd;
        if (zzeyVar == null) {
            return false;
        }
        if (zzexVar.zzh()) {
            zzw();
            zzk = zzct.zzi();
        } else {
            zzw();
            zzk = zzct.zzk();
        }
        try {
            zzeyVar.zzf(zzexVar.zzg(), zzexVar.zzd(), zzk, Collections.emptyList());
            zzj();
            return true;
        } catch (RemoteException unused) {
            zzO("Failed to send hits to AnalyticsService");
            return false;
        }
    }

    public final void zzj() {
        this.zzc.zzb();
        zzcw zzcwVar = this.zzb;
        zzw();
        zzcwVar.zzg(zzeu.zzK.zzb().longValue());
    }
}
