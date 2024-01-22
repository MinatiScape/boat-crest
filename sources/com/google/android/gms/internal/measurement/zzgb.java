package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzgb extends zzjz<zzgb, zzfz> implements zzlh {
    private static final zzgb zza;
    private int zze;
    private int zzf = 1;
    private zzkg<zzfq> zzg = zzjz.zzbA();

    static {
        zzgb zzgbVar = new zzgb();
        zza = zzgbVar;
        zzjz.zzbG(zzgb.class, zzgbVar);
    }

    public static /* synthetic */ void f(zzgb zzgbVar, zzfq zzfqVar) {
        zzfqVar.getClass();
        zzkg<zzfq> zzkgVar = zzgbVar.zzg;
        if (!zzkgVar.zzc()) {
            zzgbVar.zzg = zzjz.zzbB(zzkgVar);
        }
        zzgbVar.zzg.add(zzfqVar);
    }

    public static zzfz zza() {
        return zza.zzbu();
    }

    @Override // com.google.android.gms.internal.measurement.zzjz
    public final Object zzl(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        return zza;
                    }
                    return new zzfz(null);
                }
                return new zzgb();
            }
            return zzjz.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဌ\u0000\u0002\u001b", new Object[]{"zze", "zzf", k1.f8917a, "zzg", zzfq.class});
        }
        return (byte) 1;
    }
}
