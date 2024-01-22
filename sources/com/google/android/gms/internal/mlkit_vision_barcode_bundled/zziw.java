package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class zziw extends zzed implements zzfp {
    private static final zziw zza;
    private int zzd;
    private boolean zze;
    private int zzf;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private boolean zzg = true;
    private String zzl = "";
    private String zzm = "";

    static {
        zziw zziwVar = new zziw();
        zza = zziwVar;
        zzed.zzU(zziw.class, zziwVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 == 2) {
                zzeh zzehVar = y2.f9624a;
                return zzed.zzR(zza, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဌ\u0001\u0003ဇ\u0002\u0004ဌ\u0003\u0005ဌ\u0004\u0006ဌ\u0005\u0007ဌ\u0006\bဈ\u0007\tဈ\b", new Object[]{"zzd", "zze", "zzf", z2.f9627a, "zzg", "zzh", x2.f9622a, "zzi", zzehVar, "zzj", zzehVar, "zzk", zzehVar, "zzl", "zzm"});
            } else if (i2 != 3) {
                if (i2 != 4) {
                    if (i2 != 5) {
                        return null;
                    }
                    return zza;
                }
                return new zziv(null);
            } else {
                return new zziw();
            }
        }
        return (byte) 1;
    }
}
