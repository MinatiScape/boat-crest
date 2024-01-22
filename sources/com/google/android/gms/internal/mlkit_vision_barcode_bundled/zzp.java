package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class zzp extends zzed implements zzfp {
    private static final zzp zza;
    private int zzd;
    private zzkc zzj;
    private byte zzk = 2;
    private zzej zze = zzed.zzN();
    private zzei zzf = zzed.zzL();
    private boolean zzg = true;
    private String zzh = "";
    private String zzi = "";

    static {
        zzp zzpVar = new zzp();
        zza = zzpVar;
        zzed.zzU(zzp.class, zzpVar);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzk = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzo(null);
                }
                return new zzp();
            }
            return zzed.zzR(zza, "\u0001\u0006\u0000\u0001\u0001\u000f\u0006\u0000\u0002\u0001\u0001\u0016\u0002\u0013\u0003ဇ\u0000\u0004ဈ\u0001\u0005ဈ\u0002\u000fᐉ\u0003", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        return Byte.valueOf(this.zzk);
    }
}
