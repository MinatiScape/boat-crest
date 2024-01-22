package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class DSAPrivateKeyParameters extends DSAKeyParameters {
    public BigInteger j;

    public DSAPrivateKeyParameters(BigInteger bigInteger, DSAParameters dSAParameters) {
        super(true, dSAParameters);
        this.j = bigInteger;
    }

    public BigInteger getX() {
        return this.j;
    }
}
