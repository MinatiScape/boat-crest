package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zze extends zzuz<zze, zzd> implements zzwl {
    public static final zzux<zzak, zze> zza;
    private static final zze zze;
    private int zzf;
    private zzm zzg;
    private zzg zzh;
    private byte zzi = 2;

    static {
        zze zzeVar = new zze();
        zze = zzeVar;
        zzuz.zzak(zze.class, zzeVar);
        zza = zzuz.zzaa(zzak.zzi(), zzeVar, zzeVar, null, 47497405, zzye.zzk, zze.class);
    }

    @Override // com.google.android.gms.internal.gtm.zzuz
    public final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzi = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zze;
                    }
                    return new zzd(null);
                }
                return new zze();
            }
            return zzuz.zzaj(zze, "\u0001\u0002\u0000\u0001\u0001\u0003\u0002\u0000\u0000\u0002\u0001ᐉ\u0000\u0003ᐉ\u0001", new Object[]{"zzf", "zzg", "zzh"});
        }
        return Byte.valueOf(this.zzi);
    }
}
