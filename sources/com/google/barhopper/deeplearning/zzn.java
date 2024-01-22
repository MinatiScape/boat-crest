package com.google.barhopper.deeplearning;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
/* loaded from: classes10.dex */
public final class zzn extends zzed implements zzfp {
    private static final zzn zza;
    private int zzd;
    private String zze = "";
    private zzdb zzf;
    private String zzg;
    private zzdb zzh;
    private float zzi;
    private float zzj;
    private float zzk;
    private float zzl;
    private int zzm;

    static {
        zzn zznVar = new zzn();
        zza = zznVar;
        zzed.zzU(zzn.class, zznVar);
    }

    public zzn() {
        zzdb zzdbVar = zzdb.zzb;
        this.zzf = zzdbVar;
        this.zzg = "";
        this.zzh = zzdbVar;
        this.zzi = 0.25f;
        this.zzj = 0.25f;
        this.zzk = 0.5f;
        this.zzl = 0.85f;
        this.zzm = 1;
    }

    public static /* synthetic */ void l(zzn zznVar, zzdb zzdbVar) {
        zzdbVar.getClass();
        zznVar.zzd |= 2;
        zznVar.zzf = zzdbVar;
    }

    public static /* synthetic */ void m(zzn zznVar, zzdb zzdbVar) {
        zzdbVar.getClass();
        zznVar.zzd |= 8;
        zznVar.zzh = zzdbVar;
    }

    public static zzm zza() {
        return (zzm) zza.zzF();
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
                    return new zzm(null);
                }
                return new zzn();
            }
            return zzed.zzR(zza, "\u0001\t\u0000\u0001\u0001\t\t\u0000\u0000\u0000\u0001ဈ\u0000\u0002ည\u0001\u0003ဈ\u0002\u0004ည\u0003\u0005ခ\u0004\u0006ခ\u0005\u0007ခ\u0006\bခ\u0007\tင\b", new Object[]{"zzd", "zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl", "zzm"});
        }
        return (byte) 1;
    }
}
