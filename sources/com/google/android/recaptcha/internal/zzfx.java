package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzfx extends zzhb {
    private static final zzfx zzd;
    private int zze;
    private boolean zzf;
    private zzgf zzg;
    private byte zzi = 2;
    private zzhm zzh = zziz.zze();

    static {
        zzfx zzfxVar = new zzfx();
        zzd = zzfxVar;
        zzhf.zzC(zzfx.class, zzfxVar);
    }

    private zzfx() {
    }

    public static zzfx zzg() {
        return zzd;
    }

    @Override // com.google.android.recaptcha.internal.zzhf
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzi = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzd;
                    }
                    return new zzfw(null);
                }
                return new zzfx();
            }
            return new zzja(zzd, "\u0001\u0003\u0000\u0001\u0001ϧ\u0003\u0000\u0001\u0002\u0001ဇ\u0000\u0002ᐉ\u0001ϧЛ", new Object[]{"zze", "zzf", "zzg", "zzh", zzgj.class});
        }
        return Byte.valueOf(this.zzi);
    }
}
