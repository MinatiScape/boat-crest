package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzgj extends zzhf implements zziq {
    private static final zzgj zzb;
    private int zzd;
    private long zzg;
    private long zzh;
    private double zzi;
    private byte zzl = 2;
    private zzhm zze = zziz.zze();
    private String zzf = "";
    private zzfi zzj = zzfi.zzb;
    private String zzk = "";

    static {
        zzgj zzgjVar = new zzgj();
        zzb = zzgjVar;
        zzhf.zzC(zzgj.class, zzgjVar);
    }

    private zzgj() {
    }

    @Override // com.google.android.recaptcha.internal.zzhf
    public final Object zzh(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzl = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzb;
                    }
                    return new zzgg(null);
                }
                return new zzgj();
            }
            return new zzja(zzb, "\u0001\u0007\u0000\u0001\u0002\b\u0007\u0000\u0001\u0001\u0002Л\u0003ဈ\u0000\u0004ဃ\u0001\u0005ဂ\u0002\u0006က\u0003\u0007ည\u0004\bဈ\u0005", new Object[]{"zzd", "zze", zzgi.class, "zzf", "zzg", "zzh", "zzi", "zzj", "zzk"});
        }
        return Byte.valueOf(this.zzl);
    }
}
