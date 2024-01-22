package org.apache.commons.codec.digest;

import java.util.zip.Checksum;
/* loaded from: classes12.dex */
public class XXHash32 implements Checksum {

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f14344a;
    public final int[] b;
    public final byte[] c;
    public final int d;
    public int e;
    public int f;
    public boolean g;

    public XXHash32() {
        this(0);
    }

    public static int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public final void b() {
        int[] iArr = this.b;
        int i = this.d;
        iArr[0] = (i - 1640531535) - 2048144777;
        iArr[1] = (-2048144777) + i;
        iArr[2] = i;
        iArr[3] = i - (-1640531535);
    }

    public final void c(byte[] bArr, int i) {
        int[] iArr = this.b;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        int i5 = iArr[3];
        int[] iArr2 = this.b;
        iArr2[0] = Integer.rotateLeft(i2 + (a(bArr, i) * (-2048144777)), 13) * (-1640531535);
        iArr2[1] = Integer.rotateLeft(i3 + (a(bArr, i + 4) * (-2048144777)), 13) * (-1640531535);
        iArr2[2] = Integer.rotateLeft(i4 + (a(bArr, i + 8) * (-2048144777)), 13) * (-1640531535);
        iArr2[3] = Integer.rotateLeft(i5 + (a(bArr, i + 12) * (-2048144777)), 13) * (-1640531535);
        this.g = true;
    }

    @Override // java.util.zip.Checksum
    public long getValue() {
        int i;
        int i2 = 0;
        if (this.g) {
            i = Integer.rotateLeft(this.b[0], 1) + Integer.rotateLeft(this.b[1], 7) + Integer.rotateLeft(this.b[2], 12) + Integer.rotateLeft(this.b[3], 18);
        } else {
            i = this.b[2] + 374761393;
        }
        int i3 = i + this.e;
        int i4 = this.f - 4;
        while (i2 <= i4) {
            i3 = Integer.rotateLeft(i3 + (a(this.c, i2) * (-1028477379)), 17) * 668265263;
            i2 += 4;
        }
        while (i2 < this.f) {
            i3 = Integer.rotateLeft(i3 + ((this.c[i2] & 255) * 374761393), 11) * (-1640531535);
            i2++;
        }
        int i5 = (i3 ^ (i3 >>> 15)) * (-2048144777);
        int i6 = (i5 ^ (i5 >>> 13)) * (-1028477379);
        return (i6 ^ (i6 >>> 16)) & 4294967295L;
    }

    @Override // java.util.zip.Checksum
    public void reset() {
        b();
        this.e = 0;
        this.f = 0;
        this.g = false;
    }

    @Override // java.util.zip.Checksum
    public void update(int i) {
        byte[] bArr = this.f14344a;
        bArr[0] = (byte) (i & 255);
        update(bArr, 0, 1);
    }

    public XXHash32(int i) {
        this.f14344a = new byte[1];
        this.b = new int[4];
        this.c = new byte[16];
        this.d = i;
        b();
    }

    @Override // java.util.zip.Checksum
    public void update(byte[] bArr, int i, int i2) {
        if (i2 <= 0) {
            return;
        }
        this.e += i2;
        int i3 = i + i2;
        int i4 = this.f;
        if ((i4 + i2) - 16 < 0) {
            System.arraycopy(bArr, i, this.c, i4, i2);
            this.f += i2;
            return;
        }
        if (i4 > 0) {
            int i5 = 16 - i4;
            System.arraycopy(bArr, i, this.c, i4, i5);
            c(this.c, 0);
            i += i5;
        }
        int i6 = i3 - 16;
        while (i <= i6) {
            c(bArr, i);
            i += 16;
        }
        if (i < i3) {
            int i7 = i3 - i;
            this.f = i7;
            System.arraycopy(bArr, i, this.c, 0, i7);
            return;
        }
        this.f = 0;
    }
}
