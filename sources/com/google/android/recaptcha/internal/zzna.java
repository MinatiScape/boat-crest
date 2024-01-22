package com.google.android.recaptcha.internal;

import java.util.List;
/* loaded from: classes10.dex */
public final class zzna extends zzhf implements zziq {
    private static final zzna zzb;
    private zzhm zzd = zzhf.zzw();

    static {
        zzna zznaVar = new zzna();
        zzb = zznaVar;
        zzhf.zzC(zzna.class, zznaVar);
    }

    private zzna() {
    }

    public static zzna zzg(byte[] bArr) throws zzhp {
        return (zzna) zzhf.zzu(zzb, bArr);
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
                    return new zzmz(null);
                }
                return new zzna();
            }
            return zzhf.zzz(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zznm.class});
        }
        return (byte) 1;
    }

    public final List zzi() {
        return this.zzd;
    }
}
