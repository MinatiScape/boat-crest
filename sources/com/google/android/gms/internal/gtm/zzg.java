package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzg extends zzuz<zzg, zzf> implements zzwl {
    private static final zzg zza;
    private int zze;
    private zzak zzg;
    private byte zzi = 2;
    private zzvh<zzi> zzf = zzuz.zzag();
    private String zzh = "";

    static {
        zzg zzgVar = new zzg();
        zza = zzgVar;
        zzuz.zzak(zzg.class, zzgVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuz
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzi = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzf(null);
                }
                return new zzg();
            }
            return zzuz.zzaj(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0002\u0001Л\u0002ᐉ\u0000\u0003ဈ\u0001", new Object[]{"zze", "zzf", zzi.class, "zzg", "zzh"});
        }
        return Byte.valueOf(this.zzi);
    }
}
