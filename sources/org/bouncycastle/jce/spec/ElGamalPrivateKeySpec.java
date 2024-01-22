package org.bouncycastle.jce.spec;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class ElGamalPrivateKeySpec extends ElGamalKeySpec {
    public BigInteger i;

    public ElGamalPrivateKeySpec(BigInteger bigInteger, ElGamalParameterSpec elGamalParameterSpec) {
        super(elGamalParameterSpec);
        this.i = bigInteger;
    }

    public BigInteger getX() {
        return this.i;
    }
}
