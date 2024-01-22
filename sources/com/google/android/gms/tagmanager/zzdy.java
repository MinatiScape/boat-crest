package com.google.android.gms.tagmanager;

import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Map;
@ShowFirstParty
@VisibleForTesting
/* loaded from: classes10.dex */
public abstract class zzdy extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zzb.ARG0.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.ARG1.toString();

    public zzdy(String str) {
        super(str, zza, zzb);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        for (com.google.android.gms.internal.gtm.zzak zzakVar : map.values()) {
            if (zzakVar == zzfv.zzb()) {
                return zzfv.zzc(Boolean.FALSE);
            }
        }
        com.google.android.gms.internal.gtm.zzak zzakVar2 = map.get(zza);
        com.google.android.gms.internal.gtm.zzak zzakVar3 = map.get(zzb);
        boolean z = false;
        if (zzakVar2 != null && zzakVar3 != null) {
            z = zzd(zzakVar2, zzakVar3, map);
        }
        return zzfv.zzc(Boolean.valueOf(z));
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return true;
    }

    public abstract boolean zzd(com.google.android.gms.internal.gtm.zzak zzakVar, com.google.android.gms.internal.gtm.zzak zzakVar2, Map<String, com.google.android.gms.internal.gtm.zzak> map);
}
