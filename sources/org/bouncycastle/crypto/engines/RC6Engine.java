package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;
/* loaded from: classes12.dex */
public class RC6Engine implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public int[] f14696a = null;
    public boolean b;

    public final int a(byte[] bArr, int i) {
        int i2 = 0;
        for (int i3 = 3; i3 >= 0; i3--) {
            i2 = (i2 << 8) + (bArr[i3 + i] & 255);
        }
        return i2;
    }

    public final int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a2 = a(bArr, i);
        int a3 = a(bArr, i + 4);
        int a4 = a(bArr, i + 8);
        int a5 = a(bArr, i + 12);
        int[] iArr = this.f14696a;
        int i3 = a4 - iArr[43];
        int i4 = a2 - iArr[42];
        int i5 = 20;
        while (i5 >= 1) {
            int d = d(((i4 * 2) + 1) * i4, 5);
            int d2 = d(((i3 * 2) + 1) * i3, 5);
            int i6 = i5 * 2;
            i5--;
            int i7 = i4;
            i4 = e(a5 - this.f14696a[i6], d2) ^ d;
            a5 = i3;
            i3 = e(a3 - this.f14696a[i6 + 1], d) ^ d2;
            a3 = i7;
        }
        int[] iArr2 = this.f14696a;
        g(i4, bArr2, i2);
        g(a3 - iArr2[0], bArr2, i2 + 4);
        g(i3, bArr2, i2 + 8);
        g(a5 - iArr2[1], bArr2, i2 + 12);
        return 16;
    }

    public final int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a2 = a(bArr, i);
        int a3 = a(bArr, i + 4);
        int a4 = a(bArr, i + 8);
        int a5 = a(bArr, i + 12);
        int[] iArr = this.f14696a;
        int i3 = a3 + iArr[0];
        int i4 = a5 + iArr[1];
        int i5 = 1;
        while (i5 <= 20) {
            int d = d(((i3 * 2) + 1) * i3, 5);
            int d2 = d(((i4 * 2) + 1) * i4, 5);
            int i6 = i5 * 2;
            int d3 = d(a4 ^ d2, d) + this.f14696a[i6 + 1];
            i5++;
            a4 = i4;
            i4 = d(a2 ^ d, d2) + this.f14696a[i6];
            a2 = i3;
            i3 = d3;
        }
        int[] iArr2 = this.f14696a;
        int i7 = a4 + iArr2[43];
        g(a2 + iArr2[42], bArr2, i2);
        g(i3, bArr2, i2 + 4);
        g(i7, bArr2, i2 + 8);
        g(i4, bArr2, i2 + 12);
        return 16;
    }

    public final int d(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    public final int e(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    public final void f(byte[] bArr) {
        int[] iArr;
        int length = (bArr.length + 3) / 4;
        int length2 = ((bArr.length + 4) - 1) / 4;
        int[] iArr2 = new int[length2];
        for (int length3 = bArr.length - 1; length3 >= 0; length3--) {
            int i = length3 / 4;
            iArr2[i] = (iArr2[i] << 8) + (bArr[length3] & 255);
        }
        int[] iArr3 = new int[44];
        this.f14696a = iArr3;
        iArr3[0] = -1209970333;
        int i2 = 1;
        while (true) {
            iArr = this.f14696a;
            if (i2 >= iArr.length) {
                break;
            }
            iArr[i2] = iArr[i2 - 1] - 1640531527;
            i2++;
        }
        int length4 = length2 > iArr.length ? length2 * 3 : iArr.length * 3;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < length4; i7++) {
            int[] iArr4 = this.f14696a;
            i4 = d(iArr4[i3] + i4 + i5, 3);
            iArr4[i3] = i4;
            i5 = d(iArr2[i6] + i4 + i5, i5 + i4);
            iArr2[i6] = i5;
            i3 = (i3 + 1) % this.f14696a.length;
            i6 = (i6 + 1) % length2;
        }
    }

    public final void g(int i, byte[] bArr, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            bArr[i3 + i2] = (byte) i;
            i >>>= 8;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC6";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.b = z;
            f(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to RC6 init - " + cipherParameters.getClass().getName());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int blockSize = getBlockSize();
        if (this.f14696a != null) {
            if (i + blockSize <= bArr.length) {
                if (blockSize + i2 <= bArr2.length) {
                    return this.b ? c(bArr, i, bArr2, i2) : b(bArr, i, bArr2, i2);
                }
                throw new OutputLengthException("output buffer too short");
            }
            throw new DataLengthException("input buffer too short");
        }
        throw new IllegalStateException("RC6 engine not initialised");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
