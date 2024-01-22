package com.google.android.gms.internal.fitness;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes8.dex */
public class h2 extends e2 {
    public final byte[] zzui;

    public h2(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zzui = bArr;
    }

    @Override // com.google.android.gms.internal.fitness.zzfx
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzfx) && size() == ((zzfx) obj).size()) {
            if (size() == 0) {
                return true;
            }
            if (obj instanceof h2) {
                h2 h2Var = (h2) obj;
                int zzax = zzax();
                int zzax2 = h2Var.zzax();
                if (zzax == 0 || zzax2 == 0 || zzax == zzax2) {
                    return zza(h2Var, 0, size());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.fitness.zzfx
    public int size() {
        return this.zzui.length;
    }

    @Override // com.google.android.gms.internal.fitness.zzfx
    public final void zza(zzfu zzfuVar) throws IOException {
        zzfuVar.zza(this.zzui, zzay(), size());
    }

    @Override // com.google.android.gms.internal.fitness.zzfx
    public final boolean zzaw() {
        int zzay = zzay();
        return u4.f(this.zzui, zzay, size() + zzay);
    }

    public int zzay() {
        return 0;
    }

    @Override // com.google.android.gms.internal.fitness.zzfx
    public final int zzb(int i, int i2, int i3) {
        return zzhc.b(i, this.zzui, zzay(), i3);
    }

    @Override // com.google.android.gms.internal.fitness.zzfx
    public final zzfx zzd(int i, int i2) {
        int zzc = zzfx.zzc(0, i2, size());
        if (zzc == 0) {
            return zzfx.zzub;
        }
        return new b2(this.zzui, zzay(), zzc);
    }

    @Override // com.google.android.gms.internal.fitness.zzfx
    public byte zzj(int i) {
        return this.zzui[i];
    }

    @Override // com.google.android.gms.internal.fitness.zzfx
    public byte zzk(int i) {
        return this.zzui[i];
    }

    @Override // com.google.android.gms.internal.fitness.zzfx
    public final String zza(Charset charset) {
        return new String(this.zzui, zzay(), size(), charset);
    }

    @Override // com.google.android.gms.internal.fitness.e2
    public final boolean zza(zzfx zzfxVar, int i, int i2) {
        if (i2 <= zzfxVar.size()) {
            if (i2 <= zzfxVar.size()) {
                if (zzfxVar instanceof h2) {
                    h2 h2Var = (h2) zzfxVar;
                    byte[] bArr = this.zzui;
                    byte[] bArr2 = h2Var.zzui;
                    int zzay = zzay() + i2;
                    int zzay2 = zzay();
                    int zzay3 = h2Var.zzay();
                    while (zzay2 < zzay) {
                        if (bArr[zzay2] != bArr2[zzay3]) {
                            return false;
                        }
                        zzay2++;
                        zzay3++;
                    }
                    return true;
                }
                return zzfxVar.zzd(0, i2).equals(zzd(0, i2));
            }
            int size = zzfxVar.size();
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
