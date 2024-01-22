package com.google.android.gms.tagmanager;

import java.util.Map;
/* loaded from: classes10.dex */
public abstract class zzfl extends zzdy {
    public zzfl(String str) {
        super(str);
    }

    public abstract boolean zzc(String str, String str2, Map<String, com.google.android.gms.internal.gtm.zzak> map);

    @Override // com.google.android.gms.tagmanager.zzdy
    public final boolean zzd(com.google.android.gms.internal.gtm.zzak zzakVar, com.google.android.gms.internal.gtm.zzak zzakVar2, Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        String zzn = zzfv.zzn(zzfv.zzl(zzakVar));
        String zzn2 = zzfv.zzn(zzfv.zzl(zzakVar2));
        if (zzn == zzfv.zzm() || zzn2 == zzfv.zzm()) {
            return false;
        }
        return zzc(zzn, zzn2, map);
    }
}
