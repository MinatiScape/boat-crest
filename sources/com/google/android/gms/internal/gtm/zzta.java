package com.google.android.gms.internal.gtm;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes8.dex */
public class zzta extends zzsz {
    public final byte[] zza;

    public zzta(byte[] bArr) {
        Objects.requireNonNull(bArr);
        this.zza = bArr;
    }

    @Override // com.google.android.gms.internal.gtm.zztd
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof zztd) && zzd() == ((zztd) obj).zzd()) {
            if (zzd() == 0) {
                return true;
            }
            if (obj instanceof zzta) {
                zzta zztaVar = (zzta) obj;
                int zzl = zzl();
                int zzl2 = zztaVar.zzl();
                if (zzl == 0 || zzl2 == 0 || zzl == zzl2) {
                    int zzd = zzd();
                    if (zzd <= zztaVar.zzd()) {
                        if (zzd <= zztaVar.zzd()) {
                            byte[] bArr = this.zza;
                            byte[] bArr2 = zztaVar.zza;
                            int zzc = zzc() + zzd;
                            int zzc2 = zzc();
                            int zzc3 = zztaVar.zzc();
                            while (zzc2 < zzc) {
                                if (bArr[zzc2] != bArr2[zzc3]) {
                                    return false;
                                }
                                zzc2++;
                                zzc3++;
                            }
                            return true;
                        }
                        int zzd2 = zztaVar.zzd();
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

    @Override // com.google.android.gms.internal.gtm.zztd
    public byte zza(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.gtm.zztd
    public byte zzb(int i) {
        return this.zza[i];
    }

    public int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.gtm.zztd
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.gms.internal.gtm.zztd
    public void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.gtm.zztd
    public final int zzf(int i, int i2, int i3) {
        return zzvi.zzd(i, this.zza, zzc(), i3);
    }

    @Override // com.google.android.gms.internal.gtm.zztd
    public final zztd zzg(int i, int i2) {
        int zzk = zztd.zzk(0, i2, zzd());
        return zzk == 0 ? zztd.zzb : new zzsx(this.zza, zzc(), zzk);
    }

    @Override // com.google.android.gms.internal.gtm.zztd
    public final String zzh(Charset charset) {
        return new String(this.zza, zzc(), zzd(), charset);
    }

    @Override // com.google.android.gms.internal.gtm.zztd
    public final void zzi(zzss zzssVar) throws IOException {
        ((zztl) zzssVar).zzc(this.zza, zzc(), zzd());
    }

    @Override // com.google.android.gms.internal.gtm.zztd
    public final boolean zzj() {
        int zzc = zzc();
        return zzyd.zzf(this.zza, zzc, zzd() + zzc);
    }
}
