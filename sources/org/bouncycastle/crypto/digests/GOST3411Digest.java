package org.bouncycastle.crypto.digests;

import java.lang.reflect.Array;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.ExtendedDigest;
import org.bouncycastle.crypto.engines.GOST28147Engine;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithSBox;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class GOST3411Digest implements ExtendedDigest, Memoable {
    public static final byte[] s = {0, -1, 0, -1, 0, -1, 0, -1, -1, 0, -1, 0, -1, 0, -1, 0, 0, -1, -1, 0, -1, 0, 0, -1, -1, 0, 0, 0, -1, -1, 0, -1};

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14636a;
    public byte[] b;
    public byte[] c;
    public byte[] d;
    public byte[][] e;
    public byte[] f;
    public int g;
    public long h;
    public BlockCipher i;
    public byte[] j;
    public byte[] k;
    public byte[] l;
    public short[] m;
    public short[] n;
    public byte[] o;
    public byte[] p;
    public byte[] q;
    public byte[] r;

    public GOST3411Digest() {
        this.f14636a = new byte[32];
        this.b = new byte[32];
        this.c = new byte[32];
        this.d = new byte[32];
        this.e = (byte[][]) Array.newInstance(byte.class, 4, 32);
        this.f = new byte[32];
        this.i = new GOST28147Engine();
        this.k = new byte[32];
        this.l = new byte[8];
        this.m = new short[16];
        this.n = new short[16];
        this.o = new byte[32];
        this.p = new byte[32];
        this.q = new byte[32];
        this.r = new byte[32];
        byte[] sBox = GOST28147Engine.getSBox("D-A");
        this.j = sBox;
        this.i.init(true, new ParametersWithSBox(null, sBox));
        reset();
    }

    public GOST3411Digest(GOST3411Digest gOST3411Digest) {
        this.f14636a = new byte[32];
        this.b = new byte[32];
        this.c = new byte[32];
        this.d = new byte[32];
        this.e = (byte[][]) Array.newInstance(byte.class, 4, 32);
        this.f = new byte[32];
        this.i = new GOST28147Engine();
        this.k = new byte[32];
        this.l = new byte[8];
        this.m = new short[16];
        this.n = new short[16];
        this.o = new byte[32];
        this.p = new byte[32];
        this.q = new byte[32];
        this.r = new byte[32];
        reset(gOST3411Digest);
    }

    public GOST3411Digest(byte[] bArr) {
        this.f14636a = new byte[32];
        this.b = new byte[32];
        this.c = new byte[32];
        this.d = new byte[32];
        this.e = (byte[][]) Array.newInstance(byte.class, 4, 32);
        this.f = new byte[32];
        this.i = new GOST28147Engine();
        this.k = new byte[32];
        this.l = new byte[8];
        this.m = new short[16];
        this.n = new short[16];
        this.o = new byte[32];
        this.p = new byte[32];
        this.q = new byte[32];
        this.r = new byte[32];
        byte[] clone = Arrays.clone(bArr);
        this.j = clone;
        this.i.init(true, new ParametersWithSBox(null, clone));
        reset();
    }

    public final byte[] a(byte[] bArr) {
        for (int i = 0; i < 8; i++) {
            this.l[i] = (byte) (bArr[i] ^ bArr[i + 8]);
        }
        System.arraycopy(bArr, 8, bArr, 0, 24);
        System.arraycopy(this.l, 0, bArr, 24, 8);
        return bArr;
    }

    public final void b(byte[] bArr, byte[] bArr2, int i, byte[] bArr3, int i2) {
        this.i.init(true, new KeyParameter(bArr));
        this.i.processBlock(bArr3, i2, bArr2, i);
    }

    public final byte[] c(byte[] bArr) {
        for (int i = 0; i < 8; i++) {
            byte[] bArr2 = this.k;
            int i2 = i * 4;
            bArr2[i2] = bArr[i];
            bArr2[i2 + 1] = bArr[i + 8];
            bArr2[i2 + 2] = bArr[i + 16];
            bArr2[i2 + 3] = bArr[i + 24];
        }
        return this.k;
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new GOST3411Digest(this);
    }

    public final void d(byte[] bArr, short[] sArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            int i2 = i * 2;
            sArr[i] = (short) ((bArr[i2] & 255) | ((bArr[i2 + 1] << 8) & 65280));
        }
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        f();
        byte[] bArr2 = this.f14636a;
        System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
        reset();
        return 32;
    }

    public final void e(short[] sArr, byte[] bArr) {
        for (int i = 0; i < bArr.length / 2; i++) {
            int i2 = i * 2;
            bArr[i2 + 1] = (byte) (sArr[i] >> 8);
            bArr[i2] = (byte) sArr[i];
        }
    }

    public final void f() {
        Pack.longToLittleEndian(this.h * 8, this.b, 0);
        while (this.g != 0) {
            update((byte) 0);
        }
        processBlock(this.b, 0);
        processBlock(this.d, 0);
    }

    public final void g(byte[] bArr) {
        d(bArr, this.m);
        short[] sArr = this.n;
        short[] sArr2 = this.m;
        sArr[15] = (short) (((((sArr2[0] ^ sArr2[1]) ^ sArr2[2]) ^ sArr2[3]) ^ sArr2[12]) ^ sArr2[15]);
        System.arraycopy(sArr2, 1, sArr, 0, 15);
        e(this.n, bArr);
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "GOST3411";
    }

    @Override // org.bouncycastle.crypto.ExtendedDigest
    public int getByteLength() {
        return 32;
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 32;
    }

    public final void h(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.d;
            if (i == bArr2.length) {
                return;
            }
            int i3 = (bArr2[i] & 255) + (bArr[i] & 255) + i2;
            bArr2[i] = (byte) i3;
            i2 = i3 >>> 8;
            i++;
        }
    }

    public void processBlock(byte[] bArr, int i) {
        System.arraycopy(bArr, i, this.c, 0, 32);
        System.arraycopy(this.f14636a, 0, this.p, 0, 32);
        System.arraycopy(this.c, 0, this.q, 0, 32);
        for (int i2 = 0; i2 < 32; i2++) {
            this.r[i2] = (byte) (this.p[i2] ^ this.q[i2]);
        }
        b(c(this.r), this.o, 0, this.f14636a, 0);
        for (int i3 = 1; i3 < 4; i3++) {
            byte[] a2 = a(this.p);
            for (int i4 = 0; i4 < 32; i4++) {
                this.p[i4] = (byte) (a2[i4] ^ this.e[i3][i4]);
            }
            this.q = a(a(this.q));
            for (int i5 = 0; i5 < 32; i5++) {
                this.r[i5] = (byte) (this.p[i5] ^ this.q[i5]);
            }
            int i6 = i3 * 8;
            b(c(this.r), this.o, i6, this.f14636a, i6);
        }
        for (int i7 = 0; i7 < 12; i7++) {
            g(this.o);
        }
        for (int i8 = 0; i8 < 32; i8++) {
            byte[] bArr2 = this.o;
            bArr2[i8] = (byte) (bArr2[i8] ^ this.c[i8]);
        }
        g(this.o);
        for (int i9 = 0; i9 < 32; i9++) {
            byte[] bArr3 = this.o;
            bArr3[i9] = (byte) (this.f14636a[i9] ^ bArr3[i9]);
        }
        for (int i10 = 0; i10 < 61; i10++) {
            g(this.o);
        }
        byte[] bArr4 = this.o;
        byte[] bArr5 = this.f14636a;
        System.arraycopy(bArr4, 0, bArr5, 0, bArr5.length);
    }

    @Override // org.bouncycastle.crypto.Digest
    public void reset() {
        this.h = 0L;
        this.g = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.f14636a;
            if (i >= bArr.length) {
                break;
            }
            bArr[i] = 0;
            i++;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = this.b;
            if (i2 >= bArr2.length) {
                break;
            }
            bArr2[i2] = 0;
            i2++;
        }
        int i3 = 0;
        while (true) {
            byte[] bArr3 = this.c;
            if (i3 >= bArr3.length) {
                break;
            }
            bArr3[i3] = 0;
            i3++;
        }
        int i4 = 0;
        while (true) {
            byte[][] bArr4 = this.e;
            if (i4 >= bArr4[1].length) {
                break;
            }
            bArr4[1][i4] = 0;
            i4++;
        }
        int i5 = 0;
        while (true) {
            byte[][] bArr5 = this.e;
            if (i5 >= bArr5[3].length) {
                break;
            }
            bArr5[3][i5] = 0;
            i5++;
        }
        int i6 = 0;
        while (true) {
            byte[] bArr6 = this.d;
            if (i6 >= bArr6.length) {
                break;
            }
            bArr6[i6] = 0;
            i6++;
        }
        int i7 = 0;
        while (true) {
            byte[] bArr7 = this.f;
            if (i7 >= bArr7.length) {
                byte[] bArr8 = s;
                System.arraycopy(bArr8, 0, this.e[2], 0, bArr8.length);
                return;
            }
            bArr7[i7] = 0;
            i7++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        GOST3411Digest gOST3411Digest = (GOST3411Digest) memoable;
        byte[] bArr = gOST3411Digest.j;
        this.j = bArr;
        this.i.init(true, new ParametersWithSBox(null, bArr));
        reset();
        byte[] bArr2 = gOST3411Digest.f14636a;
        System.arraycopy(bArr2, 0, this.f14636a, 0, bArr2.length);
        byte[] bArr3 = gOST3411Digest.b;
        System.arraycopy(bArr3, 0, this.b, 0, bArr3.length);
        byte[] bArr4 = gOST3411Digest.c;
        System.arraycopy(bArr4, 0, this.c, 0, bArr4.length);
        byte[] bArr5 = gOST3411Digest.d;
        System.arraycopy(bArr5, 0, this.d, 0, bArr5.length);
        byte[][] bArr6 = gOST3411Digest.e;
        System.arraycopy(bArr6[1], 0, this.e[1], 0, bArr6[1].length);
        byte[][] bArr7 = gOST3411Digest.e;
        System.arraycopy(bArr7[2], 0, this.e[2], 0, bArr7[2].length);
        byte[][] bArr8 = gOST3411Digest.e;
        System.arraycopy(bArr8[3], 0, this.e[3], 0, bArr8[3].length);
        byte[] bArr9 = gOST3411Digest.f;
        System.arraycopy(bArr9, 0, this.f, 0, bArr9.length);
        this.g = gOST3411Digest.g;
        this.h = gOST3411Digest.h;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte b) {
        byte[] bArr = this.f;
        int i = this.g;
        int i2 = i + 1;
        this.g = i2;
        bArr[i] = b;
        if (i2 == bArr.length) {
            h(bArr);
            processBlock(this.f, 0);
            this.g = 0;
        }
        this.h++;
    }

    @Override // org.bouncycastle.crypto.Digest
    public void update(byte[] bArr, int i, int i2) {
        while (this.g != 0 && i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
        while (true) {
            byte[] bArr2 = this.f;
            if (i2 <= bArr2.length) {
                break;
            }
            System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
            h(this.f);
            processBlock(this.f, 0);
            byte[] bArr3 = this.f;
            i += bArr3.length;
            i2 -= bArr3.length;
            this.h += bArr3.length;
        }
        while (i2 > 0) {
            update(bArr[i]);
            i++;
            i2--;
        }
    }
}
