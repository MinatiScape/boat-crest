package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.KeySpec;
/* loaded from: classes13.dex */
public class GOST3410PublicKeySpec implements KeySpec {
    public BigInteger h;
    public BigInteger i;
    public BigInteger j;
    public BigInteger k;

    public GOST3410PublicKeySpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.h = bigInteger;
        this.i = bigInteger2;
        this.j = bigInteger3;
        this.k = bigInteger4;
    }

    public BigInteger getA() {
        return this.k;
    }

    public BigInteger getP() {
        return this.i;
    }

    public BigInteger getQ() {
        return this.j;
    }

    public BigInteger getY() {
        return this.h;
    }
}
