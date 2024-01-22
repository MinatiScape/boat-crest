package com.google.android.gms.internal.gtm;

import android.text.TextUtils;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import java.util.HashMap;
@ShowFirstParty
@VisibleForTesting
/* loaded from: classes8.dex */
public final class zzav extends com.google.android.gms.analytics.zzj<zzav> {
    public String zza;
    public String zzb;
    public String zzc;
    public String zzd;

    public final String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("appName", this.zza);
        hashMap.put(RemoteConfigConstants.RequestFieldKey.APP_VERSION, this.zzb);
        hashMap.put(RemoteConfigConstants.RequestFieldKey.APP_ID, this.zzc);
        hashMap.put("appInstallerId", this.zzd);
        return com.google.android.gms.analytics.zzj.zza(hashMap);
    }

    public final String zzd() {
        return this.zzc;
    }

    public final String zze() {
        return this.zzd;
    }

    public final String zzf() {
        return this.zza;
    }

    public final String zzg() {
        return this.zzb;
    }

    @Override // com.google.android.gms.analytics.zzj
    /* renamed from: zzh */
    public final void zzc(zzav zzavVar) {
        if (!TextUtils.isEmpty(this.zza)) {
            zzavVar.zza = this.zza;
        }
        if (!TextUtils.isEmpty(this.zzb)) {
            zzavVar.zzb = this.zzb;
        }
        if (!TextUtils.isEmpty(this.zzc)) {
            zzavVar.zzc = this.zzc;
        }
        if (TextUtils.isEmpty(this.zzd)) {
            return;
        }
        zzavVar.zzd = this.zzd;
    }

    public final void zzi(String str) {
        this.zzc = str;
    }

    public final void zzj(String str) {
        this.zzd = str;
    }

    public final void zzk(String str) {
        this.zza = str;
    }

    public final void zzl(String str) {
        this.zzb = str;
    }
}
