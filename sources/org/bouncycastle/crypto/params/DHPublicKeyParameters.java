package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import java.util.Objects;
/* loaded from: classes13.dex */
public class DHPublicKeyParameters extends DHKeyParameters {
    public static final BigInteger k = BigInteger.valueOf(1);
    public static final BigInteger l = BigInteger.valueOf(2);
    public BigInteger j;

    public DHPublicKeyParameters(BigInteger bigInteger, DHParameters dHParameters) {
        super(false, dHParameters);
        this.j = a(bigInteger, dHParameters);
    }

    public final BigInteger a(BigInteger bigInteger, DHParameters dHParameters) {
        Objects.requireNonNull(bigInteger, "y value cannot be null");
        BigInteger bigInteger2 = l;
        if (bigInteger.compareTo(bigInteger2) < 0 || bigInteger.compareTo(dHParameters.getP().subtract(bigInteger2)) > 0) {
            throw new IllegalArgumentException("invalid DH public key");
        }
        if (dHParameters.getQ() == null || k.equals(bigInteger.modPow(dHParameters.getQ(), dHParameters.getP()))) {
            return bigInteger;
        }
        throw new IllegalArgumentException("Y value does not appear to be in correct group");
    }

    @Override // org.bouncycastle.crypto.params.DHKeyParameters
    public boolean equals(Object obj) {
        return (obj instanceof DHPublicKeyParameters) && ((DHPublicKeyParameters) obj).getY().equals(this.j) && super.equals(obj);
    }

    public BigInteger getY() {
        return this.j;
    }

    @Override // org.bouncycastle.crypto.params.DHKeyParameters
    public int hashCode() {
        return this.j.hashCode() ^ super.hashCode();
    }
}
