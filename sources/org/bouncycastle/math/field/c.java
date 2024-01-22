package org.bouncycastle.math.field;

import java.math.BigInteger;
/* loaded from: classes13.dex */
public class c implements FiniteField {

    /* renamed from: a  reason: collision with root package name */
    public final BigInteger f15195a;

    public c(BigInteger bigInteger) {
        this.f15195a = bigInteger;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof c) {
            return this.f15195a.equals(((c) obj).f15195a);
        }
        return false;
    }

    @Override // org.bouncycastle.math.field.FiniteField
    public BigInteger getCharacteristic() {
        return this.f15195a;
    }

    @Override // org.bouncycastle.math.field.FiniteField
    public int getDimension() {
        return 1;
    }

    public int hashCode() {
        return this.f15195a.hashCode();
    }
}
