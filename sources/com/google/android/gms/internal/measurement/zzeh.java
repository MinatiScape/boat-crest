package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzeh extends zzjz<zzeh, zzeg> implements zzlh {
    private static final zzeh zza;
    private int zze;
    private int zzf;
    private zzkg<zzes> zzg = zzjz.zzbA();
    private zzkg<zzej> zzh = zzjz.zzbA();
    private boolean zzi;
    private boolean zzj;

    static {
        zzeh zzehVar = new zzeh();
        zza = zzehVar;
        zzjz.zzbG(zzeh.class, zzehVar);
    }

    public static /* synthetic */ void f(zzeh zzehVar, int i, zzes zzesVar) {
        zzesVar.getClass();
        zzkg<zzes> zzkgVar = zzehVar.zzg;
        if (!zzkgVar.zzc()) {
            zzehVar.zzg = zzjz.zzbB(zzkgVar);
        }
        zzehVar.zzg.set(i, zzesVar);
    }

    public static /* synthetic */ void g(zzeh zzehVar, int i, zzej zzejVar) {
        zzejVar.getClass();
        zzkg<zzej> zzkgVar = zzehVar.zzh;
        if (!zzkgVar.zzc()) {
            zzehVar.zzh = zzjz.zzbB(zzkgVar);
        }
        zzehVar.zzh.set(i, zzejVar);
    }

    public final int zza() {
        return this.zzf;
    }

    public final int zzb() {
        return this.zzh.size();
    }

    public final int zzc() {
        return this.zzg.size();
    }

    public final zzej zze(int i) {
        return this.zzh.get(i);
    }

    public final zzes zzf(int i) {
        return this.zzg.get(i);
    }

    public final List<zzej> zzg() {
        return this.zzh;
    }

    public final List<zzes> zzh() {
        return this.zzg;
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
                    return new zzeg(null);
                }
                return new zzeh();
            }
            return zzjz.zzbF(zza, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001င\u0000\u0002\u001b\u0003\u001b\u0004ဇ\u0001\u0005ဇ\u0002", new Object[]{"zze", "zzf", "zzg", zzes.class, "zzh", zzej.class, "zzi", "zzj"});
        }
        return (byte) 1;
    }
}
