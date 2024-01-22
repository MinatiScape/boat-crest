package com.google.android.gms.internal.measurement;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzgm extends zzjz<zzgm, zzgl> implements zzlh {
    private static final zzgm zza;
    private int zze;
    private String zzf = "";
    private zzkg<zzgt> zzg = zzjz.zzbA();

    static {
        zzgm zzgmVar = new zzgm();
        zza = zzgmVar;
        zzjz.zzbG(zzgm.class, zzgmVar);
    }

    public final String zzb() {
        return this.zzf;
    }

    public final List<zzgt> zzc() {
        return this.zzg;
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
                    return new zzgl(null);
                }
                return new zzgm();
            }
            return zzjz.zzbF(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zze", "zzf", "zzg", zzgt.class});
        }
        return (byte) 1;
    }
}
