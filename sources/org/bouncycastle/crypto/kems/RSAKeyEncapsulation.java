package org.bouncycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DerivationFunction;
import org.bouncycastle.crypto.KeyEncapsulation;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes12.dex */
public class RSAKeyEncapsulation implements KeyEncapsulation {
    public static final BigInteger d = BigInteger.valueOf(0);
    public static final BigInteger e = BigInteger.valueOf(1);

    /* renamed from: a  reason: collision with root package name */
    public DerivationFunction f14749a;
    public SecureRandom b;
    public RSAKeyParameters c;

    public RSAKeyEncapsulation(DerivationFunction derivationFunction, SecureRandom secureRandom) {
        this.f14749a = derivationFunction;
        this.b = secureRandom;
    }

    public CipherParameters decrypt(byte[] bArr, int i) {
        return decrypt(bArr, 0, bArr.length, i);
    }

    @Override // org.bouncycastle.crypto.KeyEncapsulation
    public CipherParameters decrypt(byte[] bArr, int i, int i2, int i3) throws IllegalArgumentException {
        if (this.c.isPrivate()) {
            BigInteger modulus = this.c.getModulus();
            BigInteger exponent = this.c.getExponent();
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            return generateKey(modulus, new BigInteger(1, bArr2).modPow(exponent, modulus), i3);
        }
        throw new IllegalArgumentException("Private key required for decryption");
    }

    public CipherParameters encrypt(byte[] bArr, int i) {
        return encrypt(bArr, 0, i);
    }

    @Override // org.bouncycastle.crypto.KeyEncapsulation
    public CipherParameters encrypt(byte[] bArr, int i, int i2) throws IllegalArgumentException {
        if (this.c.isPrivate()) {
            throw new IllegalArgumentException("Public key required for encryption");
        }
        BigInteger modulus = this.c.getModulus();
        BigInteger exponent = this.c.getExponent();
        BigInteger createRandomInRange = BigIntegers.createRandomInRange(d, modulus.subtract(e), this.b);
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray((modulus.bitLength() + 7) / 8, createRandomInRange.modPow(exponent, modulus));
        System.arraycopy(asUnsignedByteArray, 0, bArr, i, asUnsignedByteArray.length);
        return generateKey(modulus, createRandomInRange, i2);
    }

    public KeyParameter generateKey(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.f14749a.init(new KDFParameters(BigIntegers.asUnsignedByteArray((bigInteger.bitLength() + 7) / 8, bigInteger2), null));
        byte[] bArr = new byte[i];
        this.f14749a.generateBytes(bArr, 0, i);
        return new KeyParameter(bArr);
    }

    @Override // org.bouncycastle.crypto.KeyEncapsulation
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof RSAKeyParameters)) {
            throw new IllegalArgumentException("RSA key required");
        }
        this.c = (RSAKeyParameters) cipherParameters;
    }
}
