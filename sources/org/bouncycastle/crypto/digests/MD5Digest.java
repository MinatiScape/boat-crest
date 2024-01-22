package org.bouncycastle.crypto.digests;

import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class MD5Digest extends GeneralDigest implements EncodableDigest {
    public int d;
    public int e;
    public int f;
    public int g;
    public int[] h;
    public int i;

    public MD5Digest() {
        this.h = new int[16];
        reset();
    }

    public MD5Digest(MD5Digest mD5Digest) {
        super(mD5Digest);
        this.h = new int[16];
        e(mD5Digest);
    }

    public MD5Digest(byte[] bArr) {
        super(bArr);
        this.h = new int[16];
        this.d = Pack.bigEndianToInt(bArr, 16);
        this.e = Pack.bigEndianToInt(bArr, 20);
        this.f = Pack.bigEndianToInt(bArr, 24);
        this.g = Pack.bigEndianToInt(bArr, 28);
        this.i = Pack.bigEndianToInt(bArr, 32);
        for (int i = 0; i != this.i; i++) {
            this.h[i] = Pack.bigEndianToInt(bArr, (i * 4) + 36);
        }
    }

    public final int a(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    public final int b(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    public final int c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new MD5Digest(this);
    }

    public final int d(int i, int i2, int i3) {
        return (i | (~i3)) ^ i2;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        g(this.d, bArr, i);
        g(this.e, bArr, i + 4);
        g(this.f, bArr, i + 8);
        g(this.g, bArr, i + 12);
        reset();
        return 16;
    }

    public final void e(MD5Digest mD5Digest) {
        super.copyIn(mD5Digest);
        this.d = mD5Digest.d;
        this.e = mD5Digest.e;
        this.f = mD5Digest.f;
        this.g = mD5Digest.g;
        int[] iArr = mD5Digest.h;
        System.arraycopy(iArr, 0, this.h, 0, iArr.length);
        this.i = mD5Digest.i;
    }

    public final int f(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public final void g(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return MessageDigestAlgorithms.MD5;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.digests.EncodableDigest
    public byte[] getEncodedState() {
        byte[] bArr = new byte[(this.i * 4) + 36];
        super.populateState(bArr);
        Pack.intToBigEndian(this.d, bArr, 16);
        Pack.intToBigEndian(this.e, bArr, 20);
        Pack.intToBigEndian(this.f, bArr, 24);
        Pack.intToBigEndian(this.g, bArr, 28);
        Pack.intToBigEndian(this.i, bArr, 32);
        for (int i = 0; i != this.i; i++) {
            Pack.intToBigEndian(this.h[i], bArr, (i * 4) + 36);
        }
        return bArr;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    public void processBlock() {
        int i = this.d;
        int i2 = this.e;
        int i3 = this.f;
        int i4 = this.g;
        int f = f(((i + a(i2, i3, i4)) + this.h[0]) - 680876936, 7) + i2;
        int f2 = f(((i4 + a(f, i2, i3)) + this.h[1]) - 389564586, 12) + f;
        int f3 = f(i3 + a(f2, f, i2) + this.h[2] + 606105819, 17) + f2;
        int f4 = f(((i2 + a(f3, f2, f)) + this.h[3]) - 1044525330, 22) + f3;
        int f5 = f(((f + a(f4, f3, f2)) + this.h[4]) - 176418897, 7) + f4;
        int f6 = f(f2 + a(f5, f4, f3) + this.h[5] + 1200080426, 12) + f5;
        int f7 = f(((f3 + a(f6, f5, f4)) + this.h[6]) - 1473231341, 17) + f6;
        int f8 = f(((f4 + a(f7, f6, f5)) + this.h[7]) - 45705983, 22) + f7;
        int f9 = f(f5 + a(f8, f7, f6) + this.h[8] + 1770035416, 7) + f8;
        int f10 = f(((f6 + a(f9, f8, f7)) + this.h[9]) - 1958414417, 12) + f9;
        int f11 = f(((f7 + a(f10, f9, f8)) + this.h[10]) - 42063, 17) + f10;
        int f12 = f(((f8 + a(f11, f10, f9)) + this.h[11]) - 1990404162, 22) + f11;
        int f13 = f(f9 + a(f12, f11, f10) + this.h[12] + 1804603682, 7) + f12;
        int f14 = f(((f10 + a(f13, f12, f11)) + this.h[13]) - 40341101, 12) + f13;
        int f15 = f(((f11 + a(f14, f13, f12)) + this.h[14]) - 1502002290, 17) + f14;
        int f16 = f(f12 + a(f15, f14, f13) + this.h[15] + 1236535329, 22) + f15;
        int f17 = f(((f13 + b(f16, f15, f14)) + this.h[1]) - 165796510, 5) + f16;
        int f18 = f(((f14 + b(f17, f16, f15)) + this.h[6]) - 1069501632, 9) + f17;
        int f19 = f(f15 + b(f18, f17, f16) + this.h[11] + 643717713, 14) + f18;
        int f20 = f(((f16 + b(f19, f18, f17)) + this.h[0]) - 373897302, 20) + f19;
        int f21 = f(((f17 + b(f20, f19, f18)) + this.h[5]) - 701558691, 5) + f20;
        int f22 = f(f18 + b(f21, f20, f19) + this.h[10] + 38016083, 9) + f21;
        int f23 = f(((f19 + b(f22, f21, f20)) + this.h[15]) - 660478335, 14) + f22;
        int f24 = f(((f20 + b(f23, f22, f21)) + this.h[4]) - 405537848, 20) + f23;
        int f25 = f(f21 + b(f24, f23, f22) + this.h[9] + 568446438, 5) + f24;
        int f26 = f(((f22 + b(f25, f24, f23)) + this.h[14]) - 1019803690, 9) + f25;
        int f27 = f(((f23 + b(f26, f25, f24)) + this.h[3]) - 187363961, 14) + f26;
        int f28 = f(f24 + b(f27, f26, f25) + this.h[8] + 1163531501, 20) + f27;
        int f29 = f(((f25 + b(f28, f27, f26)) + this.h[13]) - 1444681467, 5) + f28;
        int f30 = f(((f26 + b(f29, f28, f27)) + this.h[2]) - 51403784, 9) + f29;
        int f31 = f(f27 + b(f30, f29, f28) + this.h[7] + 1735328473, 14) + f30;
        int f32 = f(((f28 + b(f31, f30, f29)) + this.h[12]) - 1926607734, 20) + f31;
        int f33 = f(((f29 + c(f32, f31, f30)) + this.h[5]) - 378558, 4) + f32;
        int f34 = f(((f30 + c(f33, f32, f31)) + this.h[8]) - 2022574463, 11) + f33;
        int f35 = f(f31 + c(f34, f33, f32) + this.h[11] + 1839030562, 16) + f34;
        int f36 = f(((f32 + c(f35, f34, f33)) + this.h[14]) - 35309556, 23) + f35;
        int f37 = f(((f33 + c(f36, f35, f34)) + this.h[1]) - 1530992060, 4) + f36;
        int f38 = f(f34 + c(f37, f36, f35) + this.h[4] + 1272893353, 11) + f37;
        int f39 = f(((f35 + c(f38, f37, f36)) + this.h[7]) - 155497632, 16) + f38;
        int f40 = f(((f36 + c(f39, f38, f37)) + this.h[10]) - 1094730640, 23) + f39;
        int f41 = f(f37 + c(f40, f39, f38) + this.h[13] + 681279174, 4) + f40;
        int f42 = f(((f38 + c(f41, f40, f39)) + this.h[0]) - 358537222, 11) + f41;
        int f43 = f(((f39 + c(f42, f41, f40)) + this.h[3]) - 722521979, 16) + f42;
        int f44 = f(f40 + c(f43, f42, f41) + this.h[6] + 76029189, 23) + f43;
        int f45 = f(((f41 + c(f44, f43, f42)) + this.h[9]) - 640364487, 4) + f44;
        int f46 = f(((f42 + c(f45, f44, f43)) + this.h[12]) - 421815835, 11) + f45;
        int f47 = f(f43 + c(f46, f45, f44) + this.h[15] + 530742520, 16) + f46;
        int f48 = f(((f44 + c(f47, f46, f45)) + this.h[2]) - 995338651, 23) + f47;
        int f49 = f(((f45 + d(f48, f47, f46)) + this.h[0]) - 198630844, 6) + f48;
        int f50 = f(f46 + d(f49, f48, f47) + this.h[7] + 1126891415, 10) + f49;
        int f51 = f(((f47 + d(f50, f49, f48)) + this.h[14]) - 1416354905, 15) + f50;
        int f52 = f(((f48 + d(f51, f50, f49)) + this.h[5]) - 57434055, 21) + f51;
        int f53 = f(f49 + d(f52, f51, f50) + this.h[12] + 1700485571, 6) + f52;
        int f54 = f(((f50 + d(f53, f52, f51)) + this.h[3]) - 1894986606, 10) + f53;
        int f55 = f(((f51 + d(f54, f53, f52)) + this.h[10]) - 1051523, 15) + f54;
        int f56 = f(((f52 + d(f55, f54, f53)) + this.h[1]) - 2054922799, 21) + f55;
        int f57 = f(f53 + d(f56, f55, f54) + this.h[8] + 1873313359, 6) + f56;
        int f58 = f(((f54 + d(f57, f56, f55)) + this.h[15]) - 30611744, 10) + f57;
        int f59 = f(((f55 + d(f58, f57, f56)) + this.h[6]) - 1560198380, 15) + f58;
        int f60 = f(f56 + d(f59, f58, f57) + this.h[13] + 1309151649, 21) + f59;
        int f61 = f(((f57 + d(f60, f59, f58)) + this.h[4]) - 145523070, 6) + f60;
        int f62 = f(((f58 + d(f61, f60, f59)) + this.h[11]) - 1120210379, 10) + f61;
        int f63 = f(f59 + d(f62, f61, f60) + this.h[2] + 718787259, 15) + f62;
        this.d += f61;
        this.e += f(((f60 + d(f63, f62, f61)) + this.h[9]) - 343485551, 21) + f63;
        this.f += f63;
        this.g += f62;
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
        e((MD5Digest) memoable);
    }
}
