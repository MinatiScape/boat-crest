package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zze extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.ADVERTISER_ID.toString();
    public final zzd zzb;

    @VisibleForTesting
    public zze(zzd zzdVar) {
        super(zza, new String[0]);
        this.zzb = zzdVar;
        zzdVar.zzc();
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        String zzc = this.zzb.zzc();
        return zzc == null ? zzfv.zzb() : zzfv.zzc(zzc);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return false;
    }
}
