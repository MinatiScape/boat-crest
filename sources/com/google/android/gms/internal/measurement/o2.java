package com.google.android.gms.internal.measurement;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes8.dex */
public class o2 extends n2 {
    public final byte[] zza;

    public o2(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zziy) && zzd() == ((zziy) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof o2) {
                o2 o2Var = (o2) obj;
                int zzk = zzk();
                int zzk2 = o2Var.zzk();
                if (zzk == 0 || zzk2 == 0 || zzk == zzk2) {
                    int zzd = zzd();
                    if (zzd <= o2Var.zzd()) {
                        if (zzd <= o2Var.zzd()) {
                            byte[] bArr = this.zza;
                            byte[] bArr2 = o2Var.zza;
                            o2Var.zzc();
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
                        int zzd2 = o2Var.zzd();
                        StringBuilder sb = new StringBuilder(59);
                        sb.append("Ran off end of other: 0, ");
                        sb.append(zzd);
                        sb.append(", ");
                        sb.append(zzd2);
                        throw new IllegalArgumentException(sb.toString());
                    }
                    int zzd3 = zzd();
                    StringBuilder sb2 = new StringBuilder(40);
                    sb2.append("Length too large: ");
                    sb2.append(zzd);
                    sb2.append(zzd3);
                    throw new IllegalArgumentException(sb2.toString());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    public byte zzb(int i) {
        return this.zza[i];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    public final int zze(int i, int i2, int i3) {
        return zzkh.a(i, this.zza, 0, i3);
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    public final zziy zzf(int i, int i2) {
        int zzj = zziy.zzj(0, i2, zzd());
        return zzj == 0 ? zziy.zzb : new m2(this.zza, 0, zzj);
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    public final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    public final void zzh(zzin zzinVar) throws IOException {
        ((r2) zzinVar).e(this.zza, 0, zzd());
    }

    @Override // com.google.android.gms.internal.measurement.zziy
    public final boolean zzi() {
        return y4.f(this.zza, 0, zzd());
    }
}
