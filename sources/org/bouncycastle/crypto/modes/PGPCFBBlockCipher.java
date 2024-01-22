package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ParametersWithIV;
/* loaded from: classes13.dex */
public class PGPCFBBlockCipher implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14779a;
    public byte[] b;
    public byte[] c;
    public byte[] d;
    public BlockCipher e;
    public int f;
    public int g;
    public boolean h;
    public boolean i;

    public PGPCFBBlockCipher(BlockCipher blockCipher, boolean z) {
        this.e = blockCipher;
        this.i = z;
        int blockSize = blockCipher.getBlockSize();
        this.g = blockSize;
        this.f14779a = new byte[blockSize];
        this.b = new byte[blockSize];
        this.c = new byte[blockSize];
        this.d = new byte[blockSize];
    }

    public final int a(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3 = this.g;
        if (i + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i4 = 0;
        this.e.processBlock(this.b, 0, this.c, 0);
        for (int i5 = 0; i5 < this.g; i5++) {
            bArr2[i2 + i5] = e(bArr[i + i5], i5);
        }
        while (true) {
            int i6 = this.g;
            if (i4 >= i6) {
                return i6;
            }
            this.b[i4] = bArr[i + i4];
            i4++;
        }
    }

    public final int b(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        int i4 = this.g;
        if (i + i4 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + i4 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i5 = this.f;
        if (i5 == 0) {
            for (int i6 = 0; i6 < this.g; i6++) {
                this.b[i6] = bArr[i + i6];
            }
            this.e.processBlock(this.b, 0, this.c, 0);
            this.f += this.g;
            return 0;
        } else if (i5 != i4) {
            if (i5 >= i4 + 2) {
                System.arraycopy(bArr, i, this.d, 0, i4);
                bArr2[i2 + 0] = e(this.d[0], this.g - 2);
                bArr2[i2 + 1] = e(this.d[1], this.g - 1);
                System.arraycopy(this.d, 0, this.b, this.g - 2, 2);
                this.e.processBlock(this.b, 0, this.c, 0);
                int i7 = 0;
                while (true) {
                    i3 = this.g;
                    if (i7 >= i3 - 2) {
                        break;
                    }
                    bArr2[i2 + i7 + 2] = e(this.d[i7 + 2], i7);
                    i7++;
                }
                System.arraycopy(this.d, 2, this.b, 0, i3 - 2);
            }
            return this.g;
        } else {
            System.arraycopy(bArr, i, this.d, 0, i4);
            byte[] bArr3 = this.b;
            System.arraycopy(bArr3, 2, bArr3, 0, this.g - 2);
            byte[] bArr4 = this.b;
            int i8 = this.g;
            byte[] bArr5 = this.d;
            bArr4[i8 - 2] = bArr5[0];
            bArr4[i8 - 1] = bArr5[1];
            this.e.processBlock(bArr4, 0, this.c, 0);
            int i9 = 0;
            while (true) {
                int i10 = this.g;
                if (i9 >= i10 - 2) {
                    System.arraycopy(this.d, 2, this.b, 0, i10 - 2);
                    this.f += 2;
                    return this.g - 2;
                }
                bArr2[i2 + i9] = e(this.d[i9 + 2], i9);
                i9++;
            }
        }
    }

    public final int c(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3 = this.g;
        if (i + i3 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i3 + i2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        int i4 = 0;
        this.e.processBlock(this.b, 0, this.c, 0);
        for (int i5 = 0; i5 < this.g; i5++) {
            bArr2[i2 + i5] = e(bArr[i + i5], i5);
        }
        while (true) {
            int i6 = this.g;
            if (i4 >= i6) {
                return i6;
            }
            this.b[i4] = bArr2[i2 + i4];
            i4++;
        }
    }

    public final int d(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        int i4;
        int i5 = this.g;
        if (i + i5 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        int i6 = this.f;
        if (i6 != 0) {
            if (i6 >= i5 + 2) {
                if (i5 + i2 > bArr2.length) {
                    throw new OutputLengthException("output buffer too short");
                }
                this.e.processBlock(this.b, 0, this.c, 0);
                int i7 = 0;
                while (true) {
                    i3 = this.g;
                    if (i7 >= i3) {
                        break;
                    }
                    bArr2[i2 + i7] = e(bArr[i + i7], i7);
                    i7++;
                }
                System.arraycopy(bArr2, i2, this.b, 0, i3);
            }
            return this.g;
        } else if ((i5 * 2) + i2 + 2 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            this.e.processBlock(this.b, 0, this.c, 0);
            int i8 = 0;
            while (true) {
                i4 = this.g;
                if (i8 >= i4) {
                    break;
                }
                bArr2[i2 + i8] = e(this.f14779a[i8], i8);
                i8++;
            }
            System.arraycopy(bArr2, i2, this.b, 0, i4);
            this.e.processBlock(this.b, 0, this.c, 0);
            int i9 = this.g;
            bArr2[i2 + i9] = e(this.f14779a[i9 - 2], 0);
            int i10 = this.g;
            bArr2[i2 + i10 + 1] = e(this.f14779a[i10 - 1], 1);
            System.arraycopy(bArr2, i2 + 2, this.b, 0, this.g);
            this.e.processBlock(this.b, 0, this.c, 0);
            int i11 = 0;
            while (true) {
                int i12 = this.g;
                if (i11 >= i12) {
                    System.arraycopy(bArr2, i2 + i12 + 2, this.b, 0, i12);
                    int i13 = this.f;
                    int i14 = this.g;
                    this.f = i13 + (i14 * 2) + 2;
                    return (i14 * 2) + 2;
                }
                bArr2[i12 + i2 + 2 + i11] = e(bArr[i + i11], i11);
                i11++;
            }
        }
    }

    public final byte e(byte b, int i) {
        return (byte) (b ^ this.c[i]);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        StringBuilder sb;
        String str;
        if (this.i) {
            sb = new StringBuilder();
            sb.append(this.e.getAlgorithmName());
            str = "/PGPCFBwithIV";
        } else {
            sb = new StringBuilder();
            sb.append(this.e.getAlgorithmName());
            str = "/PGPCFB";
        }
        sb.append(str);
        return sb.toString();
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.e.getBlockSize();
    }

    public BlockCipher getUnderlyingCipher() {
        return this.e;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        BlockCipher blockCipher;
        this.h = z;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            int length = iv.length;
            byte[] bArr = this.f14779a;
            if (length < bArr.length) {
                System.arraycopy(iv, 0, bArr, bArr.length - iv.length, iv.length);
                int i = 0;
                while (true) {
                    byte[] bArr2 = this.f14779a;
                    if (i >= bArr2.length - iv.length) {
                        break;
                    }
                    bArr2[i] = 0;
                    i++;
                }
            } else {
                System.arraycopy(iv, 0, bArr, 0, bArr.length);
            }
            reset();
            blockCipher = this.e;
            cipherParameters = parametersWithIV.getParameters();
        } else {
            reset();
            blockCipher = this.e;
        }
        blockCipher.init(true, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        return this.i ? this.h ? d(bArr, i, bArr2, i2) : b(bArr, i, bArr2, i2) : this.h ? c(bArr, i, bArr2, i2) : a(bArr, i, bArr2, i2);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.f = 0;
        int i = 0;
        while (true) {
            byte[] bArr = this.b;
            if (i == bArr.length) {
                this.e.reset();
                return;
            }
            if (this.i) {
                bArr[i] = 0;
            } else {
                bArr[i] = this.f14779a[i];
            }
            i++;
        }
    }
}
