package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzm extends zzuz<zzm, zzl> implements zzwl {
    private static final zzm zza;
    private byte zzg = 2;
    private zzvh<zzk> zze = zzuz.zzag();
    private zzvh<zzg> zzf = zzuz.zzag();

    static {
        zzm zzmVar = new zzm();
        zza = zzmVar;
        zzuz.zzak(zzm.class, zzmVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuz
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzg = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzl(null);
                }
                return new zzm();
            }
            return zzuz.zzaj(zza, "\u0001\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0002\u0002\u0001Л\u0002Л", new Object[]{"zze", zzk.class, "zzf", zzg.class});
        }
        return Byte.valueOf(this.zzg);
    }
}
