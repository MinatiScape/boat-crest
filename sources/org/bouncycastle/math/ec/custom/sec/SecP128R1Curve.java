package org.bouncycastle.math.ec.custom.sec;

import java.math.BigInteger;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECFieldElement;
import org.bouncycastle.math.ec.ECLookupTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.raw.Nat128;
import org.bouncycastle.util.encoders.Hex;
/* loaded from: classes13.dex */
public class SecP128R1Curve extends ECCurve.AbstractFp {
    public static final BigInteger q = new BigInteger(1, Hex.decode("FFFFFFFDFFFFFFFFFFFFFFFFFFFFFFFF"));
    public SecP128R1Point infinity;

    /* loaded from: classes13.dex */
    public class a implements ECLookupTable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ int f15142a;
        public final /* synthetic */ int[] b;

        public a(int i, int[] iArr) {
            this.f15142a = i;
            this.b = iArr;
        }

        @Override // org.bouncycastle.math.ec.ECLookupTable
        public int getSize() {
            return this.f15142a;
        }

        @Override // org.bouncycastle.math.ec.ECLookupTable
        public ECPoint lookup(int i) {
            int[] create = Nat128.create();
            int[] create2 = Nat128.create();
            int i2 = 0;
            for (int i3 = 0; i3 < this.f15142a; i3++) {
                int i4 = ((i3 ^ i) - 1) >> 31;
                for (int i5 = 0; i5 < 4; i5++) {
                    int i6 = create[i5];
                    int[] iArr = this.b;
                    create[i5] = i6 ^ (iArr[i2 + i5] & i4);
                    create2[i5] = create2[i5] ^ (iArr[(i2 + 4) + i5] & i4);
                }
                i2 += 8;
            }
            return SecP128R1Curve.this.createRawPoint(new SecP128R1FieldElement(create), new SecP128R1FieldElement(create2), false);
        }
    }

    public SecP128R1Curve() {
        super(q);
        this.infinity = new SecP128R1Point(this, null, null);
        this.f15127a = fromBigInteger(new BigInteger(1, Hex.decode("FFFFFFFDFFFFFFFFFFFFFFFFFFFFFFFC")));
        this.b = fromBigInteger(new BigInteger(1, Hex.decode("E87579C11079F43DD824993C2CEE5ED3")));
        this.order = new BigInteger(1, Hex.decode("FFFFFFFE0000000075A30D1B9038A115"));
        this.cofactor = BigInteger.valueOf(1L);
        this.coord = 2;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECCurve cloneCurve() {
        return new SecP128R1Curve();
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECLookupTable createCacheSafeLookupTable(ECPoint[] eCPointArr, int i, int i2) {
        int[] iArr = new int[i2 * 4 * 2];
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            ECPoint eCPoint = eCPointArr[i + i4];
            Nat128.copy(((SecP128R1FieldElement) eCPoint.getRawXCoord()).x, 0, iArr, i3);
            int i5 = i3 + 4;
            Nat128.copy(((SecP128R1FieldElement) eCPoint.getRawYCoord()).x, 0, iArr, i5);
            i3 = i5 + 4;
        }
        return new a(i2, iArr);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, boolean z) {
        return new SecP128R1Point(this, eCFieldElement, eCFieldElement2, z);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint createRawPoint(ECFieldElement eCFieldElement, ECFieldElement eCFieldElement2, ECFieldElement[] eCFieldElementArr, boolean z) {
        return new SecP128R1Point(this, eCFieldElement, eCFieldElement2, eCFieldElementArr, z);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECFieldElement fromBigInteger(BigInteger bigInteger) {
        return new SecP128R1FieldElement(bigInteger);
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public int getFieldSize() {
        return q.bitLength();
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public ECPoint getInfinity() {
        return this.infinity;
    }

    public BigInteger getQ() {
        return q;
    }

    @Override // org.bouncycastle.math.ec.ECCurve
    public boolean supportsCoordinateSystem(int i) {
        return i == 2;
    }
}
