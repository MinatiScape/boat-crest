package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes10.dex */
public class s1 extends t1 {
    public final byte[] zzsk;

    public s1(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zzsk = bArr;
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzfh) && size() == ((zzfh) obj).size()) {
            if (size() == 0) {
                return true;
            }
            if (obj instanceof s1) {
                s1 s1Var = (s1) obj;
                int zzet = zzet();
                int zzet2 = s1Var.zzet();
                if (zzet == 0 || zzet2 == 0 || zzet == zzet2) {
                    return zza(s1Var, 0, size());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public int size() {
        return this.zzsk.length;
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzsk, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public byte zzao(int i) {
        return this.zzsk[i];
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public byte zzap(int i) {
        return this.zzsk[i];
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public final int zzb(int i, int i2, int i3) {
        return zzgt.b(i, this.zzsk, zzeu(), i3);
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public final boolean zzes() {
        int zzeu = zzeu();
        return m4.g(this.zzsk, zzeu, size() + zzeu);
    }

    public int zzeu() {
        return 0;
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public final zzfh zzf(int i, int i2) {
        int zzc = zzfh.zzc(0, i2, size());
        if (zzc == 0) {
            return zzfh.zzsd;
        }
        return new q1(this.zzsk, zzeu(), zzc);
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public final void zza(zzfi zzfiVar) throws IOException {
        zzfiVar.zzc(this.zzsk, zzeu(), size());
    }

    @Override // com.google.android.gms.internal.vision.zzfh
    public final String zza(Charset charset) {
        return new String(this.zzsk, zzeu(), size(), charset);
    }

    @Override // com.google.android.gms.internal.vision.t1
    public final boolean zza(zzfh zzfhVar, int i, int i2) {
        if (i2 <= zzfhVar.size()) {
            if (i2 <= zzfhVar.size()) {
                if (zzfhVar instanceof s1) {
                    s1 s1Var = (s1) zzfhVar;
                    byte[] bArr = this.zzsk;
                    byte[] bArr2 = s1Var.zzsk;
                    int zzeu = zzeu() + i2;
                    int zzeu2 = zzeu();
                    int zzeu3 = s1Var.zzeu();
                    while (zzeu2 < zzeu) {
                        if (bArr[zzeu2] != bArr2[zzeu3]) {
                            return false;
                        }
                        zzeu2++;
                        zzeu3++;
                    }
                    return true;
                }
                return zzfhVar.zzf(0, i2).equals(zzf(0, i2));
            }
            int size = zzfhVar.size();
            StringBuilder sb = new StringBuilder(59);
            sb.append("Ran off end of other: 0, ");
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
}
