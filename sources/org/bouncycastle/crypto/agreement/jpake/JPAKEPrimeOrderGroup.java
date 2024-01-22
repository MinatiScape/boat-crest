package org.bouncycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import org.jose4j.jwk.RsaJsonWebKey;
/* loaded from: classes6.dex */
public class JPAKEPrimeOrderGroup {

    /* renamed from: a  reason: collision with root package name */
    public final BigInteger f14620a;
    public final BigInteger b;
    public final BigInteger c;

    public JPAKEPrimeOrderGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this(bigInteger, bigInteger2, bigInteger3, false);
    }

    public JPAKEPrimeOrderGroup(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, boolean z) {
        JPAKEUtil.validateNotNull(bigInteger, RsaJsonWebKey.FIRST_PRIME_FACTOR_MEMBER_NAME);
        JPAKEUtil.validateNotNull(bigInteger2, RsaJsonWebKey.SECOND_PRIME_FACTOR_MEMBER_NAME);
        JPAKEUtil.validateNotNull(bigInteger3, "g");
        if (!z) {
            BigInteger bigInteger4 = JPAKEUtil.b;
            if (!bigInteger.subtract(bigInteger4).mod(bigInteger2).equals(JPAKEUtil.f14624a)) {
                throw new IllegalArgumentException("p-1 must be evenly divisible by q");
            }
            if (bigInteger3.compareTo(BigInteger.valueOf(2L)) == -1 || bigInteger3.compareTo(bigInteger.subtract(bigInteger4)) == 1) {
                throw new IllegalArgumentException("g must be in [2, p-1]");
            }
            if (!bigInteger3.modPow(bigInteger2, bigInteger).equals(bigInteger4)) {
                throw new IllegalArgumentException("g^q mod p must equal 1");
            }
            if (!bigInteger.isProbablePrime(20)) {
                throw new IllegalArgumentException("p must be prime");
            }
            if (!bigInteger2.isProbablePrime(20)) {
                throw new IllegalArgumentException("q must be prime");
            }
        }
        this.f14620a = bigInteger;
        this.b = bigInteger2;
        this.c = bigInteger3;
    }

    public BigInteger getG() {
        return this.c;
    }

    public BigInteger getP() {
        return this.f14620a;
    }

    public BigInteger getQ() {
        return this.b;
    }
}
