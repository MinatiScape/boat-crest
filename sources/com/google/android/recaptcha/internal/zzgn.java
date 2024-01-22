package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzgn extends zzhf implements zziq {
    private static final zzgn zzb;
    private long zzd;
    private int zze;

    static {
        zzgn zzgnVar = new zzgn();
        zzb = zzgnVar;
        zzhf.zzC(zzgn.class, zzgnVar);
    }

    private zzgn() {
    }

    public static zzgm zzi() {
        return (zzgm) zzb.zzp();
    }

    public final int zzf() {
        return this.zze;
    }

    public final long zzg() {
        return this.zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzhf
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        return zzb;
                    }
                    return new zzgm(null);
                }
                return new zzgn();
            }
            return new zzja(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
