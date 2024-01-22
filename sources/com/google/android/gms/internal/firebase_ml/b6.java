package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public final class b6 extends h6 {
    private final int zzchs;
    private final int zzcht;

    public b6(byte[] bArr, int i, int i2) {
        super(bArr);
        zzvv.zzd(i, i + i2, bArr.length);
        this.zzchs = i;
        this.zzcht = i2;
    }

    @Override // com.google.android.gms.internal.firebase_ml.h6, com.google.android.gms.internal.firebase_ml.zzvv
    public final int size() {
        return this.zzcht;
    }

    @Override // com.google.android.gms.internal.firebase_ml.h6, com.google.android.gms.internal.firebase_ml.zzvv
    public final void zzb(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.bytes, zztu() + i, bArr, i2, i3);
    }

    @Override // com.google.android.gms.internal.firebase_ml.h6, com.google.android.gms.internal.firebase_ml.zzvv
    public final byte zzcw(int i) {
        zzvv.zzg(i, size());
        return this.bytes[this.zzchs + i];
    }

    @Override // com.google.android.gms.internal.firebase_ml.h6, com.google.android.gms.internal.firebase_ml.zzvv
    public final byte zzcx(int i) {
        return this.bytes[this.zzchs + i];
    }

    @Override // com.google.android.gms.internal.firebase_ml.h6
    public final int zztu() {
        return this.zzchs;
    }
}
