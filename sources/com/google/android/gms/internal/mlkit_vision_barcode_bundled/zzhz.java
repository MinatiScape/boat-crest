package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class zzhz extends zzed implements zzfp {
    private static final zzhz zza;
    private int zzd;
    private String zze = "";
    private String zzf = "";

    static {
        zzhz zzhzVar = new zzhz();
        zza = zzhzVar;
        zzed.zzU(zzhz.class, zzhzVar);
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
                    return new zzhy(null);
                }
                return new zzhz();
            }
            return zzed.zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
