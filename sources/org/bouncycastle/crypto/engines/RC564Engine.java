package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.RC5Parameters;
/* loaded from: classes12.dex */
public class RC564Engine implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public int f14695a = 12;
    public long[] b = null;
    public boolean c;

    public final long a(byte[] bArr, int i) {
        long j = 0;
        for (int i2 = 7; i2 >= 0; i2--) {
            j = (j << 8) + (bArr[i2 + i] & 255);
        }
        return j;
    }

    public final int b(byte[] bArr, int i, byte[] bArr2, int i2) {
        long a2 = a(bArr, i);
        long a3 = a(bArr, i + 8);
        for (int i3 = this.f14695a; i3 >= 1; i3--) {
            int i4 = i3 * 2;
            a3 = e(a3 - this.b[i4 + 1], a2) ^ a2;
            a2 = e(a2 - this.b[i4], a3) ^ a3;
        }
        g(a2 - this.b[0], bArr2, i2);
        g(a3 - this.b[1], bArr2, i2 + 8);
        return 16;
    }

    public final int c(byte[] bArr, int i, byte[] bArr2, int i2) {
        long a2 = a(bArr, i) + this.b[0];
        long a3 = a(bArr, i + 8) + this.b[1];
        for (int i3 = 1; i3 <= this.f14695a; i3++) {
            int i4 = i3 * 2;
            a2 = d(a2 ^ a3, a3) + this.b[i4];
            a3 = d(a3 ^ a2, a2) + this.b[i4 + 1];
        }
        g(a2, bArr2, i2);
        g(a3, bArr2, i2 + 8);
        return 16;
    }

    public final long d(long j, long j2) {
        long j3 = j2 & 63;
        return (j >>> ((int) (64 - j3))) | (j << ((int) j3));
    }

    public final long e(long j, long j2) {
        long j3 = j2 & 63;
        return (j << ((int) (64 - j3))) | (j >>> ((int) j3));
    }

    public final void f(byte[] bArr) {
        long[] jArr;
        int length = (bArr.length + 7) / 8;
        long[] jArr2 = new long[length];
        for (int i = 0; i != bArr.length; i++) {
            int i2 = i / 8;
            jArr2[i2] = jArr2[i2] + ((bArr[i] & 255) << ((i % 8) * 8));
        }
        long[] jArr3 = new long[(this.f14695a + 1) * 2];
        this.b = jArr3;
        jArr3[0] = -5196783011329398165L;
        int i3 = 1;
        while (true) {
            jArr = this.b;
            if (i3 >= jArr.length) {
                break;
            }
            jArr[i3] = jArr[i3 - 1] - 7046029254386353131L;
            i3++;
        }
        int length2 = length > jArr.length ? length * 3 : jArr.length * 3;
        long j = 0;
        long j2 = 0;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < length2; i6++) {
            long[] jArr4 = this.b;
            j = d(jArr4[i4] + j + j2, 3L);
            jArr4[i4] = j;
            j2 = d(jArr2[i5] + j + j2, j2 + j);
            jArr2[i5] = j2;
            i4 = (i4 + 1) % this.b.length;
            i5 = (i5 + 1) % length;
        }
    }

    public final void g(long j, byte[] bArr, int i) {
        for (int i2 = 0; i2 < 8; i2++) {
            bArr[i2 + i] = (byte) j;
            j >>>= 8;
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "RC5-64";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof RC5Parameters)) {
            throw new IllegalArgumentException("invalid parameter passed to RC564 init - " + cipherParameters.getClass().getName());
        }
        RC5Parameters rC5Parameters = (RC5Parameters) cipherParameters;
        this.c = z;
        this.f14695a = rC5Parameters.getRounds();
        f(rC5Parameters.getKey());
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        return this.c ? c(bArr, i, bArr2, i2) : b(bArr, i, bArr2, i2);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
