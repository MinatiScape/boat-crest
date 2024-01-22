package org.bouncycastle.crypto.params;

import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class ECPublicKeyParameters extends ECKeyParameters {
    public final ECPoint j;

    public ECPublicKeyParameters(ECPoint eCPoint, ECDomainParameters eCDomainParameters) {
        super(false, eCDomainParameters);
        this.j = a(eCPoint);
    }

    public final ECPoint a(ECPoint eCPoint) {
        if (eCPoint != null) {
            if (eCPoint.isInfinity()) {
                throw new IllegalArgumentException("point at infinity");
            }
            ECPoint normalize = eCPoint.normalize();
            if (normalize.isValid()) {
                return normalize;
            }
            throw new IllegalArgumentException("point not on curve");
        }
        throw new IllegalArgumentException("point has null value");
    }

    public ECPoint getQ() {
        return this.j;
    }
}
