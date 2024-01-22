package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zznf extends zzhf implements zziq {
    private static final zznf zzb;
    private int zzd = 0;
    private Object zze;
    private int zzf;

    static {
        zznf zznfVar = new zznf();
        zzb = zznfVar;
        zzhf.zzC(zznf.class, zznfVar);
    }

    private zznf() {
    }

    public static /* synthetic */ void zzG(zznf zznfVar, String str) {
        str.getClass();
        zznfVar.zzd = 11;
        zznfVar.zze = str;
    }

    public static /* synthetic */ void zzH(zznf zznfVar, boolean z) {
        zznfVar.zzd = 1;
        zznfVar.zze = Boolean.valueOf(z);
    }

    public static /* synthetic */ void zzI(zznf zznfVar, zzfi zzfiVar) {
        zznfVar.zzd = 2;
        zznfVar.zze = zzfiVar;
    }

    public static /* synthetic */ void zzJ(zznf zznfVar, String str) {
        str.getClass();
        zznfVar.zzd = 3;
        zznfVar.zze = str;
    }

    public static /* synthetic */ void zzK(zznf zznfVar, int i) {
        zznfVar.zzd = 4;
        zznfVar.zze = Integer.valueOf(i);
    }

    public static /* synthetic */ void zzL(zznf zznfVar, int i) {
        zznfVar.zzd = 5;
        zznfVar.zze = Integer.valueOf(i);
    }

    public static zzne zzf() {
        return (zzne) zzb.zzp();
    }

    public static /* synthetic */ zznf zzg() {
        return zzb;
    }

    public static /* synthetic */ void zzi(zznf zznfVar, long j) {
        zznfVar.zzd = 7;
        zznfVar.zze = Long.valueOf(j);
    }

    public static /* synthetic */ void zzj(zznf zznfVar, float f) {
        zznfVar.zzd = 9;
        zznfVar.zze = Float.valueOf(f);
    }

    public static /* synthetic */ void zzk(zznf zznfVar, double d) {
        zznfVar.zzd = 10;
        zznfVar.zze = Double.valueOf(d);
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
                    return new zzne(null);
                }
                return new zznf();
            }
            return zzhf.zzz(zzb, "\u0000\f\u0001\u0000\u0001\f\f\u0000\u0000\u0000\u0001:\u0000\u0002=\u0000\u0003Ȼ\u0000\u0004B\u0000\u0005B\u0000\u0006>\u0000\u0007C\u0000\b6\u0000\t4\u0000\n3\u0000\u000bȻ\u0000\f\u000b", new Object[]{"zze", "zzd", "zzf"});
        }
        return (byte) 1;
    }
}
