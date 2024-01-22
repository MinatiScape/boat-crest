package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
/* loaded from: classes12.dex */
public class RIPEMD128Digest extends GeneralDigest {
    public int d;
    public int e;
    public int f;
    public int g;
    public int[] h;
    public int i;

    public RIPEMD128Digest() {
        this.h = new int[16];
        reset();
    }

    public RIPEMD128Digest(RIPEMD128Digest rIPEMD128Digest) {
        super(rIPEMD128Digest);
        this.h = new int[16];
        j(rIPEMD128Digest);
    }

    public final int a(int i, int i2, int i3, int i4, int i5, int i6) {
        return i(i + k(i2, i3, i4) + i5, i6);
    }

    public final int b(int i, int i2, int i3, int i4, int i5, int i6) {
        return i(i + l(i2, i3, i4) + i5 + 1518500249, i6);
    }

    public final int c(int i, int i2, int i3, int i4, int i5, int i6) {
        return i(i + m(i2, i3, i4) + i5 + 1859775393, i6);
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD128Digest(this);
    }

    public final int d(int i, int i2, int i3, int i4, int i5, int i6) {
        return i(((i + n(i2, i3, i4)) + i5) - 1894007588, i6);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        o(this.d, bArr, i);
        o(this.e, bArr, i + 4);
        o(this.f, bArr, i + 8);
        o(this.g, bArr, i + 12);
        reset();
        return 16;
    }

    public final int e(int i, int i2, int i3, int i4, int i5, int i6) {
        return i(i + k(i2, i3, i4) + i5, i6);
    }

    public final int f(int i, int i2, int i3, int i4, int i5, int i6) {
        return i(i + l(i2, i3, i4) + i5 + 1836072691, i6);
    }

    public final int g(int i, int i2, int i3, int i4, int i5, int i6) {
        return i(i + m(i2, i3, i4) + i5 + 1548603684, i6);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD128";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    public final int h(int i, int i2, int i3, int i4, int i5, int i6) {
        return i(i + n(i2, i3, i4) + i5 + 1352829926, i6);
    }

