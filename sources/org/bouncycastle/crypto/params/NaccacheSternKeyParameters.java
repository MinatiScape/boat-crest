package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class NaccacheSternKeyParameters extends AsymmetricKeyParameter {
    public BigInteger i;
    public BigInteger j;
    public int k;

    public NaccacheSternKeyParameters(boolean z, BigInteger bigInteger, BigInteger bigInteger2, int i) {
        super(z);
        this.i = bigInteger;
        this.j = bigInteger2;
        this.k = i;
    }

    public BigInteger getG() {
        return this.i;
    }

    public int getLowerSigmaBound() {
        return this.k;
    }

    public BigInteger getModulus() {
        return this.j;
    }
}
