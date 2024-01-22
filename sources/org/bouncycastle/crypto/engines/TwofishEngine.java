package org.bouncycastle.crypto.engines;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.j.o;
import com.veryfit.multi.nativeprotocol.b;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
/* loaded from: classes12.dex */
public final class TwofishEngine implements BlockCipher {
    public static final byte[][] j = {new byte[]{-87, 103, BleUUID.CMD_ID_B3, -24, 4, -3, -93, com.htsmart.wristband2.a.a.a.R1, -102, -110, Byte.MIN_VALUE, 120, -28, -35, -47, 56, 13, -58, 53, -104, 24, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, com.htsmart.wristband2.a.a.a.W0, com.crrepa.c.a.K1, -48, -117, 48, -124, 84, -33, 35, 25, com.htsmart.wristband2.a.a.a.r1, 61, 89, com.crrepa.c.a.L1, -82, -94, -126, 99, 1, -125, 46, -39, 81, -101, 124, -90, -21, -91, -66, 22, 12, -29, 97, -64, -116, 58, com.crrepa.c.a.N1, 115, 44, 37, 11, -69, com.htsmart.wristband2.a.a.a.c1, BleUUID.CMD_ID_89, 107, 83, 106, BleUUID.CMD_ID_B4, com.crrepa.c.a.J1, -31, -26, -67, com.crrepa.c.a.E0, -30, com.crrepa.c.a.M1, BleUUID.CMD_ID_B6, 102, -52, -107, 3, 86, -44, 28, 30, -41, -5, -61, -114, BleUUID.CMD_ID_B5, -23, -49, -65, -70, com.crrepa.c.a.A, 119, 57, BleUUID.CMD_ID_AF, 51, -55, 98, 113, -127, com.htsmart.wristband2.a.a.a.U1, 9, BleUUID.CMD_ID_AD, 36, -51, -7, -40, -27, -59, -71, 77, 68, 8, -122, -25, -95, 29, -86, -19, 6, com.htsmart.wristband2.a.a.a.J1, -78, -46, 65, 123, BleUUID.CMD_ID_A0, 17, 49, -62, 39, BleUUID.CMD_ID_90, 32, -10, 96, -1, BleUUID.CMD_ID_96, 92, -79, -85, -98, -100, 82, 27, 95, -109, 10, -17, -111, -123, 73, o.c, 45, 79, -113, 59, 71, -121, 109, com.htsmart.wristband2.a.a.a.U0, -42, 62, 105, 100, 42, -50, -53, 47, -4, BleUUID.CMD_ID_97, 5, com.htsmart.wristband2.a.a.a.V1, -84, Byte.MAX_VALUE, -43, 26, 75, 14, -89, 90, 40, 20, 63, 41, -120, 60, com.htsmart.wristband2.a.a.a.a1, 2, BleUUID.CMD_ID_B8, -38, -80, 23, 85, 31, -118, com.crrepa.c.a.h1, 87, -57, -115, 116, BleUUID.CMD_ID_B7, -60, -97, 114, com.crrepa.c.a.l1, 21, 34, 18, com.htsmart.wristband2.a.a.a.o1, 7, -103, 52, 110, com.htsmart.wristband2.a.a.a.d1, -34, 104, 101, PSSSigner.TRAILER_IMPLICIT, -37, -8, -56, -88, 43, 64, -36, -2, 50, -92, -54, 16, 33, -16, -45, com.htsmart.wristband2.a.a.a.t1, 15, 0, com.crrepa.c.a.Z0, -99, 54, 66, com.htsmart.wristband2.a.a.a.Y0, com.htsmart.wristband2.a.a.a.u1, -63, -32}, new byte[]{117, com.crrepa.c.a.L1, -58, com.crrepa.c.a.M1, -37, 123, -5, -56, com.htsmart.wristband2.a.a.a.Y0, -45, -26, 107, com.crrepa.c.a.E0, com.crrepa.c.a.h1, -24, 75, -42, 50, -40, -3, 55, 113, com.crrepa.c.a.J1, -31, 48, 15, -8, 27, -121, -6, 6, 63, com.htsmart.wristband2.a.a.a.u1, -70, -82, com.htsmart.wristband2.a.a.a.r1, -118, 0, PSSSigner.TRAILER_IMPLICIT, -99, 109, -63, -79, 14, Byte.MIN_VALUE, com.htsmart.wristband2.a.a.a.t1, -46, -43, BleUUID.CMD_ID_A0, -124, 7, 20, BleUUID.CMD_ID_B5, BleUUID.CMD_ID_90, 44, -93, -78, 115, com.htsmart.wristband2.a.a.a.a1, 84, -110, 116, 54, 81, 56, -80, -67, 90, -4, 96, 98, BleUUID.CMD_ID_96, 108, 66, -9, 16, 124, 40, 39, -116, 19, -107, -100, -57, 36, com.htsmart.wristband2.a.a.a.U0, 59, com.htsmart.wristband2.a.a.a.J1, -54, -29, -123, -53, 17, -48, -109, BleUUID.CMD_ID_B8, -90, -125, 32, -1, -97, 119, -61, -52, 3, com.crrepa.c.a.Z0, 8, -65, 64, -25, 43, -30, com.htsmart.wristband2.a.a.a.U1, 12, -86, -126, 65, 58, com.crrepa.c.a.A, -71, -28, -102, -92, BleUUID.CMD_ID_97, com.crrepa.c.a.l1, -38, com.htsmart.wristband2.a.a.a.V1, 23, 102, -108, -95, 29, 61, -16, -34, BleUUID.CMD_ID_B3, 11, 114, -89, 28, -17, -47, 83, 62, -113, 51, 38, 95, -20, com.htsmart.wristband2.a.a.a.R1, 42, 73, -127, -120, o.c, 33, -60, 26, -21, -39, -59, 57, -103, -51, BleUUID.CMD_ID_AD, 49, -117, 1, 24, 35, -35, 31, com.htsmart.wristband2.a.a.a.c1, 45, -7, com.htsmart.wristband2.a.a.a.W0, 79, com.crrepa.c.a.K1, 101, -114, 120, 92, com.htsmart.wristband2.a.a.a.o1, 25, -115, -27, -104, 87, 103, Byte.MAX_VALUE, 5, 100, BleUUID.CMD_ID_AF, 99, BleUUID.CMD_ID_B6, -2, com.crrepa.c.a.N1, BleUUID.CMD_ID_B7, 60, -91, -50, -23, 104, 68, -32, 77, 67, 105, 41, 46, -84, 21, 89, -88, 10, -98, 110, 71, -33, 52, 53, 106, -49, -36, 34, -55, -64, -101, BleUUID.CMD_ID_89, -44, -19, -85, 18, -94, 13, 82, -69, 2, 47, -87, -41, 97, 30, BleUUID.CMD_ID_B4, com.htsmart.wristband2.a.a.a.d1, 4, -10, -62, 22, 37, -122, 86, 85, 9, -66, -111}};
    public int[] f;
    public int[] g;

