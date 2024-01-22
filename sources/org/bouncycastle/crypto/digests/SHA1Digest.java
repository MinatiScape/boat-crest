package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class SHA1Digest extends GeneralDigest implements EncodableDigest {
    public int d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int[] i;
    public int j;

    public SHA1Digest() {
        this.i = new int[80];
        reset();
    }

    public SHA1Digest(SHA1Digest sHA1Digest) {
        super(sHA1Digest);
        this.i = new int[80];
        a(sHA1Digest);
    }

    public SHA1Digest(byte[] bArr) {
        super(bArr);
        this.i = new int[80];
        this.d = Pack.bigEndianToInt(bArr, 16);
        this.e = Pack.bigEndianToInt(bArr, 20);
        this.f = Pack.bigEndianToInt(bArr, 24);
        this.g = Pack.bigEndianToInt(bArr, 28);
        this.h = Pack.bigEndianToInt(bArr, 32);
        this.j = Pack.bigEndianToInt(bArr, 36);
        for (int i = 0; i != this.j; i++) {
            this.i[i] = Pack.bigEndianToInt(bArr, (i * 4) + 40);
        }
    }

    public final void a(SHA1Digest sHA1Digest) {
        this.d = sHA1Digest.d;
        this.e = sHA1Digest.e;
        this.f = sHA1Digest.f;
        this.g = sHA1Digest.g;
        this.h = sHA1Digest.h;
        int[] iArr = sHA1Digest.i;
        System.arraycopy(iArr, 0, this.i, 0, iArr.length);
        this.j = sHA1Digest.j;
    }

    public final int b(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    public final int c(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SHA1Digest(this);
    }

    public final int d(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.d, bArr, i);
        Pack.intToBigEndian(this.e, bArr, i + 4);
        Pack.intToBigEndian(this.f, bArr, i + 8);
        Pack.intToBigEndian(this.g, bArr, i + 12);
        Pack.intToBigEndian(this.h, bArr, i + 16);
        reset();
        return 20;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SHA-1";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 20;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.j * 4) + 40];
        super.populateState(bArr);
        Pack.intToBigEndian(this.d, bArr, 16);
        Pack.intToBigEndian(this.e, bArr, 20);
        Pack.intToBigEndian(this.f, bArr, 24);
        Pack.intToBigEndian(this.g, bArr, 28);
        Pack.intToBigEndian(this.h, bArr, 32);
        Pack.intToBigEndian(this.j, bArr, 36);
        for (int i = 0; i != this.j; i++) {
            Pack.intToBigEndian(this.i[i], bArr, (i * 4) + 40);
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        for (int i13 = 16; i13 < 80; i13++) {
            int[] iArr = this.i;
            int i14 = ((iArr[i13 - 3] ^ iArr[i13 - 8]) ^ iArr[i13 - 14]) ^ iArr[i13 - 16];
            iArr[i13] = (i14 >>> 31) | (i14 << 1);
        }
        int i15 = this.d;
        int i16 = this.e;
        int i17 = this.f;
        int i18 = this.g;
        int i19 = this.h;
        int i20 = 0;
        int i21 = 0;
        while (i20 < 4) {
            int i22 = i21 + 1;
            int b = i19 + ((i15 << 5) | (i15 >>> 27)) + b(i16, i17, i18) + this.i[i21] + 1518500249;
            int i23 = (i16 >>> 2) | (i16 << 30);
            int i24 = i22 + 1;
            int b2 = i18 + ((b << 5) | (b >>> 27)) + b(i15, i23, i17) + this.i[i22] + 1518500249;
            int i25 = (i15 >>> 2) | (i15 << 30);
            int i26 = i24 + 1;
            int b3 = i17 + ((b2 << 5) | (b2 >>> 27)) + b(b, i25, i23) + this.i[i24] + 1518500249;
            i19 = (b >>> 2) | (b << 30);
            int i27 = i26 + 1;
            i16 = i23 + ((b3 << 5) | (b3 >>> 27)) + b(b2, i19, i25) + this.i[i26] + 1518500249;
            i18 = (b2 >>> 2) | (b2 << 30);
            i15 = i25 + ((i16 << 5) | (i16 >>> 27)) + b(b3, i18, i19) + this.i[i27] + 1518500249;
            i17 = (b3 >>> 2) | (b3 << 30);
            i20++;
            i21 = i27 + 1;
        }
        int i28 = 0;
        while (i28 < 4) {
            int i29 = i21 + 1;
            int d = i19 + ((i15 << 5) | (i15 >>> 27)) + d(i16, i17, i18) + this.i[i21] + 1859775393;
            int i30 = (i16 >>> 2) | (i16 << 30);
            int i31 = i29 + 1;
            int d2 = i18 + ((d << 5) | (d >>> 27)) + d(i15, i30, i17) + this.i[i29] + 1859775393;
            int i32 = (i15 >>> 2) | (i15 << 30);
            int i33 = i31 + 1;
            int d3 = i17 + ((d2 << 5) | (d2 >>> 27)) + d(d, i32, i30) + this.i[i31] + 1859775393;
            i19 = (d >>> 2) | (d << 30);
            int i34 = i33 + 1;
            i16 = i30 + ((d3 << 5) | (d3 >>> 27)) + d(d2, i19, i32) + this.i[i33] + 1859775393;
            i18 = (d2 >>> 2) | (d2 << 30);
            i15 = i32 + ((i16 << 5) | (i16 >>> 27)) + d(d3, i18, i19) + this.i[i34] + 1859775393;
            i17 = (d3 >>> 2) | (d3 << 30);
            i28++;
            i21 = i34 + 1;
        }
        int i35 = 0;
        while (i35 < 4) {
            int c = i19 + (((((i15 << 5) | (i15 >>> 27)) + c(i16, i17, i18)) + this.i[i21]) - 1894007588);
            int c2 = i18 + (((((c << 5) | (c >>> 27)) + c(i15, i8, i17)) + this.i[i7]) - 1894007588);
            int c3 = i17 + (((((c2 << 5) | (c2 >>> 27)) + c(c, i10, i8)) + this.i[i9]) - 1894007588);
            i19 = (c >>> 2) | (c << 30);
            i16 = ((i16 >>> 2) | (i16 << 30)) + (((((c3 << 5) | (c3 >>> 27)) + c(c2, i19, i10)) + this.i[i11]) - 1894007588);
            i18 = (c2 >>> 2) | (c2 << 30);
            i15 = ((i15 >>> 2) | (i15 << 30)) + (((((i16 << 5) | (i16 >>> 27)) + c(c3, i18, i19)) + this.i[i12]) - 1894007588);
            i17 = (c3 >>> 2) | (c3 << 30);
            i35++;
            i21 = i21 + 1 + 1 + 1 + 1 + 1;
        }
        int i36 = 0;
        while (i36 <= 3) {
            int d4 = i19 + (((((i15 << 5) | (i15 >>> 27)) + d(i16, i17, i18)) + this.i[i21]) - 899497514);
            int d5 = i18 + (((((d4 << 5) | (d4 >>> 27)) + d(i15, i2, i17)) + this.i[i]) - 899497514);
            int d6 = i17 + (((((d5 << 5) | (d5 >>> 27)) + d(d4, i4, i2)) + this.i[i3]) - 899497514);
            i19 = (d4 >>> 2) | (d4 << 30);
            i16 = ((i16 >>> 2) | (i16 << 30)) + (((((d6 << 5) | (d6 >>> 27)) + d(d5, i19, i4)) + this.i[i5]) - 899497514);
            i18 = (d5 >>> 2) | (d5 << 30);
            i15 = ((i15 >>> 2) | (i15 << 30)) + (((((i16 << 5) | (i16 >>> 27)) + d(d6, i18, i19)) + this.i[i6]) - 899497514);
            i17 = (d6 >>> 2) | (d6 << 30);
            i36++;
            i21 = i21 + 1 + 1 + 1 + 1 + 1;
        }
        this.d += i15;
        this.e += i16;
        this.f += i17;
        this.g += i18;
        this.h += i19;
        this.j = 0;
        for (int i37 = 0; i37 < 16; i37++) {
            this.i[i37] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processLength(long j) {
        if (this.j > 14) {
            processBlock();
        }
        int[] iArr = this.i;
        iArr[14] = (int) (j >>> 32);
        iArr[15] = (int) j;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | (bArr[i] << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.i;
        int i5 = this.j;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.j = i6;
        if (i6 == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.d = 1732584193;
        this.e = -271733879;
        this.f = -1732584194;
        this.g = 271733878;
        this.h = -1009589776;
        this.j = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.i;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SHA1Digest sHA1Digest = (SHA1Digest) memoable;
        super.copyIn(sHA1Digest);
        a(sHA1Digest);
    }
}
