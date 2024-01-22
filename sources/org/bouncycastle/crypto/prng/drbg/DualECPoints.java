package org.bouncycastle.crypto.prng.drbg;

import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes13.dex */
public class DualECPoints {

    /* renamed from: a  reason: collision with root package name */
    public final ECPoint f14823a;
    public final ECPoint b;
    public final int c;
    public final int d;

    public DualECPoints(int i, ECPoint eCPoint, ECPoint eCPoint2, int i2) {
        if (!eCPoint.getCurve().equals(eCPoint2.getCurve())) {
            throw new IllegalArgumentException("points need to be on the same curve");
        }
        this.c = i;
        this.f14823a = eCPoint;
        this.b = eCPoint2;
        this.d = i2;
    }

    public static int a(int i) {
        int i2 = 0;
        while (true) {
            i >>= 1;
            if (i == 0) {
                return i2;
            }
            i2++;
        }
    }

    public int getCofactor() {
        return this.d;
    }

    public int getMaxOutlen() {
        return ((this.f14823a.getCurve().getFieldSize() - (a(this.d) + 13)) / 8) * 8;
    }

    public ECPoint getP() {
        return this.f14823a;
    }

    public ECPoint getQ() {
        return this.b;
    }

    public int getSecurityStrength() {
        return this.c;
    }

    public int getSeedLen() {
        return this.f14823a.getCurve().getFieldSize();
    }
}
