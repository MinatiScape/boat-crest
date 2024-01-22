package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzuf extends zzuz<zzuf, zzuc> implements zzwl {
    private static final zzuf zza;
    private int zze;
    private long zzh;
    private long zzi;
    private double zzj;
    private byte zzm = 2;
    private zzvh<zzue> zzf = zzwu.zze();
    private String zzg = "";
    private zztd zzk = zztd.zzb;
    private String zzl = "";

    static {
        zzuf zzufVar = new zzuf();
        zza = zzufVar;
        zzuz.zzak(zzuf.class, zzufVar);
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
                    return new zzuc(null);
                }
                return new zzuf();
            }
            return new zzwv(zza, "\u0001\u0007\u0000\u0001\u0002\b\u0007\u0000\u0001\u0001\u0002Л\u0003ဈ\u0000\u0004ဃ\u0001\u0005ဂ\u0002\u0006က\u0003\u0007ည\u0004\bဈ\u0005", new Object[]{"zze", "zzf", zzue.class, "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        }
        return Byte.valueOf(this.zzm);
    }
}
