package com.google.android.gms.internal.vision;
/* loaded from: classes10.dex */
public final class q1 extends s1 {
    private final int zzsh;
    private final int zzsi;

    public q1(byte[] bArr, int i, int i2) {
        super(bArr);
        zzfh.zzc(i, i + i2, bArr.length);
        this.zzsh = i;
        this.zzsi = i2;
    }

    @Override // com.google.android.gms.internal.vision.s1, com.google.android.gms.internal.vision.zzfh
    public final int size() {
        return this.zzsi;
    }

    @Override // com.google.android.gms.internal.vision.s1, com.google.android.gms.internal.vision.zzfh
    public final void zza(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzsk, zzeu(), bArr, 0, i3);
    }

    @Override // com.google.android.gms.internal.vision.s1, com.google.android.gms.internal.vision.zzfh
    public final byte zzao(int i) {
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
        return this.zzsk[this.zzsh + i];
    }

    @Override // com.google.android.gms.internal.vision.s1, com.google.android.gms.internal.vision.zzfh
    public final byte zzap(int i) {
        return this.zzsk[this.zzsh + i];
    }

    @Override // com.google.android.gms.internal.vision.s1
    public final int zzeu() {
        return this.zzsh;
    }
}
