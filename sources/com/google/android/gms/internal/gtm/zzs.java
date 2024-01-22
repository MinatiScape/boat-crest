package com.google.android.gms.internal.gtm;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzs extends zzuz<zzs, zzr> implements zzwl {
    private static final zzs zza;
    private int zze;
    private int zzg;
    private int zzh;
    private boolean zzi;
    private boolean zzj;
    private byte zzk = 2;
    private zzve zzf = zzuz.zzaf();

    static {
        zzs zzsVar = new zzs();
        zza = zzsVar;
        zzuz.zzak(zzs.class, zzsVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuz
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzk = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzr(null);
                }
                return new zzs();
            }
            return zzuz.zzaj(zza, "\u0001\u0005\u0000\u0001\u0001\u0006\u0005\u0000\u0001\u0001\u0001ဇ\u0003\u0002ᔄ\u0000\u0003\u0016\u0004င\u0001\u0006ဇ\u0002", new Object[]{"zze", "zzj", "zzg", "zzf", "zzh", "zzi"});
        }
        return Byte.valueOf(this.zzk);
    }

    public final List<Integer> zzc() {
        return this.zzf;
    }
}
