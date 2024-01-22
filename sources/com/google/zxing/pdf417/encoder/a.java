package com.google.zxing.pdf417.encoder;
/* loaded from: classes11.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f11864a;
    public int b = 0;

    public a(int i) {
        this.f11864a = new byte[i];
    }

    public void a(boolean z, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.b;
            this.b = i3 + 1;
            c(i3, z);
        }
    }

    public byte[] b(int i) {
        int length = this.f11864a.length * i;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            bArr[i2] = this.f11864a[i2 / i];
        }
        return bArr;
    }

    public final void c(int i, boolean z) {
        this.f11864a[i] = z ? (byte) 1 : (byte) 0;
    }
}
