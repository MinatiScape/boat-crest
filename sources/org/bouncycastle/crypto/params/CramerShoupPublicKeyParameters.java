package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class CramerShoupPublicKeyParameters extends CramerShoupKeyParameters {
    public BigInteger j;
    public BigInteger k;
    public BigInteger l;

    public CramerShoupPublicKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        super(false, cramerShoupParameters);
        this.j = bigInteger;
        this.k = bigInteger2;
        this.l = bigInteger3;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupPublicKeyParameters) {
            CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters = (CramerShoupPublicKeyParameters) obj;
            return cramerShoupPublicKeyParameters.getC().equals(this.j) && cramerShoupPublicKeyParameters.getD().equals(this.k) && cramerShoupPublicKeyParameters.getH().equals(this.l) && super.equals(obj);
        }
        return false;
    }

    public BigInteger getC() {
        return this.j;
    }

    public BigInteger getD() {
        return this.k;
    }

    public BigInteger getH() {
        return this.l;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((this.j.hashCode() ^ this.k.hashCode()) ^ this.l.hashCode()) ^ super.hashCode();
    }
}
