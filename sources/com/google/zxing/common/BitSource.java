package com.google.zxing.common;
/* loaded from: classes11.dex */
public final class BitSource {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f11786a;
    public int b;
    public int c;

    public BitSource(byte[] bArr) {
        this.f11786a = bArr;
    }

    public int available() {
        return ((this.f11786a.length - this.b) * 8) - this.c;
    }

    public int getBitOffset() {
        return this.c;
    }

    public int getByteOffset() {
        return this.b;
    }

    public int readBits(int i) {
        if (i > 0 && i <= 32 && i <= available()) {
            int i2 = this.c;
            int i3 = 0;
            if (i2 > 0) {
                int i4 = 8 - i2;
                int min = Math.min(i, i4);
                int i5 = i4 - min;
                byte[] bArr = this.f11786a;
                int i6 = this.b;
                int i7 = (((255 >> (8 - min)) << i5) & bArr[i6]) >> i5;
                i -= min;
                int i8 = this.c + min;
                this.c = i8;
                if (i8 == 8) {
                    this.c = 0;
                    this.b = i6 + 1;
                }
                i3 = i7;
            }
            if (i > 0) {
                while (i >= 8) {
                    int i9 = i3 << 8;
                    byte[] bArr2 = this.f11786a;
                    int i10 = this.b;
                    i3 = (bArr2[i10] & 255) | i9;
                    this.b = i10 + 1;
                    i -= 8;
                }
                if (i > 0) {
                    int i11 = 8 - i;
                    int i12 = (i3 << i) | ((((255 >> i11) << i11) & this.f11786a[this.b]) >> i11);
                    this.c += i;
                    return i12;
                }
                return i3;
            }
            return i3;
        }
        throw new IllegalArgumentException(String.valueOf(i));
    }
}
