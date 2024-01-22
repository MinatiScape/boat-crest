package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.SkippingStreamCipher;
import org.bouncycastle.crypto.StreamBlockCipher;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes13.dex */
public class SICBlockCipher extends StreamBlockCipher implements SkippingStreamCipher {
    public final BlockCipher b;
    public final int c;
    public byte[] d;
    public byte[] e;
    public byte[] f;
    public int g;

    public SICBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.b = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.c = blockSize;
        this.d = new byte[blockSize];
        this.e = new byte[blockSize];
        this.f = new byte[blockSize];
        this.g = 0;
    }

    public final void a(long j) {
        int i = 5;
        if (j >= 0) {
            long j2 = (this.g + j) / this.c;
            long j3 = j2;
            if (j2 > 255) {
                while (i >= 1) {
                    long j4 = 1 << (i * 8);
                    while (j3 >= j4) {
                        e(i);
                        j3 -= j4;
                    }
                    i--;
                }
            }
            d((int) j3);
            this.g = (int) ((j + this.g) - (this.c * j2));
            return;
        }
        long j5 = ((-j) - this.g) / this.c;
        long j6 = j5;
        if (j5 > 255) {
            while (i >= 1) {
                long j7 = 1 << (i * 8);
                while (j6 > j7) {
                    c(i);
                    j6 -= j7;
                }
                i--;
            }
        }
        for (long j8 = 0; j8 != j6; j8++) {
            c(0);
        }
        int i2 = (int) (this.g + j + (this.c * j5));
        if (i2 >= 0) {
            this.g = 0;
            return;
        }
        c(0);
        this.g = this.c + i2;
    }

    public final void b() {
        if (this.d.length >= this.c) {
            return;
        }
        int i = 0;
        while (true) {
            byte[] bArr = this.d;
            if (i == bArr.length) {
                return;
            }
            if (this.e[i] != bArr[i]) {
                throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
            }
            i++;
        }
    }

    public final void c(int i) {
        byte[] bArr;
        byte b;
        int length = this.e.length - i;
        do {
            length--;
            if (length < 0) {
                return;
            }
            b = (byte) (bArr[length] - 1);
            this.e[length] = b;
        } while (b == -1);
    }

    @Override // org.bouncycastle.crypto.StreamBlockCipher
    public byte calculateByte(byte b) throws DataLengthException, IllegalStateException {
        int i = this.g;
        if (i == 0) {
            this.b.processBlock(this.e, 0, this.f, 0);
            byte[] bArr = this.f;
            int i2 = this.g;
            this.g = i2 + 1;
            return (byte) (b ^ bArr[i2]);
        }
        byte[] bArr2 = this.f;
        int i3 = i + 1;
        this.g = i3;
        byte b2 = (byte) (b ^ bArr2[i]);
        if (i3 == this.e.length) {
            this.g = 0;
            e(0);
            b();
        }
        return b2;
    }

    public final void d(int i) {
        byte[] bArr = this.e;
        byte b = bArr[bArr.length - 1];
        int length = bArr.length - 1;
        bArr[length] = (byte) (bArr[length] + i);
        if (b == 0 || bArr[bArr.length - 1] >= b) {
            return;
        }
        e(1);
    }

    public final void e(int i) {
        byte b;
        int length = this.e.length - i;
        do {
            length--;
            if (length < 0) {
                return;
            }
            byte[] bArr = this.e;
            b = (byte) (bArr[length] + 1);
            bArr[length] = b;
        } while (b == 0);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.b.getAlgorithmName() + "/SIC";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.b.getBlockSize();
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long getPosition() {
        byte[] bArr = this.e;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        int i = length - 1;
        while (i >= 1) {
            byte[] bArr3 = this.d;
            int i2 = i < bArr3.length ? (bArr2[i] & 255) - (bArr3[i] & 255) : bArr2[i] & 255;
            if (i2 < 0) {
                int i3 = i - 1;
                bArr2[i3] = (byte) (bArr2[i3] - 1);
                i2 += 256;
            }
            bArr2[i] = (byte) i2;
            i--;
        }
        return (Pack.bigEndianToLong(bArr2, length - 8) * this.c) + this.g;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        byte[] clone = Arrays.clone(parametersWithIV.getIV());
        this.d = clone;
        int i = this.c;
        if (i < clone.length) {
            throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.c + " bytes.");
        }
        int i2 = 8 > i / 2 ? i / 2 : 8;
        if (i - clone.length <= i2) {
            if (parametersWithIV.getParameters() != null) {
                this.b.init(true, parametersWithIV.getParameters());
            }
            reset();
            return;
        }
        throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.c - i2) + " bytes.");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.c, bArr2, i2);
        return this.c;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        Arrays.fill(this.e, (byte) 0);
        byte[] bArr = this.d;
        System.arraycopy(bArr, 0, this.e, 0, bArr.length);
        this.b.reset();
        this.g = 0;
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long seekTo(long j) {
        reset();
        return skip(j);
    }

    @Override // org.bouncycastle.crypto.SkippingCipher
    public long skip(long j) {
        a(j);
        b();
        this.b.processBlock(this.e, 0, this.f, 0);
        return j;
    }
}
