package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class ECPrivateKeyParameters extends ECKeyParameters {
    public BigInteger j;

    public ECPrivateKeyParameters(BigInteger bigInteger, ECDomainParameters eCDomainParameters) {
        super(true, eCDomainParameters);
        this.j = bigInteger;
    }

    public BigInteger getD() {
        return this.j;
    }
}
