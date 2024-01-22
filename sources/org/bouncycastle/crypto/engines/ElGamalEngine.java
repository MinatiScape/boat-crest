package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.params.ElGamalKeyParameters;
import org.bouncycastle.crypto.params.ElGamalPrivateKeyParameters;
import org.bouncycastle.crypto.params.ElGamalPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes12.dex */
public class ElGamalEngine implements AsymmetricBlockCipher {
    public static final BigInteger e = BigInteger.valueOf(0);
    public static final BigInteger f = BigInteger.valueOf(1);
    public static final BigInteger g = BigInteger.valueOf(2);

    /* renamed from: a  reason: collision with root package name */
    public ElGamalKeyParameters f14677a;
    public SecureRandom b;
    public boolean c;
    public int d;

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.c ? (this.d - 1) / 8 : ((this.d + 7) / 8) * 2;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return this.c ? ((this.d + 7) / 8) * 2 : (this.d - 1) / 8;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f14677a = (ElGamalKeyParameters) parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
        } else {
            this.f14677a = (ElGamalKeyParameters) cipherParameters;
            secureRandom = new SecureRandom();
        }
        this.b = secureRandom;
        this.c = z;
        this.d = this.f14677a.getParameters().getP().bitLength();
        if (z) {
            if (!(this.f14677a instanceof ElGamalPublicKeyParameters)) {
                throw new IllegalArgumentException("ElGamalPublicKeyParameters are required for encryption.");
            }
        } else if (!(this.f14677a instanceof ElGamalPrivateKeyParameters)) {
            throw new IllegalArgumentException("ElGamalPrivateKeyParameters are required for decryption.");
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i, int i2) {
        if (this.f14677a != null) {
            if (i2 <= (this.c ? ((this.d - 1) + 7) / 8 : getInputBlockSize())) {
                BigInteger p = this.f14677a.getParameters().getP();
                if (this.f14677a instanceof ElGamalPrivateKeyParameters) {
                    int i3 = i2 / 2;
                    byte[] bArr2 = new byte[i3];
                    byte[] bArr3 = new byte[i3];
                    System.arraycopy(bArr, i, bArr2, 0, i3);
                    System.arraycopy(bArr, i + i3, bArr3, 0, i3);
                    return BigIntegers.asUnsignedByteArray(new BigInteger(1, bArr2).modPow(p.subtract(f).subtract(((ElGamalPrivateKeyParameters) this.f14677a).getX()), p).multiply(new BigInteger(1, bArr3)).mod(p));
                }
                if (i != 0 || i2 != bArr.length) {
                    byte[] bArr4 = new byte[i2];
                    System.arraycopy(bArr, i, bArr4, 0, i2);
                    bArr = bArr4;
                }
                BigInteger bigInteger = new BigInteger(1, bArr);
                if (bigInteger.compareTo(p) < 0) {
                    ElGamalPublicKeyParameters elGamalPublicKeyParameters = (ElGamalPublicKeyParameters) this.f14677a;
                    int bitLength = p.bitLength();
                    BigInteger bigInteger2 = new BigInteger(bitLength, this.b);
                    while (true) {
                        if (!bigInteger2.equals(e) && bigInteger2.compareTo(p.subtract(g)) <= 0) {
                            break;
                        }
                        bigInteger2 = new BigInteger(bitLength, this.b);
                    }
                    BigInteger modPow = this.f14677a.getParameters().getG().modPow(bigInteger2, p);
                    BigInteger mod = bigInteger.multiply(elGamalPublicKeyParameters.getY().modPow(bigInteger2, p)).mod(p);
                    byte[] byteArray = modPow.toByteArray();
                    byte[] byteArray2 = mod.toByteArray();
                    int outputBlockSize = getOutputBlockSize();
                    byte[] bArr5 = new byte[outputBlockSize];
                    int i4 = outputBlockSize / 2;
                    if (byteArray.length > i4) {
                        System.arraycopy(byteArray, 1, bArr5, i4 - (byteArray.length - 1), byteArray.length - 1);
                    } else {
                        System.arraycopy(byteArray, 0, bArr5, i4 - byteArray.length, byteArray.length);
                    }
                    if (byteArray2.length > i4) {
                        System.arraycopy(byteArray2, 1, bArr5, outputBlockSize - (byteArray2.length - 1), byteArray2.length - 1);
                    } else {
                        System.arraycopy(byteArray2, 0, bArr5, outputBlockSize - byteArray2.length, byteArray2.length);
                    }
                    return bArr5;
                }
                throw new DataLengthException("input too large for ElGamal cipher.\n");
            }
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        }
        throw new IllegalStateException("ElGamal engine not initialised");
    }
}
