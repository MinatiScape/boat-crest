package com.google.android.gms.internal.auth;

import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes6.dex */
public class u0 extends t0 {
    public final byte[] zza;

    public u0(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.auth.zzee
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzee) && zzd() == ((zzee) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof u0) {
                u0 u0Var = (u0) obj;
                int zzj = zzj();
                int zzj2 = u0Var.zzj();
                if (zzj == 0 || zzj2 == 0 || zzj == zzj2) {
                    int zzd = zzd();
                    if (zzd <= u0Var.zzd()) {
                        if (zzd <= u0Var.zzd()) {
                            byte[] bArr = this.zza;
                            byte[] bArr2 = u0Var.zza;
                            u0Var.zzc();
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
                        throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + u0Var.zzd());
                    }
                    throw new IllegalArgumentException("Length too large: " + zzd + zzd());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.auth.zzee
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.auth.zzee
    public byte zzb(int i) {
        return this.zza[i];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.auth.zzee
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.auth.zzee
    public final int zze(int i, int i2, int i3) {
        return zzez.a(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.auth.zzee
    public final zzee zzf(int i, int i2) {
        int zzi = zzee.zzi(0, i2, zzd());
        return zzi == 0 ? zzee.zzb : new s0(this.zza, 0, zzi);
    }

    @Override // com.google.android.gms.internal.auth.zzee
    public final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.gms.internal.auth.zzee
    public final boolean zzh() {
        return y2.d(this.zza, 0, zzd());
    }
}
