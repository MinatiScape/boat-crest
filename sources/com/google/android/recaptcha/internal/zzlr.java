package com.google.android.recaptcha.internal;

import java.util.List;
/* loaded from: classes10.dex */
public final class zzlr extends zzhf implements zziq {
    private static final zzlr zzb;
    private zzhm zzd = zzhf.zzw();
    private zzhm zze = zzhf.zzw();

    static {
        zzlr zzlrVar = new zzlr();
        zzb = zzlrVar;
        zzhf.zzC(zzlr.class, zzlrVar);
    }

    private zzlr() {
    }

    public static /* synthetic */ void zzH(zzlr zzlrVar, zzlo zzloVar) {
        zzloVar.getClass();
        zzhm zzhmVar = zzlrVar.zzd;
        if (!zzhmVar.zzc()) {
            zzlrVar.zzd = zzhf.zzx(zzhmVar);
        }
        zzlrVar.zzd.add(zzloVar);
    }

    public static /* synthetic */ void zzI(zzlr zzlrVar, zzmd zzmdVar) {
        zzmdVar.getClass();
        zzhm zzhmVar = zzlrVar.zze;
        if (!zzhmVar.zzc()) {
            zzlrVar.zze = zzhf.zzx(zzhmVar);
        }
        zzlrVar.zze.add(zzmdVar);
    }

    public static zzlq zzi() {
        return (zzlq) zzb.zzp();
    }

    public static zzlr zzk(byte[] bArr) throws zzhp {
        return (zzlr) zzhf.zzu(zzb, bArr);
    }

    public final List zzG() {
        return this.zzd;
    }

    public final int zzf() {
        return this.zzd.size();
    }

    public final int zzg() {
        return this.zze.size();
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
                    return new zzlq(null);
                }
                return new zzlr();
            }
            return zzhf.zzz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0000\u0001\u001b\u0002\u001b", new Object[]{"zzd", zzlo.class, "zze", zzmd.class});
        }
        return (byte) 1;
    }
}
