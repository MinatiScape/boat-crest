package com.google.photos.vision.barhopper;

import androidx.annotation.NonNull;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzeo;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
import java.util.List;
/* loaded from: classes10.dex */
public final class BarhopperProto$BarhopperResponse extends zzed<BarhopperProto$BarhopperResponse, zzj> implements zzfp {
    private static final BarhopperProto$BarhopperResponse zza;
    private int zzd;
    private int zzf;
    private byte zzi = 2;
    private zzel zze = zzed.zzO();
    private String zzg = "";
    private zzdb zzh = zzdb.zzb;

    static {
        BarhopperProto$BarhopperResponse barhopperProto$BarhopperResponse = new BarhopperProto$BarhopperResponse();
        zza = barhopperProto$BarhopperResponse;
        zzed.zzU(BarhopperProto$BarhopperResponse.class, barhopperProto$BarhopperResponse);
    }

    public static BarhopperProto$BarhopperResponse zzb(byte[] bArr, zzdo zzdoVar) throws zzeo {
        return (BarhopperProto$BarhopperResponse) zzed.zzK(zza, bArr, zzdoVar);
    }

    @NonNull
    public final List zzc() {
        return this.zze;
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
                            this.zzi = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzj(null);
                }
                return new BarhopperProto$BarhopperResponse();
            }
            return zzed.zzR(zza, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0001\u0002\u0001Л\u0002ᔌ\u0000\u0003ဈ\u0001\u0004ည\u0002", new Object[]{"zzd", "zze", zzc.class, "zzf", b.f11650a, "zzg", "zzh"});
        }
        return Byte.valueOf(this.zzi);
    }
}