    /* renamed from: a  reason: collision with root package name */
    public boolean f14713a = false;
    public int[] b = new int[256];
    public int[] c = new int[256];
    public int[] d = new int[256];
    public int[] e = new int[256];
    public int h = 0;
    public byte[] i = null;

    public TwofishEngine() {
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        int[] iArr3 = new int[2];
        for (int i = 0; i < 256; i++) {
            byte[][] bArr = j;
            int i2 = bArr[0][i] & 255;
            iArr[0] = i2;
            iArr2[0] = h(i2) & 255;
            iArr3[0] = i(i2) & 255;
            int i3 = bArr[1][i] & 255;
            iArr[1] = i3;
            iArr2[1] = h(i3) & 255;
            iArr3[1] = i(i3) & 255;
            this.b[i] = iArr[1] | (iArr2[1] << 8) | (iArr3[1] << 16) | (iArr3[1] << 24);
            this.c[i] = iArr3[0] | (iArr3[0] << 8) | (iArr2[0] << 16) | (iArr[0] << 24);
            this.d[i] = (iArr3[1] << 24) | iArr2[1] | (iArr3[1] << 8) | (iArr[1] << 16);
            this.e[i] = iArr2[0] | (iArr[0] << 8) | (iArr3[0] << 16) | (iArr2[0] << 24);
        }
    }

