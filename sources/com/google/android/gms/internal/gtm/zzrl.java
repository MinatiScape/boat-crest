package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.io.InputStream;
/* loaded from: classes8.dex */
public final class zzrl extends zzuz<zzrl, zzrk> implements zzwl {
    private static final zzrl zza;
    private int zze;
    private long zzf;
    private zzaa zzg;
    private zzai zzh;
    private byte zzi = 2;

    static {
        zzrl zzrlVar = new zzrl();
        zza = zzrlVar;
        zzuz.zzak(zzrl.class, zzrlVar);
    }

    public static zzrk zze() {
        return zza.zzY();
    }

    public static zzrl zzg(InputStream inputStream, zzuj zzujVar) throws IOException {
        return (zzrl) zzuz.zzac(zza, inputStream, zzujVar);
    }

    public static /* synthetic */ void zzh(zzrl zzrlVar, long j) {
        zzrlVar.zze |= 1;
        zzrlVar.zzf = j;
    }

    public static /* synthetic */ void zzi(zzrl zzrlVar, zzaa zzaaVar) {
        zzaaVar.getClass();
        zzrlVar.zzg = zzaaVar;
        zzrlVar.zze |= 2;
    }

    public static /* synthetic */ void zzj(zzrl zzrlVar, zzai zzaiVar) {
        zzaiVar.getClass();
        zzrlVar.zzh = zzaiVar;
        zzrlVar.zze |= 4;
    }

    public final long zza() {
        return this.zzf;
    }

    @Override // com.google.android.gms.internal.gtm.zzuz
    public final Object zzb(int i, Object obj, Object obj2) {
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
                    return new zzrk(null);
                }
                return new zzrl();
            }
            return zzuz.zzaj(zza, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0003\u0001ᔂ\u0000\u0002ᔉ\u0001\u0003ᐉ\u0002", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        return Byte.valueOf(this.zzi);
    }

    public final zzaa zzc() {
        zzaa zzaaVar = this.zzg;
        return zzaaVar == null ? zzaa.zzl() : zzaaVar;
    }

    public final zzai zzd() {
        zzai zzaiVar = this.zzh;
        return zzaiVar == null ? zzai.zzf() : zzaiVar;
    }

    public final boolean zzk() {
        return (this.zze & 2) != 0;
    }

    public final boolean zzl() {
        return (this.zze & 4) != 0;
    }
}
