package com.google.zxing.pdf417.encoder;

import java.lang.reflect.Array;
/* loaded from: classes11.dex */
public final class BarcodeMatrix {

    /* renamed from: a  reason: collision with root package name */
    public final a[] f11861a;
    public int b;
    public final int c;
    public final int d;

    public BarcodeMatrix(int i, int i2) {
        a[] aVarArr = new a[i];
        this.f11861a = aVarArr;
        int length = aVarArr.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.f11861a[i3] = new a(((i2 + 4) * 17) + 1);
        }
        this.d = i2 * 17;
        this.c = i;
        this.b = -1;
    }

    public a a() {
        return this.f11861a[this.b];
    }

    public void b() {
        this.b++;
    }

    public byte[][] getMatrix() {
        return getScaledMatrix(1, 1);
    }

    public byte[][] getScaledMatrix(int i, int i2) {
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, this.c * i2, this.d * i);
        int i3 = this.c * i2;
        for (int i4 = 0; i4 < i3; i4++) {
            bArr[(i3 - i4) - 1] = this.f11861a[i4 / i2].b(i);
        }
        return bArr;
    }
}
