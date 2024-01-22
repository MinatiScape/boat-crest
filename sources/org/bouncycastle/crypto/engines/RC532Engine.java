package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RC5Parameters;
/* loaded from: classes12.dex */
public class RC532Engine implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public int f14694a = 12;
    public int[] b = null;
    public boolean c;

    public final int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    public final int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a2 = a(bArr, i);
        int a3 = a(bArr, i + 4);
        for (int i3 = this.f14694a; i3 >= 1; i3--) {
            int i4 = i3 * 2;
            a3 = e(a3 - this.b[i4 + 1], a2) ^ a2;
            a2 = e(a2 - this.b[i4], a3) ^ a3;
        }
        g(a2 - this.b[0], bArr2, i2);
        g(a3 - this.b[1], bArr2, i2 + 4);
        return 8;
    }

    public final int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        int a2 = a(bArr, i) + this.b[0];
        int a3 = a(bArr, i + 4) + this.b[1];
        for (int i3 = 1; i3 <= this.f14694a; i3++) {
            int i4 = i3 * 2;
            a2 = d(a2 ^ a3, a3) + this.b[i4];
            a3 = d(a3 ^ a2, a2) + this.b[i4 + 1];
        }
        g(a2, bArr2, i2);
        g(a3, bArr2, i2 + 4);
        return 8;
    }

    public final int d(int i, int i2) {
        int i3 = i2 & 31;
        return (i >>> (32 - i3)) | (i << i3);
    }

    public final int e(int i, int i2) {
        int i3 = i2 & 31;
        return (i << (32 - i3)) | (i >>> i3);
    }

    public final void f(byte[] bArr) {
        int[] iArr;
        int length = (bArr.length + 3) / 4;
        int[] iArr2 = new int[length];
        for (int i = 0; i != bArr.length; i++) {
            int i2 = i / 4;
            iArr2[i2] = iArr2[i2] + ((bArr[i] & 255) << ((i % 4) * 8));
        }
        int[] iArr3 = new int[(this.f14694a + 1) * 2];
        this.b = iArr3;
        iArr3[0] = -1209970333;
        int i3 = 1;
        while (true) {
            iArr = this.b;
            if (i3 >= iArr.length) {
                break;
            }
            iArr[i3] = iArr[i3 - 1] - 1640531527;
            i3++;
        }
        int length2 = length > iArr.length ? length * 3 : iArr.length * 3;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        for (int i8 = 0; i8 < length2; i8++) {
            int[] iArr4 = this.b;
            i5 = d(iArr4[i4] + i5 + i6, 3);
            iArr4[i4] = i5;
            i6 = d(iArr2[i7] + i5 + i6, i6 + i5);
            iArr2[i7] = i6;
            i4 = (i4 + 1) % this.b.length;
            i7 = (i7 + 1) % length;
        }
    }

    public final void g(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >> 8);
        bArr[i2 + 2] = (byte) (i >> 16);
        bArr[i2 + 3] = (byte) (i >> 24);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC5-32";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof RC5Parameters) {
            RC5Parameters rC5Parameters = (RC5Parameters) cipherParameters;
            this.f14694a = rC5Parameters.getRounds();
            f(rC5Parameters.getKey());
        } else if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("invalid parameter passed to RC532 init - " + cipherParameters.getClass().getName());
        } else {
            f(((KeyParameter) cipherParameters).getKey());
        }
        this.c = z;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.c ? c(bArr, i, bArr2, i2) : b(bArr, i, bArr2, i2);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
