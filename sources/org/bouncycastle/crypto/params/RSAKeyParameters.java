package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class RSAKeyParameters extends AsymmetricKeyParameter {
    public static final BigInteger k = BigInteger.valueOf(1);
    public BigInteger i;
    public BigInteger j;

    public RSAKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2) {
        super(z);
        if (!z && (bigInteger2.intValue() & 1) == 0) {
            throw new IllegalArgumentException("RSA publicExponent is even");
        }
        this.i = a(bigInteger);
        this.j = bigInteger2;
    }

    public final BigInteger a(BigInteger bigInteger) {
        if ((bigInteger.intValue() & 1) != 0) {
            if (bigInteger.gcd(new BigInteger("1451887755777639901511587432083070202422614380984889313550570919659315177065956574359078912654149167643992684236991305777574330831666511589145701059710742276692757882915756220901998212975756543223550490431013061082131040808010565293748926901442915057819663730454818359472391642885328171302299245556663073719855")).equals(k)) {
                return bigInteger;
            }
            throw new IllegalArgumentException("RSA modulus has a small prime factor");
        }
        throw new IllegalArgumentException("RSA modulus is even");
    }

    public BigInteger getExponent() {
        return this.j;
    }

    public BigInteger getModulus() {
        return this.i;
    }
}
