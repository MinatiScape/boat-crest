package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzlx extends zzhf implements zziq {
    private static final zzlx zzb;
    private zzgn zzd;
    private zzju zze;
    private zzgn zzf;
    private zzju zzg;

    static {
        zzlx zzlxVar = new zzlx();
        zzb = zzlxVar;
        zzhf.zzC(zzlx.class, zzlxVar);
    }

    private zzlx() {
    }

    public static /* synthetic */ void zzG(zzlx zzlxVar, zzgn zzgnVar) {
        zzgnVar.getClass();
        zzlxVar.zzf = zzgnVar;
    }

    public static zzlw zzf() {
        return (zzlw) zzb.zzp();
    }

    public static /* synthetic */ void zzi(zzlx zzlxVar, zzgn zzgnVar) {
        zzgnVar.getClass();
        zzlxVar.zzd = zzgnVar;
    }

    public static /* synthetic */ void zzj(zzlx zzlxVar, zzju zzjuVar) {
        zzjuVar.getClass();
        zzlxVar.zzg = zzjuVar;
    }

    public static /* synthetic */ void zzk(zzlx zzlxVar, zzju zzjuVar) {
        zzjuVar.getClass();
        zzlxVar.zze = zzjuVar;
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
                    return new zzlw(null);
                }
                return new zzlx();
            }
            return zzhf.zzz(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t\u0004\t", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
