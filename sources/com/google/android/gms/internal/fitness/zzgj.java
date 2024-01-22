package com.google.android.gms.internal.fitness;
/* loaded from: classes8.dex */
public abstract class zzgj {
    public zzgj() {
    }

    public static zzgj a(byte[] bArr, int i, int i2, boolean z) {
        j2 j2Var = new j2(bArr, i2);
        try {
            j2Var.zzm(i2);
            return j2Var;
        } catch (zzhk e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int zzbb();

    public abstract int zzm(int i) throws zzhk;
}
