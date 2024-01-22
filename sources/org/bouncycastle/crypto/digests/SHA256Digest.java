package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class SHA256Digest extends GeneralDigest implements EncodableDigest {
    public static final int[] n = {1116352408, 1899447441, -1245643825, -373957723, 961987163, 1508970993, -1841331548, -1424204075, -670586216, 310598401, 607225278, 1426881987, 1925078388, -2132889090, -1680079193, -1046744716, -459576895, -272742522, 264347078, 604807628, 770255983, 1249150122, 1555081692, 1996064986, -1740746414, -1473132947, -1341970488, -1084653625, -958395405, -710438585, 113926993, 338241895, 666307205, 773529912, 1294757372, 1396182291, 1695183700, 1986661051, -2117940946, -1838011259, -1564481375, -1474664885, -1035236496, -949202525, -778901479, -694614492, -200395387, 275423344, 430227734, 506948616, 659060556, 883997877, 958139571, 1322822218, 1537002063, 1747873779, 1955562222, 2024104815, -2067236844, -1933114872, -1866530822, -1538233109, -1090935817, -965641998};
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int[] l;
    public int m;

    public SHA256Digest() {
        this.l = new int[64];
        reset();
    }

    public SHA256Digest(SHA256Digest sHA256Digest) {
        super(sHA256Digest);
        this.l = new int[64];
        g(sHA256Digest);
    }

    public SHA256Digest(byte[] bArr) {
        super(bArr);
        this.l = new int[64];
        this.d = Pack.bigEndianToInt(bArr, 16);
        this.e = Pack.bigEndianToInt(bArr, 20);
        this.f = Pack.bigEndianToInt(bArr, 24);
        this.g = Pack.bigEndianToInt(bArr, 28);
        this.h = Pack.bigEndianToInt(bArr, 32);
        this.i = Pack.bigEndianToInt(bArr, 36);
        this.j = Pack.bigEndianToInt(bArr, 40);
        this.k = Pack.bigEndianToInt(bArr, 44);
        this.m = Pack.bigEndianToInt(bArr, 48);
        for (int i = 0; i != this.m; i++) {
            this.l[i] = Pack.bigEndianToInt(bArr, (i * 4) + 52);
        }
    }

    public final int a(int i, int i2, int i3) {
        return ((~i) & i3) ^ (i2 & i);
    }

    public final int b(int i, int i2, int i3) {
        return ((i & i3) ^ (i & i2)) ^ (i2 & i3);
    }

    public final int c(int i) {
        return ((i << 10) | (i >>> 22)) ^ (((i >>> 2) | (i << 30)) ^ ((i >>> 13) | (i << 19)));
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA256Digest(this);
    }

    public final int d(int i) {
        return ((i << 7) | (i >>> 25)) ^ (((i >>> 6) | (i << 26)) ^ ((i >>> 11) | (i << 21)));
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.d, bArr, i);
        Pack.intToBigEndian(this.e, bArr, i + 4);
        Pack.intToBigEndian(this.f, bArr, i + 8);
        Pack.intToBigEndian(this.g, bArr, i + 12);
        Pack.intToBigEndian(this.h, bArr, i + 16);
        Pack.intToBigEndian(this.i, bArr, i + 20);
        Pack.intToBigEndian(this.j, bArr, i + 24);
        Pack.intToBigEndian(this.k, bArr, i + 28);
        reset();
        return 32;
    }

    public final int e(int i) {
        return (i >>> 3) ^ (((i >>> 7) | (i << 25)) ^ ((i >>> 18) | (i << 14)));
    }

    public final int f(int i) {
        return (i >>> 10) ^ (((i >>> 17) | (i << 15)) ^ ((i >>> 19) | (i << 13)));
    }

    public final void g(SHA256Digest sHA256Digest) {
        super.copyIn(sHA256Digest);
        this.d = sHA256Digest.d;
        this.e = sHA256Digest.e;
        this.f = sHA256Digest.f;
        this.g = sHA256Digest.g;
        this.h = sHA256Digest.h;
        this.i = sHA256Digest.i;
        this.j = sHA256Digest.j;
        this.k = sHA256Digest.k;
        int[] iArr = sHA256Digest.l;
        System.arraycopy(iArr, 0, this.l, 0, iArr.length);
        this.m = sHA256Digest.m;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-256";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.m * 4) + 52];
        super.populateState(bArr);
        Pack.intToBigEndian(this.d, bArr, 16);
        Pack.intToBigEndian(this.e, bArr, 20);
        Pack.intToBigEndian(this.f, bArr, 24);
        Pack.intToBigEndian(this.g, bArr, 28);
        Pack.intToBigEndian(this.h, bArr, 32);
        Pack.intToBigEndian(this.i, bArr, 36);
        Pack.intToBigEndian(this.j, bArr, 40);
        Pack.intToBigEndian(this.k, bArr, 44);
        Pack.intToBigEndian(this.m, bArr, 48);
        for (int i = 0; i != this.m; i++) {
            Pack.intToBigEndian(this.l[i], bArr, (i * 4) + 52);
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        for (int i = 16; i <= 63; i++) {
            int[] iArr = this.l;
            int f = f(iArr[i - 2]);
            int[] iArr2 = this.l;
            iArr[i] = f + iArr2[i - 7] + e(iArr2[i - 15]) + this.l[i - 16];
        }
        int i2 = this.d;
        int i3 = this.e;
        int i4 = this.f;
        int i5 = this.g;
        int i6 = this.h;
        int i7 = this.i;
        int i8 = this.j;
        int i9 = this.k;
        int i10 = 0;
        for (int i11 = 0; i11 < 8; i11++) {
            int d = d(i6) + a(i6, i7, i8);
            int[] iArr3 = n;
            int i12 = i9 + d + iArr3[i10] + this.l[i10];
            int i13 = i5 + i12;
            int c = i12 + c(i2) + b(i2, i3, i4);
            int i14 = i10 + 1;
            int d2 = i8 + d(i13) + a(i13, i6, i7) + iArr3[i14] + this.l[i14];
            int i15 = i4 + d2;
            int c2 = d2 + c(c) + b(c, i2, i3);
            int i16 = i14 + 1;
            int d3 = i7 + d(i15) + a(i15, i13, i6) + iArr3[i16] + this.l[i16];
            int i17 = i3 + d3;
            int c3 = d3 + c(c2) + b(c2, c, i2);
            int i18 = i16 + 1;
            int d4 = i6 + d(i17) + a(i17, i15, i13) + iArr3[i18] + this.l[i18];
            int i19 = i2 + d4;
            int c4 = d4 + c(c3) + b(c3, c2, c);
            int i20 = i18 + 1;
            int d5 = i13 + d(i19) + a(i19, i17, i15) + iArr3[i20] + this.l[i20];
            i9 = c + d5;
            i5 = d5 + c(c4) + b(c4, c3, c2);
            int i21 = i20 + 1;
            int d6 = i15 + d(i9) + a(i9, i19, i17) + iArr3[i21] + this.l[i21];
            i8 = c2 + d6;
            i4 = d6 + c(i5) + b(i5, c4, c3);
            int i22 = i21 + 1;
            int d7 = i17 + d(i8) + a(i8, i9, i19) + iArr3[i22] + this.l[i22];
            i7 = c3 + d7;
            i3 = d7 + c(i4) + b(i4, i5, c4);
            int i23 = i22 + 1;
            int d8 = i19 + d(i7) + a(i7, i8, i9) + iArr3[i23] + this.l[i23];
            i6 = c4 + d8;
            i2 = d8 + c(i3) + b(i3, i4, i5);
            i10 = i23 + 1;
        }
        this.d += i2;
        this.e += i3;
        this.f += i4;
        this.g += i5;
        this.h += i6;
        this.i += i7;
        this.j += i8;
        this.k += i9;
        this.m = 0;
        for (int i24 = 0; i24 < 16; i24++) {
            this.l[i24] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processLength(long j) {
        if (this.m > 14) {
            processBlock();
        }
        int[] iArr = this.l;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) (j & (-1));
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.l;
        int i5 = this.m;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.m = i6;
        if (i6 == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.d = 1779033703;
        this.e = -1150833019;
        this.f = 1013904242;
        this.g = -1521486534;
        this.h = 1359893119;
        this.i = -1694144372;
        this.j = 528734635;
        this.k = 1541459225;
        this.m = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.l;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        g((SHA256Digest) memoable);
    }
}
