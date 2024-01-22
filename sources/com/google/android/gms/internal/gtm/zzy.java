package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzy extends zzuz<zzy, zzx> implements zzwl {
    private static final zzy zza;
    private int zze;
    private int zzf;
    private int zzg;
    private byte zzh = 2;

    static {
        zzy zzyVar = new zzy();
        zza = zzyVar;
        zzuz.zzak(zzy.class, zzyVar);
    }

    public final int zza() {
        return this.zzf;
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
                    return new zzx(null);
                }
                return new zzy();
            }
            return zzuz.zzaj(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔄ\u0000\u0002ᔄ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return Byte.valueOf(this.zzh);
    }

    public final int zzc() {
        return this.zzg;
    }
}
