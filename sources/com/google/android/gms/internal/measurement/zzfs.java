package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzfs extends zzjz<zzfs, zzfr> implements zzlh {
    private static final zzfs zza;
    private int zze;
    private long zzh;
    private float zzi;
    private double zzj;
    private String zzf = "";
    private String zzg = "";
    private zzkg<zzfs> zzk = zzjz.zzbA();

    static {
        zzfs zzfsVar = new zzfs();
        zza = zzfsVar;
        zzjz.zzbG(zzfs.class, zzfsVar);
    }

    public static /* synthetic */ void f(zzfs zzfsVar, String str) {
        str.getClass();
        zzfsVar.zze |= 1;
        zzfsVar.zzf = str;
    }

    public static /* synthetic */ void g(zzfs zzfsVar, String str) {
        str.getClass();
        zzfsVar.zze |= 2;
        zzfsVar.zzg = str;
    }

    public static /* synthetic */ void h(zzfs zzfsVar) {
        zzfsVar.zze &= -3;
        zzfsVar.zzg = zza.zzg;
    }

    public static /* synthetic */ void i(zzfs zzfsVar, long j) {
        zzfsVar.zze |= 4;
        zzfsVar.zzh = j;
    }

    public static /* synthetic */ void j(zzfs zzfsVar) {
        zzfsVar.zze &= -5;
        zzfsVar.zzh = 0L;
    }

    public static /* synthetic */ void k(zzfs zzfsVar, double d) {
        zzfsVar.zze |= 16;
        zzfsVar.zzj = d;
    }

    public static /* synthetic */ void l(zzfs zzfsVar) {
        zzfsVar.zze &= -17;
        zzfsVar.zzj = 0.0d;
    }

    public static /* synthetic */ void m(zzfs zzfsVar, zzfs zzfsVar2) {
        zzfsVar2.getClass();
        zzfsVar.p();
        zzfsVar.zzk.add(zzfsVar2);
    }

    public static /* synthetic */ void n(zzfs zzfsVar, Iterable iterable) {
        zzfsVar.p();
        zzih.zzbq(iterable, zzfsVar.zzk);
    }

    public static zzfr zze() {
        return zza.zzbu();
    }

    public final void p() {
        zzkg<zzfs> zzkgVar = this.zzk;
        if (zzkgVar.zzc()) {
            return;
        }
        this.zzk = zzjz.zzbB(zzkgVar);
    }

    public final double zza() {
        return this.zzj;
    }

    public final float zzb() {
        return this.zzi;
    }

    public final int zzc() {
        return this.zzk.size();
    }

    public final long zzd() {
        return this.zzh;
    }

    public final String zzg() {
        return this.zzf;
    }

    public final String zzh() {
        return this.zzg;
    }

    public final List<zzfs> zzi() {
        return this.zzk;
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
                    return new zzfr(null);
                }
                return new zzfs();
            }
            return zzjz.zzbF(zza, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဂ\u0002\u0004ခ\u0003\u0005က\u0004\u0006\u001b", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzfs.class});
        }
        return (byte) 1;
    }

    public final boolean zzu() {
        return (this.zze & 16) != 0;
    }

    public final boolean zzv() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzw() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzx() {
        return (this.zze & 1) != 0;
    }

    public final boolean zzy() {
        return (this.zze & 2) != 0;
    }
}
