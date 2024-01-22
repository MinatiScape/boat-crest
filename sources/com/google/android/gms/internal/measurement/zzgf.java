package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzgf extends zzjz<zzgf, zzge> implements zzlh {
    private static final zzgf zza;
    private int zze;
    private int zzf;
    private zzkf zzg = zzjz.zzby();

    static {
        zzgf zzgfVar = new zzgf();
        zza = zzgfVar;
        zzjz.zzbG(zzgf.class, zzgfVar);
    }

    public static /* synthetic */ void f(zzgf zzgfVar, int i) {
        zzgfVar.zze |= 1;
        zzgfVar.zzf = i;
    }

    public static /* synthetic */ void g(zzgf zzgfVar, Iterable iterable) {
        zzkf zzkfVar = zzgfVar.zzg;
        if (!zzkfVar.zzc()) {
            zzgfVar.zzg = zzjz.zzbz(zzkfVar);
        }
        zzih.zzbq(iterable, zzgfVar.zzg);
    }

    public static zzge zzd() {
        return zza.zzbu();
    }

    public final int zza() {
        return this.zzg.size();
    }

    public final int zzb() {
        return this.zzf;
    }

    public final long zzc(int i) {
        return this.zzg.zza(i);
    }

    public final List<Long> zzf() {
        return this.zzg;
    }

    public final boolean zzi() {
        return (this.zze & 1) != 0;
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
                    return new zzge(null);
                }
                return new zzgf();
            }
            return zzjz.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001င\u0000\u0002\u0014", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
