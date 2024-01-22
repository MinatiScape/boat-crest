package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class zzkc extends zzdz {
    private static final zzkc zzd;
    private byte zze = 2;

    static {
        zzkc zzkcVar = new zzkc();
        zzd = zzkcVar;
        zzed.zzU(zzkc.class, zzkcVar);
    }

    public static zzkc zzf() {
        return zzd;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zze = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zzd;
                    }
                    return new zzkb(null);
                }
                return new zzkc();
            }
            return zzed.zzR(zzd, "\u0003\u0000", null);
        }
        return Byte.valueOf(this.zze);
    }
}
