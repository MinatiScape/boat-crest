package com.google.android.gms.tagmanager;

import java.util.Map;
/* loaded from: classes10.dex */
public abstract class zzdr extends zzdy {
    public zzdr(String str) {
        super(str);
    }

    public abstract boolean zzc(zzfu zzfuVar, zzfu zzfuVar2, Map<String, com.google.android.gms.internal.gtm.zzak> map);

    @Override // com.google.android.gms.tagmanager.zzdy
    public final boolean zzd(com.google.android.gms.internal.gtm.zzak zzakVar, com.google.android.gms.internal.gtm.zzak zzakVar2, Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        zzfu zze = zzfv.zze(zzfv.zzl(zzakVar));
        zzfu zze2 = zzfv.zze(zzfv.zzl(zzakVar2));
        if (zze == zzfv.zzd() || zze2 == zzfv.zzd()) {
            return false;
        }
        return zzc(zze, zze2, map);
    }
}
