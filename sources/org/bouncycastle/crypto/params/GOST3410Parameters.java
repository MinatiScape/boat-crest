package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
/* loaded from: classes13.dex */
public class GOST3410Parameters implements CipherParameters {
    public BigInteger h;
    public BigInteger i;
    public BigInteger j;
    public GOST3410ValidationParameters k;

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.h = bigInteger;
        this.i = bigInteger2;
        this.j = bigInteger3;
    }

    public GOST3410Parameters(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, GOST3410ValidationParameters gOST3410ValidationParameters) {
        this.j = bigInteger3;
        this.h = bigInteger;
        this.i = bigInteger2;
        this.k = gOST3410ValidationParameters;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410Parameters) {
            GOST3410Parameters gOST3410Parameters = (GOST3410Parameters) obj;
            return gOST3410Parameters.getP().equals(this.h) && gOST3410Parameters.getQ().equals(this.i) && gOST3410Parameters.getA().equals(this.j);
        }
        return false;
    }

    public BigInteger getA() {
        return this.j;
    }

    public BigInteger getP() {
        return this.h;
    }

    public BigInteger getQ() {
        return this.i;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.k;
    }

    public int hashCode() {
        return (this.h.hashCode() ^ this.i.hashCode()) ^ this.j.hashCode();
    }
}
