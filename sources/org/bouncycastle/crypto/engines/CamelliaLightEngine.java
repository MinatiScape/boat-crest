package org.bouncycastle.crypto.engines;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.j.o;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
/* loaded from: classes12.dex */
public class CamelliaLightEngine implements BlockCipher {
    public static final int[] g = {-1600231809, 1003262091, -1233459112, 1286239154, -957401297, -380665154, 1426019237, -237801700, 283453434, -563598051, -1336506174, -1276722691};
    public static final byte[] h = {com.htsmart.wristband2.a.a.a.J1, -126, 44, -20, BleUUID.CMD_ID_B3, 39, -64, -27, -28, -123, 87, 53, com.crrepa.c.a.A, 12, -82, 65, 35, -17, 107, -109, com.crrepa.c.a.E0, 25, -91, 33, -19, 14, 79, com.htsmart.wristband2.a.a.a.c1, 29, 101, -110, -67, -122, BleUUID.CMD_ID_B8, BleUUID.CMD_ID_AF, -113, 124, -21, 31, -50, 62, 48, -36, 95, com.htsmart.wristband2.a.a.a.u1, -59, 11, 26, -90, -31, 57, -54, -43, 71, com.htsmart.wristband2.a.a.a.t1, 61, -39, 1, 90, -42, 81, 86, 108, 77, -117, 13, -102, 102, -5, -52, -80, 45, 116, 18, 43, 32, -16, -79, -124, -103, -33, com.htsmart.wristband2.a.a.a.a1, -53, -62, 52, com.crrepa.c.a.l1, com.htsmart.wristband2.a.a.a.R1, 5, 109, BleUUID.CMD_ID_B7, -87, 49, -47, 23, 4, -41, 20, com.htsmart.wristband2.a.a.a.o1, 58, 97, -34, 27, 17, 28, 50, 15, -100, 22, 83, 24, com.crrepa.c.a.K1, 34, -2, 68, -49, -78, -61, BleUUID.CMD_ID_B5, com.htsmart.wristband2.a.a.a.V1, -111, 36, 8, -24, -88, 96, -4, 105, com.htsmart.wristband2.a.a.a.d1, -86, -48, BleUUID.CMD_ID_A0, com.crrepa.c.a.h1, -95, BleUUID.CMD_ID_89, 98, BleUUID.CMD_ID_97, 84, com.htsmart.wristband2.a.a.a.r1, 30, -107, -32, -1, 100, -46, 16, -60, 0, com.htsmart.wristband2.a.a.a.W0, -93, -9, 117, -37, -118, 3, -26, -38, 9, 63, -35, -108, -121, 92, -125, 2, -51, com.htsmart.wristband2.a.a.a.Y0, BleUUID.CMD_ID_90, 51, 115, 103, -10, com.crrepa.c.a.L1, -99, Byte.MAX_VALUE, -65, -30, 82, -101, -40, 38, -56, 55, -58, 59, -127, BleUUID.CMD_ID_96, com.crrepa.c.a.Z0, 75, 19, -66, 99, 46, -23, com.htsmart.wristband2.a.a.a.U1, -89, -116, -97, 110, PSSSigner.TRAILER_IMPLICIT, -114, 41, com.crrepa.c.a.N1, -7, BleUUID.CMD_ID_B6, 47, -3, BleUUID.CMD_ID_B4, 89, 120, -104, 6, 106, -25, com.htsmart.wristband2.a.a.a.U0, 113, -70, -44, 37, -85, 66, -120, -94, -115, -6, 114, 7, -71, 85, -8, o.c, -84, 10, 54, 73, 42, 104, 60, 56, com.crrepa.c.a.J1, -92, 64, 40, -45, 123, -69, -55, 67, -63, 21, -29, BleUUID.CMD_ID_AD, com.crrepa.c.a.M1, 119, -57, Byte.MIN_VALUE, -98};

    /* renamed from: a  reason: collision with root package name */
    public boolean f14670a;
    public boolean b;
    public int[] c = new int[96];
    public int[] d = new int[8];
    public int[] e = new int[12];
    public int[] f = new int[4];

