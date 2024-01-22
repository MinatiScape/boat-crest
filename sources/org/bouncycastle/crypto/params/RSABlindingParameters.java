package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class RSABlindingParameters implements CipherParameters {
    public RSAKeyParameters h;
    public BigInteger i;

    public RSABlindingParameters(RSAKeyParameters rSAKeyParameters, BigInteger bigInteger) {
        if (rSAKeyParameters instanceof RSAPrivateCrtKeyParameters) {
            throw new IllegalArgumentException("RSA parameters should be for a public key");
        }
        this.h = rSAKeyParameters;
        this.i = bigInteger;
    }

    public BigInteger getBlindingFactor() {
        return this.i;
    }

    public RSAKeyParameters getPublicKey() {
        return this.h;
    }
}
