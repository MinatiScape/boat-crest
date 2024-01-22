package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzmy extends zzhf implements zziq {
    private static final zzmy zzb;
    private int zzd = 0;
    private Object zze;

    static {
        zzmy zzmyVar = new zzmy();
        zzb = zzmyVar;
        zzhf.zzC(zzmy.class, zzmyVar);
    }

    private zzmy() {
    }

    public static /* synthetic */ void zzG(zzmy zzmyVar, zzlo zzloVar) {
        zzloVar.getClass();
        zzmyVar.zze = zzloVar;
        zzmyVar.zzd = 1;
    }

    public static zzmx zzi() {
        return (zzmx) zzb.zzp();
    }

    public static zzmy zzk(byte[] bArr) throws zzhp {
        return (zzmy) zzhf.zzu(zzb, bArr);
    }

    public final int zzH() {
        int i = this.zzd;
        if (i != 0) {
            int i2 = 1;
            if (i != 1) {
                i2 = 2;
                if (i != 2) {
                    return 0;
                }
            }
            return i2;
        }
        return 3;
    }

    public final zzlo zzf() {
        return this.zzd == 1 ? (zzlo) this.zze : zzlo.zzG();
    }

    public final zzmd zzg() {
        return this.zzd == 2 ? (zzmd) this.zze : zzmd.zzg();
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
                    return new zzmx(null);
                }
                return new zzmy();
            }
            return zzhf.zzz(zzb, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"zze", "zzd", zzlo.class, zzmd.class});
        }
        return (byte) 1;
    }
}
