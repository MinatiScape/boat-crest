package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzlg extends zzhf implements zziq {
    private static final zzlg zzb;
    private String zzd = "";
    private String zze = "";

    static {
        zzlg zzlgVar = new zzlg();
        zzb = zzlgVar;
        zzhf.zzC(zzlg.class, zzlgVar);
    }

    private zzlg() {
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
                    return new zzlf(null);
                }
                return new zzlg();
            }
            return zzhf.zzz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
