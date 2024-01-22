package com.google.android.gms.internal.clearcut;
/* loaded from: classes7.dex */
public abstract class zzbk {
    public zzbk() {
    }

    public static zzbk a(byte[] bArr, int i, int i2, boolean z) {
        z zVar = new z(bArr, 0, i2, false);
        try {
            zVar.zzl(i2);
            return zVar;
        } catch (zzco e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static long zza(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public static int zzm(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public abstract int zzaf();

    public abstract int zzl(int i) throws zzco;
}
