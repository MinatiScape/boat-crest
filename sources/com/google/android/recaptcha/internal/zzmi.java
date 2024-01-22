package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes10.dex */
public final class zzmi extends zzhf implements zziq {
    private static final zzmi zzb;
    private zzmg zzd;
    private zzmg zze;

    static {
        zzmi zzmiVar = new zzmi();
        zzb = zzmiVar;
        zzhf.zzC(zzmi.class, zzmiVar);
    }

    private zzmi() {
    }

    public static zzmi zzj(InputStream inputStream) throws IOException {
        return (zzmi) zzhf.zzt(zzb, inputStream);
    }

    public final zzmg zzf() {
        zzmg zzmgVar = this.zzd;
        return zzmgVar == null ? zzmg.zzg() : zzmgVar;
    }

    public final zzmg zzg() {
        zzmg zzmgVar = this.zze;
        return zzmgVar == null ? zzmg.zzg() : zzmgVar;
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
                    return new zzmh(null);
                }
                return new zzmi();
            }
            return zzhf.zzz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001\t\u0002\t", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
