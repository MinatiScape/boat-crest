package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.coveiot.android.tappy.utils.Constants;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.HashMap;
@ShowFirstParty
/* loaded from: classes8.dex */
public final class zzbe extends com.google.android.gms.analytics.zzj<zzbe> {
    public String zza;
    public String zzb;
    public String zzc;
    public String zzd;
    public boolean zze;
    public boolean zzf;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("hitType", this.zza);
        hashMap.put("clientId", this.zzb);
        hashMap.put(Constants.END_USER_GLOBAL_ID, this.zzc);
        hashMap.put("androidAdId", this.zzd);
        hashMap.put("AdTargetingEnabled", Boolean.valueOf(this.zze));
        hashMap.put("sessionControl", null);
        hashMap.put("nonInteraction", Boolean.valueOf(this.zzf));
        hashMap.put("sampleRate", Double.valueOf(0.0d));
        return com.google.android.gms.analytics.zzj.zza(hashMap);
    }

    @Override // com.google.android.gms.analytics.zzj
    public final /* bridge */ /* synthetic */ void zzc(zzbe zzbeVar) {
        zzbe zzbeVar2 = zzbeVar;
        if (!TextUtils.isEmpty(this.zza)) {
            zzbeVar2.zza = this.zza;
        }
        if (!TextUtils.isEmpty(this.zzb)) {
            zzbeVar2.zzb = this.zzb;
        }
        if (!TextUtils.isEmpty(this.zzc)) {
            zzbeVar2.zzc = this.zzc;
        }
        if (!TextUtils.isEmpty(this.zzd)) {
            zzbeVar2.zzd = this.zzd;
        }
        if (this.zze) {
            zzbeVar2.zze = true;
        }
        TextUtils.isEmpty(null);
        if (this.zzf) {
            zzbeVar2.zzf = true;
        }
    }

    public final String zzd() {
        return this.zzd;
    }

    public final String zze() {
        return this.zzb;
    }

    public final String zzf() {
        return this.zza;
    }

    public final String zzg() {
        return this.zzc;
    }

    public final void zzh(boolean z) {
        this.zze = z;
    }

    public final void zzi(String str) {
        this.zzd = str;
    }

    public final void zzj(String str) {
        this.zzb = str;
    }

    public final void zzk(String str) {
        this.zza = "data";
    }

    public final void zzl(boolean z) {
        this.zzf = true;
    }

    public final void zzm(String str) {
        this.zzc = str;
    }

    public final boolean zzn() {
        return this.zze;
    }

    public final boolean zzo() {
        return this.zzf;
    }
}
