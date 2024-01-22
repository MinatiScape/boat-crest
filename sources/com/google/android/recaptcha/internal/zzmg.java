package com.google.android.recaptcha.internal;

import java.util.List;
/* loaded from: classes10.dex */
public final class zzmg extends zzhf implements zziq {
    private static final zzmg zzb;
    private zzhm zzd = zzhf.zzw();

    static {
        zzmg zzmgVar = new zzmg();
        zzb = zzmgVar;
        zzhf.zzC(zzmg.class, zzmgVar);
    }

    private zzmg() {
    }

    public static zzmg zzg() {
        return zzb;
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
                    return new zzmf(null);
                }
                return new zzmg();
            }
            return zzhf.zzz(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001Ț", new Object[]{"zzd"});
        }
        return (byte) 1;
    }

    public final List zzi() {
        return this.zzd;
    }
}
