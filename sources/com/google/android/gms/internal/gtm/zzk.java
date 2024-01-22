package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzk extends zzuz<zzk, zzj> implements zzwl {
    private static final zzk zza;
    private int zze;
    private zzak zzl;
    private byte zzm = 2;
    private zzvh<zzg> zzf = zzuz.zzag();
    private zzvh<zzg> zzg = zzuz.zzag();
    private zzvh<zzg> zzh = zzuz.zzag();
    private zzvh<zzg> zzi = zzuz.zzag();
    private zzvh<zzg> zzj = zzuz.zzag();
    private zzvh<zzg> zzk = zzuz.zzag();

    static {
        zzk zzkVar = new zzk();
        zza = zzkVar;
        zzuz.zzak(zzk.class, zzkVar);
    }

    @Override // com.google.android.gms.internal.gtm.zzuz
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzm = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzj(null);
                }
                return new zzk();
            }
            return zzuz.zzaj(zza, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0006\u0007\u0001Л\u0002Л\u0003Л\u0004Л\u0005Л\u0006Л\u0007ᐉ\u0000", new Object[]{"zze", "zzf", zzg.class, "zzg", zzg.class, "zzh", zzg.class, "zzi", zzg.class, "zzj", zzg.class, "zzk", zzg.class, "zzl"});
        }
        return Byte.valueOf(this.zzm);
    }
}
