package com.google.android.gms.tagmanager;

import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;
@VisibleForTesting
/* loaded from: classes10.dex */
public final class zzbt extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.EVENT.toString();
    public final zzeu zzb;

    public zzbt(zzeu zzeuVar) {
        super(zza, new String[0]);
        this.zzb = zzeuVar;
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        String zzb = this.zzb.zzb();
        if (zzb == null) {
            return zzfv.zzb();
        }
        return zzfv.zzc(zzb);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return false;
    }
}
