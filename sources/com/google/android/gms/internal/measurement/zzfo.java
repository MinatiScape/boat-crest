package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzfo extends zzjz<zzfo, zzfn> implements zzlh {
    private static final zzfo zza;
    private int zze;
    private zzkg<zzfs> zzf = zzjz.zzbA();
    private String zzg = "";
    private long zzh;
    private long zzi;
    private int zzj;

    static {
        zzfo zzfoVar = new zzfo();
        zza = zzfoVar;
        zzjz.zzbG(zzfo.class, zzfoVar);
    }

    public static /* synthetic */ void f(zzfo zzfoVar, int i, zzfs zzfsVar) {
        zzfsVar.getClass();
        zzfoVar.n();
        zzfoVar.zzf.set(i, zzfsVar);
    }

    public static /* synthetic */ void g(zzfo zzfoVar, zzfs zzfsVar) {
        zzfsVar.getClass();
        zzfoVar.n();
        zzfoVar.zzf.add(zzfsVar);
    }

    public static /* synthetic */ void h(zzfo zzfoVar, Iterable iterable) {
        zzfoVar.n();
        zzih.zzbq(iterable, zzfoVar.zzf);
    }

    public static /* synthetic */ void j(zzfo zzfoVar, int i) {
        zzfoVar.n();
        zzfoVar.zzf.remove(i);
    }

    public static /* synthetic */ void k(zzfo zzfoVar, String str) {
        str.getClass();
        zzfoVar.zze |= 1;
        zzfoVar.zzg = str;
    }

    public static /* synthetic */ void l(zzfo zzfoVar, long j) {
        zzfoVar.zze |= 2;
        zzfoVar.zzh = j;
    }

    public static /* synthetic */ void m(zzfo zzfoVar, long j) {
        zzfoVar.zze |= 4;
        zzfoVar.zzi = j;
    }

    public static zzfn zze() {
        return zza.zzbu();
    }

    public final void n() {
        zzkg<zzfs> zzkgVar = this.zzf;
        if (zzkgVar.zzc()) {
            return;
        }
        this.zzf = zzjz.zzbB(zzkgVar);
    }

    public final int zza() {
        return this.zzj;
    }

    public final int zzb() {
        return this.zzf.size();
    }

    public final long zzc() {
        return this.zzi;
    }

    public final long zzd() {
        return this.zzh;
    }

    public final zzfs zzg(int i) {
        return this.zzf.get(i);
    }

    public final String zzh() {
        return this.zzg;
    }

    public final List<zzfs> zzi() {
        return this.zzf;
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
                    return new zzfn(null);
                }
                return new zzfo();
            }
            return zzjz.zzbF(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001\u001b\u0002ဈ\u0000\u0003ဂ\u0001\u0004ဂ\u0002\u0005င\u0003", new Object[]{"zze", "zzf", zzfs.class, "zzg", "zzh", "zzi", "zzj"});
        }
        return (byte) 1;
    }

    public final boolean zzs() {
        return (this.zze & 8) != 0;
    }

    public final boolean zzt() {
        return (this.zze & 4) != 0;
    }

    public final boolean zzu() {
        return (this.zze & 2) != 0;
    }
}
