package org.bouncycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class ECParameterSpec implements AlgorithmParameterSpec {

    /* renamed from: a  reason: collision with root package name */
    public ECCurve f15118a;
    public byte[] b;
    public ECPoint c;
    public BigInteger d;
    public BigInteger e;

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this.f15118a = eCCurve;
        this.c = eCPoint.normalize();
        this.d = bigInteger;
        this.e = BigInteger.valueOf(1L);
        this.b = null;
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this.f15118a = eCCurve;
        this.c = eCPoint.normalize();
        this.d = bigInteger;
        this.e = bigInteger2;
        this.b = null;
    }

    public ECParameterSpec(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f15118a = eCCurve;
        this.c = eCPoint.normalize();
        this.d = bigInteger;
        this.e = bigInteger2;
        this.b = bArr;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECParameterSpec) {
            ECParameterSpec eCParameterSpec = (ECParameterSpec) obj;
            return getCurve().equals(eCParameterSpec.getCurve()) && getG().equals(eCParameterSpec.getG());
        }
        return false;
    }

    public ECCurve getCurve() {
        return this.f15118a;
    }

    public ECPoint getG() {
        return this.c;
    }

    public BigInteger getH() {
        return this.e;
    }

    public BigInteger getN() {
        return this.d;
    }

    public byte[] getSeed() {
        return this.b;
    }

    public int hashCode() {
        return getCurve().hashCode() ^ getG().hashCode();
    }
}
