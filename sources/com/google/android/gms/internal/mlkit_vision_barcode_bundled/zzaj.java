package com.google.android.gms.internal.mlkit_vision_barcode_bundled;
/* loaded from: classes8.dex */
public final class zzaj extends zzed implements zzfp {
    public static final zzeb zza;
    private static final zzek zzd = new c();
    private static final zzaj zze;
    private int zzf;
    private zzy zzl;
    private zzkc zzm;
    private byte zzn = 2;
    private String zzg = "";
    private String zzh = "";
    private zzej zzi = zzed.zzN();
    private String zzj = "";
    private String zzk = "";

    static {
        zzaj zzajVar = new zzaj();
        zze = zzajVar;
        zzed.zzU(zzaj.class, zzajVar);
        zza = zzed.zzH(zzkc.zzf(), zzajVar, zzajVar, null, 308676116, zzho.zzk, zzaj.class);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzn = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zze;
                    }
                    return new zzah(null);
                }
                return new zzaj();
            }
            return zzed.zzR(zze, "\u0001\u0007\u0000\u0001\u0001Ǵ\u0007\u0000\u0001\u0002\u0001ᔈ\u0000\u0002ဈ\u0001\u0003\u001e\u0005ဈ\u0002\u0006ဈ\u0003\u000fᐉ\u0005Ǵဉ\u0004", new Object[]{"zzf", "zzg", "zzh", "zzi", d.f9588a, "zzj", "zzk", "zzm", "zzl"});
        }
        return Byte.valueOf(this.zzn);
    }
}
