package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzkx extends zzhf implements zziq {
    private static final zzhl zzb = new zzkv();
    private static final zzkx zzd;
    private int zze;
    private String zzf = "";
    private String zzg = "";
    private String zzh = "";
    private String zzi = "";
    private String zzj = "";
    private zzhk zzk = zzhf.zzv();

    static {
        zzkx zzkxVar = new zzkx();
        zzd = zzkxVar;
        zzhf.zzC(zzkx.class, zzkxVar);
    }

    private zzkx() {
    }

    public static /* synthetic */ void zzG(zzkx zzkxVar, String str) {
        str.getClass();
        zzkxVar.zzf = str;
    }

    public static /* synthetic */ void zzI(zzkx zzkxVar, String str) {
        str.getClass();
        zzkxVar.zzh = str;
    }

    public static zzkw zzf() {
        return (zzkw) zzd.zzp();
    }

    public static /* synthetic */ void zzj(zzkx zzkxVar, String str) {
        str.getClass();
        zzkxVar.zzi = str;
    }

    public static /* synthetic */ void zzk(zzkx zzkxVar, String str) {
        str.getClass();
        zzkxVar.zzj = str;
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
                        return zzd;
                    }
                    return new zzkw(null);
                }
                return new zzkx();
            }
            return zzhf.zzz(zzd, "\u0000\u0007\u0000\u0000\u0001\u0007\u0007\u0000\u0001\u0000\u0001\u0004\u0002Ȉ\u0003Ȉ\u0004Ȉ\u0005Ȉ\u0006Ȉ\u0007,", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        return (byte) 1;
    }
}