    public final int i(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public final void j(RIPEMD128Digest rIPEMD128Digest) {
        super.copyIn(rIPEMD128Digest);
        this.d = rIPEMD128Digest.d;
        this.e = rIPEMD128Digest.e;
        this.f = rIPEMD128Digest.f;
        this.g = rIPEMD128Digest.g;
        int[] iArr = rIPEMD128Digest.h;
        System.arraycopy(iArr, 0, this.h, 0, iArr.length);
        this.i = rIPEMD128Digest.i;
    }

    public final int k(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    public final int l(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    public final int m(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    public final int n(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    public final void o(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        int i = this.d;
        int i2 = this.e;
        int i3 = this.f;
        int i4 = this.g;
        int a2 = a(i, i2, i3, i4, this.h[0], 11);
        int a3 = a(i4, a2, i2, i3, this.h[1], 14);
        int a4 = a(i3, a3, a2, i2, this.h[2], 15);
        int a5 = a(i2, a4, a3, a2, this.h[3], 12);
        int a6 = a(a2, a5, a4, a3, this.h[4], 5);
        int a7 = a(a3, a6, a5, a4, this.h[5], 8);
        int a8 = a(a4, a7, a6, a5, this.h[6], 7);
        int a9 = a(a5, a8, a7, a6, this.h[7], 9);
        int a10 = a(a6, a9, a8, a7, this.h[8], 11);
        int a11 = a(a7, a10, a9, a8, this.h[9], 13);
        int a12 = a(a8, a11, a10, a9, this.h[10], 14);
        int a13 = a(a9, a12, a11, a10, this.h[11], 15);
        int a14 = a(a10, a13, a12, a11, this.h[12], 6);
        int a15 = a(a11, a14, a13, a12, this.h[13], 7);
        int a16 = a(a12, a15, a14, a13, this.h[14], 9);
        int a17 = a(a13, a16, a15, a14, this.h[15], 8);
        int b = b(a14, a17, a16, a15, this.h[7], 7);
        int b2 = b(a15, b, a17, a16, this.h[4], 6);
        int b3 = b(a16, b2, b, a17, this.h[13], 8);
        int b4 = b(a17, b3, b2, b, this.h[1], 13);
        int b5 = b(b, b4, b3, b2, this.h[10], 11);
        int b6 = b(b2, b5, b4, b3, this.h[6], 9);
        int b7 = b(b3, b6, b5, b4, this.h[15], 7);
        int b8 = b(b4, b7, b6, b5, this.h[3], 15);
        int b9 = b(b5, b8, b7, b6, this.h[12], 7);
        int b10 = b(b6, b9, b8, b7, this.h[0], 12);
        int b11 = b(b7, b10, b9, b8, this.h[9], 15);
        int b12 = b(b8, b11, b10, b9, this.h[5], 9);
        int b13 = b(b9, b12, b11, b10, this.h[2], 11);
        int b14 = b(b10, b13, b12, b11, this.h[14], 7);
        int b15 = b(b11, b14, b13, b12, this.h[11], 13);
        int b16 = b(b12, b15, b14, b13, this.h[8], 12);
        int c = c(b13, b16, b15, b14, this.h[3], 11);
        int c2 = c(b14, c, b16, b15, this.h[10], 13);
        int c3 = c(b15, c2, c, b16, this.h[14], 6);
        int c4 = c(b16, c3, c2, c, this.h[4], 7);
        int c5 = c(c, c4, c3, c2, this.h[9], 14);
        int c6 = c(c2, c5, c4, c3, this.h[15], 9);
        int c7 = c(c3, c6, c5, c4, this.h[8], 13);
        int c8 = c(c4, c7, c6, c5, this.h[1], 15);
        int c9 = c(c5, c8, c7, c6, this.h[2], 14);
        int c10 = c(c6, c9, c8, c7, this.h[7], 8);
        int c11 = c(c7, c10, c9, c8, this.h[0], 13);
        int c12 = c(c8, c11, c10, c9, this.h[6], 6);
        int c13 = c(c9, c12, c11, c10, this.h[13], 5);
        int c14 = c(c10, c13, c12, c11, this.h[11], 12);
        int c15 = c(c11, c14, c13, c12, this.h[5], 7);
        int c16 = c(c12, c15, c14, c13, this.h[12], 5);
        int d = d(c13, c16, c15, c14, this.h[1], 11);
        int d2 = d(c14, d, c16, c15, this.h[9], 12);
        int d3 = d(c15, d2, d, c16, this.h[11], 14);
        int d4 = d(c16, d3, d2, d, this.h[10], 15);
        int d5 = d(d, d4, d3, d2, this.h[0], 14);
        int d6 = d(d2, d5, d4, d3, this.h[8], 15);
        int d7 = d(d3, d6, d5, d4, this.h[12], 9);
        int d8 = d(d4, d7, d6, d5, this.h[4], 8);
        int d9 = d(d5, d8, d7, d6, this.h[13], 9);
        int d10 = d(d6, d9, d8, d7, this.h[3], 14);
        int d11 = d(d7, d10, d9, d8, this.h[7], 5);
        int d12 = d(d8, d11, d10, d9, this.h[15], 6);
        int d13 = d(d9, d12, d11, d10, this.h[14], 8);
        int d14 = d(d10, d13, d12, d11, this.h[5], 6);
        int d15 = d(d11, d14, d13, d12, this.h[6], 5);
        int d16 = d(d12, d15, d14, d13, this.h[2], 12);
        int h = h(i, i2, i3, i4, this.h[5], 8);
        int h2 = h(i4, h, i2, i3, this.h[14], 9);
        int h3 = h(i3, h2, h, i2, this.h[7], 9);
        int h4 = h(i2, h3, h2, h, this.h[0], 11);
        int h5 = h(h, h4, h3, h2, this.h[9], 13);
        int h6 = h(h2, h5, h4, h3, this.h[2], 15);
        int h7 = h(h3, h6, h5, h4, this.h[11], 15);
        int h8 = h(h4, h7, h6, h5, this.h[4], 5);
        int h9 = h(h5, h8, h7, h6, this.h[13], 7);
        int h10 = h(h6, h9, h8, h7, this.h[6], 7);
        int h11 = h(h7, h10, h9, h8, this.h[15], 8);
        int h12 = h(h8, h11, h10, h9, this.h[8], 11);
        int h13 = h(h9, h12, h11, h10, this.h[1], 14);
        int h14 = h(h10, h13, h12, h11, this.h[10], 14);
        int h15 = h(h11, h14, h13, h12, this.h[3], 12);
        int h16 = h(h12, h15, h14, h13, this.h[12], 6);
        int g = g(h13, h16, h15, h14, this.h[6], 9);
        int g2 = g(h14, g, h16, h15, this.h[11], 13);
        int g3 = g(h15, g2, g, h16, this.h[3], 15);
        int g4 = g(h16, g3, g2, g, this.h[7], 7);
        int g5 = g(g, g4, g3, g2, this.h[0], 12);
        int g6 = g(g2, g5, g4, g3, this.h[13], 8);
        int g7 = g(g3, g6, g5, g4, this.h[5], 9);
        int g8 = g(g4, g7, g6, g5, this.h[10], 11);
        int g9 = g(g5, g8, g7, g6, this.h[14], 7);
        int g10 = g(g6, g9, g8, g7, this.h[15], 7);
        int g11 = g(g7, g10, g9, g8, this.h[8], 12);
        int g12 = g(g8, g11, g10, g9, this.h[12], 7);
        int g13 = g(g9, g12, g11, g10, this.h[4], 6);
        int g14 = g(g10, g13, g12, g11, this.h[9], 15);
        int g15 = g(g11, g14, g13, g12, this.h[1], 13);
        int g16 = g(g12, g15, g14, g13, this.h[2], 11);
        int f = f(g13, g16, g15, g14, this.h[15], 9);
        int f2 = f(g14, f, g16, g15, this.h[5], 7);
        int f3 = f(g15, f2, f, g16, this.h[1], 15);
        int f4 = f(g16, f3, f2, f, this.h[3], 11);
        int f5 = f(f, f4, f3, f2, this.h[7], 8);
        int f6 = f(f2, f5, f4, f3, this.h[14], 6);
        int f7 = f(f3, f6, f5, f4, this.h[6], 6);
        int f8 = f(f4, f7, f6, f5, this.h[9], 14);
        int f9 = f(f5, f8, f7, f6, this.h[11], 12);
        int f10 = f(f6, f9, f8, f7, this.h[8], 13);
        int f11 = f(f7, f10, f9, f8, this.h[12], 5);
        int f12 = f(f8, f11, f10, f9, this.h[2], 14);
        int f13 = f(f9, f12, f11, f10, this.h[10], 13);
        int f14 = f(f10, f13, f12, f11, this.h[0], 13);
        int f15 = f(f11, f14, f13, f12, this.h[4], 7);
        int f16 = f(f12, f15, f14, f13, this.h[13], 5);
        int e = e(f13, f16, f15, f14, this.h[8], 15);
        int e2 = e(f14, e, f16, f15, this.h[6], 5);
        int e3 = e(f15, e2, e, f16, this.h[4], 8);
        int e4 = e(f16, e3, e2, e, this.h[1], 11);
        int e5 = e(e, e4, e3, e2, this.h[3], 14);
        int e6 = e(e2, e5, e4, e3, this.h[11], 14);
        int e7 = e(e3, e6, e5, e4, this.h[15], 6);
        int e8 = e(e4, e7, e6, e5, this.h[0], 14);
        int e9 = e(e5, e8, e7, e6, this.h[5], 6);
        int e10 = e(e6, e9, e8, e7, this.h[12], 9);
        int e11 = e(e7, e10, e9, e8, this.h[2], 12);
        int e12 = e(e8, e11, e10, e9, this.h[13], 9);
        int e13 = e(e9, e12, e11, e10, this.h[9], 12);
        int e14 = e(e10, e13, e12, e11, this.h[7], 5);
        int e15 = e(e11, e14, e13, e12, this.h[10], 15);
        int e16 = e(e12, e15, e14, e13, this.h[14], 8);
        this.e = this.f + d14 + e13;
        this.f = this.g + d13 + e16;
        this.g = this.d + d16 + e15;
        this.d = e14 + d15 + this.e;
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
        j((RIPEMD128Digest) memoable);
    }
}
