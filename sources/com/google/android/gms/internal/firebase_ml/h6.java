package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes7.dex */
public class h6 extends e6 {
    public final byte[] bytes;

    public h6(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.bytes = bArr;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzvv) && size() == ((zzvv) obj).size()) {
            if (size() == 0) {
                return true;
            }
            if (obj instanceof h6) {
                h6 h6Var = (h6) obj;
                int zztt = zztt();
                int zztt2 = h6Var.zztt();
                if (zztt == 0 || zztt2 == 0 || zztt == zztt2) {
                    return zza(h6Var, 0, size());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public int size() {
        return this.bytes.length;
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final void zza(zzvs zzvsVar) throws IOException {
        zzvsVar.zzb(this.bytes, zztu(), size());
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public void zzb(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.bytes, i, bArr, i2, i3);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final int zzc(int i, int i2, int i3) {
        return zzxd.c(i, this.bytes, zztu() + i2, i3);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public byte zzcw(int i) {
        return this.bytes[i];
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public byte zzcx(int i) {
        return this.bytes[i];
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final zzvv zzf(int i, int i2) {
        int zzd = zzvv.zzd(i, i2, size());
        if (zzd == 0) {
            return zzvv.zzchp;
        }
        return new b6(this.bytes, zztu() + i, zzd);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final boolean zztq() {
        int zztu = zztu();
        return e.h(this.bytes, zztu, size() + zztu);
    }

    public int zztu() {
        return 0;
    }

    @Override // com.google.android.gms.internal.firebase_ml.e6
    public final boolean zza(zzvv zzvvVar, int i, int i2) {
        if (i2 <= zzvvVar.size()) {
            int i3 = i + i2;
            if (i3 <= zzvvVar.size()) {
                if (zzvvVar instanceof h6) {
                    h6 h6Var = (h6) zzvvVar;
                    byte[] bArr = this.bytes;
                    byte[] bArr2 = h6Var.bytes;
                    int zztu = zztu() + i2;
                    int zztu2 = zztu();
                    int zztu3 = h6Var.zztu() + i;
                    while (zztu2 < zztu) {
                        if (bArr[zztu2] != bArr2[zztu3]) {
                            return false;
                        }
                        zztu2++;
                        zztu3++;
                    }
                    return true;
                }
                return zzvvVar.zzf(i, i3).equals(zzf(0, i2));
            }
            int size = zzvvVar.size();
            StringBuilder sb = new StringBuilder(59);
            sb.append("Ran off end of other: ");
            sb.append(i);
            sb.append(", ");
            sb.append(i2);
            sb.append(", ");
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        int size2 = size();
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Length too large: ");
        sb2.append(i2);
        sb2.append(size2);
        throw new IllegalArgumentException(sb2.toString());
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final String zzb(Charset charset) {
        return new String(this.bytes, zztu(), size(), charset);
    }

    @Override // com.google.android.gms.internal.firebase_ml.zzvv
    public final int zzb(int i, int i2, int i3) {
        int zztu = zztu() + i2;
        return e.b(i, this.bytes, zztu, i3 + zztu);
    }
}
