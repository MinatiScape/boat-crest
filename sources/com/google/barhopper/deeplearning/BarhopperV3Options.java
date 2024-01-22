package com.google.barhopper.deeplearning;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
/* loaded from: classes10.dex */
public final class BarhopperV3Options extends zzed<BarhopperV3Options, zzk> implements zzfp {
    private static final BarhopperV3Options zza;
    private int zzd;
    private zzi zze;
    private zzn zzf;

    static {
        BarhopperV3Options barhopperV3Options = new BarhopperV3Options();
        zza = barhopperV3Options;
        zzed.zzU(BarhopperV3Options.class, barhopperV3Options);
    }

    public static /* synthetic */ void l(BarhopperV3Options barhopperV3Options, zzi zziVar) {
        zziVar.getClass();
        barhopperV3Options.zze = zziVar;
        barhopperV3Options.zzd |= 1;
    }

    public static /* synthetic */ void m(BarhopperV3Options barhopperV3Options, zzn zznVar) {
        zznVar.getClass();
        barhopperV3Options.zzf = zznVar;
        barhopperV3Options.zzd |= 2;
    }

    public static zzk zza() {
        return (zzk) zza.zzF();
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed
    @NonNull
    public final Object zzg(int i, @NonNull Object obj, @NonNull Object obj2) {
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
                return new BarhopperV3Options();
            }
            return zzed.zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        return (byte) 1;
    }
}
