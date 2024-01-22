package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class GOST3410PublicKeyParameters extends GOST3410KeyParameters {
    public BigInteger j;

    public GOST3410PublicKeyParameters(BigInteger bigInteger, GOST3410Parameters gOST3410Parameters) {
        super(false, gOST3410Parameters);
        this.j = bigInteger;
    }

    public BigInteger getY() {
        return this.j;
    }
}
