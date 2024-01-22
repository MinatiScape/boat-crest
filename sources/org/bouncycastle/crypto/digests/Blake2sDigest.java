package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class Blake2sDigest implements ExtendedDigest {
    public static final int[] m = {1779033703, -1150833019, 1013904242, -1521486534, 1359893119, -1694144372, 528734635, 1541459225};
    public static final byte[][] n = {new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}, new byte[]{11, 8, 12, 0, 5, 2, 15, 13, 10, 14, 3, 6, 7, 1, 9, 4}, new byte[]{7, 9, 3, 1, 13, 12, 11, 14, 2, 6, 5, 10, 4, 0, 15, 8}, new byte[]{9, 0, 5, 7, 2, 4, 10, 15, 14, 1, 11, 12, 6, 8, 3, 13}, new byte[]{2, 12, 6, 10, 0, 11, 8, 3, 4, 13, 7, 5, 15, 14, 1, 9}, new byte[]{12, 5, 1, 15, 14, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, 11}, new byte[]{13, 11, 7, 14, 12, 1, 3, 9, 5, 0, 15, 4, 8, 6, 2, 10}, new byte[]{6, 15, 14, 9, 11, 3, 0, 8, 12, 2, 13, 7, 1, 4, 10, 5}, new byte[]{10, 2, 8, 4, 7, 6, 1, 5, 15, 11, 9, 14, 3, 12, 13, 0}};

    /* renamed from: a  reason: collision with root package name */
    public int f14634a;
    public int b;
    public byte[] c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public int g;
    public int[] h;
    public int[] i;
    public int j;
    public int k;
    public int l;

    public Blake2sDigest() {
        this(256);
    }

    public Blake2sDigest(int i) {
        this.f14634a = 32;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = new int[16];
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        if (i != 128 && i != 160 && i != 224 && i != 256) {
            throw new IllegalArgumentException("BLAKE2s digest restricted to one of [128, 160, 224, 256]");
        }
        this.f = new byte[64];
        this.b = 0;
        this.f14634a = i / 8;
        d();
    }

    public Blake2sDigest(Blake2sDigest blake2sDigest) {
        this.f14634a = 32;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = new int[16];
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.g = blake2sDigest.g;
        this.f = Arrays.clone(blake2sDigest.f);
        this.b = blake2sDigest.b;
        this.e = Arrays.clone(blake2sDigest.e);
        this.f14634a = blake2sDigest.f14634a;
        this.i = Arrays.clone(blake2sDigest.i);
        this.d = Arrays.clone(blake2sDigest.d);
    }

    public Blake2sDigest(byte[] bArr) {
        this.f14634a = 32;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = new int[16];
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.f = new byte[64];
        if (bArr != null) {
            if (bArr.length > 32) {
                throw new IllegalArgumentException("Keys > 32 are not supported");
            }
            byte[] bArr2 = new byte[bArr.length];
            this.e = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            this.b = bArr.length;
            System.arraycopy(bArr, 0, this.f, 0, bArr.length);
            this.g = 64;
        }
        this.f14634a = 32;
        d();
    }

    public Blake2sDigest(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        this.f14634a = 32;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = new int[16];
        this.i = null;
        this.j = 0;
        this.k = 0;
        this.l = 0;
        this.f = new byte[64];
        if (i < 1 || i > 32) {
            throw new IllegalArgumentException("Invalid digest length (required: 1 - 32)");
        }
        this.f14634a = i;
        if (bArr2 != null) {
            if (bArr2.length != 8) {
                throw new IllegalArgumentException("Salt length must be exactly 8 bytes");
            }
            byte[] bArr4 = new byte[8];
            this.c = bArr4;
            System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
        }
        if (bArr3 != null) {
            if (bArr3.length != 8) {
                throw new IllegalArgumentException("Personalization length must be exactly 8 bytes");
            }
            byte[] bArr5 = new byte[8];
            this.d = bArr5;
            System.arraycopy(bArr3, 0, bArr5, 0, bArr3.length);
        }
        if (bArr != null) {
            if (bArr.length > 32) {
                throw new IllegalArgumentException("Keys > 32 bytes are not supported");
            }
            byte[] bArr6 = new byte[bArr.length];
            this.e = bArr6;
            System.arraycopy(bArr, 0, bArr6, 0, bArr.length);
            this.b = bArr.length;
            System.arraycopy(bArr, 0, this.f, 0, bArr.length);
            this.g = 64;
        }
        d();
    }

    public final void a(int i, int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = this.h;
        iArr[i3] = iArr[i3] + iArr[i4] + i;
        iArr[i6] = g(iArr[i6] ^ iArr[i3], 16);
        int[] iArr2 = this.h;
        iArr2[i5] = iArr2[i5] + iArr2[i6];
        iArr2[i4] = g(iArr2[i4] ^ iArr2[i5], 12);
        int[] iArr3 = this.h;
        iArr3[i3] = iArr3[i3] + iArr3[i4] + i2;
        iArr3[i6] = g(iArr3[i6] ^ iArr3[i3], 8);
        int[] iArr4 = this.h;
        iArr4[i5] = iArr4[i5] + iArr4[i6];
        iArr4[i4] = g(iArr4[i4] ^ iArr4[i5], 7);
    }

    public final int b(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public final void c(byte[] bArr, int i) {
        e();
        int[] iArr = new int[16];
        int i2 = 0;
        for (int i3 = 0; i3 < 16; i3++) {
            iArr[i3] = b(bArr, (i3 * 4) + i);
        }
        for (int i4 = 0; i4 < 10; i4++) {
            byte[][] bArr2 = n;
            a(iArr[bArr2[i4][0]], iArr[bArr2[i4][1]], 0, 4, 8, 12);
            a(iArr[bArr2[i4][2]], iArr[bArr2[i4][3]], 1, 5, 9, 13);
            a(iArr[bArr2[i4][4]], iArr[bArr2[i4][5]], 2, 6, 10, 14);
            a(iArr[bArr2[i4][6]], iArr[bArr2[i4][7]], 3, 7, 11, 15);
            a(iArr[bArr2[i4][8]], iArr[bArr2[i4][9]], 0, 5, 10, 15);
            a(iArr[bArr2[i4][10]], iArr[bArr2[i4][11]], 1, 6, 11, 12);
            a(iArr[bArr2[i4][12]], iArr[bArr2[i4][13]], 2, 7, 8, 13);
            a(iArr[bArr2[i4][14]], iArr[bArr2[i4][15]], 3, 4, 9, 14);
        }
        while (true) {
            int[] iArr2 = this.i;
            if (i2 >= iArr2.length) {
                return;
            }
            int i5 = iArr2[i2];
            int[] iArr3 = this.h;
            iArr2[i2] = (i5 ^ iArr3[i2]) ^ iArr3[i2 + 8];
            i2++;
        }
    }

    public void clearKey() {
        byte[] bArr = this.e;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
            Arrays.fill(this.f, (byte) 0);
        }
    }

    public void clearSalt() {
        byte[] bArr = this.c;
        if (bArr != null) {
            Arrays.fill(bArr, (byte) 0);
        }
    }

    public final void d() {
        if (this.i == null) {
            this.i = r1;
            int[] iArr = m;
            int[] iArr2 = {(((this.b << 8) | this.f14634a) | 16842752) ^ iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], iArr[5]};
            byte[] bArr = this.c;
            if (bArr != null) {
                iArr2[4] = b(bArr, 0) ^ iArr2[4];
                int[] iArr3 = this.i;
                iArr3[5] = iArr3[5] ^ b(this.c, 4);
            }
            int[] iArr4 = this.i;
            iArr4[6] = iArr[6];
            iArr4[7] = iArr[7];
            byte[] bArr2 = this.d;
            if (bArr2 != null) {
                iArr4[6] = b(bArr2, 0) ^ iArr4[6];
                int[] iArr5 = this.i;
                iArr5[7] = b(this.d, 4) ^ iArr5[7];
            }
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        int[] iArr;
        int i2;
        this.l = -1;
        int i3 = this.j;
        int i4 = this.g;
        int i5 = i3 + i4;
        this.j = i5;
        if (i5 < 0 && i4 > (-i5)) {
            this.k++;
        }
        c(this.f, 0);
        Arrays.fill(this.f, (byte) 0);
        Arrays.fill(this.h, 0);
        int i6 = 0;
        while (true) {
            iArr = this.i;
            if (i6 >= iArr.length || (i2 = i6 * 4) >= this.f14634a) {
                break;
            }
            byte[] f = f(iArr[i6]);
            int i7 = this.f14634a;
            if (i2 < i7 - 4) {
                System.arraycopy(f, 0, bArr, i2 + i, 4);
            } else {
                System.arraycopy(f, 0, bArr, i + i2, i7 - i2);
            }
            i6++;
        }
        Arrays.fill(iArr, 0);
        reset();
        return this.f14634a;
    }

    public final void e() {
        int[] iArr = this.i;
        System.arraycopy(iArr, 0, this.h, 0, iArr.length);
        int[] iArr2 = m;
        System.arraycopy(iArr2, 0, this.h, this.i.length, 4);
        int[] iArr3 = this.h;
        iArr3[12] = this.j ^ iArr2[4];
        iArr3[13] = this.k ^ iArr2[5];
        iArr3[14] = this.l ^ iArr2[6];
        iArr3[15] = iArr2[7];
    }

    public final byte[] f(int i) {
        return new byte[]{(byte) i, (byte) (i >> 8), (byte) (i >> 16), (byte) (i >> 24)};
    }

    public final int g(int i, int i2) {
        return (i << (32 - i2)) | (i >>> i2);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "BLAKE2s";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 64;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.f14634a;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.g = 0;
        this.l = 0;
        this.j = 0;
        this.k = 0;
        this.i = null;
        Arrays.fill(this.f, (byte) 0);
        byte[] bArr = this.e;
        if (bArr != null) {
            System.arraycopy(bArr, 0, this.f, 0, bArr.length);
            this.g = 64;
        }
        d();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int i = this.g;
        if (64 - i != 0) {
            this.f[i] = b;
            this.g = i + 1;
            return;
        }
        int i2 = this.j + 64;
        this.j = i2;
        if (i2 == 0) {
            this.k++;
        }
        c(this.f, 0);
        Arrays.fill(this.f, (byte) 0);
        this.f[0] = b;
        this.g = 1;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr == null || i2 == 0) {
            return;
        }
        int i4 = this.g;
        if (i4 != 0) {
            i3 = 64 - i4;
            if (i3 >= i2) {
                System.arraycopy(bArr, i, this.f, i4, i2);
                this.g += i2;
            }
            System.arraycopy(bArr, i, this.f, i4, i3);
            int i5 = this.j + 64;
            this.j = i5;
            if (i5 == 0) {
                this.k++;
            }
            c(this.f, 0);
            this.g = 0;
            Arrays.fill(this.f, (byte) 0);
        } else {
            i3 = 0;
        }
        int i6 = i2 + i;
        int i7 = i6 - 64;
        int i8 = i + i3;
        while (i8 < i7) {
            int i9 = this.j + 64;
            this.j = i9;
            if (i9 == 0) {
                this.k++;
            }
            c(bArr, i8);
            i8 += 64;
        }
        i2 = i6 - i8;
        System.arraycopy(bArr, i8, this.f, 0, i2);
        this.g += i2;
    }
}
