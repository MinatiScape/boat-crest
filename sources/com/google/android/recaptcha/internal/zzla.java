package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzla extends zzhf implements zziq {
    private static final zzla zzb;
    private int zzf;
    private int zzi;
    private String zzd = "";
    private String zze = "";
    private String zzg = "";
    private String zzh = "";

    static {
        zzla zzlaVar = new zzla();
        zzb = zzlaVar;
        zzhf.zzC(zzla.class, zzlaVar);
    }

    private zzla() {
    }

    public static /* synthetic */ void zzG(zzla zzlaVar, String str) {
        str.getClass();
        zzlaVar.zzd = str;
    }

    public static zzkz zzg() {
        return (zzkz) zzb.zzp();
    }

    public static zzla zzj() {
        return zzb;
    }

    public final int zzf() {
        return this.zzf;
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
                    return new zzkz(null);
                }
                return new zzla();
            }
            return zzhf.zzz(zzb, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001Ȉ\u0002\u0004\u0003Ȉ\u0004\u0004\u0005Ȉ\u0006Ȉ", new Object[]{"zzd", "zzf", "zzh", "zzi", "zze", "zzg"});
        }
        return (byte) 1;
    }

    public final String zzk() {
        return this.zzd;
    }
}
