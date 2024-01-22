package org.bouncycastle.crypto.params;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class CramerShoupPrivateKeyParameters extends CramerShoupKeyParameters {
    public BigInteger j;
    public BigInteger k;
    public BigInteger l;
    public BigInteger m;
    public BigInteger n;
    public CramerShoupPublicKeyParameters o;

    public CramerShoupPrivateKeyParameters(CramerShoupParameters cramerShoupParameters, BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4, BigInteger bigInteger5) {
        super(true, cramerShoupParameters);
        this.j = bigInteger;
        this.k = bigInteger2;
        this.l = bigInteger3;
        this.m = bigInteger4;
        this.n = bigInteger5;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public boolean equals(Object obj) {
        if (obj instanceof CramerShoupPrivateKeyParameters) {
            CramerShoupPrivateKeyParameters cramerShoupPrivateKeyParameters = (CramerShoupPrivateKeyParameters) obj;
            return cramerShoupPrivateKeyParameters.getX1().equals(this.j) && cramerShoupPrivateKeyParameters.getX2().equals(this.k) && cramerShoupPrivateKeyParameters.getY1().equals(this.l) && cramerShoupPrivateKeyParameters.getY2().equals(this.m) && cramerShoupPrivateKeyParameters.getZ().equals(this.n) && super.equals(obj);
        }
        return false;
    }

    public CramerShoupPublicKeyParameters getPk() {
        return this.o;
    }

    public BigInteger getX1() {
        return this.j;
    }

    public BigInteger getX2() {
        return this.k;
    }

    public BigInteger getY1() {
        return this.l;
    }

    public BigInteger getY2() {
        return this.m;
    }

    public BigInteger getZ() {
        return this.n;
    }

    @Override // org.bouncycastle.crypto.params.CramerShoupKeyParameters
    public int hashCode() {
        return ((((this.j.hashCode() ^ this.k.hashCode()) ^ this.l.hashCode()) ^ this.m.hashCode()) ^ this.n.hashCode()) ^ super.hashCode();
    }

    public void setPk(CramerShoupPublicKeyParameters cramerShoupPublicKeyParameters) {
        this.o = cramerShoupPublicKeyParameters;
    }
}
