package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class zzhv extends zzed implements zzfp {
    private static final zzhv zza;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzhv zzhvVar = new zzhv();
        zza = zzhvVar;
        zzed.zzU(zzhv.class, zzhvVar);
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
                    return new zzhu(null);
                }
                return new zzhv();
            }
            return zzed.zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
