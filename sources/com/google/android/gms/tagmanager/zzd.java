package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Process;
import androidx.work.PeriodicWorkRequest;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzd {
    public static final Object zza = new Object();
    public static zzd zzb;
    public volatile AdvertisingIdClient.Info zzf;
    public volatile long zzg;
    public volatile long zzh;
    public final Context zzi;
    public final Clock zzj;
    public final Thread zzk;
    public volatile long zzc = PeriodicWorkRequest.MIN_PERIODIC_INTERVAL_MILLIS;
    public volatile long zzd = 30000;
    public volatile boolean zze = false;
    public final Object zzl = new Object();
    public final zzc zzm = new zza(this);

    @VisibleForTesting
    public zzd(Context context, zzc zzcVar, Clock clock) {
        this.zzj = clock;
        if (context != null) {
            this.zzi = context.getApplicationContext();
        } else {
            this.zzi = null;
        }
        this.zzg = clock.currentTimeMillis();
        this.zzk = new Thread(new zzb(this));
    }

    public static zzd zzb(Context context) {
        if (zzb == null) {
            synchronized (zza) {
                if (zzb == null) {
                    zzd zzdVar = new zzd(context, null, DefaultClock.getInstance());
                    zzb = zzdVar;
                    zzdVar.zzk.start();
                }
            }
        }
        return zzb;
    }

    public static /* bridge */ /* synthetic */ void zzd(zzd zzdVar) {
        Process.setThreadPriority(10);
        while (!zzdVar.zze) {
            AdvertisingIdClient.Info zza2 = zzdVar.zzm.zza();
            if (zza2 != null) {
                zzdVar.zzf = zza2;
                zzdVar.zzh = zzdVar.zzj.currentTimeMillis();
                zzdh.zzb.zzb("Obtained fresh AdvertisingId info from GmsCore.");
            }
            synchronized (zzdVar) {
                zzdVar.notifyAll();
            }
            try {
                synchronized (zzdVar.zzl) {
                    zzdVar.zzl.wait(zzdVar.zzc);
                }
            } catch (InterruptedException unused) {
                zzdh.zzb.zzb("sleep interrupted in AdvertiserDataPoller thread; continuing");
            }
        }
    }

    public final String zzc() {
        if (this.zzf == null) {
            zzi();
        } else {
            zzh();
        }
        zzg();
        if (this.zzf == null) {
            return null;
        }
        return this.zzf.getId();
    }

    @VisibleForTesting
    public final void zze() {
        this.zze = true;
        this.zzk.interrupt();
    }

    public final boolean zzf() {
        if (this.zzf == null) {
            zzi();
        } else {
            zzh();
        }
        zzg();
        return this.zzf == null || this.zzf.isLimitAdTrackingEnabled();
    }

    public final void zzg() {
        if (this.zzj.currentTimeMillis() - this.zzh > 3600000) {
            this.zzf = null;
        }
    }

    public final void zzh() {
        if (this.zzj.currentTimeMillis() - this.zzg > this.zzd) {
            synchronized (this.zzl) {
                this.zzl.notify();
            }
            this.zzg = this.zzj.currentTimeMillis();
        }
    }

    public final void zzi() {
        synchronized (this) {
            try {
                if (!this.zze) {
                    zzh();
                    wait(500L);
                }
            } catch (InterruptedException unused) {
            }
        }
    }
}
