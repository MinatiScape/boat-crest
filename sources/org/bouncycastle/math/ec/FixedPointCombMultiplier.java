package org.bouncycastle.math.ec;

import java.math.BigInteger;
import org.bouncycastle.math.raw.Nat;
/* loaded from: classes13.dex */
public class FixedPointCombMultiplier extends AbstractECMultiplier {
    public int getWidthForCombSize(int i) {
        return i > 257 ? 6 : 5;
    }

    @Override // org.bouncycastle.math.ec.AbstractECMultiplier
    public ECPoint multiplyPositive(ECPoint eCPoint, BigInteger bigInteger) {
        int combSize;
        ECCurve curve = eCPoint.getCurve();
        if (bigInteger.bitLength() <= FixedPointUtil.getCombSize(curve)) {
            FixedPointPreCompInfo precompute = FixedPointUtil.precompute(eCPoint);
            ECLookupTable lookupTable = precompute.getLookupTable();
            int width = precompute.getWidth();
            int i = ((combSize + width) - 1) / width;
            ECPoint infinity = curve.getInfinity();
            int i2 = width * i;
            int[] fromBigInteger = Nat.fromBigInteger(i2, bigInteger);
            int i3 = i2 - 1;
            for (int i4 = 0; i4 < i; i4++) {
                int i5 = 0;
                for (int i6 = i3 - i4; i6 >= 0; i6 -= i) {
                    i5 = (i5 << 1) | Nat.getBit(fromBigInteger, i6);
                }
                infinity = infinity.twicePlus(lookupTable.lookup(i5));
            }
            return infinity.add(precompute.getOffset());
        }
        throw new IllegalStateException("fixed-point comb doesn't support scalars larger than the curve order");
    }
}
