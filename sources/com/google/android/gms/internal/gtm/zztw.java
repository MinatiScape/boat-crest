package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zztw extends zzuv<zztw, zztr> {
    private static final zztw zze;
    private int zzf;
    private int zzg;
    private boolean zzh;
    private int zzi;
    private boolean zzj;
    private boolean zzk;
    private boolean zzl;
    private boolean zzn;
    private boolean zzp;
    private byte zzr = 2;
    private zzvh<zztv> zzm = zzwu.zze();
    private boolean zzo = true;
    private zzvh<zzuf> zzq = zzwu.zze();

    static {
        zztw zztwVar = new zztw();
        zze = zztwVar;
        zzuz.zzak(zztw.class, zztwVar);
    }

    public static zztw zze() {
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
                            this.zzr = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zze;
                    }
                    return new zztr(null);
                }
                return new zztw();
            }
            return new zzwv(zze, "\u0001\u000b\u0000\u0001\u0001ϧ\u000b\u0000\u0002\u0001\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဇ\u0004\u0005ဇ\u0003\u0006ဌ\u0002\nဇ\u0005\u000b\u001b\fဇ\u0006\rဇ\u0007\u000eဇ\bϧЛ", new Object[]{"zzf", "zzg", zzts.zza, "zzh", "zzk", "zzj", "zzi", zztt.zza, "zzl", "zzm", zztv.class, "zzn", "zzo", "zzp", "zzq", zzuf.class});
        }
        return Byte.valueOf(this.zzr);
    }
}
