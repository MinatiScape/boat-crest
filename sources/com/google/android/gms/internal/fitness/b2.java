package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public final class b2 extends h2 {
    private final int zzuf;
    private final int zzug;

    public b2(byte[] bArr, int i, int i2) {
        super(bArr);
        zzfx.zzc(i, i + i2, bArr.length);
        this.zzuf = i;
        this.zzug = i2;
    }

    @Override // com.google.android.gms.internal.fitness.h2, com.google.android.gms.internal.fitness.zzfx
    public final int size() {
        return this.zzug;
    }

    @Override // com.google.android.gms.internal.fitness.h2
    public final int zzay() {
        return this.zzuf;
    }

    @Override // com.google.android.gms.internal.fitness.h2, com.google.android.gms.internal.fitness.zzfx
    public final byte zzj(int i) {
        int size = size();
        if (((size - (i + 1)) | i) < 0) {
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
            sb2.append(size);
            throw new ArrayIndexOutOfBoundsException(sb2.toString());
        }
        return this.zzui[this.zzuf + i];
    }

    @Override // com.google.android.gms.internal.fitness.h2, com.google.android.gms.internal.fitness.zzfx
    public final byte zzk(int i) {
        return this.zzui[this.zzuf + i];
    }
}
