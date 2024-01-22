package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.Pack;
/* loaded from: classes12.dex */
public class RFC5649WrapEngine implements Wrapper {

    /* renamed from: a  reason: collision with root package name */
    public BlockCipher f14699a;
    public KeyParameter b;
    public boolean c;
    public byte[] d;
    public byte[] e;
    public byte[] f = null;

    public RFC5649WrapEngine(BlockCipher blockCipher) {
        byte[] bArr = {-90, 89, 89, -90};
        this.d = bArr;
        this.e = bArr;
        this.f14699a = blockCipher;
    }

    public final byte[] a(byte[] bArr) {
        int length = bArr.length;
        int i = (8 - (length % 8)) % 8;
        byte[] bArr2 = new byte[length + i];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        if (i != 0) {
            System.arraycopy(new byte[i], 0, bArr2, length, i);
        }
        return bArr2;
    }

    public final byte[] b(byte[] bArr, int i, int i2) {
        int i3 = i2 - 8;
        byte[] bArr2 = new byte[i3];
        byte[] bArr3 = new byte[8];
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr, i, bArr3, 0, 8);
        System.arraycopy(bArr, i + 8, bArr2, 0, i3);
        this.f14699a.init(false, this.b);
        int i4 = (i2 / 8) - 1;
        for (int i5 = 5; i5 >= 0; i5--) {
            for (int i6 = i4; i6 >= 1; i6--) {
                System.arraycopy(bArr3, 0, bArr4, 0, 8);
                int i7 = (i6 - 1) * 8;
                System.arraycopy(bArr2, i7, bArr4, 8, 8);
                int i8 = (i4 * i5) + i6;
                int i9 = 1;
                while (i8 != 0) {
                    int i10 = 8 - i9;
                    bArr4[i10] = (byte) (((byte) i8) ^ bArr4[i10]);
                    i8 >>>= 8;
                    i9++;
                }
                this.f14699a.processBlock(bArr4, 0, bArr4, 0);
                System.arraycopy(bArr4, 0, bArr3, 0, 8);
                System.arraycopy(bArr4, 8, bArr2, i7, 8);
            }
        }
        this.f = bArr3;
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return this.f14699a.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        this.c = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof KeyParameter) {
            this.b = (KeyParameter) cipherParameters;
            this.e = this.d;
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.e = parametersWithIV.getIV();
            this.b = (KeyParameter) parametersWithIV.getParameters();
            if (this.e.length != 4) {
                throw new IllegalArgumentException("IV length not equal to 4");
            }
        }
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        byte[] b;
        if (this.c) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int i3 = i2 / 8;
        if (i3 * 8 == i2) {
            if (i3 != 1) {
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, i, bArr2, 0, i2);
                byte[] bArr3 = new byte[i2];
                if (i3 == 2) {
                    this.f14699a.init(false, this.b);
                    int i4 = 0;
                    while (i4 < i2) {
                        this.f14699a.processBlock(bArr2, i4, bArr3, i4);
                        i4 += this.f14699a.getBlockSize();
                    }
                    byte[] bArr4 = new byte[8];
                    this.f = bArr4;
                    System.arraycopy(bArr3, 0, bArr4, 0, bArr4.length);
                    byte[] bArr5 = this.f;
                    int length = i2 - bArr5.length;
                    b = new byte[length];
                    System.arraycopy(bArr3, bArr5.length, b, 0, length);
                } else {
                    b = b(bArr, i, i2);
                }
                byte[] bArr6 = new byte[4];
                byte[] bArr7 = new byte[4];
                System.arraycopy(this.f, 0, bArr6, 0, 4);
                System.arraycopy(this.f, 4, bArr7, 0, 4);
                int bigEndianToInt = Pack.bigEndianToInt(bArr7, 0);
                boolean constantTimeAreEqual = Arrays.constantTimeAreEqual(bArr6, this.e);
                int length2 = b.length;
                if (bigEndianToInt <= length2 - 8) {
                    constantTimeAreEqual = false;
                }
                if (bigEndianToInt > length2) {
                    constantTimeAreEqual = false;
                }
                int i5 = length2 - bigEndianToInt;
                if (i5 >= b.length) {
                    i5 = b.length;
                    constantTimeAreEqual = false;
                }
                byte[] bArr8 = new byte[i5];
                System.arraycopy(b, b.length - i5, bArr8, 0, i5);
                if (!Arrays.constantTimeAreEqual(bArr8, new byte[i5])) {
                    constantTimeAreEqual = false;
                }
                if (constantTimeAreEqual) {
                    byte[] bArr9 = new byte[bigEndianToInt];
                    System.arraycopy(b, 0, bArr9, 0, bigEndianToInt);
                    return bArr9;
                }
                throw new InvalidCipherTextException("checksum failed");
            }
            throw new InvalidCipherTextException("unwrap data must be at least 16 bytes");
        }
        throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i, int i2) {
        if (this.c) {
            byte[] bArr2 = new byte[8];
            byte[] intToBigEndian = Pack.intToBigEndian(i2);
            byte[] bArr3 = this.e;
            int i3 = 0;
            System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
            System.arraycopy(intToBigEndian, 0, bArr2, this.e.length, intToBigEndian.length);
            byte[] bArr4 = new byte[i2];
            System.arraycopy(bArr, i, bArr4, 0, i2);
            byte[] a2 = a(bArr4);
            if (a2.length != 8) {
                RFC3394WrapEngine rFC3394WrapEngine = new RFC3394WrapEngine(this.f14699a);
                rFC3394WrapEngine.init(true, new ParametersWithIV(this.b, bArr2));
                return rFC3394WrapEngine.wrap(a2, 0, a2.length);
            }
            int length = a2.length + 8;
            byte[] bArr5 = new byte[length];
            System.arraycopy(bArr2, 0, bArr5, 0, 8);
            System.arraycopy(a2, 0, bArr5, 8, a2.length);
            this.f14699a.init(true, this.b);
            while (i3 < length) {
                this.f14699a.processBlock(bArr5, i3, bArr5, i3);
                i3 += this.f14699a.getBlockSize();
            }
            return bArr5;
        }
        throw new IllegalStateException("not set for wrapping");
    }
}
