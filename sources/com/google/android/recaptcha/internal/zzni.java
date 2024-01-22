package com.google.android.recaptcha.internal;

import java.util.List;
/* loaded from: classes10.dex */
public final class zzni extends zzhf implements zziq {
    private static final zzni zzb;
    private String zzd = "";
    private zzhk zze = zzhf.zzv();

    static {
        zzni zzniVar = new zzni();
        zzb = zzniVar;
        zzhf.zzC(zzni.class, zzniVar);
    }

    private zzni() {
    }

    public static zzni zzg(byte[] bArr) throws zzhp {
        return (zzni) zzhf.zzu(zzb, bArr);
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
                    return new zznh(null);
                }
                return new zzni();
            }
            return zzhf.zzz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001Ȉ\u0002'", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }

    public final String zzi() {
        return this.zzd;
    }

    public final List zzj() {
        return this.zze;
    }
}
