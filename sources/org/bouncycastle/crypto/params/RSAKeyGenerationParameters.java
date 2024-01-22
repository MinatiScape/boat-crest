package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.KeyGenerationParameters;
/* loaded from: classes13.dex */
public class RSAKeyGenerationParameters extends KeyGenerationParameters {
    public BigInteger c;
    public int d;

    public RSAKeyGenerationParameters(BigInteger bigInteger, SecureRandom secureRandom, int i, int i2) {
        super(secureRandom, i);
        if (i < 12) {
            throw new IllegalArgumentException("key strength too small");
        }
        if (!bigInteger.testBit(0)) {
            throw new IllegalArgumentException("public exponent cannot be even");
        }
        this.c = bigInteger;
        this.d = i2;
    }

    public int getCertainty() {
        return this.d;
    }

    public BigInteger getPublicExponent() {
        return this.c;
    }
}
