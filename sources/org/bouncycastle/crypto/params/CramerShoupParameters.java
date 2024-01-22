package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.Digest;
/* loaded from: classes13.dex */
public class CramerShoupParameters implements CipherParameters {
    public BigInteger h;
    public BigInteger i;
    public BigInteger j;
    public Digest k;

    public CramerShoupParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest) {
        this.h = bigInteger;
        this.i = bigInteger2;
        this.j = bigInteger3;
        this.k = digest;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DSAParameters) {
            CramerShoupParameters cramerShoupParameters = (CramerShoupParameters) obj;
            return cramerShoupParameters.getP().equals(this.h) && cramerShoupParameters.getG1().equals(this.i) && cramerShoupParameters.getG2().equals(this.j);
        }
        return false;
    }

    public BigInteger getG1() {
        return this.i;
    }

    public BigInteger getG2() {
        return this.j;
    }

    public Digest getH() {
        this.k.reset();
        return this.k;
    }

    public BigInteger getP() {
        return this.h;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getG1().hashCode()) ^ getG2().hashCode();
    }
}
