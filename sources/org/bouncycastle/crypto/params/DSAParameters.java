package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class DSAParameters implements CipherParameters {
    public BigInteger h;
    public BigInteger i;
    public BigInteger j;
    public DSAValidationParameters k;

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.h = bigInteger3;
        this.j = bigInteger;
        this.i = bigInteger2;
    }

    public DSAParameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, DSAValidationParameters dSAValidationParameters) {
        this.h = bigInteger3;
        this.j = bigInteger;
        this.i = bigInteger2;
        this.k = dSAValidationParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof DSAParameters) {
            DSAParameters dSAParameters = (DSAParameters) obj;
            return dSAParameters.getP().equals(this.j) && dSAParameters.getQ().equals(this.i) && dSAParameters.getG().equals(this.h);
        }
        return false;
    }

    public BigInteger getG() {
        return this.h;
    }

    public BigInteger getP() {
        return this.j;
    }

    public BigInteger getQ() {
        return this.i;
    }

    public DSAValidationParameters getValidationParameters() {
        return this.k;
    }

    public int hashCode() {
        return (getP().hashCode() ^ getQ().hashCode()) ^ getG().hashCode();
    }
}
