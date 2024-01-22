package com.google.android.gms.tagmanager;

import java.util.Map;
/* loaded from: classes10.dex */
public final class zzeb extends zzbu {
    public static final String zza = com.google.android.gms.internal.gtm.zza.RANDOM.toString();
    public static final String zzb = com.google.android.gms.internal.gtm.zzb.MIN.toString();
    public static final String zzc = com.google.android.gms.internal.gtm.zzb.MAX.toString();

    public zzeb() {
        super(zza, new String[0]);
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final com.google.android.gms.internal.gtm.zzak zza(Map<String, com.google.android.gms.internal.gtm.zzak> map) {
        com.google.android.gms.internal.gtm.zzak zzakVar = map.get(zzb);
        com.google.android.gms.internal.gtm.zzak zzakVar2 = map.get(zzc);
        double d = 2.147483647E9d;
        double d2 = 0.0d;
        if (zzakVar != null && zzakVar != zzfv.zzb() && zzakVar2 != null && zzakVar2 != zzfv.zzb()) {
            zzfu zze = zzfv.zze(zzfv.zzl(zzakVar));
            zzfu zze2 = zzfv.zze(zzfv.zzl(zzakVar2));
            if (zze != zzfv.zzd() && zze2 != zzfv.zzd()) {
                double doubleValue = zze.doubleValue();
                double doubleValue2 = zze2.doubleValue();
                if (doubleValue <= doubleValue2) {
                    d2 = doubleValue;
                    d = doubleValue2;
                }
            }
        }
        return zzfv.zzc(Long.valueOf(Math.round((Math.random() * (d - d2)) + d2)));
    }

    @Override // com.google.android.gms.tagmanager.zzbu
    public final boolean zzb() {
        return false;
    }
}
