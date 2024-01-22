package com.google.android.gms.tagmanager;

import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public final class zzbf extends zzft {
    public static final String zza = com.google.android.gms.internal.gtm.zza.DATA_LAYER_WRITE.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.VALUE.toString();
    public static final String zzc = com.google.android.gms.internal.gtm.zzb.CLEAR_PERSISTENT_DATA_LAYER_PREFIX.toString();
    public final DataLayer zzd;

    public zzbf(DataLayer dataLayer) {
        super(zza, zzb);
        this.zzd = dataLayer;
    }

    @Override // com.google.android.gms.tagmanager.zzft
    public final void zzc(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        String zzn;
        com.google.android.gms.internal.gtm.zzak zzakVar = map.get(zzb);
        if (zzakVar != null) {
            Object zzl = zzfv.zzl(zzakVar);
            if (zzl instanceof List) {
                for (Object obj : (List) zzl) {
                    if (obj instanceof Map) {
                        this.zzd.push((Map) obj);
                    }
                }
            }
        }
        com.google.android.gms.internal.gtm.zzak zzakVar2 = map.get(zzc);
        if (zzakVar2 == null || (zzn = zzfv.zzn(zzfv.zzl(zzakVar2))) == zzfv.zzm()) {
            return;
        }
        this.zzd.zzd(zzn);
    }
}
