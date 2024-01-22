package org.bouncycastle.math.ec;
/* loaded from: classes13.dex */
public class WNafPreCompInfo implements PreCompInfo {
    public ECPoint[] preComp = null;
    public ECPoint[] preCompNeg = null;
    public ECPoint twice = null;

    public ECPoint[] getPreComp() {
        return this.preComp;
    }

    public ECPoint[] getPreCompNeg() {
        return this.preCompNeg;
    }

    public ECPoint getTwice() {
        return this.twice;
    }

    public void setPreComp(ECPoint[] eCPointArr) {
        this.preComp = eCPointArr;
    }

    public void setPreCompNeg(ECPoint[] eCPointArr) {
        this.preCompNeg = eCPointArr;
    }

    public void setTwice(ECPoint eCPoint) {
        this.twice = eCPoint;
    }
}
