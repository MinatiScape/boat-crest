package org.bouncycastle.jce.spec;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class ElGamalPublicKeySpec extends ElGamalKeySpec {
    public BigInteger i;

    public ElGamalPublicKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.i = bigInteger;
    }

    public BigInteger getY() {
        return this.i;
    }
}
