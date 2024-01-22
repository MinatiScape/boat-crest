package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzub extends zzuv<zzub, zzua> {
    private static final zzub zze;
    private int zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private boolean zzj;
    private byte zzm = 2;
    private String zzk = "";
    private zzvh<zzuf> zzl = zzwu.zze();

    static {
        zzub zzubVar = new zzub();
        zze = zzubVar;
        zzuz.zzak(zzub.class, zzubVar);
    }

    public static zzub zze() {
        return zze;
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
                        return zze;
                    }
                    return new zzua(null);
                }
                return new zzub();
            }
            return new zzwv(zze, "\u0001\u0006\u0000\u0001\u0001ϧ\u0006\u0000\u0001\u0001\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0007ဇ\u0003\nဈ\u0004ϧЛ", new Object[]{"zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", zzuf.class});
        }
        return Byte.valueOf(this.zzm);
    }
}
