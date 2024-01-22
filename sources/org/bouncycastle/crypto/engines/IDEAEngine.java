package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes12.dex */
public class IDEAEngine implements BlockCipher {
    public static final int BLOCK_SIZE = 8;

    /* renamed from: a  reason: collision with root package name */
    public int[] f14685a = null;

    public int a(int i) {
        return (0 - i) & 65535;
    }

    public final int b(byte[] bArr, int i) {
        return ((bArr[i] << 8) & 65280) + (bArr[i + 1] & 255);
    }

    public final int[] c(byte[] bArr) {
        int i;
        int[] iArr = new int[52];
        int i2 = 0;
        if (bArr.length < 16) {
            byte[] bArr2 = new byte[16];
            System.arraycopy(bArr, 0, bArr2, 16 - bArr.length, bArr.length);
            bArr = bArr2;
        }
        while (true) {
            if (i2 >= 8) {
                break;
            }
            iArr[i2] = b(bArr, i2 * 2);
            i2++;
        }
        for (i = 8; i < 52; i++) {
            int i3 = i & 7;
            if (i3 < 6) {
                iArr[i] = (((iArr[i - 7] & 127) << 9) | (iArr[i - 6] >> 7)) & 65535;
            } else if (i3 == 6) {
                iArr[i] = (((iArr[i - 7] & 127) << 9) | (iArr[i - 14] >> 7)) & 65535;
            } else {
                iArr[i] = (((iArr[i - 15] & 127) << 9) | (iArr[i - 14] >> 7)) & 65535;
            }
        }
        return iArr;
    }

    public final int[] d(boolean z, byte[] bArr) {
        return z ? c(bArr) : f(c(bArr));
    }

    public final void e(int[] iArr, byte[] bArr, int i, byte[] bArr2, int i2) {
        int b = b(bArr, i);
        int b2 = b(bArr, i + 2);
        int b3 = b(bArr, i + 4);
        int b4 = b(bArr, i + 6);
        int i3 = 0;
        int i4 = b3;
        int i5 = b2;
        int i6 = b;
        int i7 = 0;
        while (i3 < 8) {
            int i8 = i7 + 1;
            int g = g(i6, iArr[i7]);
            int i9 = i8 + 1;
            int i10 = (i5 + iArr[i8]) & 65535;
            int i11 = i9 + 1;
            int i12 = (i4 + iArr[i9]) & 65535;
            int i13 = i11 + 1;
            int g2 = g(b4, iArr[i11]);
            int i14 = i13 + 1;
            int g3 = g(i12 ^ g, iArr[i13]);
            int g4 = g(((i10 ^ g2) + g3) & 65535, iArr[i14]);
            int i15 = (g3 + g4) & 65535;
            b4 = g2 ^ i15;
            i4 = i15 ^ i10;
            i3++;
            i5 = i12 ^ g4;
            i6 = g ^ g4;
            i7 = i14 + 1;
        }
        int i16 = i7 + 1;
        i(g(i6, iArr[i7]), bArr2, i2);
        int i17 = i16 + 1;
        i(i4 + iArr[i16], bArr2, i2 + 2);
        i(i5 + iArr[i17], bArr2, i2 + 4);
        i(g(b4, iArr[i17 + 1]), bArr2, i2 + 6);
    }

    public final int[] f(int[] iArr) {
        int[] iArr2 = new int[52];
        int h = h(iArr[0]);
        int i = 1;
        int a2 = a(iArr[1]);
        int a3 = a(iArr[2]);
        iArr2[51] = h(iArr[3]);
        iArr2[50] = a3;
        iArr2[49] = a2;
        int i2 = 48;
        iArr2[48] = h;
        int i3 = 4;
        while (i < 8) {
            int i4 = i3 + 1;
            int i5 = iArr[i3];
            int i6 = i4 + 1;
            int i7 = i2 - 1;
            iArr2[i7] = iArr[i4];
            int i8 = i7 - 1;
            iArr2[i8] = i5;
            int i9 = i6 + 1;
            int h2 = h(iArr[i6]);
            int i10 = i9 + 1;
            int a4 = a(iArr[i9]);
            int i11 = i10 + 1;
            int a5 = a(iArr[i10]);
            int i12 = i8 - 1;
            iArr2[i12] = h(iArr[i11]);
            int i13 = i12 - 1;
            iArr2[i13] = a4;
            int i14 = i13 - 1;
            iArr2[i14] = a5;
            i2 = i14 - 1;
            iArr2[i2] = h2;
            i++;
            i3 = i11 + 1;
        }
        int i15 = i3 + 1;
        int i16 = iArr[i3];
        int i17 = i15 + 1;
        int i18 = i2 - 1;
        iArr2[i18] = iArr[i15];
        int i19 = i18 - 1;
        iArr2[i19] = i16;
        int i20 = i17 + 1;
        int h3 = h(iArr[i17]);
        int i21 = i20 + 1;
        int a6 = a(iArr[i20]);
        int i22 = i21 + 1;
        int a7 = a(iArr[i21]);
        int i23 = i19 - 1;
        iArr2[i23] = h(iArr[i22]);
        int i24 = i23 - 1;
        iArr2[i24] = a7;
        int i25 = i24 - 1;
        iArr2[i25] = a6;
        iArr2[i25 - 1] = h3;
        return iArr2;
    }

    public final int g(int i, int i2) {
        int i3;
        if (i == 0) {
            i3 = 65537 - i2;
        } else if (i2 == 0) {
            i3 = 65537 - i;
        } else {
            int i4 = i * i2;
            int i5 = i4 & 65535;
            int i6 = i4 >>> 16;
            i3 = (i5 - i6) + (i5 < i6 ? 1 : 0);
        }
        return i3 & 65535;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "IDEA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    public final int h(int i) {
        if (i < 2) {
            return i;
        }
        int i2 = 65537 / i;
        int i3 = 65537 % i;
        int i4 = 1;
        while (i3 != 1) {
            int i5 = i / i3;
            i %= i3;
            i4 = (i4 + (i5 * i2)) & 65535;
            if (i == 1) {
                return i4;
            }
            int i6 = i3 / i;
            i3 %= i;
            i2 = (i2 + (i6 * i4)) & 65535;
        }
        return (1 - i2) & 65535;
    }

    public final void i(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) (i >>> 8);
        bArr[i2 + 1] = (byte) i;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.f14685a = d(z, ((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to IDEA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] iArr = this.f14685a;
        if (iArr != null) {
            if (i + 8 <= bArr.length) {
                if (i2 + 8 <= bArr2.length) {
                    e(iArr, bArr, i, bArr2, i2);
                    return 8;
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("IDEA engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
