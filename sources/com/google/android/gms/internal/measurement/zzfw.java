package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzfw extends zzjz<zzfw, zzfv> implements zzlh {
    private static final zzfw zza;
    private zzkg<zzfy> zze = zzjz.zzbA();

    static {
        zzfw zzfwVar = new zzfw();
        zza = zzfwVar;
        zzjz.zzbG(zzfw.class, zzfwVar);
    }

    public static /* synthetic */ void f(zzfw zzfwVar, zzfy zzfyVar) {
        zzfyVar.getClass();
        zzkg<zzfy> zzkgVar = zzfwVar.zze;
        if (!zzkgVar.zzc()) {
            zzfwVar.zze = zzjz.zzbB(zzkgVar);
        }
        zzfwVar.zze.add(zzfyVar);
    }

    public static zzfv zza() {
        return zza.zzbu();
    }

    public final zzfy zzc(int i) {
        return this.zze.get(0);
    }

    public final List<zzfy> zzd() {
        return this.zze;
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
                    return new zzfv(null);
                }
                return new zzfw();
            }
            return zzjz.zzbF(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zze", zzfy.class});
        }
        return (byte) 1;
    }
}
