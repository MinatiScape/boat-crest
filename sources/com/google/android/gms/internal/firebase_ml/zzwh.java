package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public abstract class zzwh {
    public zzwh() {
    }

    public static zzwh a(byte[] bArr, int i, int i2, boolean z) {
        j6 j6Var = new j6(bArr, 0, i2, false);
        try {
            j6Var.zzcz(i2);
            return j6Var;
        } catch (zzxk e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzda(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long zzv(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public abstract int zzcz(int i) throws zzxk;

    public abstract int zztx();
}
