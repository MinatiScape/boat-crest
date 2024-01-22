package com.google.android.gms.internal.firebase_ml;
/* loaded from: classes7.dex */
public abstract class zzip {

    /* renamed from: a  reason: collision with root package name */
    public final int f8777a;
    public final int b;
    public final int c;
    public final byte zzafw;
    public final int zzafz;

    public zzip(int i, int i2, int i3, int i4) {
        this(3, 4, i3, i4, (byte) 61);
    }

    public abstract void a(byte[] bArr, int i, int i2, x0 x0Var);

    public abstract boolean zza(byte b);

    public final byte[] zza(int i, x0 x0Var) {
        byte[] bArr = x0Var.b;
        if (bArr == null || bArr.length < x0Var.c + i) {
            if (bArr == null) {
                x0Var.b = new byte[8192];
                x0Var.c = 0;
                x0Var.d = 0;
            } else {
                byte[] bArr2 = new byte[bArr.length << 1];
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                x0Var.b = bArr2;
            }
            return x0Var.b;
        }
        return bArr;
    }

    public final long zzc(byte[] bArr) {
        int length = bArr.length;
        int i = this.f8777a;
        long j = (((length + i) - 1) / i) * this.b;
        int i2 = this.zzafz;
        return i2 > 0 ? j + ((((i2 + j) - 1) / i2) * this.c) : j;
    }

    public zzip(int i, int i2, int i3, int i4, byte b) {
        this.f8777a = 3;
        this.b = 4;
        this.zzafz = i3 > 0 && i4 > 0 ? (i3 / 4) << 2 : 0;
        this.c = i4;
        this.zzafw = (byte) 61;
    }
}
