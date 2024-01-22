package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
/* loaded from: classes10.dex */
public final class zzae extends zzed implements zzfp {
    private static final zzae zza;
    private int zzd;
    private int zze;
    private int zzf;
    private byte zzg = 2;

    static {
        zzae zzaeVar = new zzae();
        zza = zzaeVar;
        zzed.zzU(zzae.class, zzaeVar);
    }

    public static /* synthetic */ void l(zzae zzaeVar, int i) {
        zzaeVar.zzd |= 1;
        zzaeVar.zze = i;
    }

    public static /* synthetic */ void m(zzae zzaeVar, int i) {
        zzaeVar.zzd |= 2;
        zzaeVar.zzf = i;
    }

    public static zzad zzc() {
        return (zzad) zza.zzF();
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzg = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzad(null);
                }
                return new zzae();
            }
            return zzed.zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0002\u0001ᔄ\u0000\u0002ᔄ\u0001", new Object[]{"zzd", "zze", "zzf"});
        }
        return Byte.valueOf(this.zzg);
    }
}
