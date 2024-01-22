package org.bouncycastle.pqc.crypto.gmss;

import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.reflect.Array;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes13.dex */
public class GMSSRootSig {

    /* renamed from: a  reason: collision with root package name */
    public Digest f15291a;
    public int b;
    public int c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public int g;
    public GMSSRandom h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public long o;
    public long p;
    public int q;
    public int r;
    public int s;
    public byte[] t;

    public GMSSRootSig(Digest digest, int i, int i2) {
        this.f15291a = digest;
        this.h = new GMSSRandom(digest);
        int digestSize = this.f15291a.getDigestSize();
        this.b = digestSize;
        this.g = i;
        this.s = i2;
        this.j = (1 << i) - 1;
        this.i = (int) Math.ceil((digestSize << 3) / i);
    }

    public GMSSRootSig(Digest digest, byte[][] bArr, int[] iArr) {
        this.f15291a = digest;
        this.h = new GMSSRandom(digest);
        this.m = iArr[0];
        this.l = iArr[1];
        this.n = iArr[2];
        this.k = iArr[3];
        this.q = iArr[4];
        this.c = iArr[5];
        this.s = iArr[6];
        this.g = iArr[7];
        this.r = iArr[8];
        int digestSize = this.f15291a.getDigestSize();
        this.b = digestSize;
        int i = this.g;
        this.j = (1 << i) - 1;
        this.i = (int) Math.ceil((digestSize << 3) / i);
        this.d = bArr[0];
        this.t = bArr[1];
        this.e = bArr[2];
        this.f = bArr[3];
        this.o = ((bArr[4][1] & 255) << 8) | (bArr[4][0] & 255) | ((bArr[4][2] & 255) << 16) | ((bArr[4][3] & 255) << 24) | ((bArr[4][4] & 255) << 32) | ((bArr[4][5] & 255) << 40) | ((bArr[4][6] & 255) << 48) | ((bArr[4][7] & 255) << 56);
        this.p = (bArr[4][8] & 255) | ((bArr[4][9] & 255) << 8) | ((bArr[4][10] & 255) << 16) | ((bArr[4][11] & 255) << 24) | ((bArr[4][12] & 255) << 32) | ((bArr[4][13] & 255) << 40) | ((bArr[4][14] & 255) << 48) | ((bArr[4][15] & 255) << 56);
    }

