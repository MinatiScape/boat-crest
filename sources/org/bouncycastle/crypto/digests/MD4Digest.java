package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class MD4Digest extends GeneralDigest {
    public int d;
    public int e;
    public int f;
    public int g;
    public int[] h;
    public int i;

    public MD4Digest() {
        this.h = new int[16];
        reset();
    }

    public MD4Digest(MD4Digest mD4Digest) {
        super(mD4Digest);
        this.h = new int[16];
        d(mD4Digest);
    }

    public final int a(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    public final int b(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    public final int c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new MD4Digest(this);
    }

    public final void d(MD4Digest mD4Digest) {
        super.copyIn(mD4Digest);
        this.d = mD4Digest.d;
        this.e = mD4Digest.e;
        this.f = mD4Digest.f;
        this.g = mD4Digest.g;
        int[] iArr = mD4Digest.h;
        System.arraycopy(iArr, 0, this.h, 0, iArr.length);
        this.i = mD4Digest.i;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        f(this.d, bArr, i);
        f(this.e, bArr, i + 4);
        f(this.f, bArr, i + 8);
        f(this.g, bArr, i + 12);
        reset();
        return 16;
    }

    public final int e(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public final void f(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "MD4";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        int i = this.d;
        int i2 = this.e;
        int i3 = this.f;
        int i4 = this.g;
        int e = e(i + a(i2, i3, i4) + this.h[0], 3);
        int e2 = e(i4 + a(e, i2, i3) + this.h[1], 7);
        int e3 = e(i3 + a(e2, e, i2) + this.h[2], 11);
        int e4 = e(i2 + a(e3, e2, e) + this.h[3], 19);
        int e5 = e(e + a(e4, e3, e2) + this.h[4], 3);
        int e6 = e(e2 + a(e5, e4, e3) + this.h[5], 7);
        int e7 = e(e3 + a(e6, e5, e4) + this.h[6], 11);
        int e8 = e(e4 + a(e7, e6, e5) + this.h[7], 19);
        int e9 = e(e5 + a(e8, e7, e6) + this.h[8], 3);
        int e10 = e(e6 + a(e9, e8, e7) + this.h[9], 7);
        int e11 = e(e7 + a(e10, e9, e8) + this.h[10], 11);
        int e12 = e(e8 + a(e11, e10, e9) + this.h[11], 19);
        int e13 = e(e9 + a(e12, e11, e10) + this.h[12], 3);
        int e14 = e(e10 + a(e13, e12, e11) + this.h[13], 7);
        int e15 = e(e11 + a(e14, e13, e12) + this.h[14], 11);
        int e16 = e(e12 + a(e15, e14, e13) + this.h[15], 19);
        int e17 = e(e13 + b(e16, e15, e14) + this.h[0] + 1518500249, 3);
        int e18 = e(e14 + b(e17, e16, e15) + this.h[4] + 1518500249, 5);
        int e19 = e(e15 + b(e18, e17, e16) + this.h[8] + 1518500249, 9);
        int e20 = e(e16 + b(e19, e18, e17) + this.h[12] + 1518500249, 13);
        int e21 = e(e17 + b(e20, e19, e18) + this.h[1] + 1518500249, 3);
        int e22 = e(e18 + b(e21, e20, e19) + this.h[5] + 1518500249, 5);
        int e23 = e(e19 + b(e22, e21, e20) + this.h[9] + 1518500249, 9);
        int e24 = e(e20 + b(e23, e22, e21) + this.h[13] + 1518500249, 13);
        int e25 = e(e21 + b(e24, e23, e22) + this.h[2] + 1518500249, 3);
        int e26 = e(e22 + b(e25, e24, e23) + this.h[6] + 1518500249, 5);
        int e27 = e(e23 + b(e26, e25, e24) + this.h[10] + 1518500249, 9);
        int e28 = e(e24 + b(e27, e26, e25) + this.h[14] + 1518500249, 13);
        int e29 = e(e25 + b(e28, e27, e26) + this.h[3] + 1518500249, 3);
        int e30 = e(e26 + b(e29, e28, e27) + this.h[7] + 1518500249, 5);
        int e31 = e(e27 + b(e30, e29, e28) + this.h[11] + 1518500249, 9);
        int e32 = e(e28 + b(e31, e30, e29) + this.h[15] + 1518500249, 13);
        int e33 = e(e29 + c(e32, e31, e30) + this.h[0] + 1859775393, 3);
        int e34 = e(e30 + c(e33, e32, e31) + this.h[8] + 1859775393, 9);
        int e35 = e(e31 + c(e34, e33, e32) + this.h[4] + 1859775393, 11);
        int e36 = e(e32 + c(e35, e34, e33) + this.h[12] + 1859775393, 15);
        int e37 = e(e33 + c(e36, e35, e34) + this.h[2] + 1859775393, 3);
        int e38 = e(e34 + c(e37, e36, e35) + this.h[10] + 1859775393, 9);
        int e39 = e(e35 + c(e38, e37, e36) + this.h[6] + 1859775393, 11);
        int e40 = e(e36 + c(e39, e38, e37) + this.h[14] + 1859775393, 15);
        int e41 = e(e37 + c(e40, e39, e38) + this.h[1] + 1859775393, 3);
        int e42 = e(e38 + c(e41, e40, e39) + this.h[9] + 1859775393, 9);
        int e43 = e(e39 + c(e42, e41, e40) + this.h[5] + 1859775393, 11);
        int e44 = e(e40 + c(e43, e42, e41) + this.h[13] + 1859775393, 15);
        int e45 = e(e41 + c(e44, e43, e42) + this.h[3] + 1859775393, 3);
        int e46 = e(e42 + c(e45, e44, e43) + this.h[11] + 1859775393, 9);
        int e47 = e(e43 + c(e46, e45, e44) + this.h[7] + 1859775393, 11);
        int e48 = e(e44 + c(e47, e46, e45) + this.h[15] + 1859775393, 15);
        this.d += e45;
        this.e += e48;
        this.f += e47;
        this.g += e46;
        this.i = 0;
        int i5 = 0;
        while (true) {
            int[] iArr = this.h;
            if (i5 == iArr.length) {
                return;
            }
            iArr[i5] = 0;
            i5++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processLength(long j) {
        if (this.i > 14) {
            processBlock();
        }
        int[] iArr = this.h;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] bArr, int i) {
        int[] iArr = this.h;
        int i2 = this.i;
        int i3 = i2 + 1;
        this.i = i3;
        iArr[i2] = ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        if (i3 == 16) {
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
        this.i = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.h;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        d((MD4Digest) memoable);
    }
}
