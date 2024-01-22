package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzfk extends zzjz<zzfk, zzfj> implements zzlh {
    private static final zzfk zza;
    private int zze;
    private int zzf;
    private zzgd zzg;
    private zzgd zzh;
    private boolean zzi;

    static {
        zzfk zzfkVar = new zzfk();
        zza = zzfkVar;
        zzjz.zzbG(zzfk.class, zzfkVar);
    }

    public static /* synthetic */ void f(zzfk zzfkVar, int i) {
        zzfkVar.zze |= 1;
        zzfkVar.zzf = i;
    }

    public static /* synthetic */ void g(zzfk zzfkVar, zzgd zzgdVar) {
        zzgdVar.getClass();
        zzfkVar.zzg = zzgdVar;
        zzfkVar.zze |= 2;
    }

    public static /* synthetic */ void h(zzfk zzfkVar, zzgd zzgdVar) {
        zzfkVar.zzh = zzgdVar;
        zzfkVar.zze |= 4;
    }

    public static /* synthetic */ void i(zzfk zzfkVar, boolean z) {
        zzfkVar.zze |= 8;
        zzfkVar.zzi = z;
    }

    public static zzfj zzb() {
        return zza.zzbu();
    }

    public final int zza() {
        return this.zzf;
    }

    public final zzgd zzd() {
        zzgd zzgdVar = this.zzg;
        return zzgdVar == null ? zzgd.zzh() : zzgdVar;
    }

    public final zzgd zze() {
        zzgd zzgdVar = this.zzh;
        return zzgdVar == null ? zzgd.zzh() : zzgdVar;
    }

    public final boolean zzj() {
        return this.zzi;
    }

    public final boolean zzk() {
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
                    return new zzfj(null);
                }
                return new zzfk();
            }
            return zzjz.zzbF(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002ဉ\u0001\u0003ဉ\u0002\u0004ဇ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        }
        return (byte) 1;
    }

    public final boolean zzm() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzn() {
        return (this.zze & 4) != 0;
    }
}
