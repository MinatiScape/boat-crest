package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
final class zzfc extends zzff {
    private final int zzc;

    public zzfc(byte[] bArr, int i, int i2) {
        super(bArr);
        zzfi.zzk(0, i2, bArr.length);
        this.zzc = i2;
    }

    @Override // com.google.android.recaptcha.internal.zzff, com.google.android.recaptcha.internal.zzfi
    public final byte zza(int i) {
        int i2 = this.zzc;
        if (((i2 - (i + 1)) | i) < 0) {
            if (i < 0) {
                throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
            }
            throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
        }
        return this.zza[i];
    }

    @Override // com.google.android.recaptcha.internal.zzff, com.google.android.recaptcha.internal.zzfi
    public final byte zzb(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.recaptcha.internal.zzff
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.recaptcha.internal.zzff, com.google.android.recaptcha.internal.zzfi
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.recaptcha.internal.zzff, com.google.android.recaptcha.internal.zzfi
    public final void zze(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zza, 0, bArr, 0, i3);
    }
}
