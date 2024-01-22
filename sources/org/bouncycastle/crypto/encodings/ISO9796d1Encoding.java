package org.bouncycastle.crypto.encodings;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
/* loaded from: classes12.dex */
public class ISO9796d1Encoding implements AsymmetricBlockCipher {
    public static final BigInteger f = BigInteger.valueOf(16);
    public static final BigInteger g = BigInteger.valueOf(6);
    public static byte[] h = {14, 3, 5, 8, 9, 4, 2, 15, 0, 13, 11, 6, 7, 10, 12, 1};
    public static byte[] i = {8, 15, 6, 1, 5, 2, 11, 12, 3, 4, 13, 10, 14, 9, 0, 7};

    /* renamed from: a  reason: collision with root package name */
    public AsymmetricBlockCipher f14660a;
    public boolean b;
    public int c;
    public int d = 0;
    public BigInteger e;

    public ISO9796d1Encoding(AsymmetricBlockCipher asymmetricBlockCipher) {
        this.f14660a = asymmetricBlockCipher;
    }

    public static byte[] a(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            return bArr;
        }
        return byteArray;
    }

    public final byte[] b(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        byte[] processBlock = this.f14660a.processBlock(bArr, i2, i3);
        int i4 = (this.c + 13) / 16;
        BigInteger bigInteger = new BigInteger(1, processBlock);
        BigInteger bigInteger2 = f;
        BigInteger mod = bigInteger.mod(bigInteger2);
        BigInteger bigInteger3 = g;
        if (!mod.equals(bigInteger3)) {
            if (!this.e.subtract(bigInteger).mod(bigInteger2).equals(bigInteger3)) {
                throw new InvalidCipherTextException("resulting integer iS or (modulus - iS) is not congruent to 6 mod 16");
            }
            bigInteger = this.e.subtract(bigInteger);
        }
        byte[] a2 = a(bigInteger);
        if ((a2[a2.length - 1] & 15) == 6) {
            a2[a2.length - 1] = (byte) (((a2[a2.length - 1] & 255) >>> 4) | (i[(a2[a2.length - 2] & 255) >> 4] << 4));
            byte[] bArr2 = h;
            a2[0] = (byte) (bArr2[a2[1] & 15] | (bArr2[(a2[1] & 255) >>> 4] << 4));
            int i5 = 1;
            int i6 = 0;
            boolean z = false;
            for (int length = a2.length - 1; length >= a2.length - (i4 * 2); length -= 2) {
                byte[] bArr3 = h;
                int i7 = bArr3[a2[length] & 15] | (bArr3[(a2[length] & 255) >>> 4] << 4);
                int i8 = length - 1;
                if (((a2[i8] ^ i7) & 255) != 0) {
                    if (z) {
                        throw new InvalidCipherTextException("invalid tsums in block");
                    }
                    i5 = (a2[i8] ^ i7) & 255;
                    z = true;
                    i6 = i8;
                }
            }
            a2[i6] = 0;
            int length2 = (a2.length - i6) / 2;
            byte[] bArr4 = new byte[length2];
            for (int i9 = 0; i9 < length2; i9++) {
                bArr4[i9] = a2[(i9 * 2) + i6 + 1];
            }
            this.d = i5 - 1;
            return bArr4;
        }
        throw new InvalidCipherTextException("invalid forcing byte in block");
    }

    public final byte[] c(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        int i4 = this.c;
        int i5 = (i4 + 7) / 8;
        byte[] bArr2 = new byte[i5];
        int i6 = 1;
        int i7 = this.d + 1;
        int i8 = (i4 + 13) / 16;
        int i9 = 0;
        while (i9 < i8) {
            if (i9 > i8 - i3) {
                int i10 = i8 - i9;
                System.arraycopy(bArr, (i2 + i3) - i10, bArr2, i5 - i8, i10);
            } else {
                System.arraycopy(bArr, i2, bArr2, i5 - (i9 + i3), i3);
            }
            i9 += i3;
        }
        for (int i11 = i5 - (i8 * 2); i11 != i5; i11 += 2) {
            byte b = bArr2[(i5 - i8) + (i11 / 2)];
            byte[] bArr3 = h;
            bArr2[i11] = (byte) (bArr3[b & 15] | (bArr3[(b & 255) >>> 4] << 4));
            bArr2[i11 + 1] = b;
        }
        int i12 = i5 - (i3 * 2);
        bArr2[i12] = (byte) (bArr2[i12] ^ i7);
        int i13 = i5 - 1;
        bArr2[i13] = (byte) ((bArr2[i13] << 4) | 6);
        int i14 = 8 - ((this.c - 1) % 8);
        if (i14 != 8) {
            bArr2[0] = (byte) (bArr2[0] & (255 >>> i14));
            bArr2[0] = (byte) ((128 >>> i14) | bArr2[0]);
            i6 = 0;
        } else {
            bArr2[0] = 0;
            bArr2[1] = (byte) (bArr2[1] | 128);
        }
        return this.f14660a.processBlock(bArr2, i6, i5 - i6);
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        int inputBlockSize = this.f14660a.getInputBlockSize();
        return this.b ? (inputBlockSize + 1) / 2 : inputBlockSize;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        int outputBlockSize = this.f14660a.getOutputBlockSize();
        return this.b ? outputBlockSize : (outputBlockSize + 1) / 2;
    }

    public int getPadBits() {
        return this.d;
    }

    public AsymmetricBlockCipher getUnderlyingCipher() {
        return this.f14660a;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        RSAKeyParameters rSAKeyParameters = cipherParameters instanceof ParametersWithRandom ? (RSAKeyParameters) ((ParametersWithRandom) cipherParameters).getParameters() : (RSAKeyParameters) cipherParameters;
        this.f14660a.init(z, cipherParameters);
        BigInteger modulus = rSAKeyParameters.getModulus();
        this.e = modulus;
        this.c = modulus.bitLength();
        this.b = z;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i2, int i3) throws InvalidCipherTextException {
        return this.b ? c(bArr, i2, i3) : b(bArr, i2, i3);
    }

    public void setPadBits(int i2) {
        if (i2 > 7) {
            throw new IllegalArgumentException("padBits > 7");
        }
        this.d = i2;
    }
}
