package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
/* loaded from: classes10.dex */
public final class zzl extends zzed implements zzfp {
    private static final zzl zza;
    private int zzd;
    private String zze = "";
    private zzel zzf = zzed.zzO();

    static {
        zzl zzlVar = new zzl();
        zza = zzlVar;
        zzed.zzU(zzl.class, zzlVar);
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
                    return new zzk(null);
                }
                return new zzl();
            }
            return zzed.zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zzd", "zze", "zzf", zzaa.class});
        }
        return (byte) 1;
    }
}
