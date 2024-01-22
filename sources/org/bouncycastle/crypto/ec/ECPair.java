package org.bouncycastle.crypto.ec;

import org.bouncycastle.math.ec.ECPoint;
/* loaded from: classes12.dex */
public class ECPair {

    /* renamed from: a  reason: collision with root package name */
    public final ECPoint f14659a;
    public final ECPoint b;

    public ECPair(ECPoint eCPoint, ECPoint eCPoint2) {
        this.f14659a = eCPoint;
        this.b = eCPoint2;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ECPair) {
            return equals((ECPair) obj);
        }
        return false;
    }

    public boolean equals(ECPair eCPair) {
        return eCPair.getX().equals(getX()) && eCPair.getY().equals(getY());
    }

    public ECPoint getX() {
        return this.f14659a;
    }

    public ECPoint getY() {
        return this.b;
    }

    public int hashCode() {
        return this.f14659a.hashCode() + (this.b.hashCode() * 37);
    }
}
