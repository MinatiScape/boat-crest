package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class KXTSBlockCipher extends BufferedBlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public final int f14774a;
    public final long b;
    public final long[] c;
    public final long[] d;
    public int e;

    public KXTSBlockCipher(BlockCipher blockCipher) {
        this.cipher = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.f14774a = blockSize;
        this.b = getReductionPolynomial(blockSize);
        this.c = new long[blockSize >>> 3];
        this.d = new long[blockSize >>> 3];
        this.e = -1;
    }

    public static void a(long j, long[] jArr) {
        long j2 = 0;
        int i = 0;
        while (i < jArr.length) {
            long j3 = jArr[i];
            jArr[i] = j2 ^ (j3 << 1);
            i++;
            j2 = j3 >>> 63;
        }
        jArr[0] = (j & (-j2)) ^ jArr[0];
    }

    public static long getReductionPolynomial(int i) {
        if (i != 16) {
            if (i != 32) {
                if (i == 64) {
                    return 293L;
                }
                throw new IllegalArgumentException("Only 128, 256, and 512 -bit block sizes supported");
            }
            return 1061L;
        }
        return 135L;
    }

    public final void b(byte[] bArr, int i, byte[] bArr2, int i2) {
        int i3 = this.e;
        if (i3 == -1) {
            throw new IllegalStateException("Attempt to process too many blocks");
        }
        this.e = i3 + 1;
        a(this.b, this.d);
        byte[] bArr3 = new byte[this.f14774a];
        Pack.longToLittleEndian(this.d, bArr3, 0);
        int i4 = this.f14774a;
        byte[] bArr4 = new byte[i4];
        System.arraycopy(bArr3, 0, bArr4, 0, i4);
        for (int i5 = 0; i5 < this.f14774a; i5++) {
            bArr4[i5] = (byte) (bArr4[i5] ^ bArr[i + i5]);
        }
        this.cipher.processBlock(bArr4, 0, bArr4, 0);
        for (int i6 = 0; i6 < this.f14774a; i6++) {
            bArr2[i2 + i6] = (byte) (bArr4[i6] ^ bArr3[i6]);
        }
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int doFinal(byte[] bArr, int i) {
        reset();
        return 0;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getOutputSize(int i) {
        return i;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int getUpdateOutputSize(int i) {
        return i;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("Invalid parameters passed");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        CipherParameters parameters = parametersWithIV.getParameters();
        byte[] iv = parametersWithIV.getIV();
        int length = iv.length;
        int i = this.f14774a;
        if (length != i) {
            throw new IllegalArgumentException("Currently only support IVs of exactly one block");
        }
        byte[] bArr = new byte[i];
        System.arraycopy(iv, 0, bArr, 0, i);
        this.cipher.init(true, parameters);
        this.cipher.processBlock(bArr, 0, bArr, 0);
        this.cipher.init(z, parameters);
        Pack.littleEndianToLong(bArr, 0, this.c);
        long[] jArr = this.c;
        System.arraycopy(jArr, 0, this.d, 0, jArr.length);
        this.e = 0;
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int processByte(byte b, byte[] bArr, int i) {
        throw new IllegalStateException("unsupported operation");
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public int processBytes(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (bArr.length - i >= i2) {
            if (bArr2.length - i >= i2) {
                if (i2 % this.f14774a == 0) {
                    int i4 = 0;
                    while (i4 < i2) {
                        b(bArr, i + i4, bArr2, i3 + i4);
                        i4 += this.f14774a;
                    }
                    return i2;
                }
                throw new IllegalArgumentException("Partial blocks not supported");
            }
            throw new OutputLengthException("Output buffer too short");
        }
        throw new DataLengthException("Input buffer too short");
    }

    @Override // org.bouncycastle.crypto.BufferedBlockCipher
    public void reset() {
        this.cipher.reset();
        long[] jArr = this.c;
        System.arraycopy(jArr, 0, this.d, 0, jArr.length);
        this.e = 0;
    }
}
