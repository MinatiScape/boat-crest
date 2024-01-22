package com.google.android.recaptcha.internal;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class zzff extends zzfe {
    public final byte[] zza;

    public zzff(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.recaptcha.internal.zzfi
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzfi) && zzd() == ((zzfi) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof zzff) {
                zzff zzffVar = (zzff) obj;
                int zzl = zzl();
                int zzl2 = zzffVar.zzl();
                if (zzl == 0 || zzl2 == 0 || zzl == zzl2) {
                    int zzd = zzd();
                    if (zzd <= zzffVar.zzd()) {
                        if (zzd <= zzffVar.zzd()) {
                            byte[] bArr = this.zza;
                            byte[] bArr2 = zzffVar.zza;
                            zzffVar.zzc();
                            int i = 0;
                            int i2 = 0;
                            while (i < zzd) {
                                if (bArr[i] != bArr2[i2]) {
                                    return false;
                                }
                                i++;
                                i2++;
                            }
                            return true;
                        }
                        throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzffVar.zzd());
                    }
                    throw new IllegalArgumentException("Length too large: " + zzd + zzd());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.recaptcha.internal.zzfi
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.recaptcha.internal.zzfi
    public byte zzb(int i) {
        return this.zza[i];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.recaptcha.internal.zzfi
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.recaptcha.internal.zzfi
    public void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    @Override // com.google.android.recaptcha.internal.zzfi
    public final int zzf(int i, int i2, int i3) {
        return zzhn.zzb(i, this.zza, 0, i3);
    }

    @Override // com.google.android.recaptcha.internal.zzfi
    public final zzfi zzg(int i, int i2) {
        int zzk = zzfi.zzk(0, i2, zzd());
        return zzk == 0 ? zzfi.zzb : new zzfc(this.zza, 0, zzk);
    }

    @Override // com.google.android.recaptcha.internal.zzfi
    public final String zzh(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.recaptcha.internal.zzfi
    public final void zzi(zzey zzeyVar) throws IOException {
        ((zzfq) zzeyVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.recaptcha.internal.zzfi
    public final boolean zzj() {
        return zzkl.zzf(this.zza, 0, zzd());
    }
}
