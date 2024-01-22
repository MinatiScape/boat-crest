package com.google.android.gms.internal.gtm;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzw extends zzuz<zzw, zzv> implements zzwl {
    private static final zzw zza;
    private byte zzh = 2;
    private zzvh<zzak> zze = zzuz.zzag();
    private zzvh<zzak> zzf = zzuz.zzag();
    private zzvh<zzu> zzg = zzuz.zzag();

    static {
        zzw zzwVar = new zzw();
        zza = zzwVar;
        zzuz.zzak(zzw.class, zzwVar);
    }

    public static zzw zzc() {
        return zza;
    }

    @Override // com.google.android.gms.internal.gtm.zzuz
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzh = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzv(null);
                }
                return new zzw();
            }
            return zzuz.zzaj(zza, "\u0001\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0003\u0002\u0001Л\u0002Л\u0003\u001b", new Object[]{"zze", zzak.class, "zzf", zzak.class, "zzg", zzu.class});
        }
        return Byte.valueOf(this.zzh);
    }

    public final List<zzu> zzd() {
        return this.zzg;
    }

    public final List<zzak> zze() {
        return this.zzf;
    }

    public final List<zzak> zzf() {
        return this.zze;
    }
}
