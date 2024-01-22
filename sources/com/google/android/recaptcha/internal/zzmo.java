package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzmo extends zzhf implements zziq {
    private static final zzmo zzb;
    private String zzd = "";
    private String zze = "";
    private String zzf = "";

    static {
        zzmo zzmoVar = new zzmo();
        zzb = zzmoVar;
        zzhf.zzC(zzmo.class, zzmoVar);
    }

    private zzmo() {
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
                    return new zzmn(null);
                }
                return new zzmo();
            }
            return zzhf.zzz(zzb, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
