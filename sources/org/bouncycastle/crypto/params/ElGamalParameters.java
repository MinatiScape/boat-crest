package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class ElGamalParameters implements CipherParameters {
    public BigInteger h;
    public BigInteger i;
    public int j;

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, 0);
    }

    public ElGamalParameters(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        this.h = bigInteger2;
        this.i = bigInteger;
        this.j = i;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ElGamalParameters) {
            ElGamalParameters elGamalParameters = (ElGamalParameters) obj;
            return elGamalParameters.getP().equals(this.i) && elGamalParameters.getG().equals(this.h) && elGamalParameters.getL() == this.j;
        }
        return false;
    }

    public BigInteger getG() {
        return this.h;
    }

    public int getL() {
        return this.j;
    }

    public BigInteger getP() {
        return this.i;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG().hashCode()) + this.j;
    }
}
