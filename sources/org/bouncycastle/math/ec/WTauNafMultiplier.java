package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class WTauNafMultiplier extends AbstractECMultiplier {
    public static ECPoint.AbstractF2m a(ECPoint.AbstractF2m abstractF2m, byte[] bArr, PreCompInfo preCompInfo) {
        ECPoint.AbstractF2m[] d;
        ECCurve.AbstractF2m abstractF2m2 = (ECCurve.AbstractF2m) abstractF2m.getCurve();
        byte byteValue = abstractF2m2.getA().toBigInteger().byteValue();
        if (preCompInfo == null || !(preCompInfo instanceof WTauNafPreCompInfo)) {
            d = c.d(abstractF2m, byteValue);
            WTauNafPreCompInfo wTauNafPreCompInfo = new WTauNafPreCompInfo();
            wTauNafPreCompInfo.setPreComp(d);
            abstractF2m2.setPreCompInfo(abstractF2m, "bc_wtnaf", wTauNafPreCompInfo);
        } else {
            d = ((WTauNafPreCompInfo) preCompInfo).getPreComp();
        }
        ECPoint.AbstractF2m[] abstractF2mArr = new ECPoint.AbstractF2m[d.length];
        for (int i = 0; i < d.length; i++) {
            abstractF2mArr[i] = (ECPoint.AbstractF2m) d[i].negate();
        }
        ECPoint.AbstractF2m abstractF2m3 = (ECPoint.AbstractF2m) abstractF2m.getCurve().getInfinity();
        int i2 = 0;
        for (int length = bArr.length - 1; length >= 0; length--) {
            i2++;
            byte b = bArr[length];
            if (b != 0) {
                abstractF2m3 = (ECPoint.AbstractF2m) abstractF2m3.tauPow(i2).add(b > 0 ? d[b >>> 1] : abstractF2mArr[(-b) >>> 1]);
                i2 = 0;
            }
        }
        return i2 > 0 ? abstractF2m3.tauPow(i2) : abstractF2m3;
    }

    public final ECPoint.AbstractF2m b(ECPoint.AbstractF2m abstractF2m, d dVar, PreCompInfo preCompInfo, byte b, byte b2) {
        d[] dVarArr = b == 0 ? c.d : c.f;
        return a(abstractF2m, c.l(b2, dVar, (byte) 4, BigInteger.valueOf(16L), c.g(b2, 4), dVarArr), preCompInfo);
    }

    @Override // org.bouncycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        if (eCPoint instanceof ECPoint.AbstractF2m) {
            ECPoint.AbstractF2m abstractF2m = (ECPoint.AbstractF2m) eCPoint;
            ECCurve.AbstractF2m abstractF2m2 = (ECCurve.AbstractF2m) abstractF2m.getCurve();
            int fieldSize = abstractF2m2.getFieldSize();
            byte byteValue = abstractF2m2.getA().toBigInteger().byteValue();
            byte c = c.c(byteValue);
            return b(abstractF2m, c.j(bigInteger, fieldSize, byteValue, abstractF2m2.b(), c, (byte) 10), abstractF2m2.getPreCompInfo(abstractF2m, "bc_wtnaf"), byteValue, c);
        }
        throw new IllegalArgumentException("Only ECPoint.AbstractF2m can be used in WTauNafMultiplier");
    }
}
