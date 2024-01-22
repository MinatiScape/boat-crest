package com.google.android.gms.internal.auth;
/* loaded from: classes6.dex */
public final class s0 extends u0 {
    private final int zzc;

    public s0(byte[] bArr, int i, int i2) {
        super(bArr);
        zzee.zzi(0, i2, bArr.length);
        this.zzc = i2;
    }

    @Override // com.google.android.gms.internal.auth.u0, com.google.android.gms.internal.auth.zzee
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

    @Override // com.google.android.gms.internal.auth.u0, com.google.android.gms.internal.auth.zzee
    public final byte zzb(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.gms.internal.auth.u0
    public final int zzc() {
        return 0;
    }

    @Override // com.google.android.gms.internal.auth.u0, com.google.android.gms.internal.auth.zzee
    public final int zzd() {
        return this.zzc;
    }
}
