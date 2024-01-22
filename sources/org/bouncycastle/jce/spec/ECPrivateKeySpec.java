package org.bouncycastle.jce.spec;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class ECPrivateKeySpec extends ECKeySpec {
    public BigInteger i;

    public ECPrivateKeySpec(BigInteger bigInteger, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        this.i = bigInteger;
    }

    public BigInteger getD() {
        return this.i;
    }
}
