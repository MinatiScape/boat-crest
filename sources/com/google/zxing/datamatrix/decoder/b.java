package com.google.zxing.datamatrix.decoder;

import com.google.zxing.datamatrix.decoder.Version;
/* loaded from: classes11.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    public final int f11804a;
    public final byte[] b;

    public b(int i, byte[] bArr) {
        this.f11804a = i;
        this.b = bArr;
    }

    public static b[] b(byte[] bArr, Version version) {
        Version.c b = version.b();
        Version.b[] a2 = b.a();
        int i = 0;
        for (Version.b bVar : a2) {
            i += bVar.a();
        }
        b[] bVarArr = new b[i];
        int i2 = 0;
        for (Version.b bVar2 : a2) {
            int i3 = 0;
            while (i3 < bVar2.a()) {
                int b2 = bVar2.b();
                bVarArr[i2] = new b(b2, new byte[b.b() + b2]);
                i3++;
                i2++;
            }
        }
        int length = bVarArr[0].b.length - b.b();
        int i4 = length - 1;
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            int i7 = 0;
            while (i7 < i2) {
                bVarArr[i7].b[i6] = bArr[i5];
                i7++;
                i5++;
            }
        }
        boolean z = version.getVersionNumber() == 24;
        int i8 = z ? 8 : i2;
        int i9 = 0;
        while (i9 < i8) {
            bVarArr[i9].b[i4] = bArr[i5];
            i9++;
            i5++;
        }
        int length2 = bVarArr[0].b.length;
        while (length < length2) {
            int i10 = 0;
            while (i10 < i2) {
                int i11 = z ? (i10 + 8) % i2 : i10;
                bVarArr[i11].b[(!z || i11 <= 7) ? length : length - 1] = bArr[i5];
                i10++;
                i5++;
            }
            length++;
        }
        if (i5 == bArr.length) {
            return bVarArr;
        }
        throw new IllegalArgumentException();
    }

    public byte[] a() {
        return this.b;
    }

    public int c() {
        return this.f11804a;
    }
}
