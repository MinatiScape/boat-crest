package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzmu extends zzhf implements zziq {
    private static final zzmu zzb;
    private int zzd;

    static {
        zzmu zzmuVar = new zzmu();
        zzb = zzmuVar;
        zzhf.zzC(zzmu.class, zzmuVar);
    }

    private zzmu() {
    }

    public static zzmu zzg(byte[] bArr) throws zzhp {
        return (zzmu) zzhf.zzu(zzb, bArr);
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
                    return new zzmt(null);
                }
                return new zzmu();
            }
            return zzhf.zzz(zzb, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"zzd"});
        }
        return (byte) 1;
    }

    public final zzmw zzi() {
        zzmw zzb2 = zzmw.zzb(this.zzd);
        return zzb2 == null ? zzmw.UNRECOGNIZED : zzb2;
    }
}