    public final void a() {
        long j;
        int i = this.g;
        if (8 % i == 0) {
            int i2 = this.l;
            if (i2 == 0) {
                this.d = this.h.nextSeed(this.t);
                int i3 = this.n;
                if (i3 < this.b) {
                    byte[] bArr = this.e;
                    this.l = bArr[i3] & this.j;
                    bArr[i3] = (byte) (bArr[i3] >>> this.g);
                } else {
                    int i4 = this.r;
                    this.l = this.j & i4;
                    this.r = i4 >>> this.g;
                }
            } else if (i2 > 0) {
                Digest digest = this.f15291a;
                byte[] bArr2 = this.d;
                digest.update(bArr2, 0, bArr2.length);
                byte[] bArr3 = new byte[this.f15291a.getDigestSize()];
                this.d = bArr3;
                this.f15291a.doFinal(bArr3, 0);
                this.l--;
            }
            if (this.l == 0) {
                byte[] bArr4 = this.d;
                byte[] bArr5 = this.f;
                int i5 = this.m;
                int i6 = this.b;
                System.arraycopy(bArr4, 0, bArr5, i5 * i6, i6);
                int i7 = this.m + 1;
                this.m = i7;
                if (i7 % (8 / this.g) == 0) {
                    this.n++;
                    return;
                }
                return;
            }
            return;
        }
        if (i < 8) {
            int i8 = this.l;
            if (i8 == 0) {
                int i9 = this.m;
                if (i9 % 8 == 0) {
                    int i10 = this.n;
                    int i11 = this.b;
                    if (i10 < i11) {
                        this.p = 0L;
                        if (i9 < ((i11 / i) << 3)) {
                            for (int i12 = 0; i12 < this.g; i12++) {
                                long j2 = this.p;
                                byte[] bArr6 = this.e;
                                int i13 = this.n;
                                this.p = j2 ^ ((bArr6[i13] & 255) << (i12 << 3));
                                this.n = i13 + 1;
                            }
                        } else {
                            for (int i14 = 0; i14 < this.b % this.g; i14++) {
                                long j3 = this.p;
                                byte[] bArr7 = this.e;
                                int i15 = this.n;
                                this.p = j3 ^ ((bArr7[i15] & 255) << (i14 << 3));
                                this.n = i15 + 1;
                            }
                        }
                    }
                }
                if (this.m == this.i) {
                    this.p = this.r;
                }
                this.l = (int) (this.p & this.j);
                this.d = this.h.nextSeed(this.t);
            } else if (i8 > 0) {
                Digest digest2 = this.f15291a;
                byte[] bArr8 = this.d;
                digest2.update(bArr8, 0, bArr8.length);
                byte[] bArr9 = new byte[this.f15291a.getDigestSize()];
                this.d = bArr9;
                this.f15291a.doFinal(bArr9, 0);
                this.l--;
            }
            if (this.l != 0) {
                return;
            }
            byte[] bArr10 = this.d;
            byte[] bArr11 = this.f;
            int i16 = this.m;
            int i17 = this.b;
            System.arraycopy(bArr10, 0, bArr11, i16 * i17, i17);
            this.p >>>= this.g;
        } else if (i >= 57) {
            return;
        } else {
            long j4 = this.o;
            if (j4 == 0) {
                this.p = 0L;
                this.n = 0;
                int i18 = this.k;
                int i19 = i18 % 8;
                int i20 = i18 >>> 3;
                int i21 = this.b;
                if (i20 < i21) {
                    if (i18 <= (i21 << 3) - i) {
                        int i22 = i18 + i;
                        this.k = i22;
                        i21 = (i22 + 7) >>> 3;
                    } else {
                        this.k = i18 + i;
                    }
                    while (true) {
                        j = this.p;
                        if (i20 >= i21) {
                            break;
                        }
                        int i23 = this.n;
                        this.p = j ^ ((this.e[i20] & 255) << (i23 << 3));
                        this.n = i23 + 1;
                        i20++;
                    }
                    long j5 = j >>> i19;
                    this.p = j5;
                    this.o = j5 & this.j;
                } else {
                    int i24 = this.r;
                    this.o = this.j & i24;
                    this.r = i24 >>> i;
                }
                this.d = this.h.nextSeed(this.t);
            } else if (j4 > 0) {
                Digest digest3 = this.f15291a;
                byte[] bArr12 = this.d;
                digest3.update(bArr12, 0, bArr12.length);
                byte[] bArr13 = new byte[this.f15291a.getDigestSize()];
                this.d = bArr13;
                this.f15291a.doFinal(bArr13, 0);
                this.o--;
            }
            if (this.o != 0) {
                return;
            }
            byte[] bArr14 = this.d;
            byte[] bArr15 = this.f;
            int i25 = this.m;
            int i26 = this.b;
            System.arraycopy(bArr14, 0, bArr15, i25 * i26, i26);
        }
        this.m++;
    }

    public int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public byte[] getSig() {
        return this.f;
    }

