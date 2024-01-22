package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzmq extends zzhf implements zziq {
    private static final zzmq zzb;
    private String zzd = "";
    private String zze = "";
    private int zzf;
    private int zzg;

    static {
        zzmq zzmqVar = new zzmq();
        zzb = zzmqVar;
        zzhf.zzC(zzmq.class, zzmqVar);
    }

    private zzmq() {
    }

    public static zzmq zzg(byte[] bArr) throws zzhp {
        return (zzmq) zzhf.zzu(zzb, bArr);
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
                    return new zzmp(null);
                }
                return new zzmq();
            }
            return zzhf.zzz(zzb, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003\f\u0004\f", new Object[]{"zzd", "zze", "zzf", "zzg"});
        }
        return (byte) 1;
    }

    public final zzmw zzi() {
        zzmw zzb2 = zzmw.zzb(this.zzf);
        return zzb2 == null ? zzmw.UNRECOGNIZED : zzb2;
    }

    public final String zzj() {
        return this.zzd;
    }

    public final String zzk() {
        return this.zze;
    }
}
