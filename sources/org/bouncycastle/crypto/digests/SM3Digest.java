package org.bouncycastle.crypto.digests;

import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class SM3Digest extends GeneralDigest {
    public static final int[] h = new int[64];
    public int[] d;
    public int[] e;
    public int f;
    public int[] g;

    static {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            h[i2] = (2043430169 >>> (32 - i2)) | (2043430169 << i2);
            i2++;
        }
        for (i = 16; i < 64; i++) {
            int i3 = i % 32;
            h[i] = (2055708042 >>> (32 - i3)) | (2055708042 << i3);
        }
    }

    public SM3Digest() {
        this.d = new int[8];
        this.e = new int[16];
        this.g = new int[68];
        reset();
    }

    public SM3Digest(SM3Digest sM3Digest) {
        super(sM3Digest);
        this.d = new int[8];
        this.e = new int[16];
        this.g = new int[68];
        g(sM3Digest);
    }

    public final int a(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    public final int b(int i, int i2, int i3) {
        return (i & i3) | (i & i2) | (i2 & i3);
    }

    public final int c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new SM3Digest(this);
    }

    public final int d(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToBigEndian(this.d, bArr, i);
        reset();
        return 32;
    }

    public final int e(int i) {
        return (i ^ ((i << 9) | (i >>> 23))) ^ ((i << 17) | (i >>> 15));
    }

    public final int f(int i) {
        return (i ^ ((i << 15) | (i >>> 17))) ^ ((i << 23) | (i >>> 9));
    }

    public final void g(SM3Digest sM3Digest) {
        int[] iArr = sM3Digest.d;
        int[] iArr2 = this.d;
        System.arraycopy(iArr, 0, iArr2, 0, iArr2.length);
        int[] iArr3 = sM3Digest.e;
        int[] iArr4 = this.e;
        System.arraycopy(iArr3, 0, iArr4, 0, iArr4.length);
        this.f = sM3Digest.f;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "SM3";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        int i;
        int i2 = 0;
        while (true) {
            if (i2 >= 16) {
                break;
            }
            this.g[i2] = this.e[i2];
            i2++;
        }
        for (int i3 = 16; i3 < 68; i3++) {
            int[] iArr = this.g;
            int i4 = iArr[i3 - 3];
            int i5 = iArr[i3 - 13];
            iArr[i3] = (f(((i4 >>> 17) | (i4 << 15)) ^ (iArr[i3 - 16] ^ iArr[i3 - 9])) ^ ((i5 >>> 25) | (i5 << 7))) ^ this.g[i3 - 6];
        }
        int[] iArr2 = this.d;
        int i6 = iArr2[0];
        int i7 = iArr2[1];
        int i8 = iArr2[2];
        int i9 = iArr2[3];
        int i10 = iArr2[4];
        int i11 = iArr2[5];
        int i12 = iArr2[6];
        int i13 = iArr2[7];
        int i14 = 0;
        int i15 = i12;
        for (i = 16; i14 < i; i = 16) {
            int i16 = (i6 << 12) | (i6 >>> 20);
            int i17 = i16 + i10 + h[i14];
            int i18 = (i17 << 7) | (i17 >>> 25);
            int[] iArr3 = this.g;
            int i19 = iArr3[i14];
            int i20 = (i7 << 9) | (i7 >>> 23);
            int i21 = (i11 << 19) | (i11 >>> 13);
            i14++;
            i11 = i10;
            i10 = e(c(i10, i11, i15) + i13 + i18 + i19);
            i9 = i8;
            i8 = i20;
            i13 = i15;
            i15 = i21;
            i7 = i6;
            i6 = a(i6, i7, i8) + i9 + (i18 ^ i16) + (i19 ^ iArr3[i14 + 4]);
        }
        int i22 = i13;
        int i23 = i10;
        int i24 = i15;
        int i25 = i9;
        int i26 = i8;
        int i27 = i7;
        int i28 = i6;
        int i29 = 16;
        while (i29 < 64) {
            int i30 = (i28 << 12) | (i28 >>> 20);
            int i31 = i30 + i23 + h[i29];
            int i32 = (i31 << 7) | (i31 >>> 25);
            int[] iArr4 = this.g;
            int i33 = iArr4[i29];
            int i34 = (i11 << 19) | (i11 >>> 13);
            i29++;
            i11 = i23;
            i23 = e(d(i23, i11, i24) + i22 + i32 + i33);
            i25 = i26;
            i26 = (i27 >>> 23) | (i27 << 9);
            i27 = i28;
            i28 = b(i28, i27, i26) + i25 + (i32 ^ i30) + (i33 ^ iArr4[i29 + 4]);
            i22 = i24;
            i24 = i34;
        }
        int[] iArr5 = this.d;
        iArr5[0] = i28 ^ iArr5[0];
        iArr5[1] = iArr5[1] ^ i27;
        iArr5[2] = iArr5[2] ^ i26;
        iArr5[3] = iArr5[3] ^ i25;
        iArr5[4] = iArr5[4] ^ i23;
        iArr5[5] = iArr5[5] ^ i11;
        iArr5[6] = i24 ^ iArr5[6];
        iArr5[7] = iArr5[7] ^ i22;
        this.f = 0;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processLength(long j) {
        int i = this.f;
        if (i > 14) {
            this.e[i] = 0;
            this.f = i + 1;
            processBlock();
        }
        while (true) {
            int i2 = this.f;
            if (i2 >= 14) {
                int[] iArr = this.e;
                int i3 = i2 + 1;
                this.f = i3;
                iArr[i2] = (int) (j >>> 32);
                this.f = i3 + 1;
                iArr[i3] = (int) j;
                return;
            }
            this.e[i2] = 0;
            this.f = i2 + 1;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processWord(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i3 + 1] & 255) | ((bArr[i] & 255) << 24) | ((bArr[i2] & 255) << 16) | ((bArr[i3] & 255) << 8);
        int[] iArr = this.e;
        int i5 = this.f;
        iArr[i5] = i4;
        int i6 = i5 + 1;
        this.f = i6;
        if (i6 >= 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        int[] iArr = this.d;
        iArr[0] = 1937774191;
        iArr[1] = 1226093241;
        iArr[2] = 388252375;
        iArr[3] = -628488704;
        iArr[4] = -1452330820;
        iArr[5] = 372324522;
        iArr[6] = -477237683;
        iArr[7] = -1325724082;
        this.f = 0;
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        SM3Digest sM3Digest = (SM3Digest) memoable;
        super.copyIn(sM3Digest);
        g(sM3Digest);
    }
}
