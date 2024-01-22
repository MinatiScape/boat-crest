package com.google.android.gms.internal.gtm;

import android.content.SharedPreferences;
import android.text.TextUtils;
/* loaded from: classes8.dex */
public final class zzfh extends zzbs {
    public SharedPreferences zza;
    public long zzb;
    public long zzc;
    public final zzfg zzd;

    public zzfh(zzbv zzbvVar) {
        super(zzbvVar);
        this.zzc = -1L;
        zzw();
        this.zzd = new zzfg(this, "monitoring", zzeu.zzP.zzb().longValue(), null);
    }

    public final long zza() {
        com.google.android.gms.analytics.zzr.zzh();
        zzW();
        long j = this.zzb;
        if (j == 0) {
            long j2 = this.zza.getLong("first_run", 0L);
            if (j2 != 0) {
                this.zzb = j2;
                return j2;
            }
            long currentTimeMillis = zzC().currentTimeMillis();
            SharedPreferences.Editor edit = this.zza.edit();
            edit.putLong("first_run", currentTimeMillis);
            if (!edit.commit()) {
                zzR("Failed to commit first run time");
            }
            this.zzb = currentTimeMillis;
            return currentTimeMillis;
        }
        return j;
    }

    public final long zzb() {
        com.google.android.gms.analytics.zzr.zzh();
        zzW();
        long j = this.zzc;
        if (j == -1) {
            long j2 = this.zza.getLong("last_dispatch", 0L);
            this.zzc = j2;
            return j2;
        }
        return j;
    }

    @Override // com.google.android.gms.internal.gtm.zzbs
    public final void zzd() {
        this.zza = zzo().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }

    public final zzfg zze() {
        return this.zzd;
    }

    public final zzfo zzf() {
        return new zzfo(zzC(), zza());
    }

    public final String zzg() {
        com.google.android.gms.analytics.zzr.zzh();
        zzW();
        String string = this.zza.getString("installation_campaign", null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    public final void zzh(String str) {
        com.google.android.gms.analytics.zzr.zzh();
        zzW();
        SharedPreferences.Editor edit = this.zza.edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove("installation_campaign");
        } else {
            edit.putString("installation_campaign", str);
        }
        if (edit.commit()) {
            return;
        }
        zzR("Failed to commit campaign data");
    }

    public final void zzi() {
        com.google.android.gms.analytics.zzr.zzh();
        zzW();
        long currentTimeMillis = zzC().currentTimeMillis();
        SharedPreferences.Editor edit = this.zza.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        this.zzc = currentTimeMillis;
    }
}
