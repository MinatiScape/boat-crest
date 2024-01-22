package org.bouncycastle.math.ec;
/* loaded from: classes13.dex */
public class SimpleLookupTable implements ECLookupTable {

    /* renamed from: a  reason: collision with root package name */
    public final ECPoint[] f15133a;

    public SimpleLookupTable(ECPoint[] eCPointArr, int i, int i2) {
        this.f15133a = a(eCPointArr, i, i2);
    }

    public static ECPoint[] a(ECPoint[] eCPointArr, int i, int i2) {
        ECPoint[] eCPointArr2 = new ECPoint[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            eCPointArr2[i3] = eCPointArr[i + i3];
        }
        return eCPointArr2;
    }

    @Override // org.bouncycastle.math.ec.ECLookupTable
    public int getSize() {
        return this.f15133a.length;
    }

    @Override // org.bouncycastle.math.ec.ECLookupTable
    public ECPoint lookup(int i) {
        return this.f15133a[i];
    }
}
