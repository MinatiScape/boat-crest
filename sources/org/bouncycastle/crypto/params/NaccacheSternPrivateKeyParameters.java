package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import java.util.Vector;
/* loaded from: classes13.dex */
public class NaccacheSternPrivateKeyParameters extends NaccacheSternKeyParameters {
    public BigInteger l;
    public Vector m;

    public NaccacheSternPrivateKeyParameters(BigInteger bigInteger, BigInteger bigInteger2, int i, Vector vector, BigInteger bigInteger3) {
        super(true, bigInteger, bigInteger2, i);
        this.m = vector;
        this.l = bigInteger3;
    }

    public BigInteger getPhi_n() {
        return this.l;
    }

    public Vector getSmallPrimes() {
        return this.m;
    }
}
