package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzfm extends zzjz<zzfm, zzfl> implements zzlh {
    private static final zzfm zza;
    private int zze;
    private int zzf;
    private long zzg;

    static {
        zzfm zzfmVar = new zzfm();
        zza = zzfmVar;
        zzjz.zzbG(zzfm.class, zzfmVar);
    }

    public static /* synthetic */ void f(zzfm zzfmVar, int i) {
        zzfmVar.zze |= 1;
        zzfmVar.zzf = i;
    }

    public static /* synthetic */ void g(zzfm zzfmVar, long j) {
        zzfmVar.zze |= 2;
        zzfmVar.zzg = j;
    }

    public static zzfl zzc() {
        return zza.zzbu();
    }

    public final int zza() {
        return this.zzf;
    }

    public final long zzb() {
        return this.zzg;
    }

    public final boolean zzg() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzh() {
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
                    return new zzfl(null);
                }
                return new zzfm();
            }
            return zzjz.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဂ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
