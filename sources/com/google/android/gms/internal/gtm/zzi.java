package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzi extends zzuz<zzi, zzh> implements zzwl {
    private static final zzi zza;
    private int zze;
    private zzak zzg;
    private byte zzh = 2;
    private String zzf = "";

    static {
        zzi zziVar = new zzi();
        zza = zziVar;
        zzuz.zzak(zzi.class, zziVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuz
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzh = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzh(null);
                }
                return new zzi();
            }
            return zzuz.zzaj(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0001\u0001ဈ\u0000\u0002ᐉ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return Byte.valueOf(this.zzh);
    }
}
