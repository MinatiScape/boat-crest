package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzms extends zzhf implements zziq {
    private static final zzms zzb;
    private String zzd = "";
    private String zze = "";

    static {
        zzms zzmsVar = new zzms();
        zzb = zzmsVar;
        zzhf.zzC(zzms.class, zzmsVar);
    }

    private zzms() {
    }

    public static zzmr zzf() {
        return (zzmr) zzb.zzp();
    }

    public static /* synthetic */ void zzi(zzms zzmsVar, String str) {
        str.getClass();
        zzmsVar.zzd = str;
    }

    public static /* synthetic */ void zzj(zzms zzmsVar, String str) {
        str.getClass();
        zzmsVar.zze = str;
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
                    return new zzmr(null);
                }
                return new zzms();
            }
            return zzhf.zzz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ", new Object[]{"zzd", "zze"});
        }
        return (byte) 1;
    }
}
