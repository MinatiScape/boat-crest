package com.google.android.gms.internal.measurement;
/* loaded from: classes8.dex */
public final class zzgh extends zzjz<zzgh, zzgg> implements zzlh {
    private static final zzgh zza;
    private int zze;
    private long zzf;
    private String zzg = "";
    private String zzh = "";
    private long zzi;
    private float zzj;
    private double zzk;

    static {
        zzgh zzghVar = new zzgh();
        zza = zzghVar;
        zzjz.zzbG(zzgh.class, zzghVar);
    }

    public static /* synthetic */ void f(zzgh zzghVar, long j) {
        zzghVar.zze |= 1;
        zzghVar.zzf = j;
    }

    public static /* synthetic */ void g(zzgh zzghVar, String str) {
        str.getClass();
        zzghVar.zze |= 2;
        zzghVar.zzg = str;
    }

    public static /* synthetic */ void h(zzgh zzghVar, String str) {
        str.getClass();
        zzghVar.zze |= 4;
        zzghVar.zzh = str;
    }

    public static /* synthetic */ void i(zzgh zzghVar) {
        zzghVar.zze &= -5;
        zzghVar.zzh = zza.zzh;
    }

    public static /* synthetic */ void j(zzgh zzghVar, long j) {
        zzghVar.zze |= 8;
        zzghVar.zzi = j;
    }

    public static /* synthetic */ void k(zzgh zzghVar) {
        zzghVar.zze &= -9;
        zzghVar.zzi = 0L;
    }

    public static /* synthetic */ void l(zzgh zzghVar, double d) {
        zzghVar.zze |= 32;
        zzghVar.zzk = d;
    }

    public static /* synthetic */ void m(zzgh zzghVar) {
        zzghVar.zze &= -33;
        zzghVar.zzk = 0.0d;
    }

    public static zzgg zzd() {
        return zza.zzbu();
    }

    public final double zza() {
        return this.zzk;
    }

    public final long zzb() {
        return this.zzi;
    }

    public final long zzc() {
        return this.zzf;
    }

    public final String zzf() {
        return this.zzg;
    }

    public final String zzg() {
        return this.zzh;
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
                    return new zzgg(null);
                }
                return new zzgh();
            }
            return zzjz.zzbF(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ခ\u0004\u0006က\u0005", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        return (byte) 1;
    }

    public final boolean zzq() {
        return (this.zze & 32) != 0;
    }

    public final boolean zzr() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzs() {
        return (this.zze & 1) != 0;
    }

    public final boolean zzt() {
        return (this.zze & 4) != 0;
    }
}
