package com.google.android.gms.tagmanager;

import java.util.Map;
/* loaded from: classes10.dex */
public final class zzde extends zzdr {
    public static final String zza = com.google.android.gms.internal.gtm.zza.LESS_THAN.toString();

    public zzde() {
        super(zza);
    }

    @Override // com.google.android.gms.tagmanager.zzdr
    public final boolean zzc(zzfu zzfuVar, zzfu zzfuVar2, Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        return zzfuVar.compareTo(zzfuVar2) < 0;
    }
}
