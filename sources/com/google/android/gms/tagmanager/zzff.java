package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
@ShowFirstParty
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzff extends zzey {
    public static final Object zza = new Object();
    public static zzff zzb;
    public Context zzc;
    public zzcd zzd;
    public zzfb zzh;
    public zzdk zzi;
    public volatile zzcc zzk;
    public boolean zze = true;
    public boolean zzf = false;
    public boolean zzg = true;
    public final zzez zzl = new zzez(this);
    public boolean zzj = false;

    public static zzff zzg() {
        if (zzb == null) {
            zzb = new zzff();
        }
        return zzb;
    }

    @Override // com.google.android.gms.tagmanager.zzey
    public final synchronized void zza() {
        if (!this.zzf) {
            zzdh.zzb.zzd("Dispatch call queued. Dispatch will run once initialization is complete.");
            this.zze = true;
            return;
        }
        this.zzk.zze(new zzfa(this));
    }

    @Override // com.google.android.gms.tagmanager.zzey
    public final synchronized void zzb() {
        if (zzm()) {
            return;
        }
        this.zzh.zzb();
    }

    @Override // com.google.android.gms.tagmanager.zzey
    public final synchronized void zzc(boolean z) {
        zzi(this.zzj, z);
    }

    public final synchronized zzcd zzf() {
        if (this.zzd == null) {
            Context context = this.zzc;
            if (context != null) {
                this.zzd = new zzdw(this.zzl, context, null);
            } else {
                throw new IllegalStateException("Cant get a store unless we have a context");
            }
        }
        if (this.zzh == null) {
            zzfe zzfeVar = new zzfe(this, null);
            this.zzh = zzfeVar;
            zzfeVar.zzc(1800000L);
        }
        this.zzf = true;
        if (this.zze) {
            zza();
            this.zze = false;
        }
        if (this.zzi == null) {
            zzdk zzdkVar = new zzdk(this);
            this.zzi = zzdkVar;
            Context context2 = this.zzc;
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            context2.registerReceiver(zzdkVar, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("com.google.analytics.RADIO_POWERED");
            intentFilter2.addCategory(context2.getPackageName());
            context2.registerReceiver(zzdkVar, intentFilter2);
        }
        return this.zzd;
    }

    @VisibleForTesting
    public final synchronized void zzi(boolean z, boolean z2) {
        boolean zzm = zzm();
        this.zzj = z;
        this.zzg = z2;
        if (zzm() != zzm) {
            if (zzm()) {
                this.zzh.zza();
                zzdh.zzb.zzd("PowerSaveMode initiated.");
                return;
            }
            this.zzh.zzc(1800000L);
            zzdh.zzb.zzd("PowerSaveMode terminated.");
        }
    }

    public final synchronized void zzl(Context context, zzcc zzccVar) {
        if (this.zzc != null) {
            return;
        }
        this.zzc = context.getApplicationContext();
        if (this.zzk == null) {
            this.zzk = zzccVar;
        }
    }

    public final boolean zzm() {
        return this.zzj || !this.zzg;
    }
}
