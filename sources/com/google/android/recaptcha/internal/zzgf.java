package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzgf extends zzhb {
    private static final zzgf zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private byte zzl = 2;

    static {
        zzgf zzgfVar = new zzgf();
        zzd = zzgfVar;
        zzhf.zzC(zzgf.class, zzgfVar);
    }

    private zzgf() {
    }

    @Override // com.google.android.recaptcha.internal.zzhf
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzl = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzd;
                    }
                    return new zzfy(null);
                }
                return new zzgf();
            }
            return new zzja(zzd, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001᠌\u0000\u0002᠌\u0001\u0003᠌\u0002\u0004᠌\u0003\u0005᠌\u0004\u0006᠌\u0005", new Object[]{"zze", "zzf", zzga.zza, "zzg", zzfz.zza, "zzh", zzgd.zza, "zzi", zzge.zza, "zzj", zzgc.zza, "zzk", zzgb.zza});
        }
        return Byte.valueOf(this.zzl);
    }
}
