package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes12.dex */
public class XTEAEngine implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public int[] f14714a = new int[4];
    public int[] b = new int[32];
    public int[] c = new int[32];
    public boolean d = false;
    public boolean e;

    public final int a(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i] << 24) | ((bArr[i2] & 255) << 16);
        return (bArr[i3 + 1] & 255) | i4 | ((bArr[i3] & 255) << 8);
    }

    public final int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a2 = a(bArr, i);
        int a3 = a(bArr, i + 4);
        for (int i3 = 31; i3 >= 0; i3--) {
            a3 -= (((a2 << 4) ^ (a2 >>> 5)) + a2) ^ this.c[i3];
            a2 -= (((a3 << 4) ^ (a3 >>> 5)) + a3) ^ this.b[i3];
        }
        e(a2, bArr2, i2);
        e(a3, bArr2, i2 + 4);
        return 8;
    }

    public final int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a2 = a(bArr, i);
        int a3 = a(bArr, i + 4);
        for (int i3 = 0; i3 < 32; i3++) {
            a2 += (((a3 << 4) ^ (a3 >>> 5)) + a3) ^ this.b[i3];
            a3 += (((a2 << 4) ^ (a2 >>> 5)) + a2) ^ this.c[i3];
        }
        e(a2, bArr2, i2);
        e(a3, bArr2, i2 + 4);
        return 8;
    }

    public final void d(byte[] bArr) {
        if (bArr.length != 16) {
            throw new IllegalArgumentException("Key size must be 128 bits.");
        }
        int i = 0;
        int i2 = 0;
        while (i < 4) {
            this.f14714a[i] = a(bArr, i2);
            i++;
            i2 += 4;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < 32; i4++) {
            int[] iArr = this.b;
            int[] iArr2 = this.f14714a;
            iArr[i4] = iArr2[i3 & 3] + i3;
            i3 -= 1640531527;
            this.c[i4] = iArr2[(i3 >>> 11) & 3] + i3;
        }
    }

    public final void e(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "XTEA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.e = z;
            this.d = true;
            d(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to TEA init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this.d) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (i + 8 <= bArr.length) {
            if (i2 + 8 <= bArr2.length) {
                return this.e ? c(bArr, i, bArr2, i2) : b(bArr, i, bArr2, i2);
            }
            throw new OutputLengthException("output buffer too short");
        } else {
            throw new DataLengthException("input buffer too short");
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
