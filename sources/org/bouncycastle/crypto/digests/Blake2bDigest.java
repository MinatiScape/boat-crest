package org.bouncycastle.crypto.digests;

import com.htsmart.wristband2.WristbandManager;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class Blake2bDigest implements ExtendedDigest {
    public static final long[] m = {7640891576956012808L, -4942790177534073029L, 4354685564936845355L, -6534734903238641935L, 5840696475078001361L, -7276294671716946913L, 2270897969802886507L, 6620516959819538809L};
    public static final byte[][] n = {new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}, new byte[]{11, 8, 12, 0, 5, 2, 15, 13, 10, 14, 3, 6, 7, 1, 9, 4}, new byte[]{7, 9, 3, 1, 13, 12, 11, 14, 2, 6, 5, 10, 4, 0, 15, 8}, new byte[]{9, 0, 5, 7, 2, 4, 10, 15, 14, 1, 11, 12, 6, 8, 3, 13}, new byte[]{2, 12, 6, 10, 0, 11, 8, 3, 4, 13, 7, 5, 15, 14, 1, 9}, new byte[]{12, 5, 1, 15, 14, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, 11}, new byte[]{13, 11, 7, 14, 12, 1, 3, 9, 5, 0, 15, 4, 8, 6, 2, 10}, new byte[]{6, 15, 14, 9, 11, 3, 0, 8, 12, 2, 13, 7, 1, 4, 10, 5}, new byte[]{10, 2, 8, 4, 7, 6, 1, 5, 15, 11, 9, 14, 3, 12, 13, 0}, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, 12, 0, 2, 11, 7, 5, 3}};
    public static int o = 12;

    /* renamed from: a  reason: collision with root package name */
    public int f14633a;
    public int b;
    public byte[] c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public int g;
    public long[] h;
    public long[] i;
    public long j;
    public long k;
    public long l;

    public Blake2bDigest() {
        this(512);
    }

    public Blake2bDigest(int i) {
        this.f14633a = 64;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = new long[16];
        this.i = null;
        this.j = 0L;
        this.k = 0L;
        this.l = 0L;
        if (i != 160 && i != 256 && i != 384 && i != 512) {
            throw new IllegalArgumentException("Blake2b digest restricted to one of [160, 256, 384, 512]");
        }
        this.f = new byte[128];
        this.b = 0;
        this.f14633a = i / 8;
        d();
    }

    public Blake2bDigest(Blake2bDigest blake2bDigest) {
        this.f14633a = 64;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = new long[16];
        this.i = null;
        this.j = 0L;
        this.k = 0L;
        this.l = 0L;
        this.g = blake2bDigest.g;
        this.f = Arrays.clone(blake2bDigest.f);
        this.b = blake2bDigest.b;
        this.e = Arrays.clone(blake2bDigest.e);
        this.f14633a = blake2bDigest.f14633a;
        this.i = Arrays.clone(blake2bDigest.i);
        this.d = Arrays.clone(blake2bDigest.d);
        this.c = Arrays.clone(blake2bDigest.c);
        this.j = blake2bDigest.j;
        this.k = blake2bDigest.k;
        this.l = blake2bDigest.l;
    }

    public Blake2bDigest(byte[] bArr) {
        this.f14633a = 64;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = new long[16];
        this.i = null;
        this.j = 0L;
        this.k = 0L;
        this.l = 0L;
        this.f = new byte[128];
        if (bArr != null) {
            byte[] bArr2 = new byte[bArr.length];
            this.e = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
            if (bArr.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.b = bArr.length;
            System.arraycopy(bArr, 0, this.f, 0, bArr.length);
            this.g = 128;
        }
        this.f14633a = 64;
        d();
    }

    public Blake2bDigest(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        this.f14633a = 64;
        this.b = 0;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = 0;
        this.h = new long[16];
        this.i = null;
        this.j = 0L;
        this.k = 0L;
        this.l = 0L;
        this.f = new byte[128];
        if (i < 1 || i > 64) {
            throw new IllegalArgumentException("Invalid digest length (required: 1 - 64)");
        }
        this.f14633a = i;
        if (bArr2 != null) {
            if (bArr2.length != 16) {
                throw new IllegalArgumentException("salt length must be exactly 16 bytes");
            }
            byte[] bArr4 = new byte[16];
            this.c = bArr4;
            System.arraycopy(bArr2, 0, bArr4, 0, bArr2.length);
        }
        if (bArr3 != null) {
            if (bArr3.length != 16) {
                throw new IllegalArgumentException("personalization length must be exactly 16 bytes");
            }
            byte[] bArr5 = new byte[16];
            this.d = bArr5;
            System.arraycopy(bArr3, 0, bArr5, 0, bArr3.length);
        }
        if (bArr != null) {
            byte[] bArr6 = new byte[bArr.length];
            this.e = bArr6;
            System.arraycopy(bArr, 0, bArr6, 0, bArr.length);
            if (bArr.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.b = bArr.length;
            System.arraycopy(bArr, 0, this.f, 0, bArr.length);
            this.g = 128;
        }
        d();
    }

    public final void a(long j, long j2, int i, int i2, int i3, int i4) {
        long[] jArr = this.h;
        jArr[i] = jArr[i] + jArr[i2] + j;
        jArr[i4] = g(jArr[i4] ^ jArr[i], 32);
        long[] jArr2 = this.h;
        jArr2[i3] = jArr2[i3] + jArr2[i4];
        jArr2[i2] = g(jArr2[i2] ^ jArr2[i3], 24);
        long[] jArr3 = this.h;
        jArr3[i] = jArr3[i] + jArr3[i2] + j2;
        jArr3[i4] = g(jArr3[i4] ^ jArr3[i], 16);
        long[] jArr4 = this.h;
        jArr4[i3] = jArr4[i3] + jArr4[i4];
        jArr4[i2] = g(jArr4[i2] ^ jArr4[i3], 63);
    }

    public final long b(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    public final void c(byte[] bArr, int i) {
        e();
        long[] jArr = new long[16];
        int i2 = 0;
        for (int i3 = 0; i3 < 16; i3++) {
            jArr[i3] = b(bArr, (i3 * 8) + i);
        }
        for (int i4 = 0; i4 < o; i4++) {
            byte[][] bArr2 = n;
            a(jArr[bArr2[i4][0]], jArr[bArr2[i4][1]], 0, 4, 8, 12);
            a(jArr[bArr2[i4][2]], jArr[bArr2[i4][3]], 1, 5, 9, 13);
            a(jArr[bArr2[i4][4]], jArr[bArr2[i4][5]], 2, 6, 10, 14);
            a(jArr[bArr2[i4][6]], jArr[bArr2[i4][7]], 3, 7, 11, 15);
            a(jArr[bArr2[i4][8]], jArr[bArr2[i4][9]], 0, 5, 10, 15);
            a(jArr[bArr2[i4][10]], jArr[bArr2[i4][11]], 1, 6, 11, 12);
            a(jArr[bArr2[i4][12]], jArr[bArr2[i4][13]], 2, 7, 8, 13);
            a(jArr[bArr2[i4][14]], jArr[bArr2[i4][15]], 3, 4, 9, 14);
        }
        while (true) {
            long[] jArr2 = this.i;
            if (i2 >= jArr2.length) {
                return;
            }
            long j = jArr2[i2];
            long[] jArr3 = this.h;
            jArr2[i2] = (j ^ jArr3[i2]) ^ jArr3[i2 + 8];
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
            long[] jArr = m;
            long[] jArr2 = {jArr[0] ^ ((this.f14633a | (this.b << 8)) | 16842752), jArr[1], jArr[2], jArr[3], jArr[4], jArr[5]};
            byte[] bArr = this.c;
            if (bArr != null) {
                jArr2[4] = jArr2[4] ^ b(bArr, 0);
                long[] jArr3 = this.i;
                jArr3[5] = jArr3[5] ^ b(this.c, 8);
            }
            long[] jArr4 = this.i;
            jArr4[6] = jArr[6];
            jArr4[7] = jArr[7];
            byte[] bArr2 = this.d;
            if (bArr2 != null) {
                jArr4[6] = b(bArr2, 0) ^ jArr4[6];
                long[] jArr5 = this.i;
                jArr5[7] = jArr5[7] ^ b(this.d, 8);
            }
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        long[] jArr;
        int i2;
        this.l = -1L;
        long j = this.j;
        int i3 = this.g;
        long j2 = j + i3;
        this.j = j2;
        if (i3 > 0 && j2 == 0) {
            this.k++;
        }
        c(this.f, 0);
        Arrays.fill(this.f, (byte) 0);
        Arrays.fill(this.h, 0L);
        int i4 = 0;
        while (true) {
            jArr = this.i;
            if (i4 >= jArr.length || (i2 = i4 * 8) >= this.f14633a) {
                break;
            }
            byte[] f = f(jArr[i4]);
            int i5 = this.f14633a;
            if (i2 < i5 - 8) {
                System.arraycopy(f, 0, bArr, i2 + i, 8);
            } else {
                System.arraycopy(f, 0, bArr, i + i2, i5 - i2);
            }
            i4++;
        }
        Arrays.fill(jArr, 0L);
        reset();
        return this.f14633a;
    }

    public final void e() {
        long[] jArr = this.i;
        System.arraycopy(jArr, 0, this.h, 0, jArr.length);
        long[] jArr2 = m;
        System.arraycopy(jArr2, 0, this.h, this.i.length, 4);
        long[] jArr3 = this.h;
        jArr3[12] = this.j ^ jArr2[4];
        jArr3[13] = this.k ^ jArr2[5];
        jArr3[14] = this.l ^ jArr2[6];
        jArr3[15] = jArr2[7];
    }

    public final byte[] f(long j) {
        return new byte[]{(byte) j, (byte) (j >> 8), (byte) (j >> 16), (byte) (j >> 24), (byte) (j >> 32), (byte) (j >> 40), (byte) (j >> 48), (byte) (j >> 56)};
    }

    public final long g(long j, int i) {
        return (j << (64 - i)) | (j >>> i);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "Blake2b";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 128;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return this.f14633a;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.g = 0;
        this.l = 0L;
        this.j = 0L;
        this.k = 0L;
        this.i = null;
        Arrays.fill(this.f, (byte) 0);
        byte[] bArr = this.e;
        if (bArr != null) {
            System.arraycopy(bArr, 0, this.f, 0, bArr.length);
            this.g = 128;
        }
        d();
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        int i = this.g;
        if (128 - i != 0) {
            this.f[i] = b;
            this.g = i + 1;
            return;
        }
        long j = this.j + 128;
        this.j = j;
        if (j == 0) {
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
            i3 = 128 - i4;
            if (i3 >= i2) {
                System.arraycopy(bArr, i, this.f, i4, i2);
                this.g += i2;
            }
            System.arraycopy(bArr, i, this.f, i4, i3);
            long j = this.j + 128;
            this.j = j;
            if (j == 0) {
                this.k++;
            }
            c(this.f, 0);
            this.g = 0;
            Arrays.fill(this.f, (byte) 0);
        } else {
            i3 = 0;
        }
        int i5 = i2 + i;
        int i6 = i5 + WristbandManager.SYNC_STATE_FAILED_UNKNOWN;
        int i7 = i + i3;
        while (i7 < i6) {
            long j2 = this.j + 128;
            this.j = j2;
            if (j2 == 0) {
                this.k++;
            }
            c(bArr, i7);
            i7 += 128;
        }
        i2 = i5 - i7;
        System.arraycopy(bArr, i7, this.f, 0, i2);
        this.g += i2;
    }
}
