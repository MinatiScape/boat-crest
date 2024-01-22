package org.bouncycastle.crypto.generators;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
/* loaded from: classes12.dex */
public class RSABlindingFactorGenerator {
    public static BigInteger c = BigInteger.valueOf(0);
    public static BigInteger d = BigInteger.valueOf(1);

    /* renamed from: a  reason: collision with root package name */
    public RSAKeyParameters f14744a;
    public SecureRandom b;

    public BigInteger generateBlindingFactor() {
        RSAKeyParameters rSAKeyParameters = this.f14744a;
        if (rSAKeyParameters == null) {
            throw new IllegalStateException("generator not initialised");
        }
        BigInteger modulus = rSAKeyParameters.getModulus();
        int bitLength = modulus.bitLength() - 1;
        while (true) {
            BigInteger bigInteger = new BigInteger(bitLength, this.b);
            BigInteger gcd = bigInteger.gcd(modulus);
            if (!bigInteger.equals(c) && !bigInteger.equals(d) && gcd.equals(d)) {
                return bigInteger;
            }
        }
    }

    public void init(CipherParameters cipherParameters) {
        SecureRandom secureRandom;
        if (cipherParameters instanceof ParametersWithRandom) {
            ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
            this.f14744a = (RSAKeyParameters) parametersWithRandom.getParameters();
            secureRandom = parametersWithRandom.getRandom();
        } else {
            this.f14744a = (RSAKeyParameters) cipherParameters;
            secureRandom = new SecureRandom();
        }
        this.b = secureRandom;
        if (this.f14744a instanceof RSAPrivateCrtKeyParameters) {
            throw new IllegalArgumentException("generator requires RSA public key");
        }
    }
}
