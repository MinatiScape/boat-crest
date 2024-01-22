package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitMatrix;
/* loaded from: classes11.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    public final BitMatrix f11874a;
    public Version b;
    public e c;
    public boolean d;

    public a(BitMatrix bitMatrix) throws FormatException {
        int height = bitMatrix.getHeight();
        if (height >= 21 && (height & 3) == 1) {
            this.f11874a = bitMatrix;
            return;
        }
        throw FormatException.getFormatInstance();
    }

    public final int a(int i, int i2, int i3) {
        return this.d ? this.f11874a.get(i2, i) : this.f11874a.get(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    public void b() {
        int i = 0;
        while (i < this.f11874a.getWidth()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < this.f11874a.getHeight(); i3++) {
                if (this.f11874a.get(i, i3) != this.f11874a.get(i3, i)) {
                    this.f11874a.flip(i3, i);
                    this.f11874a.flip(i, i3);
                }
            }
            i = i2;
        }
    }

    public byte[] c() throws FormatException {
        e d = d();
        Version e = e();
        c cVar = c.values()[d.c()];
        int height = this.f11874a.getHeight();
        cVar.unmaskBitMatrix(this.f11874a, height);
        BitMatrix a2 = e.a();
        byte[] bArr = new byte[e.getTotalCodewords()];
        int i = height - 1;
        boolean z = true;
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        while (i2 > 0) {
            if (i2 == 6) {
                i2--;
            }
            for (int i6 = 0; i6 < height; i6++) {
                int i7 = z ? i - i6 : i6;
                for (int i8 = 0; i8 < 2; i8++) {
                    int i9 = i2 - i8;
                    if (!a2.get(i9, i7)) {
                        i4++;
                        i5 <<= 1;
                        if (this.f11874a.get(i9, i7)) {
                            i5 |= 1;
                        }
                        if (i4 == 8) {
                            bArr[i3] = (byte) i5;
                            i3++;
                            i4 = 0;
                            i5 = 0;
                        }
                    }
                }
            }
            z = !z;
            i2 -= 2;
        }
        if (i3 == e.getTotalCodewords()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }

    public e d() throws FormatException {
        e eVar = this.c;
        if (eVar != null) {
            return eVar;
        }
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 6; i3++) {
            i2 = a(i3, 8, i2);
        }
        int a2 = a(8, 7, a(8, 8, a(7, 8, i2)));
        for (int i4 = 5; i4 >= 0; i4--) {
            a2 = a(8, i4, a2);
        }
        int height = this.f11874a.getHeight();
        int i5 = height - 7;
        for (int i6 = height - 1; i6 >= i5; i6--) {
            i = a(8, i6, i);
        }
        for (int i7 = height - 8; i7 < height; i7++) {
            i = a(i7, 8, i);
        }
        e a3 = e.a(a2, i);
        this.c = a3;
        if (a3 != null) {
            return a3;
        }
        throw FormatException.getFormatInstance();
    }

    public Version e() throws FormatException {
        Version version = this.b;
        if (version != null) {
            return version;
        }
        int height = this.f11874a.getHeight();
        int i = (height - 17) / 4;
        if (i <= 6) {
            return Version.getVersionForNumber(i);
        }
        int i2 = height - 11;
        int i3 = 0;
        int i4 = 0;
        for (int i5 = 5; i5 >= 0; i5--) {
            for (int i6 = height - 9; i6 >= i2; i6--) {
                i4 = a(i6, i5, i4);
            }
        }
        Version c = Version.c(i4);
        if (c != null && c.getDimensionForVersion() == height) {
            this.b = c;
            return c;
        }
        for (int i7 = 5; i7 >= 0; i7--) {
            for (int i8 = height - 9; i8 >= i2; i8--) {
                i3 = a(i7, i8, i3);
            }
        }
        Version c2 = Version.c(i3);
        if (c2 != null && c2.getDimensionForVersion() == height) {
            this.b = c2;
            return c2;
        }
        throw FormatException.getFormatInstance();
    }

    public void f() {
        if (this.c == null) {
            return;
        }
        c.values()[this.c.c()].unmaskBitMatrix(this.f11874a, this.f11874a.getHeight());
    }

    public void g(boolean z) {
        this.b = null;
        this.c = null;
        this.d = z;
    }
}