    public static void d(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i3 + 2;
        int i5 = i2 + 0;
        int i6 = i2 + 1;
        int i7 = 32 - i;
        iArr2[i4] = (iArr[i5] << i) | (iArr[i6] >>> i7);
        int i8 = i3 + 3;
        int i9 = i2 + 2;
        iArr2[i8] = (iArr[i6] << i) | (iArr[i9] >>> i7);
        int i10 = i3 + 0;
        int i11 = i2 + 3;
        iArr2[i10] = (iArr[i9] << i) | (iArr[i11] >>> i7);
        int i12 = i3 + 1;
        iArr2[i12] = (iArr[i11] << i) | (iArr[i5] >>> i7);
        iArr[i5] = iArr2[i4];
        iArr[i6] = iArr2[i8];
        iArr[i9] = iArr2[i10];
        iArr[i11] = iArr2[i12];
    }

    public static void e(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i3 + 2;
        int i5 = i2 + 1;
        int i6 = i - 32;
        int i7 = i2 + 2;
        int i8 = 64 - i;
        iArr2[i4] = (iArr[i5] << i6) | (iArr[i7] >>> i8);
        int i9 = i3 + 3;
        int i10 = i2 + 3;
        iArr2[i9] = (iArr[i7] << i6) | (iArr[i10] >>> i8);
        int i11 = i3 + 0;
        int i12 = i2 + 0;
        iArr2[i11] = (iArr[i10] << i6) | (iArr[i12] >>> i8);
        int i13 = i3 + 1;
        iArr2[i13] = (iArr[i5] >>> i8) | (iArr[i12] << i6);
        iArr[i12] = iArr2[i4];
        iArr[i5] = iArr2[i9];
        iArr[i7] = iArr2[i11];
        iArr[i10] = iArr2[i13];
    }

    public static int h(int i, int i2) {
        return (i << i2) + (i >>> (32 - i2));
    }

    public static int k(int i, int i2) {
        return (i >>> i2) + (i << (32 - i2));
    }

    public static void l(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i3 + 0;
        int i5 = i2 + 0;
        int i6 = i2 + 1;
        int i7 = 32 - i;
        iArr2[i4] = (iArr[i5] << i) | (iArr[i6] >>> i7);
        int i8 = i3 + 1;
        int i9 = i2 + 2;
        iArr2[i8] = (iArr[i6] << i) | (iArr[i9] >>> i7);
        int i10 = i3 + 2;
        int i11 = i2 + 3;
        iArr2[i10] = (iArr[i9] << i) | (iArr[i11] >>> i7);
        int i12 = i3 + 3;
        iArr2[i12] = (iArr[i11] << i) | (iArr[i5] >>> i7);
        iArr[i5] = iArr2[i4];
        iArr[i6] = iArr2[i8];
        iArr[i9] = iArr2[i10];
        iArr[i11] = iArr2[i12];
    }

    public static void m(int i, int[] iArr, int i2, int[] iArr2, int i3) {
        int i4 = i3 + 0;
        int i5 = i2 + 1;
        int i6 = i - 32;
        int i7 = i2 + 2;
        int i8 = 64 - i;
        iArr2[i4] = (iArr[i5] << i6) | (iArr[i7] >>> i8);
        int i9 = i3 + 1;
        int i10 = i2 + 3;
        iArr2[i9] = (iArr[i7] << i6) | (iArr[i10] >>> i8);
        int i11 = i3 + 2;
        int i12 = i2 + 0;
        iArr2[i11] = (iArr[i10] << i6) | (iArr[i12] >>> i8);
        int i13 = i3 + 3;
        iArr2[i13] = (iArr[i5] >>> i8) | (iArr[i12] << i6);
        iArr[i12] = iArr2[i4];
        iArr[i5] = iArr2[i9];
        iArr[i7] = iArr2[i11];
        iArr[i10] = iArr2[i13];
    }

