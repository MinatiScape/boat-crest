package com.google.android.gms.tagmanager;

import java.util.Map;
/* loaded from: classes10.dex */
public final class zzbr extends zzfl {
    public static final String zza = com.google.android.gms.internal.gtm.zza.ENDS_WITH.toString();

    public zzbr() {
        super(zza);
    }

    @Override // com.google.android.gms.tagmanager.zzfl
    public final boolean zzc(String str, String str2, Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        return str.endsWith(str2);
    }
}
