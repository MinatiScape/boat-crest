package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class DSAPublicKeyParameters extends DSAKeyParameters {
    public static final BigInteger k = BigInteger.valueOf(1);
    public static final BigInteger l = BigInteger.valueOf(2);
    public BigInteger j;

    public DSAPublicKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(false, dSAParameters);
        this.j = a(bigInteger, dSAParameters);
    }

    public final BigInteger a(BigInteger bigInteger, DSAParameters dSAParameters) {
        if (dSAParameters != null) {
            BigInteger bigInteger2 = l;
            if (bigInteger2.compareTo(bigInteger) > 0 || dSAParameters.getP().subtract(bigInteger2).compareTo(bigInteger) < 0 || !k.equals(bigInteger.modPow(dSAParameters.getQ(), dSAParameters.getP()))) {
                throw new IllegalArgumentException("y value does not appear to be in correct group");
            }
            return bigInteger;
        }
        return bigInteger;
    }

    public BigInteger getY() {
        return this.j;
    }
}
