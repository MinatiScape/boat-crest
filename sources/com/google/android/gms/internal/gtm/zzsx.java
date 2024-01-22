package com.google.android.gms.internal.gtm;
/* loaded from: classes8.dex */
public final class zzsx extends zzta {
    private final int zzc;
    private final int zzd;

    public zzsx(byte[] bArr, int i, int i2) {
        super(bArr);
        zztd.zzk(i, i + i2, bArr.length);
        this.zzc = i;
        this.zzd = i2;
    }

    @Override // com.google.android.gms.internal.gtm.zzta, com.google.android.gms.internal.gtm.zztd
    public final byte zza(int i) {
        int i2 = this.zzd;
        if (((i2 - (i + 1)) | i) < 0) {
            if (i < 0) {
                StringBuilder sb = new StringBuilder(22);
                sb.append("Index < 0: ");
                sb.append(i);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder(40);
            sb2.append("Index > length: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new ArrayIndexOutOfBoundsException(sb2.toString());
        }
        return this.zza[this.zzc + i];
    }

    @Override // com.google.android.gms.internal.gtm.zzta, com.google.android.gms.internal.gtm.zztd
    public final byte zzb(int i) {
        return this.zza[this.zzc + i];
    }

    @Override // com.google.android.gms.internal.gtm.zzta
    public final int zzc() {
        return this.zzc;
    }

    @Override // com.google.android.gms.internal.gtm.zzta, com.google.android.gms.internal.gtm.zztd
    public final int zzd() {
        return this.zzd;
    }

    @Override // com.google.android.gms.internal.gtm.zzta, com.google.android.gms.internal.gtm.zztd
    public final void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, this.zzc, bArr, 0, i3);
    }
}
