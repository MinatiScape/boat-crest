package org.bouncycastle.math.field;

import org.bouncycastle.util.Arrays;
/* loaded from: classes13.dex */
public class a implements Polynomial {

    /* renamed from: a  reason: collision with root package name */
    public final int[] f15193a;

    public a(int[] iArr) {
        this.f15193a = Arrays.clone(iArr);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof a) {
            return Arrays.areEqual(this.f15193a, ((a) obj).f15193a);
        }
        return false;
    }

    @Override // org.bouncycastle.math.field.Polynomial
    public int getDegree() {
        int[] iArr = this.f15193a;
        return iArr[iArr.length - 1];
    }

    @Override // org.bouncycastle.math.field.Polynomial
    public int[] getExponentsPresent() {
        return Arrays.clone(this.f15193a);
    }

    public int hashCode() {
        return Arrays.hashCode(this.f15193a);
    }
}
