package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
/* loaded from: classes10.dex */
public final class zzf extends zzed implements zzfp {
    private static final zzf zza;
    private zzel zzd = zzed.zzO();

    static {
        zzf zzfVar = new zzf();
        zza = zzfVar;
        zzed.zzU(zzf.class, zzfVar);
    }

    public static /* synthetic */ zzf k() {
        return zza;
    }

    public static /* synthetic */ void l(zzf zzfVar, zzc zzcVar) {
        zzcVar.getClass();
        zzel zzelVar = zzfVar.zzd;
        if (!zzelVar.zzc()) {
            zzfVar.zzd = zzed.zzP(zzelVar);
        }
        zzfVar.zzd.add(zzcVar);
    }

    public static zze zza() {
        return (zze) zza.zzF();
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
                    return new zze(null);
                }
                return new zzf();
            }
            return zzed.zzR(zza, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"zzd", zzc.class});
        }
        return (byte) 1;
    }
}
