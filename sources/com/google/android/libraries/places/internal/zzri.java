package com.google.android.libraries.places.internal;
/* loaded from: classes10.dex */
final class zzri extends zzrl {
    private final int zzc;
    private final int zzd;

    public zzri(byte[] bArr, int i, int i2) {
        super(bArr);
        zzrb.zzb(i, i + i2, bArr.length);
        this.zzc = i;
        this.zzd = i2;
    }

    @Override // com.google.android.libraries.places.internal.zzrl, com.google.android.libraries.places.internal.zzrb
    public final byte zza(int i) {
        int zza = zza();
        if (((zza - (i + 1)) | i) < 0) {
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
            sb2.append(zza);
            throw new ArrayIndexOutOfBoundsException(sb2.toString());
        }
        return this.zzb[this.zzc + i];
    }

    @Override // com.google.android.libraries.places.internal.zzrl, com.google.android.libraries.places.internal.zzrb
    public final byte zzb(int i) {
        return this.zzb[this.zzc + i];
    }

    @Override // com.google.android.libraries.places.internal.zzrl
    public final int zze() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzrl, com.google.android.libraries.places.internal.zzrb
    public final int zza() {
        return this.zzd;
    }
}
