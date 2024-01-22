package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzju extends zzhf implements zziq {
    private static final zzju zzb;
    private long zzd;
    private int zze;

    static {
        zzju zzjuVar = new zzju();
        zzb = zzjuVar;
        zzhf.zzC(zzju.class, zzjuVar);
    }

    private zzju() {
    }

    public static zzjt zzi() {
        return (zzjt) zzb.zzp();
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
                    return new zzjt(null);
                }
                return new zzju();
            }
            return new zzja(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\u0002\u0002\u0004", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
