package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzyp extends zzuz<zzyp, zzym> implements zzwl {
    private static final zzyp zza;
    private byte zzf = 2;
    private zzvh<zzyo> zze = zzuz.zzag();

    static {
        zzyp zzypVar = new zzyp();
        zza = zzypVar;
        zzuz.zzak(zzyp.class, zzypVar);
    }

    public static zzyp zze() {
        return zza;
    }

    @Override // com.google.android.gms.internal.gtm.zzuz
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzf = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzym(null);
                }
                return new zzyp();
            }
            return zzuz.zzaj(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0001Л", new Object[]{"zze", zzyo.class});
        }
        return Byte.valueOf(this.zzf);
    }
}
