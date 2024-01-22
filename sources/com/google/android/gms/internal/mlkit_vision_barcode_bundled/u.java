package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes8.dex */
public class u extends t {
    public final byte[] zza;

    public u(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzdb) && zzd() == ((zzdb) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof u) {
                u uVar = (u) obj;
                int zzp = zzp();
                int zzp2 = uVar.zzp();
                if (zzp == 0 || zzp2 == 0 || zzp == zzp2) {
                    return zzg(uVar, 0, zzd());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public byte zzb(int i) {
        return this.zza[i];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, i, bArr, i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.t
    public final boolean zzg(zzdb zzdbVar, int i, int i2) {
        if (i2 <= zzdbVar.zzd()) {
            int i3 = i + i2;
            if (i3 <= zzdbVar.zzd()) {
                if (zzdbVar instanceof u) {
                    u uVar = (u) zzdbVar;
                    byte[] bArr = this.zza;
                    byte[] bArr2 = uVar.zza;
                    int zzc = zzc() + i2;
                    int zzc2 = zzc();
                    int zzc3 = uVar.zzc() + i;
                    while (zzc2 < zzc) {
                        if (bArr[zzc2] != bArr2[zzc3]) {
                            return false;
                        }
                        zzc2++;
                        zzc3++;
                    }
                    return true;
                }
                return zzdbVar.zzk(i, i3).equals(zzk(0, i2));
            }
            int zzd = zzdbVar.zzd();
            throw new IllegalArgumentException("Ran off end of other: " + i + ", " + i2 + ", " + zzd);
        }
        int zzd2 = zzd();
        throw new IllegalArgumentException("Length too large: " + i2 + zzd2);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int zzi(int i, int i2, int i3) {
        return zzem.a(i, this.zza, zzc() + i2, i3);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final int zzj(int i, int i2, int i3) {
        int zzc = zzc() + i2;
        return l2.f(i, this.zza, zzc, i3 + zzc);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final zzdb zzk(int i, int i2) {
        int zzo = zzdb.zzo(i, i2, zzd());
        return zzo == 0 ? zzdb.zzb : new s(this.zza, zzc() + i, zzo);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final String zzl(Charset charset) {
        return new String(this.zza, zzc(), zzd(), charset);
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final void zzm(zzcr zzcrVar) throws IOException {
        ((y) zzcrVar).e(this.zza, zzc(), zzd());
    }

    @Override // com.google.android.gms.internal.mlkit_vision_barcode_bundled.zzdb
    public final boolean zzn() {
        int zzc = zzc();
        return l2.h(this.zza, zzc, zzd() + zzc);
    }
}