    public final void a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 3] = (byte) (i >> 24);
    }

    public final int b(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public final int c(int i, int[] iArr) {
        int i2;
        int i3;
        int l = l(i);
        int m = m(i);
        int n = n(i);
        int o = o(i);
        int i4 = iArr[0];
        int i5 = iArr[1];
        int i6 = iArr[2];
        int i7 = iArr[3];
        int i8 = this.h & 3;
        if (i8 != 0) {
            if (i8 == 1) {
                int[] iArr2 = this.b;
                byte[][] bArr = j;
                i2 = (iArr2[(bArr[0][l] & 255) ^ l(i4)] ^ this.c[(bArr[0][m] & 255) ^ m(i4)]) ^ this.d[(bArr[1][n] & 255) ^ n(i4)];
                i3 = this.e[(bArr[1][o] & 255) ^ o(i4)];
                return i2 ^ i3;
            }
            if (i8 != 2) {
                if (i8 != 3) {
                    return 0;
                }
            }
            int[] iArr3 = this.b;
            byte[][] bArr2 = j;
            i2 = (iArr3[(bArr2[0][(bArr2[0][l] & 255) ^ l(i5)] & 255) ^ l(i4)] ^ this.c[(bArr2[0][(bArr2[1][m] & 255) ^ m(i5)] & 255) ^ m(i4)]) ^ this.d[(bArr2[1][(bArr2[0][n] & 255) ^ n(i5)] & 255) ^ n(i4)];
            i3 = this.e[(bArr2[1][(bArr2[1][o] & 255) ^ o(i5)] & 255) ^ o(i4)];
            return i2 ^ i3;
        }
        byte[][] bArr3 = j;
        l = (bArr3[1][l] & 255) ^ l(i7);
        m = (bArr3[0][m] & 255) ^ m(i7);
        n = (bArr3[0][n] & 255) ^ n(i7);
        o = (bArr3[1][o] & 255) ^ o(i7);
        byte[][] bArr4 = j;
        l = (bArr4[1][l] & 255) ^ l(i6);
        m = (bArr4[1][m] & 255) ^ m(i6);
        n = (bArr4[0][n] & 255) ^ n(i6);
        o = (bArr4[0][o] & 255) ^ o(i6);
        int[] iArr32 = this.b;
        byte[][] bArr22 = j;
        i2 = (iArr32[(bArr22[0][(bArr22[0][l] & 255) ^ l(i5)] & 255) ^ l(i4)] ^ this.c[(bArr22[0][(bArr22[1][m] & 255) ^ m(i5)] & 255) ^ m(i4)]) ^ this.d[(bArr22[1][(bArr22[0][n] & 255) ^ n(i5)] & 255) ^ n(i4)];
        i3 = this.e[(bArr22[1][(bArr22[1][o] & 255) ^ o(i5)] & 255) ^ o(i4)];
        return i2 ^ i3;
    }

    public final int d(int i) {
        int[] iArr = this.g;
        return iArr[(((i >>> 24) & 255) * 2) + 513] ^ ((iArr[((i & 255) * 2) + 0] ^ iArr[(((i >>> 8) & 255) * 2) + 1]) ^ iArr[(((i >>> 16) & 255) * 2) + 512]);
    }

    public final int e(int i) {
        int[] iArr = this.g;
        return iArr[(((i >>> 16) & 255) * 2) + 513] ^ ((iArr[(((i >>> 24) & 255) * 2) + 0] ^ iArr[((i & 255) * 2) + 1]) ^ iArr[(((i >>> 8) & 255) * 2) + 512]);
    }

    public final int f(int i) {
        return ((i & 1) != 0 ? 180 : 0) ^ (i >> 1);
    }

    public final int g(int i) {
        return ((i >> 2) ^ ((i & 2) != 0 ? 180 : 0)) ^ ((i & 1) != 0 ? 90 : 0);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Twofish";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    public final int h(int i) {
        return i ^ g(i);
    }

    public final int i(int i) {
        return g(i) ^ (f(i) ^ i);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + cipherParameters.getClass().getName());
        }
        this.f14713a = z;
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.i = key;
        this.h = key.length / 8;
        r(key);
    }

    public final int j(int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            i2 = k(i2);
        }
        int i4 = i ^ i2;
        for (int i5 = 0; i5 < 4; i5++) {
            i4 = k(i4);
        }
        return i4;
    }

    public final int k(int i) {
        int i2 = (i >>> 24) & 255;
        int i3 = ((i2 << 1) ^ ((i2 & 128) != 0 ? b.n1 : 0)) & 255;
        int i4 = ((i2 >>> 1) ^ ((i2 & 1) != 0 ? 166 : 0)) ^ i3;
        return ((((i << 8) ^ (i4 << 24)) ^ (i3 << 16)) ^ (i4 << 8)) ^ i2;
    }

    public final int l(int i) {
        return i & 255;
    }

    public final int m(int i) {
        return (i >>> 8) & 255;
    }

    public final int n(int i) {
        return (i >>> 16) & 255;
    }

    public final int o(int i) {
        return (i >>> 24) & 255;
    }

    public final void p(byte[] bArr, int i, byte[] bArr2, int i2) {
        int b = b(bArr, i) ^ this.f[4];
        int b2 = b(bArr, i + 4) ^ this.f[5];
        int b3 = b(bArr, i + 8) ^ this.f[6];
        int b4 = b(bArr, i + 12) ^ this.f[7];
        int i3 = 39;
        for (int i4 = 0; i4 < 16; i4 += 2) {
            int d = d(b);
            int e = e(b2);
            int[] iArr = this.f;
            int i5 = i3 - 1;
            int i6 = b4 ^ (((e * 2) + d) + iArr[i3]);
            int i7 = i5 - 1;
            b3 = ((b3 >>> 31) | (b3 << 1)) ^ ((d + e) + iArr[i5]);
            b4 = (i6 << 31) | (i6 >>> 1);
            int d2 = d(b3);
            int e2 = e(b4);
            int[] iArr2 = this.f;
            int i8 = i7 - 1;
            int i9 = b2 ^ (((e2 * 2) + d2) + iArr2[i7]);
            i3 = i8 - 1;
            b = ((b >>> 31) | (b << 1)) ^ ((d2 + e2) + iArr2[i8]);
            b2 = (i9 << 31) | (i9 >>> 1);
        }
        a(this.f[0] ^ b3, bArr2, i2);
        a(b4 ^ this.f[1], bArr2, i2 + 4);
        a(this.f[2] ^ b, bArr2, i2 + 8);
        a(this.f[3] ^ b2, bArr2, i2 + 12);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.i != null) {
            if (i + 16 <= bArr.length) {
                if (i2 + 16 <= bArr2.length) {
                    if (this.f14713a) {
                        q(bArr, i, bArr2, i2);
                        return 16;
                    }
                    p(bArr, i, bArr2, i2);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("Twofish not initialised");
    }

    public final void q(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = 0;
        int b = b(bArr, i) ^ this.f[0];
        int b2 = b(bArr, i + 4) ^ this.f[1];
        int b3 = b(bArr, i + 8) ^ this.f[2];
        int b4 = b(bArr, i + 12) ^ this.f[3];
        int i4 = 8;
        while (i3 < 16) {
            int d = d(b);
            int e = e(b2);
            int[] iArr = this.f;
            int i5 = i4 + 1;
            int i6 = b3 ^ ((d + e) + iArr[i4]);
            b3 = (i6 >>> 1) | (i6 << 31);
            int i7 = (b4 >>> 31) | (b4 << 1);
            int i8 = i5 + 1;
            b4 = i7 ^ ((d + (e * 2)) + iArr[i5]);
            int d2 = d(b3);
            int e2 = e(b4);
            int[] iArr2 = this.f;
            int i9 = i8 + 1;
            int i10 = b ^ ((d2 + e2) + iArr2[i8]);
            b = (i10 >>> 1) | (i10 << 31);
            i3 += 2;
            b2 = ((b2 << 1) | (b2 >>> 31)) ^ ((d2 + (e2 * 2)) + iArr2[i9]);
            i4 = i9 + 1;
        }
        a(this.f[4] ^ b3, bArr2, i2);
        a(b4 ^ this.f[5], bArr2, i2 + 4);
        a(this.f[6] ^ b, bArr2, i2 + 8);
        a(this.f[7] ^ b2, bArr2, i2 + 12);
    }

    public final void r(byte[] bArr) {
        int l;
        int m;
        int n;
        int o;
        int i;
        int i2;
        int i3;
        int i4;
        int[] iArr = new int[4];
        int[] iArr2 = new int[4];
        int[] iArr3 = new int[4];
        this.f = new int[40];
        int i5 = this.h;
        if (i5 < 1) {
            throw new IllegalArgumentException("Key size less than 64 bits");
        }
        if (i5 > 4) {
            throw new IllegalArgumentException("Key size larger than 256 bits");
        }
        for (int i6 = 0; i6 < this.h; i6++) {
            int i7 = i6 * 8;
            iArr[i6] = b(bArr, i7);
            iArr2[i6] = b(bArr, i7 + 4);
            iArr3[(this.h - 1) - i6] = j(iArr[i6], iArr2[i6]);
        }
        for (int i8 = 0; i8 < 20; i8++) {
            int i9 = 33686018 * i8;
            int c = c(i9, iArr);
            int c2 = c(i9 + 16843009, iArr2);
            int i10 = (c2 >>> 24) | (c2 << 8);
            int i11 = c + i10;
            int[] iArr4 = this.f;
            int i12 = i8 * 2;
            iArr4[i12] = i11;
            int i13 = i11 + i10;
            iArr4[i12 + 1] = (i13 << 9) | (i13 >>> 23);
        }
        int i14 = iArr3[0];
        int i15 = iArr3[1];
        int i16 = 2;
        int i17 = iArr3[2];
        int i18 = iArr3[3];
        this.g = new int[1024];
        int i19 = 0;
        while (i19 < 256) {
            int i20 = this.h & 3;
            if (i20 != 0) {
                if (i20 == 1) {
                    int[] iArr5 = this.g;
                    int i21 = i19 * 2;
                    int[] iArr6 = this.b;
                    byte[][] bArr2 = j;
                    iArr5[i21] = iArr6[(bArr2[0][i19] & 255) ^ l(i14)];
                    this.g[i21 + 1] = this.c[(bArr2[0][i19] & 255) ^ m(i14)];
                    this.g[i21 + 512] = this.d[(bArr2[1][i19] & 255) ^ n(i14)];
                    this.g[i21 + 513] = this.e[(bArr2[1][i19] & 255) ^ o(i14)];
                } else if (i20 == i16) {
                    i4 = i19;
                    i3 = i4;
                    i2 = i3;
                    i = i2;
                    int[] iArr7 = this.g;
                    int i22 = i19 * 2;
                    int[] iArr8 = this.b;
                    byte[][] bArr3 = j;
                    iArr7[i22] = iArr8[(bArr3[0][(bArr3[0][i3] & 255) ^ l(i15)] & 255) ^ l(i14)];
                    this.g[i22 + 1] = this.c[(bArr3[0][(bArr3[1][i2] & 255) ^ m(i15)] & 255) ^ m(i14)];
                    this.g[i22 + 512] = this.d[(bArr3[1][(bArr3[0][i] & 255) ^ n(i15)] & 255) ^ n(i14)];
                    this.g[i22 + 513] = this.e[(bArr3[1][(bArr3[1][i4] & 255) ^ o(i15)] & 255) ^ o(i14)];
                } else if (i20 == 3) {
                    o = i19;
                    l = o;
                    m = l;
                    n = m;
                }
                i19++;
                i16 = 2;
            } else {
                byte[][] bArr4 = j;
                l = (bArr4[1][i19] & 255) ^ l(i18);
                m = (bArr4[0][i19] & 255) ^ m(i18);
                n = (bArr4[0][i19] & 255) ^ n(i18);
                o = (bArr4[1][i19] & 255) ^ o(i18);
            }
            byte[][] bArr5 = j;
            i3 = (bArr5[1][l] & 255) ^ l(i17);
            i2 = (bArr5[1][m] & 255) ^ m(i17);
            i = (bArr5[0][n] & 255) ^ n(i17);
            i4 = (bArr5[0][o] & 255) ^ o(i17);
            int[] iArr72 = this.g;
            int i222 = i19 * 2;
            int[] iArr82 = this.b;
            byte[][] bArr32 = j;
            iArr72[i222] = iArr82[(bArr32[0][(bArr32[0][i3] & 255) ^ l(i15)] & 255) ^ l(i14)];
            this.g[i222 + 1] = this.c[(bArr32[0][(bArr32[1][i2] & 255) ^ m(i15)] & 255) ^ m(i14)];
            this.g[i222 + 512] = this.d[(bArr32[1][(bArr32[0][i] & 255) ^ n(i15)] & 255) ^ n(i14)];
            this.g[i222 + 513] = this.e[(bArr32[1][(bArr32[1][i4] & 255) ^ o(i15)] & 255) ^ o(i14)];
            i19++;
            i16 = 2;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        byte[] bArr = this.i;
        if (bArr != null) {
            r(bArr);
        }
    }
}
