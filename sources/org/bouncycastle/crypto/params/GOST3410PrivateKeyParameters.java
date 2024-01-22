package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class GOST3410PrivateKeyParameters extends GOST3410KeyParameters {
    public BigInteger j;

    public GOST3410PrivateKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(true, gOST3410Parameters);
        this.j = bigInteger;
    }

    public BigInteger getX() {
        return this.j;
    }
}