    public byte[][] getStatByte() {
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, 5, this.b);
        bArr[0] = this.d;
        bArr[1] = this.t;
        bArr[2] = this.e;
        bArr[3] = this.f;
        bArr[4] = getStatLong();
        return bArr;
    }

    public int[] getStatInt() {
        return new int[]{this.m, this.l, this.n, this.k, this.q, this.c, this.s, this.g, this.r};
    }

    public byte[] getStatLong() {
        long j = this.o;
        long j2 = this.p;
        return new byte[]{(byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 56) & 255), (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), (byte) ((j2 >> 32) & 255), (byte) ((j2 >> 40) & 255), (byte) ((j2 >> 48) & 255), (byte) ((j2 >> 56) & 255)};
    }

    public void initSign(byte[] bArr, byte[] bArr2) {
        int i;
        int i2;
        int ceil;
        this.e = new byte[this.b];
        this.f15291a.update(bArr2, 0, bArr2.length);
        byte[] bArr3 = new byte[this.f15291a.getDigestSize()];
        this.e = bArr3;
        this.f15291a.doFinal(bArr3, 0);
        int i3 = this.b;
        byte[] bArr4 = new byte[i3];
        System.arraycopy(this.e, 0, bArr4, 0, i3);
        int log = getLog((this.i << this.g) + 1);
        int i4 = this.g;
        int i5 = 8;
        if (8 % i4 == 0) {
            int i6 = 8 / i4;
            i = 0;
            for (int i7 = 0; i7 < this.b; i7++) {
                for (int i8 = 0; i8 < i6; i8++) {
                    i += bArr4[i7] & this.j;
                    bArr4[i7] = (byte) (bArr4[i7] >>> this.g);
                }
            }
            int i9 = (this.i << this.g) - i;
            this.r = i9;
            int i10 = 0;
            while (i10 < log) {
                i += this.j & i9;
                int i11 = this.g;
                i9 >>>= i11;
                i10 += i11;
            }
        } else if (i4 < 8) {
            int i12 = this.b / i4;
            int i13 = 0;
            int i14 = 0;
            int i15 = 0;
            while (i13 < i12) {
                long j = 0;
                for (int i16 = 0; i16 < this.g; i16++) {
                    j ^= (bArr4[i14] & 255) << (i16 << 3);
                    i14++;
                }
                int i17 = 0;
                while (i17 < i5) {
                    i15 += (int) (this.j & j);
                    j >>>= this.g;
                    i17++;
                    i12 = i12;
                    i5 = 8;
                }
                i13++;
                i5 = 8;
            }
            int i18 = this.b % this.g;
            long j2 = 0;
            for (int i19 = 0; i19 < i18; i19++) {
                j2 ^= (bArr4[i14] & 255) << (i19 << 3);
                i14++;
            }
            int i20 = i18 << 3;
            int i21 = 0;
            while (i21 < i20) {
                i15 += (int) (this.j & j2);
                int i22 = this.g;
                j2 >>>= i22;
                i21 += i22;
            }
            int i23 = (this.i << this.g) - i15;
            this.r = i23;
            int i24 = 0;
            i = i15;
            while (i24 < log) {
                i += this.j & i23;
                int i25 = this.g;
                i23 >>>= i25;
                i24 += i25;
            }
        } else if (i4 < 57) {
            int i26 = 0;
            int i27 = 0;
            while (true) {
                i2 = this.b;
                int i28 = this.g;
                if (i26 > (i2 << 3) - i28) {
                    break;
                }
                int i29 = i26 % 8;
                i26 += i28;
                int i30 = 0;
                long j3 = 0;
                for (int i31 = i26 >>> 3; i31 < ((i26 + 7) >>> 3); i31++) {
                    j3 ^= (bArr4[i31] & 255) << (i30 << 3);
                    i30++;
                }
                i27 = (int) (i27 + ((j3 >>> i29) & this.j));
            }
            int i32 = i26 >>> 3;
            if (i32 < i2) {
                int i33 = i26 % 8;
                int i34 = 0;
                long j4 = 0;
                while (i32 < this.b) {
                    j4 ^= (bArr4[i32] & 255) << (i34 << 3);
                    i34++;
                    i32++;
                }
                i27 = (int) (i27 + ((j4 >>> i33) & this.j));
            }
            int i35 = (this.i << this.g) - i27;
            this.r = i35;
            int i36 = 0;
            i = i27;
            while (i36 < log) {
                i += this.j & i35;
                int i37 = this.g;
                i35 >>>= i37;
                i36 += i37;
            }
        } else {
            i = 0;
        }
        this.c = this.i + ((int) Math.ceil(log / this.g));
        this.q = (int) Math.ceil((ceil + i) / (1 << this.s));
        int i38 = this.c;
        int i39 = this.b;
        this.f = new byte[i38 * i39];
        this.m = 0;
        this.l = 0;
        this.n = 0;
        this.o = 0L;
        this.k = 0;
        this.d = new byte[i39];
        byte[] bArr5 = new byte[i39];
        this.t = bArr5;
        System.arraycopy(bArr, 0, bArr5, 0, i39);
    }

    public String toString() {
        String str = "" + this.p + "  ";
        int[] statInt = getStatInt();
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, 5, this.b);
        byte[][] statByte = getStatByte();
        for (int i = 0; i < 9; i++) {
            str = str + statInt[i] + HexStringBuilder.DEFAULT_SEPARATOR;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            str = str + new String(Hex.encode(statByte[i2])) + HexStringBuilder.DEFAULT_SEPARATOR;
        }
        return str;
    }

    public boolean updateSign() {
        for (int i = 0; i < this.q; i++) {
            if (this.m < this.c) {
                a();
            }
            if (this.m == this.c) {
                return true;
            }
        }
        return false;
    }
}
