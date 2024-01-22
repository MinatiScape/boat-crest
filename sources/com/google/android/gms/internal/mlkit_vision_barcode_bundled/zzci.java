package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class zzci extends zzed implements zzfp {
    private static final zzci zza;
    private int zzd;
    private int zze;
    private String zzf = "";

    static {
        zzci zzciVar = new zzci();
        zza = zzciVar;
        zzed.zzU(zzci.class, zzciVar);
    }

    public static zzci zzb() {
        return zza;
    }

    public final String zzc() {
        return this.zzf;
    }

    public final int zzd() {
        int zza2 = zzch.zza(this.zze);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
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
                    return new zzce(null);
                }
                return new zzci();
            }
            return zzed.zzR(zza, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဈ\u0001", new Object[]{"zzd", "zze", j.f9600a, "zzf"});
        }
        return (byte) 1;
    }
}
