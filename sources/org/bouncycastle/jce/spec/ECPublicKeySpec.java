package org.bouncycastle.jce.spec;

import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class ECPublicKeySpec extends ECKeySpec {
    public ECPoint i;

    public ECPublicKeySpec(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        super(eCParameterSpec);
        this.i = eCPoint.getCurve() != null ? eCPoint.normalize() : eCPoint;
    }

    public ECPoint getQ() {
        return this.i;
    }
}
