package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class zzje extends zzed implements zzfp {
    private static final zzje zza;
    private int zzd;
    private int zze;
    private int zzf = 100;
    private int zzg;

    static {
        zzje zzjeVar = new zzje();
        zza = zzjeVar;
        zzed.zzU(zzje.class, zzjeVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return null;
                        }
                        return zza;
                    }
                    return new zzjd(null);
                }
                return new zzje();
            }
            return zzed.zzR(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002င\u0001\u0003င\u0002", new Object[]{"zzd", "zze", b3.f9586a, "zzf", "zzg"});
        }
        return (byte) 1;
    }
}
