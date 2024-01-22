package com.google.photos.vision.barhopper;

import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzci;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzej;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzel;
import com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzfp;
import java.util.List;
/* loaded from: classes10.dex */
public final class zzc extends zzed implements zzfp {
    private static final zzc zza;
    private int zzd;
    private int zze;
    private zzdb zzf;
    private String zzg;
    private int zzh;
    private zzr zzi;
    private zzy zzj;
    private zzci zzk;
    private zzag zzl;
    private zzao zzm;
    private zzaj zzn;
    private zzac zzo;
    private zzp zzp;
    private zzt zzq;
    private zzl zzr;
    private zzel zzs;
    private zzej zzt;
    private String zzu;
    private zzel zzv;
    private boolean zzw;
    private double zzx;
    private zzdb zzy;
    private byte zzz = 2;

    static {
        zzc zzcVar = new zzc();
        zza = zzcVar;
        zzed.zzU(zzc.class, zzcVar);
    }

    public zzc() {
        zzdb zzdbVar = zzdb.zzb;
        this.zzf = zzdbVar;
        this.zzg = "";
        this.zzs = zzed.zzO();
        this.zzt = zzed.zzN();
        this.zzu = "";
        this.zzv = zzed.zzO();
        this.zzw = true;
        this.zzy = zzdbVar;
    }

    public static /* synthetic */ void l(zzc zzcVar, int i, zzae zzaeVar) {
        zzaeVar.getClass();
        zzel zzelVar = zzcVar.zzs;
        if (!zzelVar.zzc()) {
            zzcVar.zzs = zzed.zzP(zzelVar);
        }
        zzcVar.zzs.set(i, zzaeVar);
    }

    public final int zzA() {
        int zza2 = zzi.zza(this.zzh);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }

    public final int zza() {
        return this.zzs.size();
    }

    public final zzci zzb() {
        zzci zzciVar = this.zzk;
        return zzciVar == null ? zzci.zzb() : zzciVar;
    }

    public final zzp zzd() {
        zzp zzpVar = this.zzp;
        return zzpVar == null ? zzp.zzd() : zzpVar;
    }

    public final zzr zze() {
        zzr zzrVar = this.zzi;
        return zzrVar == null ? zzr.zzc() : zzrVar;
    }

    public final zzt zzf() {
        zzt zztVar = this.zzq;
        return zztVar == null ? zzt.zzb() : zztVar;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzed
    public final Object zzg(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 != 0) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            this.zzz = obj == null ? (byte) 0 : (byte) 1;
                            return null;
                        }
                        return zza;
                    }
                    return new zzb(null);
                }
                return new zzc();
            }
            return zzed.zzR(zza, "\u0001\u0015\u0000\u0001\u0001\u0015\u0015\u0000\u0003\u000b\u0001ᔌ\u0000\u0002ᔊ\u0001\u0003ᔈ\u0002\u0004ᔌ\u0003\u0005ᐉ\u0004\u0006ဉ\u0005\u0007ဉ\u0006\bᐉ\u0007\tᐉ\b\nᐉ\t\u000bЛ\fဈ\u000e\rЛ\u000eည\u0011\u000fᐉ\n\u0010ဉ\u000b\u0011ဉ\f\u0012\u0016\u0013ဉ\r\u0014ဇ\u000f\u0015က\u0010", new Object[]{"zzd", "zze", f.f11652a, "zzf", "zzg", "zzh", h.f11653a, "zzi", "zzj", "zzk", "zzl", "zzm", "zzn", "zzs", zzae.class, "zzu", "zzv", zzae.class, "zzy", "zzo", "zzp", "zzq", "zzt", "zzr", "zzw", "zzx"});
        }
        return Byte.valueOf(this.zzz);
    }

    public final zzy zzh() {
        zzy zzyVar = this.zzj;
        return zzyVar == null ? zzy.zzb() : zzyVar;
    }

    public final zzac zzi() {
        zzac zzacVar = this.zzo;
        return zzacVar == null ? zzac.zzd() : zzacVar;
    }

    public final zzag zzj() {
        zzag zzagVar = this.zzl;
        return zzagVar == null ? zzag.zzb() : zzagVar;
    }

    public final zzaj zzk() {
        zzaj zzajVar = this.zzn;
        return zzajVar == null ? zzaj.zzb() : zzajVar;
    }

    public final zzao zzl() {
        zzao zzaoVar = this.zzm;
        return zzaoVar == null ? zzao.zzb() : zzaoVar;
    }

    public final zzdb zzm() {
        return this.zzf;
    }

    public final String zzn() {
        return this.zzg;
    }

    public final List zzo() {
        return this.zzs;
    }

    public final boolean zzq() {
        return (this.zzd & 2048) != 0;
    }

    public final boolean zzr() {
        return (this.zzd & 16) != 0;
    }

    public final boolean zzs() {
        return (this.zzd & 4096) != 0;
    }

    public final boolean zzt() {
        return (this.zzd & 32) != 0;
    }

    public final boolean zzu() {
        return (this.zzd & 1024) != 0;
    }

    public final boolean zzv() {
        return (this.zzd & 64) != 0;
    }

    public final boolean zzw() {
        return (this.zzd & 128) != 0;
    }

    public final boolean zzx() {
        return (this.zzd & 512) != 0;
    }

    public final boolean zzy() {
        return (this.zzd & 256) != 0;
    }

    public final int zzz() {
        int zza2 = zzf.zza(this.zze);
        if (zza2 == 0) {
            return 1;
        }
        return zza2;
    }
}
