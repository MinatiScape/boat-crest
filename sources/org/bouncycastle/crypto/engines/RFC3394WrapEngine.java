package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.Wrapper;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.Arrays;
/* loaded from: classes12.dex */
public class RFC3394WrapEngine implements Wrapper {

    /* renamed from: a  reason: collision with root package name */
    public BlockCipher f14698a;
    public boolean b;
    public KeyParameter c;
    public boolean d;
    public byte[] e;

    public RFC3394WrapEngine(BlockCipher blockCipher) {
        this(blockCipher, false);
    }

    public RFC3394WrapEngine(BlockCipher blockCipher, boolean z) {
        this.e = new byte[]{-90, -90, -90, -90, -90, -90, -90, -90};
        this.f14698a = blockCipher;
        this.b = !z;
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public String getAlgorithmName() {
        return this.f14698a.getAlgorithmName();
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public void init(boolean z, CipherParameters cipherParameters) {
        this.d = z;
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof KeyParameter) {
            this.c = (KeyParameter) cipherParameters;
        } else if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            this.e = parametersWithIV.getIV();
            this.c = (KeyParameter) parametersWithIV.getParameters();
            if (this.e.length != 8) {
                throw new IllegalArgumentException("IV not equal to 8");
            }
        }
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] unwrap(byte[] bArr, int i, int i2) throws InvalidCipherTextException {
        if (this.d) {
            throw new IllegalStateException("not set for unwrapping");
        }
        int i3 = i2 / 8;
        if (i3 * 8 == i2) {
            byte[] bArr2 = this.e;
            byte[] bArr3 = new byte[i2 - bArr2.length];
            byte[] bArr4 = new byte[bArr2.length];
            byte[] bArr5 = new byte[bArr2.length + 8];
            System.arraycopy(bArr, i, bArr4, 0, bArr2.length);
            byte[] bArr6 = this.e;
            System.arraycopy(bArr, i + bArr6.length, bArr3, 0, i2 - bArr6.length);
            this.f14698a.init(!this.b, this.c);
            int i4 = i3 - 1;
            for (int i5 = 5; i5 >= 0; i5--) {
                for (int i6 = i4; i6 >= 1; i6--) {
                    System.arraycopy(bArr4, 0, bArr5, 0, this.e.length);
                    int i7 = (i6 - 1) * 8;
                    System.arraycopy(bArr3, i7, bArr5, this.e.length, 8);
                    int i8 = (i4 * i5) + i6;
                    int i9 = 1;
                    while (i8 != 0) {
                        int length = this.e.length - i9;
                        bArr5[length] = (byte) (((byte) i8) ^ bArr5[length]);
                        i8 >>>= 8;
                        i9++;
                    }
                    this.f14698a.processBlock(bArr5, 0, bArr5, 0);
                    System.arraycopy(bArr5, 0, bArr4, 0, 8);
                    System.arraycopy(bArr5, 8, bArr3, i7, 8);
                }
            }
            if (Arrays.constantTimeAreEqual(bArr4, this.e)) {
                return bArr3;
            }
            throw new InvalidCipherTextException("checksum failed");
        }
        throw new InvalidCipherTextException("unwrap data must be a multiple of 8 bytes");
    }

    @Override // org.bouncycastle.crypto.Wrapper
    public byte[] wrap(byte[] bArr, int i, int i2) {
        if (this.d) {
            int i3 = i2 / 8;
            if (i3 * 8 == i2) {
                byte[] bArr2 = this.e;
                byte[] bArr3 = new byte[bArr2.length + i2];
                byte[] bArr4 = new byte[bArr2.length + 8];
                System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
                System.arraycopy(bArr, i, bArr3, this.e.length, i2);
                this.f14698a.init(this.b, this.c);
                for (int i4 = 0; i4 != 6; i4++) {
                    for (int i5 = 1; i5 <= i3; i5++) {
                        System.arraycopy(bArr3, 0, bArr4, 0, this.e.length);
                        int i6 = i5 * 8;
                        System.arraycopy(bArr3, i6, bArr4, this.e.length, 8);
                        this.f14698a.processBlock(bArr4, 0, bArr4, 0);
                        int i7 = (i3 * i4) + i5;
                        int i8 = 1;
                        while (i7 != 0) {
                            int length = this.e.length - i8;
                            bArr4[length] = (byte) (((byte) i7) ^ bArr4[length]);
                            i7 >>>= 8;
                            i8++;
                        }
                        System.arraycopy(bArr4, 0, bArr3, 0, 8);
                        System.arraycopy(bArr4, 8, bArr3, i6, 8);
                    }
                }
                return bArr3;
            }
            throw new DataLengthException("wrap data must be a multiple of 8 bytes");
        }
        throw new IllegalStateException("not set for wrapping");
    }
}
