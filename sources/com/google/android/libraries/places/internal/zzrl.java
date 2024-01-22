package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class zzrl extends zzrm {
    public final byte[] zzb;

    public zzrl(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zzb = bArr;
    }

    @Override // com.google.android.libraries.places.internal.zzrb
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zzrb) && zza() == ((zzrb) obj).zza()) {
            if (zza() == 0) {
                return true;
            }
            if (obj instanceof zzrl) {
                zzrl zzrlVar = (zzrl) obj;
                int zzd = zzd();
                int zzd2 = zzrlVar.zzd();
                if (zzd == 0 || zzd2 == 0 || zzd == zzd2) {
                    return zza(zzrlVar, 0, zza());
                }
                return false;
            }
            return obj.equals(this);
        }
        return false;
    }

    @Override // com.google.android.libraries.places.internal.zzrb
    public byte zza(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.libraries.places.internal.zzrb
    public byte zzb(int i) {
        return this.zzb[i];
    }

    @Override // com.google.android.libraries.places.internal.zzrb
    public final boolean zzc() {
        int zze = zze();
        return zzvf.zza(this.zzb, zze, zza() + zze);
    }

    public int zze() {
        return 0;
    }

    @Override // com.google.android.libraries.places.internal.zzrb
    public int zza() {
        return this.zzb.length;
    }

    @Override // com.google.android.libraries.places.internal.zzrb
    public final zzrb zza(int i, int i2) {
        int zzb = zzrb.zzb(0, i2, zza());
        if (zzb == 0) {
            return zzrb.zza;
        }
        return new zzri(this.zzb, zze(), zzb);
    }

    @Override // com.google.android.libraries.places.internal.zzrb
    public final void zza(zzrc zzrcVar) throws IOException {
        zzrcVar.zza(this.zzb, zze(), zza());
    }

    @Override // com.google.android.libraries.places.internal.zzrb
    public final String zza(Charset charset) {
        return new String(this.zzb, zze(), zza(), charset);
    }

    @Override // com.google.android.libraries.places.internal.zzrm
    public final boolean zza(zzrb zzrbVar, int i, int i2) {
        if (i2 <= zzrbVar.zza()) {
            if (i2 <= zzrbVar.zza()) {
                if (zzrbVar instanceof zzrl) {
                    zzrl zzrlVar = (zzrl) zzrbVar;
                    byte[] bArr = this.zzb;
                    byte[] bArr2 = zzrlVar.zzb;
                    int zze = zze() + i2;
                    int zze2 = zze();
                    int zze3 = zzrlVar.zze();
                    while (zze2 < zze) {
                        if (bArr[zze2] != bArr2[zze3]) {
                            return false;
                        }
                        zze2++;
                        zze3++;
                    }
                    return true;
                }
                return zzrbVar.zza(0, i2).equals(zza(0, i2));
            }
            int zza = zzrbVar.zza();
            StringBuilder sb = new StringBuilder(59);
            sb.append("Ran off end of other: 0, ");
            sb.append(i2);
            sb.append(", ");
            sb.append(zza);
            throw new IllegalArgumentException(sb.toString());
        }
        int zza2 = zza();
        StringBuilder sb2 = new StringBuilder(40);
        sb2.append("Length too large: ");
        sb2.append(i2);
        sb2.append(zza2);
        throw new IllegalArgumentException(sb2.toString());
    }

    @Override // com.google.android.libraries.places.internal.zzrb
    public final int zza(int i, int i2, int i3) {
        return zzsg.zza(i, this.zzb, zze(), i3);
    }
}
