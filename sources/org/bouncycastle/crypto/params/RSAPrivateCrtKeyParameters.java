package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class RSAPrivateCrtKeyParameters extends RSAKeyParameters {
    public BigInteger l;
    public BigInteger m;
    public BigInteger n;
    public BigInteger o;
    public BigInteger p;
    public BigInteger q;

    public RSAPrivateCrtKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5, BigInteger bigInteger6, BigInteger bigInteger7, BigInteger bigInteger8) {
        super(true, bigInteger, bigInteger3);
        this.l = bigInteger2;
        this.m = bigInteger4;
        this.n = bigInteger5;
        this.o = bigInteger6;
        this.p = bigInteger7;
        this.q = bigInteger8;
    }

    public BigInteger getDP() {
        return this.o;
    }

    public BigInteger getDQ() {
        return this.p;
    }

    public BigInteger getP() {
        return this.m;
    }

    public BigInteger getPublicExponent() {
        return this.l;
    }

    public BigInteger getQ() {
        return this.n;
    }

    public BigInteger getQInv() {
        return this.q;
    }
}
