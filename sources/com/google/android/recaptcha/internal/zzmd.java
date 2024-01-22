package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzmd extends zzhf implements zziq {
    private static final zzmd zzb;
    private int zzd;
    private int zze;
    private zzgn zzh;
    private zzju zzi;
    private int zzj;
    private zzlj zzk;
    private String zzf = "";
    private String zzg = "";
    private zzhm zzl = zzhf.zzw();

    static {
        zzmd zzmdVar = new zzmd();
        zzb = zzmdVar;
        zzhf.zzC(zzmd.class, zzmdVar);
    }

    private zzmd() {
    }

    public static zzmd zzg() {
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
                    return new zzmc(null);
                }
                return new zzmd();
            }
            return zzhf.zzz(zzb, "\u0000\t\u0000\u0000\u0001\u000b\t\u0000\u0001\u0000\u0001\u0004\u0003\t\u0004\t\u0005\f\u0007\u001b\b\f\tȈ\nȈ\u000b\t", new Object[]{"zzd", "zzh", "zzi", "zzj", "zzl", zzlu.class, "zze", "zzf", "zzg", "zzk"});
        }
        return (byte) 1;
    }
}