    public final int a(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < 4; i3++) {
            i2 = (i2 << 8) + (bArr[i3 + i] & 255);
        }
        return i2;
    }

    public final void b(int[] iArr, int[] iArr2, int i) {
        int i2 = iArr[0] ^ iArr2[i + 0];
        int p = p(i2 & 255) | (o((i2 >>> 8) & 255) << 8) | (n((i2 >>> 16) & 255) << 16);
        byte[] bArr = h;
        int i3 = iArr[1] ^ iArr2[i + 1];
        int h2 = h((n((i3 >>> 24) & 255) << 24) | (bArr[i3 & 255] & 255) | (p((i3 >>> 8) & 255) << 8) | (o((i3 >>> 16) & 255) << 16), 8);
        int i4 = (((bArr[(i2 >>> 24) & 255] & 255) << 24) | p) ^ h2;
        int h3 = h(h2, 8) ^ i4;
        int k = k(i4, 8) ^ h3;
        iArr[2] = (h(h3, 16) ^ k) ^ iArr[2];
        iArr[3] = h(k, 8) ^ iArr[3];
        int i5 = iArr[2] ^ iArr2[i + 2];
        int p2 = ((bArr[(i5 >>> 24) & 255] & 255) << 24) | p(i5 & 255) | (o((i5 >>> 8) & 255) << 8) | (n((i5 >>> 16) & 255) << 16);
        int i6 = iArr2[i + 3] ^ iArr[3];
        int h4 = h((n((i6 >>> 24) & 255) << 24) | (bArr[i6 & 255] & 255) | (p((i6 >>> 8) & 255) << 8) | (o((i6 >>> 16) & 255) << 16), 8);
        int i7 = p2 ^ h4;
        int h5 = h(h4, 8) ^ i7;
        int k2 = k(i7, 8) ^ h5;
        iArr[0] = (h(h5, 16) ^ k2) ^ iArr[0];
        iArr[1] = iArr[1] ^ h(k2, 8);
    }

    public final void c(int[] iArr, int[] iArr2, int i) {
        iArr[1] = iArr[1] ^ h(iArr[0] & iArr2[i + 0], 1);
        iArr[0] = iArr[0] ^ (iArr2[i + 1] | iArr[1]);
        iArr[2] = iArr[2] ^ (iArr2[i + 3] | iArr[3]);
        iArr[3] = h(iArr2[i + 2] & iArr[2], 1) ^ iArr[3];
    }

    public final void f(int i, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            bArr[(3 - i3) + i2] = (byte) i;
            i >>>= 8;
        }
    }

    public final byte g(byte b, int i) {
        return (byte) (((b & 255) >>> (8 - i)) | (b << i));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Camellia";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    public final int i(byte[] bArr, int i, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            this.f[i3] = a(bArr, (i3 * 4) + i);
            int[] iArr = this.f;
            iArr[i3] = iArr[i3] ^ this.d[i3];
        }
        b(this.f, this.c, 0);
        b(this.f, this.c, 4);
        b(this.f, this.c, 8);
        c(this.f, this.e, 0);
        b(this.f, this.c, 12);
        b(this.f, this.c, 16);
        b(this.f, this.c, 20);
        c(this.f, this.e, 4);
        b(this.f, this.c, 24);
        b(this.f, this.c, 28);
        b(this.f, this.c, 32);
        int[] iArr2 = this.f;
        int i4 = iArr2[2];
        int[] iArr3 = this.d;
        iArr2[2] = iArr3[4] ^ i4;
        iArr2[3] = iArr2[3] ^ iArr3[5];
        iArr2[0] = iArr2[0] ^ iArr3[6];
        iArr2[1] = iArr3[7] ^ iArr2[1];
        f(iArr2[2], bArr2, i2);
        f(this.f[3], bArr2, i2 + 4);
        f(this.f[0], bArr2, i2 + 8);
        f(this.f[1], bArr2, i2 + 12);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("only simple KeyParameter expected.");
        }
        q(z, ((KeyParameter) cipherParameters).getKey());
        this.f14670a = true;
    }

    public final int j(byte[] bArr, int i, byte[] bArr2, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            this.f[i3] = a(bArr, (i3 * 4) + i);
            int[] iArr = this.f;
            iArr[i3] = iArr[i3] ^ this.d[i3];
        }
        b(this.f, this.c, 0);
        b(this.f, this.c, 4);
        b(this.f, this.c, 8);
        c(this.f, this.e, 0);
        b(this.f, this.c, 12);
        b(this.f, this.c, 16);
        b(this.f, this.c, 20);
        c(this.f, this.e, 4);
        b(this.f, this.c, 24);
        b(this.f, this.c, 28);
        b(this.f, this.c, 32);
        c(this.f, this.e, 8);
        b(this.f, this.c, 36);
        b(this.f, this.c, 40);
        b(this.f, this.c, 44);
        int[] iArr2 = this.f;
        int i4 = iArr2[2];
        int[] iArr3 = this.d;
        iArr2[2] = i4 ^ iArr3[4];
        iArr2[3] = iArr2[3] ^ iArr3[5];
        iArr2[0] = iArr2[0] ^ iArr3[6];
        iArr2[1] = iArr3[7] ^ iArr2[1];
        f(iArr2[2], bArr2, i2);
        f(this.f[3], bArr2, i2 + 4);
        f(this.f[0], bArr2, i2 + 8);
        f(this.f[1], bArr2, i2 + 12);
        return 16;
    }

    public final int n(int i) {
        return g(h[i], 1) & 255;
    }

    public final int o(int i) {
        return g(h[i], 7) & 255;
    }

    public final int p(int i) {
        return h[g((byte) i, 1) & 255] & 255;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws IllegalStateException {
        if (this.f14670a) {
            if (i + 16 <= bArr.length) {
                if (i2 + 16 <= bArr2.length) {
                    return this.b ? i(bArr, i, bArr2, i2) : j(bArr, i, bArr2, i2);
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Camellia is not initialized");
    }

    public final void q(boolean z, byte[] bArr) {
        int[] iArr = new int[8];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        int[] iArr4 = new int[4];
        int length = bArr.length;
        if (length != 16) {
            if (length == 24) {
                iArr[0] = a(bArr, 0);
                iArr[1] = a(bArr, 4);
                iArr[2] = a(bArr, 8);
                iArr[3] = a(bArr, 12);
                iArr[4] = a(bArr, 16);
                iArr[5] = a(bArr, 20);
                iArr[6] = ~iArr[4];
                iArr[7] = ~iArr[5];
            } else if (length != 32) {
                throw new IllegalArgumentException("key sizes are only 16/24/32 bytes.");
            } else {
                iArr[0] = a(bArr, 0);
                iArr[1] = a(bArr, 4);
                iArr[2] = a(bArr, 8);
                iArr[3] = a(bArr, 12);
                iArr[4] = a(bArr, 16);
                iArr[5] = a(bArr, 20);
                iArr[6] = a(bArr, 24);
                iArr[7] = a(bArr, 28);
            }
            this.b = false;
        } else {
            this.b = true;
            iArr[0] = a(bArr, 0);
            iArr[1] = a(bArr, 4);
            iArr[2] = a(bArr, 8);
            iArr[3] = a(bArr, 12);
            iArr[7] = 0;
            iArr[6] = 0;
            iArr[5] = 0;
            iArr[4] = 0;
        }
        for (int i = 0; i < 4; i++) {
            iArr2[i] = iArr[i] ^ iArr[i + 4];
        }
        b(iArr2, g, 0);
        for (int i2 = 0; i2 < 4; i2++) {
            iArr2[i2] = iArr2[i2] ^ iArr[i2];
        }
        b(iArr2, g, 4);
        if (this.b) {
            int[] iArr5 = this.d;
            if (z) {
                iArr5[0] = iArr[0];
                iArr5[1] = iArr[1];
                iArr5[2] = iArr[2];
                iArr5[3] = iArr[3];
                l(15, iArr, 0, this.c, 4);
                l(30, iArr, 0, this.c, 12);
                l(15, iArr, 0, iArr4, 0);
                int[] iArr6 = this.c;
                iArr6[18] = iArr4[2];
                iArr6[19] = iArr4[3];
                l(17, iArr, 0, this.e, 4);
                l(17, iArr, 0, this.c, 24);
                l(17, iArr, 0, this.c, 32);
                int[] iArr7 = this.c;
                iArr7[0] = iArr2[0];
                iArr7[1] = iArr2[1];
                iArr7[2] = iArr2[2];
                iArr7[3] = iArr2[3];
                l(15, iArr2, 0, iArr7, 8);
                l(15, iArr2, 0, this.e, 0);
                l(15, iArr2, 0, iArr4, 0);
                int[] iArr8 = this.c;
                iArr8[16] = iArr4[0];
                iArr8[17] = iArr4[1];
                l(15, iArr2, 0, iArr8, 20);
                m(34, iArr2, 0, this.c, 28);
                l(17, iArr2, 0, this.d, 4);
                return;
            }
            iArr5[4] = iArr[0];
            iArr5[5] = iArr[1];
            iArr5[6] = iArr[2];
            iArr5[7] = iArr[3];
            d(15, iArr, 0, this.c, 28);
            d(30, iArr, 0, this.c, 20);
            d(15, iArr, 0, iArr4, 0);
            int[] iArr9 = this.c;
            iArr9[16] = iArr4[0];
            iArr9[17] = iArr4[1];
            d(17, iArr, 0, this.e, 0);
            d(17, iArr, 0, this.c, 8);
            d(17, iArr, 0, this.c, 0);
            int[] iArr10 = this.c;
            iArr10[34] = iArr2[0];
            iArr10[35] = iArr2[1];
            iArr10[32] = iArr2[2];
            iArr10[33] = iArr2[3];
            d(15, iArr2, 0, iArr10, 24);
            d(15, iArr2, 0, this.e, 4);
            d(15, iArr2, 0, iArr4, 0);
            int[] iArr11 = this.c;
            iArr11[18] = iArr4[2];
            iArr11[19] = iArr4[3];
            d(15, iArr2, 0, iArr11, 12);
            e(34, iArr2, 0, this.c, 4);
            l(17, iArr2, 0, this.d, 0);
            return;
        }
        for (int i3 = 0; i3 < 4; i3++) {
            iArr3[i3] = iArr2[i3] ^ iArr[i3 + 4];
        }
        b(iArr3, g, 8);
        int[] iArr12 = this.d;
        if (z) {
            iArr12[0] = iArr[0];
            iArr12[1] = iArr[1];
            iArr12[2] = iArr[2];
            iArr12[3] = iArr[3];
            m(45, iArr, 0, this.c, 16);
            l(15, iArr, 0, this.e, 4);
            l(17, iArr, 0, this.c, 32);
            m(34, iArr, 0, this.c, 44);
            l(15, iArr, 4, this.c, 4);
            l(15, iArr, 4, this.e, 0);
            l(30, iArr, 4, this.c, 24);
            m(34, iArr, 4, this.c, 36);
            l(15, iArr2, 0, this.c, 8);
            l(30, iArr2, 0, this.c, 20);
            int[] iArr13 = this.e;
            iArr13[8] = iArr2[1];
            iArr13[9] = iArr2[2];
            iArr13[10] = iArr2[3];
            iArr13[11] = iArr2[0];
            m(49, iArr2, 0, this.c, 40);
            int[] iArr14 = this.c;
            iArr14[0] = iArr3[0];
            iArr14[1] = iArr3[1];
            iArr14[2] = iArr3[2];
            iArr14[3] = iArr3[3];
            l(30, iArr3, 0, iArr14, 12);
            l(30, iArr3, 0, this.c, 28);
            m(51, iArr3, 0, this.d, 4);
            return;
        }
        iArr12[4] = iArr[0];
        iArr12[5] = iArr[1];
        iArr12[6] = iArr[2];
        iArr12[7] = iArr[3];
        e(45, iArr, 0, this.c, 28);
        d(15, iArr, 0, this.e, 4);
        d(17, iArr, 0, this.c, 12);
        e(34, iArr, 0, this.c, 0);
        d(15, iArr, 4, this.c, 40);
        d(15, iArr, 4, this.e, 8);
        d(30, iArr, 4, this.c, 20);
        e(34, iArr, 4, this.c, 8);
        d(15, iArr2, 0, this.c, 36);
        d(30, iArr2, 0, this.c, 24);
        int[] iArr15 = this.e;
        iArr15[2] = iArr2[1];
        iArr15[3] = iArr2[2];
        iArr15[0] = iArr2[3];
        iArr15[1] = iArr2[0];
        e(49, iArr2, 0, this.c, 4);
        int[] iArr16 = this.c;
        iArr16[46] = iArr3[0];
        iArr16[47] = iArr3[1];
        iArr16[44] = iArr3[2];
        iArr16[45] = iArr3[3];
        d(30, iArr3, 0, iArr16, 32);
        d(30, iArr3, 0, this.c, 16);
        m(51, iArr3, 0, this.d, 0);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
