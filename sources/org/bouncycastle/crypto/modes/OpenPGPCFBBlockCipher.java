package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
/* loaded from: classes13.dex */
public class OpenPGPCFBBlockCipher implements BlockCipher {

    /* renamed from: a  reason: collision with root package name */
    public byte[] f14778a;
    public byte[] b;
    public byte[] c;
    public BlockCipher d;
    public int e;
    public int f;
    public boolean g;

    public OpenPGPCFBBlockCipher(BlockCipher blockCipher) {
        this.d = blockCipher;
        int blockSize = blockCipher.getBlockSize();
        this.f = blockSize;
        this.f14778a = new byte[blockSize];
        this.b = new byte[blockSize];
        this.c = new byte[blockSize];
    }

    public final int a(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        int i4;
        int i5;
        int i6 = this.f;
        if (i + i6 <= bArr.length) {
            if (i2 + i6 <= bArr2.length) {
                int i7 = this.e;
                int i8 = 2;
                int i9 = 0;
                if (i7 > i6) {
                    byte b = bArr[i];
                    this.b[i6 - 2] = b;
                    bArr2[i2] = c(b, i6 - 2);
                    byte b2 = bArr[i + 1];
                    byte[] bArr3 = this.b;
                    int i10 = this.f;
                    bArr3[i10 - 1] = b2;
                    bArr2[i2 + 1] = c(b2, i10 - 1);
                    this.d.processBlock(this.b, 0, this.c, 0);
                    while (i8 < this.f) {
                        byte b3 = bArr[i + i8];
                        int i11 = i8 - 2;
                        this.b[i11] = b3;
                        bArr2[i2 + i8] = c(b3, i11);
                        i8++;
                    }
                } else {
                    if (i7 == 0) {
                        this.d.processBlock(this.b, 0, this.c, 0);
                        while (true) {
                            i5 = this.f;
                            if (i9 >= i5) {
                                break;
                            }
                            int i12 = i + i9;
                            this.b[i9] = bArr[i12];
                            bArr2[i9] = c(bArr[i12], i9);
                            i9++;
                        }
                        i4 = this.e + i5;
                    } else if (i7 == i6) {
                        this.d.processBlock(this.b, 0, this.c, 0);
                        byte b4 = bArr[i];
                        byte b5 = bArr[i + 1];
                        bArr2[i2] = c(b4, 0);
                        bArr2[i2 + 1] = c(b5, 1);
                        byte[] bArr4 = this.b;
                        System.arraycopy(bArr4, 2, bArr4, 0, this.f - 2);
                        byte[] bArr5 = this.b;
                        int i13 = this.f;
                        bArr5[i13 - 2] = b4;
                        bArr5[i13 - 1] = b5;
                        this.d.processBlock(bArr5, 0, this.c, 0);
                        while (true) {
                            i3 = this.f;
                            if (i8 >= i3) {
                                break;
                            }
                            byte b6 = bArr[i + i8];
                            int i14 = i8 - 2;
                            this.b[i14] = b6;
                            bArr2[i2 + i8] = c(b6, i14);
                            i8++;
                        }
                        i4 = this.e + i3;
                    }
                    this.e = i4;
                }
                return this.f;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    public final int b(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        int i3;
        int i4 = this.f;
        if (i + i4 <= bArr.length) {
            if (i2 + i4 <= bArr2.length) {
                int i5 = this.e;
                int i6 = 2;
                int i7 = 0;
                if (i5 > i4) {
                    byte[] bArr3 = this.b;
                    int i8 = i4 - 2;
                    byte c = c(bArr[i], i4 - 2);
                    bArr2[i2] = c;
                    bArr3[i8] = c;
                    byte[] bArr4 = this.b;
                    int i9 = this.f;
                    int i10 = i9 - 1;
                    byte c2 = c(bArr[i + 1], i9 - 1);
                    bArr2[i2 + 1] = c2;
                    bArr4[i10] = c2;
                    this.d.processBlock(this.b, 0, this.c, 0);
                    while (i6 < this.f) {
                        byte[] bArr5 = this.b;
                        int i11 = i6 - 2;
                        byte c3 = c(bArr[i + i6], i11);
                        bArr2[i2 + i6] = c3;
                        bArr5[i11] = c3;
                        i6++;
                    }
                } else {
                    if (i5 != 0) {
                        if (i5 == i4) {
                            this.d.processBlock(this.b, 0, this.c, 0);
                            bArr2[i2] = c(bArr[i], 0);
                            bArr2[i2 + 1] = c(bArr[i + 1], 1);
                            byte[] bArr6 = this.b;
                            System.arraycopy(bArr6, 2, bArr6, 0, this.f - 2);
                            System.arraycopy(bArr2, i2, this.b, this.f - 2, 2);
                            this.d.processBlock(this.b, 0, this.c, 0);
                            while (true) {
                                i3 = this.f;
                                if (i6 >= i3) {
                                    break;
                                }
                                byte[] bArr7 = this.b;
                                int i12 = i6 - 2;
                                byte c4 = c(bArr[i + i6], i12);
                                bArr2[i2 + i6] = c4;
                                bArr7[i12] = c4;
                                i6++;
                            }
                        }
                    } else {
                        this.d.processBlock(this.b, 0, this.c, 0);
                        while (true) {
                            i3 = this.f;
                            if (i7 >= i3) {
                                break;
                            }
                            byte[] bArr8 = this.b;
                            byte c5 = c(bArr[i + i7], i7);
                            bArr2[i2 + i7] = c5;
                            bArr8[i7] = c5;
                            i7++;
                        }
                    }
                    this.e += i3;
                }
                return this.f;
            }
            throw new OutputLengthException("output buffer too short");
        }
        throw new DataLengthException("input buffer too short");
    }

    public final byte c(byte b, int i) {
        return (byte) (b ^ this.c[i]);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return this.d.getAlgorithmName() + "/OpenPGPCFB";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return this.d.getBlockSize();
    }

    public BlockCipher getUnderlyingCipher() {
        return this.d;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.g = z;
        reset();
        this.d.init(true, cipherParameters);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        return this.g ? b(bArr, i, bArr2, i2) : a(bArr, i, bArr2, i2);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
        this.e = 0;
        byte[] bArr = this.f14778a;
        byte[] bArr2 = this.b;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.d.reset();
    }
}
