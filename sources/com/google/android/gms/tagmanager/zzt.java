package com.google.android.gms.tagmanager;

import java.util.Map;
/* loaded from: classes10.dex */
public final class zzt extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.CONSTANT.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.VALUE.toString();

    public zzt() {
        super(zza, zzb);
    }

    public static String zzc() {
        return zza;
    }

    public static String zzd() {
        return zzb;
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        return map.get(zzb);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return true;
    }
}
