package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class zzv extends zzed implements zzfp {
    public static final zzeb zza;
    private static final zzv zzd;
    private int zze;
    private long zzf;
    private long zzg;
    private zzkc zzh;
    private byte zzi = 2;

    static {
        zzv zzvVar = new zzv();
        zzd = zzvVar;
        zzed.zzU(zzv.class, zzvVar);
        zza = zzed.zzH(zzkc.zzf(), zzvVar, zzvVar, null, 13258261, zzho.zzk, zzv.class);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzi = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzd;
                    }
                    return new zzu(null);
                }
                return new zzv();
            }
            return zzed.zzR(zzd, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001ᔅ\u0000\u0002ᔅ\u0001\u0003ᐉ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        return Byte.valueOf(this.zzi);
    }
}
