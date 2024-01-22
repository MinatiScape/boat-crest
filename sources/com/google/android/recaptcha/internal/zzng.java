package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzng extends zzhf implements zziq {
    private static final zzng zzb;
    private zzhm zzd = zzhf.zzw();
    private int zze;

    static {
        zzng zzngVar = new zzng();
        zzb = zzngVar;
        zzhf.zzC(zzng.class, zzngVar);
    }

    private zzng() {
    }

    public static zznd zzf() {
        return (zznd) zzb.zzp();
    }

    public static /* synthetic */ void zzi(zzng zzngVar, zznf zznfVar) {
        zznfVar.getClass();
        zzngVar.zzk();
        zzngVar.zzd.add(zznfVar);
    }

    public static /* synthetic */ void zzj(zzng zzngVar, Iterable iterable) {
        zzngVar.zzk();
        zzer.zzc(iterable, zzngVar.zzd);
    }

    private final void zzk() {
        zzhm zzhmVar = this.zzd;
        if (zzhmVar.zzc()) {
            return;
        }
        this.zzd = zzhf.zzx(zzhmVar);
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
                    return new zznd(null);
                }
                return new zzng();
            }
            return zzhf.zzz(zzb, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0001\u0000\u0001\u001b\u0002\u000b", new Object[]{"zzd", zznf.class, "zze"});
        }
        return (byte) 1;
    }
}
