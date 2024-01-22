package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzma extends zzhf implements zziq {
    private static final zzma zzb;
    private zzld zze;
    private zzkx zzf;
    private zzlg zzg;
    private String zzd = "";
    private String zzh = "";
    private String zzi = "";

    static {
        zzma zzmaVar = new zzma();
        zzb = zzmaVar;
        zzhf.zzC(zzma.class, zzmaVar);
    }

    private zzma() {
    }

    public static /* synthetic */ void zzG(zzma zzmaVar, zzkx zzkxVar) {
        zzkxVar.getClass();
        zzmaVar.zzf = zzkxVar;
    }

    public static zzlz zzf() {
        return (zzlz) zzb.zzp();
    }

    public static /* synthetic */ void zzi(zzma zzmaVar, String str) {
        str.getClass();
        zzmaVar.zzd = str;
    }

    public static /* synthetic */ void zzj(zzma zzmaVar, String str) {
        str.getClass();
        zzmaVar.zzh = str;
    }

    public static /* synthetic */ void zzk(zzma zzmaVar, String str) {
        str.getClass();
        zzmaVar.zzi = str;
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
                    return new zzlz(null);
                }
                return new zzma();
            }
            return zzhf.zzz(zzb, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001Ȉ\u0002\t\u0003\t\u0004\t\u0005Ȉ\u0006Ȉ", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi"});
        }
        return (byte) 1;
    }
}
