package org.bouncycastle.crypto.engines;

import com.coveiot.sdk.ble.api.BleUUID;
import com.crrepa.j.o;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.signers.PSSSigner;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class GOST3412_2015Engine implements BlockCipher {
    public static final int BLOCK_SIZE = 16;
    public static final byte[] g = {-4, o.c, -35, 17, -49, 110, 49, 22, -5, -60, -6, -38, 35, -59, 4, 77, -23, 119, -16, -37, -109, 46, -103, -70, 23, 54, com.crrepa.c.a.J1, -69, 20, -51, 95, -63, -7, 24, 101, 90, -30, 92, -17, 33, -127, 28, 60, 66, -117, 1, -114, 79, 5, -124, 2, -82, -29, 106, -113, BleUUID.CMD_ID_A0, 6, 11, -19, -104, Byte.MAX_VALUE, -44, -45, 31, -21, 52, 44, 81, com.crrepa.c.a.A, -56, com.htsmart.wristband2.a.a.a.W0, -85, com.crrepa.c.a.K1, 42, 104, -94, -3, 58, -50, -52, BleUUID.CMD_ID_B5, com.htsmart.wristband2.a.a.a.J1, 14, 86, 8, 12, com.htsmart.wristband2.a.a.a.R1, 18, -65, 114, 19, 71, -100, BleUUID.CMD_ID_B7, com.htsmart.wristband2.a.a.a.t1, -121, 21, -95, BleUUID.CMD_ID_96, 41, 16, 123, -102, -57, com.crrepa.c.a.L1, -111, 120, com.crrepa.c.a.Z0, -99, -98, -78, -79, 50, 117, 25, 61, -1, 53, -118, com.crrepa.c.a.l1, 109, 84, -58, Byte.MIN_VALUE, -61, -67, 13, 87, -33, com.crrepa.c.a.N1, 36, -87, 62, -88, 67, -55, -41, com.htsmart.wristband2.a.a.a.U1, -42, -10, 124, 34, -71, 3, -32, 15, -20, -34, com.htsmart.wristband2.a.a.a.V1, -108, -80, PSSSigner.TRAILER_IMPLICIT, -36, -24, 40, com.htsmart.wristband2.a.a.a.d1, com.htsmart.wristband2.a.a.a.c1, 51, 10, com.htsmart.wristband2.a.a.a.Y0, -89, BleUUID.CMD_ID_97, 96, 115, 30, 0, 98, 68, 26, BleUUID.CMD_ID_B8, 56, -126, 100, -97, 38, 65, BleUUID.CMD_ID_AD, com.crrepa.c.a.E0, com.htsmart.wristband2.a.a.a.U0, -110, 39, com.htsmart.wristband2.a.a.a.u1, 85, 47, -116, -93, -91, com.crrepa.c.a.h1, 105, -43, -107, 59, 7, com.htsmart.wristband2.a.a.a.o1, BleUUID.CMD_ID_B3, 64, -122, -84, 29, -9, 48, 55, 107, -28, -120, -39, -25, BleUUID.CMD_ID_89, -31, 27, -125, 73, com.htsmart.wristband2.a.a.a.a1, 63, -8, -2, -115, 83, -86, BleUUID.CMD_ID_90, -54, -40, -123, 97, 32, 113, 103, -92, 45, 43, 9, com.htsmart.wristband2.a.a.a.r1, -53, -101, 37, -48, -66, -27, 108, 82, 89, -90, 116, -46, -26, com.crrepa.c.a.M1, BleUUID.CMD_ID_B4, -64, -47, 102, BleUUID.CMD_ID_AF, -62, 57, 75, 99, BleUUID.CMD_ID_B6};
    public static final byte[] h = {-91, 45, 50, -113, 14, 48, 56, -64, 84, -26, -98, 57, 85, com.crrepa.c.a.l1, 82, -111, 100, 3, 87, 90, 28, 96, 7, 24, 33, 114, -88, -47, 41, -58, -92, 63, -32, 39, -115, 12, -126, com.crrepa.c.a.A, -82, BleUUID.CMD_ID_B4, -102, 99, 73, -27, 66, -28, 21, BleUUID.CMD_ID_B7, -56, 6, com.htsmart.wristband2.a.a.a.J1, -99, 65, 117, 25, -55, -86, -4, 77, -65, 42, 115, -124, -43, -61, BleUUID.CMD_ID_AF, 43, -122, -89, -79, -78, com.htsmart.wristband2.a.a.a.r1, com.htsmart.wristband2.a.a.a.U0, -45, -97, -3, -44, 15, -100, 47, -101, 67, -17, -39, com.htsmart.wristband2.a.a.a.U1, BleUUID.CMD_ID_B6, 83, Byte.MAX_VALUE, -63, -16, 35, -25, 37, com.htsmart.wristband2.a.a.a.u1, BleUUID.CMD_ID_B5, 30, -94, -33, -90, -2, -84, 34, -7, -30, com.htsmart.wristband2.a.a.a.Y0, PSSSigner.TRAILER_IMPLICIT, 53, -54, o.c, 120, 5, 107, 81, -31, 89, -93, com.crrepa.c.a.K1, 113, 86, 17, 106, BleUUID.CMD_ID_89, -108, 101, -116, -69, 119, 60, 123, 40, -85, -46, 49, -34, -60, 95, -52, -49, com.htsmart.wristband2.a.a.a.R1, 44, BleUUID.CMD_ID_B8, -40, 46, 54, -37, 105, BleUUID.CMD_ID_B3, 20, -107, -66, 98, -95, 59, 22, 102, -23, 92, 108, 109, BleUUID.CMD_ID_AD, 55, 97, 75, -71, -29, -70, com.crrepa.c.a.J1, BleUUID.CMD_ID_A0, -123, -125, -38, 71, -59, -80, 51, -6, BleUUID.CMD_ID_96, com.crrepa.c.a.Z0, 110, -62, -10, com.htsmart.wristband2.a.a.a.d1, -1, com.htsmart.wristband2.a.a.a.t1, -87, -114, 23, 27, BleUUID.CMD_ID_97, com.crrepa.c.a.h1, -20, com.htsmart.wristband2.a.a.a.o1, -9, 31, -5, 124, 9, 13, com.htsmart.wristband2.a.a.a.V1, 103, com.crrepa.c.a.E0, -121, -36, -24, 79, 29, com.htsmart.wristband2.a.a.a.c1, 4, -21, -8, com.crrepa.c.a.L1, 62, 61, -67, -118, -120, -35, -51, 11, 19, -104, 2, -109, Byte.MIN_VALUE, BleUUID.CMD_ID_90, -48, 36, 52, -53, -19, com.crrepa.c.a.M1, -50, -103, 16, 68, 64, -110, 58, 1, 38, 18, 26, com.htsmart.wristband2.a.a.a.W0, 104, com.crrepa.c.a.N1, -127, -117, -57, -42, 32, 10, 8, 0, com.htsmart.wristband2.a.a.a.a1, -41, 116};
    public boolean e;

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f14680a = {-108, 32, -123, 16, -62, -64, 1, -5, 1, -64, -62, 16, -123, 32, -108, 1};
    public int b = 32;
    public int c = 32 / 2;
    public byte[][] d = null;
    public byte[][] f = k();

    public static byte[][] k() {
        byte[][] bArr = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr[i] = new byte[256];
            for (int i2 = 0; i2 < 256; i2++) {
                bArr[i][i2] = o((byte) i, (byte) i2);
            }
        }
        return bArr;
    }

    public static byte o(byte b, byte b2) {
        byte b3 = 0;
        for (byte b4 = 0; b4 < 8 && b != 0 && b2 != 0; b4 = (byte) (b4 + 1)) {
            if ((b2 & 1) != 0) {
                b3 = (byte) (b3 ^ b);
            }
            byte b5 = (byte) (b & 128);
            b = (byte) (b << 1);
            if (b5 != 0) {
                b = (byte) (b ^ 195);
            }
            b2 = (byte) (b2 >> 1);
        }
        return b3;
    }

    public final void a(byte[] bArr, int i) {
        Arrays.clear(bArr);
        bArr[15] = (byte) i;
        d(bArr);
    }

    public final void b(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        byte[] e = e(bArr, bArr2);
        h(e, bArr3);
        System.arraycopy(bArr2, 0, bArr3, 0, this.c);
        System.arraycopy(e, 0, bArr2, 0, this.c);
    }

    public final void c(byte[] bArr, int i, byte[] bArr2, int i2) {
        byte[][] bArr3;
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, i, bArr4, 0, 16);
        int i3 = 9;
        if (this.e) {
            for (int i4 = 0; i4 < 9; i4++) {
                bArr4 = Arrays.copyOf(e(this.d[i4], bArr4), 16);
            }
            h(bArr4, this.d[9]);
        } else {
            while (true) {
                bArr3 = this.d;
                if (i3 <= 0) {
                    break;
                }
                bArr4 = Arrays.copyOf(i(bArr3[i3], bArr4), 16);
                i3--;
            }
            h(bArr4, bArr3[0]);
        }
        System.arraycopy(bArr4, 0, bArr2, i2, 16);
    }

    public final void d(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            f(bArr);
        }
    }

    public final byte[] e(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        h(copyOf, bArr2);
        g(copyOf);
        d(copyOf);
        return copyOf;
    }

    public final void f(byte[] bArr) {
        byte p = p(bArr);
        System.arraycopy(bArr, 0, bArr, 1, 15);
        bArr[0] = p;
    }

    public final void g(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = g[q(bArr[i])];
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "GOST3412_2015";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    public final void h(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = (byte) (bArr[i] ^ bArr2[i]);
        }
    }

    public final byte[] i(byte[] bArr, byte[] bArr2) {
        byte[] copyOf = Arrays.copyOf(bArr, bArr.length);
        h(copyOf, bArr2);
        l(copyOf);
        n(copyOf);
        return copyOf;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (cipherParameters instanceof KeyParameter) {
            this.e = z;
            j(((KeyParameter) cipherParameters).getKey());
        } else if (cipherParameters == null) {
        } else {
            throw new IllegalArgumentException("invalid parameter passed to GOST3412_2015 init - " + cipherParameters.getClass().getName());
        }
    }

    public final void j(byte[] bArr) {
        int i;
        if (bArr.length != this.b) {
            throw new IllegalArgumentException("Key length invalid. Key needs to be 32 byte - 256 bit!!!");
        }
        this.d = new byte[10];
        for (int i2 = 0; i2 < 10; i2++) {
            this.d[i2] = new byte[this.c];
        }
        int i3 = this.c;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[i3];
        int i4 = 0;
        while (true) {
            i = this.c;
            if (i4 >= i) {
                break;
            }
            byte[][] bArr4 = this.d;
            byte[] bArr5 = bArr4[0];
            byte b = bArr[i4];
            bArr2[i4] = b;
            bArr5[i4] = b;
            byte[] bArr6 = bArr4[1];
            byte b2 = bArr[i + i4];
            bArr3[i4] = b2;
            bArr6[i4] = b2;
            i4++;
        }
        byte[] bArr7 = new byte[i];
        for (int i5 = 1; i5 < 5; i5++) {
            for (int i6 = 1; i6 <= 8; i6++) {
                a(bArr7, ((i5 - 1) * 8) + i6);
                b(bArr7, bArr2, bArr3);
            }
            int i7 = i5 * 2;
            System.arraycopy(bArr2, 0, this.d[i7], 0, this.c);
            System.arraycopy(bArr3, 0, this.d[i7 + 1], 0, this.c);
        }
    }

    public final void l(byte[] bArr) {
        for (int i = 0; i < 16; i++) {
            m(bArr);
        }
    }

    public final void m(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(bArr, 1, bArr2, 0, 15);
        bArr2[15] = bArr[0];
        byte p = p(bArr2);
        System.arraycopy(bArr, 1, bArr, 0, 15);
        bArr[15] = p;
    }

    public final void n(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            bArr[i] = h[q(bArr[i])];
        }
    }

    public final byte p(byte[] bArr) {
        byte b = bArr[15];
        for (int i = 14; i >= 0; i--) {
            b = (byte) (b ^ this.f[q(bArr[i])][q(this.f14680a[i])]);
        }
        return b;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        if (this.d != null) {
            if (i + 16 <= bArr.length) {
                if (i2 + 16 <= bArr2.length) {
                    c(bArr, i, bArr2, i2);
                    return 16;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("GOST3412_2015 engine not initialised");
    }

    public final int q(byte b) {
        return b & 255;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
