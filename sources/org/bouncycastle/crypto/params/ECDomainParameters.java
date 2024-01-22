package org.bouncycastle.crypto.params;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECConstants;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class ECDomainParameters implements ECConstants {

    /* renamed from: a  reason: collision with root package name */
    public ECCurve f14798a;
    public byte[] b;
    public ECPoint c;
    public BigInteger d;
    public BigInteger e;

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger) {
        this(eCCurve, eCPoint, bigInteger, ECConstants.ONE, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2) {
        this(eCCurve, eCPoint, bigInteger, bigInteger2, null);
    }

    public ECDomainParameters(ECCurve eCCurve, ECPoint eCPoint, BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f14798a = eCCurve;
        this.c = eCPoint.normalize();
        this.d = bigInteger;
        this.e = bigInteger2;
        this.b = bArr;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ECDomainParameters) {
            ECDomainParameters eCDomainParameters = (ECDomainParameters) obj;
            return this.f14798a.equals(eCDomainParameters.f14798a) && this.c.equals(eCDomainParameters.c) && this.d.equals(eCDomainParameters.d) && this.e.equals(eCDomainParameters.e);
        }
        return false;
    }

    public ECCurve getCurve() {
        return this.f14798a;
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
        return Arrays.clone(this.b);
    }

    public int hashCode() {
        return (((((this.f14798a.hashCode() * 37) ^ this.c.hashCode()) * 37) ^ this.d.hashCode()) * 37) ^ this.e.hashCode();
    }
}
