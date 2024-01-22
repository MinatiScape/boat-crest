package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzku extends zzhf implements zziq {
    private static final zzku zzb;
    private int zzd;
    private zzgn zze;
    private int zzf;

    static {
        zzku zzkuVar = new zzku();
        zzb = zzkuVar;
        zzhf.zzC(zzku.class, zzkuVar);
    }

    private zzku() {
    }

    public static zzkt zzf() {
        return (zzkt) zzb.zzp();
    }

    public static /* synthetic */ void zzi(zzku zzkuVar, zzgn zzgnVar) {
        zzgnVar.getClass();
        zzkuVar.zze = zzgnVar;
        zzkuVar.zzd |= 1;
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
                    return new zzkt(null);
                }
                return new zzku();
            }
            return zzhf.zzz(zzb, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002\u0004", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
