package org.bouncycastle.jce.spec;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class GOST3410PublicKeyParameterSetSpec {

    /* renamed from: a  reason: collision with root package name */
    public BigInteger f15122a;
    public BigInteger b;
    public BigInteger c;

    public GOST3410PublicKeyParameterSetSpec(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f15122a = bigInteger;
        this.b = bigInteger2;
        this.c = bigInteger3;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GOST3410PublicKeyParameterSetSpec) {
            GOST3410PublicKeyParameterSetSpec gOST3410PublicKeyParameterSetSpec = (GOST3410PublicKeyParameterSetSpec) obj;
            return this.c.equals(gOST3410PublicKeyParameterSetSpec.c) && this.f15122a.equals(gOST3410PublicKeyParameterSetSpec.f15122a) && this.b.equals(gOST3410PublicKeyParameterSetSpec.b);
        }
        return false;
    }

    public BigInteger getA() {
        return this.c;
    }

    public BigInteger getP() {
        return this.f15122a;
    }

    public BigInteger getQ() {
        return this.b;
    }

    public int hashCode() {
        return (this.c.hashCode() ^ this.f15122a.hashCode()) ^ this.b.hashCode();
    }
}
