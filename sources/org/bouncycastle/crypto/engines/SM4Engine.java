package org.bouncycastle.crypto.engines;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.j.o;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class SM4Engine implements BlockCipher {
    public static final byte[] c = {-42, BleUUID.CMD_ID_90, -23, -2, -52, -31, 61, BleUUID.CMD_ID_B7, 22, BleUUID.CMD_ID_B6, 20, -62, 40, -5, 44, 5, 43, 103, -102, com.htsmart.wristband2.a.a.a.R1, 42, -66, 4, -61, -86, 68, 19, 38, 73, -122, 6, -103, -100, 66, com.htsmart.wristband2.a.a.a.d1, com.crrepa.c.a.M1, -111, -17, -104, com.htsmart.wristband2.a.a.a.V1, 51, 84, 11, 67, -19, -49, -84, 98, -28, BleUUID.CMD_ID_B3, 28, -87, -55, 8, -24, -107, Byte.MIN_VALUE, -33, -108, -6, 117, -113, 63, -90, 71, 7, -89, -4, com.crrepa.c.a.L1, 115, 23, -70, -125, 89, 60, 25, -26, -123, 79, -88, 104, 107, -127, -78, 113, 100, -38, -117, -8, -21, 15, 75, com.htsmart.wristband2.a.a.a.J1, 86, -99, 53, 30, 36, 14, com.htsmart.wristband2.a.a.a.u1, 99, com.htsmart.wristband2.a.a.a.o1, -47, -94, 37, 34, 124, 59, 1, 33, 120, -121, -44, 0, com.htsmart.wristband2.a.a.a.U0, 87, -97, -45, 39, 82, com.htsmart.wristband2.a.a.a.a1, 54, 2, -25, BleUUID.CMD_ID_A0, -60, -56, -98, com.crrepa.c.a.A, -65, -118, -46, 64, -57, 56, BleUUID.CMD_ID_B5, -93, -9, com.crrepa.c.a.K1, -50, -7, 97, 21, -95, -32, -82, com.htsmart.wristband2.a.a.a.t1, -92, -101, 52, 26, 85, BleUUID.CMD_ID_AD, -109, 50, 48, com.crrepa.c.a.N1, -116, -79, -29, 29, -10, -30, 46, -126, 102, -54, 96, -64, 41, 35, -85, 13, 83, com.htsmart.wristband2.a.a.a.c1, com.crrepa.c.a.Z0, -43, -37, 55, com.crrepa.c.a.E0, -34, -3, -114, 47, 3, -1, 106, 114, 109, 108, com.htsmart.wristband2.a.a.a.r1, 81, -115, 27, BleUUID.CMD_ID_AF, -110, -69, -35, PSSSigner.TRAILER_IMPLICIT, Byte.MAX_VALUE, 17, -39, 92, 65, 31, 16, 90, -40, 10, -63, 49, -120, -91, -51, 123, -67, 45, 116, -48, 18, BleUUID.CMD_ID_B8, -27, BleUUID.CMD_ID_B4, -80, BleUUID.CMD_ID_89, 105, BleUUID.CMD_ID_97, com.htsmart.wristband2.a.a.a.Y0, 12, BleUUID.CMD_ID_96, 119, com.crrepa.c.a.l1, 101, -71, com.crrepa.c.a.J1, 9, -59, 110, -58, -124, 24, -16, com.crrepa.c.a.h1, -20, 58, -36, 77, 32, com.htsmart.wristband2.a.a.a.U1, o.c, 95, 62, -41, -53, 57, com.htsmart.wristband2.a.a.a.W0};
    public static final int[] d = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};
    public static final int[] e = {-1548633402, 1453994832, 1736282519, -1301273892};

    /* renamed from: a  reason: collision with root package name */
    public final int[] f14706a = new int[4];
    public int[] b;

    public final int a(int[] iArr, int i) {
        return h((iArr[3] ^ (iArr[1] ^ iArr[2])) ^ i) ^ iArr[0];
    }

    public final int b(int[] iArr, int i) {
        return h((iArr[0] ^ (iArr[2] ^ iArr[3])) ^ i) ^ iArr[1];
    }

    public final int c(int[] iArr, int i) {
        return h((iArr[1] ^ (iArr[3] ^ iArr[0])) ^ i) ^ iArr[2];
    }

    public final int d(int[] iArr, int i) {
        return h((iArr[2] ^ (iArr[0] ^ iArr[1])) ^ i) ^ iArr[3];
    }

    public final int e(int i) {
        return k(i, 24) ^ (((k(i, 2) ^ i) ^ k(i, 10)) ^ k(i, 18));
    }

    public final int f(int i) {
        return k(i, 23) ^ (k(i, 13) ^ i);
    }

    public final void g(int[] iArr, int i) {
        int i2 = i + 1;
        int i3 = i + 2;
        int i4 = i + 3;
        iArr[i] = iArr[i] ^ iArr[i4];
        iArr[i4] = iArr[i] ^ iArr[i4];
        iArr[i] = iArr[i4] ^ iArr[i];
        iArr[i2] = iArr[i2] ^ iArr[i3];
        iArr[i3] = iArr[i2] ^ iArr[i3];
        iArr[i2] = iArr[i2] ^ iArr[i3];
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "SM4";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    public final int h(int i) {
        return e(l(i));
    }

    public final int i(int i) {
        return f(l(i));
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + cipherParameters.getClass().getName());
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("SM4 requires a 128 bit key");
        }
        this.b = j(z, key);
    }

    public final int[] j(boolean z, byte[] bArr) {
        int[] iArr = new int[32];
        int[] iArr2 = {Pack.bigEndianToInt(bArr, 0), Pack.bigEndianToInt(bArr, 4), Pack.bigEndianToInt(bArr, 8), Pack.bigEndianToInt(bArr, 12)};
        int i = iArr2[0];
        int[] iArr3 = e;
        int[] iArr4 = {i ^ iArr3[0], iArr2[1] ^ iArr3[1], iArr2[2] ^ iArr3[2], iArr2[3] ^ iArr3[3]};
        if (z) {
            int i2 = iArr4[0];
            int i3 = (iArr4[1] ^ iArr4[2]) ^ iArr4[3];
            int[] iArr5 = d;
            iArr[0] = i2 ^ i(i3 ^ iArr5[0]);
            iArr[1] = iArr4[1] ^ i(((iArr4[2] ^ iArr4[3]) ^ iArr[0]) ^ iArr5[1]);
            iArr[2] = iArr4[2] ^ i(((iArr4[3] ^ iArr[0]) ^ iArr[1]) ^ iArr5[2]);
            iArr[3] = iArr4[3] ^ i(((iArr[0] ^ iArr[1]) ^ iArr[2]) ^ iArr5[3]);
            for (int i4 = 4; i4 < 32; i4++) {
                iArr[i4] = iArr[i4 - 4] ^ i(((iArr[i4 - 3] ^ iArr[i4 - 2]) ^ iArr[i4 - 1]) ^ d[i4]);
            }
        } else {
            int i5 = iArr4[0];
            int i6 = (iArr4[1] ^ iArr4[2]) ^ iArr4[3];
            int[] iArr6 = d;
            iArr[31] = i5 ^ i(i6 ^ iArr6[0]);
            iArr[30] = iArr4[1] ^ i(((iArr4[2] ^ iArr4[3]) ^ iArr[31]) ^ iArr6[1]);
            iArr[29] = iArr4[2] ^ i(((iArr4[3] ^ iArr[31]) ^ iArr[30]) ^ iArr6[2]);
            iArr[28] = iArr4[3] ^ i(((iArr[31] ^ iArr[30]) ^ iArr[29]) ^ iArr6[3]);
            for (int i7 = 27; i7 >= 0; i7--) {
                iArr[i7] = iArr[i7 + 4] ^ i(((iArr[i7 + 3] ^ iArr[i7 + 2]) ^ iArr[i7 + 1]) ^ d[31 - i7]);
            }
        }
        return iArr;
    }

    public final int k(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    public final int l(int i) {
        byte[] bArr = c;
        return (bArr[i & 255] & 255) | ((bArr[(i >> 24) & 255] & 255) << 24) | ((bArr[(i >> 16) & 255] & 255) << 16) | ((bArr[(i >> 8) & 255] & 255) << 8);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.b != null) {
            if (i + 16 <= bArr.length) {
                if (i2 + 16 <= bArr2.length) {
                    this.f14706a[0] = Pack.bigEndianToInt(bArr, i);
                    this.f14706a[1] = Pack.bigEndianToInt(bArr, i + 4);
                    this.f14706a[2] = Pack.bigEndianToInt(bArr, i + 8);
                    this.f14706a[3] = Pack.bigEndianToInt(bArr, i + 12);
                    for (int i3 = 0; i3 < 32; i3 += 4) {
                        int[] iArr = this.f14706a;
                        iArr[0] = a(iArr, this.b[i3]);
                        int[] iArr2 = this.f14706a;
                        iArr2[1] = b(iArr2, this.b[i3 + 1]);
                        int[] iArr3 = this.f14706a;
                        iArr3[2] = c(iArr3, this.b[i3 + 2]);
                        int[] iArr4 = this.f14706a;
                        iArr4[3] = d(iArr4, this.b[i3 + 3]);
                    }
                    g(this.f14706a, 0);
                    Pack.intToBigEndian(this.f14706a[0], bArr2, i2);
                    Pack.intToBigEndian(this.f14706a[1], bArr2, i2 + 4);
                    Pack.intToBigEndian(this.f14706a[2], bArr2, i2 + 8);
                    Pack.intToBigEndian(this.f14706a[3], bArr2, i2 + 12);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("SM4 not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
