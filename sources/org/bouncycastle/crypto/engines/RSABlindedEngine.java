package org.bouncycastle.crypto.engines;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.util.BigIntegers;
/* loaded from: classes12.dex */
public class RSABlindedEngine implements AsymmetricBlockCipher {
    public static final BigInteger d = BigInteger.valueOf(1);

    /* renamed from: a  reason: collision with root package name */
    public a f14700a = new a();
    public RSAKeyParameters b;
    public SecureRandom c;

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getInputBlockSize() {
        return this.f14700a.c();
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public int getOutputBlockSize() {
        return this.f14700a.d();
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        this.f14700a.e(z, cipherParameters);
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.b = (RSAKeyParameters) parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
        } else {
            this.b = (RSAKeyParameters) cipherParameters;
            secureRandom = new SecureRandom();
        }
        this.c = secureRandom;
    }

    @Override // org.bouncycastle.crypto.AsymmetricBlockCipher
    public byte[] processBlock(byte[] bArr, int i, int i2) {
        BigInteger f;
        RSAPrivateCrtKeyParameters rSAPrivateCrtKeyParameters;
        BigInteger publicExponent;
        if (this.b != null) {
            BigInteger a2 = this.f14700a.a(bArr, i, i2);
            RSAKeyParameters rSAKeyParameters = this.b;
            if (!(rSAKeyParameters instanceof RSAPrivateCrtKeyParameters) || (publicExponent = (rSAPrivateCrtKeyParameters = (RSAPrivateCrtKeyParameters) rSAKeyParameters).getPublicExponent()) == null) {
                f = this.f14700a.f(a2);
            } else {
                BigInteger modulus = rSAPrivateCrtKeyParameters.getModulus();
                BigInteger bigInteger = d;
                BigInteger createRandomInRange = BigIntegers.createRandomInRange(bigInteger, modulus.subtract(bigInteger), this.c);
                f = this.f14700a.f(createRandomInRange.modPow(publicExponent, modulus).multiply(a2).mod(modulus)).multiply(createRandomInRange.modInverse(modulus)).mod(modulus);
                if (!a2.equals(f.modPow(publicExponent, modulus))) {
                    throw new IllegalStateException("RSA engine faulty decryption/signing detected");
                }
            }
            return this.f14700a.b(f);
        }
        throw new IllegalStateException("RSA engine not initialised");
    }
}
